/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.hshb.web.biz.mybatis.dao.NewsContentMapper;
import cn.hshb.web.biz.mybatis.dao.NewsNoticeMapper;
import cn.hshb.web.biz.mybatis.dao.NewsTypeMapper;
import cn.hshb.web.biz.mybatis.model.NewsContent;
import cn.hshb.web.biz.mybatis.model.NewsContentExample;
import cn.hshb.web.biz.mybatis.model.NewsContentWithBLOBs;
import cn.hshb.web.biz.mybatis.model.NewsNotice;
import cn.hshb.web.biz.mybatis.model.NewsNoticeExample;
import cn.hshb.web.biz.mybatis.model.NewsType;
import cn.hshb.web.biz.mybatis.model.NewsTypeExample;
import cn.hshb.web.biz.mybatis.model.NewsTypeKey;
import cn.hshb.web.biz.service.NewsService;
import cn.hshb.web.partner.baidu.common.StringUtil;

/**
 * @author ShengYoufu
 *
 */
@Service
public class NewsServiceImpl implements NewsService {
	private static final Log log = LogFactory.getLog(NewsServiceImpl.class);
	private static final Integer PAGE_SIZE = 10;
	
	@Autowired
	private NewsTypeMapper newsTypeMapper;
	
	@Autowired
	private NewsContentMapper newsContentMapper;
	
	@Autowired
	private NewsNoticeMapper newsNoticeMapper;

	/**
	 * 获取指定类型的新闻列表
	 * @param pageSize		每页记录数
	 * @param pageNumStr	字符串页码
	 * @param typeId		新闻类别Id
	 * @return
	 */
	@Override
	public List<NewsContentWithBLOBs> getNewsContent(Integer pageSize, Integer pageNum, Integer typeId){
		Integer pgSize = pageSize!=null && pageSize>0 ? pageSize : PAGE_SIZE;
		Integer pgNum = pageNum != null && pageNum>0 ?  pageNum : 1;
		PageHelper.startPage(pgNum, pgSize);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("newsType", typeId);
		return newsContentMapper.selectNewsContentWithBLOBsByNewsType(map);
	}
	/**
	 * 获取各种类型的新闻
	 * @param number	每种类型新闻的条数
	 * @return
	 */
	@Override
	public List<NewsContentWithBLOBs> getNewsContent(Integer pageSize,
			String pageNumStr, Integer typeId) {
		int pageNum = 1;
		if(StringUtil.isNotEmpty(pageNumStr)){
			Matcher ma = Pattern.compile("n([\\d]+)").matcher(pageNumStr);
			while(ma.find()){
				pageNum = Integer.parseInt(ma.group(1));
			}
		}
		return getNewsContent(pageSize, pageNum, typeId);
	}
	/**
	 * 获取各种类型的新闻
	 * @param number	每种类型新闻的条数
	 * @return
	 */
	@Override
	public List<NewsContentWithBLOBs> getNewsContent(Integer number, List<NewsType> newsTypeList) {
		Integer _number = number == null ? 5 : number;
		if(newsTypeList == null || newsTypeList.size() <1)
			newsTypeList = getNewsTypeList();
		List<NewsContentWithBLOBs> newsContentWithBLOBsList = new ArrayList<NewsContentWithBLOBs>();
		for(NewsType newsType : newsTypeList){
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("number", number);
			map.put("newsType", newsType.getTypeId());
			List<NewsContentWithBLOBs> list = newsContentMapper.selectNewsContentWithBLOBsByNewsType(map);
			if(list != null && list.size() > 0){
				for(NewsContentWithBLOBs n : list){
					newsContentWithBLOBsList.add(n);
				}
			}
		}
		return newsContentWithBLOBsList;
	}
	
	/**
	 * 获取新闻类别 
	 * @return
	 */
	@Override
	public List<NewsType> getNewsTypeList() {
		NewsTypeExample example = new NewsTypeExample();
		example.createCriteria().andDeleteflagEqualTo(0);
		
		return newsTypeMapper.selectByExample(example);
	}

	/**
	 * 根据新闻编号获取新闻内容
	 * @param id
	 * @return
	 */
	@Override
	public NewsContentWithBLOBs getNewsDetail(Integer id) {
		NewsContentExample example = new NewsContentExample();
		example.createCriteria()
			.andDeleteflagEqualTo(0)
			.andNewsIdEqualTo(id);
		List<NewsContentWithBLOBs> list = newsContentMapper.selectByExampleWithBLOBs(example);
		return list != null && list.size() > 0 ? list.get(0) : new NewsContentWithBLOBs();
	}

	/**
	 * 获取新闻类别详情
	 * @param id
	 * @return
	 */
	@Override
	public NewsType getNewsTypeDetail(Integer id) {
		NewsTypeKey key = new NewsTypeKey();
		key.setTypeId(id);
		return newsTypeMapper.selectByPrimaryKey(key);
	}

	/**
	 * 检查指定邮件地址在新闻订阅库中是否存在
	 * @param emailAddress
	 * @return
	 */
	@Override
	public boolean isExist(String emailAddress) {
		NewsNoticeExample example = new NewsNoticeExample();
		example.createCriteria()
			.andEmailAddressEqualTo(emailAddress)
			.andDeleteFlagEqualTo(0);
		int ret = newsNoticeMapper.countByExample(example);
		return ret>0;
	}

	/**
	 * 获取所有新闻订阅邮件地址
	 * @return
	 */
	@Override
	public List<NewsNotice> getEmailAddressList() {
		NewsNoticeExample example = new NewsNoticeExample();
		example.createCriteria()
			.andDeleteFlagEqualTo(0);
		return newsNoticeMapper.selectByExample(example);
	}

	/**
	 * 激活邮件
	 * @param id
	 * @return
	 */
	@Override
	public boolean acticeEmail(Integer id) {
		NewsNotice record = new NewsNotice();
		record.setEmailFlag(1);
		record.setId(id);
		int ret = newsNoticeMapper.updateByPrimaryKeySelective(record);
		return ret > 0;
	}
	
	/**
	 * 更新新闻
	 * @param record
	 * @return
	 */
	@Override
	public Boolean update(NewsContentWithBLOBs record){
		int ret = newsContentMapper.updateByPrimaryKeySelective(record);
		return ret > 0;
	}
}
