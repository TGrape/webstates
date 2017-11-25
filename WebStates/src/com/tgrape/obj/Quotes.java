package com.tgrape.obj;

public class Quotes {

	private String sn ;
	private String content ;
	private String author ;
	public Quotes(String sn, String content, String author) {
		this.sn = sn;
		this.content = content;
		this.author = author;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	
}
