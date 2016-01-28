/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.CompanyContactus;
import cn.hshb.web.biz.mybatis.model.CompanyCustomerService;
import cn.hshb.web.biz.mybatis.model.CompanyCustomerServiceType;
import cn.hshb.web.biz.mybatis.model.CompanyIntroduce;
import cn.hshb.web.biz.mybatis.model.HouseArtificialReport;

/**
 * @author ShengYoufu
 *
 */
public interface CompanyService {

	public CompanyIntroduce getCompanyInfo();

	public List<CompanyCustomerServiceType> getServiceType();

	public CompanyContactus getContact();

	public boolean theRecordIsExit(String userName, String phoneNumber, String content);

//	public List<CompanyCustomerService> getUnsyncCustomerService();

	public Boolean updateSyncFlag(String idStr);
	
	public Boolean save(CompanyCustomerService ccs);

	public Boolean save(HouseArtificialReport har);
}
