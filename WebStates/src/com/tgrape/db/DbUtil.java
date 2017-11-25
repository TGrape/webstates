package com.tgrape.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tgrape.json.JsonUtil;
import com.tgrape.obj.Quotes;
import com.tgrape.obj.QuotesAll;

public class DbUtil {

	private static Connection conn = null;
	private static Statement stmt = null;
	private static String url = "jdbc:oracle:" + "thin:@127.0.0.1:1521:STATES";
    private static String user = "STATES";
    private static String password = "liuyuding0329";
    
    static {
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
	public static boolean executeSql(String sql){
        try {        	
        	if(null==conn || conn.isClosed()){
        		conn = DriverManager.getConnection(url, user, password);
				conn.setAutoCommit(true);
        	}
        	if(stmt == null || stmt.isClosed())
        		stmt = conn.createStatement();
			stmt.execute(sql);
			return true;
        }catch(Exception e){
        	e.printStackTrace();
        	return false;
        }finally{
        	try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}        	
        }
        
	}

	public static ResultSet executeQuery(String sql){
        try {
        	if(null==conn){
        		conn = DriverManager.getConnection(url, user, password);
        		conn.setAutoCommit(true);
        	}
        	if(null==stmt)
        		stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
        }catch(Exception e){
        	e.printStackTrace();
        	return null;
        }
        
	}

	public static String getInvestAll(){
		String sql = "select sn,content,author  from quotes_invest order by sn";
		ResultSet rs = DbUtil.executeQuery(sql);
		QuotesAll qa = new QuotesAll();
		try {
			while(rs.next()){
				String sn = rs.getString("sn");
				String content =  rs.getString("content");
				String author = rs.getString("author");
				Quotes q = new Quotes(sn, content, author);
				qa.add(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return JsonUtil.object2Json(qa);
	}
	public static boolean insertQuotesLife(String quotes_life,String author)  {
		ResultSet rs = DbUtil.executeQuery("select count(*) maxid from quotes_life");
		int max = 0;
		try {
			while(rs.next()){
				max = rs.getInt("maxid");			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		max += 1;
		if(author==null)
			author = "股侠";
		String sql = "insert into quotes_life values("+max+",'"+quotes_life+"','"+author+"')";
		boolean b = DbUtil.executeSql(sql);
		try {
			rs.close();
			stmt.close();
			conn.close();
			stmt = null;
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	public static boolean insertQuotesInvest(String quotes_invest,String author) {
		ResultSet rs = DbUtil.executeQuery("select count(*) maxid from quotes_invest");
		int max = 0;
		try {
			while(rs.next()){
				max = rs.getInt("maxid");			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
		max += 1;
		if(author==null)
			author = "股侠";
		String sql = "insert into quotes_invest values("+max+",'"+quotes_invest+"','"+author+"')";
		boolean b = DbUtil.executeSql(sql);
		try {
			rs.close();
			stmt.close();
			conn.close();
			stmt = null;
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
}
