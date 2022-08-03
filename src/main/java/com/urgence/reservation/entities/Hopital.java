package com.urgence.reservation.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Hopital {
	
	private int id;
	private String name;
	private Address address;
	private Date dateCreation;

	public Hopital() {
		
	}
	/**
	 * @param name
	 * @param address
	 * @param lits
	 */
	public Hopital(int id, String name, Address address, Date dateCreation) {
		this.name = name;
		this.address = address;
		this.id = id;
		this.dateCreation = dateCreation;
	}
	@JsonProperty("id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	@JsonProperty("dateCreation")
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	@Override
	public String toString() {
		return "Hopital [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
}
