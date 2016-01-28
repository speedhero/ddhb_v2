package com.huatek.framework.util;

import com.huatek.framework.entity.FwErrors;
import com.huatek.framework.entity.FwSource;
import com.huatek.framework.security.ThreadLocalClient;
import com.huatek.framework.service.SystemErrorService;
import com.huatek.framework.util.SpringContext;
import com.opensymphony.oscache.util.StringUtil;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

public final class ErrorsManagementUtil {
	public static void saveErrorMsg(HttpServletRequest request, Throwable error) {
		try {
			SystemErrorService systemErrorService = (SystemErrorService) SpringContext.getBean("systemErrorService");
			String errorMsg = error.getMessage();
			StackTraceElement[] stackTraceElementArray = error.getStackTrace();
			if (StringUtil.isEmpty(errorMsg)) {
				errorMsg = stackTraceElementArray[0].getClassName();
			}

			String errorPosition = stackTraceElementArray[0].getClassName() + "." + stackTraceElementArray[0].getMethodName()
					+ "(line:" + stackTraceElementArray[0].getLineNumber() + ")";

			int hashCode;
			for (hashCode = 0; hashCode < stackTraceElementArray.length; ++hashCode) {
				if (stackTraceElementArray[hashCode].getClassName().indexOf("com.huatek") >= 0) {
					errorPosition = stackTraceElementArray[hashCode].getClassName() + "."
							+ stackTraceElementArray[hashCode].getMethodName() + "(line:"
							+ stackTraceElementArray[hashCode].getLineNumber() + ")";
					break;
				}
			}

			hashCode = (errorMsg + errorPosition).hashCode();
			FwErrors fwErrors = systemErrorService.getFwErrors(Integer.valueOf(hashCode));
			if (fwErrors != null) {
				String var11 = systemErrorService.getObjectByIdLock(FwErrors.class, fwErrors.getId(), "errorCount");
				fwErrors.setErrorCount(Integer.valueOf(Integer.valueOf(var11).intValue() + 1));
				fwErrors.setLastUpdate(new Date());
				systemErrorService.saveOrUpdateFwErrors(fwErrors);
				return;
			}

			fwErrors = new FwErrors();
			fwErrors.setErrorMsg(errorMsg);
			fwErrors.setErrorPosition(errorPosition);
			fwErrors.setErrorCount(Integer.valueOf(1));
			fwErrors.setLastUpdate(new Date());
			if (ThreadLocalClient.get().getMenuId() != null) {
				FwSource params = (FwSource) systemErrorService.getObjectById(FwSource.class, ThreadLocalClient.get()
						.getMenuId());
				fwErrors.setFwSource(params);
				fwErrors.setFwSystem(params.getFwSystem());
			}

			fwErrors.setHashCode(hashCode);
			fwErrors.setQueryString(request.getQueryString());
			StringBuffer var10 = getParamValues(request);
			fwErrors.setParamInfo(var10.toString());
			systemErrorService.saveOrUpdateFwErrors(fwErrors);
		} catch (Exception var9) {
			;
		}

	}

	private static StringBuffer getParamValues(HttpServletRequest request) {
		StringBuffer params = new StringBuffer();
		Enumeration enumeration = request.getParameterNames();

		while (enumeration.hasMoreElements()) {
			String paramName = (String) enumeration.nextElement();
			params.append(paramName).append("=").append(request.getParameter(paramName));
			params.append("&");
		}

		return params;
	}

	public static void saveNotFoundError(HttpServletRequest request) {
		String url = "不存在该链接" + ThreadLocalClient.get().getRequestURL();
		SystemErrorService systemErrorService = (SystemErrorService) SpringContext.getBean("systemErrorService");
		FwErrors fwErrors = systemErrorService.getFwErrors(url);
		if (fwErrors != null) {
			fwErrors.setErrorCount(Integer.valueOf(fwErrors.getErrorCount().intValue() + 1));
			fwErrors.setLastUpdate(new Date());
			systemErrorService.saveOrUpdateFwErrors(fwErrors);
		} else {
			fwErrors = new FwErrors();
			fwErrors.setErrorMsg(url);
			fwErrors.setErrorCount(Integer.valueOf(1));
			fwErrors.setLastUpdate(new Date());
			systemErrorService.saveOrupdate(fwErrors);
		}
	}
}
