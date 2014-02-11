package com.example.livemood.models;

import android.text.format.DateFormat;

public class Concert {
	
	private Artist artist;
	private Place place;
	private String date;
	private String image;
	
	
	public Concert(Artist artist, Place place, String date, String image) {
		super();
		this.artist = artist;
		this.place = place;
		this.date = date;
		this.image = image;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	

	
	
	
}
