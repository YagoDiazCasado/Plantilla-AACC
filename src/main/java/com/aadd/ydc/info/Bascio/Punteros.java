package com.aadd.ydc.info.Bascio;

import java.util.Arrays;

public class Punteros {

	public static void main(String[] args) {

		int[] numeros = new int[5];
		for (int num = 0; num < numeros.length; num++) {
			numeros[num] = 1;
		}
		System.out.println(Arrays.toString(numeros));
		
		int [] z = numeros;
		z[0] = 2;
		
		System.out.println(Arrays.toString(numeros));

	}

}
