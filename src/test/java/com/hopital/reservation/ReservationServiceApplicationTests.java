package com.hopital.reservation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.hopital.reservation.web.ReservationRestController;

@SpringBootTest
class ReservationServiceApplicationTests {

	@Autowired
	private ReservationRestController reservationRestController;

	@Test
	void contextLoads() {
		assertThat(this.reservationRestController).isNotNull();
	}

}
