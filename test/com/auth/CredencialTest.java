package com.auth;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CredencialTest {

	private Credencial credencial;

	@BeforeEach
	void setUp() {
		Credencial.resetSecuencia();
		credencial = new Credencial("Alvaro", "Rios", "Password1");
	}

	@Test
	void testConstructor() {
		assertEquals("alvrio100", credencial.getUsername());
	}

	@Test
	void testGenerarUsername() {
		Credencial.resetSecuencia();
		assertEquals("alvrio100", credencial.generarUsername("Alvaro", "Rios"));
		Credencial.resetSecuencia();
		assertEquals("alri100", credencial.generarUsername("Al", "Ri"));
	}


	@Test
	void testComprobarPassword() {
		assertTrue(credencial.comprobarPassword("Password1"));
		assertFalse(credencial.comprobarPassword("Password2"));
	}

	@Test
	void testEsPasswordSegura() {
		assertTrue(credencial.esPasswordSegura());
		credencial.setPassword("password1");
		assertFalse(credencial.esPasswordSegura());
		credencial.setPassword("Password");
		assertFalse(credencial.esPasswordSegura());
		credencial.setPassword("P1");
		assertFalse(credencial.esPasswordSegura());
		credencial.setPassword("A1bcdefgh");
		assertTrue(credencial.esPasswordSegura());
		credencial.setPassword("A1bcdefg");
		assertFalse(credencial.esPasswordSegura());
	}

	@Test
	void testSetPassword() {
		credencial.setPassword("NewPassword123");
		assertTrue(credencial.comprobarPassword("NewPassword123"));
		credencial.setPassword("");
		assertFalse(credencial.esPasswordSegura());
	}

	@Test
	void testLongitudPassword() {
		assertEquals(9, credencial.longitudPassword("Password1"));
		credencial.setPassword("");
		assertEquals(0, credencial.longitudPassword(""));
	}

	@Test
	void testSecuenciaIncrementa() {
		Credencial credencial2 = new Credencial("Alvaro", "Rios", "Password2");
		assertNotEquals(credencial.getUsername(), credencial2.getUsername());
	}

	@Test
	void testSecuenciaConMultiplesInstancias() {
		Credencial credencial2 = new Credencial("Alvaro", "Rios", "Password2");
		assertEquals("alvrio101", credencial2.getUsername());
		Credencial credencial3 = new Credencial("Maria", "Gomez", "Password3");
		assertEquals("margom102", credencial3.getUsername());
	}

	@Test
	void testResetSecuencia() {
		Credencial.resetSecuencia();
		Credencial credencial3 = new Credencial("Ana", "Lopez", "Password3");
		assertEquals("analop100", credencial3.getUsername());
	}
}