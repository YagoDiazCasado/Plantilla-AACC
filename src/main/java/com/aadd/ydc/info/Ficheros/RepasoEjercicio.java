package com.aadd.ydc.info.Ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class RepasoEjercicio {

	public static void main(String[] args) throws Exception {
		// Lee un array de caracteres que forman frases e introducelos como palabras
		// enteras, sin contar espacios, en un archivo txt. Una linea para cada frase
		// Luego, lee el archivo y mete cada frase en un arraylist, un hueco para cada
		// frase.
		// Vas a crear un fichero de acceso aleatorio e introducir en el las frases
		// codificadas.
		// Después, leerás el fichero de acceso aleatorio y lo meterás a un nuevo
		// ficehro normal que debe de ser idéntico al primero que hicistes.

		ArrayList<String> frases = new ArrayList<String>();
		frases.add("primera de las frases");
		frases.add("segunda de las frases");
		frases.add("tercera de las frases");
		frases.add("cuarta de las frases");

		File documento = new File("Documento_1_.txt"); // no hace falta que lco createNewFIle, se crea al escribit en el
		crearTxT(frases, documento);
		System.out.println(documento.length());
		leerDoc(documento);

		RandomAccessFile lista = crearFicheroAccesoAleatorio(frases);

		File documento2 = convertirRandomANormal(lista);
		System.out.println(documento2.length());
		leerDoc(documento2);

		System.out.println("Los dos documentos son: " + documento.equals(documento2)
				+ ".\nEsto ocurre porque cuando los lees de un RAF, cada caraccter ocupa 2 bytes, mientras que en txt ocupan 1");
		System.out.println();

		File docCopiado = new File("Documento_C_.txt");
		FileOutputStream oCopiado = new FileOutputStream(docCopiado); // vamos a abrir un flujo a este doc
		copiarFichero(documento, oCopiado); // enviamos el flujo y el doc que copiaremos en el nuevo
		leerDoc(docCopiado);

	}

	private static void copiarFichero(File documento, FileOutputStream oCopiado) { // no returnea porque el puntero
																					// sigue apuntando a ellos
		try {
			FileInputStream o = new FileInputStream(documento);
			int b;
			while ((b = o.read()) != -1) { // mientras queda que copiar (o)
				oCopiado.write(b); // escribimos en la copia nueva lo que encuentra o
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				oCopiado.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	private static File convertirRandomANormal(RandomAccessFile lista) throws Exception {
		lista.seek(0);
		File envio = new File("Documento_2_.txt");
		FileWriter lienzo = null;
		BufferedWriter escritor = null;
		try {
			lienzo = new FileWriter(envio);
			escritor = new BufferedWriter(lienzo);
			while (lista.getFilePointer() < lista.length()) {
				for (int i = 0; i < 30; i++) {
					escritor.write(lista.readChar());
				}
				escritor.write("\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "ERROR");
		} finally {
			escritor.close(); // primero siempre el buffered
			lienzo.close();
			lista.close();
		}
		return envio;
	}

	private static RandomAccessFile crearFicheroAccesoAleatorio(ArrayList<String> frases) {
		try {
			RandomAccessFile lista = new RandomAccessFile("Documento_2_.dat", "rw");
			for (String a : frases) {
				StringBuffer frase = new StringBuffer(a);
				frase.setLength(30); // 60 bytes por frase.
				lista.writeChars(frase.toString());
			}
			return lista;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	private static File crearTxT(ArrayList<String> frases, File doc) throws IOException {

		FileWriter archivo = null;
		BufferedWriter escritor = null;

		try {
			archivo = new FileWriter(doc);
			escritor = new BufferedWriter(archivo);
			for (String a : frases) {
				escritor.write(a);
				escritor.write("\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			escritor.close(); // si no cierras lit no funciona el ecribir y nucna guarda el archivo
			archivo.close();
		}

		return doc;
	}

	private static void leerDoc(File documento) throws Exception {
		FileReader doc = null;
		BufferedReader o = null;
		try {
			doc = new FileReader(documento);
			o = new BufferedReader(doc);
			String linea = "";
			while ((linea = o.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			o.close();
			doc.close();
		}
		System.out.println();
	}

}
