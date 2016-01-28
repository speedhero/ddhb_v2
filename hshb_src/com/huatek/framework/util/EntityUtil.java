package com.huatek.framework.util;

import com.huatek.base.entity.BaseEntity;
import com.huatek.framework.exception.BusinessRuntimeException;
import com.huatek.framework.util.CommonUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;

public class EntityUtil {
	public static Object getClassTypeValue(String value, Class<?> valueType) {
		if (valueType.equals(String.class)) {
			return value;
		} else if (!valueType.equals(Integer.TYPE) && !valueType.equals(Integer.class)) {
			if (!valueType.equals(Long.TYPE) && !valueType.equals(Long.class)) {
				if (BaseEntity.class.isAssignableFrom(valueType)) {
					BaseEntity object;
					try {
						object = (BaseEntity) valueType.newInstance();
					} catch (Exception var4) {
						throw new BusinessRuntimeException(var4.getMessage(), var4);
					}

					object.setId(Long.valueOf(value));
					return object;
				} else if (valueType.equals(Date.class)) {
					return CommonUtil.getDateValue(value);
				} else if (valueType.equals(java.sql.Date.class)) {
					return java.sql.Date.valueOf(value);
				} else if (!valueType.equals(Double.TYPE) && !valueType.equals(Double.class)) {
					if (!valueType.equals(Float.TYPE) && !valueType.equals(Float.class)) {
						if (!valueType.equals(Boolean.TYPE) && !valueType.equals(Boolean.class)) {
							if (valueType.equals(BigInteger.class)) {
								return new BigInteger(value);
							} else if (valueType.equals(BigDecimal.class)) {
								return new BigDecimal(value);
							} else if (valueType.equals(Character.TYPE)) {
								return Character.valueOf(value.charAt(0));
							} else if (!valueType.equals(Byte.TYPE) && !valueType.equals(Byte.class)) {
								throw new BusinessRuntimeException("Can not find a suitability data type for the type " + valueType);
							} else {
								return Byte.valueOf(value);
							}
						} else {
							return Boolean.valueOf(value);
						}
					} else {
						return Float.valueOf(value);
					}
				} else {
					return Double.valueOf(value);
				}
			} else {
				return Long.valueOf(value);
			}
		} else {
			return Integer.valueOf(value);
		}
	}

	public static Class<?> getClassPropertyType(Class<?> myClass, String propertyName) {
		String methodName = "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);

		try {
			return myClass.getMethod(methodName, new Class[0]).getReturnType();
		} catch (Exception var4) {
			throw new BusinessRuntimeException(var4.getMessage(), var4);
		}
	}

	public static Object getPropertyValue(Class<?> myclass, String inputProperty, String value) {
		String property = inputProperty.substring(inputProperty.indexOf(".") + 1);
		String[] properties = property.split("\\.");
		Class propertyClass = null;

		for (int i = 0; i < properties.length; ++i) {
			if (i == 0) {
				propertyClass = getClassPropertyType(myclass, properties[0]);
			} else {
				propertyClass = getClassPropertyType(propertyClass, properties[i]);
			}
		}

		return getClassTypeValue(value, propertyClass);
	}

	public static Object getPropertyValue(HashMap<String, Class<?>> entityClassMap, String inputProperty, String value) {
		String classAliaName = inputProperty.substring(0, inputProperty.indexOf("."));
		String property = inputProperty.substring(inputProperty.indexOf(".") + 1);
		String[] properties = property.split("\\.");
		Class propertyClass = null;

		for (int i = 0; i < properties.length; ++i) {
			if (i == 0) {
				propertyClass = getClassPropertyType((Class) entityClassMap.get(classAliaName), properties[0]);
			} else {
				propertyClass = getClassPropertyType(propertyClass, properties[i]);
			}
		}

		return getClassTypeValue(value, propertyClass);
	}
}
