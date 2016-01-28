/**
 * 
 */
package cn.hshb.web.biz.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.hshb.web.biz.mybatis.dao.PriceChartMapper;
import cn.hshb.web.biz.mybatis.model.PriceChart;
import cn.hshb.web.biz.mybatis.model.PriceChartExample;
import cn.hshb.web.biz.service.PriceChartService;
import cn.hshb.web.house.enums.EnumHouseType;

/**
 * @author ShengYoufu
 *
 */
@Service
public class PriceChartServiceImpl implements PriceChartService {
	private static final Log log = LogFactory.getLog(PriceChartServiceImpl.class);

	@Autowired
	private PriceChartMapper priceChartMapper;
	
	/**
	 * 获取首页房源价格走势图数据
	 * @param houseType
	 * @return
	 */
	@Override
	public List<List<Object[]>> getPriceChart(EnumHouseType houseType) {
		List<List<Object[]>> list = null;
		
		PriceChartExample example = new PriceChartExample();
		example.createCriteria().andHouseTypeEqualTo(houseType.value());
		example.setOrderByClause("default_id desc");
		
		PageHelper.startPage(1, 12);	//取最近12个月的数据
		List<PriceChart> pcList = priceChartMapper.selectByExample(example);
		
		Collections.sort(pcList, new Comparator<PriceChart>(){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			@Override
			public int compare(PriceChart pc1, PriceChart pc2) {
				Long t = 0L;
				try {
					t = (sdf.parse(pc1.getCurrentDate()).getTime() - sdf.parse(pc2.getCurrentDate()).getTime());
				} catch (ParseException ex) {
					log.error("按日期对首页房源价格走势图数据进行排序时出错。", ex);
				}
				return t.intValue(); 
			}
		});
		
		if(pcList!=null && pcList.size()>0){
			list = new ArrayList<List<Object[]>>();
			
			//存放挂牌均价数据
			Object[] medianListing = new Object[2];
			List<Object[]> mList = new ArrayList<Object[]>();
			
			//存放成交均价数据
			Object[] averageTransactionPrice = new Object[2];
			List<Object[]> aList = new ArrayList<Object[]>();
			
			//成交数量		
			Object[] volume = new Object[2];
			List<Object[]> vList = new ArrayList<Object[]>();
			
			for(PriceChart pc : pcList){
				medianListing = new Object[2];
				averageTransactionPrice = new Object[2];
				volume = new Object[2];
				
				medianListing[0] = pc.getCurrentDate();
				medianListing[1] = pc.getMedianListing();
				mList.add(medianListing);
				
				averageTransactionPrice[0] = pc.getCurrentDate();
				averageTransactionPrice[1] = pc.getAverageTransactionPrice();
				aList.add(averageTransactionPrice);
				
				volume[0] = pc.getCurrentDate();
				volume[1] = pc.getVolume();
				vList.add(volume);
			}
			list.add(mList);
			list.add(aList);
			list.add(vList);
		}
		return list;
	}		
}
