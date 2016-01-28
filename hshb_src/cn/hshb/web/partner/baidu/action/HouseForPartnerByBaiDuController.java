package cn.hshb.web.partner.baidu.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huatek.framework.util.Parameter;

import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.PartnerBaiduCommunity;
import cn.hshb.web.biz.mybatis.model.PartnerBaiduHouseRent;
import cn.hshb.web.biz.mybatis.model.PartnerBaiduHouseSecond;
import cn.hshb.web.biz.service.HouseCommunityService;
import cn.hshb.web.biz.service.HouseRentService;
import cn.hshb.web.biz.service.HouseSecondhandHouseService;
import cn.hshb.web.biz.service.PartnerBaiduCommunityService;
import cn.hshb.web.biz.service.PartnerBaiduHouseRentService;
import cn.hshb.web.biz.service.PartnerBaiduHouseSecondService;
import cn.hshb.web.common.util.HttpConnectionUtil;
import cn.hshb.web.common.util.StringUtil;
import cn.hshb.web.partner.baidu.service.DocumentXML;
import cn.hshb.web.partner.baidu.servlet.WrapperResponse;

/**
 * 给"百度房产"提供接口
 * @author hejianbo
 *	2015年8月21日
 */

@Controller
@RequestMapping("/partner/baidu")
public class HouseForPartnerByBaiDuController {
	private static final Log log = LogFactory.getLog(HouseForPartnerByBaiDuController.class);
	private static final String CONTENT_TYPE_XML ="application/xml;charset=UTF-8";
	private static int BATCH_ROW_COUNT = 1000;
	private static String COMMIT_XML_SAVE_PATH = "D:/logs/commit_xml/";
	
	//出售房源数据下架信息推送URL
	private static String PUSH_URL_CHUSHOU_DEL = "http://ping.baidu.com/sitemap?site=www.hshb.cn&resource_name=SecondHandHouseDeletehouseproperty&access_token=Hpy8pyXE";
	//出租房源数据下架信息推送URL
	private static String PUSH_URL_CHUZU_DEL = "http://ping.baidu.com/sitemap?site=www.hshb.cn&resource_name=ZufangDeletehouseproperty&access_token=Hpy8pyXE";
	
	private static final String SALE = "sale";
	private static final String RENT = "rent";
	private static final String COMMUNITY = "community";
	
	static {
		String s = Parameter.getInstance().getProperty("batch.row.count");
		if(!StringUtil.isEmpty(s) && StringUtils.isNumeric(s)){
			BATCH_ROW_COUNT = Integer.valueOf(s);	//每次传送的记录数，小于1则表示传送全部数据
		}
		s = Parameter.getInstance().getProperty("commit.xml.save.path");
		if(!StringUtil.isEmpty(s))
			COMMIT_XML_SAVE_PATH = s;
		
		s = Parameter.getInstance().getProperty("baidu.house.push.url.chushou.del");		
		if(StringUtil.isNotEmpty(s))
			PUSH_URL_CHUSHOU_DEL = s;
		
		s = Parameter.getInstance().getProperty("baidu.house.push.url.chuzu.del");		
		if(StringUtil.isNotEmpty(s))
			PUSH_URL_CHUZU_DEL = s;
	}

	@Autowired
	HouseSecondhandHouseService houseSecondHandHouseService;
	@Autowired
	PartnerBaiduHouseRentService partnerBaiduHouseRentService;
	@Autowired
	HouseCommunityService houseCommunityService;
	@Autowired
	PartnerBaiduHouseSecondService partnerBaiduHouseSecondService;
	@Autowired
	PartnerBaiduCommunityService partnerBaiduCommunityService;
//	@Autowired
//	private HttpConnectionUtil httpConnectionUtil;
	
	@RequestMapping(value= {"/chushou"})
	public void getHouseSecondInformation(Model model, HttpServletRequest request, HttpServletResponse response){
		List<PartnerBaiduHouseSecond> houseList = partnerBaiduHouseSecondService.getHouseList(BATCH_ROW_COUNT);
		if(houseList == null) houseList = new ArrayList<PartnerBaiduHouseSecond>();
		cn.hshb.web.partner.baidu.entity.newest.houseSecond.Urlset urlset = cn.hshb.web.partner.baidu.entity.newest.houseSecond.Urlset.fromHouse(houseList);
		renderMergedOutputModel(urlset, request, response, SALE, "");
		//更新状态
		partnerBaiduHouseSecondService.updateHouseInformation(houseList);
	}
	@RequestMapping(value= {"/chushouDel"})
	public void DelHouseSecondInformation(Model model, HttpServletRequest request, HttpServletResponse response){
		List<PartnerBaiduHouseSecond> houseList = partnerBaiduHouseSecondService.getDeleteHouseList();
		if(houseList == null) houseList = new ArrayList<PartnerBaiduHouseSecond>();
		cn.hshb.web.partner.baidu.entity.newest.houseSecondDel.Urlset urlset = cn.hshb.web.partner.baidu.entity.newest.houseSecondDel.Urlset.fromHouse(houseList);
		try{
			String xmlContent = urlset.toXML("saleDel");
			//测试
			//String url = "http://localhost:8080/ddhb/postxml";
			//正式
//			String url = "http://ping.baidu.com/sitemap?site=www.hshb.cn&resource_name=SecondHandHouseDeletehouseproperty&access_token=Hpy8pyXE";
//			String respStr = HttpConnectionUtil.postXML(url, xmlContent, "UTF-8", "UTF-8");
			String respStr = HttpConnectionUtil.postXML(PUSH_URL_CHUSHOU_DEL, xmlContent, "UTF-8", "UTF-8");
			printXML(respStr, response);
			recordDelXmlContent("chushouDel", respStr);
		}catch(Exception ex){
			log.error("提交删除代码失败",ex);
		}
		partnerBaiduHouseSecondService.updateHouseInformation(houseList);
	}
	
