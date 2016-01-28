/**
 * 
 */
package cn.hshb.web.biz.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.google.zxing.WriterException;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.util.CaptchaServiceSingleton;
import com.huatek.hbwebsite.util.ClientFlagUtil;
import com.huatek.hbwebsite.util.TwoDimensionMaker;

import cn.hshb.web.biz.mybatis.model.HouseNewBrowseAnalyse;
import cn.hshb.web.biz.mybatis.model.HouseNewhouse;
import cn.hshb.web.biz.mybatis.model.HouseNewhouseGroupbuy;
import cn.hshb.web.biz.mybatis.model.HouseNewhouseWithBLOBs;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.service.CommonActivityService;
import cn.hshb.web.biz.service.CommonFooterMenuService;
import cn.hshb.web.biz.service.HouseNewBrowseAnalyseService;
import cn.hshb.web.biz.service.HouseNewhouseGroupbuyService;
import cn.hshb.web.biz.service.HouseNewhouseService;
import cn.hshb.web.biz.util.HouseQueryStrParser;
import cn.hshb.web.biz.util.PageMetaBean;
import cn.hshb.web.common.util.StringUtil;

/**
 * @author ShengYoufu
 *
 */
@Controller("houseNew")
@RequestMapping("/xinfang" )
public class HouseNewhouseController extends HouseBaseController{
	
	private static final int MIN_GROUPBUY_COUNT = 50;  //最新团购列表最少人数，如果不够 ，则虚拟一些
	
	@Autowired
	private HouseNewhouseService houseNewhouseService;
	@Autowired
	private HouseNewhouseGroupbuyService houseNewhouseGroupbuyService;
	@Autowired
	private HouseNewBrowseAnalyseService houseNewBrowseAnalyseService;
	@Autowired
	private CommonActivityService commonActivityService;
	@Autowired
	private CommonFooterMenuService commonFooterMenuService;
	
