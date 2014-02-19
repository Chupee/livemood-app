package com.example.livemood.models;


public class Concert {
	
	private int id;
	private Artist artist;
	private Place place;
	private String date;
	private String image;
	
	
	public Concert(int id, Artist artist, Place place, String date, String image) {
		super();
		this.id = id;
		this.artist = artist;
		this.place = place;
		this.date = date;
		this.image = image;
	}

	public int getId() {
		return id;
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
