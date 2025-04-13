package com.aadd.ydc.excepciones;

/*
 *   EJEMPLO USO:
 *   
 *   try (Session session = GestorConexionHibernate.getSession()) {
            return session.get(Pais.class, entidad.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExamenException(e.getMessage(), ExamenException.ERROR_BUSQUEDA, "Pais Hibernate");
        }
 * */

public class ExamenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// Códigos de error para identificar la causa
	public static final long ERROR_CREACION = 0;
	public static final long ERROR_ACTUALIZACION = 1;
	public static final long ERROR_ELIMINACION = 2;
	public static final long ERROR_BUSQUEDA = 3;
	public static final long ERROR_FECHAS = 4;
	public static final long ERROR_IMAGEN = 5;
	public static final long ERROR_URL = 6;
	public static final long ERROR_OTRO = 7;

	// Variable que guardará el tipo de error
	private final long codigoError;

	// Variable que guardará el nombre de la clase
	private final String nombreClase;

	// Constructor sin argumentos
	public ExamenException() {
		super("Ha ocurrido un error relacionado con MapaMundi en MongoDB.");
		this.codigoError = -1;
		this.nombreClase = "";
	}

	// Constructor con mensaje y código de error (Este usaré habitualmente)
	public ExamenException(String mensaje, long codigoError, String nombreClase) {
		super(mensaje);
		this.codigoError = codigoError;
		this.nombreClase = nombreClase;
	}

	// Constructor con mensaje, código de error y causa
	public ExamenException(String mensaje, long codigoError, String nombreClase, Throwable causa) {
		super(mensaje, causa);
		this.codigoError = codigoError;
		this.nombreClase = nombreClase;
	}

	// Constructor con solo causa
	public ExamenException(Throwable causa) {
		super(causa);
		this.codigoError = -1;
		this.nombreClase = "";
	}

	// Getter para el código de error
	public long getCodigoError() {
		return codigoError;
	}

	// Método para obtener una descripción del código de error
	public String getDescripcionError() {
		switch ((int) codigoError) {
		case (int) ERROR_CREACION:
			return "Error durante la creación.";
		case (int) ERROR_ACTUALIZACION:
			return "Error durante la actualización.";
		case (int) ERROR_ELIMINACION:
			return "Error durante la eliminación.";
		case (int) ERROR_BUSQUEDA:
			return "Error durante la búsqueda.";
		case (int) ERROR_FECHAS:
			return "Error en el manejo de las fechas.";
		case (int) ERROR_IMAGEN:
			return "Error en el manejo de la imagen.";
		case (int) ERROR_URL:
			return "Error en el tratamiento de la url.";
		case (int) ERROR_OTRO:
			return "Error desconocido pero controlado.";
		default:
			return "Error desconocido y no manejado.";
		}
	}

	// ToString para darle forma a la excepción
	@Override
	public String toString() {
		return "Examen Exception {\nClase = " + this.nombreClase + "\nCódigo = " + codigoError + "\nMensaje = "
				+ getMessage() + "\nDescripcion = " + getDescripcionError() + "\n}";
	}

}