package com.hopital.reservation.unitTests.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.hopital.reservation.dtos.ReservationDTO;

@SpringBootTest
public class ReservationDTOTest {
	
	private ReservationDTO reservationDTO;
	
	@BeforeEach
	public void init() {
		this.reservationDTO = new ReservationDTO(2, 2, "Test");
	}
	
	@Test
	public void createAddressTest() {
		ReservationDTO testReservationDTO = new ReservationDTO();
		
		testReservationDTO.setHopital_id(this.reservationDTO.getHopital_id());
		testReservationDTO.setSpecialite_id(this.reservationDTO.getSpecialite_id());
		testReservationDTO.setIntervenant(this.reservationDTO.getIntervenant());
		
		assertEquals(testReservationDTO.getHopital_id(), this.reservationDTO.getHopital_id());
		assertEquals(testReservationDTO.getSpecialite_id(), this.reservationDTO.getSpecialite_id());
		assertEquals(testReservationDTO.getIntervenant(), this.reservationDTO.getIntervenant());
		assertEquals(testReservationDTO.valid(), this.reservationDTO.valid());
	}

}
