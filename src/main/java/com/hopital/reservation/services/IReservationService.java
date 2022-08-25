package com.hopital.reservation.services;

import com.hopital.reservation.entities.Disponibilite;
import com.hopital.reservation.entities.Reservation;
import com.hopital.reservation.exceptions.ResourceNotFoundException;
import com.hopital.reservation.exceptions.ResourceNotInsertedException;

public interface IReservationService {
	
	public Reservation reserverUnLit(int hopital_id, int specialisation_id, String intervenant) throws ResourceNotInsertedException;
	public Disponibilite decrementerDisponibilite(int hopital_id, int specialite_id) throws ResourceNotFoundException;
	public Disponibilite incrementerDisponibilite(int hopital_id, int specialite_id) throws ResourceNotFoundException;
}
