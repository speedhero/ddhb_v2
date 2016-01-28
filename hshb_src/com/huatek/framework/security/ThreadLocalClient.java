package com.huatek.framework.security;

import com.huatek.framework.security.ClientInfoBean;

public class ThreadLocalClient {
	private static final ThreadLocal<ClientInfoBean> CONTAINER = new ThreadLocal() {
		protected synchronized ClientInfoBean initialValue() {
			return new ClientInfoBean();
		}
	};

	public static void put(ClientInfoBean object) {
		CONTAINER.set(object);
	}

	public static ClientInfoBean get() {
		return (ClientInfoBean) CONTAINER.get();
	}

	public static void remove() {
		CONTAINER.remove();
	}
}
