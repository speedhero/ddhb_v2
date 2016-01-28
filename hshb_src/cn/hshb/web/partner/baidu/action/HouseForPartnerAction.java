
/**
 * 
 */
package cn.hshb.web.partner.baidu.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

import cn.hshb.web.common.exception.HshbException;
//import cn.hshb.web.partner.baidu.entity.houseSecond.Urlset;


import cn.hshb.web.common.util.HttpConnectionUtil;
import cn.hshb.web.partner.baidu.service.DocumentXML;
import cn.hshb.web.partner.baidu.servlet.WrapperResponse;

import com.huatek.framework.util.Parameter;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.community.service.CommunityService;
import com.huatek.hbwebsite.house.entity.BaiDuHouseRent;
import com.huatek.hbwebsite.house.entity.BaiDuHouseSecond;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.house.service.HouseSecondService;
import com.huatek.hbwebsite.rent.service.RentService;
import com.opensymphony.oscache.util.StringUtil;

/**
 * 给“百度房产”供数的接口
 * 
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 *  
 */
@Controller
@RequestMapping({ "/partner/baidu.show" })
public class HouseForPartnerAction {
	private static final Log log = LogFactory.getLog(HouseForPartnerAction.class);
	private static final String CONTENT_TYPE_XML = "application/xml;charset=UTF-8";
	//private static Integer BATCH_ROW_COUNT = Integer.valueOf(Parameter.getInstance().getProperty("batch.row.count"));	//每次传送的记录数，小于1则表示传送全部数据
	private static Integer BATCH_ROW_COUNT = 1000; 
	//private static String  LAST_COMMIT_XML_CONTENT = String.valueOf( Parameter.getInstance().getProperty("last.commit.xml.save.path"));
	private static String  LAST_COMMIT_XML_CONTENT = "./";
	
	private static final String SALE = "sale";
	private static final String RENT = "rent";
	private static final String COMMUNITY = "community";
	
	@Autowired
	HouseSecondService houseSecondService;
	@Autowired
	private RentService rentService;
	@Autowired
	private CommunityService communityService;
	@Autowired
	private HttpConnectionUtil httpConnectionUtil;
	
	static {
		String s = Parameter.getInstance().getProperty("batch.row.count");
		if(!StringUtil.isEmpty(s) && StringUtils.isNumeric(s)){
			BATCH_ROW_COUNT = Integer.valueOf(s);	//每次传送的记录数，小于1则表示传送全部数据
		}
		s = Parameter.getInstance().getProperty("commit.xml.save.path");
		if(!StringUtil.isEmpty(s))
			LAST_COMMIT_XML_CONTENT = s;
	}
	
