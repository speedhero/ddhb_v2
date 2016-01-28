package cn.hshb.web.biz.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hshb.web.biz.mybatis.model.CommonHousequestionStrategy;
import cn.hshb.web.biz.mybatis.model.CommonQuestionStrategyType;
import cn.hshb.web.biz.service.BBrokerService;
import cn.hshb.web.biz.service.CommonHousequestionStrategyService;
import cn.hshb.web.biz.service.CommonQuestionStrategyTypeService;

/**
 * 问题与攻略
 * @author hejianbo
 *	2015年9月30日
 */
@Controller
@RequestMapping(value = {"/questionStrategy"})
public class CommonHousequestionStrategyController {
	
	@Autowired
	CommonQuestionStrategyTypeService commonQuestionStrategyTypeService;
	@Autowired
	CommonHousequestionStrategyService commonHouseQuestionStrategyService;
	@Autowired
	BBrokerService bBrokerService;
	
	
	@RequestMapping(value = {"/questionAndAnswer"})
	public String initRequestAndAnswer(Model model, HttpServletRequest request){
		List<CommonQuestionStrategyType> qestionStrategyTypeList = commonQuestionStrategyTypeService.getAllData();
		//盛经理：固定显示问题&攻略
		//1:房屋买卖,3:银行贷款,5:房屋租赁,6:房产知识,7:房屋装修,8:政策法规,9:其他问题
		String[][] questionStrategyTypes = {{"1","housingSaleList","房屋买卖"},{"3","bankLoanList","银行贷款"},{"5","housingLeaseList","房屋租赁"},
				{"6","realEstateKnowledgeList","房产知识"},{"7","housingDecorationList","房屋装修"},{"8","policyAndRegulationsList","政策法规"},
				{"9","otherQuestionsList","其他问题"}};
		//存放所有问题&攻略
		Map<String, List<CommonHousequestionStrategy>> mapQuestions = new HashMap<String, List<CommonHousequestionStrategy>>();
		for(String[] q : questionStrategyTypes ){
			mapQuestions.put(q[1], commonHouseQuestionStrategyService.getAllData(q[0]));
		}
		Set<Entry<String, List<CommonHousequestionStrategy>>> set = mapQuestions.entrySet();
		Iterator<Entry<String, List<CommonHousequestionStrategy>>> it = set.iterator();
		while(it.hasNext()){
			Map.Entry<String, List<CommonHousequestionStrategy>> en = it.next();
			model.addAttribute(en.getKey(), en.getValue());
		}
		model.addAttribute("qestionStrategyTypeList", qestionStrategyTypeList);
//		model.addAttribute("qestionStrategyListFirst", arg1)
		return "question.Answer.show";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
