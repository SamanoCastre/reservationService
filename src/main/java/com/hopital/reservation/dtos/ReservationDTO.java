package com.hopital.reservation.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ReservationDTO {
	
	@Min(1)
	private int hopital_id;
	
	@Min(1)
	private int specialite_id;
	
	@NotEmpty
	private String intervenant;
	
	public ReservationDTO(int hopital_id, int specialite_id, String intervenant) {
		super();
		this.hopital_id = hopital_id;
		this.specialite_id = specialite_id;
		this.intervenant = intervenant;
	}
	public ReservationDTO() {
		
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
	public String getIntervenant() {
		return intervenant;
	}
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}
	
}
