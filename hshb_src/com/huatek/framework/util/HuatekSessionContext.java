package com.huatek.framework.util;

import cn.hshb.web.biz.util.HouseRecommendUtil;

import com.huatek.framework.util.HouseSecondRecommendUtil;
import com.huatek.framework.util.MemberBrowseHistoryAnalyser;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public final class HuatekSessionContext {
	private static HuatekSessionContext instance = new HuatekSessionContext();
	private Map<String, HttpSession> sessionMap = new HashMap();

	public static HuatekSessionContext getInstance() {
		return instance;
	}

	public synchronized void addSession(HttpSession session) {
		if (session != null) {
			session.setAttribute("browsedHistory", new MemberBrowseHistoryAnalyser(Integer.valueOf(10)));
			session.setAttribute("houseSecondHistory", new HouseSecondRecommendUtil(Integer.valueOf(10)));
			session.setAttribute("houseRentHistory", new HouseSecondRecommendUtil(Integer.valueOf(10)));
			//新增
			session.setAttribute("houseSecondHistorys", new HouseRecommendUtil(Integer.valueOf(10)));
			session.setAttribute("houseRentHistorys", new HouseRecommendUtil(Integer.valueOf(10)));
			session.setAttribute("browsedHistorys", new HouseRecommendUtil(Integer.valueOf(10)));
			this.sessionMap.put(session.getId(), session);
		}

	}

	public synchronized void delSession(HttpSession session) {
		if (session != null) {
			this.sessionMap.remove(session.getId());
		}

	}

	public synchronized HttpSession getSession(String session_id) {
		return session_id == null ? null : (HttpSession) this.sessionMap.get(session_id);
	}
}
