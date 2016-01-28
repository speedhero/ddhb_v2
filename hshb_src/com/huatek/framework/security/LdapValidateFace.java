package com.huatek.framework.security;

public interface LdapValidateFace {
	boolean isLdapUser(String var1, String var2);

	Object updateLocalUserInfo(String var1, String var2);
}
