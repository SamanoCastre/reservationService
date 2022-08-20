package com.hopital.reservation.unitTests.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.hopital.reservation.entities.Specialite;

@SpringBootTest
public class SpecialiteUnitTest {
	private Specialite specialite;
	
	@BeforeEach
	public void init() {
		this.specialite =  new Specialite(2, "Anethsesie", "Anethsésie", new Date());
	}
	
	@Test
	public void createValidSpecialite() {
		Specialite testedSpecialite = new Specialite();
		testedSpecialite.setId(this.specialite.getId());
		testedSpecialite.setDateCreation(this.specialite.getDateCreation());
		testedSpecialite.setName(this.specialite.getName());
		testedSpecialite.setGroupe(this.specialite.getGroupe());
		
		assertThat(testedSpecialite.getId()).isEqualTo(this.specialite.getId());
		assertThat(testedSpecialite.getDateCreation()).isEqualTo(this.specialite.getDateCreation());
		assertThat(testedSpecialite.getName()).isEqualTo(this.specialite.getName());
		assertThat(testedSpecialite.getGroupe()).isEqualTo(this.specialite.getGroupe());
	}

}
