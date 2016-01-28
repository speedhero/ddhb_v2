package com.huatek.hbwebsite.company.service;

import com.huatek.base.service.BaseService;
import com.huatek.hbwebsite.company.entity.CompanyCustomerService;
import com.huatek.hbwebsite.company.entity.CompanyCustomerServiceType;
import com.huatek.hbwebsite.company.entity.CompanyIntroduce;
import com.huatek.hbwebsite.company.entity.Contact;
import java.util.List;

public interface CompanyService extends BaseService {
	CompanyIntroduce getCompanyInfo();

	List<CompanyCustomerServiceType> getServiceType();

	Contact getContact();

	boolean theRecordIsExit(String var1, String var2, String var3);

	List<CompanyCustomerService> getUnsyncCustomerService();

	void updateSyncFlag(String var1);
}
