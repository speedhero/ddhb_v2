package cn.hshb.web.biz.mybatis.model;

public class HouseNewhouseWithBLOBs extends HouseNewhouse {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_newhouse.recommand_content
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String recommandContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_newhouse.selling_point
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String sellingPoint;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_newhouse.sales_promotion
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String salesPromotion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_newhouse.building_introduce
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String buildingIntroduce;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column house_newhouse.house_facility
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    private String houseFacility;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_newhouse.recommand_content
     *
     * @return the value of house_newhouse.recommand_content
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getRecommandContent() {
        return recommandContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_newhouse.recommand_content
     *
     * @param recommandContent the value for house_newhouse.recommand_content
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setRecommandContent(String recommandContent) {
        this.recommandContent = recommandContent == null ? null : recommandContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_newhouse.selling_point
     *
     * @return the value of house_newhouse.selling_point
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getSellingPoint() {
        return sellingPoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_newhouse.selling_point
     *
     * @param sellingPoint the value for house_newhouse.selling_point
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setSellingPoint(String sellingPoint) {
        this.sellingPoint = sellingPoint == null ? null : sellingPoint.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_newhouse.sales_promotion
     *
     * @return the value of house_newhouse.sales_promotion
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getSalesPromotion() {
        return salesPromotion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_newhouse.sales_promotion
     *
     * @param salesPromotion the value for house_newhouse.sales_promotion
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setSalesPromotion(String salesPromotion) {
        this.salesPromotion = salesPromotion == null ? null : salesPromotion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_newhouse.building_introduce
     *
     * @return the value of house_newhouse.building_introduce
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getBuildingIntroduce() {
        return buildingIntroduce;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_newhouse.building_introduce
     *
     * @param buildingIntroduce the value for house_newhouse.building_introduce
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setBuildingIntroduce(String buildingIntroduce) {
        this.buildingIntroduce = buildingIntroduce == null ? null : buildingIntroduce.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column house_newhouse.house_facility
     *
     * @return the value of house_newhouse.house_facility
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public String getHouseFacility() {
        return houseFacility;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column house_newhouse.house_facility
     *
     * @param houseFacility the value for house_newhouse.house_facility
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    public void setHouseFacility(String houseFacility) {
        this.houseFacility = houseFacility == null ? null : houseFacility.trim();
    }
}