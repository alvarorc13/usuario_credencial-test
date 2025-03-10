package com.auth;

public class Credencial {

	private String username;
	private String password;
	private static int secuencia = 100;

	public Credencial(String nombre, String apellidos, String password) {
		super();
		this.username = generarUsername(nombre, apellidos);
		secuencia++;
		this.password = password;
	}

	public String generarUsername(String nombre, String apellidos) {
		return (((nombre.length() >= 3 ? nombre.substring(0, 3) : nombre)
				+ (apellidos.length() >= 3 ? apellidos.substring(0, 3) : apellidos)).toLowerCase()) + secuencia;
	}

	public boolean comprobarPassword(String password) {
		return this.password.equals(password);
	}

	public boolean esPasswordSegura() {
		boolean contieneLetraMayuscula = false;
		boolean contieneDigito = false;
		for (int i = 0; i < this.password.length() && !(contieneLetraMayuscula && contieneDigito); i++) {
			if (Character.isAlphabetic(this.password.charAt(i))
					&& Character.toUpperCase(this.password.charAt(i)) == this.password.charAt(i)) {
				contieneLetraMayuscula = true;
			}
			if (Character.isDigit(this.password.charAt(i))) {
				contieneDigito = true;
			}
		}
		return contieneLetraMayuscula && contieneDigito && this.password.length() > 8;
	}

	public String getUsername() {
		return username;
	}

	protected void setPassword(String password) {
		this.password = password;
	}

	protected int longitudPassword(String password) {
		return this.password.length();
	}

	public static void resetSecuencia() {
		secuencia = 100;
	}

}
