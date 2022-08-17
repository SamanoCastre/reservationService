package com.hopital.reservation.unitTests.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.hopital.reservation.entities.Reservation;


@SpringBootTest
public class ReservationTest {
	
	private Reservation reservation;
	
	@BeforeEach
	public void init() {
		this.reservation = new Reservation(2, 2, new Date(), null, null, "Test");
	}
	
	@Test
	public void createReservationTest() {
		Reservation testedReservation = new Reservation();
		testedReservation.setHopital_id(this.reservation.getHopital_id());
		testedReservation.setSpecialite_id(this.reservation.getSpecialite_id());
		testedReservation.setIntervenant(this.reservation.getIntervenant());
		testedReservation.setDate_creation(this.reservation.getDate_creation());
		testedReservation.setDate_annulation(this.reservation.getDate_annulation());
		testedReservation.setDate_fin(this.reservation.getDate_fin());
		
		assertEquals(testedReservation.getHopital_id(), this.reservation.getHopital_id());
		assertEquals(testedReservation.getSpecialite_id(), this.reservation.getSpecialite_id());
		assertEquals(testedReservation.getIntervenant(), this.reservation.getIntervenant());
		assertEquals(testedReservation.getDate_creation(), this.reservation.getDate_creation());
		assertEquals(testedReservation.getDate_annulation(), this.reservation.getDate_annulation());
		assertEquals(testedReservation.getDate_fin(), this.reservation.getDate_fin());
	}

}
