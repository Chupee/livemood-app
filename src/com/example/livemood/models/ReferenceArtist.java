package com.example.livemood.models;

import java.util.ArrayList;

public class ReferenceArtist {
	
	private String name;
	private ArrayList<Artist> artistsList; // Artists who are alike this ReferenceArtist.
	
	public ReferenceArtist(String name, ArrayList<Artist> artistsList) {
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
