package com.urgence.reservation.entities;

import java.util.Date;

public class Disponibilite {
	
	private int id;
	public Hopital hopital;
	public Specialite specialite;
	public int lits;
	public Date dateCreation;
	public Date dateMiseAJour;
	
	public Disponibilite() {
		
	}
	
	public Disponibilite(int id, Hopital hopital, Specialite specialite, int lits, Date dateCreation) {
		this.hopital = hopital;
		this.specialite = specialite;
		this.lits = lits;
		this.dateCreation = dateCreation;
		this.id = id;
	}

	public Hopital getHopital() {
		return hopital;
	}

	public void setHopital(Hopital hopital) {
		this.hopital = hopital;
	}

	public Specialite getSpecialite() {
		return specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	public int getLits() {
		return lits;
	}

	public void setLits(int lits) {
		this.lits = lits;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateMiseAJour() {
		return dateMiseAJour;
	}

	public void setDateMiseAJour(Date dateMiseAJour) {
		this.dateMiseAJour = dateMiseAJour;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
