package com.aadd.ydc.info.ObjetosEstudio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ManejoDeObjetos {

	public static void main(String[] args) {
/////////////////////////////////////////////////// Manejo de Objetos /////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	
		ObjetoUno o1 = new ObjetoUno(); // inicializamos y declaramos el objeto, esta vez usando su constructor sin
										// poarámetros
		ObjetoUno o2 = new ObjetoUno("caja", 5, true); // uso el otro constructor
		ObjetoUno o3 = new ObjetoUno();
		System.out.println(o1); // muestra el toString si no se pone nada, sin el override, habría mostrado la
								// dir de memoria
		o1 = o2; // puedes igualar (copiar) objetos que sean de la misma clase (instanceof)
		System.out.println(o1); // mostrará el mismo objeto, pero con su nuevo valor
		System.out.println(o1.toString()); // también puedes llamar manualmente al toString
		System.out.println(o1.equals(o2)); // llamamos por ejemplo a su método equals (true or false, pero dará true
											// porque copiamos el obj)
		// Podremos acceder a los atributos públicos de los objetos:
		String aux = o1.nombre;
		// Y podemos acceder a los protected y private usando sus getter y Setters:
		int num = o1.getCantidad();
		o1.setCantidad(5); // la podemos modificar con un setter

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////ArrayList con OBJ
		ArrayList<ObjetoUno> listaDeObjetos = new ArrayList<ObjetoUno>(); // si no se declara el tipo del contenido
																			// luego hay que castear
		listaDeObjetos.add(o2);
		listaDeObjetos.add(o1);
		listaDeObjetos.add(o3);
		System.out.println(listaDeObjetos.contains(o1)); // utilizará el equals()
		Collections.sort(listaDeObjetos); // es capaz de hacerlo gracias al compareTo(). Ordenará el array según el
											// criterio en el metodo del obj
		System.out.println(listaDeObjetos); // imprime el arrayList, pero si son objetos, su toString()
		listaDeObjetos.get(0).setCantidad(7); // puedo tratar a la posición del Array como el propio objeto, y este se
												// modificará
		System.out.println("Objeto original = " + o3.getCantidad());
		System.out.println("Desde la posición de la lista = " + listaDeObjetos.get(0).getCantidad());
		System.out.println(listaDeObjetos.contains("caja")); // no se puede buscar esperando que use el toString()
		System.out.println(listaDeObjetos.contains(o1)); // con el equals buscará el obejto en sí.
		System.out.println(listaDeObjetos.contains(new ObjetoUno("caja", 5, true))); // usará el equals()
		listaDeObjetos.remove(o1); // borra el objeto siempre y cuando tengan un equals hecho. Si no, se complica
									// borrar y hay que recorrer array hasta encotnrar el que queremos
		System.out.println(listaDeObjetos);
		System.out.println(listaDeObjetos.lastIndexOf(o3)); // busca usando el equals() SIEMPRE
		// equals() BUSCA
		// toString() MUESTRA
		// compareTo() ORDENA

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////HashMaps con OBJ
		HashMap<String, ObjetoUno> mapaUno = new HashMap<String, ObjetoUno>();
		mapaUno.put("objeto cero", o1);
		System.out.println(mapaUno.entrySet()); // al imprimir el objeto, se imprime el toString()
		System.out.println(mapaUno.get("objeto cero")); // imprime el toString tambien, pero puede usarse como objeto
														// normal:
		mapaUno.get("objeto cero").setCantidad(3); // podemos usar los métodos del objeto desde aqui
		System.out.println(mapaUno.get("objeto cero").getCantidad()); // mapaUno.get("objeto cero") es la portada del
																		// verdadero objeto.
		// si uso getValue() dentro de un bucle con Entry también lo podré usar así.

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Objetos Heredados
		ObjetoTres segundo = new ObjetoTres();
		segundo.modCant(4); // aunque pertenece a ObjetoDos, todos los ObjetosTres lo tienen, pues lo
							// heredan de él.
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Llamando Métodos 
		recivoDeTodo(segundo);
		recivoDeTodo(o1);
		recivoDeTodo(o2);
		recivoDeTodo(listaDeObjetos);

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Métodos 
	// Cuando creas un objeto se guarda en memoria, asique modificarlos, al igual
	// que ArrayList, HashMaps y Arrays, se puede hacer sin tener que returnear
	// nada.
	// Métodos genéricos

	public static <T> void recivoDeTodo(T algo) { // puede recibir de todo sin necesidad de sobrecargar el metodo, pero
													// no puede acceder a propiedades de objetos porque no sabemos
													// cuales serán, a no ser que hayamos adecuado un metodo para este caso
		System.out.println(algo.getClass());
		//se puede hacer dentro una función para identificar el tipo de objeto(instanceof) y según eso, actuar de una forma u otra
	}
	
	public static <T> T envíoDeTodo() { //envía cualquier tipo de dato, pero dará error si el que lo recibe no lo soporta
		return null;
		
	}

}
