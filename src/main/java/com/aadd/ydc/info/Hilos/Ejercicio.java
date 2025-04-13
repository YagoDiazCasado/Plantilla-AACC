package com.aadd.ydc.info.Hilos;

import java.util.LinkedList;
import java.util.Queue;

public class Ejercicio {
	public static void main(String[] args) {
		ListaCompartida lista = new ListaCompartida();
		Thread productor = new Thread(new Productor(lista));
		Thread consumidor = new Thread(new Consumidor(lista));

		productor.start();
		consumidor.start();
	}
}

class ListaCompartida {
	private final Queue<Integer> lista = new LinkedList<>();
	private final int LIMITE = 5;
	private boolean puedeProducir = true;

	public synchronized void producir(int valor) throws InterruptedException {
		while (!puedeProducir) {
			wait(); // Espera hasta que pueda producir
		}
		lista.add(valor);
		System.out.println("Productor agregó: " + valor);
		puedeProducir = false;
		notify(); // Notifica al consumidor
	}

	public synchronized int consumir() throws InterruptedException {
		while (puedeProducir) {
			wait(); // Espera hasta que haya algo que consumir
		}
		int valor = lista.poll();
		System.out.println("Consumidor retiró: " + valor);
		puedeProducir = true;
		notify(); // Notifica al productor
		return valor;
	}
}

class Productor implements Runnable {
	private final ListaCompartida lista;

	public Productor(ListaCompartida lista) {
		this.lista = lista;
	}

	@Override
	public void run() {
		try {
			for (int i = 1; i <= 10; i++) {
				lista.producir(i);
				Thread.sleep(500); // Simula retraso en producción
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}

class Consumidor implements Runnable {
	private final ListaCompartida lista;

	public Consumidor(ListaCompartida lista) {
		this.lista = lista;
	}

	@Override
	public void run() {
		try {
			for (int i = 1; i <= 10; i++) {
				lista.consumir();
				Thread.sleep(700); // Simula retraso en consumo
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
