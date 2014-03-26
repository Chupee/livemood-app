package com.example.livemood.models;


public class Dig {
	
	private Digger digger;
	private String text;
	private int score;			 // 1, 2 or 3 hearts ?
	
	
	public Dig(Digger digger, String text, int score) {
		super();
		this.digger = digger;
		this.text = text;
		this.score = score;
	}

	public Digger getDigger() {
		return digger;
	}

	public void setDigger(Digger digger) {
		this.digger = digger;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	

}
