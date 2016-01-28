/**
 * 
 */
package cn.hshb.web.partner.baidu.service;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import cn.hshb.web.partner.baidu.entity.houseSecond.T1Type;
import cn.hshb.web.partner.baidu.entity.houseSecond.TBuildType;
import cn.hshb.web.partner.baidu.entity.houseSecond.TContact;
import cn.hshb.web.partner.baidu.entity.houseSecond.TData;
import cn.hshb.web.partner.baidu.entity.houseSecond.TDecoration;
import cn.hshb.web.partner.baidu.entity.houseSecond.TDomain;
import cn.hshb.web.partner.baidu.entity.houseSecond.TGender;
import cn.hshb.web.partner.baidu.entity.houseSecond.THouse;
import cn.hshb.web.partner.baidu.entity.houseSecond.THouseOrientation;
import cn.hshb.web.partner.baidu.entity.houseSecond.THouseSituation;
import cn.hshb.web.partner.baidu.entity.houseSecond.THouseStructurename;
import cn.hshb.web.partner.baidu.entity.houseSecond.THouseType;
import cn.hshb.web.partner.baidu.entity.houseSecond.TInstallations;
import cn.hshb.web.partner.baidu.entity.houseSecond.TRealEstate;
import cn.hshb.web.partner.baidu.entity.houseSecond.TSellingInfo;
import cn.hshb.web.partner.baidu.entity.houseSecond.TSellingPayment;
import cn.hshb.web.partner.baidu.entity.houseSecond.TStatus;
import cn.hshb.web.partner.baidu.entity.houseSecond.TTaxInfo;
import cn.hshb.web.partner.baidu.entity.houseSecond.TType;
import cn.hshb.web.partner.baidu.entity.houseSecond.TUrl;
import cn.hshb.web.partner.baidu.entity.houseSecond.Urlset;

/**
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public class TestForXML {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	private THouse getHouseSecond(int idx){
		THouse h = new THouse();
		h.setBuildNum("BuildNum_"+idx);
		h.setBuildType(TBuildType.塔楼);
		h.setBuildUnit( BigInteger.valueOf(70));
		h.setCovorImage("CovorImage_"+idx);
		h.setDecoration(TDecoration.精装修);
		h.setHouseAllFloor("HouseAllFloor_"+idx);
		h.setHouseArea(String.valueOf(75)+"m2");
		h.setHouseFloor(String.valueOf(10));
		h.setHouseImageNum(String.valueOf(10)+"张");
		h.setHouseOrientation(THouseOrientation.南北通透);
		h.setHouseSituation(THouseSituation.自住);
		h.setHouseStructurename(THouseStructurename.两居);
		h.setHouseStructureshi(String.valueOf(2)+"室");
		h.setHouseStructureting(String.valueOf(2)+"厅");
		h.setHouseStructurewei(String.valueOf(1)+"卫");
		h.setHouseType(THouseType.公寓);
		h.setPropertyVerified("1");
		h.setRoomNum(BigInteger.valueOf(2));
		h.setUrl("Url_"+idx);
		
		for(int ii=0; ii<10; ii++)
			h.getHouseImage().add("HouseImage_"+idx+"_"+ii);

		return h;
	}
	
	private List<TContact> getContacts(int idx){
		List<TContact> list = new ArrayList<TContact>();
		for(int jj=0; jj<5; jj++){
			TContact c = new TContact();
			c.setFlag("1");
			c.setGender(TGender.男);
			c.setName("Name_"+idx);
			c.setProfile("Profile_"+idx);
			c.setTelephone("Telephone_"+idx);
			c.setType(T1Type.个人);
			c.setWorksFor("豪世华邦");
			
			list.add(c);
		}
		return list;
	}
	
	private List<TInstallations> getInstallations(int idx){
		List<TInstallations> list = new ArrayList<TInstallations>();
		for(int ii=0; ii<TInstallations.values().length; ii++){
			list.add(TInstallations.values()[ii]);
		}
		return list;
	}
	
	private Urlset getHouseSecondSet() {
		Urlset urlset = new Urlset();
		for(int ii=0; ii<2; ii++){
			TUrl url = new TUrl();
			urlset.getUrl().add(url);
			url.setLoc("http://www.hshb.cn/"+ii);
			
			TData data = new TData();
			url.setData(data);

			TSellingInfo sell = new TSellingInfo();
			data.setSellingInfo(sell);
			
			sell.setBroker("Broker_"+ii);
			sell.setBrokerTel("BrokerTel_"+ii);
			sell.setCheckinTime("CheckinTime_"+ii);
			sell.setDomain(TDomain.房产);
			sell.setHouse(getHouseSecond(ii));
			
			sell.setLastmod(sdf.format(Calendar.getInstance().getTime()));
			sell.setName("Name_"+ii);
			sell.setPriority(BigDecimal.valueOf(0.5));
			sell.setPublishTime(sdf.format(Calendar.getInstance().getTime()));
			
			TRealEstate e = new TRealEstate();
			sell.setRealEstate(e);
			e.setRealestateCity("RealestateCity_"+ii);
			e.setRealestateID("RealestateID_"+ii);
			e.setRealestateName("RealestateName_"+ii);
			
			sell.setSchoolDistrict(true);
			sell.setSellingIntroduce("SellingIntroduce_"+ii);
			sell.setSellingPayment(TSellingPayment.商业贷款);
			sell.setSellingPrice(BigInteger.valueOf(2000000));
			sell.setSellingPriceAvg("SellingPriceAvg_"+ii);
			sell.setSource("Source_"+ii);
			sell.setStatus(TStatus.ADD);
			sell.setTaxInfo(TTaxInfo.唯一不满五);
			
			sell.setTimeforView("TimeforView_"+ii);
			sell.setType(TType.HOUSE_PROPERTY);
			
			sell.getContact().addAll(getContacts(ii));
			
			sell.getInstallations().addAll(getInstallations(ii));
		}
		return urlset;
	}
	
	private boolean validateXml(String xsdPath, String xmlStr) {
		try{
      SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
      Schema schema = sf.newSchema(new File(xsdPath)); 

      Validator validator = schema.newValidator();
      InputSource inputSource = new InputSource(new StringReader(xmlStr)) ;
      Source source = new SAXSource(inputSource); 
      
//      validator.setErrorHandler(new MyErrorHandler());
      validator.validate(source);			
			
			return true;
		}catch(SAXException ex){
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void testForHouseSecond(){
		Urlset urlset = getHouseSecondSet();
		try{
	    JAXBContext jaxbContext = JAXBContext.newInstance(Urlset.class);  
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();  
	
	    // output pretty printed  
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
	
	//    jaxbMarshaller.marshal(urlset, file);  
	    StringWriter sw = new StringWriter();
	    jaxbMarshaller.marshal(urlset, sw);
	    sw.flush();
	    String xml = sw.toString();
	    System.out.println(xml);
	    
	    validateXml("D:/Project/website/project/ddhb/hshb_src/cn/hshb/web/partner/baidu/entity/example/HouseSecondSchema.xsd", xml);
		} catch (JAXBException ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args){
		TestForXML test = new TestForXML();
		test.testForHouseSecond();
	}
}
