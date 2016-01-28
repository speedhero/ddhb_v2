package com.huatek.hbwebsite.member.service;

import cn.hshb.web.common.exception.HshbException;

import com.huatek.base.service.BaseServiceImpl;
import com.huatek.ddhb.manage.frontsystemsetting.entity.FrontSystemSetting;
import com.huatek.framework.exception.BusinessRuntimeException;
import com.huatek.framework.security.ThreadLocalClient;
import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.EncryptService;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.member.entity.IntegrateRule;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.service.PlatMemberInfoService;
import com.huatek.hbwebsite.service.ForbiddenService;
import com.huatek.mail.entity.MailTemplate;
import com.huatek.mail.service.MailSendService;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

public class PlatMemberInfoServiceImpl extends BaseServiceImpl implements PlatMemberInfoService {
	@Autowired
	EncryptService encryptServiceImpl;
	@Autowired
	MailSendService mailSendService;
	@Autowired
	ForbiddenService forbiddenService;

	public int saveOrUpdatePlatMember(PlatMemberInfo platMemberInfo) {
		if (this.isDuplicate("from PlatMemberInfo t where t.accName=?", new Object[] { platMemberInfo.getAccName() },
				platMemberInfo.getId())) {
			return 0;
		} else if (this.isDuplicate("from PlatMemberInfo t where t.emailAddress=?",
				new Object[] { platMemberInfo.getEmailAddress() }, platMemberInfo.getId())) {
			return 2;
		} else {
			String s = UUID.randomUUID().toString();
			platMemberInfo.setUuid(s);
			platMemberInfo.setRegTime(new Date());
			platMemberInfo.setPassWd(this.encryptServiceImpl.encrypt(platMemberInfo.getPassWd()));
			platMemberInfo.setStatus("N");
			this.saveOrupdate(platMemberInfo);
			return 3;
		}
	}

	public int saveOrUpdatePlatMemberEditMember(PlatMemberInfo platMemberInfo) {
		if (this.isDuplicate("from PlatMemberInfo t where t.accName=?", 
				new Object[] { platMemberInfo.getAccName() }, platMemberInfo.getId())) {
			return 0;
		} else if (this.isDuplicate("from PlatMemberInfo t where t.emailAddress=?",
				new Object[] { platMemberInfo.getEmailAddress() }, platMemberInfo.getId())) {
			return 2;
		} else {
			this.saveOrupdate(platMemberInfo);
			return 3;
		}
	}

	public void updatePlatMemberInfo(PlatMemberInfo platMemberInfo) {
		this.update(platMemberInfo);
	}

	public PlatMemberInfo getAndActiveAccountByUUID(String uuId) {
		String hsql = "from PlatMemberInfo t where t.uuid = ?";
		PlatMemberInfo platMemberInfo = null;
		List platMemberInfoList = this.hibernateTemplate.find(hsql, uuId);
		if (platMemberInfoList.size() > 0) {
			platMemberInfo = (PlatMemberInfo) platMemberInfoList.get(0);
			if ("A".equals(platMemberInfo.getStatus().trim())) { return null; }

			platMemberInfo.setLastLogin(new Date());
			platMemberInfo.setLastIp(ThreadLocalClient.get().getHostIp());
			platMemberInfo.setLoginNum(Integer.valueOf(1));
			platMemberInfo.setStatus("A");
			platMemberInfo.setEmailValidationFlag(1);
			this.update(platMemberInfo);
		}

		return platMemberInfo;
	}

	public PlatMemberInfo getPlatMemberInfoByUUID(String uuId) {
		String hsql = "from PlatMemberInfo t where t.uuid = ?";
		PlatMemberInfo platMemberInfo = null;
		List platMemberInfoList = this.hibernateTemplate.find(hsql, uuId);
		if (platMemberInfoList.size() > 0) {
			platMemberInfo = (PlatMemberInfo) platMemberInfoList.get(0);
		}

		return platMemberInfo;
	}

	public void updateVipMemberLastLoginDate(PlatMemberInfo platMemberInfo) {
		super.hibernateTemplate.bulkUpdate("update PlatMemberInfo t set t.lastLogin=? where t.id=?", new Object[] {
				new Date(), platMemberInfo.getId() });
	}

	public void updateVipMemberUuid(PlatMemberInfo platMemberInfo) {
		super.hibernateTemplate.bulkUpdate("update PlatMemberInfo t set t.uuid=null where t.id=?", platMemberInfo.getId());
	}

