package com.hopital.reservation.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.hopital.reservation.entities.Reservation;
import com.hopital.reservation.services.IReservationService;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationRestControllerIntegrationTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean 
	private IReservationService reservationService;
	
	private Reservation reservation;
	
	@BeforeEach
	public void init() {
		this.reservation = new Reservation(2, 2, new Date(), null, null, "Test");
	}
	
	@Test
	public void reserverLitValidTest() throws Exception {
		
		when(this.reservationService.reserverUnLit(anyInt(), anyInt(), any(String.class))).thenReturn(this.reservation);
		
		String jsonRequest = "{\"hopital_id\":"+this.reservation.getHopital_id()+",\"specialite_id\":"+this.reservation.getSpecialite_id()+",\"intervenant\":\""+this.reservation.getIntervenant()+"\"}";
		
		this.mockMvc.perform(post("/reservation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.intervenant").value("Test")); 
	}
	
	@Test
	public void reserverLitInvalidTest() throws Exception {
		
		when(this.reservationService.reserverUnLit(anyInt(), anyInt(), any(String.class))).thenReturn(this.reservation);
		
		String jsonRequest = "{\"hopital_id\":"+0+",\"specialite_id\":"+0+",\"intervenant\":\""+this.reservation.getIntervenant()+"\"}";
		
		this.mockMvc.perform(post("/reservation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist()); 
	}
	
	@Test
	public void terminerReservationValidTest() throws Exception {
		this.reservation.setReservation_id(1);
		when(this.reservationService.terminerReservation(anyInt())).thenReturn(this.reservation);
		
		this.mockMvc.perform(put("/reservation")
				.contentType(MediaType.APPLICATION_JSON)
				.param("reservation_id", this.reservation.getReservation_id() + ""))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.hopital_id").value(this.reservation.getHopital_id())); 
	}
	
	@Test
	public void terminerReservationInvalidTest() throws Exception {
		this.reservation.setReservation_id(0);
		when(this.reservationService.terminerReservation(anyInt())).thenReturn(this.reservation);
		
		this.mockMvc.perform(put("/reservation")
				.contentType(MediaType.APPLICATION_JSON)
				.param("reservation_id", this.reservation.getReservation_id() + ""))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist()); 
	}
	
}
