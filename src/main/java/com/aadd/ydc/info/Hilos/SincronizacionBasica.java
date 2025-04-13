package com.aadd.ydc.info.Hilos;

/////////////////////////////////////////////////// USO DE WAIT Y NOTIFY /////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class Monitor {
	private boolean listo = false;

	public synchronized void esperar() {
		while (!listo) {
			try {
				wait(); // Espera hasta que otro hilo lo despierte
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Notificación recibida. Continuando ejecución.");
	}

	public synchronized void notificar() {
		listo = true;
		notify(); // Despierta un hilo que esté esperando
	}
}

class HiloEsperar implements Runnable {
	private Monitor monitor;

	public HiloEsperar(Monitor monitor) {
		this.monitor = monitor;
	}

	@Override
	public void run() {
		monitor.esperar();
	}
}

class HiloNotificar implements Runnable {
	private Monitor monitor;

	public HiloNotificar(Monitor monitor) {
		this.monitor = monitor;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(2000); // Simula un retraso
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		monitor.notificar();
	}
}

public class SincronizacionBasica {
	public static void main(String[] args) {
		Monitor monitor = new Monitor();
		Thread t1 = new Thread(new HiloEsperar(monitor));
		Thread t2 = new Thread(new HiloNotificar(monitor));

		t1.start();
		t2.start();
	}
}
