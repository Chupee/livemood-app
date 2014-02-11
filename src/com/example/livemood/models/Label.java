package com.example.livemood.models;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

public class Label {
	
	private String name;
	private String icon;
	private ArrayList<Artist> artistsList;
	
	
	public Label(String name, String icon, ArrayList<Artist> artistsList) {
		super();
		this.name = name;
		this.icon = icon;
		this.artistsList = artistsList;
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

	public ArrayList<Artist> getArtistsList() {
		return artistsList;
	}

	public void setArtistsList(ArrayList<Artist> artistsList) {
		this.artistsList = artistsList;
	}
	
	

}
