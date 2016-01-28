package cn.hshb.web.biz.mybatis.model;

import java.util.Date;

public class HouseSecondPriceTrend extends HouseSecondPriceTrendKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_second_price_trend.house_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String houseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_second_price_trend.price
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Float price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_second_price_trend.unit_price
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Float unitPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_second_price_trend.dateandmonth
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date dateandmonth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_second_price_trend.priceUpdateDate
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date priceupdatedate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_second_price_trend.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer deleteflag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_second_price_trend.house_id
     *
     * @return the value of house_second_price_trend.house_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getHouseId() {
        return houseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_second_price_trend.house_id
     *
     * @param houseId the value for house_second_price_trend.house_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setHouseId(String houseId) {
        this.houseId = houseId == null ? null : houseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_second_price_trend.price
     *
     * @return the value of house_second_price_trend.price
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Float getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_second_price_trend.price
     *
     * @param price the value for house_second_price_trend.price
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_second_price_trend.unit_price
     *
     * @return the value of house_second_price_trend.unit_price
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Float getUnitPrice() {
        return unitPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_second_price_trend.unit_price
     *
     * @param unitPrice the value for house_second_price_trend.unit_price
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_second_price_trend.dateandmonth
     *
     * @return the value of house_second_price_trend.dateandmonth
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getDateandmonth() {
        return dateandmonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_second_price_trend.dateandmonth
     *
     * @param dateandmonth the value for house_second_price_trend.dateandmonth
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDateandmonth(Date dateandmonth) {
        this.dateandmonth = dateandmonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_second_price_trend.priceUpdateDate
     *
     * @return the value of house_second_price_trend.priceUpdateDate
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getPriceupdatedate() {
        return priceupdatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_second_price_trend.priceUpdateDate
     *
     * @param priceupdatedate the value for house_second_price_trend.priceUpdateDate
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setPriceupdatedate(Date priceupdatedate) {
        this.priceupdatedate = priceupdatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_second_price_trend.deleteflag
     *
     * @return the value of house_second_price_trend.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_second_price_trend.deleteflag
     *
     * @param deleteflag the value for house_second_price_trend.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }
}