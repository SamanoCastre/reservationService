package com.hopital.reservation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hopital.reservation.dtos.ReservationDTO;
import com.hopital.reservation.entities.Reservation;
import com.hopital.reservation.services.IReservationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class ReservationRestController {
	Logger logger = LoggerFactory.getLogger(ReservationRestController.class);
	
	@Autowired
	private IReservationService reservationService;
	
	@PostMapping("/reservation")
	public Reservation reserverLitHopital(@RequestBody  ReservationDTO request) {
		
		try {
			if(!request.valid()) {
				throw new Exception("Au moins un champ mal renseigné");
			}
			return this.reservationService.reserverUnLit(request.getHopital_id(), request.getSpecialite_id(), request.getIntervenant());
		}
		catch(Exception e) {
			this.logger.error(e.getMessage());
		}
		return null;
	}
	
	@PutMapping("/reservation")
	public Reservation terminerReservation(@RequestParam int reservation_id) {
		
		try {
			if(reservation_id < 1) {
				throw new Exception("id réservation inférieur ou égal à 0");
			}
			return this.reservationService.terminerReservation(reservation_id);
		}
		catch(Exception e) {
			this.logger.error(e.getMessage());
		}
		return null;
	}
}
