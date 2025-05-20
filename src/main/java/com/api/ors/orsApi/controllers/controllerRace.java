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

import com.api.ors.orsApi.entitys.Race;
import com.api.ors.orsApi.service.serviceRace;

@RestController
@RequestMapping("/api/races")
public class controllerRace {

	private final serviceRace raceService;

	public controllerRace(serviceRace raceService) {
		this.raceService = raceService;
	}

	@GetMapping
	public List<Race> getAll() {
		return raceService.obtenerTodos();
	}

	@GetMapping("/{id}")
	public Race getById(@PathVariable String id) {
		return raceService.obtenerPorId(id);
	}

	@PostMapping
	public void create(@RequestBody Race race) {
		raceService.insertar(race);
	}

	@PutMapping
	public void update(@RequestBody Race race) {
		raceService.actualizar(race);
	}

	@DeleteMapping
	public void delete(@RequestBody Race race) {
		raceService.eliminar(race);
	}
}
