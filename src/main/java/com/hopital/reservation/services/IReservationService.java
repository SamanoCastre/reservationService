package com.hopital.reservation.services;

import com.hopital.reservation.entities.Disponibilite;
import com.hopital.reservation.entities.Reservation;
import com.hopital.reservation.exceptions.ResourceNotUpdatedException;

public interface IReservationService {
	
	public Reservation reserverUnLit(int hopital_id, int specialisation_id, String intervenant) throws ResourceNotUpdatedException;
	public Reservation terminerReservation(int reservation_id) throws ResourceNotUpdatedException;
	public Disponibilite decrementerDisponibilite(int hopital_id, int specialite_id) throws ResourceNotUpdatedException;
	public Disponibilite incrementerDisponibilite(int hopital_id, int specialite_id) throws ResourceNotUpdatedException;
	public Reservation consulterReservation(int reservation_id);
}
