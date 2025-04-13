package Servidor;

import java.util.HashMap;

public class Comun {

	public static HashMap<String, Observer> clientes = new HashMap<String, Observer>();
	public static boolean parar = false;

	public static void addObserver(RnEscucharCliente rn) throws Exception {
		if (!clientes.keySet().contains(rn.getNombre())) {
			clientes.put(rn.getNombre(), rn);
		} else {
			throw new Exception("Nombre ya existente");
		}
	}

	public static void updateAll(Mensaje mensaje) {
		// Normalmente leeriamos el mensaje para ver si necesita ser tratado o tiene
		// indicaciones.
		for (String nom : clientes.keySet()) {
			clientes.get(nom).update(mensaje);
		}
	}

	public static void updateAll(String json) {
		// Normalmente leeriamos el mensaje para ver si necesita ser tratado o tiene
		// indicaciones.
		// Si es un Json, tendríamos que deserializarlo, leerlo, ver que hay que hacer y
		// luego serializarlo de nuevo para enviarlo.
		// Utilizar Jackson en ese caso
		for (String nom : clientes.keySet()) {
			clientes.get(nom).update(json);
		}
	}
}
