package com.api.ors.orsApi.service;

import java.util.List;

import com.api.ors.orsApi.entitys.Race;
import com.api.ors.orsApi.repository.EntidadDao;

public class serviceRace {
	 private final EntidadDao<Race> raceDao = new EntidadDao<>(Race.class);

	    public List<Race> obtenerTodos() {
	        return raceDao.obtenerTodos();
	    }

	    public Race obtenerPorId(String id) {
	        return raceDao.obtenerPorId(id);
	    }

	    public void insertar(Race race) {
	        raceDao.insertar(race);
	    }

	    public void actualizar(Race race) {
	        raceDao.actualiza(race);
	    }

	    public void eliminar(Race race) {
	        raceDao.eliminar(race);
	    }
}
