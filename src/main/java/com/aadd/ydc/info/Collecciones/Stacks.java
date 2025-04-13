package com.aadd.ydc.info.Collecciones;

import java.util.Stack;

public class Stacks {
/////////////////////////////////////////////////// STACKS ///////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Estructuras lineales LIFO
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();

		// Push sirve para introducir valores
		stack.push(10); // posicion 0
		stack.push(60); // posicion 1
		stack.push(30); // ...

		// Size como en casi todas las collecciones, da el tamaño
		System.out.println("Cual es el tamaño de esta pila? " + stack.size());

		// Peek devuelve el valor en la cima de la pila, el último
		System.out.println("Elemento en la cima de la pila: " + stack.peek()); // Output: 30

		// Pop elimina un elemento de la cima y lo devuelve por si se quiere usar, pero
		// ya no esta en la pila
		System.out.println("Elemento que sale de la pila: " + stack.pop()); // Output: 30

		// IsEmpty devuelve true or false en el caso de estar o no vacío
		System.out.println("Esta vacío? " + stack.isEmpty()); // Output: false

		// Elimino los que queden
		stack.pop();
		stack.pop();

		// IsEmpty
		System.out.println("Vacío? " + stack.isEmpty()); // Output: true

	}

}
