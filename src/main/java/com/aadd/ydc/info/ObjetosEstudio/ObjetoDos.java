package com.aadd.ydc.info.ObjetosEstudio;

public abstract class ObjetoDos extends ObjetoUno { 
	//todos los atributos del padre existen en este, pero además, este puede tener extras.
	private int cant2; //como este, aunque es ejemplo porque no servirá mas que para explciar.

	public ObjetoDos() {
		super(); //llamo al constructor del que hereda
		this.cant2 = super.getCantidad(); //tenemos que llamar al get si el atributo del padre es privado.
		//System.out.println(this.cantidad); no podemos porque cantidad no está publica o protected, en privado ni siquiera deja.
		System.out.println(cant2);
	}
	public ObjetoDos(String nom, int cant, boolean esta) {
		super(nom,cant,esta); //llamo al constructor del que hereda.
	}
	
	public void modCant(int ca) {
		super.setCantidad(ca);
		System.out.println(super.getCantidad()); //modificamos el super, pero es parte de este objeto, es parte de la INSTANCIA.
	}
	
	abstract public void eliminarCant(); //declaramos la clase abstracta en la firma. Esto es un metodo abstracto
	//Significa que todos los objetos que hereden de este, deberán tener este método obligados, y no de cualquier forma, sino personalizado.
	
	
}
