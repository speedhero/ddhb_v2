package com.huatek.framework.security;

import com.huatek.framework.security.ClientInfoBean;
import javax.servlet.http.HttpServletRequest;

public interface ClientInformation {
	ClientInfoBean collectClientInfo(HttpServletRequest var1);
}
