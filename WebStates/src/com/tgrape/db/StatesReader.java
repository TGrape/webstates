package com.tgrape.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tgrape.obj.States;
import com.tgrape.obj.StatesToday;
import com.tgrape.obj.Verifies;
import com.tgrape.obj.VerifiesToday;



public class StatesReader {
	private static Connection conn = null;
	private static Statement stmt = null;
	private static String url = "jdbc:oracle:" + "thin:@127.0.0.1:1521:STATES";
    private static String user = "STATES";
    private static String password = "liuyuding0329";
    
	public static StatesToday get(String date){
		StatesToday st = new StatesToday();
        try {
        	try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(true);
			stmt = conn.createStatement();
			String sql = "";
			if(date.equalsIgnoreCase("today")){	
				sql = "select T_STG_REC.stgcode stgcode,ZD_STRATEGE.NAME stgname,T_STG_REC.stkcode stkcode,stkname,hit,to_char(turnover3) turnover3,to_char(turnover6) turnover6,to_char(T_STG_REC.CREATE_DATE,'YYYY-MM-DD') time from T_STG_REC,T_STOCK, ZD_STRATEGE "+
							" where T_STG_REC.CREATE_DATE>sysdate- interval '20' hour " + 
							" and ZD_STRATEGE.scode=T_STG_REC.STGCODE "+
						    " and T_STG_REC.stkcode=T_STOCK.stkcode "+
//						    " and turnover3>3 and turnover6>6 "+
//						    " and T_STG_REC.STKCODE not in (  select T_STG_REC.STKCODE from T_STG_REC   "+
//							"						  where hit = 0 and CREATE_DATE>(select max(T_STG_REC.CREATE_DATE) from T_STG_REC)- interval '3' day  "+
//							"								and CREATE_DATE<(select max(T_STG_REC.CREATE_DATE) from T_STG_REC)- interval '20' hour)  "+			
							" order by T_STG_REC.stkcode,turnover3 desc,turnover6 desc,T_STG_REC.CREATE_DATE desc";
			}else{
				sql = "select T_STG_REC.stgcode stgcode,ZD_STRATEGE.NAME stgname,T_STG_REC.stkcode stkcode,stkname,hit,to_char(turnover3) turnover3,to_char(turnover6) turnover6,to_char(T_STG_REC.CREATE_DATE,'YYYY-MM-DD') time from T_STG_REC,T_STOCK, ZD_STRATEGE "+
						" where T_STG_REC.CREATE_DATE=to_date('"+date+"','YYYY-MM-DD')" + 
						" and ZD_STRATEGE.scode=T_STG_REC.STGCODE "+
					    " and T_STG_REC.stkcode=T_STOCK.stkcode "+
					    " and turnover3>3 and turnover6>6 "+
					    " and T_STG_REC.STKCODE not in (  select T_STG_REC.STKCODE from T_STG_REC   "+
						"						  where CREATE_DATE>(select max(T_STG_REC.CREATE_DATE) from T_STG_REC)- interval '3' day  "+
						"								and CREATE_DATE<(select max(T_STG_REC.CREATE_DATE) from T_STG_REC)- interval '7' hour)  "+			
						" order by T_STG_REC.stkcode,turnover3 desc,turnover6 desc,T_STG_REC.CREATE_DATE desc";
			}
			ResultSet rs = stmt.executeQuery(sql);
			States states = null;
			while(rs.next()){
				states = new States();
				states.setStgcode( rs.getString("stgcode") );
				states.setStgname(rs.getString("stgname"));
				states.setStkcode(rs.getString("stkcode"));
				states.setStkname(rs.getString("stkname"));
				states.setHit(""+rs.getInt("hit"));
				states.setTime(rs.getString("time"));
				states.setTurnover3(rs.getString("turnover3"));
				states.setTurnover6(rs.getString("turnover6"));
				String klinefile = getKlinefile(rs.getString("stkcode"));
				states.setKlinefile(klinefile);
				st.getStateslist().add(states);
			}
			st.refreshcout();
			
			sql = "select content  from (select * from quotes_life order by dbms_random.value) where rownum=1 ";
			rs = stmt.executeQuery(sql);
			String quotes_life = "";
			while(rs.next()){
				quotes_life = rs.getString("content");
			}
			if(null!=quotes_life && quotes_life.length()>0)
				st.quotes_life = quotes_life;
			
			sql = "select content  from (select * from quotes_invest order by dbms_random.value) where rownum=1 ";
			rs = stmt.executeQuery(sql);
			String quotes_invest = "";
			while(rs.next()){
				quotes_invest = rs.getString("content");
			}
			if(null!=quotes_invest && quotes_invest.length()>0)
				st.quotes_invest = quotes_invest;
			
			rs.close();
			stmt.close();
			conn.close();
			stmt = null;
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return st;
	}
	private static String getKlinefile(String stkcode) {
		String kfile = "newchart/daily/n/";
		if(stkcode.startsWith("00") || stkcode.startsWith("300"))
			kfile += "sz"+stkcode+".gif";
		else
			kfile += "sh"+stkcode+".gif";
		//http://image.sinajs.cn/newchart/daily/n/sz300099.gif
		return kfile;
	}
	
	public static VerifiesToday getVerify(int days){
		VerifiesToday vt = new VerifiesToday();
        try {
        	try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(true);
			stmt = conn.createStatement();
			//find all 
//			String  sql = "select count(*) ct from t_stg_rec where create_date>sysdate - interval '"+days+"' day";
//			ResultSet rs = stmt.executeQuery(sql);
//			int count  = 0;
//			if(rs.next()){
//				count = rs.getInt("ct");
//			}
//			vt.setCount(count);
			//--find one week stock
			//select stkcode,create_date from t_stg_rec where create_date>sysdate - interval '7' day group by stkcode,create_date;
			String sql = "select stkcode,stkname,stgcode,stgname,to_char(CREATE_DATE,'YYYY-MM-DD') time from (" +
								" select t_stg_rec.stkcode stkcode,t_stg_rec.create_date create_date,t_stock.stkname stkname,t_stg_rec.stgcode stgcode,zd_stratege.name stgname" +
								" from t_stg_rec,t_stock,zd_stratege " +
								" where t_stg_rec.create_date>sysdate - interval '"+days+"' day "+ 
								"	    and t_stg_rec.create_date<sysdate- interval '1' day " +
								" 		and t_stg_rec.stgcode = zd_stratege.scode" +
								" 		and t_stg_rec.stkcode = t_stock.stkcode ) " +
								" order by time desc ";
			ResultSet rs = stmt.executeQuery(sql);
			List<Verifies>  vlistall = new ArrayList<Verifies>();
			while(rs.next()){
				Verifies v = new Verifies();
				v.setStgcode(rs.getString("stgcode"));
				v.setStgname(rs.getString("stgname"));
				v.setStkcode(rs.getString("stkcode"));
				v.setStkname(rs.getString("stkname"));
				v.setTime(rs.getString("time"));
				String klinefile = getKlinefile(rs.getString("stkcode"));
				v.setKlinefile(klinefile);
				vlistall.add(v);				
			}
			
			rs.close();
			/**
			 select P_START start from 
			(select P_START from t_market_day where cur_date>sysdate - interval '6' day and stkcode='000001' order by cur_date ) 
			where rownum<2 
			union
			select max(P_END) max from t_market_day where cur_date>sysdate - interval '6' day and stkcode='000001';
			 */
			List<Verifies> vlist = new ArrayList<Verifies>();
			List<Verifies> novlist = new ArrayList<Verifies>();

			int qwcount = 0;
			int qsszcount = 0;
			int tp18count = 0;
			int tp54count = 0;
			int bpcount = 0;
			int ly6count = 0;
			
			int qwcountok = 0;
			int qsszcountok = 0;
			int tp18countok = 0;
			int tp54countok = 0;
			int bpcountok = 0;
			int ly6countok = 0;
			for( int i=0;i<vlistall.size(); i++){				
				Verifies v = vlistall.get(i);
				String stkcode = v.getStkcode();
				String stg = v.getStgcode();
				if(stg.equalsIgnoreCase("qw")){
					qwcount ++;
				}else if(stg.equalsIgnoreCase("qssz")){
					qsszcount ++;
				}else if(stg.equalsIgnoreCase("buypnt")){
					bpcount ++;
				}else if(stg.equalsIgnoreCase("6ly")){
					ly6count ++;
				}else if(stg.equalsIgnoreCase("tp18")){
					tp18count ++;
				}else if(stg.equalsIgnoreCase("tp54")){
					tp54count ++;
				}
				sql =  "select P_START  "
						+ " from  (select * from t_market_day where cur_date>to_date('"+v.getTime()+"','YYYY-MM-DD') and stkcode='"+stkcode+"' order by cur_date ) " 
						+ " where rownum<2  ";
				rs = stmt.executeQuery(sql);
				float start = 0;
				if(rs.next()){
					start = rs.getFloat("P_START");
					rs.close();
				}else{
					rs.close();	
					novlist.add(v);
					continue;
				}
				
				sql = " select max(P_END) max from t_market_day where cur_date>to_date('"+v.getTime()+"','YYYY-MM-DD') + interval '1' day and stkcode='"+stkcode+"' ";
				rs = stmt.executeQuery(sql);					
				float max = 0;
				if(rs.next()){
					max = rs.getFloat("max");
					rs.close();	
				}else{
					rs.close();	
					novlist.add(v);
					continue;
				}					
				if(max > 0 && start>0) {
					Float percent = (max-start)/start * 100;
					String p = percent.toString();
					if(p.contains(".")){
						p = p.substring(0,p.indexOf(".")+1);
					}
					v.setPercent(p);
					if(percent>=3){
						vlist.add(v);						
						if(stg.equalsIgnoreCase("qw")){
							qwcountok ++;
						}else if(stg.equalsIgnoreCase("qssz")){
							qsszcountok ++;
						}else if(stg.equalsIgnoreCase("buypnt")){
							bpcountok ++;
						}else if(stg.equalsIgnoreCase("6ly")){
							ly6countok ++;
						}else if(stg.equalsIgnoreCase("tp18")){
							tp18countok ++;
						}else if(stg.equalsIgnoreCase("tp54")){
							tp54countok ++;
						}
					}
				}else{
					novlist.add(v);
				}
				
							
			}
//			for(int i=0;i<vlist.size();i++){
//				vlistall.remove(vlist.get(i));
//			}
//			for(int i=0;i<novlist.size();i++){
//				vlistall.remove(novlist.get(i));
//			}
			
			vt.setVerifieslistall(vlistall);			
			vt.setCount(vlistall.size()-novlist.size()-vlist.size());
			
			vt.setVerifieslist(vlist);
			vt.setCountok(vlist.size());
			
			vt.setLy6count(ly6count);
			vt.setQsszcount(qsszcount);
			vt.setTp18count(tp18count);
			vt.setTp54count(tp54count);			
			vt.setQwcount(qwcount);
			vt.setBpcount(bpcount);
			
			
			vt.setLy6countok(ly6countok);
			vt.setQsszcountok(qsszcountok);
			vt.setTp18countok(tp18countok);
			vt.setTp54countok(tp54countok);			
			vt.setQwcountok(qwcountok);
			vt.setBpcountok(bpcountok);
			sql = "select content  from (select * from quotes_life order by dbms_random.value) where rownum=1 ";
			rs = stmt.executeQuery(sql);
			String quotes_life = "";
			while(rs.next()){
				quotes_life = rs.getString("content");
			}
			if(null!=quotes_life && quotes_life.length()>0)
				vt.quotes_life = quotes_life;
			
			sql = "select content  from (select * from quotes_invest order by dbms_random.value) where rownum=1 ";
			rs = stmt.executeQuery(sql);
			String quotes_invest = "";
			while(rs.next()){
				quotes_invest = rs.getString("content");
			}
			if(null!=quotes_invest && quotes_invest.length()>0)
				vt.quotes_invest = quotes_invest;
			
			rs.close();
			stmt.close();
			conn.close();
			stmt = null;
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return vt;
	}
	public static void setHit(String stkcode, boolean hit) {
		try {
        	try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(true);
			stmt = conn.createStatement();
			String sql = "update t_stg_rec set hit = 1 where stkcode='"+stkcode+"' and create_date > sysdate - interval '8' hour ";
			if(!hit)
				sql = "update t_stg_rec set hit = 0 where stkcode='"+stkcode+"' and create_date > sysdate - interval '8' hour ";
			stmt.execute(sql);
			stmt.close();
			conn.close();
			stmt = null;
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
