package com.tgrape.obj;

public class Verifies {

	private String stgcode;
	private String stgname;
	private String stkcode;
	private String stkname;
	private String time;

	private String klineurl = "http://image.sinajs.cn/";
	private String klinefile = "newchart/daily/n/sh000001.gif";
	private String percent;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
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
	
	
	public void setPercent(String p) {
		this.percent = p;
	}
	public String getPercent() {
		return this.percent;
	}
}
