package com.hopital.reservation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping(path="/reservation", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> reserverLitHopital(@RequestBody  ReservationDTO request) {
		
		try {
			if(!request.valid()) {
				throw new Exception("Au moins un champ mal renseigné");
			}
			return new ResponseEntity<Reservation>(this.reservationService.reserverUnLit(request.getHopital_id(), request.getSpecialite_id(), request.getIntervenant()), HttpStatus.CREATED);
		}
		catch(Exception e) {
			this.logger.error(e.getMessage());
			return new ResponseEntity<Reservation>(HttpStatus.NOT_MODIFIED);
		}
	}
	
	@PutMapping(path="/reservation", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> terminerReservation(@RequestParam int reservation_id) {
		
		try {
			if(reservation_id < 1) {
				throw new Exception("id réservation inférieur ou égal à 0");
			}
			return new ResponseEntity<Reservation>(this.reservationService.terminerReservation(reservation_id), HttpStatus.CREATED);
		}
		catch(Exception e) {
			this.logger.error(e.getMessage());
			return new ResponseEntity<Reservation>(HttpStatus.NOT_MODIFIED);
		}
	}
}
