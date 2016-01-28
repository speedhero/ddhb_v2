package com.huatek.framework.security;

import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.HuatekSessionContext;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = null;
		if (CommonUtil.isNotZeroLengthTrimString(request.getParameter("sessionId"))) {
			session = HuatekSessionContext.getInstance().getSession(request.getParameter("sessionId"));
			if (session != null) {
				session.invalidate();
				HuatekSessionContext.getInstance().delSession(session);
				session = null;
			} else {
				request.getSession().invalidate();
			}
		} else {
			session = request.getSession();
			session.invalidate();
		}

		request.getRequestDispatcher("/").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
