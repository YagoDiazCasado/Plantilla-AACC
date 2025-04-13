package com.aadd.ydc.util.gestores;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class GestorJpa {
	private static final EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("baseObjectDb");

	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	public static void shutdown() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}
}
