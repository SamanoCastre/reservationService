package com.urgence.reservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.urgence.reservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	

}
