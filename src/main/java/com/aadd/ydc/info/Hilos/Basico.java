package com.aadd.ydc.info.Hilos;

/////////////////////////////////////////////////// HILOS BÁSICOS /////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//Un hilo es un proceso ligero dentro de una aplicación. Permite ejecutar código en paralelo al hilo principal.
//Se pueden crear hilos de dos maneras:
//1️⃣ Extendiendo la clase Thread.
//2️⃣ Implementando Runnable (más recomendado).

class HiloEjemplo extends Thread {
	@Override
	public void run() {
		System.out.println("Ejecutando hilo: " + Thread.currentThread().getName());
	}
}

class HiloRunnable implements Runnable {
	@Override
	public void run() {
		System.out.println("Ejecutando Runnable: " + Thread.currentThread().getName());
	}
}

public class Basico {
	public static void main(String[] args) {
		HiloEjemplo hilo1 = new HiloEjemplo();
		Thread hilo2 = new Thread(new HiloRunnable());

		hilo1.start(); // Inicia un hilo independiente
		hilo2.start(); // También inicia un hilo independiente
	}
}

