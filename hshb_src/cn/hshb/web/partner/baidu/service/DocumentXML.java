package cn.hshb.web.partner.baidu.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

public class DocumentXML {
//	private static final String  LAST_COMMIT_XML_CONTENT = String.valueOf( Parameter.getInstance().getProperty("last.commit.xml.content"));
//	private static final String  LAST_COMMIT_XML_CONTENT = "d:/";
	private static final Log log = LogFactory.getLog(DocumentXML.class);
	public DocumentXML(){}
	public DocumentXML(String savePath, String content){
		//获取当前时间
		String newContent = "<Datas>"+
							"<Timestamp>"+new Timestamp(System.currentTimeMillis())+"</Timestamp>"+
							"<Content>"+content+"</Content>"
							+ "</Datas>";
		this.saveHouseInformation(savePath, newContent);
	}
	public DocumentXML(String content, String houseId, String beforeSplit, String afterSplit, String savePath){
		this.saveHouseInformation(savePath, this.subStringContent(content, houseId, beforeSplit, afterSplit));
	}
	public DocumentXML(String houseId, String savePath, String del){
		if ("delete".equals(del))
			this.processingContentXML(houseId, savePath);
	}
	public String getContent(String savePath){
		return processingContentXML(null, savePath);
	}
	/**
	 * HEJB
	 * 把最近一次处理的数据保存下来
	 * @param savePath 文件类型
	 * @param content	内容
	 */
	protected void saveHouseInformation(String savePath, String content){
		File f = null;
		Writer fw = null;
		BufferedWriter bd = null;
		try {
			String _savePath = savePath;
			if( savePath.lastIndexOf("\\") != -1){
				_savePath = savePath.substring(0, savePath.lastIndexOf("\\"));
			}else if(savePath.lastIndexOf("/") != -1){
				_savePath = savePath.substring(0, savePath.lastIndexOf("/"));
			}
			f = new File(_savePath);
			if(!f.exists())
				f.mkdirs();
			fw = new FileWriter(savePath, true);
			bd = new BufferedWriter(fw);
			bd.write(content);
		} catch (Exception e) {
			log.error(e);
		}finally{
			if(bd != null){
				try {
					bd.close();
				} catch (IOException e) {
					log.error(e);
					e.printStackTrace();
				}
			}
			if(fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					log.error(e);
				}
		}
	}
	
	/**
	 * 
	 * @param content 内容
	 * @param houseId	        以houseId 为关键字
	 * @param beforeSplit  需要截取的前标签		<url>
	 * @param afterSplit   需要截取的后半部标签	</url>
	 * @return
	 */
	private String subStringContent(String content, String houseId, String beforeSplit, String afterSplit){
		String beforeContent = "";
		int subscript = content.indexOf(houseId);
		String valueContent = "";
		//获取前半部分的内容
		valueContent =   content.substring(0, subscript);
		beforeContent = content.substring(0, valueContent.lastIndexOf(beforeSplit));
		
		//获取下半部分的内容
		String afterContent = "";
		valueContent = content.substring(subscript,content.length());
		afterContent = content.substring(subscript + valueContent.indexOf(afterSplit)+afterSplit.length(), content.length());
		
		//在合并连个内容
		StringBuilder sb = new StringBuilder();
		sb.append(beforeContent);
		sb.append(afterContent);
		return sb.toString();
	}
	
	/**
	 * 查找保存的xml文件中 存在 要删除房源的ID
	 */
	private String processingContentXML(String houseId, String savePath){
		Reader fr =null;
		BufferedReader r = null;
		StringBuilder sb = new StringBuilder();
		String contentXML = null;
//		String savePath = LAST_COMMIT_XML_CONTENT + fileName + ".xml";
		try {
			fr =  new FileReader(savePath);
			r = new BufferedReader(fr);
			String content = r.readLine();
			while(content != null){
			sb.append(content);
			content = r.readLine();
			}
			content = sb.toString();
			//是否包含要删除房源的ID
			if (houseId != null && !"".equals(houseId)) {
				if (content.contains(houseId)) {
					// 删除带有houseId的数据，并存入xml
					saveHouseInformation(savePath, subStringContent(content, houseId, "<url>", "</url>"));
					// new DocumentXML(content, houseId, "<url>",
					// "</url>","d:/rent.xml");
				}
			}else{
				contentXML = sb.toString();
			}
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		finally{
			if(r != null){
				try {
					r.close();
				} catch (IOException e) {
					log.warn(e);
				}
			}
			if(fr != null){
				try {
					fr.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
		return contentXML;
	}
	
}
