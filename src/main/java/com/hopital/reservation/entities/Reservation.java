package com.hopital.reservation.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reservation_id;
	private int hopital_id;
	private int specialite_id;
	private Date date_annulation;
	private Date date_creation;
	private Date date_fin;
	private String intervenant;
	
	public Reservation() {
		
	}
	
	public Reservation(int hopital_id, int specialite_id,Date date_creation, Date date_annulation, 
			Date date_fin, String intervenant) {
		
		this.hopital_id = hopital_id;
		this.specialite_id = specialite_id;
		this.date_annulation = date_annulation;
		this.date_creation = date_creation;
		this.date_fin = date_fin;
		this.intervenant = intervenant;
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public int getHopital_id() {
		return hopital_id;
	}

	public void setHopital_id(int hopital_id) {
		this.hopital_id = hopital_id;
	}

	public int getSpecialite_id() {
		return specialite_id;
	}

	public void setSpecialite_id(int specialite_id) {
		this.specialite_id = specialite_id;
	}

	public Date getDate_annulation() {
		return date_annulation;
	}

	public void setDate_annulation(Date date_annulation) {
		this.date_annulation = date_annulation;
	}

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public String getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}
}
