package cn.hshb.web.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.CommonFooterMenuMapper;
import cn.hshb.web.biz.mybatis.model.CommonFooterMenu;
import cn.hshb.web.biz.service.CommonFooterMenuService;

@Service
public class CommonFooterMenuServiceImpl implements CommonFooterMenuService {

	private static List<CommonFooterMenu> commonFooterMenuList = new ArrayList<CommonFooterMenu>();
	
	@Autowired
	private CommonFooterMenuMapper commonFooterMenuMapper;
	
	private void loadCommonFooterMenu(){
		if(commonFooterMenuList == null || commonFooterMenuList.size() < 1){
			synchronized (CommonFooterMenuServiceImpl.class) {
				if(commonFooterMenuList.size() < 1 || commonFooterMenuList == null){
					commonFooterMenuList = commonFooterMenuMapper.selectByAllCommonFooterMenu();
				}
			}
		}

	}
	
	@Override
	public List<CommonFooterMenu> getCommonFooterMenu() {
		loadCommonFooterMenu();
		return commonFooterMenuList;
	}

}
