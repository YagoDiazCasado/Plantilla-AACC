package com.aadd.ydc.dao;

import java.util.List;

public interface IEntidadDao<T> {

	public List<T> obtenerTodos();

	public T obtenerPorId(T obj);

	public void insertar(T obj);

	public void actualiza(T obj);

	public void eliminar(T entity);

}
