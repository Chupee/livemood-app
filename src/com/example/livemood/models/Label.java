package com.example.livemood.models;


public class Label {
	
	private String name;
	private String icon;
	
	public Label(String name, String icon) {
		super();
		this.name = name;
		this.icon = icon;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	

}
