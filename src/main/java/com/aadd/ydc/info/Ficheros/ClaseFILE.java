package com.aadd.ydc.info.Ficheros;

import java.io.File;

public class ClaseFILE {

//////////////////////////////////////////////////////////////////////// FICHEROS  /////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {

//Siempre se rodea por try y catch
		try {
// Usando doble barra diagonal. Si no se especifica la rita completa, sólo lo encontrará si el archivo está en la carpeta del proyecto.
			File nuevoArchivo = new File(
					"G:\\Mi unidad\\PC COMPARTIDOS\\Proyectos\\ORS-ArchivosCreadosInGame\\nuevo.txt"); // esto es un
																										// puntero al
																										// archivo, una
																										// proyección
//el tipo de ruta depende del sistema operativo, en linux las barras no son así
//se deben crear o buscar archivos de uno en uno
			File nuevoArchivo2 = new File(
					"G:\\Mi unidad\\PC COMPARTIDOS\\Proyectos\\ORS-ArchivosCreadosInGame\\Pruebas2");
//Es posible usar una ruta relativa.
			File rutaRelativaEjemplo = new File("src/FICHEROS"); // coge lo que hay en la carpeta del proyecto, relativo
																	// a él

////////////////////////////////////////////////////////////////////////////////COMANDOS
			nuevoArchivo.createNewFile(); // crea un archivo, si ya existe, lo sobreescribe !!CUIDADO!!
			nuevoArchivo2.mkdir(); // crea un directorio especificado .mkdirs(); crea incluso directorios neesarios entre medias, es decir, puede crear una carpeta que contiene otra carpeta.
			System.out.println("Existe la ruta? " + nuevoArchivo.exists());
			System.out.println("Es un directorio/carpeta? " + nuevoArchivo.isDirectory());
			System.out.println("Es un fichero? " + nuevoArchivo.isFile());
			System.out.println("Como se llama? " + nuevoArchivo.getName());
			System.out.println("Dónde está? " + nuevoArchivo.getParent()); // Nos devuelve el directorio de algo
			System.out.println("Cual es su ruta absoluta? " + nuevoArchivo.getAbsolutePath());
			System.out.println("Cual es su ruta canon? " + nuevoArchivo.getCanonicalPath());
			System.out.println("Cual es su ruta telativa? " + nuevoArchivo.getPath());
			System.out.println("Cómo de largo es su contenido? " + nuevoArchivo.length());
			System.out.println("Cúanto espacio le queda? " + nuevoArchivo.getFreeSpace());
			System.out.println("Cuanto espacio tiene total? " + nuevoArchivo.getTotalSpace());
			System.out.println("Es ejecutable? " + nuevoArchivo.canExecute());
			System.out
					.println("Cuando fue modificado por ultima vez? " + nuevoArchivo.lastModified() + " milisegundos");
			try {
				File[] grupoFicheros = new File(nuevoArchivo.getParent()).listFiles(); // lee todo lo que contenga un
																						// directorio
				for (File fichero : grupoFicheros) {
					System.out.println(fichero.getName());
				}
			} catch (Exception e) {
				e.getStackTrace();
			}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////GESTIÓN

//renameTo() sirve para cambiar el nombre de un archivo:
			File docOrigen = new File("G:\\Mi unidad\\PC COMPARTIDOS\\Proyectos\\ORS-ArchivosCreadosInGame\\Pruebas2");
			docOrigen.mkdir(); // si uso ruta relativa se crea automaticamente en la carpeta del proyecto
			File nuevoNom = new File("G:\\Mi unidad\\PC COMPARTIDOS\\Proyectos\\ORS-ArchivosCreadosInGame\\DOCS");
//ahora ejecutaremos el cambio:
			docOrigen.renameTo(nuevoNom); // rename no recibe una ruta, recibe un File
//como la carpeta DOCS no existe, sólo cambia el nombre de documentos a DOCS
//Si DOCS existiera no funciona.

//Crearemos una carpeta dentro de documentos
			File nuevoDoc = new File(
					"G:\\Mi unidad\\PC COMPARTIDOS\\Proyectos\\ORS-ArchivosCreadosInGame\\DOCS\\FOTOGRAFÍAS");
			nuevoDoc.mkdir(); // creo la carpeta

			System.out.println("Borrarlo? " + nuevoArchivo.delete()); // puede borrar de todo, incluso carpetas y todo
																		// lo que contengan

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////ESCRITURA 

//Quitar la extensión a un fichero?
//REcorro la ruta con un array de los ficheros a los que quiero cambiar o quitar la extensión.
//En cada elemento renombro el fichero haciendo un substring de su antiguo nombre EJ:
			File[] cambiador = docOrigen.listFiles();
			for (File o : cambiador) {
				String nombre = o.getName().substring(0, o.getName().indexOf("."));
				o.renameTo(new File(o.getParent() + "\\" + nombre)); // si estuviera usando rutas relativas, usaría / en
																		// vez de \\
			}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////LECTURA 

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
