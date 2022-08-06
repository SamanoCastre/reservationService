package com.hopital.reservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hopital.reservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	

}
