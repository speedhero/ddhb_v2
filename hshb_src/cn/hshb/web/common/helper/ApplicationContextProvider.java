/**
 * 
 */
package cn.hshb.web.common.helper;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 在spring配置文件中声明：
 * <bean id="applicationContextProvider" class="cn.hshb.web.common.helper.ApplicationContextProvider"></bean>
 * ，然后就可以在程序的任何类里通过
 * ApplicationContextProvider.getApplicationContext()获取到ApplicationContext
 * @author ShengYoufu
 *
 */
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext ctx = null;
    public static ApplicationContext getContext() {
        return ctx;    
    }
    
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        // Assign the ApplicationContext into a static method
        this.ctx = ctx;
    }
    
    /**
     * 根据类型取所有Bean
     * @param clz
     * @return
     */
    public static Map<String, ? extends Object> getBeansOfType(Class<? extends Object> clz){
    	Map<String, ? extends Object> beanMap = getContext().getBeansOfType(clz);
    	return beanMap;
    }
    
    /**
     * 根据类型取一个Bean
     * @param clz
     * @return
     */
    public static Object getBeanOfType(Class<? extends Object> clz){
    	Map<String, ? extends Object> beanMap = getBeansOfType(clz);
    	if(beanMap!=null && beanMap.size()>0){
    		for(Object o: beanMap.values())
    			return o;
    	}
    	return null;
    }
    
    
}