	public PlatMemberInfo getPlatMemberByPhoneOrEmail(PlatMemberInfo platMemberInfo) {
		String loginAcount = platMemberInfo.getAccName();
		StringBuffer queryCondition = new StringBuffer("");
		if (loginAcount.indexOf("@") != -1) {
			queryCondition.append(" t.email = ?");
		} else if (Util.getMatchResult(loginAcount, "[0-9]*")) {
			queryCondition.append(" t.mobilephone = ?");
		} else {
			queryCondition.append(" t.userName = ?");
		}

		String hsql = "from PlatMemberInfo t where t.passWd = ? and " + queryCondition;
		List vipMemberList = this.hibernateTemplate.find(hsql,
				new Object[] { this.encryptServiceImpl.encrypt(platMemberInfo.getPassWd()), loginAcount });
		PlatMemberInfo platMemberInfoNew = null;
		if (vipMemberList.size() > 0) {
			platMemberInfoNew = (PlatMemberInfo) vipMemberList.get(0);
		}

		return platMemberInfoNew;
	}

	public PlatMemberInfo findPlatMemberInfoByUserName(String userName) {
		PlatMemberInfo platMemberInfo = null;
		if (CommonUtil.isNotZeroLengthTrimString(userName)) {
			StringBuffer queryCondition = new StringBuffer("");
			if (userName.indexOf("@") != -1) {
				queryCondition.append(" t.emailAddress = ?");
			} else if (Util.getMatchResult(userName, "[0-9]*")) {
				queryCondition.append(" t.mobilephone = ?");
			} else {
				queryCondition.append(" t.accName = ?");
			}

			String hsql = "from PlatMemberInfo t where 1=1 and " + queryCondition;
			List platMemberInfoList = this.hibernateTemplate.find(hsql, userName);
			if (platMemberInfoList.size() > 0) {
				platMemberInfo = (PlatMemberInfo) platMemberInfoList.get(0);
				String s = UUID.randomUUID().toString();
				platMemberInfo.setUuid(s);
				this.update(platMemberInfo);
			}
		}

		return platMemberInfo;
	}

	public PlatMemberInfo getPlatMemberInfoByFwAccount(Long fwAccountId) {
		String hsql = "from PlatMemberInfo t where t.fwAccount.id=?";
		PlatMemberInfo platMemberInfo = null;
		List platMemberInfoList = this.hibernateTemplate.find(hsql, fwAccountId);
		if (platMemberInfoList.size() > 0) {
			platMemberInfo = (PlatMemberInfo) platMemberInfoList.get(0);
		}

		return platMemberInfo;
	}

	public PlatMemberInfo changePassword(PlatMemberInfo platMemberInfo, String newPassword) {
		String password = this.encryptServiceImpl.encrypt(newPassword);
		platMemberInfo.setPassWd(password);
		platMemberInfo.setUuid("");
		this.update(platMemberInfo);
		return platMemberInfo;
	}

	public void updatePassword(PlatMemberInfo platMemberInfo, String newPassword) {
		platMemberInfo.setPassWd(this.encryptServiceImpl.encrypt(newPassword));
		this.update(platMemberInfo);
	}

	public void modifyHeader(PlatMemberInfo platMemberInfo) {
		this.update(platMemberInfo);
	}

	public PlatMemberInfo getCurrentMemberInfo() {
		if (ThreadLocalClient.get().getOperator() != null) {
			PlatMemberInfo platMemberInfo = this.getPlatMemberInfoByFwAccount(ThreadLocalClient.get().getOperator().getId());
			return platMemberInfo;
		} else {
			return null;
		}
	}

	public Long saveMailSendInfoByTemplate(PlatMemberInfo platMemberInfo, String requestURL, Long mailTemplateId) throws HshbException {
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("userName", platMemberInfo.getAccName());
		infoMap.put("activeAction", requestURL);
		infoMap.put("activeKey", platMemberInfo.getUuid());
//		MailTemplate template = (MailTemplate) this.getObjectById(MailTemplate.class, mailTemplateId);
//		Long mailSendInfoId = this.mailSendService.saveMailSendInfo(template.getTemplateName(),
//				platMemberInfo.getEmailAddress(), mailTemplateId, infoMap);
//		return mailSendInfoId;

		Map<String, String> retMap = mailSendService.saveMailSendInfo(platMemberInfo.getEmailAddress(), mailTemplateId, infoMap);
		return Long.valueOf(retMap.get(MailSendService.KEY_MAIL_RESULT_MAIL_ID));
	}

