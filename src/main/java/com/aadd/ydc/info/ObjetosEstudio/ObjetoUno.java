package com.aadd.ydc.info.ObjetosEstudio;



public class ObjetoUno implements Comparable<ObjetoUno>{
	public String nombre; //declaramos atributos, pueden o no estar inicializados. Public es una putita para todos
	private int cantidad; //private no permitirá al atributo ser visto ni manipulado por nadie sin eprmiso.
	protected boolean enStock; //protected es como private, pero lo podrán manipular los hijos de este objeto que lo hereden.
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Constructor	 
	public ObjetoUno() { //esto es el constructor, es lo que se ejecutará cuando crees un objeto, y este nacera con lo que ponga aqui dentro
		this.nombre = "nombre predeterminado";
		this.cantidad = 1;
		this.enStock = true;
	}
	public ObjetoUno(String nom, int cant, boolean esta) { //se puede sobrecargar el constructor, pero tiene que tener diferencias en la firma.
		this.nombre = nom; //si al inicializar ponermos parámetros al objeto, usará este constructor, sino, el otro.
		this.cantidad = cant;
		this.enStock = esta;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Getter y Setters
	//Tenemos un atributo en private y otro en protected, asique, para poder usarlos desde un posible main:
	
	public void setCantidad(int cant) {
		this.cantidad = cant; //siempre usar el this., es buena praxis.
	}
	public int getCantidad() {
		return this.cantidad;
	}
	public void setEnStock(boolean esta) {
		this.enStock = esta;
	}
	public Boolean getEnStock() {
		return this.enStock;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Métodos 
	public static void metodoDeEstaClase() { //pertenece a la clase, asique se le llame con el nombre de la clase
		
	}
    public void metodoDeCadaObjetoQueSeInstancie() { //pertenece a cada objeto que se instancie, asique se le llama con el nombre de cada objeto, son propios.
		
	}	
    
    //Se pueden hacer toda clase de cosas con métodos, desde cambiar los atributos iniciales del objeto hasta crear nuevos objetos.
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Métodos Override e Importantes
    //Estos métodos sobreescriben a otros que ya existen, en herencia lo explicaremos desde otro psrisma.
    //Un método sobreescrito de java suele significar poder empezar a usar ciertas funciones para solucionarnos la vida:
    
    @Override
    public boolean equals(Object o) { //se usa para buscar en arrayList de objetos o HashMaps con keys que sean objetos. Java usa equals para encontrar la coincidencia
    	if( o instanceof ObjetoUno) { //instanceof devuelve true si ese object nace de la misma clase que la que hay
    		ObjetoUno oTransformado = (ObjetoUno) o;
    		return (oTransformado.nombre.equals(this.nombre))?true:false; //criterio para considerar que un objeto es igual a otro.
    	}else {
    		return false;
    	}    	
    }
    
    @Override
    public String toString() { //al llamar al objeto para usarlo como String (para comparar, buscar etc...), el objeto enviará el toString sin necesidad de poner nada
		return nombre;	
    }
    
    public int compareTo(ObjetoUno o) { //lo utilizará sobre todo la clase Collections de java, es lo que necesitamos para poder ordenar arrayList que contengan este objeto.
    	return Integer.compare(this.cantidad, o.getCantidad()); //indicamos el criterio de orden, returneará 0 si es igual, negativo si es menor y positivo si mayor.
    } //solo servirá si en la firma de la clase se pone "implements Comparable<*nombre del objeto comparable>"
   
    
    

}
