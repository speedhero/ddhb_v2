/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.huatek.hbwebsite.common.entity.BroderAnsered;
import com.huatek.hbwebsite.common.entity.CommunityExpert;

import cn.hshb.web.biz.mybatis.dao.BBrokerMapper;
import cn.hshb.web.biz.mybatis.dao.BBrokeransweredMapper;
import cn.hshb.web.biz.mybatis.dao.CommonHousequestionStrategyMapper;
import cn.hshb.web.biz.mybatis.dao.CommonQuestionStrategySubtypeMapper;
import cn.hshb.web.biz.mybatis.dao.CommonQuestionStrategyTypeMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCbdExpertMapper;
import cn.hshb.web.biz.mybatis.dao.HouseCommunityExpertMapper;
import cn.hshb.web.biz.mybatis.dao.HouseRentHouseMapper;
import cn.hshb.web.biz.mybatis.dao.HouseSecondHandHouseMapper;
import cn.hshb.web.biz.mybatis.model.BBroker;
import cn.hshb.web.biz.mybatis.model.BBrokerKey;
import cn.hshb.web.biz.mybatis.model.BBrokeranswered;
import cn.hshb.web.biz.mybatis.model.BBrokeransweredExample;
import cn.hshb.web.biz.mybatis.model.CommonHousequestionStrategy;
import cn.hshb.web.biz.mybatis.model.CommonHousequestionStrategyExample;
import cn.hshb.web.biz.mybatis.model.CommonQuestionStrategySubtype;
import cn.hshb.web.biz.mybatis.model.CommonQuestionStrategySubtypeExample;
import cn.hshb.web.biz.mybatis.model.CommonQuestionStrategyType;
import cn.hshb.web.biz.mybatis.model.CommonQuestionStrategyTypeExample;
import cn.hshb.web.biz.mybatis.model.HouseCbdExpert;
import cn.hshb.web.biz.mybatis.model.HouseCbdExpertExample;
import cn.hshb.web.biz.mybatis.model.HouseCommunityExpert;
import cn.hshb.web.biz.mybatis.model.HouseCommunityExpertExample;
import cn.hshb.web.biz.mybatis.model.HouseRentHouse;
import cn.hshb.web.biz.mybatis.model.HouseRentHouseExample;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouse;
import cn.hshb.web.biz.mybatis.model.HouseSecondHandHouseExample;
import cn.hshb.web.biz.mybatis.model.PageAccessQuantity;
import cn.hshb.web.biz.service.BBrokerService;
import cn.hshb.web.biz.service.HouseSecondhandHouseService;
import cn.hshb.web.partner.baidu.common.StringUtil;

/**
 * @author ShengYoufu
 *
 */
@Service
public class BBrokerServiceImpl implements BBrokerService {
	private static final Log log = LogFactory.getLog(BBrokerServiceImpl.class);
	
	private static final Integer PAGE_SIZE = 10;	//默认每页记录数
	
	@Autowired
	private BBrokerMapper bBrokerMapper;
	
	@Autowired
	private HouseCommunityExpertMapper houseCommunityExpertMapper;
	
	@Autowired
	private HouseCbdExpertMapper houseCbdExpertMapper;
	
	@Autowired
	private HouseSecondHandHouseMapper houseSecondHandHouseMapper;
	
	@Autowired
	private HouseRentHouseMapper houseRentHouseMapper;
	
	@Autowired
	private BBrokeransweredMapper bBrokeransweredMapper;
	
	@Autowired
	private CommonQuestionStrategyTypeMapper commonQuestionStrategyTypeMapper;
	
	@Autowired
	private CommonHousequestionStrategyMapper commonHousequestionStrategyMapper;
	
	@Autowired
	private CommonQuestionStrategySubtypeMapper commonQuestionStrategySubtypeMapper;
	
	/**
	 * 根据经纪人ID查询经纪人
	 * @param brokerId	经纪人ID
	 */
	@Override
	public BBroker getBroker(String brokerId){
//		BBrokerKey key = new BBrokerKey();
//		key.setErpId(brokerId);
//		return bBrokerMapper.selectByPrimaryKey(key);
		return bBrokerMapper.selectAssociateByBrokerId(brokerId);
	}
	
	/**
	 * 根据经纪人查询经纪人熟悉的小区
	 * @param brokerId	经纪人ID
	 * @return
	 */
	@Override
	public List<HouseCommunityExpert> getCommunityExpertByBrokerId(String brokerId) {
//		HouseCommunityExpertExample example = new HouseCommunityExpertExample();
//		example.createCriteria()
//			.andBrokerIdEqualTo(brokerId)
//			.andDeleteflagEqualTo(0);
		return houseCommunityExpertMapper.selectAssociateByBrokerId(brokerId);
	}

