package com.hopital.reservation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hopital.reservation.dtos.ReservationDTO;
import com.hopital.reservation.entities.Reservation;
import com.hopital.reservation.services.IReservationService;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Validated
@RestController
public class ReservationRestController {
	Logger logger = LoggerFactory.getLogger(ReservationRestController.class);
	
	@Autowired
	private IReservationService reservationService;
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws ResourceNotUpdatedException
	 */
	@PostMapping(path="/reservation", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> reserverLitHopital(
			@RequestBody  @Valid ReservationDTO request) throws RuntimeException {
			return new ResponseEntity<Reservation>(this.reservationService.reserverUnLit(request.getHopital_id(), request.getSpecialite_id(), request.getIntervenant()), HttpStatus.CREATED);
	}
}
