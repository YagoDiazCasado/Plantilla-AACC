package com.aadd.ydc.info.Hilos;

/////////////////////////////////////////////////// HILOS AVANZADOS /////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//En aplicaciones reales, es importante gestionar la sincronización y concurrencia.
//Se usan: synchronized, volatile, ConcurrentCollections, y bloques sincronizados.

import java.util.concurrent.*;

class Contador {
	private int contador = 0;

	// Bloque sincronizado para evitar condiciones de carrera
	public synchronized void incrementar() {
		contador++;
	}

	public synchronized int getContador() {
		return contador;
		// Esto es asi porque no queremos que dos hilos accedan a la vez
	}
}

public class Avanzado {
	public static void main(String[] args) throws InterruptedException {
		Contador contador = new Contador();
		ExecutorService executor = Executors.newFixedThreadPool(3);

		Runnable tarea = () -> {
			for (int i = 0; i < 1000; i++) {
				contador.incrementar();
			}
		};

		// Ejecutar tareas en paralelo
		for (int i = 0; i < 3; i++) {
			executor.submit(tarea);
		}

		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.SECONDS);
		System.out.println("Valor final del contador: " + contador.getContador());

		// Uso de CompletableFuture
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			System.out.println("Tarea asíncrona ejecutándose");
		}); // Es un hilo que se realiza cuando puede sin molestar
		future.join(); // Espera a que la tarea termine
		System.out.println("Tarea completada");
	}
}
