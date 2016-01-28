package com.huatek.hbwebsite.broker.service;

import com.huatek.authority.factory.DataAuthority;
import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.tag.QueryPageBean;
import com.huatek.hbwebsite.broker.entity.Broker;
import com.huatek.hbwebsite.broker.service.BrokerService;
import com.huatek.hbwebsite.common.entity.BroderAnsered;
import com.huatek.hbwebsite.common.entity.CBDExport;
import com.huatek.hbwebsite.common.entity.CommunityExpert;
import com.huatek.hbwebsite.common.entity.PageAccessQuantity;
import com.huatek.hbwebsite.common.entity.QuestionStategy;
import com.huatek.hbwebsite.common.entity.QuestionStrategySubtype;
import com.huatek.hbwebsite.common.entity.QuestionStrategyType;
import com.huatek.hbwebsite.service.BaseServiceToImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

public class BrokerServiceImpl extends BaseServiceToImpl implements BrokerService {
	
	/**
	 * 查询经纪人的持有房源
	 */
	public CutPageBean findHouseSecondByBrokerId(String brokerId, CutPageBean cutPageBean, List<CommonBean> commonList) {
		/**
		 * 原程序的逻辑有误：
		 * 查询经纪人的持有房源，不要把该经纪人评价的房源也算进来，只取该经纪人发布的房源即可
		 * Modified by syf at 2015.01.30
		String totalRowsHsql = "select count(distinct t.shelvingID) from HouseSecond t where t.deleteFlag = 0 and t.broker.erpId = \'"
				+ brokerId
				+ "\' or t.shelvingID in ( select distinct ha.houseId from HouseAppraise ha where ha.houseType = 1 and ha.broker.erpId = \'"
				+ brokerId + "\')";
		String resultSql = "from HouseSecond t where t.deleteFlag = 0 and t.broker.erpId = \'"
				+ brokerId
				+ "\' or t.shelvingID in ( select distinct ha.houseId from HouseAppraise ha where ha.houseType = 1 and ha.broker.erpId = \'"
				+ brokerId + "\')";
		*/

		String totalRowsHsql = "select count(distinct t.shelvingID) from HouseSecond t "
														+"where t.deleteFlag = 0 and t.broker.erpId = \'" + brokerId+ "\' ";
		String resultSql = "from HouseSecond t where t.deleteFlag = 0 and t.broker.erpId = \'" + brokerId + "\' ";
		String sortSql = "order by t.sortIndex asc";

		CutPageBean pageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, sortSql, cutPageBean, commonList);
		return pageBean;
	}

	public CutPageBean findHouseRentByBrokerId(String brokerId, CutPageBean cutPageBean, List<CommonBean> commonList) {
		String totalRowsHsql = "select count(t) from HouseRent t where t.deleteFlag = 0 and t.broker.erpId = \'"
				+ brokerId
				+ "\' or t.shelvingID in ( select distinct ha.houseId from HouseAppraise ha where ha.houseType = 2 and ha.broker.erpId = \'"
				+ brokerId + "\')";
		String resultSql = "from HouseRent t where t.deleteFlag = 0 and t.broker.erpId = \'"
				+ brokerId
				+ "\' or t.shelvingID in ( select distinct ha.houseId from HouseAppraise ha where ha.houseType = 2 and ha.broker.erpId = \'"
				+ brokerId + "\')";
		String sortSql = "order by t.sortIndex asc";
		CutPageBean pageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, sortSql,
				cutPageBean, commonList);
		return pageBean;
	}

	public CutPageBean getBrokerAnswered(String brokerId, CutPageBean cutPageBean, List<CommonBean> commonList) {
		String totalRowHsql = "select count(t) from BroderAnsered t where t.deleteFlag = 0 and t.broker.erpId = \'"
				+ brokerId + "\'";
		String resultSql = "from BroderAnsered t where t.deleteFlag = 0 and t.broker.erpId = \'" + brokerId + "\'";
		String sortSql = "order by t.lastModified asc";
		CutPageBean pageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowHsql, resultSql, sortSql,
				cutPageBean, commonList);
		return pageBean;
	}

	public int getBrokerAnsweredIsAccept(String brokerId) {
		String resultSql = "from BroderAnsered t where t.deleteFlag = 0 and t.isAccepted = 1 and t.broker.erpId = \'"
				+ brokerId + "\'";
		List list = this.hibernateTemplate.find(resultSql);
		return list.size();
	}

	public List<QuestionStrategyType> getQuestionStrategyType() {
		String sqlString = "from QuestionStrategyType t where t.deleteFlag = 0";
		return this.hibernateTemplate.find(sqlString);
	}

	public CutPageBean getSearchFiledList(CutPageBean pageBean, Map oneMap, Map<String, List> twoMap,
			Set<String> tagsSet, String orderStr, String sortfield, String housetype, String brokerId, int currentPage) {
		StringBuilder sbQuery = new StringBuilder();
		StringBuilder sbCount = new StringBuilder();
		sbQuery.append(" from ");
		sbCount.append("select count(h.shelvingID) from ");
		if ("1".equals(housetype.trim())) {
			sbQuery.append(" HouseSecond h ");
			sbCount.append(" HouseSecond h ");
		} else {
			sbQuery.append(" HouseRent h ");
			sbCount.append(" HouseRent h ");
		}

		sbQuery.append(" where h.deleteFlag = 0 and (h.broker.erpId=\'" + brokerId + "\' or h.shelvingID in ( ");
		sbCount.append(" where h.deleteFlag = 0 and (h.broker.erpId=\'" + brokerId + "\' or h.shelvingID in ( ");
		sbQuery.append(" select distinct ha.houseId from HouseAppraise ha where ha.houseType = 1 and ha.broker.erpId = \'"
				+ brokerId + "\')) ");
		sbCount.append(" select distinct ha.houseId from HouseAppraise ha where ha.houseType = 1 and ha.broker.erpId = \'"
				+ brokerId + "\')) ");
		Iterator cutpageBean = oneMap.keySet().iterator();

		String tagQuery;
		while (cutpageBean.hasNext()) {
			tagQuery = (String) cutpageBean.next();
			if (tagQuery.indexOf("erpId") >= 0) {
				sbQuery.append(" and h." + tagQuery + " = \'" + oneMap.get(tagQuery) + "\' ");
				sbCount.append(" and h." + tagQuery + " = \'" + oneMap.get(tagQuery) + "\' ");
			} else {
				sbQuery.append(" and h." + tagQuery + " = " + oneMap.get(tagQuery) + " ");
				sbCount.append(" and h." + tagQuery + " = " + oneMap.get(tagQuery) + " ");
			}
		}

		cutpageBean = twoMap.keySet().iterator();

		while (cutpageBean.hasNext()) {
			tagQuery = (String) cutpageBean.next();
			sbQuery.append(" and h." + tagQuery + "  between " + ((List) twoMap.get(tagQuery)).get(0) + " and "
					+ ((List) twoMap.get(tagQuery)).get(1) + " ");
			sbCount.append(" and h." + tagQuery + "  between " + ((List) twoMap.get(tagQuery)).get(0) + " and "
					+ ((List) twoMap.get(tagQuery)).get(1) + " ");
		}

		StringBuilder tagQuery1 = null;
		if (!tagsSet.isEmpty()) {
			tagQuery1 = new StringBuilder(" h.tags like ( \'");
			Iterator var14 = tagsSet.iterator();

			while (var14.hasNext()) {
				String cutpageBean1 = (String) var14.next();
				tagQuery1.append("%," + cutpageBean1 + ",%");
			}

			tagQuery1.append(" \') ");
		}

		if (tagQuery1 != null) {
			sbQuery.append(" and " + tagQuery1.toString());
			sbCount.append(" and " + tagQuery1.toString());
		}

		if (orderStr != null && !"".equals(orderStr.trim())) {
			if ("asc".equalsIgnoreCase(orderStr)) {
				sbQuery.append(" order by " + sortfield + " asc ");
			} else {
				sbQuery.append(" order by " + sortfield + " desc ");
			}
		}

		CutPageBean cutpageBean2 = this.getCutPage(pageBean, sbQuery.toString(), sbCount.toString(), currentPage);
		return cutpageBean2;
	}

	private CutPageBean getCutPage(CutPageBean pageBean, String hqlQuery, String hqlCount, int currentPage) {
		CutPageBean newPage = new CutPageBean();
		newPage.setExport(pageBean.isExport());
		newPage.setPageSize(pageBean.getPageSize());
		newPage.setCurrentPage(currentPage);
		String authorityTotalSQL = DataAuthority.getAuthorityString(hqlQuery, this.getSessionFactory());
		Query query = this.getSessionFactory().getCurrentSession().createQuery(authorityTotalSQL);
		query.setFirstResult((currentPage - 1) * 6);
		query.setMaxResults(6);
		Long rowCount = (Long) this.getHibernateTemplate().find(hqlCount).get(0);
		newPage.setTotalRows(rowCount.intValue());
		if (rowCount.longValue() == 0L) {
			return newPage;
		} else {
			newPage.setDataList(query.list());
			return newPage;
		}
	}

	public List<QuestionStategy> getQuestionStategyBySubType(String subTypeId, int displayNum) {
		String sqlString = "from QuestionStategy t where t.questionType.erpId= \'" + subTypeId
				+ "\' and t.deleteFlag=0 and t.isShow = 1 order by t.createTime desc";
		return this.getQueryWithMaxCount(sqlString, displayNum);
	}

	public CutPageBean getAllQuestionStategy(String subTypeId, CutPageBean cutPageBean, List<CommonBean> commonList,
			String keyWord) {
		String totalRowHsql = "select count(t) from QuestionStategy t where t.isShow = 1 and t.questionType.erpId = \'"
				+ subTypeId + "\' and t.deleteFlag = 0";
		String resultSql = "from QuestionStategy t where t.isShow = 1 and t.questionType.erpId = \'" + subTypeId
				+ "\'and t.deleteFlag = 0 and t.title like \'%" + keyWord + "%\'";
		String sortSql = "order by t.createTime desc";
		CutPageBean pageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowHsql, resultSql, sortSql,
				cutPageBean, commonList);
		return pageBean;
	}

	public List<QuestionStrategySubtype> getAllQuestionStrategySubType(String typeId) {
		String sqlString = "from QuestionStrategySubtype t where deleteFlag = 0 and t.parentType.erpId = \'" + typeId
				+ "\'";
		return this.hibernateTemplate.find(sqlString);
	}

	public List<Object> getQuestionStrategyCount(String typeId) {
		String sqlString = "select t.questionType.erpId, count(t.erpId) from QuestionStategy t where t.deleteFlag = 0 and t.isShow = 1 and t.questionType.parentType.erpId = \'"
				+ typeId + "\'group by t.questionType.erpId";
		return this.hibernateTemplate.find(sqlString);
	}

	public List<BroderAnsered> getBrokerAnswer(String questionId) {
		String sqlString = "from BroderAnsered t where t.questionStategy.erpId = \'" + questionId + "\'";
		return this.hibernateTemplate.find(sqlString);
	}

	public List<QuestionStategy> getQuestionStategyByTypeId(String typeId, int typrNum) {
		String sqlString = "from QuestionStategy t where t.questionType.parentType.erpId= \'" + typeId
				+ "\' and t.deleteFlag=0 and t.isShow = 1 order by t.createTime desc";
		return this.getQueryWithMaxCount(sqlString, typrNum);
	}

	public List<Object> getAllQuestionWithOutType() {
		String queryString = "select count(t.erpId) from QuestionStategy t where t.isShow = 1 and t.deleteFlag = 0";
		return this.hibernateTemplate.find(queryString);
	}

	public List<Object> getAllBrokerWithOutType() {
		String queryString = "select count(distinct t.broker.erpId) from BroderAnsered t";
		return this.hibernateTemplate.find(queryString);
	}

	public CutPageBean searchQuestionStategy(CutPageBean pageBean, String searchFileds, String subtype,
			List<CommonBean> commonList) {
		String totalRowHsql = "select count(t) from QuestionStategy t where (t.title like ? or t.content like ?) and t.isShow = 1 and t.deleteFlag = 0 and t.questionType.erpId = ?";
		String queryString = "from QuestionStategy t where (t.title like ? or t.content like ?) and t.isShow = 1 and t.deleteFlag = 0 and t.questionType.erpId = ?";
		String tempLike = "%" + searchFileds + "%";
		Object[] parmObjArray = new Object[] { tempLike, tempLike, subtype };
		CutPageBean cutPageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowHsql, queryString, "",
				parmObjArray, pageBean, commonList);
		return cutPageBean;
	}

	public List<CommunityExpert> getCommunityByBrokerId(String brokerId) {
		String sqlString = "from CommunityExpert t where t.broker.erpId = ? and t.deleteFlag = 0";
		return this.hibernateTemplate.find(sqlString, brokerId);
	}

	public List<CBDExport> getCBDExportByBrokerId(String brokerId) {
		String sqlString = "from CBDExport t where t.broker.erpId = \'" + brokerId + "\'";
		return this.hibernateTemplate.find(sqlString);
	}

	@Override
	public List<QuestionStategy> getAllQuestionStategy(String questionStrategyType) {
		// TODO Auto-generated method stub
//		String sqlString = " from QuestionStategy t where t.deleteFlag = 0 and t.isShow = 1 and t.questionType.parentType.erpId = \'"
//				+ questionStrategyType + "\'order by t.synchronizedTime desc ";
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(QuestionStategy.class,"q");
		criteria.createAlias("q.questionType", "questionType");
		criteria.createAlias("questionType.parentType", "parentType");
		criteria.add(Restrictions.eq("parentType.erpId", questionStrategyType));
		criteria.add(Restrictions.eq("q.deleteFlag", 0));
		criteria.add(Restrictions.eq("q.isShow", 1));
		//显示的上线为5条
		criteria.setMaxResults(5);
		criteria.addOrder(Order.desc("q.synchronizedTime"));
		return criteria.list();
	}

	@Override
	public List<BroderAnsered> getBrokerAnswerConditionBrokerErpId(String erpId) {
		String sqlString = "from BroderAnsered t where t.broker.erpId = \'" + erpId + "\'";
		return this.hibernateTemplate.find(sqlString);
	}

	/**
	 * 返回经纪人详情页访问量
	 */
	@Override
	public PageAccessQuantity getAccessQuantity(String erpId) {
		String hql = "from PageAccessQuantity p where p.pageInformationId = '" + erpId + "'";
		List<PageAccessQuantity> list = this.hibernateTemplate.find(hql);
		PageAccessQuantity paq = new PageAccessQuantity();
		//如果没有数据 ， 则添加这条数据
		if(list.size() == 0){
			PageAccessQuantity pa = new PageAccessQuantity();
			pa.setPageInformationId(erpId);
			pa.setAccessQuantity(0);
			pa.setDescription("经纪人详情页");
			this.hibernateTemplate.save(pa);
			paq = pa;
		}else{
			paq = list.get(0);
		}
			
		return paq;
	}

	/**
	 * 返回经纪人详情页的背景图
	 */
	@Override
	public void saveBackgroundImage(Broker broker) {
		//先判断 broker.getBackgroundImage是否有值
		if(!StringUtils.hasLength(broker.getBackgroundImage())){
			//获取1~10的随机数
			int randomNumber =  (int)(Math.random()*10+1);
			if(randomNumber > 5){
				broker.setBackgroundImage( getImageName(randomNumber - 5 ));
			}else{
				broker.setBackgroundImage( getImageName(randomNumber));
			}
			this.hibernateTemplate.update(broker);
		}
	}
	private String getImageName(int digital){
		int serialNumber = 1;
		switch(digital){
			case 1:
				serialNumber = 1;
				break;
			case 2:
				serialNumber = 2;
				break;
			case 3:
				serialNumber = 3;
				break;
			case 4:
				serialNumber = 4;
				break;
			default:
				serialNumber = 5;
				break;
		}	
		
		//一共五张图片：jjr_banner_001.jpg,jjr_banner_002.jpg，jjr_banner_003.jpg，jjr_banner_004.jpg，jjr_banner_005.jpg
		String[] backgroundImages= {"jjr_banner_001.jpg","jjr_banner_002.jpg","jjr_banner_003.jpg","jjr_banner_004.jpg","jjr_banner_005.jpg"};
		
		return backgroundImages[serialNumber-1];
	}
}
