package com.huatek.framework.util;

import com.huatek.framework.security.ThreadLocalClient;
import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.EntityUtil;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class QueryCondition {
	private static List<CommonBean> getPrivateQueryCondition(HttpServletRequest request, Object queryClass,
			HashMap<String, String> operatorMap, boolean isApplyLikeOperator) {
		Object paramterList = new ArrayList();
		String saveKey = null;
		if (ThreadLocalClient.get().getSrcAction() != null) {
			saveKey = ThreadLocalClient.get().getSrcAction().getId() + ThreadLocalClient.get().getActionMethod();
		}

		if (request.getMethod().equalsIgnoreCase("post")) {
			Enumeration i = request.getParameterNames();

			while (i.hasMoreElements()) {
				String name = ((String) i.nextElement()).toString();
				String flag = "";
				if (name.startsWith("ht_")) {
					String fieldName = name.substring(3);
					   empty(request,name,fieldName);
					if (name.startsWith("ht_") && CommonUtil.isNotZeroLengthTrimString(request.getParameter(name))) {
						CommonBean commonBean = null;
						String value = request.getParameter(name);
						if (queryClass == null) {
							commonBean = new CommonBean(fieldName, value);
						} else {
							Object typeValue = null;
							if ("_s".equals(fieldName.substring(fieldName.length() - 2, fieldName.length()))) {
								fieldName = fieldName.substring(0, fieldName.length() - 2);
								value = value + " 00:00:00";
								flag = "lt";
							}

							if ("_l".equals(fieldName.substring(fieldName.length() - 2, fieldName.length()))) {
								fieldName = fieldName.substring(0, fieldName.length() - 2);
								value = value + " 23:59:59";
								flag = "gt";
							}

							if ("_ss".equals(fieldName.substring(fieldName.length() - 3, fieldName.length()))) {
								fieldName = fieldName.substring(0, fieldName.length() - 3);
								flag = "lt";
							}

							if ("_ll".equals(fieldName.substring(fieldName.length() - 3, fieldName.length()))) {
								fieldName = fieldName.substring(0, fieldName.length() - 3);
								flag = "gt";
							}

							if ("_ac".equals(fieldName.substring(fieldName.length() - 3, fieldName.length()))) {
								fieldName = fieldName.substring(0, fieldName.length() - 3);
								flag = "ac";
							}

							if (queryClass instanceof HashMap) {
								typeValue = EntityUtil.getPropertyValue((HashMap) queryClass, fieldName, value);
							} else {
								typeValue = EntityUtil.getPropertyValue((Class) queryClass, fieldName, value);
							}

							commonBean = new CommonBean(fieldName, value, typeValue);
							if ("lt".equals(flag)) {
								commonBean.setArithmeticOpertor(">=");
							}

							if ("gt".equals(flag)) {
								commonBean.setArithmeticOpertor("<=");
							}
						}

						if (operatorMap != null && operatorMap.get(fieldName) != null) {
							commonBean.setArithmeticOpertor((String) operatorMap.get(fieldName));
						}

						if ("ac".equals(flag)) {
							commonBean.setApplyLikeOperator(false);
						} else {
							commonBean.setApplyLikeOperator(isApplyLikeOperator);
						}

						((List) paramterList).add(commonBean);
						if ("lt".equals(flag)) {
							request.getSession().setAttribute(fieldName.replaceAll("\\.", "_") + "_s", request.getParameter(name));
						} else if ("gt".equals(flag)) {
							request.getSession().setAttribute(fieldName.replaceAll("\\.", "_") + "_l", request.getParameter(name));
						} else {
							request.getSession().setAttribute(fieldName.replaceAll("\\.", "_"), request.getParameter(name));
						}
					} else if (request.getSession().getAttribute(fieldName.replaceAll("\\.", "_")) != null) {
						request.getSession().setAttribute(fieldName.replaceAll("\\.", "_"), (Object) null);
					}
				}
			}

			if (saveKey != null) {
				request.getSession().setAttribute(saveKey, paramterList);
			}
		} else if (saveKey != null && request.getSession().getAttribute(saveKey) != null) {
			paramterList = (List) request.getSession().getAttribute(saveKey);

			for (int var13 = 0; var13 < ((List) paramterList).size(); ++var13) {
				request.getSession().setAttribute(
						((CommonBean) ((List) paramterList).get(var13)).getName().replaceAll("\\.", "_"),
						((CommonBean) ((List) paramterList).get(var13)).getValue());
			}
		}

		return (List) paramterList;
	}

	/**
	 * 清空
	 * @param request
	 * @param entityClassMap
	 * @return
	 */
	   public static void empty(HttpServletRequest request,String name,String fieldName){
		   if("_s".equals(fieldName.substring(fieldName.length() - 2, fieldName.length()))) {
			   request.getSession().setAttribute(fieldName.replaceAll("\\.", "_") , request.getParameter(name));
	        }

	        if("_l".equals(fieldName.substring(fieldName.length() - 2, fieldName.length()))) {
	        	 request.getSession().setAttribute(fieldName.replaceAll("\\.", "_") , request.getParameter(name));
	        }
		   if("_ss".equals(fieldName.substring(fieldName.length() - 3, fieldName.length()))) {
	           fieldName = fieldName.substring(0, fieldName.length() - 3);
	           request.getSession().setAttribute(fieldName.replaceAll("\\.", "_") + "_s", request.getParameter(name));
	        }

	        if("_ll".equals(fieldName.substring(fieldName.length() - 3, fieldName.length()))) {
	           fieldName = fieldName.substring(0, fieldName.length() - 3);
	           request.getSession().setAttribute(fieldName.replaceAll("\\.", "_") + "_l", request.getParameter(name));
	        }
	        
	   }
	public static List<CommonBean> getQueryConditionByClassMap(HttpServletRequest request,
			HashMap<String, Class<?>> entityClassMap) {
		return getPrivateQueryCondition(request, entityClassMap, (HashMap) null, true);
	}

	public static List<CommonBean> getQueryConditionByClassMap(HttpServletRequest request,
			HashMap<String, Class<?>> entityClassMap, boolean isApplyLikeOperator) {
		return getPrivateQueryCondition(request, entityClassMap, (HashMap) null, isApplyLikeOperator);
	}

	public static List<CommonBean> getQueryConditionByClassMap(HttpServletRequest request,
			HashMap<String, Class<?>> entityClassMap, HashMap<String, String> operatorMap) {
		return getPrivateQueryCondition(request, entityClassMap, operatorMap, true);
	}

	public static List<CommonBean> getQueryConditionByClassMap(HttpServletRequest request,
			HashMap<String, Class<?>> entityClassMap, HashMap<String, String> operatorMap, boolean isApplyLikeOperator) {
		return getPrivateQueryCondition(request, entityClassMap, operatorMap, isApplyLikeOperator);
	}

	public static List<CommonBean> getQueryCondition(HttpServletRequest request, Class<?> queryClass) {
		return getPrivateQueryCondition(request, queryClass, (HashMap) null, true);
	}

	public static List<CommonBean> getQueryCondition(HttpServletRequest request, Class<?> queryClass,
			boolean isApplyLikeOperator) {
		return getPrivateQueryCondition(request, queryClass, (HashMap) null, isApplyLikeOperator);
	}

	public static List<CommonBean> getQueryCondition(HttpServletRequest request, Class<?> queryClass,
			HashMap<String, String> operatorMMap) {
		return getPrivateQueryCondition(request, queryClass, operatorMMap, true);
	}

	public static List<CommonBean> getQueryCondition(HttpServletRequest request, Class<?> queryClass,
			HashMap<String, String> operatorMMap, boolean isApplyLikeOperator) {
		return getPrivateQueryCondition(request, queryClass, operatorMMap, isApplyLikeOperator);
	}

	public static List<CommonBean> getQueryCondition(HttpServletRequest request) {
		return getQueryCondition(request, (Class) null);
	}

	public static CutPageBean getQueryPageBean(HttpServletRequest request, CutPageBean pageBean) {
		if (ThreadLocalClient.get().getSrcAction() == null) {
			return pageBean;
		} else {
			String saveKey = ThreadLocalClient.get().getSrcAction().getId() + ThreadLocalClient.get().getActionMethod();
			if (ThreadLocalClient.get().getRequestMethod().equalsIgnoreCase("post")) {
				request.getSession().setAttribute(saveKey + "_pageBean", pageBean);
				return pageBean;
			} else {
				return request.getSession().getAttribute(saveKey + "_pageBean") != null ? (CutPageBean) request.getSession()
						.getAttribute(saveKey + "_pageBean") : pageBean;
			}
		}
	}

	public static CutPageBean getQueryPageBean(HttpServletRequest request) {
		return getQueryPageBean(request, 0);
	}

	public static CutPageBean getQueryPageBean(HttpServletRequest request, int pageInitSize) {
		CutPageBean pageBean = new CutPageBean();
		if (request.getParameter("pageSize") == null && pageInitSize > 0) {
			pageBean.setPageSize(pageInitSize);
		} else if (request.getParameter("pageSize") != null) {
			pageBean.setPageSize(Integer.valueOf(request.getParameter("pageSize")).intValue());
		}

		if (request.getParameter("currentPage") != null) {
			pageBean.setCurrentPage(Integer.valueOf(request.getParameter("currentPage")).intValue());
		}

		if (ThreadLocalClient.get().getSrcAction() == null) {
			return pageBean;
		} else {
			String saveKey = ThreadLocalClient.get().getSrcAction().getId() + ThreadLocalClient.get().getActionMethod();
			if (ThreadLocalClient.get().getRequestMethod().equalsIgnoreCase("post")) {
				request.getSession().setAttribute(saveKey + "_pageBean", pageBean);
				return pageBean;
			} else {
				return request.getSession().getAttribute(saveKey + "_pageBean") != null ? (CutPageBean) request.getSession()
						.getAttribute(saveKey + "_pageBean") : pageBean;
			}
		}
	}
}
