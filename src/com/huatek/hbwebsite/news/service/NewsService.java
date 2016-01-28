package com.huatek.hbwebsite.news.service;

import com.huatek.base.service.BaseService;
import com.huatek.ddhb.manage.news.entity.NewsBean;
import com.huatek.ddhb.manage.news.entity.NewsType;
import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.hbwebsite.news.entity.NewsNotice;
import java.util.List;

public interface NewsService extends BaseService {
	CutPageBean getCutPageBean(CutPageBean var1, List<CommonBean> var2, int var3);

	List<NewsType> getNewsTypeList();

	NewsBean getNewsDetail(long var1);

	NewsType getNewsTypeDetail(long var1);

	boolean isExist(String var1);

	List<NewsNotice> getEmailAddressList();

	boolean acticeEmail(String var1);
}
