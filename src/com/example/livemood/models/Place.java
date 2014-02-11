package com.example.livemood.models;

import java.util.ArrayList;

public class Place {
	
	private String name;
	private String address;
	private ArrayList<Concert> concertsList;
	
	
	public Place(String name, String address, ArrayList<Concert> concertsList) {
		super();
		this.name = name;
		this.address = address;
		this.concertsList = concertsList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Concert> getConcertsList() {
		return concertsList;
	}

	public void setConcertsList(ArrayList<Concert> concertsList) {
		this.concertsList = concertsList;
	}
	
	

}
