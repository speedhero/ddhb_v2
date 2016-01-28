/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import com.github.pagehelper.PageHelper;

import cn.hshb.web.biz.mybatis.model.NewsContent;
import cn.hshb.web.biz.mybatis.model.NewsContentExample;
import cn.hshb.web.biz.mybatis.model.NewsContentWithBLOBs;
import cn.hshb.web.biz.mybatis.model.NewsNotice;
import cn.hshb.web.biz.mybatis.model.NewsNoticeExample;
import cn.hshb.web.biz.mybatis.model.NewsType;
import cn.hshb.web.biz.mybatis.model.NewsTypeExample;
import cn.hshb.web.biz.mybatis.model.NewsTypeKey;

/**
 * @author ShengYoufu
 *
 */
public interface NewsService {
	/**
	 * 获取指定类型的新闻
	 * @param pageSize	每页记录数
	 * @param pageNum	当前页码
	 * @param typeId	新闻类别ID
	 * @return
	 */
	public List<NewsContentWithBLOBs> getNewsContent(Integer pageSize, Integer pageNum, Integer typeId);
	
	/**
	 * 获取指定类型的新闻列表
	 * @param pageSize		每页记录数
	 * @param pageNumStr	字符串页码
	 * @param typeId		新闻类别Id
	 * @return
	 */
	public List<NewsContentWithBLOBs> getNewsContent(Integer pageSize, String pageNumStr, Integer typeId);
	/**
	 * 获取各种类型的新闻
	 * @param number	每种类型新闻的条数
	 * @param newsTypeList 新闻类型
	 * @return
	 */
	public List<NewsContentWithBLOBs> getNewsContent(Integer number, List<NewsType> newsTypeList);
	
	/**
	 * 获取新闻类别 
	 * @return
	 */
	public List<NewsType> getNewsTypeList();

	/**
	 * 根据新闻编号获取新闻内容
	 * @param id
	 * @return
	 */
	public NewsContentWithBLOBs getNewsDetail(Integer id);

	/**
	 * 获取新闻类别详情
	 * @param id
	 * @return
	 */
	public NewsType getNewsTypeDetail(Integer id);

	/**
	 * 检查指定邮件地址在新闻订阅库中是否存在
	 * @param emailAddress
	 * @return
	 */
	public boolean isExist(String emailAddress);

	/**
	 * 获取所有新闻订阅邮件地址
	 * @return
	 */
	public List<NewsNotice> getEmailAddressList();

	/**
	 * 激活邮件
	 * @param id
	 * @return
	 */
	public boolean acticeEmail(Integer id);
	
	/**
	 * 更新新闻
	 * @param record
	 * @return
	 */
	public Boolean update(NewsContentWithBLOBs record);
}
