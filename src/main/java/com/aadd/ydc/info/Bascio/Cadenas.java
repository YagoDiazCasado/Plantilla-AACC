package com.aadd.ydc.info.Bascio;
import java.util.Arrays;

public class Cadenas {
/////////////////////////////////////////////////// CADENAS //////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
//////////////////////////////////////////////////////////
//////////////////////////////////////////////////QUE ES?
		// Las cadenas String son tipos complejos. Por la misma razón tienen su propios
		// métodos
		// Son a todas luces arrays de caracteres, de ahi que tenga un método
		// (toCharArray()) que lo hace.
		// Se usan con "" a diferencia de los char '', no confundir con Character, la
		// clase compleja de char.
		// Dentro de un String se puede meter todo, int, char, boolean ...
		Boolean k = true;
		String j = "hola" + k;
		System.out.println(j);
		// Incluso acepta otros tipos complejos
		Integer i = 55;
		String h = "numero = " + i;
		System.out.println(h);

		// Los String se usan para casi todo, sobretodo en ficheros, asique es
		// importante manejarlas :)

//////////////////////////////////////////////////////////
///////////////////////////////////////// ESPACIOS y SPLIT
		// Los espacios son char que contienen vacío, pero no son null. Como 0 != null.
		// Si se repiten en el número que sea, esto:
		String b = "hola     que tal";
		String[] z = b.split(" ");
		// No serbirá:
		System.out.println(Arrays.toString(z));
		// En cambio, si usamos "\\s+":
		String[] y = b.split("\\s+");
		System.out.println(Arrays.toString(y));

//////////////////////////////////////////////////////////
///////////////////////////////////////////////////// TRIM
		// Trim lima los laterales de una cadena de texto, quita los espacios
		String a = "   hola   ";
		System.out.println(a.trim());
		// Para que la cadena conserve el cambio, debe igualarse de nuevo
		a = a.trim();
		System.out.println(a);

//////////////////////////////////////////////////////////
////////////////////////////////////////////////// INDEXOF 
		// IndexOf busca la primera aparición de una CADENA dentro de un STRING.
		String c = "hola que tal";
		System.out.println(c.indexOf("tal"));
		// Si no encuentra, devuelve -1
		System.out.println(c.indexOf("pepe"));
		// Incluso si contiene las palabras, pero no en ese orden
		System.out.println(c.indexOf("tal que hola"));
		// Es un valor numérico, por lo que se puede guardar en Integers

//////////////////////////////////////////////////////////
//////////////////////////////////////////////// SUBSTRING 
		// Permite hacer cadenas más pequeñas de una mayor
		String d = "hola que tal";
		// Puede ser con inicio y final dado o con inicio y fianl total (no se pone)
		System.out.println(d.substring(0, 3));
		System.out.println(d.substring(4));
		// El número de final queda excluido. El inicio si entra

//////////////////////////////////////////////////////////
///////////////////////////////////////////////////// CHAR 
		// Los char pueden tener valor numérico.
		// Se guian segun la tabla ASCII, asi podemos hacer mayusculas.
		// Podemos contar sin tener que cambiar a int, ej: 0 = 48 (ASCII)

//////////////////////////////////////////////////////////
////////////////////////////////////////////////// CHAR AT

//////////////////////////////////////////////////////////
/////////////////////////////////////////// ARRAYS DE CHAR

//////////////////////////////////////////////////////////
///////////////////////////////////////////////// VALUE OF

//////////////////////////////////////////////////////////
//////////////////////////////////////////// CONCATENACION
	}

}
