DROP PROCEDURE IF EXISTS `merge_sync_data`;
DELIMITER ;;

CREATE PROCEDURE `merge_sync_data`(
IN dataType VARCHAR(100)	-- 数据类别，只合并该类别的数据
)
begin
-- ----------------------------
-- ERP向网站同步的数据合并存储过程
-- ----------------------------

	IF lcase(dataType)= 'city' THEN
		-- 更新城市_开始
    INSERT INTO common_city (erp_id, city_name, lastmodified, lastSync, DELETEflag) 
		SELECT erp_id, city_name, lastmodified, lastSync, DELETEflag FROM common_city_temp t
		ON DUPLICATE KEY UPDATE 
		city_name=t.city_name, lastmodified=t.lastmodified, lastSync=t.lastSync, DELETEflag=t.DELETEflag;
		
    DELETE FROM common_city_temp where 1 = 1;
		-- 更新城市_结束
	END IF;

	IF lcase(dataType)= 'cityarea' THEN
		-- 更新城区_开始
    DELETE FROM common_county_temp where 1 = 1;
		-- 更新城区_结束
	END IF;

	IF lcase(dataType)= 'businessdistrict' THEN
		-- 更新商圈_开始
    DELETE FROM common_cbd_temp where 1 = 1;
		-- 更新商圈_结束
	END IF;

	IF lcase(dataType)= 'subwayline' THEN
		-- 更新地铁线_开始
    INSERT INTO common_subway (erp_id, subway_name, city_id, `comment`, lastmodified, lastsync, DELETEflag) 
    SELECT erp_id, subway_name, city_id, `comment`, lastmodified, lastsync, DELETEflag FROM common_subway_temp t
		ON DUPLICATE KEY UPDATE 
		subway_name=t.subway_name, city_id=t.city_id, `comment`=t.`comment`, lastmodified=t.lastmodified, lastsync=t.lastsync, DELETEflag=t.DELETEflag;
		
    DELETE FROM common_subway_temp where 1 = 1;
		-- 更新地铁线_结束
	END IF;

	IF lcase(dataType)= 'subwaystation' THEN
		-- 更新地铁站_开始
    INSERT INTO common_subway_station (station_line_id, station_name, location, sortindex, erp_id, lastmodified, lastsync, DELETEflag) 
    SELECT station_line_id, station_name, location, sortindex, erp_id, lastmodified, lastsync, DELETEflag FROM common_subway_station_temp t
		ON DUPLICATE KEY UPDATE 
		station_line_id=t.station_line_id, station_name=t.station_name, location=t.location, sortindex=t.sortindex, lastmodified=t.lastmodified, lastsync=t.lastsync, DELETEflag=t.DELETEflag;

    DELETE FROM common_subway_station_temp where 1 = 1;
		-- 更新地铁站_结束    
	END IF;

	IF lcase(dataType)= 'subwaysubdistrictrelation' THEN
		-- 更新小区与地铁站关联关系_开始
    INSERT INTO house_community_subway_station_mapping (community_id, subway_station_id, distance, erp_id, lastsync, lastmodified, DELETEflag) 
    SELECT community_id, subway_station_id, distance, erp_id, lastsync, lastmodified, DELETEflag FROM house_community_subway_station_mapping_temp t 
		ON DUPLICATE KEY UPDATE 
		community_id=t.community_id, subway_station_id=t.subway_station_id, distance=t.distance, lastsync=t.lastsync, lastmodified=t.lastmodified, DELETEflag=t.DELETEflag;
    DELETE FROM house_community_subway_station_mapping_temp where 1 = 1;
		-- 更新小区与地铁站关联关系_结束
	END IF;

	IF lcase(dataType)= 'school' THEN
		-- 更新学校_开始
    INSERT INTO common_study_zone (region_id, sz_name, sz_type_id, gps_coordinate, isschoolzone, erp_id, lastmodified, lastsync, sortindex, `comment`, associatedschool_id, DELETEflag) 
    SELECT region_id, sz_name, sz_type_id, gps_coordinate, isschoolzone, erp_id, lastmodified, lastsync, sortindex, `comment`, associatedschool_id, DELETEflag FROM common_study_zone_temp t
		ON DUPLICATE KEY UPDATE 
		region_id=t.region_id, sz_name=t.sz_name, sz_type_id=t.sz_type_id, gps_coordinate=t.gps_coordinate, isschoolzone=t.isschoolzone, lastmodified=t.lastmodified,
		lastsync=t.lastsync, sortindex=t.sortindex, `comment`=t.`comment`, associatedschool_id=t.associatedschool_id, DELETEflag=t.DELETEflag;
		
    DELETE FROM common_study_zone_temp where 1 = 1;
		-- 更新学校_结束
	END IF;

	IF lcase(dataType)= 'schoolsubdistrictrelation' THEN
		-- 更新小区与学校关联关系_开始
    INSERT INTO house_community_school_mapping (community_id, school_id, distance, erp_id, lastmodified, lastsync, DELETEflag) 
    SELECT community_id, school_id, distance, erp_id, lastmodified, lastsync, DELETEflag FROM house_community_school_mapping_temp t
		ON DUPLICATE KEY UPDATE 
		community_id=t.community_id, school_id=t.school_id, distance=t.distance, lastmodified=t.lastmodified, lastsync=t.lastsync, DELETEflag=t.DELETEflag;
		
		DELETE FROM house_community_school_mapping_temp where 1 = 1;
		-- 更新小区与学校关联关系_结束
	END IF;

	IF lcase(dataType)= 'trainstationandbusStop' THEN
		-- 更新车站_开始
    INSERT INTO common_station (sortindex, station_name, gps_location, city_id, station_description, erp_id, lastmodified, lastsync, DELETEflag) 
    SELECT sortindex, station_name, gps_location, city_id, station_description, erp_id, lastmodified, lastsync, DELETEflag FROM common_station_temp t
		ON DUPLICATE KEY UPDATE 
		sortindex=t.sortindex, station_name=t.station_name, gps_location=t.gps_location, city_id=t.city_id, station_description=t.station_description, 
		lastmodified=t.lastmodified, lastsync=t.lastsync, DELETEflag=t.DELETEflag ;
		
    DELETE FROM common_station_temp where 1 = 1;
		-- 更新车站_结束    
	END IF;

	IF lcase(dataType)= 'stationsubdistrictrelation' THEN
		-- 更新小区与车站关联关系_开始
    INSERT INTO house_community_station_mapping (community_id, station_id, distance, erp_id, lastmodified, lastsync, DELETEflag) 
    SELECT community_id, station_id, distance, erp_id, lastmodified, lastsync, DELETEflag FROM house_community_station_mapping_temp t
		ON DUPLICATE KEY UPDATE 
		community_id=t.community_id, station_id=t.station_id, distance=t.distance, lastmodified=t.lastmodified, lastsync=t.lastsync, DELETEflag=t.DELETEflag ;
		
		DELETE FROM house_community_station_mapping_temp where 1 = 1;
		-- 更新小区与车站关联关系_结束
	END IF;

	IF lcase(dataType)= 'hospital' THEN
		-- 更新医院_开始
    INSERT INTO common_hospital_table (hospital_name, hospital_type, city_id, region_id, gps_location, erp_id, sortindex, lastmodified, lastsync, DELETEflag) 
    SELECT hospital_name, hospital_type, city_id, region_id, gps_location, erp_id, sortindex, lastmodified, lastsync, DELETEflag FROM common_hospital_table_temp t
		ON DUPLICATE KEY UPDATE 
		hospital_name=t.hospital_name, hospital_type=t.hospital_type, city_id=t.city_id, region_id=t.region_id, gps_location=t.gps_location, sortindex=t.sortindex,
		lastmodified=t.lastmodified, lastsync=t.lastsync, DELETEflag=t.DELETEflag;

    DELETE FROM common_hospital_table_temp where 1 = 1;
		-- 更新医院_结束
	END IF;

	IF lcase(dataType)= 'hospitalsubdistrictrelation' THEN
		-- 更新小区医院关联关系_开始
    INSERT INTO house_community_hospital_mapping (community_id, hospital_id, distance, erp_id, lastmodified, lastsync, DELETEflag) 
    SELECT community_id, hospital_id, distance, erp_id, lastmodified, lastsync, DELETEflag FROM house_community_hospital_mapping_temp t
		ON DUPLICATE KEY UPDATE 
		community_id=t.community_id, hospital_id=t.hospital_id, distance=t.distance, lastmodified=t.lastmodified, lastsync=t.lastsync, DELETEflag=t.DELETEflag;

    DELETE FROM house_community_hospital_mapping_temp where 1 = 1;
		-- 更新小区医院关联关系_结束
	END IF;

	IF lcase(dataType)= 'facility' THEN
		-- 更新出租屋内设施_开始
    DELETE FROM common_live_facility_temp where 1 = 1;
		-- 更新出租屋内设施_结束
	END IF;

	IF lcase(dataType)= 'storefront' THEN
		-- 更新门店_开始
    INSERT INTO common_store (store_name, store_address, telephone_no, cbd_id, erp_id, location, lastmodified, lastsync, DELETEflag) 
    SELECT store_name, store_address, telephone_no, cbd_id, erp_id, location, lastmodified, lastsync, DELETEflag FROM common_store_temp t
		ON DUPLICATE KEY UPDATE 
		store_name=t.store_name, store_address=t.store_address, telephone_no=t.telephone_no, cbd_id=t.cbd_id, location=t.location, lastmodified=t.lastmodified,
		lastsync=t.lastsync, DELETEflag=t.DELETEflag;

    DELETE FROM common_store_temp where 1 = 1;
		-- 更新门店_结束
	END IF;

	IF lcase(dataType)= 'employee' THEN
		-- 更新经纪人_开始
		INSERT INTO b_broker (erp_id, agent_id, agent_name, photograph_path, telephone, qq, wchat, store_id, introduction, 
			daikan, house_count, lastsync, lastmodified, wechat_url, DELETEflag )
		SELECT erp_id, agent_id, agent_name, photograph_path, telephone, qq, wchat, store_id, introduction,
			daikan, house_count, lastsync, lastmodified, wechat_url, DELETEflag FROM b_broker_temp t
		ON DUPLICATE KEY UPDATE 
		agent_id=t.agent_id, agent_name=t.agent_name, photograph_path=t.photograph_path, telephone=t.telephone, qq=t.qq, wchat=t.wchat,
		store_id=t.store_id, introduction=t.introduction, daikan=t.daikan, house_count=t.house_count, lastsync=t.lastsync, lastmodified=t.lastmodified,
		wechat_url=t.wechat_url, DELETEflag=t.DELETEflag;

    DELETE FROM b_broker_temp where 1 = 1;
		-- 更新经纪人_结束
	END IF;

	IF lcase(dataType)= 'expertofsubdistrict' THEN
		-- 更新小区专家_开始
    INSERT INTO house_community_expert (community_id, broker_id, erp_id, lastmodified, lastsync, DELETEflag) 
    SELECT community_id, broker_id, erp_id, lastmodified, lastsync, DELETEflag FROM house_community_expert_temp t
		ON DUPLICATE KEY UPDATE 
		community_id=t.community_id, broker_id=t.broker_id, lastmodified=t.lastmodified, lastsync=t.lastsync, DELETEflag=t.DELETEflag;
		
    DELETE FROM house_community_expert_temp where 1 = 1;
		-- 更新小区专家_结束
	END IF;

	IF lcase(dataType)= 'expertofbusinessdistrict' THEN
		-- 更新商圈专家_开始
    INSERT INTO house_cbd_expert (erp_id, community_id, broker_id, lastmodified, lastsync, DELETEflag) 
    SELECT erp_id, community_id, broker_id, lastmodified, lastsync, DELETEflag FROM house_cbd_expert_temp t
		ON DUPLICATE KEY UPDATE 
		community_id=t.community_id, broker_id=t.broker_id, lastmodified=t.lastmodified, lastsync=t.lastsync, DELETEflag=t.DELETEflag;

    DELETE FROM house_cbd_expert_temp where 1 = 1;
		-- 更新商圈_结束
	END IF;

	IF lcase(dataType)= 'question' THEN
		-- 更新用户问题_开始
    INSERT INTO common_housequestion_strategy (question_type_id, title, content, parent_question_id, create_time, 
		browsed, user_no, last_modify_time, answered_flag, isfinished, isshow, erp_id, lastmodified, lastsync, updateflag, 
		DELETEflag, synchronized_flag, synchronized_time, publisher_name) 
    SELECT question_type_id, title, content, parent_question_id, create_time, 
		browsed, user_no, last_modify_time, answered_flag, isfinished, isshow, erp_id, lastmodified, lastsync, updateflag, 
		DELETEflag, synchronized_flag, synchronized_time, publisher_name FROM common_housequestion_strategy_temp t
		ON DUPLICATE KEY UPDATE 
		question_type_id=t.question_type_id, title=t.title, content=t.content, parent_question_id=t.parent_question_id, create_time=t.create_time,
		browsed=t.browsed, user_no=t.user_no, last_modify_time=t.last_modify_time, answered_flag=t.answered_flag, isfinished=t.isfinished, 
		isshow=t.isshow, lastmodified=t.lastmodified, lastsync=t.lastsync, updateflag=t.updateflag, DELETEflag=t.DELETEflag, synchronized_flag=t.synchronized_flag,
		synchronized_time=t.synchronized_time, publisher_name=t.publisher_name;
		
    DELETE FROM common_housequestion_strategy_temp where 1 = 1;
		-- 更新用户问题_结束
	END IF;

	IF lcase(dataType)= 'answer' THEN
		-- 更新经纪人问答_开始
    INSERT INTO b_brokeranswered (broker_id, answered_content, question_id, answered_time, isshow, isaccepted, updateflag, 
		erp_id, lastmodified, lastsync, DELETEflag) 
    SELECT broker_id, answered_content, question_id, answered_time, isshow, isaccepted, updateflag, 
		erp_id, lastmodified, lastsync, DELETEflag  FROM b_brokeranswered_temp t
		ON DUPLICATE KEY UPDATE 
		broker_id=t.broker_id, answered_content=t.answered_content, question_id=t.question_id, answered_time=t.answered_time, isaccepted=t.isaccepted,
		updateflag=t.updateflag, lastmodified=t.lastmodified, lastsync=t.lastsync, DELETEflag=t.DELETEflag;
		
    DELETE FROM b_brokeranswered_temp where 1 = 1;
		-- 更新经纪人问答_结束
	END IF;

	IF lcase(dataType)= 'evaluationofhouseforlease' OR lcase(dataType)= 'evaluationofhouseforsale' THEN
		-- 更新房源评价_开始
		INSERT INTO house_appraise (erp_id, house_id, house_no, house_type, broker, search_no, title, content, appraise_date, 
		sortindex, lastmodified, lastsync, shelving_id, DELETEflag)
		SELECT * FROM(
	    SELECT
	        hat.erp_id AS erp_id,
	        hat.house_id AS house_id,
	        hshh.house_id AS house_no,
	        hat.house_type AS house_type,
	        hat.broker AS broker,
	        hat.search_no AS search_no,
	        hat.title AS title,
	        hat.content AS content,
	        hat.appraise_date AS appraise_date,
	        hat.sortindex AS sortindex,
	        hat.lastmodified AS lastmodified,
	        hat.lastsync AS lastsync,
	        hat.shelving_id AS shelving_id,
	        hat.DELETEflag AS DELETEflag
	    FROM house_appraise_temp hat
			INNER JOIN (
				SELECT house_id, shelving_id FROM house_second_hand_house
				UNION 
				SELECT house_id, shelving_id FROM house_rent_house
			) hshh
	    WHERE hat.shelving_id = hshh.shelving_id 
    ) t
		ON DUPLICATE KEY UPDATE 
		house_id=t.house_id, house_no=t.house_no, house_type=t.house_type, broker=t.broker, search_no=t.search_no,
		title=t.title, content=t.content, appraise_date=t.appraise_date, sortindex=t.sortindex, lastmodified=t.lastmodified, lastsync=t.lastsync, 
		shelving_id=t.shelving_id, DELETEflag=t.DELETEflag;
    
    DELETE FROM house_appraise_temp where 1 = 1;
		-- 更新房源评价_结束
	END IF;

	IF lcase(dataType)= 'pictureofhouseforlease' OR lcase(dataType)= 'pictureofhouseforsale' OR lcase(dataType)= 'pictureofsubdistrict' THEN
		-- 更新房源图片_开始
    INSERT INTO house_picture (house_id, house_type, picture_type, picture_name, picture_comment, picture_uri, erp_id, 
		sortindex, picture_format, layoutflag, lastmodified, lastsync, shelving_id, DELETEflag) 
    SELECT house_id, house_type, picture_type, picture_name, picture_comment, picture_uri, erp_id, 
		sortindex, picture_format, layoutflag, lastmodified, lastsync, shelving_id, DELETEflag 
		FROM house_picture_temp t
		ON DUPLICATE KEY UPDATE 
		house_id=t.house_id, house_type=t.house_type, picture_type=t.picture_type, picture_name=t.picture_name, picture_comment=t.picture_comment,
		picture_uri=t.picture_uri, sortindex=t.sortindex, picture_format=t.picture_format, layoutflag=t.layoutflag, lastmodified=t.lastmodified, 
		lastsync=t.lastsync, shelving_id=t.shelving_id, DELETEflag=t.DELETEflag;

    DELETE FROM house_picture_temp where 1 = 1;
		-- 更新房源图片_结束
	END IF;

	IF lcase(dataType)= 'subdistrict' THEN
		-- 更新小区数据_开始
    INSERT INTO house_community (erp_id, community_no, community_name, title, community_address, build_year, complate_year, 
		tents, structureType, developer, propertyManagement, Property_expense, property_type_id, location, community_area, 
		floorArea, startSaleDate, plot_ratio, landscaping_ratio, sh_count, rent_count, picture_count, business_circle_id, 
		parking_count, house_count, city_id, region_id, inital, sortindex, pinyin, introduction, lastmodified, lastsync, 
		huanbishangyue, tongbiqunian, DELETEflag, current_month_sh_avg_price, current_month_rh_avg_price, huanbishangyuerh, 
		tongbiqunianrh, nearest_store, cover_image)
		SELECT * FROM (
	    SELECT
	        hct.erp_id as erp_id,
	        hct.community_no as community_no,
	        hct.community_name as community_name,
	        hct.title as title,
	        hct.community_address as community_address,
	        hct.build_year as build_year,
	        hct.complate_year as complate_year,
	        hct.tents as tents,
	        hct.structureType as structureType,
	        hct.developer as developer,
	        hct.propertyManagement as propertyManagement,
	        hct.Property_expense as Property_expense,
	        hct.property_type_id as property_type_id,
	        hct.location as location,
	        hct.community_area as community_area,
	        hct.floorArea as floorArea,
	        hct.startSaleDate as startSaleDate,
	        hct.plot_ratio as plot_ratio,
	        hct.landscaping_ratio as landscaping_ratio,
	        case when hc.sh_count is null then hct.sh_count else hc.sh_count end as sh_count,
	        case when hc.rent_count is null then hct.rent_count else hc.rent_count end as rent_count,
	        case when hc.picture_count is null then hct.picture_count else hc.picture_count end as picture_count,
	        hct.business_circle_id as business_circle_id,
	        hct.parking_count as parking_count,
	        case when hc.house_count is null then hct.house_count else hc.house_count end as house_count,
	        hct.city_id as city_id,
	        hct.region_id as region_id,
	        hct.inital as inital,
	        hct.sortindex as sortindex,
	        hct.pinyin as pinyin,
	        hct.introduction as introduction,
	        hct.lastmodified as lastmodified,
	        hct.lastsync as lastsync,
	        case when hc.huanbishangyue is null then hct.huanbishangyue else hc.huanbishangyue end as huanbishangyue,
	        case when hc.tongbiqunian is null then hct.tongbiqunian else hc.tongbiqunian end as tongbiqunian,
	        hct.DELETEflag as DELETEflag,
	        case when hc.current_month_sh_avg_price is null then hct.current_month_sh_avg_price else hc.current_month_sh_avg_price end as current_month_sh_avg_price,
	        case when hc.current_month_rh_avg_price is null then hct.current_month_rh_avg_price else hc.current_month_rh_avg_price end as current_month_rh_avg_price,
	        case when hc.huanbishangyuerh is null then hct.huanbishangyuerh else hc.huanbishangyuerh end as huanbishangyuerh,   
	        case when hc.tongbiqunianrh is null then hct.tongbiqunianrh else hc.tongbiqunianrh end as tongbiqunianrh,
	        hct.nearest_store as nearest_store,
					hc.cover_image
	    FROM house_community_temp hct
	      left join house_community hc on hct.erp_id = hc.erp_id
    ) t
		ON DUPLICATE KEY UPDATE 
		community_no=t.community_no, community_name=t.community_name, title=t.title, community_address=t.community_address, build_year=t.build_year, 
		complate_year=t.complate_year, tents=t.tents, structureType=t.structureType, developer=t.developer, propertyManagement=t.propertyManagement, 
		Property_expense=t.Property_expense, property_type_id=t.property_type_id, location=t.location, community_area=t.community_area, 
		floorArea=t.floorArea, startSaleDate=t.startSaleDate, plot_ratio=t.plot_ratio, landscaping_ratio=t.landscaping_ratio, sh_count=t.sh_count, 
		rent_count=t.rent_count, picture_count=t.picture_count, business_circle_id=t.business_circle_id, parking_count=t.parking_count, house_count=t.house_count, 
		city_id=t.city_id, region_id=t.region_id, inital=t.inital, sortindex=t.sortindex, pinyin=t.pinyin, introduction=t.introduction, lastmodified=t.lastmodified, 
		lastsync=t.lastsync, huanbishangyue=t.huanbishangyue, tongbiqunian=t.tongbiqunian, DELETEflag=t.DELETEflag, current_month_sh_avg_price=t.current_month_sh_avg_price, 
		current_month_rh_avg_price=t.current_month_rh_avg_price, huanbishangyuerh=t.huanbishangyuerh, 
		tongbiqunianrh=t.tongbiqunianrh, nearest_store=t.nearest_store, cover_image=t.cover_image;
    
    DELETE FROM house_community_temp where 1=1;
		-- 更新小区数据_结束
	END IF;

	IF lcase(dataType)= 'houseforsale' THEN
		-- 更新二手房价格变动历史_开始
    INSERT INTO pirce_change_history ( house_id, last_price, new_price, house_type ) 
    SELECT h2t.shelving_id, ht.price, h2t.price, 1 as house_type 
      FROM house_second_hand_house_temp h2t 
        left join house_second_hand_house ht on h2t.shelving_id = ht.shelving_id 
      where h2t.price <> ht.price;


		-- 更新二手房数据_开始
    INSERT INTO house_second_hand_house (erp_id, house_id, house_status, title, content, location, price, unit_price, 
		previous_unit_price, area, shi, ting, wei, orientation_no, tags, storey, storey_count, house_vervical_location, 
		property, decoration_id, decorateYear, sortindex, usage_id, time_to_see, changerate, usage_status, publisher_id,
    community_id, browsed, appraise_count, daikan, characteristic, business_circle, haslift, lastmodified, lastsync, 
		primaryflag, exclusiveflag, focusflag, qualityflag, schoolregionflag, subwayregionflag, lastupdatecontent, hasKey, 
		homepage_recommand_flag, shelving_id, yangtai, DELETEflag, homepage_recommand_time, toErpSynFlag, house_priority, 
		cover_image ) 
		SELECT * FROM (
	    SELECT 
	        h2t.erp_id as erp_id,
	        h2t.house_id as house_id,
	        h2t.house_status as house_status,
	        h2t.title as title,
	        h2t.content as content,
	        h2t.location as location,
	        h2t.price as price,
	        h2t.unit_price as unit_price,
	        case when h2.unit_price is not null and h2t.unit_price <> h2.unit_price then h2.unit_price else h2t.unit_price end as previous_unit_price,
	        h2t.area as area,
	        h2t.shi as shi,
	        h2t.ting as ting,
	        h2t.wei as wei,
	        h2t.orientation_no as orientation_no,
	        h2t.tags as tags,
	        h2t.storey as storey,
	        h2t.storey_count as storey_count,
	        h2t.house_vervical_location as house_vervical_location,
	        h2t.property as property,
	        h2t.decoration_id as decoration_id,
	        h2t.decorateYear as decorateYear,
	        h2t.sortindex as sortindex,
	        h2t.usage_id as usage_id,
	        h2t.time_to_see as time_to_see,
	        h2t.changerate as changerate,
	        h2t.usage_status as usage_status,
	        h2t.publisher_id as publisher_id,
	        h2t.community_id as community_id,
	        case when h2.browsed is null then h2t.browsed else h2.browsed end as browsed,
	        h2t.appraise_count as appraise_count,
	        case when h2.daikan is null then h2t.daikan else h2.daikan end as daikan,
	        h2t.characteristic as characteristic,
	        h2t.business_circle as business_circle,
	        h2t.haslift as haslift,
	        h2t.lastmodified as lastmodified,
	        h2t.lastsync as lastsync,
	        h2t.primaryflag as primaryflag,
	        h2t.exclusiveflag as exclusiveflag,
	        h2t.focusflag as focusflag,
	        h2t.qualityflag as qualityflag,
	        h2t.schoolregionflag as schoolregionflag,
	        h2t.subwayregionflag as subwayregionflag,
	        h2t.lastupdatecontent as lastupdatecontent,
	        h2t.hasKey as hasKey,
	        case when h2.homepage_recommand_flag is null then h2t.homepage_recommand_flag else h2.homepage_recommand_flag end as homepage_recommand_flag,
	        h2t.shelving_id as shelving_id,
	        h2t.yangtai as yangtai,
	        h2t.DELETEflag as DELETEflag,
					h2.homepage_recommand_time as homepage_recommand_time,
					h2t.toErpSynFlag as toErpSynFlag,
					h2.house_priority,
	        h2.cover_image  
	        FROM house_second_hand_house_temp h2t 
	          left join house_second_hand_house h2 on h2t.shelving_id = h2.shelving_id
    ) t
		ON DUPLICATE KEY UPDATE 
		house_id=t.house_id, house_status=t.house_status, title=t.title, content=t.content, location=t.location, price=t.price, unit_price=t.unit_price, 
		previous_unit_price=t.previous_unit_price, area=t.area, shi=t.shi, ting=t.ting, wei=t.wei, orientation_no=t.orientation_no, tags=t.tags, 
		storey=t.storey, storey_count=t.storey_count, house_vervical_location=t.house_vervical_location, property=t.property, decoration_id=t.decoration_id, 
		decorateYear=t.decorateYear, sortindex=t.sortindex, usage_id=t.usage_id, time_to_see=t.time_to_see, changerate=t.changerate, usage_status=t.usage_status, 
		publisher_id=t.publisher_id, community_id=t.community_id, browsed=t.browsed, appraise_count=t.appraise_count, daikan=t.daikan, characteristic=t.characteristic, 
		business_circle=t.business_circle, haslift=t.haslift, lastmodified=t.lastmodified, lastsync=t.lastsync, primaryflag=t.primaryflag, exclusiveflag=t.exclusiveflag, 
		focusflag=t.focusflag, qualityflag=t.qualityflag, schoolregionflag=t.schoolregionflag, subwayregionflag=t.subwayregionflag, lastupdatecontent=t.lastupdatecontent, 
		hasKey=t.hasKey, homepage_recommand_flag=t.homepage_recommand_flag, shelving_id=t.shelving_id, yangtai=t.yangtai, DELETEflag=t.DELETEflag, 
		homepage_recommand_time=t.homepage_recommand_time, toErpSynFlag=t.toErpSynFlag, house_priority=t.toErpSynFlag, cover_image=t.cover_image;
		
		DELETE FROM house_second_hand_house_temp;
		-- 更新二手房数据_结束
	END IF;

	IF lcase(dataType)= 'houseforlease' THEN
		-- 更新出租房数据_开始
		INSERT INTO house_rent_house (erp_id, house_id, usage_status, title, content, previous_rent_price, rent_price, area, 
		usage_id, shi, appraise_count, ting, wei, rent_type_id, orientation_no, tags, payfor_way, storey, storey_count, 
		house_vervical_location, decoration_id, decoration_time, time_to_see, browsed, daikan, sortindex, community_id, 
		publisher_id, furniture, haslift, lastmodified, lastsync, Property, primaryflag, focusflag, quality, haskey, 
		schoolregionflag, subwayregionflag, lastupdatecontent, homepage_recommand_flag, shelving_id, yangtai, DELETEflag, 
		homepage_recommand_time, toErpSynFlag, house_priority, exclusiveflag, cover_image )
		SELECT * FROM (
				SELECT 
						h2r.erp_id as erp_id,
						h2r.house_id as house_id,
						h2r.usage_status as usage_status,
						h2r.title as title,
						h2r.content as content,
						case when (hr.rent_price is not null and h2r.rent_price <> hr.rent_price) then hr.rent_price else h2r.rent_price end as previous_rent_price,
						h2r.rent_price as rent_price,
						h2r.area as area,
						h2r.usage_id as usage_id,
						h2r.shi as shi,
						h2r.appraise_count as appraise_count,
						h2r.ting as ting,
						h2r.wei as wei,
						h2r.rent_type_id as rent_type_id,
						h2r.orientation_no as orientation_no,
						h2r.tags as tags,
						h2r.payfor_way as payfor_way,
						h2r.storey as storey,
						h2r.storey_count as storey_count,
						h2r.house_vervical_location as house_vervical_location,
						h2r.decoration_id as decoration_id,
						h2r.decoration_time as decoration_time,
						h2r.time_to_see as time_to_see,
						case when (hr.browsed is null) then h2r.browsed else hr.browsed	end as browsed,
						case when (hr.daikan is null) then h2r.daikan else	hr.daikan	end as daikan,
						h2r.sortindex as sortindex,
						h2r.community_id as community_id,
						h2r.publisher_id as publisher_id,
						h2r.furniture as furniture,
						h2r.haslift as haslift,
						h2r.lastmodified as lastmodified,
						h2r.lastsync as lastsync,
						h2r.Property as Property,
						h2r.primaryflag as primaryflag,
						h2r.focusflag as focusflag,
						h2r.quality as quality,
						h2r.haskey as haskey,
						h2r.schoolregionflag as schoolregionflag,
						h2r.subwayregionflag as subwayregionflag,
						h2r.lastupdatecontent as lastupdatecontent,
						case when (hr.homepage_recommand_flag is null) then	h2r.homepage_recommand_flag else hr.homepage_recommand_flag	end as homepage_recommand_flag,
						h2r.shelving_id as shelving_id,
						h2r.yangtai as yangtai,
						h2r.DELETEflag as DELETEflag,
						hr.homepage_recommand_time as homepage_recommand_time,
						h2r.toErpSynFlag as toErpSynFlag ,
						hr.house_priority,
						hr.exclusiveflag,
						hr.cover_image 
				FROM house_rent_house_temp h2r 
				  left join house_rent_house hr on h2r.shelving_id = hr.shelving_id
			) t
			ON DUPLICATE KEY UPDATE
			house_id=t.house_id, usage_status=t.usage_status, title=t.title, content=t.content, previous_rent_price=t.previous_rent_price, rent_price=t.rent_price, 
			area=t.area, usage_id=t.usage_id, shi=t.shi, appraise_count=t.appraise_count, ting=t.ting, wei=t.wei, rent_type_id=t.rent_type_id, 
			orientation_no=t.orientation_no, tags=t.tags, payfor_way=t.payfor_way, storey=t.storey, storey_count=t.storey_count, 
			house_vervical_location=t.house_vervical_location, decoration_id=t.decoration_id, decoration_time=t.decoration_time, time_to_see=t.time_to_see, 
			browsed=t.browsed, daikan=t.daikan, sortindex=t.sortindex, community_id=t.community_id, publisher_id=t.publisher_id, furniture=t.furniture, 
			haslift=t.haslift, lastmodified=t.lastmodified, lastsync=t.lastsync, Property=t.Property, primaryflag=t.primaryflag, focusflag=t.focusflag, 
			quality=t.quality, haskey=t.haskey, schoolregionflag=t.schoolregionflag, subwayregionflag=t.subwayregionflag, lastupdatecontent=t.lastupdatecontent, 
			homepage_recommand_flag=t.homepage_recommand_flag, shelving_id=t.shelving_id, yangtai=t.yangtai, DELETEflag=t.DELETEflag, 
			homepage_recommand_time=t.homepage_recommand_time, toErpSynFlag=t.toErpSynFlag, house_priority=t.house_priority, exclusiveflag=t.exclusiveflag, 
			cover_image=t.cover_image;
			
			DELETE FROM house_rent_house_temp;
			-- 更新出租房数据_结束
	END IF;

	
	-- 计算相关表的统计列_开始
	-- CALL backjob_calccountandprice();
	-- 计算相关表的统计列_结束 
end