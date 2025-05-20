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

import com.api.ors.orsApi.entitys.Item;
import com.api.ors.orsApi.service.serviceItem;

@RestController
@RequestMapping("/api/items")
public class controllerItem {
	private final serviceItem itemService;

	public controllerItem(serviceItem itemService) {
		this.itemService = itemService;
	}

	@GetMapping
	public List<Item> getAll() {
		return itemService.obtenerTodos();
	}

	@GetMapping("/{id}")
	public Item getById(@PathVariable Long id) {
		return itemService.obtenerPorId(id);
	}

	@PostMapping
	public void create(@RequestBody Item item) {
		itemService.insertar(item);
	}

	@PutMapping
	public void update(@RequestBody Item item) {
		itemService.actualizar(item);
	}

	@DeleteMapping
	public void delete(@RequestBody Item item) {
		itemService.eliminar(item);
	}
}
