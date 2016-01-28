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

import cn.hshb.web.biz.mybatis.dao.CommonLoanRateMapper;
import cn.hshb.web.biz.mybatis.model.CommonLoanRate;
import cn.hshb.web.biz.mybatis.model.CommonLoanRateExample;
import cn.hshb.web.biz.service.CommonLoanRateService;
import cn.hshb.web.common.util.DeepCloneUtil;

/**
 * @author ShengYoufu
 *
 */
@Service
public class CommonLoanRateServiceImpl implements CommonLoanRateService {
	private static final Log log = LogFactory.getLog(CommonLoanRateServiceImpl.class);
	
	private static List<CommonLoanRate> rateList = new ArrayList<CommonLoanRate>();
	
	//最后刷新时间
	private static Long LAST_LOAD_TIME = System.currentTimeMillis();
	//刷新间隔
	private static Long RELOAD_INTERVAL = 24 * 3600L;
	
	@Autowired
	private CommonLoanRateMapper commonLoanRateMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CommonLoanRate> getCommonLoanRates() {
		loadCommonLoanRates();
		try {
			return (List<CommonLoanRate>)DeepCloneUtil.cloneObject(rateList);
		} catch (IllegalArgumentException ex) {
			log.warn("返回贷款利率对象失败，克隆出错：", ex);
		} catch (IllegalAccessException ex) {
			log.warn("返回贷款利率对象失败，克隆出错：", ex);
		} catch (InstantiationException ex) {
			log.warn("返回贷款利率对象失败，克隆出错：", ex);
		}
		//如果克隆失败，则返回原始对象
		return rateList;
	}

	@Override
	public CommonLoanRate getCommonLoanRate(Date date){
		loadCommonLoanRates();
		for(CommonLoanRate r: rateList){
			if(r.getRateDate().equals(date)){
				try {
					return (CommonLoanRate)DeepCloneUtil.cloneObject(r);
				} catch (IllegalArgumentException ex) {
					log.warn("返回贷款利率对象失败，克隆出错：", ex);
				} catch (IllegalAccessException ex) {
					log.warn("返回贷款利率对象失败，克隆出错：", ex);
				} catch (InstantiationException ex) {
					log.warn("返回贷款利率对象失败，克隆出错：", ex);
				}
				//如果克隆失败，则返回原始对象
				return r;
			}
		}
		return null;
	}

	protected void loadCommonLoanRates(){
		if(rateList==null || rateList.isEmpty() || isNeedReload()){
			synchronized (CommonLoanRateServiceImpl.class) {
				if(rateList==null || rateList.size()<1 || isNeedReload()) {
					CommonLoanRateExample example = new CommonLoanRateExample();
					example.createCriteria().andDeleteflagEqualTo(0);
					rateList = commonLoanRateMapper.selectByExample(example);
					
					LAST_LOAD_TIME = System.currentTimeMillis();  //重新设置最后刷新时间
				}
			}
		}
	}

	/**
	 * 判断是否到了指定的刷新时间
	 * @return
	 */
	protected Boolean isNeedReload(){
		Long t = System.currentTimeMillis() - LAST_LOAD_TIME;
		return ( t / 1000 >= RELOAD_INTERVAL);
	}
}
