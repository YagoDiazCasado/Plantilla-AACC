package com.aadd.ydc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.aadd.ydc.dao.IEntidadDao;
import com.aadd.ydc.util.gestores.GestorMongoDb;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.sun.net.httpserver.Filter;

public class MongoDaoEjemplo implements IEntidadDao<Object> {

	public MongoCollection<Document> c = GestorMongoDb.getCollection("pais");
	
	// Podemos poner en el append "_id" y poner nuestro propio id, asi evitamos que mongo pornga el que quiera
	// 

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List obtenerTodos() {
		List<Document> results = new ArrayList<>();
		FindIterable<Document> iterable = c.find();
		for (Document doc : iterable) {
			results.add(doc);
		}
		return results;
	}

	@Override
	public Object obtenerPorId(Object obj) {
//		Document doc = c.find(Filters.eq("_id", obj.getIdentificador)).first();
//		obj.setX(doc.getString("clase")); Le metes los atributos
		return obj; // y devuelves el obj

	}

	@Override
	public void insertar(Object obj) {
		Document doc = new Document("clase", obj.getClass());
		c.insertOne(doc);
	}

	@Override
	public void actualiza(Object obj) {
//		Document doc = new Document().append("clase", obj.getClass());
//		c.updateOne(Filter.eq("id", obj.getId()), new Document("$set", doc)); // Esto es necesario.
	}

	@Override
	public void eliminar(Object obj) {
//		Bson filtro = Filters.eq("id", obj.getId());
//		c.deleteOne(filtro);

	}

}
