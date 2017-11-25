package com.tgrape.obj;

import java.util.ArrayList;
import java.util.List;

public class VerifiesToday {

	private int count = 0;
	private int countok = 0; 
	private List<Verifies> verifieslist = new ArrayList<Verifies>();
	private List<Verifies> verifieslistall = new ArrayList<Verifies>();

	public String quotes_life = "人生永无止境";	
	public String quotes_invest = "投机永无止境";
	
	private int qwcountok = 0;
	private int qsszcountok = 0;
	private int tp18countok = 0;
	private int tp54countok = 0;
	private int bpcountok = 0;
	private int ly6countok = 0;
	
	
	private int qwcount = 0;
	private int qsszcount = 0;
	private int tp18count = 0;
	private int tp54count = 0;
	private int bpcount = 0;
	private int ly6count = 0;
	
	public List<Verifies> getVerifieslist() {
		return verifieslist;
	}
	public void setVerifieslist(List<Verifies> verifieslist) {
		this.verifieslist = verifieslist;
	}
	public int getQwcountok() {
		return qwcountok;
	}
	public void setQwcountok(int qwcountok) {
		this.qwcountok = qwcountok;
	}
	public int getQsszcountok() {
		return qsszcountok;
	}
	public void setQsszcountok(int qsszcountok) {
		this.qsszcountok = qsszcountok;
	}
	public int getTp18countok() {
		return tp18countok;
	}
	public void setTp18countok(int tp18countok) {
		this.tp18countok = tp18countok;
	}
	public int getTp54countok() {
		return tp54countok;
	}
	public void setTp54countok(int tp54countok) {
		this.tp54countok = tp54countok;
	}
	public int getBpcountok() {
		return bpcountok;
	}
	public void setBpcountok(int bpcountok) {
		this.bpcountok = bpcountok;
	}
	public int getLy6countok() {
		return ly6countok;
	}
	public void setLy6countok(int ly6countok) {
		this.ly6countok = ly6countok;
	}

	
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
	
	
	
	public String getQuotes_life() {
		return quotes_life;
	}
	public void setQuotes_life(String quotes_life) {
		this.quotes_life = quotes_life;
	}
	public int getCountok() {
		return countok;
	}
	public void setCountok(int countok) {
		this.countok = countok;
	}
	public int getQwcount() {
		return qwcount;
	}
	public void setQwcount(int qwcount) {
		this.qwcount = qwcount;
	}
	public int getQsszcount() {
		return qsszcount;
	}
	public void setQsszcount(int qsszcount) {
		this.qsszcount = qsszcount;
	}
	public int getTp18count() {
		return tp18count;
	}
	public void setTp18count(int tp18count) {
		this.tp18count = tp18count;
	}
	public int getTp54count() {
		return tp54count;
	}
	public void setTp54count(int tp54count) {
		this.tp54count = tp54count;
	}
	public int getBpcount() {
		return bpcount;
	}
	public void setBpcount(int bpcount) {
		this.bpcount = bpcount;
	}
	public int getLy6count() {
		return ly6count;
	}
	public void setLy6count(int ly6count) {
		this.ly6count = ly6count;
	}
	public void setVerifieslistall(List<Verifies> vlistall) {
		this.verifieslistall = vlistall;
	}
	
	public List<Verifies> getVerifieslistall() {
		return this.verifieslistall ;
	}
	
}
