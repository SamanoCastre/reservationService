package com.hopital.reservation.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.hopital.reservation.consumers.HopitalServiceConsumer;
import com.hopital.reservation.entities.Disponibilite;
import com.hopital.reservation.entities.Reservation;
import com.hopital.reservation.exceptions.ResourceNotInsertedException;
import com.hopital.reservation.exceptions.ResourceNotUpdatedException;
import com.hopital.reservation.repositories.ReservationRepository;
import com.hopital.reservation.services.IReservationService;

@Service
public class ReservationServiceImpl implements IReservationService{
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private HopitalServiceConsumer hopitalServiceConsumer;

	@Override
	public Reservation reserverUnLit(int hopital_id, int specialite_id, String intervenant) throws ResourceNotUpdatedException{
		
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
	public Disponibilite decrementerDisponibilite(int hopital_id, int specialite_id) throws ResourceNotUpdatedException {
		 
		Disponibilite disponibilite = this.hopitalServiceConsumer.decrementerLits(hopital_id, specialite_id);
		if(disponibilite== null) {
			throw new ResourceNotUpdatedException("Une erreur s'est produite: disponibilite = null {hopital_id:" +hopital_id + ",specialite_id:"+specialite_id+"}");
		}
		return disponibilite;
	}
	
	@Override
	public Disponibilite incrementerDisponibilite(int hopital_id, int specialite_id) throws ResourceNotUpdatedException {
		 Disponibilite disponibilite = this.hopitalServiceConsumer.incrementerLits(hopital_id, specialite_id);
		if(disponibilite== null) {
			throw new ResourceNotUpdatedException("Une erreur s'est produite: disponibilite = null {hopital_id:" +hopital_id + ",specialite_id:"+specialite_id+"}");
		}
		return disponibilite;
	}

	@Override
	public Reservation terminerReservation(int reservation_id) throws ResourceNotUpdatedException {
		Reservation reservation = this.consulterReservation(reservation_id);
		this.incrementerDisponibilite(reservation_id, reservation_id);
		reservation.setDate_fin(new Date());
		reservation = this.reservationRepository.save(reservation);
		if(reservation == null) {
			this.decrementerDisponibilite(reservation_id, reservation_id);
			throw new ResourceNotUpdatedException("La réservation a échoué {reservation_id:" +reservation_id+"}");
		}
		return reservation;
	}
	
	@Override
	public Reservation consulterReservation(int reservation_id) throws ResourceNotFoundException {
		Optional<Reservation> reservationOptional = this.reservationRepository.findById(reservation_id);
		if(reservationOptional.isEmpty()) {
			throw new ResourceNotFoundException("Aucune réservation n'a été trouvée pour l'id : " + reservation_id);
		}
		return reservationOptional.get();
	}
}
