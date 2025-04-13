package Servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RnEscucharCliente implements Runnable, Observer {
	private String nombre; // podría ser con un id. Cambiar tambien en la comun
	private ObjectOutputStream salidaObjetos;
	private ObjectInputStream entradaObjetos;
	// Podemos ponerlos con Data, y enviar Json como texto.

	public RnEscucharCliente(Socket cliente) throws Exception {
		try {
			salidaObjetos = new ObjectOutputStream(cliente.getOutputStream());
			salidaObjetos.flush();
			entradaObjetos = new ObjectInputStream(cliente.getInputStream());
			// Podemos hacer aqui la comprobacion de nombre. Si ya existe, lanzamos error
			Comun.addObserver(this);
		} catch (Exception e) {
			throw new Exception("No se ha podido aceptar el cliente");
		}
	}

	@Override
	public void update(String json) {
		// Si usasemos Data... utilizaríamos este
		// Sólo sería necesario deserializar el Json en el cliente
		// A no ser que requiera de alguna operacion o modificacion en la clase común
	}

	@Override
	public void update(Object mensaje) {
		try {
			salidaObjetos.writeObject(mensaje);
			salidaObjetos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (!Comun.parar) {
			try {
				Mensaje mensaje = (Mensaje) entradaObjetos.readObject();
				Comun.updateAll(mensaje);
			} catch (Exception e) {

			}
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
