/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.List;

import cn.hshb.web.biz.mybatis.model.HouseReduceNotice;


/**
 * @author Administrator
 *
 */
public interface HouseReduceNoticeService {
	
	/**
	 * 根据会员ID和房源ID查询房源降价订阅记录
	 * @param memberId 	会员ID 
	 * @param houseId	房源ID 
	 * @return
	 */
	public List<HouseReduceNotice> getHouseNoticeByMemberAndHouseId(Long memberId, String houseId); 
	
	/**
	 * 根据会员查询订阅的降价通知房源并处理降价信息
	 * @param memberId	会员ID
	 * @param pageSize	分页大小
	 * @param pageNum	页码
	 * @return
	 */
	public List<HouseReduceNotice> getHouseReduceNoticeByMember(Long memberId, Integer pageSize, Integer pageNum);
	
	/**
	 * 查询需要发送降价通知的总数
	 * @param memberId	会员ID
	 * @return
	 */
	public int getNoticeCount();

	/**
	 * 把指定的记录置上删除标志
	 * @param Id		记录ID
	 * @param memberId	会员ID
	 * @return
	 */
	public boolean deletePriceNotice(String Id, Long memberId);	
	
	/**
	 * 校验指定的邮件地址是否已在订阅表中 且邮件地址标志为1
	 * @param emailAddress 邮件地址
	 * @return
	 */
	public boolean validEmail(String emailAddress);
	
	/**
	 * 激活邮件地址
	 * @param erpId 订阅记录ID
	 * @return
	 */
	public boolean activeEmail(String erpId);
	
	/**
	 * 激活电话号码
	 * @param erpId 订阅记录ID
	 * @return
	 */
	public boolean activePhone(String erpId);
	
	/**
	 * 根据会员ID、房源类型和房源上架ID检查订阅记录是否存在
	 * @param memberId		会员ID 
	 * @param houseType		房源类型
	 * @param houseId		房源上架ID
	 * @return
	 */
	public boolean isSubscriptionExist(Long memberId, Integer houseType, String houseId);

}
