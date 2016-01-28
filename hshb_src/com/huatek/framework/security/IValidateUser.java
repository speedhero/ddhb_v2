package com.huatek.framework.security;

public interface IValidateUser {
	Object getUser(String var1, String var2, String var3,String verifyCode, String sessionId);
}
