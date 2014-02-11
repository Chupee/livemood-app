package com.example.livemood.models;

import android.text.format.DateFormat;

public class Concert {
	
	private Artist artist;
	private Place place;
	private DateFormat date;
	
	

	public Concert(Artist artist, Place place, DateFormat date) {
		super();
		this.artist = artist;
		this.place = place;
		this.date = date;
	}


	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public DateFormat getDate() {
		return date;
	}

	public void setDate(DateFormat date) {
		this.date = date;
	}

	
	
	
}
