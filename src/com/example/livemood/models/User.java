package com.example.livemood.models;

import java.util.ArrayList;

public class User {
	
	private String login;
	private String password;
	private String email;
	private ArrayList<Artist> artistsList; // Artists that the user follows
	private ArrayList<Digger> diggersList; // Diggers that the user follows
	private ArrayList<Concert> concertsList; // Concerts where the user went/is going to
	
	public User(String login, String password, String email,
			ArrayList<Artist> artistsList, ArrayList<Digger> diggersList,
			ArrayList<Concert> concertsList) {
		super();
		this.login = login;
		this.password = password;
		this.email = email;
		this.artistsList = artistsList;
		this.diggersList = diggersList;
		this.concertsList = concertsList;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Artist> getArtistsList() {
		return artistsList;
	}

	public void setArtistsList(ArrayList<Artist> artistsList) {
		this.artistsList = artistsList;
	}

	public ArrayList<Digger> getDiggersList() {
		return diggersList;
	}

	public void setDiggersList(ArrayList<Digger> diggersList) {
		this.diggersList = diggersList;
	}

	public ArrayList<Concert> getConcertsList() {
		return concertsList;
	}

	public void setConcertsList(ArrayList<Concert> concertsList) {
		this.concertsList = concertsList;
	}
	
	
	

}
