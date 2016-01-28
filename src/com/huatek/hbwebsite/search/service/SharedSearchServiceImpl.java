package com.huatek.hbwebsite.search.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.Query;

import com.huatek.base.service.BaseServiceImpl;
import com.huatek.hbwebsite.search.entity.SharedSearch;

public class SharedSearchServiceImpl extends BaseServiceImpl implements SharedSearchService {
	public boolean isExist(String sharedUrl) {
		String fingger = this.createFinger(sharedUrl);
		String hql = "select count(ss.searchItemFinger) from SharedSearch ss where ss.searchItemFinger = ?";
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, fingger);
		Long resultCount = (Long) query.uniqueResult();
		return resultCount.longValue() != 0L;
	}

	public String save(String sharedUrl) {
		SharedSearch sharedSearch = new SharedSearch();
		String id = String.valueOf(UUID.randomUUID().hashCode());
		sharedSearch.setSearchID(id);
		sharedSearch.setDeleteFlag(0);
		sharedSearch.setSearchItemFinger(this.createFinger(sharedUrl));
		sharedSearch.setSearchContent(sharedUrl);
		this.save(sharedSearch);
		return id;
	}

	public String getSharedUrl(String id) {
		String hql = " from SharedSearch ss where ss.searchID = ? and ss.deleteFlag = 0";
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, id);
		SharedSearch ss = (SharedSearch) query.uniqueResult();
		return ss == null ? null : ss.getSearchContent();
	}

	private String createFinger(String sharedUrl) {
		int start = sharedUrl.lastIndexOf("/");
		String urlUseable = sharedUrl.substring(start + 1, sharedUrl.length());
		List<String> list = new ArrayList<String>();
		String[] searchItems = urlUseable.split("&");

		for (int ii = 0; ii < searchItems.length; ++ii) {
			String str = searchItems[ii];
			list.add(str);
		}
		Collections.sort(list);

		StringBuffer sb = new StringBuffer();
		for(String item: list)
			sb.append(item);

//		return String.valueOf(sb.toString().hashCode());
		return sb.toString();
	}

	/**
	 * 从URL中截取查询参数
	 */
	public Map<String, String> getParameterMap(String sharedUrl) {
		Map<String, String> parameterMap = new LinkedHashMap<String, String>();
		if (sharedUrl == null) {
			return parameterMap;
		} else {
			int start = sharedUrl.lastIndexOf("/");
			
			if(start>=0)
				sharedUrl = sharedUrl.substring(start + 1, sharedUrl.length());
			String[] searchItems = sharedUrl.split("&");

			if (searchItems == null) {
				return parameterMap;
			} else {
				/**
				 * Modified by syf at 2015.02.02
				for (int ii = 0; ii < searchItems.length; ++ii) {
					String str = searchItems[ii];
					if (str.indexOf("?") < 0) {
						String[] nameAndValue = str.split("=");
						if (nameAndValue.length == 2) {
							parameterMap.put(nameAndValue[0], nameAndValue[1]);
						}
					}
				}
			 */

				for(String item: searchItems){
					item = item.replace("?", "");
					String[] nameAndValue = item.split("=");
					if (nameAndValue.length == 2) {
						parameterMap.put(nameAndValue[0], nameAndValue[1]);
					}
				}
				
				return parameterMap;
			}
		}
	}

	public String getSharedUrlShort(String sharedUrl) {
		String fingger = this.createFinger(sharedUrl);
		Object shortId = null;
		String hql = " from SharedSearch ss where ss.deleteFlag = 0 and ss.searchItemFinger = ?";
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, fingger);
		SharedSearch sharedSearch = (SharedSearch) query.uniqueResult();
		return (String) (sharedSearch != null ? sharedSearch.getSearchID() : shortId);
	}
	
}
