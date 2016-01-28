/**
 * 
 */
package cn.hshb.web.partner.baidu.entity;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

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

import cn.hshb.web.partner.baidu.common.StringUtil;
import cn.hshb.web.partner.baidu.entity.houseSecond.Urlset;

/**
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0 http://www.hshb.cn
 * 
 */
public class BaseEntity {

	/**
	 * 当前对象转换成XML
	 * @return
	 * @throws JAXBException
	 */
	public String toXML() throws JAXBException {
		// Urlset urlset = getHouseSecondSet();

		JAXBContext jaxbContext = JAXBContext.newInstance(Urlset.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		// jaxbMarshaller.marshal(urlset, file);
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(this, sw);
		sw.flush();
		String xml = sw.toString();
		System.out.println(xml);
		return xml;

	}
	public String toXML(String urlsetType) throws JAXBException {
		// Urlset urlset = getHouseSecondSet();

		JAXBContext jaxbContext = null ;
		if("_rentDel".equals(urlsetType))
			jaxbContext = JAXBContext.newInstance(cn.hshb.web.partner.baidu.entity.houseRentDel.Urlset.class);
		if("_saleDel".equals(urlsetType))
			jaxbContext = JAXBContext.newInstance(cn.hshb.web.partner.baidu.entity.houseSecondDel.Urlset.class);
		if("rentDel".equals(urlsetType))
			jaxbContext = JAXBContext.newInstance(cn.hshb.web.partner.baidu.entity.newest.houseRentDel.Urlset.class);
		if("saleDel".equals(urlsetType))
			jaxbContext = JAXBContext.newInstance(cn.hshb.web.partner.baidu.entity.newest.houseSecondDel.Urlset.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		// jaxbMarshaller.marshal(urlset, file);
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(this, sw);
		sw.flush();
		String xml = sw.toString();
		System.out.println(xml);
		return xml;

	}
	/**
	 * 根据Schema校验XML
	 * 
	 * @param xsdPath
	 * @param xml
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 */
	public Boolean validateXML(String xsdPath, String xml) throws SAXException, IOException {
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(new File(xsdPath));

		Validator validator = schema.newValidator();
		InputSource inputSource = new InputSource(new StringReader(xml));
		Source source = new SAXSource(inputSource);

		// validator.setErrorHandler(new MyErrorHandler());
		validator.validate(source);

		return true;
	}
	
    protected static String strToCDATA(String val, String defaultStr){
    	String result = val;
    	if(StringUtil.isBlank(val)) result = defaultStr;
    	if(result.contains(">") || result.contains("<") || result.contains("&") || result.contains("\"") || result.contains("'"))
    		result = "<![CDATA["+result+"]]>";
    	return result;
    }
}
