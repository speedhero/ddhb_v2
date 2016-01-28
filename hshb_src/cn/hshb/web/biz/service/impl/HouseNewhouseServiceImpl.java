/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.huatek.framework.tag.CutPageBean;
import com.huatek.framework.util.Parameter;
import com.huatek.hbwebsite.house.entity.RemainTime;

import cn.hshb.web.biz.mybatis.dao.HouseNewhouseMapper;
import cn.hshb.web.biz.mybatis.model.HouseNewhouse;
import cn.hshb.web.biz.mybatis.model.HouseNewhouseExample;
import cn.hshb.web.biz.mybatis.model.HouseNewhouseGroupbuy;
import cn.hshb.web.biz.mybatis.model.HouseNewhouseKey;
import cn.hshb.web.biz.mybatis.model.HouseNewhouseWithBLOBs;
import cn.hshb.web.biz.service.CommonCityService;
import cn.hshb.web.biz.service.CommonCountyService;
import cn.hshb.web.biz.service.CommonDecorationTypeService;
import cn.hshb.web.biz.service.HouseNewhouseGroupbuyService;
import cn.hshb.web.biz.service.HouseNewhouseService;
import cn.hshb.web.biz.util.CreateRandomData;
import cn.hshb.web.common.util.DateUtil;
import cn.hshb.web.common.util.StringUtil;

/**
 * @author ShengYoufu
 *
 */
@Service
public class HouseNewhouseServiceImpl implements HouseNewhouseService {
	private static final Log log = LogFactory.getLog(HouseNewhouseServiceImpl.class);
	private static final int PAGE_SIZE = 5;  //默认每页显示房源数
	private static boolean REPLACE_ENABLE = false;	//是否启用,把新房天数为0的改用随机
	private static int RANDOM_RANGE_MIN = 1;	//随机数最小值范围
	private static int RANDOM_RANGE_MAX = 30;	//随机数最大值范围
	static{
		String s = Parameter.getInstance().getProperty("replace.enable");
		if(StringUtil.isNotEmpty(s))
			REPLACE_ENABLE = Boolean.parseBoolean(s);
		s = Parameter.getInstance().getProperty("replace.date.random.range.day.min");
		if(StringUtil.isNotEmpty(s))
			RANDOM_RANGE_MIN = Integer.parseInt(s);
		s = Parameter.getInstance().getProperty("replace.date.random.range.day.max");
		if(StringUtil.isNotEmpty(s))
			RANDOM_RANGE_MAX = Integer.parseInt(s);
	}
	
	@Autowired
	private HouseNewhouseMapper houseNewhouseMapper;
	
	@Autowired
	private HouseNewhouseGroupbuyService houseNewhouseGroupbuyService;
	
	@Autowired
	private CommonCountyService commonCountyService;
	
	@Autowired
	private CommonCityService commonCityService;

	@Autowired
	private CommonDecorationTypeService commonDecorationTypeService;
	
	/**
	 * 根据新楼盘编号 查询新楼盘
	 * @param newHouseNo	新楼盘编号
	 * @return
	 */
	@Override
	public HouseNewhouseWithBLOBs loadNewHouse(String newHouseNo){
		if(StringUtil.isNotEmpty(newHouseNo) && StringUtil.isNumeric(newHouseNo)){
			Integer houseNo = Integer.parseInt(newHouseNo);
			HouseNewhouseKey key = new HouseNewhouseKey();
			key.setNewhouseNo(houseNo);
			
			HouseNewhouseWithBLOBs house = houseNewhouseMapper.selectByPrimaryKey(key);
			if(house!=null){
				setRemainDays(house);
				setEnableFlag(house);
				populateHouseNew(house);
			}
			return house;
		}
		return null;
	}
	
