package com.hopital.reservation.acceptanceTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class ReservationRestControllerAcceptanceTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void reserverLitShouldReturnNotFoundException() throws Exception {
		
		String jsonRequest = "{\"hopital_id\":"+0+",\"specialite_id\":"+0+",\"intervenant\":\"Test\"}";
		
		this.mockMvc.perform(post("/reservation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isNotFound()); 
	}
	
	@Test
	public void reserverLitShouldReturnAReservation() throws Exception {
		
		String jsonRequest = "{\"hopital_id\":"+2+",\"specialite_id\":"+2+",\"intervenant\":\"Test\"}";
		
		this.mockMvc.perform(post("/reservation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.hopital_id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.specialite_id").value(2)); 
	}
	
	@Test //Cas lit indisponible, la bdd doit avoir 0 en nombre de lits
	public void reserverLitShouldReturnResourceNotFoundException() throws Exception {
		
		String jsonRequest = "{\"hopital_id\":"+1+",\"specialite_id\":"+1+",\"intervenant\":\"Test\"}";
		
		this.mockMvc.perform(post("/reservation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isNotFound());
	}
}
