package com.huatek.hbwebsite.member.service;

import cn.hshb.web.common.exception.HshbException;

import com.huatek.base.service.BaseService;
import com.huatek.ddhb.manage.frontsystemsetting.entity.FrontSystemSetting;
import com.huatek.hbwebsite.member.entity.IntegrateRule;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;

import java.util.List;

public interface PlatMemberInfoService extends BaseService {
	void updateVipMemberLastLoginDate(PlatMemberInfo var1);

	PlatMemberInfo getPlatMemberByPhoneOrEmail(PlatMemberInfo var1);

	void updateVipMemberUuid(PlatMemberInfo var1);

	int saveOrUpdatePlatMember(PlatMemberInfo var1);

	int saveOrUpdatePlatMemberEditMember(PlatMemberInfo var1);

	void updatePlatMemberInfo(PlatMemberInfo var1);

	PlatMemberInfo getAndActiveAccountByUUID(String var1);

	PlatMemberInfo getPlatMemberInfoByUUID(String var1);

	PlatMemberInfo findPlatMemberInfoByUserName(String var1);

	PlatMemberInfo getPlatMemberInfoByFwAccount(Long var1);

	PlatMemberInfo changePassword(PlatMemberInfo var1, String var2);

	void updatePassword(PlatMemberInfo var1, String var2);

	void modifyHeader(PlatMemberInfo var1);

	PlatMemberInfo getCurrentMemberInfo();

	/**
	 * 根据模板ID生成邮件内容并保存到数据库
	 * @param platMemberInfo	会员信息
	 * @param requestURL	请求URL
	 * @param mailTemplateId	邮件模板ID
	 * @return	邮件ID
	 * @throws HshbException
	 */
	Long saveMailSendInfoByTemplate(PlatMemberInfo platMemberInfo, String requestURL, Long mailTemplateId) throws HshbException;

	Object getUser(String var1, String var2, String var3);

	List<IntegrateRule> getAllRulesByRules(String[] var1);

	PlatMemberInfo getPlatMemberByEmail(String var1, Long var2);

	PlatMemberInfo getUserByName(String var1);

	FrontSystemSetting getFrontSystemSettingByName(String var1);
}
