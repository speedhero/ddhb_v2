package com.huatek.hbwebsite.util;

import com.huatek.hbwebsite.house.entity.HouseNew;
import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class HomePageNewHouseRecommandTag extends BodyTagSupport {
	private static final long serialVersionUID = 5050163385659683039L;
	private List<HouseNew> newHouseList = null;
	private String pictureHost = "";
	private StringBuffer tag = new StringBuffer();

	public int doStartTag() throws JspException {
		if (this.newHouseList != null && this.newHouseList.size() > 0) {
			for (int ii = 0; ii < this.newHouseList.size(); ii += 2) {
				this.tag.append("<div class=\"item\">");
				this.tag.append("<div class=\"li_1\">");
				this.tag.append("<a href=\"#\"><img src=\"").append(this.pictureHost).append("/")
					.append(((HouseNew) this.newHouseList.get(ii)).getPictureUrl())
					.append("\" onclick=\"window.open(\'houseNew.show?actionMethod=showDetail&id=")
					.append(((HouseNew) this.newHouseList.get(ii)).getId())
					.append("\')\" width=\"230\" height=\"193\" /></a>");
				this.tag.append("<p></p>");
				this.tag.append("<a class=\"dw_a\" onclick=\"window.open(\'houseNew.show?actionMethod=showDetail&id=")
				.append(((HouseNew) this.newHouseList.get(ii)).getId()).append("\')\">")
				.append(((HouseNew) this.newHouseList.get(ii)).getBuildingName())
				.append( "</a>");
				this.tag.append("</div>");
				if (ii + 1 < this.newHouseList.size()) {
					this.tag.append("<div class=\"li_2\">");
					this.tag.append("<a href=\"#\"><img src=\"")
					.append(this.pictureHost)
					.append("/")
					.append(((HouseNew) this.newHouseList.get(ii + 1)).getPictureUrl())
					.append("\" onclick=\"window.open(\'houseNew.show?actionMethod=showDetail&id=")
					.append(((HouseNew) this.newHouseList.get(ii + 1)).getId())
					.append("\')\" width=\"230\" height=\"193\" /></a>");
					this.tag.append("<p></p>");
					this.tag.append("<a class=\"dw_a\" onclick=\"window.open(\'houseNew.show?actionMethod=showDetail&id=")
					.append(((HouseNew) this.newHouseList.get(ii)).getId())
					.append("\')\">")
					.append(((HouseNew) this.newHouseList.get(ii + 1)).getBuildingName())
					.append("</a>");
					this.tag.append("</div>");
				}

				this.tag.append("<div style=\"clear:both\"></div>");
				this.tag.append("</div>");
			}
			this.tag.append("<div style=\"clear:both\"></div>");
		}

		try {
			JspWriter writer = this.pageContext.getOut();
			writer.print(this.tag.toString());
			this.tag = new StringBuffer("");
			return 1;
		} catch (IOException ex) {
			throw new JspException(ex);
		}
	}

	public String getPictureHost() {
		return this.pictureHost;
	}

	public void setPictureHost(String pictureHost) {
		this.pictureHost = pictureHost;
	}

	public StringBuffer getTag() {
		return this.tag;
	}

	public void setTag(StringBuffer tag) {
		this.tag = tag;
	}

	public List<HouseNew> getNewHouseList() {
		return this.newHouseList;
	}

	public void setNewHouseList(List<HouseNew> newHouseList) {
		this.newHouseList = newHouseList;
	}
}
