/**
 * 
 */
package cn.hshb.web.test.house;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;

import cn.hshb.web.common.util.StringUtil;
import cn.hshb.web.house.enums.EnumHouseTag;

/**
 * @author ShengYoufu
 *
 */
public class MyTest {
	
	/**
	 * 生成测试数据
	 */
	public static void test(Integer count){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/hbwebsite?useUnicode=true&amp;characterEncoding=utf8", 
//					"web", "123456");
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://192.168.1.5:1433;DatabaseName=newhouseagent", 
					"sa", "123456");
						
			pstmt = conn.prepareStatement("INSERT INTO _t_tagtest (description, tag) values (?, ?)");
			long st = System.currentTimeMillis();
			int ii=0;
			for(ii=0; ii<count; ii++){
				pstmt.setString(1, StringUtil.getRandStr(20, 100));
				pstmt.setInt(2, RandomUtils.nextInt(512));

				//每1000条提交一次
				if(ii>0 && (ii % 1000) == 0){
					pstmt.executeBatch();
					System.out.println(ii);
				}else
					pstmt.addBatch();

			}
			pstmt.executeBatch();
			System.out.println(ii);
			
			long et = System.currentTimeMillis();
			System.out.println("耗时：" + (et - st)+" ms");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{ pstmt.close(); } catch(Exception ex){ex.printStackTrace();}
			}
			if(conn!=null){
				try{ conn.close(); } catch(Exception ex){ex.printStackTrace();}
			}
		}
		
	}

	public static void test1(){
		Connection conn = null;
//		PreparedStatement pstmt1 = null;
//		PreparedStatement pstmt2 = null;
		Statement pstmt1 = null;
		Statement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/hbwebsite?useUnicode=true&amp;characterEncoding=utf8", 
//					"web", "123456");
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://192.168.1.5:1433;DatabaseName=newhouseagent", 
					"sa", "123456");
			
			String s = "select * from _t_tagtest where ";
			StringBuilder sbSql = new StringBuilder(s);
			sbSql.append(" tag & ").append(EnumHouseTag.CI_XIN_FANG.value()).append("=").append(EnumHouseTag.CI_XIN_FANG.value());
//			System.out.println(sbSql.toString());
//			pstmt1 = conn.prepareStatement(sbSql.toString());
			pstmt1 = conn.createStatement();
			long st = System.currentTimeMillis();
//			rs1 = pstmt1.executeQuery();
			rs1 = pstmt1.executeQuery(sbSql.toString());
			long et = System.currentTimeMillis();
			int count = 0;
			while(rs1.next())
				count++;
			System.out.print("用位运算模式耗时：" + (et - st)+" ms; ");
			System.out.print("查询到记录数：" + (count)+" \r\n");

			StringBuilder sb = new StringBuilder("");
			for(int ii=1; ii<=128; ii++){
				Integer f = ii | EnumHouseTag.CI_XIN_FANG.value();
				sb.append(",").append(f);
				//System.out.println(intToBinaryString(f));
			}
			if(sb.length()>1) sb.deleteCharAt(0);
			sb.insert(0, s + " tag in (");
			sb.append(")");
//			System.out.println(sb.toString());
//			pstmt2 = conn.prepareStatement(sb.toString());
			pstmt2 = conn.createStatement();
			st = System.currentTimeMillis();
//			rs2 = pstmt2.executeQuery();
			rs2 = pstmt2.executeQuery(sb.toString());
			et = System.currentTimeMillis();
			count = 0;
			while(rs2.next())
				count++;
			System.out.print("用IN模式耗时：" + (et - st)+" ms; ");
			System.out.print("查询到记录数：" + (count)+" \r\n");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs1!=null){
				try{ rs1.close(); } catch(Exception ex){ex.printStackTrace();}
			}
			if(rs2!=null){
				try{ rs2.close(); } catch(Exception ex){ex.printStackTrace();}
			}
			if(pstmt1!=null){
				try{ pstmt1.close(); } catch(Exception ex){ex.printStackTrace();}
			}
			if(pstmt2!=null){
				try{ pstmt2.close(); } catch(Exception ex){ex.printStackTrace();}
			}
			if(conn!=null){
				try{ conn.close(); } catch(Exception ex){ex.printStackTrace();}
			}
		}
	}
	
	public static String intToBinaryString(Integer val){
		String s = Integer.toBinaryString(val);
		s = "0000000000" + s;
		return s.substring(s.length() - 10, s.length() - 1);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		MyTest.test(500000);
		for(int ii=0; ii<10; ii++){
			System.out.println("\r\n第"+ii+"次测试: ");
			MyTest.test1();
		}
	}

}
