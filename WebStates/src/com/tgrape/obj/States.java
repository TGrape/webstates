package com.tgrape.obj;

public class States {

	private String stgcode;
	private String stgname;
	private String stkcode;
	private String stkname;
	private String turnover3;
	private String turnover6;
	private String time;
	private String klineurl = "http://image.sinajs.cn/";
	private String klinefile = "newchart/daily/n/sh000001.gif";
	
	
	public String getKlineurl() {
		return klineurl;
	}
	public void setKlineurl(String klineurl) {
		this.klineurl = klineurl;
	}
	public String getKlinefile() {
		return klinefile;
	}
	public void setKlinefile(String klinefile) {
		this.klinefile = klinefile;
	}
	public String getStgcode() {
		return stgcode;
	}
	public void setStgcode(String stgcode) {
		this.stgcode = stgcode;
	}
	public String getStgname() {
		return stgname;
	}
	public void setStgname(String stgname) {
		this.stgname = stgname;
	}
	public String getStkcode() {
		return stkcode;
	}
	public void setStkcode(String stkcode) {
		this.stkcode = stkcode;
	}
	public String getStkname() {
		return stkname;
	}
	public void setStkname(String stkname) {
		this.stkname = stkname;
	}
	public String getTurnover3() {
		return turnover3;
	}
	public void setTurnover3(String turnover3) {
		this.turnover3 = turnover3;
	}
	public String getTurnover6() {
		return turnover6;
	}
	public void setTurnover6(String turnover6) {
		this.turnover6 = turnover6;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
