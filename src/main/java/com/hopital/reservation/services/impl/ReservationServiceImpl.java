package com.hopital.reservation.services.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hopital.reservation.consumers.HopitalServiceConsumer;
import com.hopital.reservation.entities.Disponibilite;
import com.hopital.reservation.entities.Reservation;
import com.hopital.reservation.exceptions.ResourceNotFoundException;
import com.hopital.reservation.exceptions.ResourceNotInsertedException;
import com.hopital.reservation.repositories.ReservationRepository;
import com.hopital.reservation.services.IReservationService;

@Service
public class ReservationServiceImpl implements IReservationService{
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private HopitalServiceConsumer hopitalServiceConsumer;

	@Override
	public Reservation reserverUnLit(int hopital_id, int specialite_id, String intervenant) throws ResourceNotInsertedException{
		
		this.decrementerDisponibilite(hopital_id, specialite_id);
		Reservation reservation = new Reservation(hopital_id, specialite_id, new Date(), null, null, intervenant); 
		reservation =  this.reservationRepository.save(reservation);
		if(reservation == null) {
			this.incrementerDisponibilite(hopital_id, specialite_id);
			throw new ResourceNotInsertedException("La réservation a échoué {hopital_id:" +hopital_id + ",specialite_id:"+specialite_id+"}");
		}
		return reservation;
	}
	
	@Override
	public Disponibilite decrementerDisponibilite(int hopital_id, int specialite_id) throws ResourceNotFoundException {
		try {
			Disponibilite disponibilite = this.hopitalServiceConsumer.decrementerLits(hopital_id, specialite_id);
			if(disponibilite == null) {
				throw new Exception();
			}
			return disponibilite;
		}
		catch(Exception e) {
			throw new ResourceNotFoundException(e.getMessage() + "\n Une erreur s'est produite: disponibilite = null {hopital_id:" +hopital_id + ",specialite_id:"+specialite_id+"}");
		}
	}
	
	@Override
	public Disponibilite incrementerDisponibilite(int hopital_id, int specialite_id) throws ResourceNotFoundException {
		try {
			Disponibilite disponibilite = this.hopitalServiceConsumer.incrementerLits(hopital_id, specialite_id);
			if(disponibilite == null) {
				throw new Exception();
			}
			return disponibilite;
		}
		catch(Exception e) {
			throw new ResourceNotFoundException(e.getMessage() + "\n Une erreur s'est produite: disponibilite = null {hopital_id:" +hopital_id + ",specialite_id:"+specialite_id+"}");
		}
	}
}
