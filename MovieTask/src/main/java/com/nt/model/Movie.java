package com.nt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;



@Entity
public class Movie implements Serializable{
	@Id
	private String title;
	 private float year;
	 
	 @ElementCollection
	 @CollectionTable(name = "cast", joinColumns = @JoinColumn(name = "title"))
	 List<String> cast = new ArrayList<> ();
	 
	 @ElementCollection
	 @CollectionTable(name = "genre", joinColumns = @JoinColumn(name = "title"))
	 List<String> genres = new ArrayList<> ();

	 // Getter Methods 
	 public String getTitle() {
	  return title;
	 }
	 public float getYear() {
	  return year;
	 }
	 // Setter Methods 
	 public void setTitle(String title) {
	  this.title = title;
	 }
	 public void setYear(float year) {
	  this.year = year;
	 }
	public List<String> getCast() {
		return cast;
	}
	public void setCast(List<String> cast) {
		this.cast = cast;
	}
	public List<String> getGenres() {
		return genres;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	
	 
}

