/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.hshb.web.biz.mybatis.dao.HouseCommunityMapper;
import cn.hshb.web.biz.mybatis.dao.HouseRentHouseMapper;
import cn.hshb.web.biz.mybatis.dao.HouseSecondHandHouseMapper;
import cn.hshb.web.biz.mybatis.dao.MemberBrowseHistoryMapper;
import cn.hshb.web.biz.mybatis.model.HouseCommunity;
import cn.hshb.web.biz.mybatis.model.HouseCommunityKey;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseKey;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouseKey;
import cn.hshb.web.biz.mybatis.model.MemberBrowseHistory;
import cn.hshb.web.biz.mybatis.model.MemberBrowseHistoryExample;
import cn.hshb.web.biz.service.MemberBrowseHistoryService;

/**
 * @author Administrator
 *
 */
@Service
public class MemberBrowseHistoryServiceImpl implements MemberBrowseHistoryService {
	
	private static final Integer PAGE_SIZE=20;	//默认分页大小
	
	@Autowired
	private MemberBrowseHistoryMapper memberBrowseHistoryMapper;
	
	@Autowired
	private HouseSecondHandHouseMapper houseSecondHandHouseMapper;
	
	@Autowired
	private HouseRentHouseMapper houseRentHouseMapper;
	
	@Autowired
	private HouseCommunityMapper houseCommunityMapper;

	
	@Override
	public List<MemberBrowseHistory> findMemberBrowseHistoryByMember(Long memberId, Integer houseType, String houseId) {
		MemberBrowseHistoryExample example = new MemberBrowseHistoryExample();
		example.createCriteria()
			.andMemberIdEqualTo(memberId.intValue())
			.andObjectTypeEqualTo(houseType)
			.andObjectIdEqualTo(houseId)
			.andDeleteflagEqualTo(0);
		return memberBrowseHistoryMapper.selectByExample(example);
	}

	@Override
	public List<MemberBrowseHistory> getBrowseHistory(Long memberId, Integer houseType, Integer pageSize, Integer pageNum) {
		
		int psize = pageSize==null? PAGE_SIZE: pageSize;
		int page = pageNum==null? 1: pageNum;
		
		MemberBrowseHistoryExample example = new MemberBrowseHistoryExample();
		example.createCriteria()
			.andMemberIdEqualTo(memberId.intValue())
			.andObjectTypeEqualTo(houseType)
			.andDeleteflagEqualTo(0);
		example.setOrderByClause("modifiedTime desc");
		
		PageHelper.startPage(page, psize);
		List<MemberBrowseHistory> list = memberBrowseHistoryMapper.selectByExample(example);

		if(list.size()>0){
			for(MemberBrowseHistory mbh: list){
				switch(mbh.getObjectType()){
					case 1:
						HouseSecondHandHouseKey key = new HouseSecondHandHouseKey();
						key.setShelvingId(mbh.getObjectId());
						HouseSecondHandHouse house = houseSecondHandHouseMapper.selectByPrimaryKey(key);
						mbh.setSecondHouse(house);
						break;
					case 2:
						HouseRentHouseKey key1 = new HouseRentHouseKey();
						key1.setShelvingId(mbh.getObjectId());
						HouseRentHouse house1 = houseRentHouseMapper.selectByPrimaryKey(key1);
						mbh.setRentHouse(house1);
						break;
					case 3:
						HouseCommunityKey key2 = new HouseCommunityKey();
						key2.setErpId(mbh.getObjectId());
						HouseCommunity community = houseCommunityMapper.selectByPrimaryKey(key2);
						mbh.setCommunity(community);
						break;
				}
			}
		}
		return list;
	}


	@Override
	public List<Map> getCountByObjectType(Long memberId) {
		MemberBrowseHistoryExample example = new MemberBrowseHistoryExample();
		example.createCriteria()
			.andMemberIdEqualTo(memberId.intValue())
			.andDeleteflagEqualTo(0);
		
		String sql = "SELECT object_type, count(*) cnt from member_browse_history where member_id="+memberId+" and delete_flag=0 group by object_type";
		List<Map> list = memberBrowseHistoryMapper.selectBySQL(sql);
		return list;
	}
	
	@Override
	public void update(MemberBrowseHistory mbh){
		memberBrowseHistoryMapper.updateByPrimaryKey(mbh);
	}

	@Override
	public void save(MemberBrowseHistory mbh){
		memberBrowseHistoryMapper.insert(mbh);
	}
	
	/**
	 * 保存会员浏览记录
	 * @param memberId		会员ID
	 * @param houseType		房源类型
	 * @param houseId		房源ID
	 */
	public void saveUserBrowseHistory(Long memberId, Integer houseType, String houseId){
		if (memberId != null) {
			List<MemberBrowseHistory> browseHisList = this.findMemberBrowseHistoryByMember(memberId, houseType, houseId);
			if (browseHisList.size() > 0) {
				browseHisList.get(0).setModifiedTime(new Date());
				update(browseHisList.get(0));
			} else {
				MemberBrowseHistory history = new MemberBrowseHistory();
				history.setMemberId(memberId.intValue());
				history.setObjectId(houseId);
				history.setObjectType(0);
				history.setCreatedTime(new Date());
				history.setModifiedTime(new Date());
				save(history);
			}
		}
	}
}
