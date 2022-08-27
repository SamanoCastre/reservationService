package com.hopital.reservation.acceptanceTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
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
		JSONObject json = new JSONObject();
		json.put("hopital_id", 0);
		json.put("specialite_id", 0);
		json.put("intervenant", "Test");
		
		this.mockMvc.perform(post("/reservation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.toString()))
				.andExpect(status().isNotFound()); 
	}
	
	@Test
	public void reserverLitShouldReturnAReservation() throws Exception {
		JSONObject json = new JSONObject();
		json.put("hopital_id", 2);
		json.put("specialite_id", 2);
		json.put("intervenant", "Test");
		
		this.mockMvc.perform(post("/reservation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.toString()))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.hopital_id").value(json.get("hopital_id")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.specialite_id").value(json.get("specialite_id"))); 
	}
	
	@Test //Cas lit indisponible, la bdd doit avoir 0 en nombre de lits
	public void reserverLitShouldReturnResourceNotFoundException() throws Exception {
		JSONObject json = new JSONObject();
		json.put("hopital_id", 1);
		json.put("specialite_id", 1);
		json.put("intervenant", "Test");
		
		this.mockMvc.perform(post("/reservation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.toString()))
				.andExpect(status().isNotFound());
	}
}