	/**
	 * 根据经纪人ID查询经纪人熟悉的商圈
	 * @param brokerId	经纪人ID
	 * @return
	 */
	public List<HouseCbdExpert> getCBDExpertByBrokerId(String brokerId) {
//		HouseCbdExpertExample example = new HouseCbdExpertExample();
//		example.createCriteria()
//			.andBrokerIdEqualTo(brokerId)
//			.andDeleteflagEqualTo(0);
		if(StringUtil.isNotEmpty(brokerId))
			return houseCbdExpertMapper.selectCbdExpertByBorkerErpId(brokerId);
		return null;
	}
	
	/**
	 * 查询经纪人持有的二手房源量
	 * @param brokerId	经纪人ID
	 * @param pageSize	每页记录数
	 * @param pageNum	页码
	 * @return
	 */
	public List<HouseSecondHandHouse> findHouseSecondByBrokerId(String brokerId, Integer pageSize, Integer pageNum) {
		Integer pgSize = pageSize!=null && pageSize>0 ? pageSize : PAGE_SIZE;
		Integer pgNum = pageNum!=null && pageNum>0 ? pageNum : 1;
		
		HouseSecondHandHouseExample example = new HouseSecondHandHouseExample();
		example.createCriteria()
			.andPublisherIdEqualTo(brokerId)
			.andDeleteflagEqualTo(0);
		example.setOrderByClause("sortIndex asc");
		
		PageHelper.startPage(pgNum, pgSize);
		List<HouseSecondHandHouse> list = houseSecondHandHouseMapper.selectByExample(example);
		if (list != null) {
			for (HouseSecondHandHouse house : list) {
				if(house.getTagIds()==null) 
					house.setTagIds(new ArrayList<String>());
				String tagsStr = house.getTags();
				String[] tagArr = tagsStr.split(",");
				for(String s: tagArr){
					house.getTagIds().add(s);
				}
			}
		}
		
		return list;
	}

	/**
	 * 根据经纪人ID查询经纪人持有的出租房源
	 * @param brokerId	经纪人ID
	 * @param pageSize	每页记录数
	 * @param pageNum	页码
	 * @return
	 */
	public List<HouseRentHouse> findHouseRentByBrokerId(String brokerId, Integer pageSize, Integer pageNum) {
		/*
		 * TODO: 原逻辑
		String totalRowsHsql = "select count(t) from HouseRent t where t.deleteFlag = 0 and t.broker.erpId = \'"
				+ brokerId
				+ "\' or t.shelvingID in ( select distinct ha.houseId from HouseAppraise ha where ha.houseType = 2 and ha.broker.erpId = \'"
				+ brokerId + "\')";
		String resultSql = "from HouseRent t where t.deleteFlag = 0 and t.broker.erpId = \'"
				+ brokerId
				+ "\' or t.shelvingID in ( select distinct ha.houseId from HouseAppraise ha where ha.houseType = 2 and ha.broker.erpId = \'"
				+ brokerId + "\')";
		String sortSql = "order by t.sortIndex asc";
		 */
		
		Integer pgSize = pageSize!=null && pageSize>0 ? pageSize : PAGE_SIZE;
		Integer pgNum = pageNum!=null && pageNum>0 ? pageNum : 1;
		
		HouseRentHouseExample example = new HouseRentHouseExample();
		example.createCriteria()
			.andPublisherIdEqualTo(brokerId)
			.andDeleteflagEqualTo(0);
		example.setOrderByClause("sortIndex asc");
		PageHelper.startPage(pgNum, pgSize);
		List<HouseRentHouse> hList = houseRentHouseMapper.selectByExample(example);
		if (hList != null) {
			for (HouseRentHouse h : hList) {
				String[] furArr = h.getFurniture().split(",");
				List<String> furIdList = new ArrayList<String>();
				for (int i = 1; i < furArr.length; ++i) {
					furIdList.add(furArr[i]);
				}
				h.setFurnitureIds(furIdList);
			}
		}
		return hList;
	}

	
	/**
	 * 根据经纪人ID查询经纪人回答的问题
	 * @param brokerId	经纪人ID
	 * @param pageSize	每页记录数
	 * @param pageNum	页码
	 * @return
	 */
	public List<BBrokeranswered> getBrokerAnswered(String brokerId, Integer pageSize, Integer pageNum) {
		Integer pgSize = pageSize!=null && pageSize>0 ? pageSize : PAGE_SIZE;
		Integer pgNum = pageNum!=null && pageNum>0 ? pageNum : 1;
		
		BBrokeransweredExample example = new BBrokeransweredExample();
		example.createCriteria()
			.andBrokerIdEqualTo(brokerId)
			.andDeleteflagEqualTo(0);
		example.setOrderByClause(" lastModified asc ");
		PageHelper.startPage(pgNum, pgSize);
		return bBrokeransweredMapper.selectByExample(example);
	}

	
	/**
	 * 根据经纪人ID查询已被接受的经纪人回答的问题数
	 * @param brokerId	经纪人ID
	 * @return
	 */
	@Override
	public Integer getAcceptedAnswereCountByBrokerId(String brokerId) {
		BBrokeransweredExample example = new BBrokeransweredExample();
		example.createCriteria()
			.andBrokerIdEqualTo(brokerId)
			.andIsacceptedEqualTo(1)
			.andDeleteflagEqualTo(0);
		return bBrokeransweredMapper.countByExample(example);
	}

