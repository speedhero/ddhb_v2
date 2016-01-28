package com.huatek.hbwebsite.recruit.service;

import com.huatek.base.service.BaseService;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.hbwebsite.recruit.entity.RecruitPosition;
import com.huatek.hbwebsite.recruit.entity.RecruitPositionType;
import java.util.List;

public interface RecruitService extends BaseService {
	List<RecruitPositionType> getAllRecruitPositionType();

	CutPageBean getRecruitPositionByType(CutPageBean var1, Long var2);

	RecruitPosition getPositionDetailByPositionId(Long var1);

	boolean getIsExist(long var1, String var3, String var4);
}
