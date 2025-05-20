package com.api.ors.orsApi.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntidadDao<T> {

	@PersistenceContext
	private EntityManager em;

	private final Class<T> clase;

	public EntidadDao(Class<T> clase) {
		this.clase = clase;
	}

	public List<T> obtenerTodos() {
		return em.createQuery("FROM " + clase.getName(), clase).getResultList();
	}

	public T obtenerPorId(Object id) {
		return em.find(clase, id);
	}

	@Transactional
	public void insertar(T obj) {
		em.persist(obj);
	}

	@Transactional
	public void actualiza(T obj) {
		em.merge(obj);
	}

	@Transactional
	public void eliminar(T entity) {
		T managed = em.contains(entity) ? entity : em.merge(entity);
		em.remove(managed);
	}
}
