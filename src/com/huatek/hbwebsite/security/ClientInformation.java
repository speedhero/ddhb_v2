package com.huatek.hbwebsite.security;

import com.huatek.hbwebsite.security.ClientInfoBean;
import javax.servlet.http.HttpServletRequest;

public interface ClientInformation {
	ClientInfoBean collectClientInfo(HttpServletRequest var1);
}
