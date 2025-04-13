package com.aadd.ydc.info.Interfaces;

public class ExpresionesLambda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Supongamos que tenemos una interfaz como Operacion, está abajo puesta.
		// Ahora, la expresiónLambda nos ayudará a implementarla en nuestro código.

		Operacion suma = (a, b) -> a + b; // hemos asignado utilidad a los parametros, antes no hacían nada
		// Una vez implementada correctamente:
		System.out.println(suma.operar(3, 5)); // ahora podemos usarlo como cualquier otra interfaz funcional

		// Es útil porque operar no es un método siquiera, sólo un parametro al que
		// luego le podemos dar utilidad al implementar la interfaz.
		// Por ejemplo, voy a implementar más metodos:

		Operacion multiplicacion = (a, b) -> a * b;
		System.out.println(multiplicacion.operar(3, 5)); // llamo a la nueva implementacion, entonces obedece a la nueva
															// directriz

		System.out.println(suma.operar(3, 5)); // puedo seguir llamando a la anterior

		// Si te lo preguntas, eso que hemos estado llamando para decirle que hacer

		Operacion.saludar("yago");

	}

	@FunctionalInterface
	interface Operacion {
		int operar(int a, int b); // Como es una funcional, sólo puede tener un método abstracto a implmentar
		// si queremos más metodos, tendrán que ser estáticos

		static void saludar(String o) {
			System.out.println("hola " + o);
		}
	}

}
