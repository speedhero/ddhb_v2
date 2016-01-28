/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.hshb.web.biz.mybatis.dao.HouseReduceNoticeMapper;
import cn.hshb.web.biz.mybatis.dao.HouseRentHouseMapper;
import cn.hshb.web.biz.mybatis.dao.HouseSecondHandHouseMapper;
import cn.hshb.web.biz.mybatis.dao.PirceChangeHistoryMapper;
import cn.hshb.web.biz.mybatis.model.HouseReduceNotice;
import cn.hshb.web.biz.mybatis.model.HouseReduceNoticeExample;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseExample;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouseExample;
import cn.hshb.web.biz.mybatis.model.PirceChangeHistory;
import cn.hshb.web.biz.mybatis.model.PirceChangeHistoryExample;
import cn.hshb.web.biz.service.HouseReduceNoticeService;
import cn.hshb.web.house.enums.EnumHouseType;

/**
 * @author Administrator
 *
 */
@Service
public class HouseReduceNoticeServiceImpl implements HouseReduceNoticeService {

	private static final Integer EMAIL_ACTIVED = 1;	//邮件地址已激活
	private static final Integer PHONE_ACTIVED = 1;	//电话号码已激活
	
	@Autowired
	private HouseReduceNoticeMapper houseReduceNoticeMapper;
	
	@Autowired
	private HouseSecondHandHouseMapper houseSecondHandHouseMapper;
	
	@Autowired
	private HouseRentHouseMapper houseRentHouseMapper;

	@Autowired
	private PirceChangeHistoryMapper pirceChangeHistoryMapper;
	
	@Override
	public List<HouseReduceNotice> getHouseNoticeByMemberAndHouseId(Long memberId, String houseId) {
		HouseReduceNoticeExample example = new HouseReduceNoticeExample();
		example.createCriteria()
			.andMemberIdEqualTo(memberId.intValue())
			.andHouseIdEqualTo(houseId)
			.andDeleteflagEqualTo(0);
		
		List<HouseReduceNotice> list = houseReduceNoticeMapper.selectByExample(example);
		return list;
	}
	
	
	/**
	 * 根据会员查询订阅的降价通知房源并处理降价信息
	 * @param memberId	会员ID
	 * @param pageSize	分页大小
	 * @param pageNum	页码
	 * @return
	 */
	@Override
	public List<HouseReduceNotice> getHouseReduceNoticeByMember(Long memberId, Integer pageSize, Integer pageNum) {
		HouseReduceNoticeExample example = new HouseReduceNoticeExample();
		example.createCriteria()
			.andMemberIdEqualTo(memberId.intValue())
			.andDeleteflagEqualTo(0);
		
		PageHelper.startPage(pageNum, pageSize);
		
		List<HouseReduceNotice> list = houseReduceNoticeMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			for(HouseReduceNotice hrn: list){
				if(hrn.getHouseType()==EnumHouseType.SALE.value()){
					HouseSecondHandHouse house = null;

					HouseSecondHandHouseExample hshhExam = new HouseSecondHandHouseExample();
					hshhExam.createCriteria().andHouseIdEqualTo(hrn.getHouseId()).andDeleteflagEqualTo(0);
					List<HouseSecondHandHouse> hlist = houseSecondHandHouseMapper.selectByExample(hshhExam);
					if(hlist.size()>0)  house = hlist.get(0);
					if(house!=null && house.getErpId() != null ){
						PirceChangeHistoryExample pcheExam = new PirceChangeHistoryExample();
						pcheExam.createCriteria()
							.andHouseIdEqualTo(house.getErpId())
							.andHouseTypeEqualTo(EnumHouseType.SALE.value());
						List<PirceChangeHistory> lst = pirceChangeHistoryMapper.selectByExample(pcheExam);
						house.setPriceChangeHistory(lst);
						hrn.setHouseSecond(house);
						if(house.getPrice() > hrn.getCurrentPrice()){
							hrn.setPriceFlag(-1);
						}else if(house.getPrice() == hrn.getCurrentPrice()){
							hrn.setPriceFlag(-1);
						}else{
							hrn.setPriceFlag(1);
						}
					}
				}else if(hrn.getHouseType()==EnumHouseType.RENT.value()){
					HouseRentHouse house = null;

					HouseRentHouseExample hrhExam = new HouseRentHouseExample();
					hrhExam.createCriteria().andHouseIdEqualTo(hrn.getHouseId()).andDeleteflagEqualTo(0);
					List<HouseRentHouse> hlist = houseRentHouseMapper.selectByExample(hrhExam);
					if(hlist.size()>0)  house = hlist.get(0);
					if(house!=null && house.getErpId() != null ){
						PirceChangeHistoryExample pcheExam = new PirceChangeHistoryExample();
						pcheExam.createCriteria()
							.andHouseIdEqualTo(house.getErpId())
							.andHouseTypeEqualTo(EnumHouseType.RENT.value());
						List<PirceChangeHistory> lst = pirceChangeHistoryMapper.selectByExample(pcheExam);
						house.setPriceChangeHistory(lst);
						hrn.setHouseRent(house);
						if(house.getRentPrice() > hrn.getCurrentPrice()){
							hrn.setPriceFlag(-1);
						}else if(house.getRentPrice() == hrn.getCurrentPrice()){
							hrn.setPriceFlag(-1);
						}else{
							hrn.setPriceFlag(1);
						}
					}
				}
			}
		}
		
