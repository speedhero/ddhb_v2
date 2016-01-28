package com.huatek.framework.security;

public interface SecurityLoginInterFace {
	void passwordErrorNumRecord(Long var1);

	boolean isAccountLocked(Long var1);

	void unlocked(Long var1);
}
