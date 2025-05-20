package com.api.ors.orsApi.service;

import java.util.List;

import com.api.ors.orsApi.entitys.Skill;
import com.api.ors.orsApi.repository.EntidadDao;

public class serviceSkill {
	private final EntidadDao<Skill> skillDao = new EntidadDao<>(Skill.class);

    public List<Skill> obtenerTodos() {
        return skillDao.obtenerTodos();
    }

    public Skill obtenerPorId(String id) {
        return skillDao.obtenerPorId(id);
    }

    public void insertar(Skill skill) {
        skillDao.insertar(skill);
    }

    public void actualizar(Skill skill) {
        skillDao.actualiza(skill);
    }

    public void eliminar(Skill skill) {
        skillDao.eliminar(skill);
    }
}
