package cn.hshb.web.biz.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hshb.web.biz.service.InputSearchService;
import cn.hshb.web.biz.service.impl.InputSearchServiceImpl;
import cn.hshb.web.partner.baidu.common.StringUtil;

/**
 * 手动输入查询条件时,被ajax调用,产生下拉框数据
 * @author hejianbo
 *	2015年9月28日
 */
@Controller
@RequestMapping(value={"/search"})
public class InputSearchController {
	private static final Log log =  LogFactory.getLog(InputSearchController.class);
	@Autowired
	InputSearchService inputSearchService;
	/**
	 * 首页智能搜索
	 * @param pathStr1
	 * @param pathStr2
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value={"/inputKeyword/{pathStr}"})
	public void searchKeyword(@PathVariable String pathStr ,Model model, HttpServletRequest request, HttpServletResponse response){
		String strJson = "";
		if(StringUtil.isNotEmpty(pathStr)){
			strJson = inputSearchService.getSearchJsonData(pathStr);
		}
		response.setCharacterEncoding("UTF-8");
		PrintWriter w = null;
		try {
			w = response.getWriter();
			w.print(strJson);
			w.flush();
		} catch (IOException e) {
			log.error("智能搜索报错啦！,需急救>_<",e);
		}finally{
			if(w != null){
				w.close();
			}
			w = null;
		}
	}
}
