package com.example.livemood.models;

import java.util.ArrayList;

public class Mood {
	
	private String name;
	private ArrayList<Artist> artistsList;
	
	
	public Mood(String name, ArrayList<Artist> artistsList) {
		super();
		this.name = name;
		this.artistsList = artistsList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Artist> getArtistsList() {
		return artistsList;
	}

	public void setArtistsList(ArrayList<Artist> artistsList) {
		this.artistsList = artistsList;
	}
	
	
	

}
