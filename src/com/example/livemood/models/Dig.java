package com.example.livemood.models;

import android.text.format.DateFormat;

public class Dig {
	
	private Digger digger;
	private String title;
	private String content;
	private DateFormat date;
	private int hearts;			 // 1, 2 or 3 hearts ?
	
	
	public Dig(Digger digger, String title, String content, DateFormat date,
			int hearts) {
		super();
		this.digger = digger;
		this.title = title;
		this.content = content;
		this.date = date;
		this.hearts = hearts;
	}

	public Digger getDigger() {
		return digger;
	}

	public void setDigger(Digger digger) {
		this.digger = digger;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public DateFormat getDate() {
		return date;
	}

	public void setDate(DateFormat date) {
		this.date = date;
	}

	public int getHearts() {
		return hearts;
	}

	public void setHearts(int hearts) {
		this.hearts = hearts;
	}
	
	
	

}
