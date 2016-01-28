package com.huatek.hbwebsite.calculator.show;

import com.huatek.framework.util.GsonUtil;
import com.huatek.hbwebsite.calculator.entity.HouseCalculateEntity;
import com.huatek.hbwebsite.util.HouseLoadCalculator;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({ "/calculator.show" })
public class HouseLoadCalculatorShow {
	private static final Logger LOGGER = Logger.getLogger(HouseLoadCalculatorShow.class);

	@RequestMapping(params = { "actionMethod=calculateWithBenXi" })
	public void calculateWithBenXi(@RequestParam("area") Integer area,
			@RequestParam("pricePerUnit") Integer pricePerUnit, @RequestParam("percent") Double percent,
			@RequestParam("month") Integer month, @RequestParam("rate") Double rate,
			@RequestParam("moneyLoaded") Integer moneyLoaded, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		HouseLoadCalculator calculator = HouseLoadCalculator.getInstance();
		String returnedString = "";
		HouseCalculateEntity calculateEntity = new HouseCalculateEntity();
		double moneyPerMonth = 0.0D;
//		double e;
//		BigDecimal e1;
		double priceToPay;
		BigDecimal bgPriceToPay;
		double interest;
		BigDecimal bgInterest;
		if (moneyLoaded != 0 && month != null && rate != null) {
			double loanRate = rate / 12.0D / 100.0D;
			calculateEntity.setLoadPrice(moneyLoaded);
			calculateEntity.setMonth(month.intValue());
			moneyPerMonth = calculator.calculateMoneyPerMonth(moneyLoaded, month, loanRate);
			BigDecimal mPMonth = new BigDecimal(moneyPerMonth);
			calculateEntity.setPayforPerMonth(mPMonth.setScale(2, 4).doubleValue());
			priceToPay = calculator.calculateTotalInterest(moneyPerMonth, month, moneyLoaded) + moneyLoaded;
			bgPriceToPay = new BigDecimal(priceToPay);
			calculateEntity.setPriceToPay(bgPriceToPay.setScale(2, 4).doubleValue());
			interest = calculateEntity.getPriceToPay() - calculateEntity.getLoadPrice();
			bgInterest = new BigDecimal(interest);
			calculateEntity.setInterest(bgInterest.setScale(2, 4).doubleValue());
		} else if (area != 0 && pricePerUnit != 0 && percent != null && month != null && rate != null) {
			double loanRate = rate / 12.0D / 100.0D;
			calculateEntity.setHousePrice(area * pricePerUnit);
			calculateEntity.setMonth(month);
			calculateEntity.setLoadPrice(area * pricePerUnit * percent / 10.0D);
			moneyPerMonth = calculator.calculateMoneyPerMonth(area, pricePerUnit,	percent / 10.0D, month, loanRate);
			BigDecimal mPMonth = new BigDecimal(moneyPerMonth);
			calculateEntity.setPayforPerMonth(mPMonth.setScale(2, 4).doubleValue());
			priceToPay = calculator.calculateTotalInterest(moneyPerMonth, month, moneyLoaded) + moneyLoaded;
			bgPriceToPay = new BigDecimal(priceToPay);
			calculateEntity.setPriceToPay(bgPriceToPay.setScale(2, 4).doubleValue());
			interest = calculateEntity.getPriceToPay() - calculateEntity.getLoadPrice();
			bgInterest = new BigDecimal(interest);
			calculateEntity.setInterest(bgInterest.setScale(2, 4).doubleValue());
		}

		returnedString = GsonUtil.getCommonGsonInstance().toJson(calculateEntity);

		try {
			response.getOutputStream().write(returnedString.getBytes());
			response.getOutputStream().flush();
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			try {
				response.getOutputStream().close();
			} catch (IOException ex) {
				LOGGER.error(ex.getMessage());
			}
		}
	}

	@RequestMapping(params = { "actionMethod=calculateWithBenJin" })
	public void calculateWithBenJin(@RequestParam("area") Integer area,
			@RequestParam("pricePerUnit") Integer pricePerUnit, @RequestParam("percent") Double percent,
			@RequestParam("month") Integer month, @RequestParam("rate") Double rate,
			@RequestParam("moneyLoaded") Double moneyLoaded, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		if (0.0D == moneyLoaded.doubleValue()) {
			moneyLoaded = Double.valueOf(area * pricePerUnit * percent / 10.0D);
		}

		double ratePerMonth = rate / 12.0D / 100.0D;
		double moneyPerMonth = moneyLoaded.doubleValue() / (double) month;

		List<Double> resultList = new ArrayList<Double>();
		for (int ii = 0; ii < month; ++ii) {
			double e = moneyLoaded.doubleValue() / (double) month	+ (moneyLoaded - moneyPerMonth * ii) * ratePerMonth;
			BigDecimal e1 = new BigDecimal(e);
			resultList.add(e1.setScale(2, 4).doubleValue());
		}

		String json = GsonUtil.getCommonGsonInstance().toJson(resultList);

		try {
			response.getOutputStream().write(json.getBytes());
			response.getOutputStream().flush();
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			try {
				response.getOutputStream().close();
			} catch (IOException ex) {
				LOGGER.error(ex.getMessage());
			}
		}
	}
}