	public Object getUser(String userName, String password, String ipAddress) {
		if (!CommonUtil.isZeroLengthTrimString(userName) && !CommonUtil.isZeroLengthTrimString(password)
				&& !userName.trim().equals("用户名/邮箱/手机号码")) {
			PlatMemberInfo member = null;
			StringBuffer queryCondition = new StringBuffer("");
			if (userName.indexOf("@") != -1) {
				queryCondition.append(" t.emailAddress = ?");
			} else if (Util.getMatchResult(userName, "^(([\\d]{3,4}-)?[\\d]{7,8}|[\\d]{11})$")) {
				queryCondition.append(" t.mobilephone = ?");
			} else {
				queryCondition.append(" t.accName = ?");
			}

			String hsql = "from PlatMemberInfo t where " + queryCondition;
			List memberList = this.hibernateTemplate.find(hsql, userName);
			if (memberList.size() > 0) {
				member = (PlatMemberInfo) memberList.get(0);
			}

			if (this.forbiddenService.isInForbiddenList(ipAddress)) {
				throw new BusinessRuntimeException("userlogin.error");
			} else if (member == null) {
				throw new BusinessRuntimeException("login.account.isIncorrect");
			} else {
				long time = 0L;
				Calendar calendar = Calendar.getInstance();
				FrontSystemSetting frontSystemSettingWrongTime = this.getFrontSystemSettingByName("count_lock_time");
				if (frontSystemSettingWrongTime != null) {
					time = Long.parseLong(frontSystemSettingWrongTime.getSettingValue());
				}

				FrontSystemSetting frontSystemSettingWrongCount = this.getFrontSystemSettingByName("count_wrong_time");
				if (member.getWrongCount() >= Integer.valueOf(frontSystemSettingWrongCount.getSettingValue()).intValue()) {
					if (member.getWrongLoginTime() + time > calendar.getTimeInMillis() / 1000L) { throw new BusinessRuntimeException(
							"login.lock.time.error"); }

					long md5Password = calendar.getTimeInMillis();
					member.setWrongLoginTime(md5Password / 1000L);
				}

				String md5Password1 = this.encryptServiceImpl.encrypt(password);
				if (!md5Password1.equals(member.getPassWd())) {
					if (member.getWrongCount() < Integer.valueOf(frontSystemSettingWrongCount.getSettingValue()).intValue()) {
						long time1 = calendar.getTimeInMillis();
						member.setWrongLoginTime(time1 / 1000L);
					}

					member.setWrongCount(member.getWrongCount() + 1);
					this.save(member);
					throw new BusinessRuntimeException("login.password.isIncorrect");
				} else if (member.getStatus().equals("N")) {
					throw new BusinessRuntimeException("account.inactive");
				} else if (member.getStatus().equals("D")) {
					throw new BusinessRuntimeException("login.account.disabled");
				} else {
					member.setWrongCount(0);
					this.save(member);
					return member;
				}
			}
		} else {
			throw new BusinessRuntimeException("username.and.password.not.permit.empty");
		}
	}

	public List<IntegrateRule> getAllRulesByRules(String[] rules) {
		StringBuffer param = new StringBuffer("");

		for (int queryString = 0; queryString < rules.length; ++queryString) {
			if (queryString > 0) {
				param.append(",");
			}
			param.append("\'" + rules[queryString] + "\'");
		}

		param.toString();
		String hql = "from IntegrateRule t where t.ruleName in (" + param + ") and t.deleteFlag = 0";
		return (List<IntegrateRule>)this.hibernateTemplate.find(hql);
	}

	public PlatMemberInfo getPlatMemberByEmail(String emailAccount, Long id) {
		StringBuffer queryCondition = new StringBuffer("");
		queryCondition.append(" t.email = ?");
		String hsql = "from PlatMemberInfo t where t.emailAddress =  \'" + emailAccount + "\' and t.id != " + id;
		List vipMemberList = this.hibernateTemplate.find(hsql);
		PlatMemberInfo platMemberInfoNew = null;
		if (vipMemberList.size() > 0) {
			platMemberInfoNew = (PlatMemberInfo) vipMemberList.get(0);
		}

		return platMemberInfoNew;
	}

	public PlatMemberInfo getUserByName(String username) {
		String sqlString = "from PlatMemberInfo t where t.accName = ?";
		List platMemberInfos = this.hibernateTemplate.find(sqlString, username);
		return platMemberInfos != null && platMemberInfos.size() > 0 ? (PlatMemberInfo) platMemberInfos.get(0) : null;
	}

	public FrontSystemSetting getFrontSystemSettingByName(String name) {
		String queryString = "from FrontSystemSetting t where t.settingName = ? and t.deleteFlag = 0";
		List frontSystemSettings = this.hibernateTemplate.find(queryString, name);
		return frontSystemSettings != null 
				&& frontSystemSettings.size() > 0 ? (FrontSystemSetting) frontSystemSettings.get(0) : null;
	}
}
