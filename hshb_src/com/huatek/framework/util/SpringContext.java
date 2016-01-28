package com.huatek.framework.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringContext implements ServletContextListener {
	private WebApplicationContext springContext;
	public static SpringContext instance = new SpringContext();

	public static void setSpringContext(WebApplicationContext springContext) {
		instance.springContext = springContext;
	}

	public void contextInitialized(ServletContextEvent event) {
		instance.springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	}

	public void contextDestroyed(ServletContextEvent event) {
		instance.springContext = null;
	}

	public static ApplicationContext getApplicationContext() {
		return instance.springContext;
	}

	public static Object getBean(String beanName) {
		return instance.springContext.getBean(beanName);
	}
}
