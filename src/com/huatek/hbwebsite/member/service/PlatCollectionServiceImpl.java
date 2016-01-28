package com.huatek.hbwebsite.member.service;

import cn.hshb.web.partner.baidu.common.StringUtil;

import com.huatek.base.service.BaseServiceImpl;
import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.tag.QueryPageBean;
import com.huatek.hbwebsite.common.entity.Community;
import com.huatek.hbwebsite.house.entity.HouseNew;
import com.huatek.hbwebsite.house.entity.HouseRent;
import com.huatek.hbwebsite.house.entity.HouseSecond;
import com.huatek.hbwebsite.member.entity.PlatCollection;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;
import com.huatek.hbwebsite.member.service.PlatCollectionService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlatCollectionServiceImpl extends BaseServiceImpl implements PlatCollectionService {
	private static final String GET_COMMUNITY_COLLECTS = "from PlatCollection p where p.objectId = ? and p.platMemberInfo.id = ? and p.collectType = ? and p.deleteFlag = 0";

	public CutPageBean getCutPageBean(List<CommonBean> parameterList, CutPageBean pageBean, PlatMemberInfo platMemberInfo) {
		String totalRowHsql = "select count(t) from PlatCollection t where t.platMemberInfo.id = ?";
		String resultHsql = "from PlatCollection t where t.platMemberInfo.id = ?";
		return QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowHsql, resultHsql,
				"order by t.createDate desc", new Object[] { platMemberInfo.getId() }, pageBean, parameterList);
	}

	/**
	 * 收藏房源或小区
	 * @param searchNo	小区或房源ID
	 * @param collType	收藏目标类别，1：二手房，2：租赁，其他：小区
	 * @param priceCc		房源价格，对于小区则为空
	 * @param platMemberInfo	当前会员对象
	 * @param brokerId	经纪人ID，对于小区则为空		
	 * @return
	 */
	public int savePlatCollection(int collType, String houseId, String brokerId, Float priceCc, PlatMemberInfo platMemberInfo) {
		try{
		if (this.HasExistedCollection(platMemberInfo.getId(), houseId, collType, brokerId) == null) {
			PlatCollection platCollection = new PlatCollection();
			platCollection.setPlatMemberInfo(platMemberInfo);
			platCollection.setCollectType(collType);
			platCollection.setObjectId(houseId);
			platCollection.setCreateDate(new Date());
			platCollection.setDeleteFlag(0);

			if(priceCc!=null) platCollection.setPriceCc(priceCc);
			if(brokerId!=null) platCollection.setBrokerId(brokerId);
			
			this.hibernateTemplate.saveOrUpdate(platCollection);
			return 1;
		} else {
			return 0;
		}
		}catch(Exception ex){
			return 2;
		}
	}

	/**
	 * 收藏小区，已作废
	 * @deprecated
	 * @param searchNo	小对ID
	 * @param collType	收藏类型
	 * @param platMemberInfo	当前会员对象
	 * @return
	 */
	public int addCollectCommunity(int collType, String searchNo, PlatMemberInfo platMemberInfo) {
		List platCollections = getHibernateTemplate().find(
						"from PlatCollection p where p.objectId = ? and p.platMemberInfo.id = ? and p.collectType = ? and p.deleteFlag = 0",
						new Object[] { searchNo, platMemberInfo.getId(), Integer.valueOf(collType) });
		if (platCollections.size() > 0) {
			return 0;
		} else if (this.HasExistedCollection(platMemberInfo.getId(), searchNo, collType) == null) {
			PlatCollection platCollection1 = new PlatCollection();
			platCollection1.setPlatMemberInfo(platMemberInfo);
			platCollection1.setCollectType(collType);
			platCollection1.setObjectId(searchNo);
			platCollection1.setCreateDate(new Date());
			this.hibernateTemplate.save(platCollection1);
			return 1;
		} else {
			return 2;
		}
	}

	public List<PlatCollection> getPlatCollections(String collectionId) {
		return this.getHibernateTemplate().find("from PlatCollection p where p.objectId = ?", collectionId);
	}

	public Long HasExistedCollection(Long memberId, String productId, int type, String brokerId) {
		String resultSql = "from PlatCollection t where t.platMemberInfo.id=? and t.objectId=? and t.collectType=? and t.deleteFlag = 0 and t.brokerId = ?";
		Object[] obArr = new Object[] { memberId, productId, Integer.valueOf(type), brokerId };
		List collList = this.hibernateTemplate.find(resultSql, obArr);
		return collList != null && collList.size() > 0 ? ((PlatCollection) collList.get(0)).getId() : null;
	}

	/**
	 * 检查房源或小区是否已收藏，返回收藏ID
	 */
	public Long HasExistedCollection(Long memberId, String productId, int type) {
		String resultSql = "from PlatCollection t where t.platMemberInfo.id=? and t.objectId=? and t.collectType=? and t.deleteFlag = 0";
		Object[] obArr = new Object[] { memberId, productId, Integer.valueOf(type) };
		List collList = this.hibernateTemplate.find(resultSql, obArr);
		return collList != null && collList.size() > 0 ? ((PlatCollection) collList.get(0)).getId() : null;
	}
	
	/**
	 * 根据会员ID查询会员收藏的房源
	 */
	public List<PlatCollection> getPlatCollectionListByMemberId(Long id) {
		String resultSql = "from PlatCollection t where t.platMemberInfo.id=? and t.deleteFlag=?";
		Object[] obArr = new Object[] { id, Integer.valueOf(0) };
		@SuppressWarnings("unchecked")
		List<PlatCollection> platCollectionList = (List<PlatCollection>)this.hibernateTemplate.find(resultSql, obArr);
		return platCollectionList;
	}

	public CutPageBean getCollectHouse(CutPageBean pageBean, Long memberId) {
		pageBean.setPageSize(5);
		String totalRowsHsql = "select count(t) from PlatCollection t where t.platMemberInfo.id = " + memberId
				+ "and t.deleteFlag = 0 order by t.createDate desc";
		String resultSql = "from PlatCollection t where t.platMemberInfo.id = " + memberId
				+ "and t.deleteFlag = 0 order by t.createDate desc";
		CutPageBean cutPageBean = QueryPageBean.getQueryPageBean(this.hibernateTemplate, totalRowsHsql, resultSql, "",
				pageBean, new ArrayList());
		if (cutPageBean.getDataList() != null && cutPageBean.getDataList().size() > 0) {
			HouseSecond houseSecond = null;
			HouseRent houseRent = null;
			Community community = null;
			HouseNew houseNew = null;
			
			for (int i = 0; i < cutPageBean.getDataList().size(); ++i) {
				PlatCollection collect = (PlatCollection) cutPageBean.getDataList().get(i);
				if (collect.getCollectType() == 0) {
					houseSecond = this.getHouseSecond(collect.getObjectId());
					if (houseSecond != null) {
						collect.setHouseSecond(houseSecond);
					}
				} else if (collect.getCollectType() == 1) {
					houseRent = this.getHouseRent(collect.getObjectId());
					if (houseRent != null) {
						collect.setHouseRent(houseRent);
					}
				} else if (collect.getCollectType() == 2) {
					community = this.getCommunity(collect.getObjectId());
					if (community != null) {
						collect.setCommunity(community);
					}
				} else if (collect.getCollectType() == 4){
					//新房
					if(StringUtil.isNotEmpty(collect.getObjectId()))
						houseNew = this.getHouseNew(collect.getObjectId());
					if(houseNew != null)
						collect.setHouseNew(houseNew);
				}
			}
		}

		return cutPageBean;
	}

	private HouseSecond getHouseSecond(String objectId) {
		String sql = "from HouseSecond h where h.shelvingID = ? and h.deleteFlag=0";
		Object[] obArr = new Object[] { objectId };
		HouseSecond houseSecond = null;
		List houseSecondList = this.hibernateTemplate.find(sql, obArr);
		if (houseSecondList != null && houseSecondList.size() >= 1) {
			houseSecond = (HouseSecond) houseSecondList.get(0);
		}

		return houseSecond;
	}

	private HouseRent getHouseRent(String objectId) {
		String sql = "from HouseRent h where h.shelvingID = ? and h.deleteFlag=0";
		Object[] obArr = new Object[] { objectId };
		HouseRent houseRent = null;
		@SuppressWarnings("rawtypes")
		List houseRentList = hibernateTemplate.find(sql, obArr);
		if (houseRentList != null && houseRentList.size() >= 1) {
			houseRent = (HouseRent) houseRentList.get(0);
		}

		return houseRent;
	}

	private HouseNew getHouseNew(String objectId){
		String sql = "from HouseNew h where h.id =? and deleteFlag = 0";
		long id = Long.parseLong((objectId));
		Object[] obArr = new Object[]{id};
		HouseNew houseNew = null;
		@SuppressWarnings("rawtypes")
		List houseNewList = hibernateTemplate.find(sql, obArr);
		if(houseNewList != null && houseNewList.size()>= 1){
			houseNew = (HouseNew) houseNewList.get(0);
		}
		return houseNew;
	}
	
	private Community getCommunity(String objectId) {
		String sql = " from Community c where c.erpId = ? and c.deleteFlag = 0";
		Object[] obArr = new Object[] { objectId };
		Community community = null;
		@SuppressWarnings("rawtypes")
		List communityList = hibernateTemplate.find(sql, obArr);
		if (communityList != null && communityList.size() >= 1) {
			community = (Community) communityList.get(0);
		}

		return community;
	}

	public Long getCountByHouseType(Long memberId, int type) {
		String totalRowsHsql = "select count(t) from PlatCollection t where t.platMemberInfo.id = " + memberId
				+ "and t.collectType = " + type + "and t.deleteFlag = 0";
		@SuppressWarnings("unchecked")
		List<Long> resultList = hibernateTemplate.find(totalRowsHsql);
		Long count = resultList.get(0);
		return count;
	}

	public List<PlatCollection> getPlatCollectionListByMemberIdAndType(Long id, int type) {
		String resultSql = "from PlatCollection t where t.platMemberInfo.id=? and t.deleteFlag=0 and t.collectType=?";
		Object[] obArr = new Object[] { id, Integer.valueOf(type) };
		List<PlatCollection> platCollectionList = (List<PlatCollection>)hibernateTemplate.find(resultSql, obArr);
		return platCollectionList;
	}
}
