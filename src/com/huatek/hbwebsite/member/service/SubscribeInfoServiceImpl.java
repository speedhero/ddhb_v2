package com.huatek.hbwebsite.member.service;

import com.huatek.base.service.BaseServiceImpl;
import com.huatek.framework.util.CommonUtil;
import com.huatek.hbwebsite.house.entity.HouseAppraise;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.entity.SubscribeInfo;
import com.huatek.hbwebsite.member.service.SubscribeInfoService;
import java.util.List;

public class SubscribeInfoServiceImpl extends BaseServiceImpl implements SubscribeInfoService {
	public Boolean checkEmailIfExist(String email) {
		if (CommonUtil.isNotZeroLengthTrimString(email)) {
			String hsql = "from SubscribeInfo t where t.email = ?";
			List subscribeInfoList = this.hibernateTemplate.find(hsql, email);
			if (subscribeInfoList != null && subscribeInfoList.size() > 0) { return Boolean.valueOf(true); }
		}

		return Boolean.valueOf(false);
	}

	public void saveSubScribeInfo(String email, PlatMemberInfo platMemberInfo) {
		SubscribeInfo subScribeInfo = new SubscribeInfo();
		subScribeInfo.setEmail(email);
		subScribeInfo.setPlatMemberInfo(platMemberInfo);
		this.save(subScribeInfo);
	}

	public HouseAppraise getAppraiseBySearchno(String searchno) {
		String resultSql = "from HouseAppraise t where t.deleteFlag = 0 and t.searchno = ?";
		List appraiseList = this.hibernateTemplate.find(resultSql, searchno);
		return appraiseList != null && appraiseList.size() > 0 ? (HouseAppraise) appraiseList.get(0) : null;
	}

	public HouseAppraise getAppraiseByHouseNo(String houseNo) {
		String resultSql = "from HouseAppraise t where t.deleteFlag = 0 and t.houseNo = ?";
		List appraiseList = this.hibernateTemplate.find(resultSql, houseNo);
		return appraiseList != null && appraiseList.size() > 0 ? (HouseAppraise) appraiseList.get(0) : null;
	}
}
