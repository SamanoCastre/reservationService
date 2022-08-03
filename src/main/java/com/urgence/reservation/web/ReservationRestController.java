package com.urgence.reservation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.urgence.reservation.dtos.ReservationDTO;
import com.urgence.reservation.entities.Reservation;
import com.urgence.reservation.services.IReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("finally")
@RestController
public class ReservationRestController {
	Logger logger = LoggerFactory.getLogger(ReservationRestController.class);
	
	@Autowired
	private IReservationService reservationService;
	
	@PostMapping("/reservation")
	public Reservation reserverLitHopital(@RequestBody  ReservationDTO request) {
		Reservation reservation = null;
		try {
			reservation = this.reservationService.reserverUnLit(request.getHopital_id(), request.getSpecialite_id(), request.getIntervenant());
		}
		catch(Exception e) {
			this.logger.error(e.getMessage());
		}
		finally {
			return reservation;
		}
	}
	
	@PutMapping("/reservation")
	public Reservation terminerReservation(@RequestParam int reservation_id) {
		Reservation reservation = null;
		try {
			if(reservation_id <= 0) {
				throw new Exception("id réservation inférieur ou égal à 0");
			}
			reservation = this.reservationService.terminerReservation(reservation_id);
		}
		catch(Exception e) {
			this.logger.error(e.getMessage());
		}
		finally {
			return reservation;
		}
	}
	
	@GetMapping("/reservation/{reservation_id}")
	public Reservation consulterReservation(@RequestParam("reservation_id") int reservation_id) {
		Reservation reservation = null;
		try {
			if(reservation_id <= 0) {
				throw new Exception("id réservation inférieur ou égal à 0");
			}
			reservation = this.reservationService.consulterRservation(reservation_id);
		}
		catch(Exception e) {
			this.logger.error(e.getMessage());
		}
		finally {
			return reservation;
		}
	}
}
