package com.aadd.ydc.info.Hilos;

class AvanzadoMonitor {
    private boolean listo = false;

    public synchronized void esperar() {
        while (!listo) {
            try {
                wait(); // Espera hasta que otro hilo lo despierte
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " - Notificación recibida.");
    }

    public synchronized void notificarUno() {
        listo = true;
        notify(); // Despierta un solo hilo
    }

    public synchronized void notificarTodos() {
        listo = true;
        notifyAll(); // Despierta a todos los hilos en espera
    }
}

public class SincronizacionAvanzada {
    public static void main(String[] args) {
        AvanzadoMonitor monitor = new AvanzadoMonitor();

        Runnable tarea = () -> {
            monitor.esperar();
        };

        Thread t1 = new Thread(tarea, "Hilo 1");
        Thread t2 = new Thread(tarea, "Hilo 2");
        Thread t3 = new Thread(tarea, "Hilo 3");

        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(2000); // Simula procesamiento
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        monitor.notificarTodos(); // Despierta a todos los hilos
    }
}