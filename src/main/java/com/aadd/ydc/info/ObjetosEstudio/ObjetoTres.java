package com.aadd.ydc.info.ObjetosEstudio;

public class ObjetoTres extends ObjetoDos{
	
	public ObjetoTres() {
		super();
	}
	public ObjetoTres(String nom, int cant, boolean esta) {
		super(nom,cant,esta); 
	}

	@Override
	public void eliminarCant() { //Es Override porque la clase de la que heredamos nos obliga a hacer nuestra versión personalizada.
		// TODO Auto-generated method stub
		
	}

}
