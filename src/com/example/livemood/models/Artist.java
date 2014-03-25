package com.example.livemood.models;


public class Artist {
	
	private String name;
	private String picture;
	private String coverPicture;
	private Label label;
	
	public Artist(String name, String picture, String coverPicture,
			Label label) {
		super();
		this.name = name;
		this.picture = picture;
		this.coverPicture = coverPicture;
		this.label = label;
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

	@Override
	public String toString() {
		return "Artist [name=" + name + ", picture=" + picture
				+ ", coverPicture=" + coverPicture + ", label=" + label
				+ "]";
	}
	
	
	
	

}
