/**
 * 
 */
package cn.hshb.web.biz.service;

import java.util.Date;
import java.util.List;

import cn.hshb.web.biz.mybatis.model.CommonLoanRate;

/**
 * @author ShengYoufu
 *
 */
public interface CommonLoanRateService {
	/**
	 * 当前贷款利率
	 * @return
	 */
	public List<CommonLoanRate> getCommonLoanRates();
	
	/**
	 * 根据日期查询贷款利率
	 * @param date
	 * @return
	 */
	public CommonLoanRate getCommonLoanRate(Date date);
}
