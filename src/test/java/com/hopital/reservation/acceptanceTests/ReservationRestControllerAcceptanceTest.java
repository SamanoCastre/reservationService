package com.hopital.reservation.acceptanceTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
	public void reserverLitInvalidTest() throws Exception {
		
		String jsonRequest = "{\"hopital_id\":"+0+",\"specialite_id\":"+0+",\"intervenant\":\"Test\"}";
		
		this.mockMvc.perform(post("/reservation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isNotModified())
				.andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist()); 
	}
	
	
	@Test
	public void terminerReservationInvalidTest() throws Exception {
		
		this.mockMvc.perform(put("/reservation")
				.contentType(MediaType.APPLICATION_JSON)
				.param("reservation_id", 0 + ""))
				.andExpect(status().isNotModified())
				.andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist()); 
	}
	
}