	/**
	 * 查询问题分类
	 * @return
	 */
	public List<CommonQuestionStrategyType> getQuestionStrategyType() {
		CommonQuestionStrategyTypeExample example = new CommonQuestionStrategyTypeExample();
		example.createCriteria().andDeleteflagEqualTo(0);
		return commonQuestionStrategyTypeMapper.selectByExample(example);
	}

	/**
	 * 根据问答子类查询问题
	 * @param subTypeId		问答子类
	 * @param displayNum	查询记录数
	 * @return
	 */
	public List<CommonHousequestionStrategy> getQuestionStategyBySubType(String subTypeId, int displayNum) {
		CommonHousequestionStrategyExample example = new CommonHousequestionStrategyExample();
		example.createCriteria()
			.andQuestionTypeIdEqualTo(subTypeId)
			.andIsshowEqualTo(1)
			.andDeleteflagEqualTo(0);
		example.setOrderByClause("createTime desc");
		Integer pgSize = displayNum;
		Integer pgNum = 1;
		PageHelper.startPage(pgNum, pgSize);
		return commonHousequestionStrategyMapper.selectByExample(example);
	}

	/**
	 * 根据问答子类和关键词查询问答
	 * @param subTypeId	问答子类
	 * @param pageSize	每页记录数
	 * @param pageNum	页码
	 * @param keyWord	标题关键词
	 * @return
	 */
	public List<CommonHousequestionStrategy> getAllQuestionStategy(String subTypeId, Integer pageSize, Integer pageNum, String keyWord) {
		Integer pgSize = pageSize!=null && pageSize>0 ? pageSize : PAGE_SIZE;
		Integer pgNum = pageNum!=null && pageNum>0 ? pageNum : 1;
		
		CommonHousequestionStrategyExample example = new CommonHousequestionStrategyExample();
		example.createCriteria()
			.andQuestionTypeIdEqualTo(subTypeId)
			.andIsshowEqualTo(1)
			.andDeleteflagEqualTo(0)
			.andTitleLike(keyWord);
		example.setOrderByClause("createTime desc");
		PageHelper.startPage(pgNum, pgSize);
		return commonHousequestionStrategyMapper.selectByExample(example);
	}

	/**
	 * 根据问答大类查询子类
	 * @param typeId	问答大类
	 * @return
	 */
	public List<CommonQuestionStrategySubtype> getAllQuestionStrategySubType(String typeId) {
		CommonQuestionStrategySubtypeExample example = new CommonQuestionStrategySubtypeExample();
		example.createCriteria()
			.andParentTypeIdEqualTo(typeId)
			.andDeleteflagEqualTo(0);
		return commonQuestionStrategySubtypeMapper.selectByExample(example);
	}

	/**
	 * 按问题类型统计问题数
	 * @param typeId	问题类型
	 * @return
	 */
	public List<Object> getQuestionStrategyCount(String typeId) {
		String sql = "select question_type_id as erp_id, count(erp_id) as cnt "
					+" from common_housequestion_strategy" 
					+ " where deleteflag = 0 and isshow=1 and parent_question_id=\'"+typeId+"\' " 
					+ " group by question_type_id";
		return commonHousequestionStrategyMapper.selectBySQL(sql);
	}