	/**
	 * 载入新楼盘数据
	 * @param maxRows	要载入的记录数
	 * @return
	 */
	@Override
	public List<HouseNewhouseWithBLOBs> loadNewHouse(Integer maxRows) {
		HouseNewhouseExample example = new HouseNewhouseExample();
		example.createCriteria().andDeleteflagEqualTo(0);
		
		if(maxRows!=null && maxRows>0)
			PageHelper.startPage(1, maxRows);
		List<HouseNewhouseWithBLOBs> list = houseNewhouseMapper.selectByExampleWithBLOBs(example);
		for(HouseNewhouseWithBLOBs h: list){
			if(h!=null){
				setRemainDays(h);
				setEnableFlag(h);
				populateHouseNew(h);
			}
		}
		return list;
	}
	
	/**
	 * 查询新楼盘 
	 * @param pageNum	页码
	 * @param pageSize	每页记录数
	 * @return
	 */
	@Override
	public List<HouseNewhouseWithBLOBs> getNewHouse(Integer pageNum, Integer pageSize){
		HouseNewhouseExample example = new HouseNewhouseExample();
		example.createCriteria().andDeleteflagEqualTo(0);
		
		int pgNum = pageNum!=null && pageNum>0? pageNum: 1;
		int pgSize = pageSize!=null && pageSize>0? pageSize: PAGE_SIZE;
		PageHelper.startPage(pgNum, pgSize);
		List<HouseNewhouseWithBLOBs> list = houseNewhouseMapper.selectByExampleWithBLOBs(example);
		for(HouseNewhouseWithBLOBs h: list){
			if(h!=null){
				setRemainDays(h);
				setEnableFlag(h);
				populateHouseNew(h);
			}
		}
		return list;
	}
	
	/**
	 * 设置房源有效标志，必须在设置了RemainDays再调用 
	 * @param house
	 */
	@Override
	public void setEnableFlag(HouseNewhouseWithBLOBs house) {
		RemainTime remainTime = house.getRemainDays();
		if(remainTime!=null) {
			Boolean isEnabled = ((remainTime.getDay() + remainTime.getHour()  + remainTime.getMin() + remainTime.getSec())>0 && house.getDeleteflag()==0);
			house.setEnableFlag(isEnabled);
		}else{
			house.setEnableFlag(false);
		}
	}
	
	/**
	 * 计算团购剩余时间
	 * @param house
	 */
	@Override
	public void setRemainDays(HouseNewhouseWithBLOBs house) {
		RemainTime tmp = new RemainTime();
		if (house.getEndDate() == null) {
			return;
		} else {
			Long remainDays = DateUtil.dateDiff(house.getEndDate(), new Date(), Calendar.DATE);
			if (remainDays < 0) {
				tmp.setDay(0);
				tmp.setHour(0);
				tmp.setMin(0);
				tmp.setSec(0);
				house.setRemainDays(tmp);
				//当新房过期时,使用随机数
				if(REPLACE_ENABLE){
					//取出一个随机数
					Date date = new Date();
					long days = new CreateRandomData().createRandomNumber(RANDOM_RANGE_MAX, RANDOM_RANGE_MIN);
					date = DateUtil.addDay(date, (int)days);
					long hours = new CreateRandomData().createRandomNumber(24);
					date = DateUtil.addHour(date, (int)hours);
					long minutes = new CreateRandomData().createRandomNumber(60);
					date = DateUtil.addMinute(date, (int)minutes);
					long seconds = new CreateRandomData().createRandomNumber(60);
					date = DateUtil.addSecond(date, (int)seconds);
					house.setEndDate(date);
					house.setChoiceUpdateData(true);
					setRemainDays(house);
				}
				return;
			} else {
				Long tempSec = DateUtil.dateDiff(house.getEndDate(), new Date(), Calendar.MILLISECOND);
				Long days = tempSec / 86400L;
				Long hours = tempSec % 86400L / 3600L;
				Long minutes = tempSec % 3600L / 60L;
				Long seconds = tempSec % 60L / 1L;
				tmp.setDay(days.intValue());
				tmp.setHour(hours.intValue());
				tmp.setMin(minutes.intValue());
				tmp.setSec(seconds.intValue());
				house.setRemainDays(tmp);
			}
		}
	}

