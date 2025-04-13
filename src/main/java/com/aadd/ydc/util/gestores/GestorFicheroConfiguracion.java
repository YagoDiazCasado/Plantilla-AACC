package com.aadd.ydc.util.gestores;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

public class GestorFicheroConfiguracion {

	public static String ruta = "conf/conf.properties";

	public static String devolverCredencial(String v) {
		Properties p = new Properties();
		String envio = "";
		try {
			FileReader fr = new FileReader(ruta);
			p.load(fr);
			envio = p.getProperty(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return envio;
	}

	public static void actualizarValor(String key, String value) {
		Properties props = new Properties();
		try (FileInputStream pin = new FileInputStream(ruta)) {
			props.load(pin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		props.setProperty(key, value);

		try (FileWriter writer = new FileWriter(ruta)) {
			props.store(writer, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
