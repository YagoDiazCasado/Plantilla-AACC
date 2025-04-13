package com.aadd.ydc.info.Ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Leer {
//////////////////////////////////////////////////////////////////////// LEER  /////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		// BUFFERED READER Y FILE READER. Hay que cerrarlo SIEMPRE.
		try {
			FileReader fr = new FileReader("nuevo.txt"); // siempre va con el buffered, el file es el puntero para leer.
			BufferedReader br = new BufferedReader(fr); // el buffered reader amortigua el file, siempre van juntos. El
														// buffer es el que lee
			String linea;
			while ((linea = br.readLine()) != null) { //asigna a linea una linea completa del archivo hasta que no queden
				System.out.println(linea);//linea se renombra  Entiende \n y \t
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			System.out.println("FAllO");
		}

	}
	
	public static void imprimirContenido(File a) {
		if(a.isDirectory() && ((File) a).exists()) {
			File[] j = a.listFiles(); //las carpetas pueden vertir sus contenidos en un array
			Arrays.sort(j); //es ordenable
			for(File u : j) {
				System.out.println(u.getName() +" "+ u.length()/1024);//podemos ver cuant opesa el archivo, dividiendo los bytes
			}
		}
	}

}
