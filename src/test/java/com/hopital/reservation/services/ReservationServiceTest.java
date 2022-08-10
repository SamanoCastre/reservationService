package com.hopital.reservation.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hopital.reservation.consumers.HopitalServiceConsumer;
import com.hopital.reservation.dtos.ReservationDTO;
import com.hopital.reservation.entities.Address;
import com.hopital.reservation.entities.Disponibilite;
import com.hopital.reservation.entities.Hopital;
import com.hopital.reservation.entities.Reservation;
import com.hopital.reservation.entities.Specialite;
import com.hopital.reservation.repositories.ReservationRepository;
import com.hopital.reservation.services.impl.ReservationServiceImpl;


@SpringBootTest
public class ReservationServiceTest {
	
	@MockBean
	private ReservationRepository reservationRepository;
	
	@Autowired
	private HopitalServiceConsumer hopitalServiceConsumer;
	
	@InjectMocks
	private IReservationService reservationService = new ReservationServiceImpl();
	
	private Reservation reservation;
	private Disponibilite disponibilite;
	
	@BeforeEach
	public void init() {
		this.reservation = new Reservation(2, 2, new Date(), null, null, "Test_Reservation");
		this.disponibilite = new Disponibilite(1,new Hopital(2, "Test_Disponibilite", new Address("1","place du Docteur Baylac","31059","Toulouse","France"), new Date()), new Specialite(2, "Anethsesie", "Anethsésie", new Date()), 15, new Date());
		
	}
/*
	@Test
	public void reserverLitHopitalTest() throws Exception {
		when(this.hopitalServiceConsumer.decrementerLits(anyInt(), anyInt())).thenReturn(this.disponibilite);
		when(this.reservationRepository.save(any(Reservation.class))).thenReturn(this.reservation);
		assertEquals(this.reservationService.reserverUnLit(
				this.reservation.getHopital_id(), 
				this.reservation.getSpecialite_id(), 
				this.reservation.getIntervenant()), 
				this.reservation);
	}*/
}
