package com.hopital.reservation.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hopital.reservation.consumers.HopitalServiceConsumer;
import com.hopital.reservation.entities.Disponibilite;
import com.hopital.reservation.entities.Reservation;
import com.hopital.reservation.repositories.ReservationRepository;
import com.hopital.reservation.services.IReservationService;

@Service
public class ReservationServiceImpl implements IReservationService{
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	HopitalServiceConsumer hopitalServiceConsumer;

	@Override
	public Reservation reserverUnLit(int hopital_id, int specialite_id, String intervenant) throws Exception {
		
		Disponibilite disponibilite = this.hopitalServiceConsumer.decrementerLits(hopital_id, specialite_id);
		if(disponibilite == null) {
			throw new Exception("Une erreur s'est produite: disponibilite = null");
		}
		Reservation reservation = new Reservation(hopital_id, specialite_id, new Date(), null, null, intervenant); 
		reservation =  this.reservationRepository.save(reservation);
		return reservation;
	}

	@Override
	public Reservation terminerReservation(int reservation_id) throws Exception {
		Reservation reservation = this.reservationRepository.findById(reservation_id).get();
		if(reservation == null) {
			throw new Exception("Aucune réservation n'a été trouvée pour l'id : " + reservation_id);
		}
		Disponibilite disponibilite = this.hopitalServiceConsumer.decrementerLits(reservation.getHopital_id(), reservation.getSpecialite_id());
		if(disponibilite == null) {
			throw new Exception("Une erreur s'est produite: disponibilite = null");
		}
		reservation.setDate_fin(new Date());
		this.reservationRepository.save(reservation);
		return reservation;
	}

	@Override
	public Reservation consulterRservation(int reservation_id) {
		return this.reservationRepository.findById(reservation_id).get();
	}
}
