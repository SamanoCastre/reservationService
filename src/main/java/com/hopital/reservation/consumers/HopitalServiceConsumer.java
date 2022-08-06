package com.hopital.reservation.consumers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hopital.reservation.entities.Disponibilite;


@FeignClient(value = "hopital-service", url="${GATEWAY_URI}")
public interface HopitalServiceConsumer {
	
	@PutMapping("/disponibilite/incrementer")
	public Disponibilite incrementerLits(@RequestParam("hopital_id") int hopital_id, @RequestParam("specialite_id") int specialite_id);
	
	@PutMapping("/disponibilite/decrementer")
	public Disponibilite decrementerLits(@RequestParam("hopital_id") int hopital_id, @RequestParam("specialite_id") int specialite_id);
	
	@GetMapping("/disponibilite")
	public Disponibilite getDisponibilite(@RequestParam("hopital_id") int hopital_id, @RequestParam("specialite_id") int specialite_id );
}
