/**
 * 
 */
package cn.hshb.web.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hshb.web.common.helper.HouseHelper;

/**
 * @author Sheng Youfu (wdmsyf@yahoo.com)
 * @since 2015-01
 * @version 1.0
 * http://www.hshb.cn
 * 
 */
public class GeoCoordinatesConvert {

	public Connection getConn(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/hbwebsite?useUnicode=true&amp;characterEncoding=utf8", "web", "123456");
			return conn;
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public void close(ResultSet rs, Statement stmt, Connection conn){
		if(rs!=null){
			try{
				rs.close();
				rs = null;
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		if(stmt!=null){
			try{
				stmt.close();
				stmt = null;
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		if(conn!=null){
			try{
				conn.close();
				conn = null;
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * 转换小区坐标
	 */
	public void convertForCommunity(){
		String querySql = "select * from house_community where location is not null";
		String updateSql = "update house_community set lng_qq='{0}', lat_qq='{1}', lng_baidu='{2}', lat_baidu='{3}' where erp_id='{4}'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = this.getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(querySql);
			
			Map<String, GeoBean> datMap = new HashMap<String, GeoBean>();
			while(rs.next()){
				String loc = rs.getString("location");
				String[] s = loc.split(",|\\|");
				if(s.length<2) continue;

				GeoBean bean = new GeoBean();
				bean.setErpId(rs.getString("erp_id"));
				datMap.put(rs.getString("erp_id"), bean);
				String s1[] = HouseHelper.geoConvertTencent2Baidu(Double.parseDouble(s[1]), Double.parseDouble(s[0]));
				bean.setLng_qq(s[1]);
				bean.setLat_qq(s[0]);
				bean.setLng_bd(s1[0]);
				bean.setLat_bd(s1[1]);
				
				System.out.println(bean.toString());
			}
			for(Map.Entry<String, GeoBean> e: datMap.entrySet()){
				GeoBean bean = e.getValue();
				String _sql = updateSql.replace("{0}", bean.lng_qq);
				_sql = _sql.replace("{1}", bean.lat_qq);
				_sql = _sql.replace("{2}", bean.lng_bd);
				_sql = _sql.replace("{3}", bean.lat_bd);
				_sql = _sql.replace("{4}", e.getKey());
				
				stmt.addBatch(_sql);
			}
			
			int[] rets = stmt.executeBatch();
			System.out.println("总共更新了 "+rets.length+" 行。");

		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			this.close(rs, stmt, conn);
		}
		
	}
	
	private class GeoBean{
		private String erpId;
		private String lng_qq;
		private String lat_qq;
		private String lng_bd;
		private String lat_bd;
		
		public String getErpId() {
			return erpId;
		}
		public void setErpId(String erpId) {
			this.erpId = erpId;
		}
		public String getLng_qq() {
			return lng_qq;
		}
		public void setLng_qq(String lng_qq) {
			this.lng_qq = lng_qq;
		}
		public String getLat_qq() {
			return lat_qq;
		}
		public void setLat_qq(String lat_qq) {
			this.lat_qq = lat_qq;
		}
		public String getLng_bd() {
			return lng_bd;
		}
		public void setLng_bd(String lng_bd) {
			this.lng_bd = lng_bd;
		}
		public String getLat_bd() {
			return lat_bd;
		}
		public void setLat_bd(String lat_bd) {
			this.lat_bd = lat_bd;
		}
		@Override
		public String toString() {
			return "GeoBean [erpId=" + erpId + ", lng_qq=" + lng_qq + ", lat_qq=" + lat_qq + ", lng_bd=" + lng_bd
					+ ", lat_bd=" + lat_bd + "]";
		}

	} 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GeoCoordinatesConvert convert = new GeoCoordinatesConvert();
		convert.convertForCommunity();
	}

}


