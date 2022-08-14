package com.hopital.reservation.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hopital.reservation.consumers.HopitalServiceConsumer;
import com.hopital.reservation.entities.Disponibilite;
import com.hopital.reservation.entities.Reservation;
import com.hopital.reservation.exceptions.DecrementationFailException;
import com.hopital.reservation.exceptions.IncrementationFailException;
import com.hopital.reservation.exceptions.ReservationFailException;
import com.hopital.reservation.exceptions.ReservationNotFoundException;
import com.hopital.reservation.repositories.ReservationRepository;
import com.hopital.reservation.services.IReservationService;

@Service
public class ReservationServiceImpl implements IReservationService{
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private HopitalServiceConsumer hopitalServiceConsumer;

	@Override
	public Reservation reserverUnLit(int hopital_id, int specialite_id, String intervenant) throws Exception {
		 
		try {
			this.decrementerDisponibilite(hopital_id, specialite_id);
			try {
				Reservation reservation = new Reservation(hopital_id, specialite_id, new Date(), null, null, intervenant); 
				reservation =  this.reservationRepository.save(reservation);
				if(reservation == null) {
					throw new ReservationFailException("La réservation a échoué {hopital_id:" +hopital_id + ",specialite_id:"+specialite_id+"}");
				}
				return reservation;
			}
			catch(Exception e) {
				this.incrementerDisponibilite(hopital_id, specialite_id);
				throw e;
			}
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	@Override
	public Disponibilite decrementerDisponibilite(int hopital_id, int specialite_id) throws Exception {
		 
		try {
			Disponibilite disponibilite = this.hopitalServiceConsumer.decrementerLits(hopital_id, specialite_id);
			if(disponibilite== null) {
				throw new DecrementationFailException("Une erreur s'est produite: disponibilite = null {hopital_id:" +hopital_id + ",specialite_id:"+specialite_id+"}");
			}
			return disponibilite;
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	@Override
	public Disponibilite incrementerDisponibilite(int hopital_id, int specialite_id) throws Exception {
		 
		try {
			Disponibilite disponibilite = this.hopitalServiceConsumer.incrementerLits(hopital_id, specialite_id);
			if(disponibilite== null) {
				throw new IncrementationFailException("Une erreur s'est produite: disponibilite = null {hopital_id:" +hopital_id + ",specialite_id:"+specialite_id+"}");
			}
			return disponibilite;
		}
		catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Reservation terminerReservation(int reservation_id) throws Exception {
		
		try {
			Reservation reservation = this.consulterReservation(reservation_id);
			this.incrementerDisponibilite(reservation_id, reservation_id);
			try {
				reservation.setDate_fin(new Date());
				this.reservationRepository.save(reservation);
				return reservation;
			}
			catch(Exception e) {
				this.decrementerDisponibilite(reservation_id, reservation_id);
				throw new ReservationFailException("La réservation a échoué {reservation_id:" +reservation_id+"}");
			}
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	@Override
	public Reservation consulterReservation(int reservation_id) throws Exception {
		try {
			Optional<Reservation> reservationOptional = this.reservationRepository.findById(reservation_id);
			if(reservationOptional.isEmpty()) {
				throw new ReservationNotFoundException("Aucune réservation n'a été trouvée pour l'id : " + reservation_id);
			}
			return reservationOptional.get();
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	
}
