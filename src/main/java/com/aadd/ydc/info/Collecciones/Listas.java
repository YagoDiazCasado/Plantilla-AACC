package com.aadd.ydc.info.Collecciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Listas {
	
	// NO SON SINCRONIZABLES!!!
	
/////////////////////////////////////////////////// LIST /////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Todas las listas pueden ser llamadas por LIST, pero en la declaracion se pone
	// de que tipo es la declaración(ArrayList,Vector,LinkedList...).
	public static void main(String[] args) {
//////////////////////////////////////////////////////////
//////////////////////////////////////////////// ARRAYLIST
		ArrayList<String> listaUno = new ArrayList<String>(); // en el paréntesis se podría añadir una longitud. Se deve
																// declarar siempre

		// ArrayList es una colección, lo que significa que puede usar comandos de
		// Collections.
		// Mantiene un órden y pueden existir repetidos.

		//////////////////////////////////////// Comandos:
		listaUno.add("Añado un valor");
		listaUno.add(0, "Nuevo valor"); // ahora la anterior posición 0 se ha desplazado a la uno
		listaUno.add(1, "suejeto 1");
		listaUno.add(2, "suejeto 2");
		listaUno.set(2, "muerto"); // machaca un valor y pone uno nuevo
		listaUno.size(); // devolverá 2, esto es el tamaño, lo que sería length en un array simple.
		listaUno.get(0); // devolverá el primer valor de la lista.
		listaUno.remove(0); // borra el primer valor de la lista.
		listaUno.remove("Añado un valor"); // borra el primer valor de la lista que coincida con el argumento.
		listaUno.contains("Añado un valor"); // devolverá false, porque ya no existe lo que busca
		listaUno.indexOf("Añado un valor"); // devolverá -1, porque no existe
		listaUno.indexOf("Nuevo valor"); // devolverá 0, que es la primera posición que tiene una coincidencia
		listaUno.lastIndexOf("suejeto 1"); // busca la coincidencia desde el final
		listaUno.isEmpty(); // returnea true si esta vacío, false si no
		listaUno.clear(); // borra todos los valores de la lista
		listaUno.toArray(); // convierte la lista en un array simple, puede almacenarlo un array simple
		listaUno.add("2");
		listaUno.add("1");

		/////////////////////////////////////// Comandos Collections
		Collections.sort(listaUno); // ordena la listas. Si es un objeto, ordenará segun el método compareTo,
									// sobrescrito en el objeto, sino no sirve.
		System.out.println(listaUno.toString());
		Collections.shuffle(listaUno);
		System.out.println(listaUno.toString());
		Collections.reverse(listaUno);
		System.out.println(listaUno.toString());
		System.out.println(Collections.min(listaUno));
		System.out.println(Collections.max(listaUno));

		// si querermos ordenar sin usar el compareTo() de los objetos, podemos usar el
		// compare:
		Comparator<String> comparadorDescendente = new Comparator<String>() { // abre corchte, contiene el criterio de
																				// la comparación, que sobreescribe la
																				// de collections
			@Override
			public int compare(String num1, String num2) {
				return num2.compareTo(num2); // Para orden descendente
			}
		};

		Collections.sort(listaUno, comparadorDescendente); // llamamos ahora al collections, pero añadimos el nuevo
															// criterio a la derecha. De esta forma podemos tener varios
															// tipos de orden.

		/////////////////////////////////////// Llamar al ArrayList:
		System.out.println(listaUno.size());
		System.out.println(listaUno.toString());

		/////////////////////////////////////// Recorrer el ArrayList
		for (int i = 0; i < listaUno.size(); i++) {
			System.out.println(listaUno.get(i));
		}

		// En un foreach lo que hay a la izquierda es un intérprete. por lo que lo que
		// le hagamos a él, se lo hará a su referencia en el ArrayList, incluso si son
		// objetos.
		System.out.println();
		for (String b : listaUno) { // debe de ser del tipo de lo que conmtiene el arrayLsit
			System.out.println(b); // si modifico o, modifico también el array original
		}

		Iterator<String> herramientaQueBusca = listaUno.iterator(); // creas un iterador para el arrayList.
		while (herramientaQueBusca.hasNext()) { // mientras exista uno más en la lista
			String elemento = herramientaQueBusca.next(); // imprime ese uno respecto al anterior
			System.out.println(elemento);
		} // pasa al siguiente elemento de la lista

//////////////////////////////////////////////////////////
/////////////////////////////////////////////// LINKEDLIST
		// Son MUY EFICIENTES !!
		// Almacena nodos con enlaces que apuntan al elemento siguiente y al anterior.
		// El primer y ultimo enclace estan libres.
		// La ventaja de esto es la eficiencia. En un arrayList, si borras la posicion
		// 3, los que hubiera por encima se reubican,
		// consume memoria. En cambio, en linked list si se elimina un nodo sólo se
		// cambia el enlace y no es necesario cambiar la ubicacion del resto de
		// elementos.
		// Sus caracteristicas son:
		/*
		 * -Acceder aleatoriamente 
		 * -Estan ordenadas y tienen posición 
		 * -Permiten añadir y eliminar 
		 * -ListIterator modifica en cualquier dirección
		 */

		List grupodeNodos = new LinkedList();

		// COMANDOS:

		grupodeNodos.add("nodo 1");
		grupodeNodos.add("nodo 2");
		grupodeNodos.add("nodo 3");

		grupodeNodos.removeFirst();
		grupodeNodos.removeLast();
		grupodeNodos.remove("nodo 2");

		grupodeNodos.add("nodo 0"); 
		grupodeNodos.add("nodo 1");
		grupodeNodos.add("nodo 2");
		grupodeNodos.add(2,"nodo 3"); // añade por posición
		grupodeNodos.size();
		System.out.println(grupodeNodos); // se puede imprimir directamente

		// Recorrer la lista con un ListIterator:<s

		ListIterator<String> it = grupodeNodos.listIterator(); // hereda de iterator

		// El iterador normal de listas sólo puede moverse hacia delante, este permite
		// ir también hacia detrás.

		while (it.hasNext()) { // aqui solo estaría usando los métodos del iterator normal
			System.out.println(it.next());
		}

		// El iterador apunta a la lista, asique si añadimos algo al iterador, se añade
		// a la lista

		// Como ya he usado el iterador, ahora está en la ultima posicion

		while (it.hasPrevious()) { 
			System.out.println(it.previous());
		}

		// Asique podemos ir hacia detrás gracias al metodo previous.
		// otra foirma de reiniciar el iterador es vovler a declararlo.

		it.next(); // ahora nos hemos posicionado en el hueco de la posición 1 en lugar de la cero,
					// por lo que actuamos sobnre la posición 0 y podemos añadir cosas por encima

		it.add("infiltrado en el hueco entre el 0 y el 1"); // lo introducirá en la posicion en la que esta ahora, que
															// es el hueco entre el elemento 0 y el 1. El puntero se
															// mueve 1 lugar adelante

		System.out.println(grupodeNodos);
	

		// El iterador puede borrar en el mismo lugar que está el puntero en el que nos
		// deje el next.
		it.previous();
		// Cuando añades un elemento la posición que le afecta es la previa, al revés
		// que normalmente que la posicion que altera el iterador es la que deja
		// inmediatamente atrás
		it.remove();
		System.out.println(grupodeNodos);
		grupodeNodos.removeAll(grupodeNodos);

		////////////////////////////////////// Lista circular circular
		// Para ocasiones en las que se deba dar vueltas hay varias maneras. Se utiliza
		////////////////////////////////////// el módulo de la longitud para qu ese de
		////////////////////////////////////// la vuelta.

//////////////////////////////////////////////////////////
////////////////////////////////////////// Arrays.asList()
		// Devuelve una lista que se basa en un array. La lista es "vista" sobre el
		// array, lo que significa que los cambios en la lista se reflejan en el array
		// subyacente.
	}
}
