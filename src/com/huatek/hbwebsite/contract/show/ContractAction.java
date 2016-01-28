package com.huatek.hbwebsite.contract.show;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huatek.framework.util.GsonUtil;
import com.huatek.hbwebsite.contract.entity.ContractEntity;
import com.huatek.hbwebsite.contract.entity.ContractFlowEntity;
import com.huatek.hbwebsite.util.CallERPPublicCls;
import com.huatek.hbwebsite.util.CaptchaServiceSingleton;
import com.opensymphony.oscache.util.StringUtil;

@Controller
@RequestMapping({ "/contract.show" })
public class ContractAction {
	private static final Log log = LogFactory.getLog(ContractAction.class);

	/**
	 * 查询合同进度
	 * 如果未传入参数，则显示查询表单；如果已传入参数，则根据参数查询合同进度信息并显示
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = { "actionMethod=contractQuery" })
	public String queryContractFlowPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		String cardNo = request.getParameter("cardNo");
		String phoneNum = request.getParameter("phoneNum");
		String verifyCode = request.getParameter("verifyCode");
		
		//如果身份证号和合同号码为空，则打开查询页面
		if(StringUtils.isBlank(cardNo) && StringUtils.isBlank(phoneNum)) 
			return "contract.query";
		cardNo = cardNo==null? "" : cardNo.trim();
		phoneNum = phoneNum==null? "" : phoneNum.trim();
		verifyCode = verifyCode==null? "" : verifyCode.trim();
		
		String resultJson = "";
		ContractEntity ce = null;
		String sessId = request.getSession().getId();
		try{
			String result = validate(cardNo, phoneNum, verifyCode, sessId);
			if("ok".equalsIgnoreCase(result)){
				ce = this.doContractQuery(cardNo, phoneNum);
				if(StringUtil.isEmpty(ce.getPhoneNum()))
					ce.setPhoneNum(phoneNum);
				ce.setResultCode("success");
				ce.setResultMessage("查询成功");
			}else{
				ce = new ContractEntity();
				ce.setPhoneNum(phoneNum);
				ce.setIDCardNo(cardNo);
				ce.setResultCode("failed");
				ce.setResultMessage(result);
			}
		}catch(Exception ex){
			ce = new ContractEntity();
			ce.setPhoneNum(phoneNum);
			ce.setIDCardNo(cardNo);
			ce.setResultCode("failed");
			ce.setResultMessage("查询合同进度失败，请重试。\r\n失败原因："+ex.getMessage());
		}
		if (ce != null) {
			resultJson = GsonUtil.getCommonGsonInstance().toJson(ce);
		}

		//把结果输出
		PrintWriter writer = null;
		try{
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			writer.print(resultJson);
		}catch(IOException ex ){
			log.error(ex);
		}finally{
			if(writer!=null){
				writer.close();
			}
			writer = null;
		}
		return null;
	}
	
	/**
	 * 校验参数有效性
	 * @param cardNo			身份证号
	 * @param contractNo	合同号
	 * @param verifyCode	图片校验码
	 * @param sessionId		Session ID
	 * @return
	 */
	private String validate(String cardNo, String phoneNum, String verifyCode, String sessionId){
		Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
		Pattern contractNoRegx = Pattern.compile("^[a-zA-Z0-9]{1,}$");
		Pattern phoneNumRegx = Pattern.compile("^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$");

		String message = "";
		if (StringUtil.isEmpty(verifyCode)) {
			message = "验证码必须录入!";
		} else if (StringUtil.isEmpty(cardNo)) {
			message = "身份证号必须录入!";
		} else if (!idNumPattern.matcher(cardNo).matches()) {
			message = "身份证号无效!";
		} else if (StringUtil.isEmpty(phoneNum)) {
			message = "手机号码必须录入!";
		} else if (!phoneNumRegx.matcher(phoneNum).matches()) {
			message = "手机号码无效!";
		} else {
			boolean flag = CaptchaServiceSingleton.getInstance().validateResponseForID(sessionId, verifyCode.toLowerCase());
			if (!flag) {
				message = "验证码错误!";
			} else {
				message = "ok";
			}
		}
		return message;
	}
	
