package com.huatek.framework.util;

import com.huatek.framework.util.HuatekSessionContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class HuatekSessionListener implements HttpSessionListener {
	public void sessionCreated(HttpSessionEvent arg0) {
		HuatekSessionContext.getInstance().addSession(arg0.getSession());
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		HuatekSessionContext.getInstance().delSession(arg0.getSession());
	}
}
