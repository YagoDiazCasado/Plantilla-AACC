package com.aadd.ydc.dao.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.aadd.ydc.dao.IEntidadDao;
import com.aadd.ydc.util.gestores.GestorMongoDb;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class EntidadDaoMongo<T> implements IEntidadDao<T> {

	private final Class<T> clase; // Aqui guardaremos el tipo de clase que se usa. Por eso no hay metodos staticos
	private final MongoCollection<Document> collection;
	public Map<String, Method> getters;

	public EntidadDaoMongo(Class<T> clase) {
		this.clase = clase;
		this.collection = GestorMongoDb.getCollection("c_" + clase.getName().toLowerCase());

		getters = new HashMap<>();
		for (Method method : clase.getDeclaredMethods()) {
			if (method.getName().startsWith("get")) {
				String nom = method.getName().substring(3).toString();
				getters.put(nom.toLowerCase(), method);
			}
		}

	}

	@Override
	public List<T> obtenerTodos() {
		List<T> lista = new ArrayList<>();
		for (Document doc : collection.find()) {
			try {
				T obj = clase.getDeclaredConstructor().newInstance();
				for (String key : getters.keySet()) {
					Method setter = clase.getMethod("set" + Character.toUpperCase(key.charAt(0)) + key.substring(1),
							getters.get(key).getReturnType());
					setter.invoke(obj, doc.get(key));
				}
				lista.add(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public T obtenerPorId(T obj) {
		try {
			Object idValue = getters.get("id").invoke(obj);
			Document doc = collection.find(Filters.eq("_id", idValue)).first();
			if (doc != null) {
				T result = clase.getDeclaredConstructor().newInstance();
				for (String key : getters.keySet()) {
					Method setter = clase.getMethod("set" + Character.toUpperCase(key.charAt(0)) + key.substring(1),
							getters.get(key).getReturnType());
					setter.invoke(result, doc.get(key));
				}
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertar(T obj) {
		Document doc = new Document();
		for (String o : getters.keySet()) {
			Object v;
			try {
				v = getters.get(o).invoke(obj);
				doc.append(o, v);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		collection.insertOne(doc);
	}

	@Override
	public void actualiza(T obj) {
		Document doc = new Document();
		for (String o : getters.keySet()) {
			Object v;
			try {
				v = getters.get(o).invoke(obj);
				doc.append(o, v);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		try {
			if (getters.get("_id").invoke(obj) != null) {
				collection.replaceOne(Filters.eq("_id", getters.get("_id").invoke(obj)), doc);
			} else {
				collection.insertOne(doc); // triple histórico
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminar(T obj) {
		try {
			Object idValue = getters.get("id").invoke(obj);
			if (idValue != null) {
				collection.deleteOne(Filters.eq("_id", idValue));
			} else {
				System.err.println("No existe bro, no se puede borrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