	/**
	 * 按首页推荐标志查询新楼盘（用在主页显示）
	 * @param count	要查询的记录数
	 * @return
	 */
	@Override
	public List<HouseNewhouseWithBLOBs> loadNewHouseForHomepageRecommand(Integer count) {
		HouseNewhouseExample example = new HouseNewhouseExample();
		example.createCriteria().andDeleteflagEqualTo(0);
		if(count!=null && count>0)
			PageHelper.startPage(1, count);
		example.setOrderByClause(" homepage_recommand_flag desc, homepage_recommand_time desc");
		List<HouseNewhouseWithBLOBs> list = houseNewhouseMapper.selectByExampleWithBLOBs(example);
		for(HouseNewhouseWithBLOBs house: list){
			if(house!=null){
				setRemainDays(house);
				setEnableFlag(house);
				populateHouseNew(house);
			}
		}
		return list;
	}

	/**
	 * 查询指定日期之前的新楼盘记录数
	 * @param date
	 * @return
	 */
	@Override
	public Integer getCountHouseNewBeforeDate(Date date) {
		HouseNewhouseExample example = new HouseNewhouseExample();
		example.createCriteria()
			.andEndDateLessThanOrEqualTo(date)
			.andDeleteflagEqualTo(0);
		return houseNewhouseMapper.countByExample(example);
	}
	
	/**
	 * 查询指定日期之后(含指定日期)的新楼盘记录数
	 * @param date	要判断的日期
	 * @return
	 */
	@Override
	public Integer getCountHouseNewAfterDate(Date date) {
		HouseNewhouseExample example = new HouseNewhouseExample();
		example.createCriteria()
			.andEndDateGreaterThanOrEqualTo(date)
			.andDeleteflagEqualTo(0);
		return houseNewhouseMapper.countByExample(example);
	}

	/**
	 * 给新楼盘载入城市、城区和装修情况的信息
	 * @param house
	 */
	public void populateHouseNew(HouseNewhouseWithBLOBs house){
		String countyId = house.getCountyNo();
		String cityId = house.getCityNo();
		String decorationId = house.getDecorationNo();

		house.setCity(commonCityService.getCommonCity(cityId));
		house.setCounty(commonCountyService.getCounty(countyId));
		house.setDecoration(commonDecorationTypeService.getDecorationType(decorationId));
	}
	
	
	/**
	 * 给每个楼盘设置团购人数
	 * @param pageBean
	 * @return
	 */
	@Override
	public void setHouseGroupbuys(List<HouseNewhouseWithBLOBs> houseList) {
		List<Integer> ids = new ArrayList<Integer>();
		for(HouseNewhouseWithBLOBs house: houseList){
			ids.add(house.getNewhouseNo());
		}
		List<HouseNewhouseGroupbuy> lsit = houseNewhouseGroupbuyService.getHouseGroupbuys(ids);
		for(HouseNewhouseGroupbuy b: lsit){
			for(HouseNewhouseWithBLOBs house: houseList){
				if(house.getNewhouseNo()==b.getNewhouseNo()){
					house.getGroupbuys().add(b);
				}
			}
		}
	}

	@Override
	public void updateHouseNewHouseData(List<HouseNewhouseWithBLOBs> houseList) {
		if(houseList != null && houseList.size() > 0){
			for(HouseNewhouseWithBLOBs house : houseList){
				updateHouseNewHouseData(house);
			}
		}
		
	}

	@Override
	public void updateHouseNewHouseData(HouseNewhouseWithBLOBs house) {
		if(house != null){
			if(house.getChoiceUpdateData())
				houseNewhouseMapper.updateByPrimaryKeyWithBLOBs(house);
		}
	}

	@Override
	public void updateHouseNewHouseData(HouseNewhouse house) {
		if(house != null){
			if(house.getChoiceUpdateData())
				houseNewhouseMapper.updateByPrimaryKey(house);
		}
	}

}
