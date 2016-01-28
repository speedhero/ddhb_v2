package com.huatek.hbwebsite.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 贷款利率计算工具类
 * 
 * @author ShengYoufu
 *
 */
public class HouseLoadCalculator {
	private static HouseLoadCalculator houseLoadCalculator = null;
	public static final Integer LOAN_TO_VALUE_RATIO = 7;  //默认贷款成数
	public static final Integer LOAN_MONTHS = 30 * 12;  //默认贷款月数（10年）
	
	public static synchronized HouseLoadCalculator getInstance() {
		if (houseLoadCalculator == null) {
			houseLoadCalculator = new HouseLoadCalculator();
		}
		return houseLoadCalculator;
	}

	public synchronized double calculateMoneyPerMonth(double moneyLoaded, int month, double rate) {
		return moneyLoaded * rate * Math.pow(1.0D + rate, (double) month) / (Math.pow(1.0D + rate, (double) month) - 1.0D);
	}

	public synchronized double calculateMoneyPerMonth(int area, int pricePerUnit, double percent, int month, double rate) {
		double moneyLoaded = (double) (area * pricePerUnit) * percent;
		return moneyLoaded * rate * Math.pow(1.0D + rate, (double) month) / (Math.pow(1.0D + rate, (double) month) - 1.0D);
	}

	public synchronized double calculateTotalInterest(double moneyPermonth, int month, double moneyLoaded) {
		return moneyPermonth * (double) month - moneyLoaded;
	}

	public synchronized List<Double> calculatePrincipalAmount(double rate, int month, double moneyLoaned) {
		List<Double> assetsList = new ArrayList<Double>();
		double principalAmount = moneyLoaned / (double) month;

		for (int i = 0; i < month; ++i) {
			double assets = principalAmount + (double) (month - i) * rate * (moneyLoaned - principalAmount * (double) i);
			assetsList.add(Double.valueOf(assets));
		}

		return assetsList;
	}

	public int calculateBJ(double yg, int yue, double yll) {
		Double benjin = Double.valueOf(yg * (Math.pow(1.0D + yll, (double) yue) - 1.0D)
				/ (yll * Math.pow(1.0D + yll, (double) yue)));
		return benjin.intValue();
	}
}
