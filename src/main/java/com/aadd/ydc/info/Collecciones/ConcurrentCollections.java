package com.aadd.ydc.info.Collecciones;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class ConcurrentCollections {
/////////////////////////////////////////////////// ConcurrentCollections /////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// - Son versiones sincronizadas de las collections normales.
	// - No requieren "synchronized", pues manejan concurrencia internamente.
	// - NO garantizan el orden de modificación, pero sí de lectura en algunos
	// casos.
	// - Si se necesitan modificaciones ordenadas, usar una BlockingQueue o
	// sincronización manual.

	public static void main(String[] args) {

		ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
		concurrentMap.put("A", 1);
		concurrentMap.put("B", 2);
		System.out.println("ConcurrentHashMap: " + concurrentMap.get("A")); // 1

		CopyOnWriteArrayList<String> concurrentList = new CopyOnWriteArrayList<>();
		concurrentList.add("Hola");
		concurrentList.add("Mundo");
		System.out.println("CopyOnWriteArrayList: " + concurrentList);

		ConcurrentLinkedQueue<Integer> concurrentQueue = new ConcurrentLinkedQueue<>();
		concurrentQueue.add(10);
		concurrentQueue.add(20);
		System.out.println("ConcurrentLinkedQueue poll: " + concurrentQueue.poll()); // 10

		BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(2);
		try {
			blockingQueue.put(1);
			blockingQueue.put(2);
			// blockingQueue.put(3); // Se bloquearía si la capacidad es 2
			System.out.println("BlockingQueue take: " + blockingQueue.take()); // 1
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
