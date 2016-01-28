package com.huatek.hbwebsite.member.service;

import com.huatek.framework.tag.CutPageBean;
import com.huatek.hbwebsite.common.entity.HouseReduceNotice;
import com.huatek.hbwebsite.service.BaseServiceTo;
import java.util.List;

public interface HouseReduceNoticeService extends BaseServiceTo {
	int ifHouseNotice(String var1, long var2);

	List<HouseReduceNotice> getHouseNotice(String var1, long var2);

	CutPageBean getHouseReduceNotice(CutPageBean var1, Long var2);

	int getNoticeCount(Long var1);

	boolean deletePriceNotice(String var1, Long var2);

	boolean validEmail(String var1);

	boolean acticeEmail(String var1);

	boolean validPhone(String var1);

	boolean valiDatas(Long var1, Integer var2, String var3);
}
