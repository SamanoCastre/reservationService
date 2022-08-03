package com.urgence.reservation.services;

import com.urgence.reservation.entities.Reservation;

public interface IReservationService {
	
	public Reservation reserverUnLit(int hopital_id, int specialisation_id, String intervenant) throws Exception;

	public Reservation terminerReservation(int reservation_id) throws Exception;
	
	public Reservation consulterRservation(int reservation_id);

}
