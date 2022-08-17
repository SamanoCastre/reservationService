package com.hopital.reservation.unitTests.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.hopital.reservation.entities.Address;
import com.hopital.reservation.entities.Disponibilite;
import com.hopital.reservation.entities.Hopital;
import com.hopital.reservation.entities.Specialite;

@SpringBootTest
public class DisponibiliteTest {
	private Disponibilite disponibilite;
	
	@BeforeEach
	public void init() {
		this.disponibilite = new Disponibilite(1,new Hopital(2, "Test", new Address("1","place du Docteur Baylac","31059","Toulouse","France"), new Date()), new Specialite(2, "Anethsesie", "Anethsésie", new Date()), 15, new Date());
	}
	
	@Test
	public void createDisponibiliteTest() {
		Disponibilite testedDisponibilite = new Disponibilite();
		testedDisponibilite.setId(this.disponibilite.getId());
		testedDisponibilite.setHopital(this.disponibilite.getHopital());
		testedDisponibilite.setSpecialite(this.disponibilite.getSpecialite());
		testedDisponibilite.setDateCreation(this.disponibilite.getDateCreation());
		testedDisponibilite.setDateMiseAJour(this.disponibilite.getDateMiseAJour());
		testedDisponibilite.setLits(this.disponibilite.getLits());
		
		assertThat(testedDisponibilite.getId()).isEqualTo(this.disponibilite.getId());
		assertThat(testedDisponibilite.getHopital()).isEqualTo(this.disponibilite.getHopital());
		assertThat(testedDisponibilite.getSpecialite()).isEqualTo(this.disponibilite.getSpecialite());
		assertThat(testedDisponibilite.getDateCreation()).isEqualTo(this.disponibilite.getDateCreation());
		assertThat(testedDisponibilite.getDateMiseAJour()).isEqualTo(this.disponibilite.getDateMiseAJour());
		assertThat(testedDisponibilite.getLits()).isEqualTo(this.disponibilite.getLits());
	}

}
