package com.huatek.hbwebsite.member.service;

import com.huatek.framework.tag.CutPageBean;
import com.huatek.hbwebsite.member.entity.MemberBrowseHistory;
import com.huatek.hbwebsite.service.BaseServiceTo;
import java.util.List;

public interface MemberBrowseHistoryService extends BaseServiceTo {
	List<MemberBrowseHistory> findMemberBrowseHistoryByMember(Long var1, int var2, String var3);

	CutPageBean getBrowseHistory(CutPageBean var1, Long var2, String var3);

	List<Object> getCountByObjectType(Long var1);
}
