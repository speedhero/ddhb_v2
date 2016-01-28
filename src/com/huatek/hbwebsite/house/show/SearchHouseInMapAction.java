package com.huatek.hbwebsite.house.show;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import cn.hshb.web.partner.baidu.common.StringUtil;

import com.huatek.hbwebsite.community.service.CommunityService;
import com.huatek.hbwebsite.house.service.HouseSecondService;
import com.huatek.hbwebsite.rent.service.RentService;

/**
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
@Controller
@RequestMapping({ "/map.show" })
public class SearchHouseInMapAction {
	
	@Autowired
	CommunityService communityService;

	@Autowired
	HouseSecondService houseSecondService;

	@Autowired
	private RentService rentService;
	
	
	/**
	 * 初始化地图
	 */
	@RequestMapping(params = { "actionMethod=init" })
	public String init(Model model, HttpServletRequest request, HttpServletResponse reponse){
		return "secondHouse.map";
	}
	
	/**
	 * 地图找二手房
	 * 这里定义actionMethod=api是为了与javascript接口中一致
	 * 
	 * 取数URL:
	 * http://localhost:8080/map/api/?dataType=area&zoom=11  //取城区
	 * http://localhost:8080/map/api/?dataType=community&zoom=16&swLng=120.196001&swLat=30.196661&neLng=120.263194&neLat=30.227871&line=  //取小区数据
	 *
	 * 
	 * @param request
	 * @param reponse
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=api" })
	public void searchApi(Model model, HttpServletRequest request, HttpServletResponse reponse, PrintWriter printWriter ) {
		getHouseCount(model, request, reponse, printWriter);
	}
	@RequestMapping(params = { "actionMethod=getHouseCount" })
	public void getHouseCount(Model model, HttpServletRequest request, HttpServletResponse reponse, PrintWriter printWriter) {
		Map<String, Object> param = model.asMap();
		String dataType = (String)param.get("dataType");
		String zoom = (String)param.get("zoom");
		String lng = (String)param.get("swLng");
		String lat = (String)param.get("swLat");
		
		Map<String, Object> datMap = null;
		if("area".equals(dataType)){
			//城区
			datMap = findHouseByArea(zoom);
			
		}else if("community".equals(dataType)){
			//小区
			
		}

//		//return datMap;
//		String jsonString = JSON.toJSONString(datMap);
//    printWriter.write(jsonString);
//    printWriter.flush();
//    printWriter.close();
	}

	/**
	 * 根据城区统计房源数
	 * 
	 * 表中加字段: 给城区表增加地理位置坐标
ALTER TABLE `common_county`
ADD COLUMN `location`  varchar(30) NULL COMMENT '城区坐标（以区政府所在地坐标为准）' AFTER `sort_flag`;

ALTER TABLE `common_county_temp`
ADD COLUMN `location`  varchar(30) NULL COMMENT '城区坐标（以区政府所在地坐标为准）' AFTER `sort_flag`;

{
    "houses": [
        {
            "countNumber": 4191,
            "exchangeNumber": "9131",
            "rentNumber": "3349",
            "title": "拱墅",
            "point": "120.15243|30.32502",
            "isOpen": 0,
            "icon": "w:20,h:29",
            "type": 2,
            "id": "1094"
        },
				...
        {
            "countNumber": 4566,
            "exchangeNumber": "21785",
            "rentNumber": "7439",
            "title": "西湖",
            "point": "120.13438|30.26636",
            "isOpen": 0,
            "icon": "w:20,h:29",
            "type": 2,
            "id": "1092"
        }
    ],
    "zoom": "10"
}
	 * @param model
	 */
	private Map<String, Object> findHouseByArea(String zoom){
		List hsList = this.houseSecondService.getHouseCountByArea();
		List hrList = this.rentService.getHouseCountByArea();
		Map<String, Map<String, String>> dataMap = new HashMap<String, Map<String, String>>();
		Iterator it = hsList.iterator();
		while(it.hasNext()){
			Object[] vals = (Object[])it.next();
			String areaName = vals[1].toString();
			Map<String, String> row = null;
			if(!dataMap.containsKey(areaName)){
				row = new HashMap<String, String>();
				dataMap.put(areaName, row);
			}else{
				row = dataMap.get(areaName);
			}

			row.put("id", vals[0].toString());
			row.put("title", areaName);
			row.put("countNumber", "0");
			row.put("exchangeNumber", vals[3].toString());
			String point = vals[2].toString();
			point = point==null ? "0|0" : point.replace(",", "|");
			row.put("point", point);
			row.put("isOpen", "0");
			row.put("icon", "w:20,h:29");
			row.put("type", "2");
		}
		
		it = hrList.iterator();
		while(it.hasNext()){
			Object[] vals = (Object[])it.next();
			String areaName = vals[1].toString();
			Map<String, String> row = null;
			if(!dataMap.containsKey(areaName)){
				row = new HashMap<String, String>();
				dataMap.put(areaName, row);
			}else{
				row = dataMap.get(areaName);
			}
			row.put("rentNumber", vals[3].toString());
		}
		List<Map<String, String>> datList = new ArrayList<Map<String, String>>();
		for(Map.Entry<String, Map<String, String>> e: dataMap.entrySet()){
			datList.add(e.getValue());
		}
		Map<String, Object> datMap = new HashMap<String, Object>();
		datMap.put("houses", datList);
		datMap.put("zoom", zoom);
		
		//JSONArray json = JSONArray.fromObject(datMap); 
		
		return datMap;
	}
	
	/**
{
    "houses": [
        {
            "count": 23,
            "salecount": 9,
            "rentcount": 14,
            "selladd": "朝阳区安华里四区",
            "exchange_price": "45225",
            "averagePrice": "45225",
            "rent_one_price": "45225",
            "imgurl": "/themes/new2013/files/common/images/error/default_middle.jpg",
            "percentage": "0",
            "priceType": 1,
            "id": 10780,
            "title": "外馆东街",
            "point": "120.17467|30.26406",
            "isOpen": 0,
            "icon": "w:20,h:29",
            "type": 0
        },
			...
        {
            "count": 371,
            "salecount": 228,
            "rentcount": 143,
            "selladd": "文二西路与益乐路",
            "exchange_price": "29691",
            "averagePrice": "29691",
            "rent_one_price": "29691",
            "imgurl": "http://image.5i5j.com/picture/section/2/20536/poelplje85dd1fd9.JPG",
            "percentage": "0",
            "priceType": 1,
            "id": 20536,
            "title": "世纪新城",
            "point": "120.12233|30.28873",
            "isOpen": 0,
            "icon": "w:20,h:29",
            "type": 0
        }
    ],
    "recommend": "<div id=\"recommendComunity\">\r\n        <div class=\"title\">推荐小区</div>\r\n  ...",
    "zoom": "13"
}
	 * 
	 * 
	 * 根据小区统计房源数
	 * @param zoom
	 * @return
	 */
	private Map<String, Object> findHouseByComunity(String zoom){
		List hsList = this.communityService.getHouseCountByComunity();
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		
		/*
hc.erpId, hc.communityName, hc.communityAddress, hc.communityNo,
hc.currentMonthShAvgPrice, hc.currentMonthRhAvgPrice,
hc.rentCount, hc.shCount, hc.rentCount + hc.shCount as totalCount,
pic.pictureUri, hc.location, count(*)
		 */
		Iterator it = hsList.iterator();
		while(it.hasNext()){
			Object[] vals = (Object[])it.next();
			Map<String, String> row = new HashMap<String, String>();

			row.put("count", vals[8].toString());
			row.put("salecount", vals[7].toString());
			row.put("rentcount", vals[6].toString());
			row.put("selladd", vals[2].toString());
			row.put("exchange_price", vals[4].toString());
			row.put("averagePrice", vals[4].toString());
			row.put("rent_one_price", vals[5].toString());
			row.put("imgurl", vals[9].toString());
			row.put("percentage", "0");
			row.put("priceType", "1");  //房价升或降，1：升，2：降
			row.put("id", vals[0].toString());
			row.put("title", vals[1].toString());
			
			String point = vals[10].toString();
			if(StringUtil.isNotEmpty(point)){
				String[] loc = point.split(",");
				point = loc[1] + "|" + loc[0];
			}
			row.put("point", point);
			row.put("isOpen", "0");
			row.put("icon", "w:20,h:29");	//定位图标，在大图上的位置
			row.put("type", "0");
			dataList.add(row);
		}
		Map<String, Object> datMap = new HashMap<String, Object>();
		datMap.put("houses", dataList);
		datMap.put("recommend", "");   //TODO: 这里推荐小区暂时留空
		datMap.put("zoom", zoom);
		
		//JSONArray json = JSONArray.fromObject(datMap); 
		
		return datMap;
	}
}
