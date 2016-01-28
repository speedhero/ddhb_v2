/**
 * 
 */
package cn.hshb.web.common.util;

import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.huatek.framework.util.DESEncryptor;

/**
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0 http://www.hshb.cn
 * 
 */
//public class PropertiesEncryptFactoryBean implements FactoryBean {
public class PropertiesEncryptFactoryBean extends PropertyPlaceholderConfigurer{

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props){
		decryptProperties(props);
		super.processProperties(beanFactoryToProcess, props);
	}

	/*
	@Override
	protected String resolvePlaceholder(String placeholder, Properties props){
		System.out.println("resolvePlaceholder");
		return  "";
	}

	@Override
	protected String 	resolvePlaceholder(String placeholder, Properties props, int systemPropertiesMode){
		System.out.println("resolvePlaceholder");
		return "";
	}

	@Override
	protected String 	resolveSystemProperty(String key){
		System.out.println("resolveSystemProperty");
		return "";
	}
	*/
	
	protected void decryptProperties(Properties props){
		//对加密属性进行解密
		DESEncryptor des = new DESEncryptor();
		for(Object k: (Set<Object>)props.keySet()){
			if(k == null) continue;
			String key = (String)k;
			String val = props.getProperty(key);
			if(val!=null){
				if(val.toUpperCase().startsWith("{DES}")){
					//如果属性值前面有{des}表示是采用DES加密
					val = des.decrypt(val.substring(5));
					props.put(key, val);
				}
				//TODO:这里还可以实现对其他加密方式的解密 
			}
		}

	}
}