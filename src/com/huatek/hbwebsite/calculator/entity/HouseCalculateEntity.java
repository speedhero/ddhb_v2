package com.huatek.hbwebsite.calculator.entity;

public class HouseCalculateEntity {
	private double housePrice;
	private double loadPrice;
	private double priceToPay;
	private double interest;
	private double payforPerMonth;
	private int month;

	public HouseCalculateEntity() {
	}

	public HouseCalculateEntity(double housePrice, double loadPrice, double priceToPay, double interest,
			double payforPerMonth, int month) {
		this.housePrice = housePrice;
		this.loadPrice = loadPrice;
		this.priceToPay = priceToPay;
		this.interest = interest;
		this.payforPerMonth = payforPerMonth;
		this.month = month;
	}

	public double getHousePrice() {
		return this.housePrice;
	}

	public void setHousePrice(double housePrice) {
		this.housePrice = housePrice;
	}

	public double getLoadPrice() {
		return this.loadPrice;
	}

	public void setLoadPrice(double loadPrice) {
		this.loadPrice = loadPrice;
	}

	public double getPriceToPay() {
		return this.priceToPay;
	}

	public void setPriceToPay(double priceToPay) {
		this.priceToPay = priceToPay;
	}

	public double getInterest() {
		return this.interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getPayforPerMonth() {
		return this.payforPerMonth;
	}

	public void setPayforPerMonth(double payforPerMonth) {
		this.payforPerMonth = payforPerMonth;
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
}
