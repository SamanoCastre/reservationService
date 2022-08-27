package com.hopital.reservation.integrationTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.json.JSONObject;
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
		
		JSONObject json = new JSONObject();
		json.put("hopital_id", this.reservation.getHopital_id());
		json.put("specialite_id", this.reservation.getSpecialite_id());
		json.put("intervenant", this.reservation.getIntervenant());
		
		this.mockMvc.perform(post("/reservation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.toString()))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.intervenant").value(json.get("intervenant"))); 
	}
}
