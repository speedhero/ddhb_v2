/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hshb.web.biz.mybatis.dao.CompanyContactusMapper;
import cn.hshb.web.biz.mybatis.dao.CompanyCustomerServiceMapper;
import cn.hshb.web.biz.mybatis.dao.CompanyCustomerServiceTypeMapper;
import cn.hshb.web.biz.mybatis.dao.CompanyIntroduceMapper;
import cn.hshb.web.biz.mybatis.dao.HouseArtificialReportMapper;
import cn.hshb.web.biz.mybatis.model.CompanyContactus;
import cn.hshb.web.biz.mybatis.model.CompanyContactusExample;
import cn.hshb.web.biz.mybatis.model.CompanyCustomerService;
import cn.hshb.web.biz.mybatis.model.CompanyCustomerServiceExample;
import cn.hshb.web.biz.mybatis.model.CompanyCustomerServiceType;
import cn.hshb.web.biz.mybatis.model.CompanyCustomerServiceTypeExample;
import cn.hshb.web.biz.mybatis.model.CompanyIntroduce;
import cn.hshb.web.biz.mybatis.model.CompanyIntroduceExample;
import cn.hshb.web.biz.mybatis.model.HouseArtificialReport;
import cn.hshb.web.biz.service.CompanyService;
import cn.hshb.web.partner.baidu.common.StringUtil;

/**
 * @author ShengYoufu
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {
	private static final Log log = LogFactory.getLog(CompanyServiceImpl.class);
	private static final int PAGE_SIZE = 5;

	@Autowired
	private CompanyContactusMapper companyContactusMapper;
	
	@Autowired
	private CompanyCustomerServiceMapper companyCustomerServiceMapper;
	
	@Autowired
	private CompanyCustomerServiceTypeMapper companyCustomerServiceTypeMapper;
	
	@Autowired
	private HouseArtificialReportMapper houseArtificialReportMapper;
	
	@Autowired
	private CompanyIntroduceMapper companyIntroduceMapper;

	@Override
	public CompanyIntroduce getCompanyInfo() {
		CompanyIntroduceExample example = new CompanyIntroduceExample();
		example.createCriteria().andDeleteflagEqualTo(0);
		List<CompanyIntroduce> list = companyIntroduceMapper.selectByExample(example);
		return (list !=null &&  list.size()>0)? list.get(0) : new CompanyIntroduce();
	}

	@Override
	public List<CompanyCustomerServiceType> getServiceType() {
		CompanyCustomerServiceTypeExample example = new CompanyCustomerServiceTypeExample();
		example.createCriteria().andDeleteflagEqualTo(0);
		return companyCustomerServiceTypeMapper.selectByExample(example);
	}

	@Override
	public CompanyContactus getContact() {
		CompanyContactusExample example = new CompanyContactusExample();
		example.createCriteria().andDeleteflagEqualTo(0);
		List<CompanyContactus> list = companyContactusMapper.selectByExample(example);
		return list != null && list.size() > 0 ? list.get(0) : new CompanyContactus();
	}

	@Override
	public boolean theRecordIsExit(String userName, String phoneNumber, String content) {
		CompanyCustomerServiceExample example = new CompanyCustomerServiceExample();
		example.createCriteria()
			.andUsernameEqualTo(userName)
			.andTelephoneNoEqualTo(phoneNumber)
			.andContentEqualTo(content)
			.andDeleteflagEqualTo(0);
		Integer count = companyCustomerServiceMapper.countByExample(example);
		return count>0;
	}

//	@Override
//	public List<CompanyCustomerService> getUnsyncCustomerService() {
//		String authorityTotalSQL = DataAuthority.getAuthorityString(
//				" from CompanyCustomerService cs where cs.deleteFlag = 0 and cs.synchronizedFlag = 0",
//				this.getSessionFactory());
//		Query query = this.getSessionFactory().getCurrentSession().createQuery(authorityTotalSQL);
//		query.setMaxResults(PAGE_SIZE);
//		List objlist = query.list();
//		return objlist;
//	}
	
	@Override
	public Boolean updateSyncFlag(String idStr) {
		CompanyCustomerService record = new CompanyCustomerService();
		record.setSynchronizedFlag(1);
		record.setSynchronizedTime(new Date());
		
		String[] ids = idStr.split(",");
		List<Integer> idList = new ArrayList<Integer>();
		for(String id: ids)
			if(StringUtil.isNumeric(id))
				idList.add(Integer.parseInt(id));
		CompanyCustomerServiceExample example = new CompanyCustomerServiceExample();
		example.createCriteria().andServiceNoIn(idList);
		
		int ret = companyCustomerServiceMapper.updateByExampleSelective(record, example);
		return ret > 0;
	}
	
	@Override
	public Boolean save(CompanyCustomerService ccs){
		int ret = companyCustomerServiceMapper.insert(ccs);
		return ret > 0;
	}
	
	@Override
	public Boolean save(HouseArtificialReport har){
		int ret = houseArtificialReportMapper.insert(har);
		return ret > 0;
	}
	
}
