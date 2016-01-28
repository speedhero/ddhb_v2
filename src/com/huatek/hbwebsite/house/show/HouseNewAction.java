package com.huatek.hbwebsite.house.show;

import cn.hshb.web.common.util.StringUtil;

import com.google.zxing.WriterException;
import com.huatek.ddhb.manage.activityManage.entity.ADAndActivityBar;
import com.huatek.ddhb.manage.activityManage.service.ActivityManageService;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.QueryCondition;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.house.entity.HouseNew;
import com.huatek.hbwebsite.house.entity.HouseNewBrowseAnalyse;
import com.huatek.hbwebsite.house.entity.HouseNewGroupbuy;
import com.huatek.hbwebsite.house.service.HouseNewService;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.search.service.SearchService;
import com.huatek.hbwebsite.util.CaptchaServiceSingleton;
import com.huatek.hbwebsite.util.ClientFlagUtil;
import com.huatek.hbwebsite.util.TwoDimensionMaker;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({ "/houseNew.show" })
public class HouseNewAction {
	@Autowired
	HouseNewService houseNewService;
	@Autowired
	SearchService searchService;
	@Autowired
	private ActivityManageService activityManageService;
	private static final Integer isMember = Integer.valueOf(1);
	private static final Integer notMember = Integer.valueOf(0);

	//<meta name="keywords" content="KEYWORDS" /> 关键字    搜索引擎预期查找
	private static final String KEYWORDS ="新房,杭州新房,新楼盘,杭州楼盘,新盘";			
	//<meta name="description" content="DESCRIPTION" /> 网页内容 不超过
	private static final String DESCRIPTION = "杭州豪世华邦新房频道是最专业最真实的杭州新房网,为您提供大量的杭州新房出售信息,查找杭州新房信息,请到杭州豪世华邦。"; 		
	//<title>TITLECONTENT</title> 标题 	
	private static final String TITLECONTENT = "杭州新房";	
	
	private static final int MIN_GROUPBUY_COUNT = 50;  //最新团购列表最少人数，如果不够 ，则虚拟一些

	/**
	 * 显示新楼盘详情
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=showDetail" })
	public String showDetail(@RequestParam("id") String id, Model model, HttpServletRequest request) {
		HouseNew houseNew = (HouseNew) houseNewService.getObjectById(HouseNew.class, Long.valueOf(id));
		model.addAttribute("houseNew", houseNew);
		model.addAttribute("groupBuyList", this.houseNewService.getNewHouseGroupBuy( new Date() ));
		
		Long groupCount = houseNewService.getCountGroupBuy();
		if(groupCount>=0)
			model.addAttribute("groupCount", groupCount);
		else
			model.addAttribute("groupCount", "");
		
		Long houseCount = houseNewService.getCountHouseNew(new HashMap<String, String>(), new Date());
		if(houseCount>=0)
			model.addAttribute("houseCount", houseCount);
		else
			model.addAttribute("houseCount", "");

		Long houseCountEnd = houseNewService.getCountHouseNewEnd(new HashMap<String, String>(), new Date());
		if(houseCountEnd>=0)
			model.addAttribute("houseCountEnd", houseCountEnd);
		else
			model.addAttribute("houseCountEnd", "");
		
		Long entrantCount = houseNewService.getHouseNewEntrantsById(houseNew.getId());
		if(entrantCount>=0)
			model.addAttribute("HouseNewEntrant", entrantCount);
		else
			model.addAttribute("HouseNewEntrant", "");
		
		model.addAttribute("backType", 5);
		PlatMemberInfo memberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		String clientFlag = ClientFlagUtil.getClientFlag(request);
		HouseNewBrowseAnalyse houseAnalyse = new HouseNewBrowseAnalyse();
		houseAnalyse.setAnalyseId(UUID.randomUUID().toString());
		houseAnalyse.setProjectNo(houseNew.getProjectNo());
		houseAnalyse.setProjectName(houseNew.getBuildingName());
		houseAnalyse.setBrowseTime(new Date());
		houseAnalyse.setCountyId(houseNew.getArea().getId().toString());
		houseAnalyse.setCountyName(houseNew.getArea().getCountyName());
		houseAnalyse.setClientFlag(clientFlag);
		if (memberInfo != null) {
			houseAnalyse.setIsMember(isMember);
			houseAnalyse.setMemberName(memberInfo.getAccName());
			houseAnalyse.setRegisterTime(memberInfo.getRegTime());
		} else {
			houseAnalyse.setIsMember(notMember);
		}

		houseNewService.save(houseAnalyse);
		
		//Add by 何剑波  
		//功能：在<head>标签添加<meta>的值
		//title内容房源站点地址
		model.addAttribute("titleContent","杭州新盘-"+ houseNew.getBuildingName() );
		model.addAttribute("keywords",null);
		model.addAttribute("description","杭州豪世华邦为您提供最新杭州"+ houseNew.getArea().getCountyName() +"出租房信息，找房、租房不再难");

		return "houseNew.detail.show";
	}
	
	/**
	 * 随机生成虚拟客户
	 * @param groupBuyList
	 */
	private void fillGroupBuyList(List<HouseNewGroupbuy> groupBuyList){
		if(groupBuyList.size()<MIN_GROUPBUY_COUNT){
			int st = groupBuyList.size();
			for(int ii=st; ii<MIN_GROUPBUY_COUNT; ii++){
				// person.clientName, person.clientTelephone, person.clientTelephone
				HouseNewGroupbuy gb = new HouseNewGroupbuy();
				gb.setClientName(StringUtil.getRandName(2));
				gb.setClientTelephone(StringUtil.getRandPhoneNum());
				groupBuyList.add(gb);
			}
		}
	}

