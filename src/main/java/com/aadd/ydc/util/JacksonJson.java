package com.aadd.ydc.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.text.Document;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJson {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T fromJson(String json, Class<T> clazz) throws IOException {
		return objectMapper.readValue(json, clazz);
	}

	public static <T> T fromJsonFile(File file, Class<T> clazz) throws IOException {
		return objectMapper.readValue(file, clazz);
	}

	public static <T> List<T> listFromJsonFile(String json, Class<T> clazz) throws IOException {
		return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
	}

	public static <T> T fromJsonDocToJsonObject(Document file, Class<T> clazz) throws IOException {
		// Esto es apra recibir de mongoDb y darlo directamente en clase mapeada de
		// Json.
		String json = toJson(file);
		return objectMapper.readValue(json, clazz);
	}

	public static <T> String toJson(T object) throws IOException {
		return objectMapper.writeValueAsString(object);
	}

	public static <T> void toJsonFile(T object, File file) throws IOException {
		objectMapper.writeValue(file, object);
	}

	public static <T> T findInJson(File file, Class<T> clazz, Predicate<T> predicate) throws IOException {
		List<T> objects = objectMapper.readValue(file,
				objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
		Iterator<T> iterator = objects.iterator();
		while (iterator.hasNext()) {
			T object = iterator.next();
			if (predicate.test(object)) {
				return object;
			}
		}
		return null;
	}

	public static <T> List<T> listFromJsonFile(File file, Class<T> clazz) throws IOException {
		return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
	}

}
