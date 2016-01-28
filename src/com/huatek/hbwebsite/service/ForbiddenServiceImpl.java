package com.huatek.hbwebsite.service;

import com.huatek.base.service.BaseServiceImpl;
import com.huatek.hbwebsite.service.ForbiddenService;
import java.util.List;

public class ForbiddenServiceImpl extends BaseServiceImpl implements ForbiddenService {
	private static final String IP_IS_FORBIDDEN = " from FwForbiddenIP ffi where ffi.ipAddress = ? and ffi.deleteFlag = 0";

	public boolean isInForbiddenList(String ipAddress) {
		List fwForbiddenList = this.hibernateTemplate.find(
				" from FwForbiddenIP ffi where ffi.ipAddress = ? and ffi.deleteFlag = 0", ipAddress.trim());
		return fwForbiddenList.size() > 0;
	}
}
