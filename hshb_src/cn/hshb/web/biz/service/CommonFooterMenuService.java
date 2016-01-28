package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.CommonFooterMenu;

public interface CommonFooterMenuService {
	
	/**
	 * 返回底部菜单区域及下级菜单信息
	 * @return
	 */
	public List<CommonFooterMenu> getCommonFooterMenu();
}
