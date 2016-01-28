package com.huatek.hbwebsite.service;

import com.huatek.base.service.BaseService;

public interface ForbiddenService extends BaseService {
	boolean isInForbiddenList(String var1);
}
