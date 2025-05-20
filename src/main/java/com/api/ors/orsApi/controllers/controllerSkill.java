package com.api.ors.orsApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.ors.orsApi.entitys.Skill;
import com.api.ors.orsApi.service.serviceSkill;

@RestController
@RequestMapping("/api/skills")
public class controllerSkill {

	private final serviceSkill skillService;

	public controllerSkill(serviceSkill skillService) {
	        this.skillService = skillService;
	    }

	@GetMapping
	public List<Skill> getAll() {
		return skillService.obtenerTodos();
	}

	@GetMapping("/{id}")
	public Skill getById(@PathVariable String id) {
		return skillService.obtenerPorId(id);
	}

	@PostMapping
	public void create(@RequestBody Skill skill) {
		skillService.insertar(skill);
	}

	@PutMapping
	public void update(@RequestBody Skill skill) {
		skillService.actualizar(skill);
	}

	@DeleteMapping
	public void delete(@RequestBody Skill skill) {
		skillService.eliminar(skill);
	}
}
