package cn.hshb.web.biz.action;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hshb.web.biz.service.MemberSavedSearchcontentService;

import com.huatek.hbwebsite.member.entity.PlatMemberInfo;

/**
 * 用户保存查询条件
 * @author hejianbo
 *	2015年8月7日
 */
@Controller
@RequestMapping("/saveInformation")
public class MemberSaveSearchFieldsController {
	private static final Log log = LogFactory.getLog(MemberSaveSearchFieldsController.class);
	
	@Autowired
	private MemberSavedSearchcontentService memberSavedSearchcontentService;
	
	/**
	 * 保存查询条件
	 * @param houseType		房源类型  小区:3 , 租赁: 2, 二手房: 1
	 * @param pathStr		查询的条件
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = {"/{houseType}"})
	public void saveSearchItem(@PathVariable String houseType,Model model, HttpServletRequest request, HttpServletResponse response){
		String resultString = "";
		PlatMemberInfo accountBean = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		String savedUrl = request.getParameter("savedUrl");
		String fieldNames = request.getParameter("fieldNames");
		resultString = memberSavedSearchcontentService.saveSearchFieldByMember(accountBean, savedUrl, fieldNames, houseType);
		
		try {
			PrintWriter print = response.getWriter();
			print.write(resultString);
			print.flush();
		} catch (IOException e) {
			log.error(e);
		}
		
	}
	
}
