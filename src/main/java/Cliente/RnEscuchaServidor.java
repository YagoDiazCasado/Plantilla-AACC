package Cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Servidor.Mensaje;

public class RnEscuchaServidor implements Runnable {
	private Client client;
	private ObjectInputStream entradaObjetos;
	private ObjectOutputStream salidaObjetos;

	public RnEscuchaServidor(Client client) {
		this.setClient(client); // de aqui tomamos el nombre y toda la pesca
		try {
			entradaObjetos = new ObjectInputStream(client.getCliente().getInputStream());
			salidaObjetos = new ObjectOutputStream(client.getCliente().getOutputStream());
			salidaObjetos.flush();
			// Podemos meter aqui la comprobacion de nombre
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Mensaje mensaje = (Mensaje) entradaObjetos.readObject();
				// Y aqui hacer lo que queramos con el mensaje
			} catch (Exception e) {

			}
		}
	}

	public void enviarMensaje(Mensaje mensaje) {
		try {
			salidaObjetos.writeObject(mensaje);
			salidaObjetos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
