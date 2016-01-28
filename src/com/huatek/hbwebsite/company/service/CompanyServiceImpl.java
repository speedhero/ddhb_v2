package com.huatek.hbwebsite.company.service;

import com.huatek.authority.factory.DataAuthority;
import com.huatek.base.service.BaseServiceImpl;
import com.huatek.hbwebsite.company.entity.CompanyCustomerService;
import com.huatek.hbwebsite.company.entity.CompanyCustomerServiceType;
import com.huatek.hbwebsite.company.entity.CompanyIntroduce;
import com.huatek.hbwebsite.company.entity.Contact;
import com.huatek.hbwebsite.company.service.CompanyService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;

public class CompanyServiceImpl extends BaseServiceImpl implements CompanyService {
	private static final String GET_UNSYNCRENTCUSTOMERSERVICE = " from CompanyCustomerService cs where cs.deleteFlag = 0 and cs.synchronizedFlag = 0";
	private static final int PAGE_SIZE = 5;

	public CompanyIntroduce getCompanyInfo() {
		String result = "from CompanyIntroduce t where t.deleteFlag = 0";
		List comList = this.hibernateTemplate.find(result);
		return comList != null && comList.size() > 0 ? (CompanyIntroduce) comList.get(0) : new CompanyIntroduce();
	}

	public List<CompanyCustomerServiceType> getServiceType() {
		String result = "from CompanyCustomerServiceType t where t.deleteFlag = 0";
		return this.hibernateTemplate.find(result);
	}

	public Contact getContact() {
		String result = "from Contact t where t.deleteFlag = 0";
		List list = this.hibernateTemplate.find(result);
		return list != null && list.size() > 0 ? (Contact) list.get(0) : new Contact();
	}

	public boolean theRecordIsExit(String userName, String phoneNumber, String content) {
		String hql = " from CompanyCustomerService ccs where ccs.username = ? and ccs.telephoneNo = ? and ccs.content = ? and deleteflag = 0";
		Object[] valueArray = new Object[] { userName, phoneNumber, content };
		List companyCustomerServiceList = this.hibernateTemplate.find(hql, valueArray);
		return companyCustomerServiceList.size() > 0;
	}

	public List<CompanyCustomerService> getUnsyncCustomerService() {
		String authorityTotalSQL = DataAuthority
				.getAuthorityString(" from CompanyCustomerService cs where cs.deleteFlag = 0 and cs.synchronizedFlag = 0",
						this.getSessionFactory());
		Query query = this.getSessionFactory().getCurrentSession().createQuery(authorityTotalSQL);
		query.setMaxResults(5);
		List objlist = query.list();
		return objlist;
	}

	public void updateSyncFlag(String idStr) {
		StringBuilder updateSql = new StringBuilder("update CompanyCustomerService cs");
		updateSql.append(" set cs.synchronizedFlag = 1, cs.synchronizedTime = ? where cs.id in (");
		updateSql.append(idStr);
		updateSql.append(")");
		this.hibernateTemplate.bulkUpdate(updateSql.toString(), new Timestamp((new Date()).getTime()));
	}
}
