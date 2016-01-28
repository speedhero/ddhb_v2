package com.huatek.framework.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class Parameter {
	private static final Log log = LogFactory.getLog(Parameter.class);
	private static final String PARAMETER_FILE = "/parameter.properties";
	private static final Parameter INSTANCE = new Parameter();
	private Properties prop = null;

	public static Parameter getInstance() {
		return INSTANCE;
	}

	public Properties getProp() {
		return this.prop;
	}

	public String getProperty(String key) {
		return this.prop.getProperty(key);
	}

	public String getProperty(String key, String defaultValue) {
		return this.prop.getProperty(key, defaultValue);
	}

	private Parameter() {
		this.prop = new Properties();

		try {
			if (log.isInfoEnabled()) {
				log.info("parameter config init");
			}
			prop.load(this.getClass().getResourceAsStream(PARAMETER_FILE));
		} catch (IOException ex) {
			log.error("载入配置文件["+PARAMETER_FILE+"]失败。", ex);
		}

	}
}
