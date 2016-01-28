package com.huatek.hbwebsite.member.service;

import com.huatek.base.service.BaseService;
import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.hbwebsite.member.entity.PlatCollection;
import com.huatek.hbwebsite.member.entity.PlatMemberInfo;

import java.util.List;

public interface PlatCollectionService extends BaseService {
	CutPageBean getCutPageBean(List<CommonBean> parameterList, CutPageBean pageBean, PlatMemberInfo platMemberInfo);

	/**
	 * 收藏房源或小区
	 * @param searchNo	小区或房源ID
	 * @param collType	收藏目标类别，1：二手房，2：租赁，其他：小区
	 * @param priceCc		房源价格，对于小区则为空
	 * @param platMemberInfo	当前会员对象
	 * @param brokerId	经纪人ID，对于小区则为空		
	 * @return
	 */
	int savePlatCollection(int collType, String houseId, String brokerId, Float priceCc, PlatMemberInfo platMemberInfo);

	/**
	 * 收藏小区，已作废
	 * @deprecated
	 * @param searchNo	小对ID
	 * @param collType	收藏类型
	 * @param platMemberInfo	当前会员对象
	 * @return
	 */
	int addCollectCommunity(int collType, String houseId, PlatMemberInfo platMemberInfo);

	Long HasExistedCollection(Long memberId, String productId, int type, String brokerId);

	Long HasExistedCollection(Long memberId, String productId, int type);

	/**
	 * 根据会员ID查询会员收藏的房源
	 * @param memberId 会员ID
	 * @return
	 */
	List<PlatCollection> getPlatCollectionListByMemberId(Long memberId);

	List<PlatCollection> getPlatCollectionListByMemberIdAndType(Long memberId, int type);

	CutPageBean getCollectHouse(CutPageBean pageBean, Long memberId);

	Long getCountByHouseType(Long memberId, int type);

	List<PlatCollection> getPlatCollections(String collectionId);
}
