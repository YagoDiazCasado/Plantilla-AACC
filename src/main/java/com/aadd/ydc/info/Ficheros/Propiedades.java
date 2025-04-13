package com.aadd.ydc.info.Ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Propiedades {
//////////////////////////////////////////////////////////////////Properties //////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
//Son como archivos / hashMaps
//Almacenan informacion clave valor

//Para leerlos, es necesario declarar una isntancia de Propierties:

		Properties prop = new Properties();

		// Ahora necesitamos un archivo qu eleer, el archivo propieties en si

		File archP = new File("alumno_notas.dat");

		// Ahora, lo leeremos. No es estrictamente de texto, asique podemos usar streams
		// o FileReader (este es mas correcto)

		try {
			InputStream o = new FileInputStream(archP);
			prop.load(o);

			// Ya tenemos la información en su formato clave valor reconocido.
			// Toca leerlo como si buscasemos en un hashMap

			System.out.println(prop.getProperty("la clave que busques")); // devolverá el valor

			// Tiene más métodos similares a los de un hashMap.
			// Estos ficheros tampoco tienen un orden

//////////////////////////////////////////////////////////
//////////////////////////////////////////////// Crearlo

			// Se guardan claves sin posicion
			Properties props = new Properties();
			props.setProperty("clave1", "valor1");
			props.setProperty("clave2", "valor2");

			// Guardar en un archivo
			OutputStream g = new FileOutputStream("nuevoArch.dat");
			props.store(g,
					"Aqui puede ir un comentario dentro del archivo, como un disclimer que saldrá arriba del todo. Suele ponerse null");
			// Y ya esta creado :)

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