	/**
	 * 执行合同查询
	 * @param cardNo			身份证号
	 * @param contractNo	合同号
	 * @return
	 */
	private ContractEntity doContractQuery(String cardNo, String contractNo){
		if (StringUtils.isNotBlank(cardNo ) && StringUtils.isNotBlank(contractNo)) {
			String requestXML = this.createQueryXML(contractNo, cardNo);

			//测试
//			String returnedXml = loadTestXML("D:\\test1\\ContractQuery_resp_20150206094238.xml");
			String returnedXml = CallERPPublicCls.CallERPWebser(requestXML);
			ContractEntity ce = this.parseXmlToObject(returnedXml);

			return ce;
	
		}
		return null;
	}
	
	/**
	 * 创建查询请求XML
	 * @param ContractID	合同ID
	 * @param IDCardNo		身份证号
	 * @return
	 */
	private String createQueryXML(String ContractID, String IDCardNo) {
		String strXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		strXML = strXML + "<BasicData>";
		strXML = strXML + "<DataHeader>";
		strXML = strXML + "<DataSetID>" + UUID.randomUUID() + "</DataSetID>";
		strXML = strXML + "<DataType>ContractQuery</DataType>";
		strXML = strXML + "</DataHeader>";
		strXML = strXML + "<DataBody>";
		strXML = strXML + "<Item>";
		strXML = strXML + "<ItemID>" + UUID.randomUUID() + "</ItemID>";
//		strXML = strXML + "<ContractID>" + ContractID + "</ContractID>";
		//TODO: 下面ContractID后面加“_1”是ERP接口中为了兼容可以使用原来的合同号查询而设，
		//没有“_1”表示按合同号查询，有“_1”表示按手机号码查询
		strXML = strXML + "<ContractID>" + ContractID + "_1</ContractID>";
		strXML = strXML + "<IDCardNo>" + IDCardNo + "</IDCardNo>";
		strXML = strXML + "</Item>";
		strXML = strXML + "</DataBody>";
		strXML = strXML + "</BasicData>";
		return strXML;
	}
	

	/**
	 * 把XML解析成对象
	 * @param xmlStr
	 * @return
	 */
	private ContractEntity parseXmlToObject(String xmlStr) {
		ContractEntity ce = new ContractEntity();
		SAXReader reader = new SAXReader();

		try {
			Document doc = reader.read(new StringReader(xmlStr));
			List<Node> contractList = doc.selectNodes("/BasicDataSyncResult/Results/Item/ContractID");
			if (contractList.size() > 0) {
				Node node = contractList.get(0);
				ce.setContractID(node.getText());
			}

			List<Node> idCardNumList = doc.selectNodes("/BasicDataSyncResult/Results/Item/IDCardNo");
			if (idCardNumList.size() > 0) {
				Node node = idCardNumList.get(0);
				ce.setIDCardNo(node.getText());
			}

			List<Node> flowNodeList = doc.selectNodes("/BasicDataSyncResult/Results/Item/Flow");
			List<ContractFlowEntity> flowList = new ArrayList<ContractFlowEntity>();
			for(Node node: flowNodeList){
				ContractFlowEntity flow = new ContractFlowEntity();
				flow.setStep(node.selectSingleNode("Step").getText());
				flow.setStepName(node.selectSingleNode("StepName").getText());
				flow.setState(node.selectSingleNode("State").getText());
				flow.setStepRemarks(node.selectSingleNode("StepRemarks").getText());
				String completionDate = node.selectSingleNode("CompletionDate").getText();
				if (!"".equals(completionDate)) {
					completionDate = completionDate.substring(0, 9);
				}

				if ("1".equals(flow.getState())) {
					completionDate = "【完成时间】" + completionDate;
				} else {
					completionDate = "【常规参考】" + completionDate;
				}

				flow.setCompletionDate(completionDate);
				flowList.add(flow);
			}

			ce.setFlows(flowList);
			
		} catch (DocumentException ex) {
			log.error(ex.getMessage());
			ce.setResultCode("failed");
			ce.setResultMessage("查询合同执行进度失败，失败原因："+ex.getMessage());
		}
		return ce;
	}

	/**
	 * 载入测试用的文件，仅供测试
	 * @param filePath
	 * @return
	 */
	private String loadTestXML(String filePath) {
		StringBuilder result = new StringBuilder();
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					result.append(lineTxt);
				}
				read.close();
			} else {
				log.error("找不到指定的文件");
			}
		} catch (Exception ex) {
			log.error("读取文件内容出错", ex);
		}
		return result.toString();
	}
		
}
