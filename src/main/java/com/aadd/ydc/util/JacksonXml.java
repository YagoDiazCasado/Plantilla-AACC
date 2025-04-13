package com.aadd.ydc.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JacksonXml {
	private static final XmlMapper xmlMapper = new XmlMapper();

	public static <T> T fromXml(String xml, Class<T> clazz) throws IOException {
		return xmlMapper.readValue(xml, clazz);
	}

	public static <T> T fromXmlFile(File file, Class<T> clazz) throws IOException {
		return xmlMapper.readValue(file, clazz);
	}

	public static <T> String toXml(T object) throws IOException {
		return xmlMapper.writeValueAsString(object);
	}

	public static <T> void toXmlFile(T object, File file) throws IOException {
		xmlMapper.writeValue(file, object);
	}

	public static <T> T findInXml(File file, Class<T> clazz, Predicate<T> predicate) throws IOException {
		List<T> objects = xmlMapper.readValue(file,
				xmlMapper.getTypeFactory().constructCollectionType(List.class, clazz));
		Iterator<T> iterator = objects.iterator();
		while (iterator.hasNext()) {
			T object = iterator.next();
			if (predicate.test(object)) {
				return object;
			}
		}
		return null;
	}

	public static <T> List<T> listFromXmlFile(File file, Class<T> clazz) throws IOException {
		return xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, clazz));
	}
}
