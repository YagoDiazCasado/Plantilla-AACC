package com.aadd.ydc.info.Interfaces;

public class Basico {

	public static void main(String[] args) {
//////////////////////////////////////////////// Interfaces /////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Se las confunde con clases abstractas, por el hecho de tener métodos
		// abstractos y no poder instanciarlo como tal.
		// Como las clases en JAVA solo pueden heredar de 1 clase a la vez, el resto de
		// herecias las debemos hacer con interfaces, que son infinitas.

		// La interfaz es una colección de métodos abstractos y valores constantes. No
		// puede hacer nada por sí sola, sólo la puede utilizar la clase hija que la
		// implemente. A diferencia de la herencia, una clase puede recibir varias
		// implementaciones

		// Una clase invocada desde su interfaz, como por ejemplo:
		// IEntidadDao d = new EntidadDao();
		// Hemos creado el objeto a traves de la interfaz que implementa.
		// Como resultado, no podremos acceder a ningun metodo que no tenga la interfaz
		// Sirve especialmente para estandarizar procesos y simplificar cosas.
		// En el patron observer es crucial, ya que , otra mecanica de las interfaces
		// es:
		// Independientemente de quien las implementa, pueden ser metidas en una misma
		// lista, o llamadas por lem ismo metodo.
		// Por ejemplo, si caja y bola implementan la misma interfaz, pueden estar en
		// una lsita de figuras.
		// Similar a lo que ocurría con herencia.
		// Además, podrán llamar al mismo metodo por el mismo nombre si iteramos la
		// lista.

//////////////////////////////////////////////////////////
/////////////////////////////////// Interfaces funcionales

		// La interfaz que contenga métodos abstractos obligará a la hija a
		// reescribirlos con Override

		EjemploFuncional.decirHola(); // aqui no nos dirá nada porque esto solo es la referencia
		EjemploFuncional.decirHolap().run(); // si utilizamos el método abstracto sí que actúa
		System.out.println(EjemploFuncional.isValid("Hola"));

	}

// Son aquellas que sólo tienen un método abstracto.
// Normalmente ese método ejecuta otros que no son abstractos, como si fuera un
// amo de llaves dentro de la carcel que sería la interfaz

// Interfaz funcional
	public interface EjemploFuncional {
		void run(); // el único método abstracto que tendrá y habrá que implementarlo, aunque sino
					// aún podremos usar los métodos estáticos de esta interfaz.

		static EjemploFuncional decirHola() { // Es estático asique podemos usarlo sin implementar nada
			return () -> System.out.println("Hola"); // cuando se ejecuta el método decirHola().run() esto es lo que
														// devuelve run.
			// Esto pasa porque instancia una copia de EjemploFuncional e implementa el
			// método run a la vez, por eso de esta manera no es necesario implementar la
			// interfaz en ningún sitio Y por eso usamos el return () ->. Báscicamente,
			// rellenamos el run con lo que hace decirHola.
		}

		static EjemploFuncional decirHolap() {
			return () -> System.out.println("pepe");
		}

		// Método default (opcional)
		default String getInfo() { // si no es estático podemos usar lo que queramos, pero para usar este debemos
									// implementar la interfaz
			return "Información predeterminada";
		}

		// Método estático (opcional)
		static boolean isValid(String input) {
			return input != null && !input.isEmpty();
		}
	}

// Interfaz normal
	public interface EjemploNormal {
		void metodo1(); // métodos abstractos

		int metodo2(int parametroPosible); // metodo abstracto con param y return

		default void metodo3() { // no tendrá que ser sobreescrito por la clase hija
			System.out.println("Método default");
		}

		static void metodo4() { // pertenece a la interfaz
			System.out.println("Método estático");
		}
	}

}
