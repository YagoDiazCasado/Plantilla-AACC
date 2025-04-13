package com.aadd.ydc.util;

import java.io.IOException;

import org.bson.Document;

public class DocumentUtil {
	// Document de BSON

	public static Document getSubDocument(Document doc, String nombreSubDocumento) {
		Document sub = (Document) doc.get(nombreSubDocumento);
		return sub;
	}

	// En mongoDB lo mejor es pasar el obj a jackson y meterlo por string Json en
	// mongo. Te ahorras appends
	public static <T> Document toDocument(T object) {
		try {
			return Document.parse(JacksonJson.toJson(object));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
