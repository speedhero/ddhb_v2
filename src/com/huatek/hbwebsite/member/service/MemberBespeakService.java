package com.huatek.hbwebsite.member.service;

import com.huatek.base.service.BaseService;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.hbwebsite.member.entity.MemberBespeak;
import java.util.List;

public interface MemberBespeakService extends BaseService {
	List<MemberBespeak> getMemberBespeakItem(long var1);

	CutPageBean getAppointmentHouse(CutPageBean var1, Long var2);

	Long getSecondHouseCount(Long var1);

	Long getRentHouseCount(Long var1);

	boolean deleteAppointment(Long var1, Long var2);

	boolean deleteMemberCollect(Long var1, Long var2);

	List<MemberBespeak> getUnsyncBespeak();

	void updateSyncFlag(String var1);
}
