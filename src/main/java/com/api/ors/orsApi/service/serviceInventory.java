package com.api.ors.orsApi.service;

import java.util.HashMap;

import com.api.ors.orsApi.entitys.Inventory;
import com.api.ors.orsApi.entitys.Item;
import com.api.ors.orsApi.entitys.PJ;
import com.api.ors.orsApi.extraUtiles.EnumsDeItems.ItemShape;
import com.api.ors.orsApi.repository.InvDao;

public class serviceInventory {

	private static InvDao invDao = new InvDao();

	public void insertar(Inventory obj) {
		invDao.insertar(obj);
	}

	public double getPesoActual(PJ pj) {
		return invDao.geCurrentCarry(pj);
	}

	public HashMap<Item, Integer> obtenerPorPJ(PJ pj) {
		return invDao.obtenerTodosPorPJ(pj);
	}

	public boolean existeObjeto(String nombre, PJ pj) {
		return invDao.checkExistance(nombre, pj);
	}

	public void usarMunicion(ItemShape tipo, PJ pj, int cantidad) throws Exception {
		invDao.useAmmo(tipo, pj, cantidad);
	}

	public void tirar(Item item, PJ pj, int cantidad) throws Exception {
		invDao.tirar(item, pj, cantidad);
	}

	public String cantidadAmmo(PJ pj, ItemShape tipo) {
		return invDao.cantidadAmmo(pj, tipo);
	}

	public int getCantidad(PJ pj, Item item) {
		return invDao.getQuantity(pj, item);
	}

	public void sumar(PJ pj, Item item, int cantidad) {
		invDao.sumar(pj, item, cantidad);
	}

}
