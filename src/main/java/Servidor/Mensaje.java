package Servidor;

import java.io.Serializable;

public class Mensaje implements Serializable {
	private static final long serialVersionUID = 1L;
	private String texto;

	public Mensaje(String texto) {
		this.setTexto(texto);
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
