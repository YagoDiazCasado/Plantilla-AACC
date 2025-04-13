package Cliente;

import java.net.Socket;

public class Client {
	private String nombre_o_codigo;
	private Socket cliente;

	public Client(String nombre_o_codigo) {
		this.nombre_o_codigo = nombre_o_codigo;
		try {
			setCliente(new Socket("ip de conexion", 8888));
			RnEscuchaServidor cliente = new RnEscuchaServidor(this);
			Thread hiloEscucha = new Thread(cliente);
			hiloEscucha.start();
		} catch (Exception e) {
			e.printStackTrace();
			// Si el nombre/ codigo ya existe en el servidor, nos fallará porque asi esta
			// programado.
			// El server no nos aceptará.
		}
	}

	public String getNombre_o_codigo() {
		return nombre_o_codigo;
	}

	public void setNombre_o_codigo(String nombre_o_codigo) {
		this.nombre_o_codigo = nombre_o_codigo;
	}

	public Socket getCliente() {
		return cliente;
	}

	public void setCliente(Socket cliente) {
		this.cliente = cliente;
	}

}
