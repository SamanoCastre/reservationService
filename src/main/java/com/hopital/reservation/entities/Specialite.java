package com.hopital.reservation.entities;

import java.util.Date;

public class Specialite {
	
	private int id;
	private String name;
	private String groupe;
	private Date dateCreation;
	
	public Specialite() {
		
	}
	
	public Specialite(int id, String groupe, String name, Date dateCreation) {
		this.name = name;
		this.groupe = groupe;
		this.dateCreation = dateCreation;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
}
