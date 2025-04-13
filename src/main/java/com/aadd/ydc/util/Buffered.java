package com.aadd.ydc.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Buffered {

	// Lee un archivo de texto y devuelve su contenido como una lista de líneas
	public static List<String> readFile(String filePath) throws IOException {
		List<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		}
		return lines;
	}

	// Escribe una lista de líneas en un archivo (sobrescribe el existente)
	public static void writeFile(String filePath, List<String> lines) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
			for (String line : lines) {
				bw.write(line);
				bw.newLine();
			}
			bw.flush();
		}
	}

	// Agrega una lista de líneas al final de un archivo (sin sobrescribir)
	public static void appendToFile(String filePath, List<String> lines) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
			for (String line : lines) {
				bw.write(line);
				bw.newLine();
			}
			bw.flush();
		}
	}

	// Lee un archivo y lo imprime por consola
	public static void printFile(String filePath) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}
	}

}
