package com.huatek.hbwebsite.show.memberfunc;

import com.huatek.hbwebsite.show.memberfunc.CleanCompareAndHistoryMapServletThread;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CleanCompareAndHistoryMapServlet extends HttpServlet {
	private static final long serialVersionUID = -2172483305749704104L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	public void init() throws ServletException {
		CleanCompareAndHistoryMapServletThread andHistoryMapServletThread = new CleanCompareAndHistoryMapServletThread();
		andHistoryMapServletThread.start();
		super.init();
	}
}
