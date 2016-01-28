package cn.hshb.web.biz.mybatis.model;

public class EfwTargetFieldMap extends EfwTargetFieldMapKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column efw_target_field_map.MODULE_ID
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private Integer moduleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column efw_target_field_map.MAP_NAME
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private String mapName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column efw_target_field_map.TARGET_CLASS
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private String targetClass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column efw_target_field_map.TARGET_FIELD
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private String targetField;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column efw_target_field_map.IS_ENABLE
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    private Integer isEnable;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column efw_target_field_map.MODULE_ID
     *
     * @return the value of efw_target_field_map.MODULE_ID
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public Integer getModuleId() {
        return moduleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column efw_target_field_map.MODULE_ID
     *
     * @param moduleId the value for efw_target_field_map.MODULE_ID
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column efw_target_field_map.MAP_NAME
     *
     * @return the value of efw_target_field_map.MAP_NAME
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column efw_target_field_map.MAP_NAME
     *
     * @param mapName the value for efw_target_field_map.MAP_NAME
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setMapName(String mapName) {
        this.mapName = mapName == null ? null : mapName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column efw_target_field_map.TARGET_CLASS
     *
     * @return the value of efw_target_field_map.TARGET_CLASS
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getTargetClass() {
        return targetClass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column efw_target_field_map.TARGET_CLASS
     *
     * @param targetClass the value for efw_target_field_map.TARGET_CLASS
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass == null ? null : targetClass.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column efw_target_field_map.TARGET_FIELD
     *
     * @return the value of efw_target_field_map.TARGET_FIELD
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public String getTargetField() {
        return targetField;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column efw_target_field_map.TARGET_FIELD
     *
     * @param targetField the value for efw_target_field_map.TARGET_FIELD
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setTargetField(String targetField) {
        this.targetField = targetField == null ? null : targetField.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column efw_target_field_map.IS_ENABLE
     *
     * @return the value of efw_target_field_map.IS_ENABLE
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public Integer getIsEnable() {
        return isEnable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column efw_target_field_map.IS_ENABLE
     *
     * @param isEnable the value for efw_target_field_map.IS_ENABLE
     *
     * @mbggenerated Wed Jul 08 19:17:39 GMT+08:00 2015
     */
    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }
}