	/**
	 * 查询新楼盘
	 * @param request
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(params = { "actionMethod=dimquery" })
	public String dimQuery(HttpServletRequest request, Model model) throws ParseException {
		CutPageBean cutPageBean = QueryCondition.getQueryPageBean(request);
		cutPageBean.setPageSize(5);
		Map<String, String> oneMap = new HashMap<String, String>();
		Map<String, List> twoMap = new HashMap<String, List>();
		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String topADBar = ((String) parameterNames.nextElement()).toString();
			if (topADBar.startsWith("ddhb_one_")) {
				String bottomADBar = topADBar.substring(9);
				String sortfield = request.getParameter(topADBar);
				if (!"".equals(sortfield.trim())) {
					oneMap.put(bottomADBar.trim(), sortfield);
				}
			}
		}

		ADAndActivityBar topADBar1 = this.activityManageService.getActivityByPageAndPosition(5, 1);
		model.addAttribute("topADBar", topADBar1);
		ADAndActivityBar bottomADBar1 = this.activityManageService.getActivityByPageAndPosition(5, 2);
		model.addAttribute("bottomADBar", bottomADBar1);
		model.addAttribute("backType", Integer.valueOf(5));
		//model.addAttribute("groupBuyList", this.houseNewService.getNewHouseGroupBuy(new Date()));
		List<HouseNewGroupbuy> groupBuyList = this.houseNewService.getNewHouseGroupBuy(new Date());
		fillGroupBuyList(groupBuyList);
		model.addAttribute("groupBuyList", groupBuyList);

		Long groupCount = houseNewService.getCountGroupBuy();
		if(groupCount>=0)
			model.addAttribute("groupCount", groupCount);
		else
			model.addAttribute("groupCount", "");

		Long houseCount = houseNewService.getCountHouseNew(new HashMap<String, String>(), new Date());
		if(houseCount>=0)
			model.addAttribute("houseCount", houseCount);
		else
			model.addAttribute("houseCount", "");

		Long houseCountEnd = houseNewService.getCountHouseNewEnd(new HashMap<String, String>(), new Date());
		if(houseCountEnd>=0)
			model.addAttribute("houseCountEnd", houseCountEnd);
		else
			model.addAttribute("houseCountEnd", "");

		String sortfield = request.getParameter("sort");
		String orderStr = request.getParameter("order");
		String ispage = request.getParameter("ispage");
		CutPageBean pageBean;
		if (!"1".equals(ispage) && !"0".equals(ispage)) {
			pageBean = this.houseNewService.getSearchFiledList(cutPageBean, new HashMap<String, String>(), new HashMap(), "desc",
					"homepageRecommandFlag,homepageRecommandTime,publishDate");
		} else {
			if (sortfield == null) {
				sortfield = "homepageRecommandFlag,homepageRecommandTime,publishDate";
				orderStr = "desc";
			}

			pageBean = this.houseNewService.getSearchFiledList(cutPageBean, oneMap, twoMap, orderStr, sortfield);
		}

		//Add by 何剑波  
		//功能：在<head>标签添加<meta>的值
		model.addAttribute("titleContent", TITLECONTENT);
		model.addAttribute("keywords", KEYWORDS);
		model.addAttribute("description", DESCRIPTION);
		
		model.addAttribute("pageBean", this.setHouseEntrants(pageBean));
		if ("1".equals(ispage)) {
			return "houseNew.search.list";
		} else {
			String returnSring = this.searchService.loadSearchMenuByModuleName("7");
			model.addAttribute("jsonString", returnSring);
			return "houseNew.list.show";
		}
	}

	/**
	 * 团购登记
	 * @param houseNewGroupbuy
	 * @param result
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=addNewHouseGroupbuy" })
	public String addNewHouseGroupbuy(@ModelAttribute("signupform") HouseNewGroupbuy houseNewGroupbuy,
			BindingResult result, Model model, HttpServletRequest request) {
		long houseId = Long.valueOf(request.getParameter("houseid")).longValue();
		HouseNew housnew = (HouseNew) this.houseNewService.getObjectById(HouseNew.class, Long.valueOf(houseId));
		houseNewGroupbuy.setHouseNew(housnew);
		this.validate(houseNewGroupbuy, result, request);
		if (result.hasErrors()) {
			model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "houseNewGroupbuyFrom"));
			return "ddhb.out.error";
		} else {
			houseNewGroupbuy.setApplyDate(new Date());
			houseNewGroupbuy.setDeleteFlag(Integer.valueOf(0));
			this.houseNewService.save(houseNewGroupbuy);
			model.addAttribute("issave", "success");
			return "ddhb.out.error";
		}
	}

	/**
	 * 团购录入校验
	 * @param houseNewGroupbuy
	 * @param errors
	 * @param request
	 */
	private void validate(HouseNewGroupbuy houseNewGroupbuy, Errors errors, HttpServletRequest request) {
		if (CommonUtil.isZeroLengthTrimString(houseNewGroupbuy.getClientName())) {
			errors.reject("姓名是必填项");
		} else if (houseNewGroupbuy.getClientName().toString().getBytes(Charset.forName("GBK")).length > 40) {
			errors.reject("姓名不能超过20个汉字");
		}

		if (!houseNewGroupbuy.getClientEmail().trim().equals("")
				&& !Util.getMatchResult(houseNewGroupbuy.getClientEmail(),
						"^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$")) {
			errors.rejectValue("clientEmail", "email.errors");
		}

		if (CommonUtil.isZeroLengthTrimString(houseNewGroupbuy.getClientTelephone())) {
			errors.reject("电话号码是必填项");
		} else if (!Util.getMatchResult(houseNewGroupbuy.getClientTelephone(), "^(([\\d]{3,4}-)?[\\d]{7,8}|[\\d]{11})$")) {
			errors.reject("联系电话格式不正确，正确的格式为xxx-xxxxxxxx或1xxxxxxxxxx");
		}

		if (!houseNewGroupbuy.getClientproperties().trim().equals("")
				&& houseNewGroupbuy.getClientName().toString().getBytes(Charset.forName("GBK")).length > 400) {
			errors.reject("置业需求字段不能超过200个汉字");
		}

		if (CommonUtil.isZeroLengthTrimString(houseNewGroupbuy.getVerifyCode())) {
			errors.reject("请输入验证码");
		} else {
			boolean flag = Boolean.FALSE.booleanValue();
			String sessId = request.getSession().getId();
			flag = CaptchaServiceSingleton.getInstance()
					.validateResponseForID(sessId, houseNewGroupbuy.getVerifyCode().toLowerCase()).booleanValue();
			if (!flag) {
				errors.reject("验证码输入有误");
			}
		}

		if (1 == this.houseNewService.findHouseNewByItems(houseNewGroupbuy.getClientTelephone(), houseNewGroupbuy
				.getHouseNew().getId().longValue())) {
			errors.reject("已经报名过该房源");
		}

	}

