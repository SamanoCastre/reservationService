package com.hopital.reservation.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.hopital.reservation.consumers.HopitalServiceConsumer;
import com.hopital.reservation.entities.Address;
import com.hopital.reservation.entities.Disponibilite;
import com.hopital.reservation.entities.Hopital;
import com.hopital.reservation.entities.Reservation;
import com.hopital.reservation.entities.Specialite;
import com.hopital.reservation.exceptions.DecrementationFailException;
import com.hopital.reservation.exceptions.IncrementationFailException;
import com.hopital.reservation.exceptions.ReservationFailException;
import com.hopital.reservation.exceptions.ReservationNotFoundException;
import com.hopital.reservation.repositories.ReservationRepository;
import com.hopital.reservation.services.impl.ReservationServiceImpl;


@SpringBootTest
public class ReservationServiceTest {
	
	@Mock
	private HopitalServiceConsumer hopitalServiceConsumer;
	
	@Mock
	private ReservationRepository reservationRepository;
	
	@InjectMocks
	private IReservationService reservationService = new ReservationServiceImpl();
	
	private Reservation reservation;
	private Disponibilite disponibilite;
	
	@BeforeEach
	public void init() {
		this.reservation = new Reservation(2, 2, new Date(), null, null, "Test_Reservation");
		this.disponibilite = new Disponibilite(1,new Hopital(2, "Test_Disponibilite", new Address("1","place du Docteur Baylac","31059","Toulouse","France"), new Date()), new Specialite(2, "Anethsesie", "Anethsésie", new Date()), 15, new Date());
		
	}
	
	@Test
	public void incrementerDisponibiliteValidTest() throws Exception {
		when(this.hopitalServiceConsumer.incrementerLits(anyInt(), anyInt())).thenReturn(this.disponibilite);
		assertEquals(this.reservationService.incrementerDisponibilite(this.reservation.getHopital_id(), this.reservation.getSpecialite_id()), this.disponibilite);
	}
	
	@Test
	public void incrementerDisponibiliteInvalidTest() {

		when(this.hopitalServiceConsumer.incrementerLits(anyInt(), anyInt())).thenReturn(null);
		assertThrows(IncrementationFailException.class, 
				() -> { this.reservationService.incrementerDisponibilite(this.reservation.getHopital_id(), this.reservation.getSpecialite_id());});
	}
	
	@Test
	public void decrementerDisponibiliteValidTest() throws Exception {
		when(this.hopitalServiceConsumer.decrementerLits(anyInt(), anyInt())).thenReturn(this.disponibilite);
		assertEquals(this.reservationService.decrementerDisponibilite(this.reservation.getHopital_id(), this.reservation.getSpecialite_id()), this.disponibilite);
	}
	
	@Test
	public void decrementerDisponibiliteInvalidTest() {

		when(this.hopitalServiceConsumer.decrementerLits(anyInt(), anyInt())).thenReturn(null);
		assertThrows(DecrementationFailException.class, 
				() -> { this.reservationService.decrementerDisponibilite(this.reservation.getHopital_id(), this.reservation.getSpecialite_id());});
	}
	
	@Test
	public void reserverUnLitDecrementationFailExceptionTest() throws Exception {
		when(this.reservationRepository.save(any(Reservation.class))).thenReturn(this.reservation);
		assertThrows(
				DecrementationFailException.class, 
				() -> {this.reservationService.reserverUnLit(3, 3, "Samano_show");});
	}
	
	@Test
	public void reserverUnLitReservationNotFoundExceptionTest() throws Exception {
		when(this.hopitalServiceConsumer.decrementerLits(anyInt(), anyInt())).thenReturn(this.disponibilite);
		assertThrows(
				IncrementationFailException.class, 
				() -> {this.reservationService.reserverUnLit(3, 3, "Samano_show");});
	}
	
	@Test
	public void consulterReservationReservationNotFoundExceptionTest() throws Exception {
		
		//when(this.reservationService.consulterReservation(anyInt())).thenReturn(this.reservation);
		assertThrows(
				ReservationNotFoundException.class, 
				() -> {this.reservationService.consulterReservation(0);});
	}
	
	@Test
	public void consulterReservationValidTest() throws Exception {
		
		Optional<Reservation> reservationOptional = Optional.of(this.reservation);
		when(this.reservationRepository.findById(anyInt())).thenReturn(reservationOptional);
		assertEquals(this.reservationService.consulterReservation(0), this.reservation);
	}
}
