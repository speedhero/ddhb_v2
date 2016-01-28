package com.huatek.hbwebsite.security;

import com.huatek.hbwebsite.security.ClientInfoBean;

public class ThreadLocalClient {
	private static final ThreadLocal<ClientInfoBean> CONTAINER = new ThreadLocal<ClientInfoBean>() {
		protected synchronized ClientInfoBean initialValue() {
			return new ClientInfoBean();
		}
	};

	public static void put(ClientInfoBean object) {
		CONTAINER.set(object);
	}

	public static ClientInfoBean get() {
		return CONTAINER.get();
	}

	public static void remove() {
		CONTAINER.remove();
	}
}
