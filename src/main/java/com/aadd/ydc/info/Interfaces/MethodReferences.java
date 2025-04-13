package com.aadd.ydc.info.Interfaces;

import java.util.ArrayList;
import java.util.Random;

public class MethodReferences {

	public static void main(String[] args) {
		// En ocasiones queremos almacenar métodos para usarlos dinámicamente,
		// Pero, SORPRESA!, no son objetos de ningun tipo.

		// Esto es una interfaz, lo cual nos permitirá hacer referencia a cosas que no
		// son objetos (entre otras cosas)

		ArrayList<Habilidad> listadeMetodos = new ArrayList<Habilidad>();
		listadeMetodos.add(Habilidad.habilidadEspecial());

		System.out.println(listadeMetodos.get(0).aplicar()); // para ver lo que contiene llamamaos al abstracto

		// Para pasar una llamada de referencia sin que se ejecute, o queremos que el
		// método se ejecute cada vez que se pase como parámetro,
		// NO SE PUEDE llamar como Habilidad.habilidadEspecial();

		usarMetodoVariasVeces(Habilidad.habilidadEspecial()); // De esta manera pasamos el resultado, no la ref del
																// método, no queremos eso.
		usarMetodoVariasVeces(() -> Habilidad.habilidadEspecial().aplicar()); // Va a salir diferente porque pasamos la
																				// ref y no el resultado

		// Ese paréntesis es lo que usamos para indicar que es una REFERNCIA!!

		// Es importante saberlo si queremos manipular cosas en estos métodos de
		// interfaz.
		// Porque sino sólo se ejecutarán la primera vez que los llamemos, pues el
		// parámetro será el resultado y no el propio método.

	}

	private static void usarMetodoVariasVeces(Habilidad s) {
		System.out.println(s.aplicar());
		System.out.println(s.aplicar());
		System.out.println(s.aplicar());
	}

	@FunctionalInterface
	interface Habilidad {
		String aplicar();
		// Esto es un método abstracto aunque no lo ponga.
		// Siempre debe lelvarlo una interfaz funcional

		static Habilidad habilidadEspecial() { // metemos dentro los métodos
			Random dado = new Random();
			return () -> "" + dado.nextInt(1, 20);
		}
	}

}
