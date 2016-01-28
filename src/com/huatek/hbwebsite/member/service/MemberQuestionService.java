package com.huatek.hbwebsite.member.service;

import com.huatek.base.service.BaseService;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.hbwebsite.common.entity.QuestionStategy;
import java.util.List;

public interface MemberQuestionService extends BaseService {
	Long getAllQuestionCount(Long var1);

	CutPageBean getQuestionListByType(CutPageBean var1, Long var2, int var3);

	Long getQuestionCountByType(Long var1, int var2);

	List<QuestionStategy> getUnsyncQuestion();

	void updateSyncFlag(String var1);

	CutPageBean getQuestionList(CutPageBean var1, Long var2);
}
