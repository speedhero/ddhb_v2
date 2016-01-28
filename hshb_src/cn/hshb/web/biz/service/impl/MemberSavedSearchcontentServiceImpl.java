package cn.hshb.web.biz.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatek.hbwebsite.member.entity.PlatMemberInfo;

import cn.hshb.web.biz.mybatis.dao.MemberSavedSearchcontentMapper;
import cn.hshb.web.biz.mybatis.model.MemberSavedSearchcontent;
import cn.hshb.web.biz.mybatis.model.MemberSavedSearchcontentExample;
import cn.hshb.web.biz.service.MemberSavedSearchcontentService;

@Service
public class MemberSavedSearchcontentServiceImpl implements MemberSavedSearchcontentService {
	//保存成功
	private static final String SAVE_SUCCESS = "{\"resultCode\":\"1\"}";
	//已存在
	private static final String SAVE_EXIST = "{\"resultCode\":\"2\"}";
	//未登录
	private static final String SAVE_NOTLOGIN = "{\"resultCode\":\"3\"}";
	
	@Autowired
	private MemberSavedSearchcontentMapper memberSavedSearchcontentMapper;
	
	@Override
	public String saveSearchFieldByMember(PlatMemberInfo accountBean,
			String savedUrl, String fieldNames, String houseType) {
		MemberSavedSearchcontent content = new MemberSavedSearchcontent();
		
		if(accountBean == null){
			 return SAVE_NOTLOGIN;
		}else {
			if(fieldNames.length() > 0 )
				fieldNames = fieldNames.substring(0,fieldNames.length()-1);
			
			fieldNames.replaceAll("<span>", "");
			fieldNames.replaceAll("</span>", "");
			
			int type = 0;
			if("chushou".equals(houseType))
				type = 1;
			else if("chuzu".equals(houseType))
				type = 2;
			else if("xiaoqu".equals(houseType))
				type = 3;
			
			//判断该条件是否已存在
			MemberSavedSearchcontentExample example = new MemberSavedSearchcontentExample();
			example.createCriteria().andMemberIdEqualTo(accountBean.getId().intValue())
			.andSavedFieldEqualTo(fieldNames)
			.andTypeEqualTo(type);
			int count = memberSavedSearchcontentMapper.countByExample(example);
			if(count > 0)
				return SAVE_EXIST;
			
			content.setNo(UUID.randomUUID().toString());
			content.setMemberId(accountBean.getId().intValue());
			content.setType(type);
			content.setUrl(savedUrl);
			content.setSavedField(fieldNames);
			content.setDeleteflag(0);
			memberSavedSearchcontentMapper.insert(content);
			return SAVE_SUCCESS;
		}
	}
	
}
