package com.aadd.ydc.dao.impl;

import java.util.List;

import com.aadd.ydc.dao.IEntidadDao;
import com.aadd.ydc.util.gestores.GestorJpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EntidadDaoJPA<T> implements IEntidadDao<T> {

	private final Class<T> clase; // Aqui guardaremos el tipo de clase que se usa. Por eso no hay metodos staticos

	public EntidadDaoJPA(Class<T> clase) {
		this.clase = clase;
	}

	@Override
	public List<T> obtenerTodos() {
		EntityManager em = GestorJpa.getEntityManager();
		try {
			return em.createQuery("FROM " + clase.getName(), clase).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public T obtenerPorId(T obj) {
		EntityManager em = GestorJpa.getEntityManager();
		try {
			return em.find(clase, obj); // Maneja el ID como un objeto
		} finally {
			em.close();
		}
	}

	@Override
	public void insertar(T obj) {
		EntityTransaction transaction = null;
		EntityManager em = GestorJpa.getEntityManager();
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.persist(obj);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void actualiza(T obj) {
		EntityTransaction transaction = null;
		EntityManager em = GestorJpa.getEntityManager();
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.merge(obj);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void eliminar(T entity) {
		EntityTransaction transaction = null;
		EntityManager em = GestorJpa.getEntityManager();
		try {
			transaction = em.getTransaction();
			transaction.begin();
			if (entity != null) {
				em.remove(entity);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}