	/**
	 * 查询新楼盘列表
	 * @param request
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value= {""})
	public String index(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		return list(null, request, model);
	}
	
	/**
	 * 新楼盘详情页(无页头页尾，供微官网调用 )
	 * @param houseNo 新楼盘ID
	 * @param request
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value= {"/{houseNo}_raw.html"})
	public String detailRaw(@PathVariable String houseNo, HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		if(StringUtil.isEmpty(houseNo)) return "house.new.detail.notexists";
		
		HouseNewhouse house = houseNewhouseService.loadNewHouse(houseNo);
		String ret = houseDetail(house, houseNo, request, model);
		
		if("house.new.detail".equals(ret))
			return "house.new.detail.raw";
		else
			return "house.new.detail.error";
	}
	
	/**
	 * 新楼盘详情页
	 * @param houseNo	房源编号
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value= {"/{houseNo}.html"})
	public String detail(@PathVariable String houseNo, HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		if(StringUtil.isEmpty(houseNo)) return "house.new.detail.notexists";
		
		HouseNewhouse house = houseNewhouseService.loadNewHouse(houseNo);
		String ret = houseDetail(house, houseNo, request, model);
		if("house.new.detail".equals(ret))
			return "house.new.detail.raw";
		else
			return "house.new.detail.error";
	}
	
	/**
	 * 查询新楼盘
	 * @param pathStr 如果是新楼盘编号，则显示新楼盘详情，否则查询新楼盘列表并显示
	 * @param request
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value= {"/{pathStr}"})
	public String index(@PathVariable String pathStr, HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		HouseNewhouse house = houseNewhouseService.loadNewHouse(pathStr);
		if(house!=null){
			return houseDetail(house, pathStr, request, model);
		}else if("qrcode".equalsIgnoreCase(pathStr)){
			String id = request.getParameter("id");
			if(StringUtil.isNotEmpty(id))
				try {
					drawCode(id, request, response);
				} catch (WriterException ex) {
					Log.error("Draw qrcode for new house failed.", ex);
				} catch (IOException ex) {
					Log.error("Draw qrcode for new house failed.", ex);
				}
			return "";
		}else{
			return list(pathStr, request, model);
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
	@RequestMapping(params = { "/buy" })
	public String addNewHouseGroupbuy(@ModelAttribute("signupform") HouseNewhouseGroupbuy houseNewGroupbuy,
			BindingResult result, Model model, HttpServletRequest request) {
		HouseNewhouseWithBLOBs housnew = houseNewhouseService.loadNewHouse(request.getParameter("houseid"));
		houseNewGroupbuy.setHouse(housnew);
		validate(houseNewGroupbuy, result, request);
		if (result.hasErrors()) {
			model.addAttribute("_out_data", Util.getErrorMsgInfo(result, "houseNewGroupbuyFrom"));
			return "ddhb.out.error";
		} else {
			houseNewGroupbuy.setApplyDate(new Date());
			houseNewGroupbuy.setDeleteflag(0);
			houseNewhouseGroupbuyService.save(houseNewGroupbuy);
			model.addAttribute("issave", "success");
			return "ddhb.out.error";
		}
	}

	/**
	 * 生成房源二维码
	 * @param id		房源ID
	 * @param request
	 * @param response
	 * @throws WriterException
	 * @throws IOException
	 */
	@RequestMapping(value= {"/qrcode"})
	public void drawCode(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws WriterException, IOException {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append("http://");
		urlBuilder.append(request.getServerName());
		if(request.getServerPort()!=80)
			urlBuilder.append(":" + request.getServerPort());
		urlBuilder.append(request.getContextPath());
		urlBuilder.append("/xinfang/" + id);
		String url = urlBuilder.toString();
		url = url.replace("//", "/");
		String format = "JPEG";
		BufferedImage image = TwoDimensionMaker.getTDCPic(urlBuilder.toString());
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(image, format, sos);
		sos.close();
	}
	
	/**
	 * 房源列表
	 * @param pathStr
	 * @param request
	 * @param model
	 * @return
	 */
	public String list(@PathVariable String pathStr, HttpServletRequest request, Model model){
		model.addAttribute("basePath", "/xinfang");
		
		Map<String, String>  condition = parseQueryCondition(pathStr, null);
		Integer pageNum = 1;
		if(condition.containsKey("pageNum")){
			if(StringUtil.isNumeric(condition.get("pageNum"))){
				pageNum = Integer.parseInt(condition.get("pageNum"));
			}
		}

		Map<String, String> orderFields = new HashMap<String, String>();
		if(condition.containsKey("orderby")){
			orderFields.put(condition.get("orderby"), "asc");
		}
		
		List<HouseNewhouseWithBLOBs> houseList = houseNewhouseService.getNewHouse(pageNum, 5);
		//如果团购时间被更新了,那么则需要把该时间保存在数据库里边
		houseNewhouseService.updateHouseNewHouseData(houseList);
		houseNewhouseService.setHouseGroupbuys(houseList);
		model.addAttribute("houseList", houseList);
		
		PageInfo<HouseNewhouseWithBLOBs> page = new PageInfo<HouseNewhouseWithBLOBs>(houseList);
		model.addAttribute("pageBean", page);
		
		model.addAttribute("topADBar", commonActivityService.getActivityByPageAndPosition(5, 1));
		model.addAttribute("bottomADBar", commonActivityService.getActivityByPageAndPosition(5, 2));
		model.addAttribute("backType", 5);

		List<HouseNewhouseGroupbuy> groupBuyList =  houseNewhouseGroupbuyService.getNewHouseGroupBuyAfterDate(new Date());
		fillGroupBuyList(groupBuyList);
		model.addAttribute("groupBuyList", groupBuyList);

		Integer groupCount = houseNewhouseGroupbuyService.getCountGroupBuy();
		model.addAttribute("groupCount", groupCount>=0 ?groupCount : "");

		Integer houseCount = houseNewhouseService.getCountHouseNewAfterDate(new Date());
		model.addAttribute("houseCount", houseCount>=0 ? houseCount : "");

		Integer houseCountEnd = houseNewhouseService.getCountHouseNewBeforeDate(new Date());
		model.addAttribute("houseCountEnd", houseCountEnd>=0 ? houseCountEnd : "");
		//底部菜单信息,没有会报js错误
		model.addAttribute("footerMenuList",commonFooterMenuService.getCommonFooterMenu());
		//用于顶部菜单栏 搜索
		model.addAttribute("backType", "4");
		
		//生成页面Meta所需要的信息
		PageMetaBean pageMeta = new PageMetaBean();
		pageMeta.setModuleName("新房");
		pageMeta.setViewType(PageMetaBean.VIEW_TYPE_LIST);
		HouseQueryStrParser parser = new HouseQueryStrParser();
		model.addAttribute(PageMetaBean.BEAN_NAME_MODULE, pageMeta);

		return "house.new.list";
	}
	
	/**
	 * 显示新楼盘详情
	 * @param house		新楼盘对象
	 * @param pathStr	路径参数
	 * @param request	
	 * @param model
	 * @return
	 */
	public String houseDetail(HouseNewhouse house, String pathStr, HttpServletRequest request, Model model){
		model.addAttribute("houseNew", house);
		model.addAttribute("groupBuyList", houseNewhouseGroupbuyService.getNewHouseGroupBuyAfterDate( new Date() ));
		
		//如果团购时间被更新了,那么则需要把该时间保存在数据库里
		houseNewhouseService.updateHouseNewHouseData(house);
		
		Integer groupCount = houseNewhouseGroupbuyService.getCountGroupBuy();
		model.addAttribute("groupCount", groupCount>=0? groupCount : "");
		
		//查询截止日期为今天之前的楼盘数
		Integer houseCount = houseNewhouseService.getCountHouseNewAfterDate(new Date());
		model.addAttribute("houseCount", houseCount>=0 ? houseCount : "");

		//查询截止日期为今天之后的楼盘数
		Integer houseCountEnd = houseNewhouseService.getCountHouseNewBeforeDate(new Date());
		model.addAttribute("houseCountEnd", houseCountEnd>=0? houseCountEnd : "");
		
		Integer entrantCount = houseNewhouseGroupbuyService.getHouseNewEntrantsById(house.getNewhouseNo());
		model.addAttribute("HouseNewEntrant", entrantCount>=0? entrantCount : "");
		
		PlatMemberInfo memberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		String clientFlag = ClientFlagUtil.getClientFlag(request);
		HouseNewBrowseAnalyse houseAnalyse = new HouseNewBrowseAnalyse();
		houseAnalyse.setAnalyseId(UUID.randomUUID().toString());
		houseAnalyse.setProjectNo(house.getProjectNo());
		houseAnalyse.setProjectName(house.getBuildingName());
		houseAnalyse.setBrowseTime(new Date());
		houseAnalyse.setCountyId(house.getCountyNo());
		if(house.getCounty()!=null)
			houseAnalyse.setCountyName(house.getCounty().getCountyName());
		houseAnalyse.setClientflag(clientFlag);
		if (memberInfo != null) {
			houseAnalyse.setIsMember(1);
			houseAnalyse.setMemberName(memberInfo.getAccName());
			houseAnalyse.setRegisterTime(memberInfo.getRegTime());
		} else {
			houseAnalyse.setIsMember(0);
		}
		houseNewBrowseAnalyseService.save(houseAnalyse);
		//底部菜单信息,没有会报js错误
		model.addAttribute("footerMenuList",commonFooterMenuService.getCommonFooterMenu());
		model.addAttribute("houseType",1);
		
		//生成页面Meta所需要的信息
		PageMetaBean pageMeta = new PageMetaBean();
		pageMeta.setModuleName("新房");
		pageMeta.setViewType(PageMetaBean.VIEW_TYPE_DETAIL);
		pageMeta.setNewHouseTitle(house.getHouseTitle());
		pageMeta.setMetaKeywords(house.getMetaKeywords());
		pageMeta.setMetaDescription(house.getMetaDescription());
		model.addAttribute(PageMetaBean.BEAN_NAME_MODULE, pageMeta);

		return "house.new.detail";
	}
	
	
	
	/**
	 * 随机生成虚拟客户
	 * @param groupBuyList
	 */
	private void fillGroupBuyList(List<HouseNewhouseGroupbuy> groupBuyList){
		if(groupBuyList.size()<MIN_GROUPBUY_COUNT){
			int st = groupBuyList.size();
			for(int ii=st; ii<MIN_GROUPBUY_COUNT; ii++){
				// person.clientName, person.clientTelephone, person.clientTelephone
				HouseNewhouseGroupbuy gb = new HouseNewhouseGroupbuy();
				gb.setClientName(StringUtil.getRandName(2));
				gb.setClientTelephone(StringUtil.getRandPhoneNum());
				groupBuyList.add(gb);
			}
		}
	}
	
	/**
	 * 团购录入校验
	 * @param houseNewGroupbuy
	 * @param errors
	 * @param request
	 */
	private void validate(HouseNewhouseGroupbuy groupbuy, Errors errors, HttpServletRequest request) {
		if (StringUtil.isEmpty(groupbuy.getClientName()) || StringUtil.isBlank(groupbuy.getClientName())) {
			errors.reject("姓名是必填项");
		} else if (groupbuy.getClientName().getBytes(Charset.forName("GBK")).length > 40) {
			errors.reject("姓名不能超过20个汉字");
		}

		if (!StringUtil.isEmpty(groupbuy.getClientEmail()) 
				&& !Util.getMatchResult(groupbuy.getClientEmail(),
						"^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
			errors.rejectValue("clientEmail", "email.errors");
		}

		if (StringUtil.isEmpty(groupbuy.getClientTelephone())) {
			errors.reject("电话号码是必填项");
		} else if (!Util.getMatchResult(groupbuy.getClientTelephone(), "^(([\\d]{3,4}-)?[\\d]{7,8}|[\\d]{11})$")) {
			errors.reject("联系电话格式不正确，正确的格式为xxx-xxxxxxxx或1xxxxxxxxxx");
		}

		if (!StringUtil.isBlank(groupbuy.getClientProperties())
				&& groupbuy.getClientName().getBytes(Charset.forName("GBK")).length > 400) {
			errors.reject("置业需求字段不能超过200个汉字");
		}

		if (StringUtil.isEmpty(groupbuy.getVerifyCode())) {
			errors.reject("请输入验证码");
		} else {
			String sessId = request.getSession().getId();
			Boolean flag = CaptchaServiceSingleton.getInstance().validateResponseForID(sessId, groupbuy.getVerifyCode().toLowerCase());
			if (!flag) {
				errors.reject("验证码输入有误");
			}
		}

		if(houseNewhouseGroupbuyService.isExistsGroupbuy(groupbuy.getClientTelephone(), groupbuy.getNewhouseNo())){
			errors.reject("您已经报名过该房源.");
		}
	}	
}
