/**
 * 
 */
package cn.hshb.web.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;

import cn.hshb.web.biz.mybatis.model.CommonFlag;

/**
 * @author Administrator
 *
 */
public class HouseTagsTag extends HshbBaseSimpleTag {
	private List<CommonFlag> tagList;
	
	private String houseTagId;

	public List<CommonFlag> getTagList() {
		return tagList;
	}

	public void setTagList(List<CommonFlag> tagList) {
		this.tagList = tagList;
	}

	public String getHouseTagId() {
		return houseTagId;
	}

	public void setHouseTagId(String houseTagId) {
		this.houseTagId = houseTagId;
	}
	
	public void doTag() throws JspException, IOException{
		StringBuilder sb = new StringBuilder("");
		if(tagList!=null && houseTagId!=null){
			String[] ids = houseTagId.split(",");
			for(CommonFlag tag: tagList){
				for(String id: ids){
					if(tag.getErpId().equalsIgnoreCase(id)){
						sb.append("<span style=\"background-color:").append(tag.getFlagcolor())
							.append(";color:").append(tag.getFontcolor()).append("\">")
							.append(tag.getTagName()).append("</span>").append("\n");
					}
				}
			}
		}
		write(sb.toString());
	}
}