	/**
	 * 二手房源数据接口，调用地址示例：
	 * http://localhost:8080/ddhb/partner/baidu.show?m=sale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = { "m=sale" })
	public void getHouseSecond(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<HouseSecond> houseList = houseSecondService.getHousesForBaidu(BATCH_ROW_COUNT);
		cn.hshb.web.partner.baidu.entity.houseSecond.Urlset urlset = cn.hshb.web.partner.baidu.entity.houseSecond.Urlset.fromHouse(houseList);
		String contentXML = null;
		if(houseList.size() == 0 ){
			contentXML = getContentXMl(SALE);
			renderMergedOutputModel(urlset, request, response, SALE, contentXML);
		}
		else{
			renderMergedOutputModel(urlset, request, response, SALE);
		}
		houseSecondService.saveChangeIsSync(houseList);
	}
	/**
	 * 二手房源数据接口，调用地址示例：
	 * http://localhost:8080/ddhb/partner/baidu.show?m=saleDel
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = { "m=saleDel" })
	public void getDelHouseSecond(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<BaiDuHouseSecond> houseList = houseSecondService.getDelHousesForBaidu(BATCH_ROW_COUNT);
		cn.hshb.web.partner.baidu.entity.houseSecondDel.Urlset urlset = cn.hshb.web.partner.baidu.entity.houseSecondDel.Urlset.fromHouse(houseList);
		houseSecondService.saveChangeIsSyncDel(houseList);
		String xmlContext = urlset.toXML("_saleDel");
		//正式的
		String url = "http://ping.baidu.com/sitemap?site=www.hshb.cn&resource_name=SecondHandHouseDeletehouseproperty&access_token=Hpy8pyXE";
//		String url = "http://localhost:8080/ddhb/postxml";
		//样例
//		String url = "http://ping.baidu.com/sitemap?site=www.hshb.cn&resource_name=SecondHandHouseDelete&access_token=Hpy8pyXE";
		String respStr = this.httpConnectionUtil.postXML(url, xmlContext, "UTF-8", "UTF-8");
		printXML(respStr, response);
//		renderMergedOutputModel(urlset, request, response, "saleDel");
	}
	
	
	/**
	 * 租赁房源数据接口，调用地址示例：
	 * http://localhost:8080/ddhb/partner/baidu.show?m=rent
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = { "m=rent" })
	public void getHouseRent(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<HouseRent> houseList = this.rentService.getHousesForBaidu(BATCH_ROW_COUNT);
		 cn.hshb.web.partner.baidu.entity.houseRent.Urlset urlset = cn.hshb.web.partner.baidu.entity.houseRent.Urlset.fromHouse(houseList);
		 String contentXML = null;
		if (houseList.size() == 0) {
			contentXML = getContentXMl(RENT);
			renderMergedOutputModel(urlset, request, response, RENT, contentXML);
		} else {
			renderMergedOutputModel(urlset, request, response, RENT);
		}
		rentService.saveChangeIsSync(houseList);
	}
	/**
	 * 删除租赁房源数据接口，调用地址示例：
	 * http://localhost:8080/ddhb/partner/baidu.show?m=rentDel
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = {"m=rentDel"})
	public void getDelHouseRent(Model model, HttpServletRequest request, HttpServletResponse response)throws Exception{
		List<BaiDuHouseRent> houseList = this.rentService.getDelHousesForBaidu(BATCH_ROW_COUNT);
		cn.hshb.web.partner.baidu.entity.houseRentDel.Urlset urlset = cn.hshb.web.partner.baidu.entity.houseRentDel.Urlset.fromHouse(houseList);
		rentService.saveChangeIsSyncDel(houseList);
		String xmlContext = urlset.toXML("_rentDel");
		//正式
		String url = "http://ping.baidu.com/sitemap?site=www.hshb.cn&resource_name=ZufangDeletehouseproperty&access_token=Hpy8pyXE";
//		String url = "http://localhost:8080/ddhb/postxml";
		//样例
//		String  url = "http://ping.baidu.com/sitemap?site=www.hshb.cn&resource_name=ZufangDelete&access_token=Hpy8pyXE";
		String respStr = this.httpConnectionUtil.postXML(url, xmlContext, "UTF-8", "UTF-8");
		printXML(respStr, response);
		//		renderMergedOutputModel(urlset, request, response, "rentDel");
	}
	private void printXML(String context, HttpServletResponse response)
	{
		PrintWriter writer = null;
		try{
			response.setContentType(CONTENT_TYPE_XML);
			writer = response.getWriter();
			writer.println(context);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (writer != null) {
				writer.flush();
				writer.close();
				writer = null;
			}
		}
	}
	
	/**
	 * 小区数据接口，调用地址示例：
	 * http://localhost:8080/ddhb/partner/baidu.show?m=community
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = { "m=community" })
	public void getCommunity(Model model, HttpServletRequest request, HttpServletResponse response)throws Exception{
		List<Community> houseList = this.communityService.getHousesForBaidu(BATCH_ROW_COUNT);
		cn.hshb.web.partner.baidu.entity.community.Urlset urlset = cn.hshb.web.partner.baidu.entity.community.Urlset.fromHouse(houseList);
		String contentXML = null;
		if (houseList.size() == 0) {
			contentXML = getContentXMl(COMMUNITY);
			renderMergedOutputModel(urlset, request, response, COMMUNITY, contentXML);
		} else {
			renderMergedOutputModel(urlset, request, response, COMMUNITY);
		}
		communityService.saveChangeIsSync(houseList);
	}
	
	protected void renderMergedOutputModel(Object xmlObject, HttpServletRequest request, HttpServletResponse response, String type)throws Exception {
		renderMergedOutputModel(xmlObject, request, response, type, null);
	}
	/**
	 * Render the XML object response which will be sent to the requested client.
	 *
	 * @param objectMap
	 * @param response
	 * @param content	 xml的内容
	 * @throws Exception
	 */
	protected void renderMergedOutputModel(Object xmlObject, HttpServletRequest request, HttpServletResponse response, String type, String contentXML)
			throws Exception {
		PrintWriter writer = null;
		if (xmlObject == null) { throw new HshbException("Object is null."); }

		try {
			response.setContentType(CONTENT_TYPE_XML);
//			writer = response.getWriter();
			WrapperResponse resp = new WrapperResponse(response);
			writer = resp.getWriter();
			JAXBContext jaxbContext = null;
			//文件保存路径 
			String savePath = LAST_COMMIT_XML_CONTENT + type +".xml";
			if(SALE.equals(type)){
				jaxbContext = JAXBContext.newInstance(cn.hshb.web.partner.baidu.entity.houseSecond.Urlset.class);
			}
			if("saleDel".equals(type)){
				jaxbContext = JAXBContext.newInstance(cn.hshb.web.partner.baidu.entity.houseSecondDel.Urlset.class);
			}
			if(RENT.equals(type)){
				jaxbContext = JAXBContext.newInstance(cn.hshb.web.partner.baidu.entity.houseRent.Urlset.class);
			}
			if(COMMUNITY.equals(type)){
				jaxbContext = JAXBContext.newInstance(cn.hshb.web.partner.baidu.entity.community.Urlset.class);
			}
			if("rentDel".equals(type)){
				jaxbContext = JAXBContext.newInstance(cn.hshb.web.partner.baidu.entity.houseRentDel.Urlset.class);
			}

			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.marshal(xmlObject, writer);
			writer.flush();
			
			String content = resp.getContent();
			//correctContent(content);
			
//			System.out.println("对生成的xml内容进行输出：content:" + content);
//			System.out.println(content);
			 if(contentXML != null && !"".equals(contentXML.trim()))
				 resp.superWrite(contentXML);
			 else{
				 new DocumentXML(savePath, content);
				 resp.superWrite(content);
			 }
			resp.superFlush();
		} catch (JAXBException exc) {
			log.error("JAXB Exception while rendering XML response to client: " + request.getRemoteAddr(), exc);
			throw new HshbException("JAXB Exception", exc);
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
				writer = null;
			}
		}
	}
	 
	/**
	 * 判断字符编码
	 * @param content
	 */
	private void correctContent(String content){
		String[] encodes = {"GBK","gb2312","UTF8","utf-8","ISO-8859-1"}; 
		for(String encode : encodes){
			try {
				if(content.equals(new String(content.getBytes(encode), encode))){
					System.out.println("该内容字符编码为" + encode);
					System.out.println("内容为：" + new String(content.getBytes(encode),"UTF-8"));
					break;
				}else{
					System.out.println("该内容编码不为：" + encode);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				log.error("判断字符错误哦");
			}
		}
	}
	/**
	 * 提取内容
	 * @param fileName
	 * @return
	 */
	private String getContentXMl(String fileName){
		DocumentXML dx = new DocumentXML();
		String savepath =LAST_COMMIT_XML_CONTENT +  fileName + ".xml" ;
		return dx.getContent(savepath);
	}
}
