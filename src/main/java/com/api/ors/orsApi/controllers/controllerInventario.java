package com.api.ors.orsApi.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.ors.orsApi.entitys.Inventory;
import com.api.ors.orsApi.entitys.Item;
import com.api.ors.orsApi.entitys.PJ;
import com.api.ors.orsApi.extraUtiles.EnumsDeItems.ItemShape;
import com.api.ors.orsApi.service.serviceInventory;

@RestController
	@RequestMapping("/api/inventory")
	public class controllerInventario {

	    @Autowired
	    private serviceInventory sI;

	    // 📥 Insertar entrada de inventario
	    @PostMapping("/insert")
	    public ResponseEntity<String> insertar(@RequestBody Inventory inventario) {
	        sI.insertar(inventario);
	        return ResponseEntity.ok("Inventario insertado");
	    }

	    // ⚖️ Obtener peso actual de un PJ
	    @GetMapping("/peso")
	    public ResponseEntity<Double> pesoActual(@RequestBody PJ pj) {
	        double peso = sI.getPesoActual(pj);
	        return ResponseEntity.ok(peso);
	    }

	    // 📦 Obtener inventario completo de un PJ
	    @GetMapping("/porPJ")
	    public ResponseEntity<HashMap<Item, Integer>> obtenerPorPJ(@RequestBody PJ pj) {
	        return ResponseEntity.ok(sI.obtenerPorPJ(pj));
	    }

	    // ❓ Comprobar si existe objeto
	    @GetMapping("/existe")
	    public ResponseEntity<Boolean> existeObjeto(@RequestParam String nombre, @RequestBody PJ pj) {
	        return ResponseEntity.ok(sI.existeObjeto(nombre, pj));
	    }

	    // 🎯 Usar munición
	    @PostMapping("/usar-municion")
	    public ResponseEntity<String> usarMunicion(@RequestParam ItemShape tipo, @RequestBody PJ pj,
	                                               @RequestParam int cantidad) throws Exception {
	        sI.usarMunicion(tipo, pj, cantidad);
	        return ResponseEntity.ok("Munición usada");
	    }

	    // 🗑️ Tirar objeto
	    @PostMapping("/tirar")
	    public ResponseEntity<String> tirar(@RequestBody TirarPeticion peticion) throws Exception {
	        sI.tirar(peticion.item, peticion.pj, peticion.cantidad);
	        return ResponseEntity.ok("Objeto tirado");
	    }

	    // 📊 Ver cantidad de munición
	    @GetMapping("/cantidad-ammo")
	    public ResponseEntity<String> cantidadAmmo(@RequestParam ItemShape tipo, @RequestBody PJ pj) {
	        return ResponseEntity.ok(sI.cantidadAmmo(pj, tipo));
	    }

	    // 🔢 Obtener cantidad de un item
	    @GetMapping("/cantidad")
	    public ResponseEntity<Integer> cantidad(@RequestParam String itemName, @RequestBody PJ pj) {
	        // Suponiendo que tú buscas el item por nombre con otra capa
	        Item item = new Item(); item.setName(itemName); // ⚠️ Esto debería mejorarse
	        return ResponseEntity.ok(sI.getCantidad(pj, item));
	    }

	    // ➕ Sumar cantidad de un item
	    @PostMapping("/sumar")
	    public ResponseEntity<String> sumar(@RequestBody SumarPeticion peticion) {
	        sI.sumar(peticion.pj, peticion.item, peticion.cantidad);
	        return ResponseEntity.ok("Cantidad actualizada");
	    }

	    // Clases auxiliares para las peticiones compuestas
	    public static class TirarPeticion {
	        public PJ pj;
	        public Item item;
	        public int cantidad;
	    }

	    public static class SumarPeticion {
	        public PJ pj;
	        public Item item;
	        public int cantidad;
	    }
	}
