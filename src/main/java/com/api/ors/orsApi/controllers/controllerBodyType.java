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

import com.api.ors.orsApi.entitys.BodyType;
import com.api.ors.orsApi.service.serviceBodyType;

@RestController
@RequestMapping("/api/bodytypes")
public class controllerBodyType {

	private final serviceBodyType bodyTypeService;

	public controllerBodyType(serviceBodyType bodyTypeService) {
		this.bodyTypeService = bodyTypeService;
	}

	@GetMapping
	public List<BodyType> getAll() {
		return bodyTypeService.obtenerTodos();
	}

	@GetMapping("/{id}")
	public BodyType getById(@PathVariable String id) {
		return bodyTypeService.obtenerPorId(id);
	}

	@PostMapping
	public void create(@RequestBody BodyType bodyType) {
		bodyTypeService.insertar(bodyType);
	}

	@PutMapping
	public void update(@RequestBody BodyType bodyType) {
		bodyTypeService.actualizar(bodyType);
	}

	@DeleteMapping
	public void delete(@RequestBody BodyType bodyType) {
		bodyTypeService.eliminar(bodyType);
	}
}
