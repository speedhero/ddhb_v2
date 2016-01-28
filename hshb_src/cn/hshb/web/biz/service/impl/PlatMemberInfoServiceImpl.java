package cn.hshb.web.biz.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatek.framework.exception.BusinessRuntimeException;
import com.huatek.framework.security.ThreadLocalClient;
import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.EncryptService;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.service.ForbiddenService;
import com.huatek.mail.service.MailSendService;

import cn.hshb.web.biz.mybatis.dao.DdhbSystemSetMapper;
import cn.hshb.web.biz.mybatis.dao.MemberInfoMapper;
import cn.hshb.web.biz.mybatis.dao.MemberIntegralHistoryMapper;
import cn.hshb.web.biz.mybatis.dao.MemberIntegralTableMapper;
import cn.hshb.web.biz.mybatis.dao.MemberIntegrateRuleMapper;
import cn.hshb.web.biz.mybatis.model.DdhbSystemSet;
import cn.hshb.web.biz.mybatis.model.DdhbSystemSetExample;
import cn.hshb.web.biz.mybatis.model.MemberIntegralHistory;
import cn.hshb.web.biz.mybatis.model.MemberIntegralTable;
import cn.hshb.web.biz.mybatis.model.MemberIntegrateRule;
import cn.hshb.web.biz.mybatis.model.MemberIntegrateRuleExample;
import cn.hshb.web.biz.mybatis.model.MemberInfo;
import cn.hshb.web.biz.mybatis.model.MemberInfoExample;
import cn.hshb.web.biz.mybatis.model.MemberInfoKey;
import cn.hshb.web.biz.mybatis.model.MemberIntegralHistoryExample;
import cn.hshb.web.biz.mybatis.model.MemberIntegralHistoryKey;
import cn.hshb.web.biz.mybatis.model.PlatMemberInfo;
import cn.hshb.web.biz.service.PlatMemberInfoService;
import cn.hshb.web.common.exception.HshbException;

@Service
public class PlatMemberInfoServiceImpl implements PlatMemberInfoService {

