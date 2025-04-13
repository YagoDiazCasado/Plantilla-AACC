package com.aadd.ydc.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.aadd.ydc.dao.IEntidadDao;
import com.aadd.ydc.util.gestores.GestorHibernate;

public class EntidadDaoHibernate<T> implements IEntidadDao<T> {

	private final Class<T> clase; // Aqui guardaremos el tipo de clase que se usa. Por eso no hay metodos staticos

	public EntidadDaoHibernate(Class<T> clase) {
		this.clase = clase;
	}

	@Override
	public List<T> obtenerTodos() {
		try (Session session = GestorHibernate.getSessionFactory()) {
			return session.createQuery("FROM " + clase.getName(), clase).list(); // tipo list<clase>
		}
	}

	@Override
	public T obtenerPorId(T obj) {
		try (Session session = GestorHibernate.getSessionFactory()) {
			return session.get(clase, obj); // devolverlo con el tipo que sea el ID
		}
	}

	@Override
	public void insertar(T obj) {
		Transaction transaction = null;
		try (Session session = GestorHibernate.getSessionFactory()) {
			transaction = session.beginTransaction();
			session.persist(obj);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void actualiza(T obj) {
		Transaction transaction = null;
		try (Session session = GestorHibernate.getSessionFactory()) {
			transaction = session.beginTransaction();
			session.merge(obj);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void eliminar(T entity) {
		Transaction transaction = null;
		try (Session session = GestorHibernate.getSessionFactory()) {
			transaction = session.beginTransaction();
			if (entity != null) {
				session.remove(entity);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

}