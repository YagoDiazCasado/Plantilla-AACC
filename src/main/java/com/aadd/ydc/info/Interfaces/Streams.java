package com.aadd.ydc.info.Interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {

/////////////////////////////////////////////////// STREAMS /////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// El stream es una "vista" de una lista. Como lo haríamos en sql
	// Sólo pueden usarse una vez, después debe crearse de nuevo.

//	map(): Transforma cada elemento de la secuencia para devolverlo, pero no altera directamente la lista. 
	// Para map es normal utilizar .map(Objeto::atributo que quiero de cada objeto
	// de la lista) para crear una
	// lista de String, por ejemplo, a partir de una lista de objetos.
	// Se puede usar también como operacion .map(n -> n+1) para aplicar +1 a cada
	// hueco de la lista
//	filter(): Filtra los elementos según una condición.
//	distinct(): Elimina duplicados.
//	sorted(): Ordena los elementos.
//	limit(): Limita la cantidad de elementos.

	static List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
	List<Integer> evenNumbers = numbers.stream().filter(n -> n % 2 == 0) // Filtra números pares
			.collect(Collectors.toList()); // Esto lo empaqueta para poder guardarlo en una lista

	// Operaciones como el collecto son:

//	collect(): Recoge los elementos del Stream en una colección o estructura de datos.
//	forEach(): Aplica una acción a cada elemento del Stream. A diferencia del .map(), este no devuelve un objeto
//	reduce(): Realiza una operación acumulativa sobre los elementos (por ejemplo, suma o multiplicación).
//	count(): Cuenta el número de elementos en el Stream.
//	anyMatch(), allMatch(), noneMatch(): Verifican si algún, todos o ninguno de los elementos cumplen una condición.

	int sum = numbers.stream().reduce(0, (a, b) -> a + b); // Suma de los elementos

	// Lo normal es utilizar los streams combinandolos.
	// Por ejemplo, primero usar.filter(*condicion), juntarlo con .map(*cambio) para
	// alterar ese grupo filtrado, y luego forEach(Syso) para mostrar cada uno

	static { // Sin aplicar cambios
		numbers.stream().filter(n -> n >= 1).map(n -> n + 1).forEach(n -> System.out.print(n));
		// No es necesario almacenar el resultado, ya que solo se imprimen los valores.
		// Importante: .map() no modifica la lista original, solo transforma los
		// elementos en el Stream.
	}

	static List<Integer> updatedNumbers;

	static { // Aplicando cambios
		updatedNumbers = numbers.stream().filter(n -> n >= 1).map(n -> n + 1).collect(Collectors.toList());

		System.out.println(updatedNumbers); // Imprime la nueva lista
	}

}
