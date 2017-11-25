package com.tgrape.obj;

import java.util.ArrayList;
import java.util.List;

public class StatesToday {

	private int count = 0;
	private List<States> stateslist = new ArrayList<States>();
	public String quotes_life = "人生永无止境";	
	public String quotes_invest = "投机永无止境";
	
	
	
	public String getQuotes_invest() {
		return quotes_invest;
	}
	public void setQuotes_invest(String quotes_invest) {
		this.quotes_invest = quotes_invest;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<States> getStateslist() {
		return stateslist;
	}
	public void setStateslist(List<States> stateslist) {
		this.stateslist = stateslist;
	}
	public void refreshcout() {
		this.setCount(this.getStateslist().size());
	}
	
	public String getQuotes_life() {
		return quotes_life;
	}
	public void setQuotes_life(String quotes_life) {
		this.quotes_life = quotes_life;
	}
	
}
