/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.List;

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
import cn.hshb.web.biz.mybatis.model.NewsContentKey;
import cn.hshb.web.biz.mybatis.model.NewsNotice;
import cn.hshb.web.biz.mybatis.model.NewsNoticeExample;
import cn.hshb.web.biz.mybatis.model.NewsType;
import cn.hshb.web.biz.mybatis.model.NewsTypeExample;
import cn.hshb.web.biz.mybatis.model.NewsTypeKey;
import cn.hshb.web.biz.service.NewsTypeService;
import cn.hshb.web.common.util.StringUtil;

/**
 * @author ShengYoufu
 *
 */
@Service
public class NewsTypeServiceImpl implements NewsTypeService {
	
	private static final Log log = LogFactory.getLog(NewsTypeServiceImpl.class);
	private static final Integer PAGE_SIZE = 10;
	
	@Autowired
	private NewsContentMapper newsContentMapper;

	@Autowired
	private NewsTypeMapper newsTypeMapper;

	@Autowired
	private NewsNoticeMapper newsNoticeMapper;
	
	/**
	 * 根据新闻类型查询新闻内容
	 * @param type			新闻类别
	 * @param pageNum		页码
	 * @param pageSize		每页记录数
	 * @return
	 */
	@Override
	public List<NewsContent> getNewsByType(Integer newsType, Integer pageNum, Integer pageSize){
		NewsContentExample example = new NewsContentExample();
		example.createCriteria()
			.andNewsTypeEqualTo(newsType)
			.andDeleteflagEqualTo(0);
		example.setOrderByClause("news_id desc");
		
		Integer _pgNum = (pageNum==null || pageNum<1)? 1: pageNum;
		Integer _pgSize = (pageSize==null || pageSize<1)? PAGE_SIZE: pageSize;
		PageHelper.startPage(_pgNum, _pgSize);
		List<NewsContent> list = newsContentMapper.selectByExample(example);
		return list;
	}

	/**
	 * 查询所有新闻类型列表
	 * @return
	 */
	@Override
	public List<NewsType> getNewsTypeList() {
		NewsTypeExample example = new NewsTypeExample();
		example.createCriteria().andDeleteflagEqualTo(0);
		return newsTypeMapper.selectByExample(example);
	}

	/**
	 * 根据ID取新闻内容
	 * @param id	新闻ID
	 * @return
	 */
	@Override
	public NewsContent getNewsDetail(Long id) {
		NewsContentKey key = new NewsContentKey();
		key.setNewsId(id.intValue());
		return newsContentMapper.selectByPrimaryKey(key);
	}

	/**
	 * 根据ID取新闻类别
	 * @param id	新闻类别ID
	 * @return
	 */
	@Override
	public NewsType getNewsTypeDetail(Long id) {
		NewsTypeKey key = new NewsTypeKey();
		key.setTypeId(id.intValue());
		return newsTypeMapper.selectByPrimaryKey(key);
	}

	/**
	 * 根据邮件地址检测相符的新闻订阅是否存在
	 * @param emailAddress	邮件地址
	 * @return
	 */
	@Override
	public boolean isNoticeExistByEmail(String emailAddress) {
		NewsNoticeExample example = new NewsNoticeExample();
		example.createCriteria()
			.andEmailAddressEqualTo(emailAddress)
			.andDeleteFlagEqualTo(0);
		List<NewsNotice> list = newsNoticeMapper.selectByExample(example);
		return (list!=null && list.size()>0);
	}

	/**
	 * 获取所有新闻订阅信息
	 * @return
	 */
	@Override
	public List<NewsNotice> getNewsNotices() {
		NewsNoticeExample example = new NewsNoticeExample();
		example.createCriteria()
			.andDeleteFlagEqualTo(0);
		return newsNoticeMapper.selectByExample(example);
	}

	/**
	 * 激活订阅邮件
	 * @param noticeId	订阅ID
	 * @return
	 */
	@Override
	public boolean activeEmail(String noticeId) {
		if(StringUtil.isEmpty(noticeId)){
			log.warn("激活新闻订阅邮件失败。未指定要激活的订阅号.");
			return false;
		}
		if(!StringUtil.isNumeric(noticeId)){
			log.warn("激活新闻订阅邮件失败。指定的订阅号不是数值型.");
			return false;
		}
		NewsNoticeExample example = new NewsNoticeExample();
		example.createCriteria().andIdEqualTo(Integer.parseInt(noticeId));
		NewsNotice notice = new NewsNotice();
		notice.setEmailFlag(1);
		notice.setId(Integer.parseInt(noticeId));
		int ret = newsNoticeMapper.updateByPrimaryKeySelective(notice);
		return ret>0;
	}
}
