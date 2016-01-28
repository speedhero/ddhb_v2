package cn.hshb.web.biz.mybatis.model;

import java.util.List;

public class CommonFooterMenu extends CommonFooterMenuKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_footer_menu.menu_name
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private String menuName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_footer_menu.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private Integer deleteflag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_footer_menu.menu_name
     *
     * @return the value of common_footer_menu.menu_name
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_footer_menu.menu_name
     *
     * @param menuName the value for common_footer_menu.menu_name
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_footer_menu.deleteflag
     *
     * @return the value of common_footer_menu.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_footer_menu.deleteflag
     *
     * @param deleteflag the value for common_footer_menu.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }
    /* 自定义 */
    
    /**
     * 关联底部商圈
     */
    private List<CommonFooterLink> linkList;

	public List<CommonFooterLink> getLinkList() {
		return linkList;
	}

	public void setLinkList(List<CommonFooterLink> linkList) {
		this.linkList = linkList;
	}
}