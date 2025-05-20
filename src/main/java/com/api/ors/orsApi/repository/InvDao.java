package com.api.ors.orsApi.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.api.ors.orsApi.entitys.Inventory;
import com.api.ors.orsApi.entitys.Item;
import com.api.ors.orsApi.entitys.PJ;
import com.api.ors.orsApi.extraUtiles.EnumsDeItems.ItemFamily;
import com.api.ors.orsApi.extraUtiles.EnumsDeItems.ItemShape;
import com.api.ors.orsApi.extraUtiles.EnumsDeItems.Rarity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class InvDao {

	@PersistenceContext // Esto es mejor que el gestor que tenía,
	// mete solo el entitymanager con todo ya puesto (lo mismo que el getor pero
	// ahorrando una clase)
	private EntityManager em;

	public void insertar(Inventory obj) {
		em.persist(obj);
	}

	public double geCurrentCarry(PJ pj) {
		Double result = em
				.createQuery("SELECT SUM(i.singleWeight * i.quantity) FROM Inventory i WHERE i.idIn.pj = :pjName",
						Double.class)
				.setParameter("pjName", pj).getSingleResult();
		return result != null ? result : 0.0;
	}

	public HashMap<Item, Integer> obtenerTodosPorPJ(PJ selected) {
		HashMap<Item, Integer> invent = new HashMap<>();
		List<Object[]> resultados = em
				.createQuery("SELECT i.idIn.item, i.quantity FROM Inventory i WHERE i.idIn.pj = :pjName",
						Object[].class)
				.setParameter("pjName", selected).getResultList();

		for (Object[] fila : resultados) {
			invent.put((Item) fila[0], (Integer) fila[1]);
		}
		return invent;
	}

	public boolean checkExistance(String objectName, PJ pj) {
		Long c = em
				.createQuery("SELECT COUNT(i) FROM Inventory i WHERE i.idIn.pj = :pjName AND i.objectName = :objName",
						Long.class)
				.setParameter("pjName", pj).setParameter("objName", objectName).getSingleResult();
		return c > 0;
	}

	public void useAmmo(ItemShape itemShape, PJ pj, int cantidadUsar) throws Exception {
		String nombreMunicion = itemShape.name() + "AMMO";

		Long c = em
				.createQuery("SELECT COUNT(i) FROM Inventory i WHERE i.idIn.pj = :pjName AND i.objectName = :objName",
						Long.class)
				.setParameter("pjName", pj).setParameter("objName", nombreMunicion).getSingleResult();

		if (c > 0) {
			em.createQuery(
					"UPDATE Inventory i SET i.quantity = i.quantity + :c WHERE i.idIn.pj = :pjName AND i.objectName = :it")
					.setParameter("c", cantidadUsar).setParameter("pjName", pj).setParameter("it", nombreMunicion)
					.executeUpdate();
		} else {
			Item i = new Item(nombreMunicion, Rarity.H, ItemFamily.AMMO, ItemShape.AMMO, 0.2, 0.0,
					"Munición de " + nombreMunicion);
			i = em.merge(i);
			Inventory o = new Inventory(i, pj, cantidadUsar, i.getWeight(), nombreMunicion);
			em.persist(o);
		}

		limpiarCeros(pj);
	}

	private void limpiarCeros(PJ pj) {
		em.createQuery("DELETE FROM Inventory i WHERE i.idIn.pj = :pj AND i.quantity <= 0").setParameter("pj", pj)
				.executeUpdate();
	}

	public void tirar(Item tipo, PJ pj, int cantidad) throws Exception {
		int updated = em.createQuery(
				"UPDATE Inventory i SET i.quantity = i.quantity - :cantidadUsar WHERE i.idIn.pj = :pjName AND i.idIn.item = :id AND i.quantity >= :cantidadUsar")
				.setParameter("cantidadUsar", cantidad).setParameter("pjName", pj).setParameter("id", tipo)
				.executeUpdate();

		if (updated == 0) {
			throw new Exception("No hay suficiente");
		}

		limpiarCeros(pj);
	}

	public String cantidadAmmo(PJ pj, ItemShape tipo) {
		try {
			Integer cantidad = em
					.createQuery("SELECT i.quantity FROM Inventory i WHERE i.idIn.pj = :pjName AND i.objectName = :it",
							Integer.class)
					.setParameter("pjName", pj).setParameter("it", tipo + "AMMO").getSingleResult();
			return cantidad.toString();
		} catch (Exception e) {
			return "0";
		}
	}

	public int getQuantity(PJ pj, Item tipo) {
		try {
			return em.createQuery(
					"SELECT i.quantity FROM Inventory i WHERE i.idIn.pj = :pjName AND i.idIn.item = :itemId",
					Integer.class).setParameter("pjName", pj).setParameter("itemId", tipo).getSingleResult();
		} catch (Exception e) {
			return 0;
		}
	}

	public void sumar(PJ pj, Item tipo, int cantidad) {
		em.createQuery(
				"UPDATE Inventory i SET i.quantity = i.quantity + :cantidadUsar WHERE i.idIn.pj = :pjName AND i.idIn.item = :id AND i.quantity >= :cantidadUsar")
				.setParameter("cantidadUsar", cantidad).setParameter("pjName", pj).setParameter("id", tipo)
				.executeUpdate();

		limpiarCeros(pj);
	}
}