	@Autowired
	private MemberInfoMapper memberInfoMapper;
	@Autowired
	private MemberIntegralHistoryMapper memberIntegralHistoryMapper;
	@Autowired
	private MemberIntegrateRuleMapper memberIntegrateRuleMapper;
	@Autowired
	private MemberIntegralTableMapper memberIntegralTableMapper;
	@Autowired
	DdhbSystemSetMapper ddhbSystemSetMapper;
	@Autowired
	EncryptService encryptServiceImpl;
	@Autowired
	MailSendService mailSendService;
	@Autowired
	ForbiddenService forbiddenService;
	@Override
	public void updateVipMemberLastLoginDate(PlatMemberInfo record) {
		memberInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public PlatMemberInfo getPlatMemberByPhoneOrEmail(PlatMemberInfo var1) {
		MemberInfoExample example = new MemberInfoExample();
		if (var1.getMobilephone() == null && var1.getEmailAddress() != null) {
			example.createCriteria().andEmailAddressEqualTo(var1.getEmailAddress());
		} else if (var1.getEmailAddress() == null && var1.getMobilephone() != null) {
			example.createCriteria().andMobilephoneEqualTo(var1.getMobilephone());
		} else {
			example.createCriteria().andEmailAddressEqualTo(var1.getEmailAddress())
					.andMobilephoneEqualTo(var1.getEmailAddress());
		}
		memberInfoMapper.selectByExample(example);
		return var1;

	}

	@Override
	public void updateVipMemberUuid(PlatMemberInfo record) {
		/*
		 * MemberInfoExample example=new MemberInfoExample();
		 * example.createCriteria().andUuidEqualTo(record.getUuid());
		 */
		memberInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public int saveOrUpdatePlatMember(PlatMemberInfo record) {
		MemberInfoExample example = new MemberInfoExample();
		List<MemberInfo> memberList = new ArrayList<MemberInfo>();

		if (record.getAccName() != null) {
			example.createCriteria().andAccNameEqualTo(record.getAccName());
			memberList = memberInfoMapper.selectByExample(example);
			if (memberList.size() > 0)
				return 0;
		} else if (record.getEmailAddress() != null) {
			example.createCriteria().andEmailAddressEqualTo(record.getEmailAddress());
			memberList = memberInfoMapper.selectByExample(example);
			if (memberList.size() > 0)
				return 2;
		} else {
			String s = UUID.randomUUID().toString();
			record.setUuid(s);
			record.setRegTime(new Date());
			record.setPassword(this.encryptServiceImpl.encrypt(record.getPassword()));
			record.setStatus("N");
			
			if (record.getMemberId()!=null)
				memberInfoMapper.updateByPrimaryKeySelective(record);
			else
				memberInfoMapper.insertSelective(record);
			return 3;
		}
		return 3;

	}

	@Override
	public int saveOrUpdatePlatMemberEditMember(PlatMemberInfo record) {

		MemberInfoExample example = new MemberInfoExample();
		List<MemberInfo> memberList = new ArrayList<MemberInfo>();

		if (record.getAccName() != null) {
			example.createCriteria().andAccNameEqualTo(record.getAccName());
			memberList = memberInfoMapper.selectByExample(example);
			if (memberList.size() > 0)
				return 0;
		} else if (record.getEmailAddress() != null) {
			example.createCriteria().andEmailAddressEqualTo(record.getEmailAddress());
			memberList = memberInfoMapper.selectByExample(example);
			if (memberList.size() > 0)
				return 2;
		} else {
			if (record.getMemberId()!=null)
				memberInfoMapper.updateByPrimaryKeySelective(record);
			else
				memberInfoMapper.insertSelective(record);
			return 3;
		}
		return 3;
	}

	@Override
	public void updatePlatMemberInfo(PlatMemberInfo record) {
		memberInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public PlatMemberInfo getAndActiveAccountByUUID(String var1) {
		PlatMemberInfo platMemberInfo = null;
		List<MemberInfo> memberList=new ArrayList<MemberInfo>();
		MemberInfoExample example=new MemberInfoExample();
		example.createCriteria().andUuidEqualTo(var1);
		memberList=memberInfoMapper.selectByExample(example);
		if(memberList.size()>0)
		{
			platMemberInfo=(PlatMemberInfo) memberList.get(0);
			if ("A".equals(platMemberInfo.getStatus().trim())) { return null; }

			platMemberInfo.setLastLogin(new Date());
			platMemberInfo.setLastIp(ThreadLocalClient.get().getHostIp());
			platMemberInfo.setLoginNum(Integer.valueOf(1));
			platMemberInfo.setStatus("A");
			platMemberInfo.setEmailValidatedFlag(1);
			memberInfoMapper.updateByPrimaryKeySelective(platMemberInfo);
		}
		return platMemberInfo;
	}

	@Override
	public PlatMemberInfo getPlatMemberInfoByUUID(String var1) {
		
		PlatMemberInfo platMemberInfo = null;
		List<MemberInfo> memberList=new ArrayList<MemberInfo>();
		MemberInfoExample example=new MemberInfoExample();
		example.createCriteria().andUuidEqualTo(var1);
		memberList=memberInfoMapper.selectByExample(example);
		if(memberList.size()>0)
		{
			platMemberInfo=(PlatMemberInfo) memberList.get(0);
		}
		return platMemberInfo;
	}

	@Override
	public PlatMemberInfo findPlatMemberInfoByUserName(String userName) {
		PlatMemberInfo platMemberInfo = null;
		MemberInfoExample example=new MemberInfoExample();
		if (CommonUtil.isNotZeroLengthTrimString(userName)) {
			if (userName.indexOf("@") != -1) {
				example.createCriteria().andEmailAddressEqualTo(userName);
			} else if (Util.getMatchResult(userName, "[0-9]*")) {
				example.createCriteria().andMobilephoneEqualTo(userName);
			} else {
				example.createCriteria().andAccNameEqualTo(userName);
			}

			List<MemberInfo> platMemberInfoList = memberInfoMapper.selectByExample(example);
			if (platMemberInfoList.size() > 0) {
				platMemberInfo = (PlatMemberInfo) platMemberInfoList.get(0);
				String s = UUID.randomUUID().toString();
				platMemberInfo.setUuid(s);
				memberInfoMapper.updateByPrimaryKey(platMemberInfo);
			}
		}
		return platMemberInfo;
	}

	@Override
	public PlatMemberInfo getPlatMemberInfoByFwAccount(Integer fwAccountId) {
		MemberInfoExample example=new MemberInfoExample();
		example.createCriteria().andAccountstatusEqualTo(fwAccountId);
		PlatMemberInfo platMemberInfo = null;
		List<MemberInfo> platMemberInfoList = memberInfoMapper.selectByExample(example);
		if (platMemberInfoList.size() > 0) {
			platMemberInfo = (PlatMemberInfo) platMemberInfoList.get(0);
		}
		return platMemberInfo;
	}

	@Override
	public PlatMemberInfo changePassword(PlatMemberInfo platMemberInfo, String newPassword) {
		String password = this.encryptServiceImpl.encrypt(newPassword);
		platMemberInfo.setPassword(password);
		platMemberInfo.setUuid("");
		memberInfoMapper.updateByPrimaryKey(platMemberInfo);
		return platMemberInfo;

	}

	@Override
	public void updatePassword(PlatMemberInfo platMemberInfo, String newPassword) {

		platMemberInfo.setPassword(this.encryptServiceImpl.encrypt(newPassword));
		memberInfoMapper.updateByPrimaryKey(platMemberInfo);
	}

	@Override
	public void modifyHeader(PlatMemberInfo platMemberInfo) {

		memberInfoMapper.updateByPrimaryKey(platMemberInfo);
	}

	@Override
	public PlatMemberInfo getCurrentMemberInfo() {
		if (ThreadLocalClient.get().getOperator() != null) {
			PlatMemberInfo platMemberInfo = this.getPlatMemberInfoByFwAccount(ThreadLocalClient.get().getOperator().getId().intValue());
			return platMemberInfo;
		} else {
			return null;
		}
	}

	@Override
	public Long saveMailSendInfoByTemplate(PlatMemberInfo platMemberInfo, String requestURL, Long mailTemplateId)
			throws HshbException {
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

	@Override
	public Object getUser(String userName, String password, String ipAddress) {
		if (!CommonUtil.isZeroLengthTrimString(userName) && !CommonUtil.isZeroLengthTrimString(password)
				&& !userName.trim().equals("用户名/邮箱/手机号码")) {
			PlatMemberInfo member = null;
			MemberInfoExample example=new MemberInfoExample();
			if (userName.indexOf("@") != -1) {
				example.createCriteria().andEmailAddressEqualTo(userName);
			} else if (Util.getMatchResult(userName, "^(([\\d]{3,4}-)?[\\d]{7,8}|[\\d]{11})$")) {
				example.createCriteria().andMobilephoneEqualTo(userName);
			} else {
				example.createCriteria().andAccNameEqualTo(userName);
			}

			List<MemberInfo> memberList=memberInfoMapper.selectByExample(example);
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
				DdhbSystemSet frontSystemSettingWrongTime = this.getFrontSystemSettingByName("count_lock_time");
				if (frontSystemSettingWrongTime != null) {
					time = Long.parseLong(frontSystemSettingWrongTime.getSetValue());
				}

				DdhbSystemSet frontSystemSettingWrongCount = this.getFrontSystemSettingByName("count_wrong_time");
				if (member.getWrongLoginCount() >= Integer.valueOf(frontSystemSettingWrongCount.getSetValue()).intValue()) {
					if (member.getWrongLoginTime() + time > calendar.getTimeInMillis() / 1000L) { throw new BusinessRuntimeException(
							"login.lock.time.error"); }

					long md5Password = calendar.getTimeInMillis();
					member.setWrongLoginTime(md5Password / 1000L);
				}

				String md5Password1 = this.encryptServiceImpl.encrypt(password);
				if (!md5Password1.equals(member.getPassword())) {
					if (member.getWrongLoginCount() < Integer.valueOf(frontSystemSettingWrongCount.getSetValue()).intValue()) {
						long time1 = calendar.getTimeInMillis();
						member.setWrongLoginTime(time1 / 1000L);
					}

					member.setWrongLoginCount(member.getWrongLoginCount() + 1);
					memberInfoMapper.insert(member);
					throw new BusinessRuntimeException("login.password.isIncorrect");
				} else if (member.getStatus().equals("N")) {
					throw new BusinessRuntimeException("account.inactive");
				} else if (member.getStatus().equals("D")) {
					throw new BusinessRuntimeException("login.account.disabled");
				} else {
					member.setWrongLoginCount(0);
					memberInfoMapper.insert(member);
					return member;
				}
			}
		} else {
			throw new BusinessRuntimeException("username.and.password.not.permit.empty");
		}
	}

	@Override
	public List<MemberIntegrateRule> getAllRulesByRules(String[] rules) {

		List<String> values=Arrays.asList(rules);
		MemberIntegrateRuleExample example=new MemberIntegrateRuleExample();
		example.createCriteria().andRuleNameIn(values).andDeleteflagEqualTo(0);
		return (List<MemberIntegrateRule>)memberIntegrateRuleMapper.selectByExample(example);
	}

	@Override
	public PlatMemberInfo getPlatMemberByEmail(String emailAccount, Long id) {
		
		MemberInfoExample example=new MemberInfoExample();
		example.createCriteria().andEmailAddressEqualTo(emailAccount).andMemberIdEqualTo(id.intValue());
		List<MemberInfo> vipMemberList = memberInfoMapper.selectByExample(example);
		PlatMemberInfo platMemberInfoNew = null;
		if (vipMemberList.size() > 0) {
			platMemberInfoNew = (PlatMemberInfo) vipMemberList.get(0);
		}

		return platMemberInfoNew;
	}

	@Override
	public PlatMemberInfo getUserByName(String username) {
		
		MemberInfoExample example=new MemberInfoExample();
		example.createCriteria().andAccNameEqualTo(username);
		List<MemberInfo> platMemberInfos=memberInfoMapper.selectByExample(example);
		return platMemberInfos != null && platMemberInfos.size() > 0 ? (PlatMemberInfo) platMemberInfos.get(0) : null;
	}

	@Override
	public DdhbSystemSet getFrontSystemSettingByName(String name) {
		DdhbSystemSetExample example=new DdhbSystemSetExample();
		example.createCriteria().andSetNameEqualTo(name).andDeleteflagEqualTo(0);
		List<DdhbSystemSet> frontSystemSettings = ddhbSystemSetMapper.selectByExample(example);
		return frontSystemSettings != null 
				&& frontSystemSettings.size() > 0 ? frontSystemSettings.get(0) : null;
	}

	@Override
	public void deletePlatMember(PlatMemberInfo var1) {
         memberInfoMapper.deleteByPrimaryKey((MemberInfoKey)var1);
	}

	@Override
	public void deleteAll(Collection<?> var1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveIntegralHistory(MemberIntegralHistory var1) {
		memberIntegralHistoryMapper.insert(var1);

	}

	@Override
	public void saveOrupdateIntegralHistory(MemberIntegralHistory var1) {
		MemberIntegralHistoryExample example=new MemberIntegralHistoryExample();
	    List<MemberIntegralHistory> list=(List<MemberIntegralHistory>) memberIntegralHistoryMapper.selectByPrimaryKey((MemberIntegralHistoryKey) var1);
        
	}

	@Override
	public void updateIntegralHistory(MemberIntegralHistory integralHistory) {
		memberIntegralHistoryMapper.updateByPrimaryKey(integralHistory);

	}

	@Override
	public void saveIntegralTable(MemberIntegralTable var1) {
		memberIntegralTableMapper.insert(var1);

	}

}
