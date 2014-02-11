package com.example.livemood.models;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

public class Artist {
	
	private String name;
	private String picture;
	private String coverPicture;
	private Label label;
	private ArrayList<Dig> digsList;
	private ArrayList<Concert> concertsList;
	private ArrayList<Mood> moodsList;
	private ArrayList<ReferenceArtist> referencesList;
	
	
	
	public Artist(String name, String picture, String coverPicture,
			Label label, ArrayList<Dig> digsList,
			ArrayList<Concert> concertsList, ArrayList<Mood> moodsList,
			ArrayList<ReferenceArtist> referencesList) {
		super();
		this.name = name;
		this.picture = picture;
		this.coverPicture = coverPicture;
		this.label = label;
		this.digsList = digsList;
		this.concertsList = concertsList;
		this.moodsList = moodsList;
		this.referencesList = referencesList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public String getCoverPicture() {
		return coverPicture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public void setCoverPicture(String coverPicture) {
		this.coverPicture = coverPicture;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public ArrayList<Dig> getDigsList() {
		return digsList;
	}

	public void setDigsList(ArrayList<Dig> digsList) {
		this.digsList = digsList;
	}

	public ArrayList<Concert> getConcertsList() {
		return concertsList;
	}

	public void setConcertsList(ArrayList<Concert> concertsList) {
		this.concertsList = concertsList;
	}

	public ArrayList<Mood> getMoodsList() {
		return moodsList;
	}

	public void setMoodsList(ArrayList<Mood> moodsList) {
		this.moodsList = moodsList;
	}

	public ArrayList<ReferenceArtist> getReferencesList() {
		return referencesList;
	}

	public void setReferencesList(ArrayList<ReferenceArtist> referencesList) {
		this.referencesList = referencesList;
	}

	@Override
	public String toString() {
		return "Artist [name=" + name + ", picture=" + picture
				+ ", coverPicture=" + coverPicture + ", label=" + label
				+ ", digsList=" + digsList + ", concertsList=" + concertsList
				+ ", moodsList=" + moodsList + ", referencesList="
				+ referencesList + "]";
	}
	
	
	
	

}
