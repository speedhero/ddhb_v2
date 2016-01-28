package cn.hshb.web.biz.mybatis.dao;

import cn.hshb.web.biz.mybatis.model.NewsNotice;
import cn.hshb.web.biz.mybatis.model.NewsNoticeExample;
import cn.hshb.web.biz.mybatis.model.NewsNoticeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsNoticeMapper extends HshbBaseMapper<NewsNotice> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int countByExample(NewsNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByExample(NewsNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int deleteByPrimaryKey(NewsNoticeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insert(NewsNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int insertSelective(NewsNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    List<NewsNotice> selectByExample(NewsNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    NewsNotice selectByPrimaryKey(NewsNoticeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExampleSelective(@Param("record") NewsNotice record, @Param("example") NewsNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByExample(@Param("record") NewsNotice record, @Param("example") NewsNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKeySelective(NewsNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_notice
     *
     * @mbggenerated Wed Jul 08 19:17:40 GMT+08:00 2015
     */
    int updateByPrimaryKey(NewsNotice record);
}