	@RequestMapping(value={"/chuzu"})
	public void getHouseRentInformation(Model model, HttpServletRequest request, HttpServletResponse response){
		List<PartnerBaiduHouseRent> houseList = partnerBaiduHouseRentService.getHouseList(BATCH_ROW_COUNT);
		if(houseList == null) houseList = new ArrayList<PartnerBaiduHouseRent>();
		cn.hshb.web.partner.baidu.entity.newest.houseRent.Urlset urlset =  cn.hshb.web.partner.baidu.entity.newest.houseRent.Urlset.fromHouse(houseList);
		renderMergedOutputModel(urlset, request, response, RENT, "");
		partnerBaiduHouseRentService.updateHouseInformation(houseList);
	}
	
	@RequestMapping(value={"/chuzuDel"})
	public void DelHouseRentInformation(Model model, HttpServletRequest request, HttpServletResponse response){
		List<PartnerBaiduHouseRent> houseList = partnerBaiduHouseRentService.getDeleteHouseList();
		if(houseList == null) houseList = new ArrayList<PartnerBaiduHouseRent>();
		cn.hshb.web.partner.baidu.entity.newest.houseRentDel.Urlset urlset = cn.hshb.web.partner.baidu.entity.newest.houseRentDel.Urlset.fromHouse(houseList);
		try{
			String xmlContext = urlset.toXML("rentDel");
			//测试
//			String url = "http://localhost:8080/ddhb/postxml";
			//正式
//			String url = "http://ping.baidu.com/sitemap?site=www.hshb.cn&resource_name=ZufangDeletehouseproperty&access_token=Hpy8pyXE";
//			String respStr = HttpConnectionUtil.postXML(url, xmlContext, "UTF-8", "UTF-8");
			String respStr = HttpConnectionUtil.postXML(PUSH_URL_CHUZU_DEL, xmlContext, "UTF-8", "UTF-8");
			printXML(respStr, response);
			recordDelXmlContent("chuzuDel", respStr);
		}catch(Exception ex){
			log.error("提交到百度的租赁删除解析失败",ex);
		}
		partnerBaiduHouseRentService.updateHouseInformation(houseList);
	}
	
	@RequestMapping(value= {"/xiaoqu"})
	public void getCommunityinformation(Model model, HttpServletRequest request, HttpServletResponse response){
		List<PartnerBaiduCommunity> communityList = partnerBaiduCommunityService.getCommunityList(BATCH_ROW_COUNT);
		if(communityList == null) communityList = new ArrayList<PartnerBaiduCommunity>();
		cn.hshb.web.partner.baidu.entity.newest.community.Urlset urlset = cn.hshb.web.partner.baidu.entity.newest.community.Urlset.fromHouse(communityList);
		renderMergedOutputModel(urlset, request, response, COMMUNITY, "");
		partnerBaiduCommunityService.updateCommunityInformation(communityList);
	}
	
	protected void renderMergedOutputModel(Object xmlObject, HttpServletRequest request, HttpServletResponse response,String type, String contentXML){
		PrintWriter writer = null;
		if(xmlObject == null){
			log.error("xmlObject is Null");
			return;
		}
		try{
			response.setContentType(CONTENT_TYPE_XML);
			WrapperResponse resp = new WrapperResponse(response);
			writer = resp.getWriter();
			JAXBContext jaxbContext = null;

			//文件保存路径:
			String savePath = COMMIT_XML_SAVE_PATH + type + ".xml";
			
			if(SALE.equals(type))
				jaxbContext = JAXBContext.newInstance(cn.hshb.web.partner.baidu.entity.newest.houseSecond.Urlset.class);
			else if("saleDel".equals(type))
				jaxbContext = JAXBContext.newInstance(cn.hshb.web.partner.baidu.entity.newest.houseSecondDel.Urlset.class);
			else if(RENT.equals(type))
				jaxbContext = JAXBContext.newInstance(cn.hshb.web.partner.baidu.entity.newest.houseRent.Urlset.class);
			else if(COMMUNITY.equals(type))
				jaxbContext = JAXBContext.newInstance(cn.hshb.web.partner.baidu.entity.newest.community.Urlset.class);
			else if("rentDel".equals(type))
				jaxbContext = JAXBContext.newInstance(cn.hshb.web.partner.baidu.entity.newest.houseRentDel.Urlset.class);
			
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.marshal(xmlObject, writer);
			writer.flush();
			
			String content = resp.getContent();
			//对这数据进行保存,以便查看
			new DocumentXML(savePath, content);
			resp.superWrite(content);
			resp.superFlush();
		}catch(Exception ex){
			log.error("JAXB Exception while rendering XML response to client: " + request.getRemoteAddr(), ex);
		}finally{
			if(writer != null){
				writer.flush();
				writer.close();
				writer = null;
			}
		}
	}
	
	private void printXML(String content, HttpServletResponse response){
		PrintWriter writer = null;
		try{
			response.setContentType(CONTENT_TYPE_XML);
			writer = response.getWriter();
			writer.println(content);
		}catch(Exception ex){
			log.error("把提交删除数据输出到页面失败:" + ex);
		}finally{
			if(writer != null){
				writer.flush();
				writer.close();
				writer = null;
			}
		}
	}
	
	/**
	 * 记录删除的数据
	 * @param fileName
	 * @param content
	 */
	private void recordDelXmlContent(String fileName, String content){
		String savePath = COMMIT_XML_SAVE_PATH + fileName + ".xml";
		new DocumentXML(savePath, content);
	}
}
