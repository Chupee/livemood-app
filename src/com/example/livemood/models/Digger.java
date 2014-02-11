package com.example.livemood.models;

import java.util.ArrayList;

public class Digger {
	
	private User user;
	private ArrayList<Dig> digsList; // Digs that the diggers digged.
	
	
	public Digger(User user, ArrayList<Dig> digsList) {
		super();
		this.user = user;
		this.digsList = digsList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<Dig> getDigsList() {
		return digsList;
	}

	public void setDigsList(ArrayList<Dig> digsList) {
		this.digsList = digsList;
	}
	
	

}
