/**
 * 
 */
package cn.hshb.web.biz.util;

import java.beans.PropertyEditorSupport;
import java.lang.reflect.Method;

import com.huatek.framework.exception.BusinessRuntimeException;

import cn.hshb.web.common.util.StringUtil;

/**
 * @author ShengYoufu
 *
 */
public class BaseEntityEditor extends PropertyEditorSupport {
	private final Class<?> beanClass;
	private final boolean allowEmpty;

	/** @deprecated */
	public BaseEntityEditor(Class<?> beanClass, boolean allowEmpty) throws IllegalArgumentException {
		if (beanClass == null) {
			throw new IllegalArgumentException("Property class must be a subclass of Number");
		} else {
			this.beanClass = beanClass;
			this.allowEmpty = allowEmpty;
		}
	}

	public BaseEntityEditor(Class<?> beanClass) throws IllegalArgumentException {
		if (beanClass == null) {
			throw new IllegalArgumentException("Property class must be a subclass of Number");
		} else {
			this.beanClass = beanClass;
			this.allowEmpty = true;
		}
	}

	public void setAsText(String text) {
		Object obj = null;

		try {
			if (StringUtil.isEmpty(text) && allowEmpty) {
				setValue(obj);
				return;
			}

			obj = beanClass.newInstance();
			Long e = Long.valueOf(text);
			Method setMethod = beanClass.getMethod("setId", new Class[] { Long.class });
			setMethod.invoke(obj, new Object[] { e });
		} catch (Exception ex) {
			throw new BusinessRuntimeException(ex.getMessage(), ex);
		}

		this.setValue(obj);
	}

	public String getAsText() {
		Object obj = getValue();
		if (obj == null) {
			return null;
		} else if (!beanClass.isInstance(obj)) {
			return obj.toString();
		} else {
			try {
				Method e = beanClass.getMethod("getId", new Class[0]);
				Long id = (Long) e.invoke(obj, new Object[0]);
				return id == null ? null : id.toString();
			} catch (Exception ex) {
				throw new BusinessRuntimeException(ex.getMessage(), ex);
			}
		}
	}
}
