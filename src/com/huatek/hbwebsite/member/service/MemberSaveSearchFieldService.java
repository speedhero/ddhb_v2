package com.huatek.hbwebsite.member.service;

import com.huatek.base.service.BaseService;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.hbwebsite.member.entity.MemberSavedSearchContent;
import java.util.List;

public interface MemberSaveSearchFieldService extends BaseService {
	boolean isUrlExist(String var1, Long var2, int var3);

	List<MemberSavedSearchContent> getAllSavedFieldList(Long var1);

	boolean deleteSavedFieldList(String var1);

	CutPageBean getCutPageBean(CutPageBean var1, Long var2);
}
