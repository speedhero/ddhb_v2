package com.huatek.hbwebsite.saveQuery.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huatek.hbwebsite.saveQuery.entity.SaveQueryConditions;
import com.huatek.hbwebsite.service.BaseServiceToImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SaveQueryConditionsServiceImpl extends BaseServiceToImpl implements SaveQueryConditionsService {

	@Override
	public void saveQueryConditions(Map<String, String> searchMap, String type) {
		//处理searhMap的值
		Map<String, String> map = new HashMap<String, String>();
		if(searchMap != null)
			map = handleSearchMap(searchMap);
		if(map.size() > 0){
		//存储方式为json
		JSONObject json = new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		
		JSONObject member = new JSONObject();
		for(Map.Entry<String, String> item : map.entrySet()){
			member.put(item.getKey(), item.getValue());
		}
		jsonMembers.add(member);
		json.put(type, jsonMembers);
		System.out.println("json.toString" + json.toString());
		//自增长 有问题  所以就这么写了
		String hql = " select max(s.queryId) from SaveQueryConditions s  ";
		List<Integer> list = (List<Integer>) this.hibernateTemplate.find(hql);
		Integer id = null;
		if(list.size() > 0)
			id = list.get(0);
		if(id == null)
			id = 0;
		SaveQueryConditions sq = new SaveQueryConditions( id+1 ,json.toString());
		this.hibernateTemplate.save(sq);
		}
	}
	
	/**
	 * 处理searchMap的值
	 * @param map
	 * @return
	 */
	private Map<String, String> handleSearchMap(Map<String, String> searchMap){
		Map<String, String> _searchMap = new HashMap<String, String>();
		
		for(Map.Entry<String, String> item : searchMap.entrySet() ){
			if(item.getKey().startsWith("ddhb_one_")){
				_searchMap.put(item.getKey(), item.getValue());
			}else if(item.getKey().startsWith("ddhb_two_")){
				_searchMap.put(item.getKey(), item.getValue());
			}else if(item.getKey().startsWith("inputSearch")){
				_searchMap.put(item.getKey(), item.getValue());
			}
		}
		return _searchMap;
	}
}
