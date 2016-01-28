/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.NewsContent;
import cn.hshb.web.biz.mybatis.model.NewsNotice;
import cn.hshb.web.biz.mybatis.model.NewsType;

/**
 * @author ShengYoufu
 *
 */
public interface NewsTypeService {
	/**
	 * 根据新闻类型查询新闻内容
	 * @param type			新闻类别
	 * @param pageNum		页码
	 * @param pageSize		每页记录数
	 * @return
	 */
	public List<NewsContent> getNewsByType(Integer newsType, Integer pageNum, Integer pageSize);

	/**
	 * 查询所有新闻类型列表
	 * @return
	 */
	public List<NewsType> getNewsTypeList();

	/**
	 * 根据ID取新闻内容
	 * @param id	新闻ID
	 * @return
	 */
	public NewsContent getNewsDetail(Long id);

	/**
	 * 根据ID取新闻类别
	 * @param id	新闻类别ID
	 * @return
	 */
	public NewsType getNewsTypeDetail(Long id) ;

	/**
	 * 根据邮件地址检测相符的新闻订阅是否存在
	 * @param emailAddress	邮件地址
	 * @return
	 */
	public boolean isNoticeExistByEmail(String emailAddress);

	/**
	 * 获取所有新闻订阅信息
	 * @return
	 */
	public List<NewsNotice> getNewsNotices();

	/**
	 * 激活订阅邮件
	 * @param noticeId	订阅ID
	 * @return
	 */
	public boolean activeEmail(String noticeId) ;
}
