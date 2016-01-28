package com.huatek.hbwebsite.util;

import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

public final class Parameter {
	private static final Logger LOGGER = Logger.getLogger(Parameter.class);
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
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("parameter config init");
			}

			this.prop.load(this.getClass().getResourceAsStream("/parameter.properties"));
		} catch (IOException var2) {
			LOGGER.error(var2.getMessage());
		}

	}
}
