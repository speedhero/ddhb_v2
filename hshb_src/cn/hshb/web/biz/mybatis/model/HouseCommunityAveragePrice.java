package cn.hshb.web.biz.mybatis.model;

import java.util.Date;

public class HouseCommunityAveragePrice extends HouseCommunityAveragePriceKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_average_price.community_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String communityId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_average_price.calculateDate
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Date calculatedate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_average_price.price_announced
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Float priceAnnounced;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_average_price.price_dealed
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Float priceDealed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_average_price.rent_average_price
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Float rentAveragePrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_community_average_price.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private Integer deleteflag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_average_price.community_id
     *
     * @return the value of house_community_average_price.community_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getCommunityId() {
        return communityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_average_price.community_id
     *
     * @param communityId the value for house_community_average_price.community_id
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_average_price.calculateDate
     *
     * @return the value of house_community_average_price.calculateDate
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Date getCalculatedate() {
        return calculatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_average_price.calculateDate
     *
     * @param calculatedate the value for house_community_average_price.calculateDate
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setCalculatedate(Date calculatedate) {
        this.calculatedate = calculatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_average_price.price_announced
     *
     * @return the value of house_community_average_price.price_announced
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Float getPriceAnnounced() {
        return priceAnnounced;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_average_price.price_announced
     *
     * @param priceAnnounced the value for house_community_average_price.price_announced
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setPriceAnnounced(Float priceAnnounced) {
        this.priceAnnounced = priceAnnounced;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_average_price.price_dealed
     *
     * @return the value of house_community_average_price.price_dealed
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Float getPriceDealed() {
        return priceDealed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_average_price.price_dealed
     *
     * @param priceDealed the value for house_community_average_price.price_dealed
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setPriceDealed(Float priceDealed) {
        this.priceDealed = priceDealed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_average_price.rent_average_price
     *
     * @return the value of house_community_average_price.rent_average_price
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Float getRentAveragePrice() {
        return rentAveragePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_average_price.rent_average_price
     *
     * @param rentAveragePrice the value for house_community_average_price.rent_average_price
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setRentAveragePrice(Float rentAveragePrice) {
        this.rentAveragePrice = rentAveragePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_community_average_price.deleteflag
     *
     * @return the value of house_community_average_price.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_community_average_price.deleteflag
     *
     * @param deleteflag the value for house_community_average_price.deleteflag
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }
}