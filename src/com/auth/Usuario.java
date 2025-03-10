package com.auth;

public class Usuario {
	private static final int MAXIMO_INTENTOS = 3;
	private static Usuario[] usuarios;
	private String nombre;
	private String apellidos;
	private String email;
	private int intentos;
	private Credencial credencial;

	public Usuario(String nombre, String apellidos, String password) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.credencial = new Credencial(nombre, apellidos, password);
		usuarios = new Usuario[10];
		this.aniadirUsuario();
	}

	public Usuario(String nombre, String apellidos, String email, String password) {
		this(nombre, apellidos, password);
		this.email = email;
	}

	public boolean esCuentaBloqueada() {
		return this.intentos == MAXIMO_INTENTOS;
	}

	public boolean modificarPassword(String oldPass, String newPass, String newPassVerif) {
		boolean modificada = true;
		if (this.credencial.comprobarPassword(oldPass) && !oldPass.equals(newPass) && newPass.equals(newPassVerif)
				&& esPasswordSegura()) {
			this.credencial.setPassword(newPass);
		} else {
			modificada = false;
		}
		return modificada;
	}

	public boolean esPasswordSegura() {
		return this.credencial.esPasswordSegura();
	}

	private void setCredencial(Credencial credencial) {
		this.credencial = credencial;
	}

	public boolean hacerLogin(String username, String password) {
		boolean logueado = true;
		if (!esCuentaBloqueada() && this.credencial.getUsername().equals(username)
				&& !this.credencial.comprobarPassword(password)) {
			this.intentos++;
			logueado = false;
		} else if (esCuentaBloqueada()) {
			logueado = false;
		} else {
			this.intentos = 0;
		}
		return logueado;
	}

	public void aniadirUsuario() {
		boolean existe = false;
		Usuario usuarioInsertar = null;
		for (int i = 0; i < usuarios.length && !existe; i++) {
			if (usuarios[i] != null && usuarios[i].credencial.getUsername().equals(this.credencial.getUsername())) {
				existe = true;
				usuarioInsertar = usuarios[i];
				boolean insertado = false;
				for (int j = 0; j < usuarios.length && !insertado; j++) {
					if (usuarios[j] == null) {
						usuarios[j] = usuarioInsertar;
						insertado = true;
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		return !esCuentaBloqueada()
				? "Usuari@: " + this.nombre + " " + this.apellidos + " con email " + this.email + ", username "
						+ this.credencial.getUsername() + " y contraseña "
						+ "*".repeat(this.credencial.longitudPassword(apellidos))
				: "Cuenta bloqueada";
	}

}
