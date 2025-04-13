package com.aadd.ydc.info.Collecciones;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class Sets {
/////////////////////////////////////////////////// SET /////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Guarda valores unicos 
	public static void main(String[] args) {
//////////////////////////////////////////////////////////
///////////////////////////////////////////////// HASH SET
		// Se declara como una lista y almacena valores/ objetos que no se repetirán ni
		// guardaran orden ni posicion
		// Pueden sacarse de un Hashmap o crearse de primeras.
		HashSet<String> conjunto = new HashSet<>();
		// Puede ponerse "Set" sin más
		conjunto.add("perro");
		conjunto.add("gato");
		conjunto.add("perro"); // No se agregará porque ya existe
		conjunto.add("leon");
		conjunto.add("tigre");

		System.out.println("Conjunto 1: " + conjunto); //se puede imprimir sin parafernalia ninguna
		
//////////////////////////////////////////////////////////
////////////////////////////////////////// LINKED HASH SET
		// Es un Hash set en el que existe un orden y una posición, dictada SOLO por el
		// orden en el que se metieron los elementos
		LinkedHashSet<String> conjunto2 = new LinkedHashSet<>();
		// También se puede poner como "Set" sin más
		conjunto2.add("perro");
		conjunto2.add("tigre");
		conjunto2.add("leon");
		conjunto2.add("gato");
		conjunto2.add("perro"); // No se agregará porque ya existe

		System.out.println("Conjunto 2: " + conjunto2);

//////////////////////////////////////////////////////////
////////////////////////////////////////////// TREE SET
		// Es como un Hash Set, pero se guarda todo en orden
		TreeSet<String> conjunto3 = new TreeSet<>();
		// También se puede poner como "Set" sin más
		conjunto3.add("perro");
		conjunto3.add("gato");
		conjunto3.add("leon");
		conjunto3.add("perro"); // No se agregará porque ya existe
		conjunto3.add("tigre");

		System.out.println("Conjunto 3: " + conjunto3);
	}

}
