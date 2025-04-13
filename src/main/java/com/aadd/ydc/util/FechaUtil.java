package com.aadd.ydc.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FechaUtil {
	private static final SimpleDateFormat FORMATO = new SimpleDateFormat("yyyy/MM/dd"); // Ajusta el formato según tu
																						// JSON
	public static Date convertirAFecha(String fecha) throws ParseException {
		java.util.Date utilDate = FORMATO.parse(fecha); // Convierte a java.util.Date
		return new Date(utilDate.getTime()); // Convierte a java.sql.Date
	}
}