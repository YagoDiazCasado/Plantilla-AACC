package com.aadd.ydc.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LectorApis {

	// Normalmente devuelve un Json para leer directamente con ObjectMapper
	public static String leerUrl(String url) throws Exception {
		HttpClient client = java.net.http.HttpClient.newHttpClient();

		// Crear una solicitud GET a la API
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

		// Enviar la solicitud y obtener la respuesta
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		// Comprobar si la respuesta fue exitosa
		if (response.statusCode() == 200) {
			return response.body();
		}
		return url;
	}

	public static void downloadFile(String urlStr, String outputFileName) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		try (InputStream in = connection.getInputStream();
				FileOutputStream out = new FileOutputStream(outputFileName)) {
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		}
	}

}
