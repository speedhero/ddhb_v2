package com.huatek.hbwebsite.show.memberfunc;

import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.util.QueryCondition;
import com.huatek.framework.util.Util;
import com.huatek.hbwebsite.member.entity.PlatCollection;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.service.PlatCollectionService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping({ "/myCollection.do" })
@SessionAttributes(types = { PlatCollection.class })
public class PlatCollectionController {
	@Autowired
	private PlatCollectionService platCollectionService;
	private static final String PLAT_COLLECTION_QUERY_PAGE_PATH = "bps/front/buyer/order/mycollection/myCollectionList.jsp";
	private static final String GOODS_SPEC_LIST_PAGE_PATH = "bps/front/buyer/order/mycollection/goodsSpecPopup.jsp";
	private static final Logger LOGGER = Logger.getLogger(PlatCollectionController.class);

	@RequestMapping(params = { "actionMethod=query" })
	public String queryMyCollection(Model model, HttpServletRequest request) {
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		CutPageBean pageBean = QueryCondition.getQueryPageBean(request);
		CutPageBean cutPageBean = this.platCollectionService.getCutPageBean((List) null, pageBean, platMemberInfo);
		List platColloctionList = cutPageBean.getDataList();
		if (platColloctionList != null && platColloctionList.size() > 0) {
			this.evaluateGoodsTimes(platColloctionList);
		}

		model.addAttribute("pageBean", cutPageBean);
		model.addAttribute("totalRows", Integer.valueOf(cutPageBean.getTotalRows()));
		return "bps/front/buyer/order/mycollection/myCollectionList.jsp";
	}

	public void evaluateGoodsTimes(List<PlatCollection> platColloctionList) {
	}

	@RequestMapping(params = { "actionMethod=deleteMyCollection" })
	public String deleteMyCollection(@RequestParam("collectionId") Long collectionId) {
		PlatCollection platCollection = (PlatCollection) this.platCollectionService.getObjectById(PlatCollection.class, collectionId);
		this.platCollectionService.delete(platCollection);
		return "redirect:myCollection.do?actionMethod=query";
	}

	@RequestMapping(params = { "actionMethod=delete" })
	public String delete(@RequestParam("ids") String vipCollectionIds) {
		String[] strIds = vipCollectionIds.split(",");
		if (strIds != null) {
			for (int i = 0; i < strIds.length; ++i) {
				PlatCollection vipCollection = (PlatCollection) this.platCollectionService.getObjectById(PlatCollection.class, Long.valueOf(strIds[i]));
				this.platCollectionService.delete(vipCollection);
			}
		}
		return "redirect:myCollection.do?actionMethod=query";
	}

	@RequestMapping(params = { "actionMethod=addtoCollection" }, method = { RequestMethod.POST })
	public void addToCollection(Model model, HttpServletRequest request, HttpServletResponse response) {
		String status = "{\"result\":\"error\"}";
		String houseId = request.getParameter("ObjectId");
		String collectType = request.getParameter("collectType");
		String brokerId = request.getParameter("brokerId");
		int colType = Integer.parseInt(collectType);
		String priceCc = request.getParameter("priceCc");
		float pc = Float.parseFloat(priceCc);
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (houseId != null) {
			int ret = this.platCollectionService.savePlatCollection(colType, houseId, brokerId, pc, platMemberInfo);
			if (ret == 1) {
				Long e = this.platCollectionService.HasExistedCollection(platMemberInfo.getId(), houseId, colType);
				status = "{\"result\":\"success\", \"collectId\":\"" + e + "\"}";
			} else if (ret == 0) {
				status = "{\"result\":\"recall\"}";
			} else if (ret == 2) {
				status = "{\"result\":\"error\"}";
			}
		} else {
			status = "{\"result\":\"error\"}";
		}

		PrintWriter out = null;

		try {
			out = response.getWriter();
			out.write(status);
			out.flush();
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}	

	/**
	 * 取消收藏房源或小区
	 * @param response
	 * @param collectionId
	 */
	@RequestMapping(params = { "actionMethod=cancleMyCollection" })
	public void cancleCollection(HttpServletResponse response, @RequestParam("collectionId") Long collectionId) {
		PlatCollection platCollection = (PlatCollection) this.platCollectionService.getObjectById(PlatCollection.class, collectionId);
		this.platCollectionService.delete(platCollection);
		PrintWriter out = null;
		String status = "{\"result\":\"success\"}";
		try {
			out = response.getWriter();
			out.write(status);
			out.flush();
		} catch (IOException var10) {
			LOGGER.error(var10.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}


	/**
	 * 收藏小区
	 * @deprecated
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = { "actionMethod=addCommunitytoCollection" }, method = { RequestMethod.POST })
	public void addCommunitytoCollection(Model model, HttpServletRequest request, HttpServletResponse response) {
		String status = "{\"result\":\"error\"}";
		String houseId = request.getParameter("ObjectId");
		String collectType = request.getParameter("collectType");
		int colType = Integer.parseInt(collectType);
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		if (houseId != null) {
			int ret = this.platCollectionService.addCollectCommunity(colType, houseId, platMemberInfo);
			if (ret == 1) {
				Long e = this.platCollectionService.HasExistedCollection(platMemberInfo.getId(), houseId, colType);
				status = "{\"result\":\"success\", \"collectId\":\"" + e + "\"}";
			} else if (ret == 0) {
				status = "{\"result\":\"recall\"}";
			} else if (ret == 2) {
				status = "{\"result\":\"error\"}";
			}
		} else {
			status = "{\"result\":\"error\"}";
		}

		PrintWriter out = null;

		try {
			out = response.getWriter();
			out.write(status);
			out.flush();
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}	
	
	/**
	 * 取消小区收藏
	 * @deprecated 本方法已不建议使用，统一改调用 cancleCollection
	 * @param response
	 * @param collectionId
	 */
	@RequestMapping(params = { "actionMethod=cancleMyCommunityCollection" })
	public void cancleCommunityCollection(HttpServletResponse response, @RequestParam("collectionId") String collectionId) {
		PlatCollection platCollection = null;
		List platCollections = this.platCollectionService.getPlatCollections(collectionId);
		if (platCollections != null) {
			platCollection = (PlatCollection) platCollections.get(0);
		}

		this.platCollectionService.delete(platCollection);
		PrintWriter out = null;
		String status = "{\"result\":\"success\"}";
		try {
			out = response.getWriter();
			out.write(status);
			out.flush();
		} catch (IOException var11) {
			LOGGER.error(var11.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}


	/**
	 * 检测房源或小区是否已收藏
	 * @param model
	 * @param request	
	 * @param productId	房源或小区ID
	 * @return	收藏ID
	 */
	@RequestMapping(params = { "actionMethod=checkAddToCollection" })
	public String checkAddToCollection(Model model, HttpServletRequest request, @RequestParam("productId") String productId) {
		PlatMemberInfo platMemberInfo = (PlatMemberInfo) request.getSession().getAttribute("LoginMember");
		Long collectId = platCollectionService.HasExistedCollection(platMemberInfo.getId(), productId, 0);
		return Util.printString(String.valueOf(collectId));
	}

	//无用方法
//	@RequestMapping(params = { "actionMethod=initGoodsSpecList" })
//	public String initGoodsSpecList(Model model, @RequestParam("goodsId") Long goodsId) {
//		return "bps/front/buyer/order/mycollection/goodsSpecPopup.jsp";
//	}
}
