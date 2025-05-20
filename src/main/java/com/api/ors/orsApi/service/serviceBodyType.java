package com.api.ors.orsApi.service;

import java.util.List;

import com.api.ors.orsApi.entitys.BodyType;
import com.api.ors.orsApi.repository.EntidadDao;

public class serviceBodyType {
	 private final EntidadDao<BodyType> bodyTypeDao = new EntidadDao<>(BodyType.class);

	    public List<BodyType> obtenerTodos() {
	        return bodyTypeDao.obtenerTodos();
	    }

	    public BodyType obtenerPorId(String id) {
	        return bodyTypeDao.obtenerPorId(id);
	    }

	    public void insertar(BodyType bodyType) {
	        bodyTypeDao.insertar(bodyType);
	    }

	    public void actualizar(BodyType bodyType) {
	        bodyTypeDao.actualiza(bodyType);
	    }

	    public void eliminar(BodyType bodyType) {
	        bodyTypeDao.eliminar(bodyType);
	    }
}