	/**
	 * 生成网页地址二维码
	 * @param id
	 * @param request
	 * @param response
	 * @throws WriterException
	 * @throws IOException
	 */
	@RequestMapping(params = { "actionMethod=drawcode" })
	public void drawCode(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws WriterException, IOException {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append("http://");
		urlBuilder.append(request.getServerName());
		urlBuilder.append(":" + request.getServerPort());
		urlBuilder.append(request.getContextPath());
		urlBuilder.append("/houseNew.show?actionMethod=showDetail&id=" + id);
		String format = "JPEG";
		BufferedImage image = TwoDimensionMaker.getTDCPic(urlBuilder.toString());
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(image, format, sos);
		sos.close();
	}

	/**
	 * 给每个楼盘设置团购人数
	 * @param pageBean
	 * @return
	 */
	public CutPageBean setHouseEntrants(CutPageBean pageBean) {
		List<HouseNew> houseNews = (List<HouseNew>)pageBean.getDataList();
		if (houseNews != null && houseNews.size() != 0) {
			int len = houseNews.size();
			long[] houseIds = new long[len];

			for (int ii = 0; ii < len; ++ii) {
				houseIds[ii] = houseNews.get(ii).getId();
			}

			List entrantCountList = houseNewService.getHouseEntrants(houseIds);
			Iterator<HouseNew> itHouse = houseNews.iterator();

			while (itHouse.hasNext()) {
				HouseNew houseNew = itHouse.next();
				Iterator itEntrant = entrantCountList.iterator();
				while (itEntrant.hasNext()) {
					Object[] objArray = (Object[]) itEntrant.next();
					if (houseNew.getId().equals(Long.valueOf(objArray[0].toString()))) {
						houseNew.setEntrants(Long.valueOf(objArray[1].toString()));
					}
				}
			}

			pageBean.setDataList(houseNews);
			return pageBean;
		} else {
			return pageBean;
		}
	}
}
