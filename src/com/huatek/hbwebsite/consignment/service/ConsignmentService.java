package com.huatek.hbwebsite.consignment.service;

import com.huatek.hbwebsite.common.entity.Area;
import com.huatek.hbwebsite.common.entity.CBDWebsite;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.common.entity.Furniture;
import com.huatek.hbwebsite.consignment.entity.EntrustBhouse;
import com.huatek.hbwebsite.consignment.entity.EntrustRenthouse;
import com.huatek.hbwebsite.consignment.entity.EntrustRentinghouse;
import com.huatek.hbwebsite.consignment.entity.EntrustSalehouse;
import com.huatek.hbwebsite.house.entity.HouseType;
import com.huatek.hbwebsite.service.BaseServiceTo;
import java.util.List;

public interface ConsignmentService extends BaseServiceTo {
	List<Area> findAllCounty();

	List<CBDWebsite> findCbd(String var1);

	List<Community> getAllCommunity();

	List<HouseType> getAllHouseType();

	List<Community> getCommunityIdByCommName(String var1);

	List<Furniture> getFurList();

	List<EntrustRenthouse> getUnsyncRentHouse();

	List<EntrustRentinghouse> getUnsyncRentingHouse();

	List<EntrustSalehouse> getUnsyncSaleHouse();

	List<EntrustBhouse> getUnsyncBuyHouse();

	void updateSyncFlag(String var1, String var2);
}
