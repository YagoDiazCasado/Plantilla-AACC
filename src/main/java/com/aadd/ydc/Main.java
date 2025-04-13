package com.aadd.ydc;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {

		// System.out.println(convert("PAYPALISHIRING", 3));

	}

	public static String convert(String s, int numRows) {
		int longitud = s.length();
		if (longitud <= numRows || numRows == 1) {
			return s;
		}

		List<StringBuilder> partes = new ArrayList<>();
		for (int i = 0; i < numRows; i++) {
			partes.add(new StringBuilder());
		}

		int fila = 0;
		boolean bajando = false;

		for (char c : s.toCharArray()) {
			partes.get(fila).append(c);

			if (fila == 0 || fila == numRows - 1) { // Esto es un interruptor con dos modos
				bajando = !bajando;
			}

			fila += bajando ? 1 : -1;
		}

		StringBuilder resultado = new StringBuilder();
		for (StringBuilder parte : partes) {
			resultado.append(parte);
		}

		return resultado.toString();
	}
}
