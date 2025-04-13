package com.aadd.ydc.util.gestores;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class GestorMongoDb {

	private static MongoClient mongoClient;
	private static MongoDatabase database;

	static {
		String uri = GestorFicheroConfiguracion.devolverCredencial("URLmongo");
		mongoClient = MongoClients.create(uri);
		database = mongoClient.getDatabase(GestorFicheroConfiguracion.devolverCredencial("BASEmongo"));
	}

	public static MongoCollection<Document> getCollection(String collectionName) {
		return database.getCollection(collectionName);
		// si no existía la crea
		// a esta lista: collection.insertMany(list<Document); para meter varios a la
		// tabla
		// a esta lista: collection.deleteMany(Filters.eq("campo", "valor")); para
		// borrar varios de la tabla
		// a esta lista: collection.updateMany(Filters.eq("campo", "valor"),
		// Updates.set("nuevoValor", "nuevoValor")); para modificar varios de la tabla
		// a esta lista: collection.find(Filters.eq("campo",
		// "valor")).forEach(System.out::println); para leer varios de la tabla
		// si queremos solo a uno: collection.insertOne(Document); etc...

		/*
		 * Creacion de documentos:
		 * 
		 * Document documento = new Document("nombre", "Juan") .append("edad", 25)
		 * .append("ciudad", "Madrid");
		 * 
		 * 
		 * Por ejemplo. Lo que hemos hecho es hacer un Json.
		 */
	}

	public static void insertGroup(MongoCollection<Document> c, List<Document> documents) {
		c.insertMany(documents);
	}

	public static void removeGroup(MongoCollection<Document> c, Bson filtro) {
		c.deleteMany(filtro); // Borrara todos los que cumplan el filtro
		// El Bson es en plan:
		// Bson filtro = Filters.and(
		// Filters.eq("nombre", "Juan"), //esto es como equals eq
		// Filters.gt("edad", 20) // Mayor que 20, es greater gt
		// );
		// Luego se lo metes al .find() del collecion y listo.
		// Si usamos Filters.empty(); es como decir all, es decir, no filtra.
	}

	public static void updateGroup(MongoCollection<Document> c, Bson a_estos, Bson les_aplicas_esto) {
		c.updateMany(a_estos, les_aplicas_esto);
	}

	public static List<Document> findAll(MongoCollection<Document> c) {
		List<Document> results = new ArrayList<>();
		FindIterable<Document> iterable = c.find();
		for (Document doc : iterable) {
			results.add(doc);
		}
		return results;
	}

	public static List<Document> findByFilter(MongoCollection<Document> c, Bson filter) {
		List<Document> results = new ArrayList<>();
		FindIterable<Document> iterable = c.find(filter);
		for (Document doc : iterable) {
			results.add(doc);
		}
		return results;
		// para acceder a atributos de cada uno. usas el document.get( "_id",
		// class.class)
	}

	public static void close() {
		if (mongoClient != null) {
			mongoClient.close();
		}
	}
}
