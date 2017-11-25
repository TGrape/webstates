package com.tgrape.obj;

import java.util.ArrayList;
import java.util.List;

public class QuotesAll {

	private int count = 0;
	private List<Quotes> quoteslist = new ArrayList<Quotes>();

	
	
	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public List<Quotes> getQuoteslist() {
		return quoteslist;
	}



	public void setQuoteslist(List<Quotes> quoteslist) {
		this.quoteslist = quoteslist;
	}



	public void add(Quotes q){
		this.quoteslist.add(q);
		this.count = this.quoteslist.size();
	}
}
