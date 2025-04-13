package Servidor;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try (ServerSocket sk = new ServerSocket(8888)) {
			while (true) {
				Socket cliente = sk.accept();
				try {
					RnEscucharCliente rn = new RnEscucharCliente(cliente);
					Thread hiloCliente = new Thread(rn);
					hiloCliente.setDaemon(true);
					hiloCliente.start();
				} catch (Exception e) {
					System.out.println("Problemas con la creacion");
					cliente.close(); // Le saldrá un error al cliente y así luego lo gestionamos
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
