package com.huatek.hbwebsite.news.service;

import com.huatek.base.service.BaseServiceImpl;
import com.huatek.ddhb.manage.news.entity.NewsBean;
import com.huatek.ddhb.manage.news.entity.NewsType;
import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.tag.QueryPageBean;
import com.huatek.hbwebsite.news.entity.NewsNotice;
import com.huatek.hbwebsite.news.service.NewsService;
import java.util.List;

public class NewsServiceImpl extends BaseServiceImpl implements NewsService {
	public CutPageBean getCutPageBean(CutPageBean cutPageBean, List<CommonBean> commonList, int type) {
		String totalRowsHsql = "select count(t) from NewsBean t where 1=1 and t.deleteFlag = 0";
		String resultSql = "from NewsBean t where t.deleteFlag = 0";
		if (type != 0) {
			totalRowsHsql = totalRowsHsql + " and t.newsType.id = " + type;
			resultSql = resultSql + " and t.newsType.id = " + type;
		}

		CutPageBean pageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql,
				"order by t.id desc", cutPageBean, commonList);
		return pageBean;
	}

	public List<NewsType> getNewsTypeList() {
		String sql = "from NewsType t";
		List newsTypeList = this.hibernateTemplate.find(sql);
		return newsTypeList;
	}

	public NewsBean getNewsDetail(long id) {
		String sql = "from NewsBean t where 1=1 and t.deleteFlag = 0 and id = " + id;
		List list = this.hibernateTemplate.find(sql);
		return list != null && list.size() > 0 ? (NewsBean) list.get(0) : null;
	}

	public NewsType getNewsTypeDetail(long id) {
		String sql = "from NewsType t where 1=1 and t.id = " + id;
		List list = this.hibernateTemplate.find(sql);
		return list != null && list.size() > 0 ? (NewsType) list.get(0) : null;
	}

	public boolean isExist(String emailAddress) {
		String sql = "from NewsNotice t where 1=1 and t.emailAddress = \'" + emailAddress + "\'";
		List list = this.hibernateTemplate.find(sql);
		return list != null && list.size() > 0;
	}

	public List<NewsNotice> getEmailAddressList() {
		String sql = "from NewsNotice t where t.deleteFlag = 0";
		List newsNoticeList = this.hibernateTemplate.find(sql);
		return newsNoticeList;
	}

	public boolean acticeEmail(String id) {
		String delHsql = "update NewsNotice t set t.emailFlag = 1 where t.id = ? ";
		int count = this.hibernateTemplate.bulkUpdate(delHsql, Long.valueOf(id));
		return count > 0;
	}
}