	/**
	 * 根据问题ID查询经纪的回答答案
	 * @param questionId	问题ID
	 * @return
	 */
	public List<BBrokeranswered> getBrokerAnswer(String questionId) {
		BBrokeransweredExample example = new BBrokeransweredExample();
		example.createCriteria().andErpIdEqualTo(questionId);
		return bBrokeransweredMapper.selectByExample(example);
	}

	/**
	 * 根据大类查询问答攻略
	 * @param typeId	大类ID
	 * @param rowCount	查询记录数
	 * @return
	 */
	public List<CommonHousequestionStrategy> getQuestionStategyByTypeId(String typeId, int rowCount) {
		CommonQuestionStrategySubtypeExample example = new CommonQuestionStrategySubtypeExample();
		example.createCriteria()
			.andParentTypeIdEqualTo(typeId)
			.andDeleteflagEqualTo(0);
		List<CommonQuestionStrategySubtype> list = commonQuestionStrategySubtypeMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			List<String> typeIds = new ArrayList<String>();
			for(CommonQuestionStrategySubtype t: list){
				typeIds.add(t.getErpId());
			}
			CommonHousequestionStrategyExample example1 = new CommonHousequestionStrategyExample();
			example1.createCriteria()
				.andIsshowEqualTo(1)
				.andDeleteflagEqualTo(0)
				.andQuestionTypeIdIn(typeIds);
			
			PageHelper.startPage(1, rowCount);
			return commonHousequestionStrategyMapper.selectByExample(example1);
		}
		return new ArrayList<CommonHousequestionStrategy>();
	}

	/**
	 * 统计问题总数
	 * @return
	 */
	public Integer getQuestionCount() {
		CommonHousequestionStrategyExample example = new CommonHousequestionStrategyExample();
		example.createCriteria().andIsshowEqualTo(1).andDeleteflagEqualTo(0);
		return commonHousequestionStrategyMapper.countByExample(example);
	}

	/**
	 * 统计有回答问题的经纪人数量
	 * @return
	 */
	public Integer getAnseredBrokerCount() {
		BBrokeransweredExample example = new BBrokeransweredExample();
		example.createCriteria().andDeleteflagEqualTo(0);
		example.setDistinct(true);
		
		return bBrokeransweredMapper.countByExample(example);
	}

	/**
	 * 查询问答攻略内容
	 * @param searchFileds	查询内容
	 * @param subtype		小类ID
	 * @param pageSize		每页记录数
	 * @param pageNum		当前页码
	 * @return
	 */
	public List<CommonHousequestionStrategy> searchQuestionStategy(String searchFileds, String subtype, Integer pageSize, Integer pageNum) {
		Integer pgSize = pageSize!=null && pageSize>0 ? pageSize : PAGE_SIZE;
		Integer pgNum = pageNum!=null && pageNum>0 ? pageNum : 1;		
		
		CommonHousequestionStrategyExample example = new CommonHousequestionStrategyExample();
		example.or()
			.andIsshowEqualTo(1)
			.andDeleteflagEqualTo(0)
			.andQuestionTypeIdEqualTo(subtype);
		example.or().andTitleLike(searchFileds);
		example.or().andContentLike(searchFileds);
		
		PageHelper.startPage(pgNum, pgSize);
		return commonHousequestionStrategyMapper.selectByExample(example);
	}
	
	
	/**
	 * 给经纪人主页设置随机背景图，设置后就保存到数据库中，不再改变
	 * @param broker
	 * @return
	 */
	public Boolean saveBackgroundImage(BBroker broker) {
		String[] backgroundImages= {"jjr_banner_001.jpg","jjr_banner_002.jpg","jjr_banner_003.jpg","jjr_banner_004.jpg","jjr_banner_005.jpg"};
		//先判断 broker.getBackgroundImage是否有值
		if(!StringUtils.hasLength(broker.getBackgroundImage())){
			//获取1~10的随机数
			int idx =  (int)(Math.random()*5);
			broker.setBackgroundImage( backgroundImages[idx] );
			int ret = bBrokerMapper.updateByPrimaryKeySelective(broker);
			return ret>0;
		}
		return false;
	}
	
	/**
	 * 根据经纪人ID获取经纪人回答的答案
	 * @param erpId
	 * @return
	 */
	@Override
	public List<BBrokeranswered> getBrokerAnswerConditionBrokerErpId(String erpId) {
		BBrokeransweredExample example = new BBrokeransweredExample();
		example.or()
			.andBrokerIdEqualTo(erpId)
			.andIsacceptedEqualTo(1)
			.andDeleteflagEqualTo(0);
		return bBrokeransweredMapper.selectByExample(example);
	}	
}
