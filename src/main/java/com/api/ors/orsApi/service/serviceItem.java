package com.api.ors.orsApi.service;

import java.util.List;

import com.api.ors.orsApi.entitys.Item;
import com.api.ors.orsApi.repository.EntidadDao;

public class serviceItem {
	 private final EntidadDao<Item> itemDao = new EntidadDao<>(Item.class);

	    public List<Item> obtenerTodos() {
	        return itemDao.obtenerTodos();
	    }

	    public Item obtenerPorId(Long id) {
	        return itemDao.obtenerPorId(id);
	    }

	    public void insertar(Item item) {
	        itemDao.insertar(item);
	    }

	    public void actualizar(Item item) {
	        itemDao.actualiza(item);
	    }

	    public void eliminar(Item item) {
	        itemDao.eliminar(item);
	    }
	
}
