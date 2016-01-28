/**
 * 
 */
package cn.hshb.web.test;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.message.SOAPHeaderElement;

/**
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0 http://www.hshb.cn
 * 
 */
public class DataSyncTest {
	
	public static String strXml;
	static {
		strXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		strXml += "<BasicData>";
		strXml += "<DataHeader>";
		strXml += "<DataSetID>6f9960c0-0ac7-4efd-9bad-1f0941735bdd</DataSetID>";
		strXml += "<DataType>Employee</DataType>";
		strXml += "</DataHeader>";
		strXml += "<DataBody>";
		strXml += "<Item>";
		strXml += "<ItemID>d8ca4b2c-f70c-4c0b-9edf-644e39dd5e7c</ItemID>";
		strXml += "<EmployeeID>eb4a310f-9fa3-4a0a-ad5e-0185ff30bba1</EmployeeID>";
		strXml += "<EmployeeName>戴金华</EmployeeName>";
		strXml += "<Introduction>1、本人08年毕业于浙江交通大学，多次参加杭州多家媒体（二手房特邀评认员）2、在杭州居住6年，熟悉杭州每一个角落，挖掘（高性价比房源和谈价）是我的强项!3.专营拱墅区（和睦 小河 塘河 桥西）等学区房</Introduction>";
		strXml += "<MobilePhone>15384013400</MobilePhone>";
		strXml += "<QQ>1426603563@qq.com</QQ>";
		strXml += "<Wechat>15384013400</Wechat>";
		strXml += "<qrcode>";
		strXml += "<![CDATA[123456]]>";
		strXml += "</qrcode>";
		strXml += "<PictureData>";
		strXml += "<![CDATA[123456]]>";
		strXml += "</PictureData>";
		strXml += "<PictureFormat>JPG</PictureFormat>";
		strXml += "<StorefrontID>abda0703-570d-4135-856a-7fada1a4fb7f</StorefrontID>";
		strXml += "<IsDimission>3</IsDimission>";
		strXml += "<IsDeleted>0</IsDeleted>";
		strXml += "<LastModified>2015-05-14 14:27:00</LastModified>";
		strXml += "<LastSync>2015-05-15 13:31:45</LastSync>";
		strXml += "<OperationID>1</OperationID>";
		strXml += "</Item>";
		strXml += "</DataBody>";
		strXml += "</BasicData>";
	}
	
	public static String employeeSync(){

		String strResult = "";
		try {
			Service svc = new Service();
			Call call = (Call) svc.createCall();
			call.setTargetEndpointAddress(new URL("http://127.0.0.1:8080/ddhbManage/services/HBWebService?wsdl"));
			call.setUseSOAPAction(true);
			call.setSOAPActionURI("http://axis2.dongdao.net/DataSync");
			call.setOperationName(new QName("http://axis2.dongdao.net", "DataSync"));
			call.addParameter(new QName("http://axis2.dongdao.net", "xmlString"), XMLType.XSD_STRING, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);
			
			SOAPHeaderElement soapHeaderElement = new SOAPHeaderElement("http://axis2.dongdao.net", "ERPSoapHeader");
			soapHeaderElement.setNamespaceURI("http://axis2.dongdao.net");
			soapHeaderElement.addChildElement("UserName").setValue("testuser");
			soapHeaderElement.addChildElement("PassWord").setValue("111111");
			call.addHeader(soapHeaderElement);
			strResult = (String) call.invoke(new Object[] { strXml });
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strResult;
	}
//	
//	public static String employeeTest(){
//		try {
//			ServiceClient client = new ServiceClient();
//			
//			//Creating a SOAPFactory
//			SOAPFactory factory = OMAbstractFactory.getSOAP12Factory();
//
//			// Creating a namespace for the header
//			OMNamespace ns = factory.createOMNamespace("http://ws.apache.org/axis2", "hns");
//
//			//creating a SOAP header block
//			SOAPHeaderBlock header = factory.createSOAPHeaderBlock("header", ns);
//
//			//adding a text value
//			header.setText("my custom header");
//
//			//adding an attribute
//			header.addAttribute("UserName" ,"testuser" ,null);
//			header.addAttribute("PassWord" ,"111111" ,null);
//			
//			//adding the created header block to ServiceClient
//			client.addHeader(header);
//
//		// create option object
//			Options opts = new Options();
//			//setting target EPR
//			opts.setTo(new EndpointReference("http://127.0.0.1:8080/ddhbManage/services/HBWebService?wsdl"));
//			//Setting action ,and which can be found from the wsdl of the service
//			opts.setAction("urn:DataSync");
//
//
//      OMFactory fac = OMAbstractFactory.getOMFactory();
//      OMNamespace omNs = fac.createOMNamespace(
//                  "http://ws.apache.org/axis2/xsd", "ns1");
//      OMElement method = fac.createOMElement("ping", omNs);
//      OMElement value = fac.createOMElement("value", omNs);
//      value.setText("10");
//      method.addChild(value);
//      return method;
//      
//			//setting created option into service client
//			client.setOptions(opts);
//			
//						client.setTargetEPR(targetEpr);
//		} catch (AxisFault e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataSyncTest.employeeSync();
	}
}
