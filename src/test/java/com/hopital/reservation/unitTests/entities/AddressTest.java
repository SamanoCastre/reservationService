package com.hopital.reservation.unitTests.entities;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.hopital.reservation.entities.Address;

@SpringBootTest
public class AddressTest {
	private Address address;
	
	@BeforeEach
	public void init() {
		this.address = new Address("1","place du Docteur Baylac","31059","Toulouse","France");
	}
	
	@Test
	public void createAddressTest() {
		Address testedAddress = new Address();
		testedAddress.setCodePostal(this.address.getCodePostal());
		testedAddress.setNumero(this.address.getNumero());
		testedAddress.setRue(this.address.getRue());
		testedAddress.setVille(this.address.getVille());
		testedAddress.setPays(this.address.getPays());
		
		assertThat(testedAddress.getCodePostal()).isEqualTo(this.address.getCodePostal());
		assertThat(testedAddress.getNumero()).isEqualTo(this.address.getNumero());
		assertThat(testedAddress.getRue()).isEqualTo(this.address.getRue());
		assertThat(testedAddress.getVille()).isEqualTo(this.address.getVille());
		assertThat(testedAddress.getPays()).isEqualTo(this.address.getPays());
		
	}

}
