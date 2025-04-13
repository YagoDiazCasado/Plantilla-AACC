package com.aadd.ydc.info.Bascio;
import java.util.Arrays;
import java.util.List;

public class ArraysClasicos {
/////////////////////////////////////////////////// ARRAYS ///////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
//////////////////////////////////////////////////////////
////////////////////////////////////////////////// QUE ES?
		// UItilizan la librería Arrays. Por lo mismo, pueden usar los métodos.
		// No tienen propios, como las collecciones.
		int[] ejemplo = { 5, 4, 3, 6 };
		System.out.println(Arrays.toString(ejemplo));
		// Los arrays apuntan a una dirección de memoria.
		// Por lo que igualar dos, solo hace que ambos apunten a la misma info
		System.out.println("Este numero es la direccion de memoria: " + ejemplo);
		int[] ejemplo2 = ejemplo;
		// No he clonado el array, sino que he creado otro nombre para el mismo.
		ejemplo2[2] = 4;
		System.out.println(Arrays.toString(ejemplo));
		// Ves, he cambiado el ejemplo modificando el ejemplo2

//////////////////////////////////////////////////////////
////////////////////////////////////////// LIBRERIA ARRAYS	

		// El Arrays.toString() funciona para imprimir porque convierte a String
		int[] arr = { 1, 2, 3, 4, 5 };
		String str = Arrays.toString(arr);
		System.out.println(str);

		// Los arrays de String pueden convertirse a Listas, pero no los de int
		String[] ejemplo3 = { "hola", "que tal" };
		List<String> listaEjemplos = Arrays.asList(ejemplo3);
		System.out.println(listaEjemplos);

		// Existe también la ordenacion automática
		Arrays.sort(ejemplo);
		System.out.println(Arrays.toString(ejemplo));

		// Llenado automático
		int[] arr2 = new int[5];
		Arrays.fill(arr, 120);
		System.out.println(Arrays.toString(arr));

		// Copiar un array. Se mete el array del que se copia y la longitud desde el 0
		int[] copy = Arrays.copyOf(ejemplo, 3);
		System.out.println("copia "+Arrays.toString(copy));
		// Esta vez ya no apuntan a la misma dirección de memoria

		// Incluso podemos encontrar el maximo y el minimo
		int min = Arrays.stream(ejemplo).min().getAsInt();
		int max = Arrays.stream(ejemplo).max().getAsInt();
		System.out.println("Min: " + min + ", Max: " + max);
		
//////////////////////////////////////////////////////////
////////////////////////////////////////// Array Copy
		
		


	}

}
