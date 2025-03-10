package com.auth;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioTest {
	private Usuario usuario;

	@BeforeEach
	void setUp() {
		usuario = new Usuario("Alvaro", "Rios", "alvarorios@gmail.com", "Password1");
	}

	@Test
	void testEsCuentaBloqueada() {
		assertFalse(usuario.esCuentaBloqueada());
		usuario.hacerLogin("alvrio100", "Password2");
		usuario.hacerLogin("alvrio100", "Password2");
		usuario.hacerLogin("alvrio100", "Password2");
		assertTrue(usuario.esCuentaBloqueada());	
		assertFalse(usuario.hacerLogin("alvrio100", "Password2"));
	}
	
	@Test
	void testModificarPassword() {
		assertTrue(usuario.modificarPassword("Password1", "Password2", "Password2"));
		assertFalse(usuario.modificarPassword("Password3", "Password2", "Password2"));
	}

	@Test
	void testHacerLogin() {
		assertTrue(usuario.hacerLogin("alvrio100", "Password1"));
	}

	@Test
	void testAniadirUsuario() {
		Usuario usuario2 = new Usuario("Ana", "Gomez", "anagomez@gmail.com", "Password3");
		assertNotNull(usuario2);
		usuario.aniadirUsuario();
	}

	@Test	
	void testToString() {
		assertTrue(usuario.toString().contains("Usuari@: Alvaro Rios"));
		assertTrue(usuario.toString().contains("alvarorios@gmail.com"));
		assertTrue(usuario.toString().contains("alvrio101"));
		assertTrue(usuario.toString().contains("*********"));
		usuario.hacerLogin("alvrio101", "Password2");
		usuario.hacerLogin("alvrio101", "Password2");
		usuario.hacerLogin("alvrio101", "Password2");
		assertTrue(usuario.toString().contains("bloqueada"));
	}
}
