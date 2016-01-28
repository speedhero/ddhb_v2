package com.huatek.hbwebsite.util;

import com.huatek.ddhb.manage.frontsystemsetting.entity.FrontSystemSetting;
import com.huatek.ddhb.manage.frontsystemsetting.service.FrontSystemSettingService;
import com.huatek.framework.util.SpringContext;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.log4j.Logger;

public class CallERPPublicCls {
	private static FrontSystemSettingService frontSystemSettingService = null;
	private static final CallERPPublicCls callERPPublicCls = new CallERPPublicCls();
	private static String ERPWebServiceAddr_path = "";
	private static String ERPWebServiceAddr_UserName = "";
	private static String ERPWebServiceAddr_PassWord = "";
	private static final Logger LOGGER = Logger.getLogger(CallERPPublicCls.class);

	public static CallERPPublicCls getInstance() {
		return callERPPublicCls;
	}

	public static synchronized String getERPWebServiceAddrUserName() {
		if (!"".equals(ERPWebServiceAddr_UserName) && ERPWebServiceAddr_UserName != null) {
			return ERPWebServiceAddr_UserName;
		} else {
			frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = frontSystemSettingService.loadSpecificSetting("WS_UserName");
			return frontSystemSetting.getSettingValue();
		}
	}

	public static synchronized String getERPWebServiceAddrPassword() {
		if (!"".equals(ERPWebServiceAddr_PassWord) && ERPWebServiceAddr_PassWord != null) {
			return ERPWebServiceAddr_PassWord;
		} else {
			frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = frontSystemSettingService.loadSpecificSetting("WS_Password");
			return frontSystemSetting.getSettingValue();
		}
	}

	public static synchronized String getERPWebServiceAddrPath() {
		if (!"".equals(ERPWebServiceAddr_path) && ERPWebServiceAddr_path != null) {
			return ERPWebServiceAddr_path;
		} else {
			frontSystemSettingService = (FrontSystemSettingService) SpringContext.getBean("frontSystemSettingService");
			FrontSystemSetting frontSystemSetting = frontSystemSettingService.loadSpecificSetting("WS_Path");
			return frontSystemSetting.getSettingValue();
		}
	}

	public static synchronized void clean() {
		ERPWebServiceAddr_UserName = "";
		ERPWebServiceAddr_PassWord = "";
		ERPWebServiceAddr_path = "";
	}

	public static String CallERPWebser(String strXML) {
		String strResult = "";

		try {
			Service ex = new Service();
			Call call = (Call) ex.createCall();
			call.setTargetEndpointAddress(new URL(getERPWebServiceAddrPath()));
			call.setUseSOAPAction(true);
			call.setSOAPActionURI("http://xerp.hzhuabang.net/DataSync");
			call.setOperationName(new QName("http://xerp.hzhuabang.net/", "DataSync"));
			call.addParameter(new QName("http://xerp.hzhuabang.net/", "xmlString"), XMLType.XSD_STRING, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);
			SOAPHeaderElement soapHeaderElement = new SOAPHeaderElement("http://xerp.hzhuabang.net/", "ERPSoapHeader");
			soapHeaderElement.setNamespaceURI("http://xerp.hzhuabang.net/");
			soapHeaderElement.addChildElement("UserName").setValue(getERPWebServiceAddrUserName());
			soapHeaderElement.addChildElement("PassWord").setValue(getERPWebServiceAddrPassword());
			call.addHeader(soapHeaderElement);
			strResult = (String) call.invoke(new Object[] { strXML });
		} catch (Exception var5) {
			LOGGER.error(var5.getMessage());
		}

		return strResult;
	}

	public static String strXML(String UUID, String DataType, String DataBodyXML) {
		StringBuffer strBu = new StringBuffer();
		DataBodyXML = DataBodyXML.replace("<list>", "<DataBody>");
		DataBodyXML = DataBodyXML.replace("</list>", "</DataBody>");
		strBu.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		strBu.append("<BasicData>");
		strBu.append("<DataHeader>");
		strBu.append("<DataSetID>" + UUID + "</DataSetID>");
		strBu.append("<DataType>" + DataType + "</DataType>");
		strBu.append("</DataHeader>");
		strBu.append(DataBodyXML);
		strBu.append("</BasicData>");
		return strBu.toString();
	}
}
