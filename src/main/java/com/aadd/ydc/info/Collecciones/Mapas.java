package com.aadd.ydc.info.Collecciones;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Mapas {
	public static void main(String[] args) {
		/////////////////////////////////////////////////////// MAPAS /////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//////////////////////////////////////////////////////////
///////////////////////////////////////////////// HASH MAP
		HashMap<String, Integer> mapaUno = new HashMap<String, Integer>(); // declaracion

		// en los hashmaps el orden lo dicta el hash que se calcula al momento de
		// crearlos. Por eso no hay posiciones.
		// no se permiten repetirdos, por lo tanto, es algo que podemos aprovechar
		// la llave puede ser String, char, integer,Objeto etc...

		//////////////////////////////////////////////////////////////////////////////////////// Comandos de mapas:
		mapaUno.put("Elemento cero", mapaUno.getOrDefault("nope", 0)); // si no existe lo que queremos, pone un default
		mapaUno.put("Elemento cero", 0);
		mapaUno.put("Elemento uno", 1); // se introduce la "key" y su valor relacionado
		mapaUno.put(null, null);
		mapaUno.put("Elemento dos", 2);
		mapaUno.put("Elemento tres", 3);
		mapaUno.put("Elemento uno", 2); // si tiene la misma llave, reemplaza la anterior por la nueva.
		System.out.println(mapaUno.get("Elemento uno")); // mostrará el 2 porque el uno ha sido machacado. Con este
															// comando llamas al valor de la key introducida
		System.out.println(mapaUno.keySet()); // muestra todas las keys del hashmap, sin sus valores
		System.out.println(mapaUno.values()); // muestra todos los valores del hashmap, sin sus keys
		mapaUno.remove(null);// elimina el valor y la key introducida (meto null porque cuenta como key)
		mapaUno.remove("Elemento cero"); // pero también podemos borrar existentes.
		System.out.println(mapaUno.entrySet()); // muestra la cada Key con su valor. Si contiene objetos, muestra su
												// toString o direccion de memoria.
		System.out.println(mapaUno.containsKey("Elemento uno")); // devuelve true or false segun la key exista o no
		System.out.println(mapaUno.containsValue(1)); // true or false según el valor exista. Si es un objeto, pregunta
														// por el equals.

		//////////////////////////////////////////////////////////////////////////////////////// Recorrer el HashMap
		for (String clave : mapaUno.keySet()) { // lo puedes recorrer usando las keys
			System.out.print(clave + " = ");
			System.out.print(mapaUno.get(clave));
			System.out.println();
		}
		for (Integer clave : mapaUno.values()) {
		} // lo puedes recorrer usando las keys

		Iterator<Entry<String, Integer>> recorreMapas = mapaUno.entrySet().iterator(); // esto no se usa nada, pero lo
																						// pongo por si acaso
		while (recorreMapas.hasNext()) {
			// usa .getKey() y .getValue(), que toman los valores del hash que esté mirando
			// y en la siguiente vuelta tomara el siguiente valor y clave
			Entry<String, Integer> entrada = recorreMapas.next();
			String clave = (String) entrada.getKey();
			Integer valor = (Integer) entrada.getValue();
			System.out.println(clave + " = " + valor);
		}

		///////////////////////////////////////// Entry 
		// usa .getKey() y .getValue()
		// Funciona como un iterator de un entrySet, por lo que se usa en bucles y
		// cambia de valor siempre que haya siguiente.
		// los dos comandos que tiene toman el valor automático del hash que esté
		// consultando en ese momento.
		// Es lo que más se suele usar

		for (Entry<String, Integer> mapaCopia : mapaUno.entrySet()) { // Usas entry para que recorra el entrySet de un
																		// HashMap.
			System.out.println(mapaCopia.getKey() + " " + mapaCopia.getValue());
		}
		for (Entry<?, ?> mapaCopia : mapaUno.entrySet()) { // Puedes dejar en incognita los tipos del entry, util para
															// métodos.
			System.out.println(mapaCopia.getKey() + " " + mapaCopia.getValue());
		}

	}
}
