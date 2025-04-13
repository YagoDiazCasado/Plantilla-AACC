package com.aadd.ydc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.aadd.ydc.util.gestores.GestorFicheroConfiguracion;


public class Conexion {

	public static Connection conectarMySql() throws Exception {
		try {
			Class.forName(GestorFicheroConfiguracion.devolverCredencial("controladorMySql"));
			Connection conexion = DriverManager.getConnection(GestorFicheroConfiguracion.devolverCredencial("URLMySQL"),
					GestorFicheroConfiguracion.devolverCredencial("UserMySQL"),
					GestorFicheroConfiguracion.devolverCredencial("PaswordMySQL"));
			System.out.println("Conectado MySql");
			return conexion;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	public static Connection conectarPostgreSql() throws Exception {
		try {
			Class.forName(GestorFicheroConfiguracion.devolverCredencial("controladorPsg"));
			Connection conexion = DriverManager.getConnection(GestorFicheroConfiguracion.devolverCredencial("URLPsg"),
					GestorFicheroConfiguracion.devolverCredencial("UserPsg"),
					GestorFicheroConfiguracion.devolverCredencial("PaswordPsg"));
			System.out.println("Conectado Postgre");
			return conexion;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