		return list;
	}

	
	/**
	 * 查询需要发送降价通知的总数
	 * @param memberId	会员ID
	 * @return
	 */
	public int getNoticeCount() {
		Integer count = houseReduceNoticeMapper.selectNeedSendNoticeCount();
		return count;
	}

	/**
	 * 把指定的记录置上删除标志
	 * @param Id		记录ID
	 * @param memberId	会员ID
	 * @return
	 */
	public boolean deletePriceNotice(String Id, Long memberId) {
		HouseReduceNoticeExample example = new HouseReduceNoticeExample();
		example.createCriteria()
			.andErpIdEqualTo(Id)
			.andMemberIdEqualTo(memberId.intValue());
		HouseReduceNotice record = new HouseReduceNotice();
		record.setDeleteflag(1);

		int count = houseReduceNoticeMapper.updateByExampleSelective(record, example);
		return count > 0;
	}

	/**
	 * 校验指定的邮件地址是否已在订阅表中 且邮件地址已激活（标志为1）
	 * @param emailAddress 邮件地址
	 * @return
	 */
	public boolean validEmail(String emailAddress) {
		HouseReduceNoticeExample example = new HouseReduceNoticeExample();
		example.createCriteria()
			.andEmailFlagEqualTo(EMAIL_ACTIVED)
			.andNoticeEmailEqualTo(emailAddress)
			.andDeleteflagEqualTo(0);
		
		List<HouseReduceNotice> list = houseReduceNoticeMapper.selectByExample(example);
		return list.size() > 0 ;
	}

	/**
	 * 校验指定的电话号码是否已在订阅表中 且电话已激活（标志为1）
	 * @param phoneNum
	 * @return
	 */
	public boolean validPhone(String phoneNum) {
		HouseReduceNoticeExample example = new HouseReduceNoticeExample();
		example.createCriteria()
			.andPhoneFlagEqualTo(PHONE_ACTIVED)
			.andNoticePhoneEqualTo(phoneNum)
			.andDeleteflagEqualTo(0);

		List<HouseReduceNotice> list = houseReduceNoticeMapper.selectByExample(example);
		return list.size() > 0;
	}

	/**
	 * 激活邮件地址
	 * @param erpId 订阅记录ID
	 * @return
	 */
	public boolean activeEmail(String erpId) {
		HouseReduceNoticeExample example = new HouseReduceNoticeExample();
		example.createCriteria()
			.andErpIdEqualTo(erpId);

		HouseReduceNotice record = new HouseReduceNotice();
		record.setEmailFlag(EMAIL_ACTIVED);
		
		int count = houseReduceNoticeMapper.updateByExampleSelective(record, example);
		return count > 0;
	}
	
	/**
	 * 激活电话号码
	 * @param erpId 订阅记录ID
	 * @return
	 */
	public boolean activePhone(String erpId) {
		HouseReduceNoticeExample example = new HouseReduceNoticeExample();
		example.createCriteria()
			.andErpIdEqualTo(erpId);

		HouseReduceNotice record = new HouseReduceNotice();
		record.setPhoneFlag(PHONE_ACTIVED);
		
		int count = houseReduceNoticeMapper.updateByExampleSelective(record, example);
		return count > 0;
	}
	
	/**
	 * 根据会员ID、房源类型和房源上架ID检查订阅记录是否存在
	 * @param memberId		会员ID 
	 * @param houseType		房源类型
	 * @param houseId		房源上架ID
	 * @return
	 */
	public boolean isSubscriptionExist(Long memberId, Integer houseType, String houseId) {
		HouseReduceNoticeExample example = new HouseReduceNoticeExample();
		example.createCriteria()
			.andMemberIdEqualTo(memberId.intValue())
			.andHouseTypeEqualTo(houseType)
			.andHouseIdEqualTo(houseId)
			.andDeleteflagEqualTo(0);
		List<HouseReduceNotice> list = houseReduceNoticeMapper.selectByExample(example);
		return list.size()>0;
	}

}
