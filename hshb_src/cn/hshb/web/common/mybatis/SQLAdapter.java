/**
 * 
 */
package cn.hshb.web.common.mybatis;

/**
 * 用以直接在MyBatis中执行SQL的适配器
 * 用法：
    1、create typeAlias of class SQLAdapter
    <typeAlias alias="sqladapter" type="cn.hshb.web.common.mybatis.SQLAdapter" />

    2、put select tag in each object xml where you need to execute the sql directly.
    <select id="findRecords" parameterType="SQLAdapter" resultMap="xxxxxResultMap">  
        ${sql}  
    </select> 

    3、call this select method like
        String _sql = "select * from table where... order by... limit...";
        xxxxx.findRecords(new SQLAdapter(_sql));

    4、Things have been all done. you can no longer writer complex sql language in the xml file. Good Luck.
 * 
 * @author Administrator
 *
 */
public class SQLAdapter {
	String sql;

	public SQLAdapter(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
