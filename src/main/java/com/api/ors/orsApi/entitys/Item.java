package com.api.ors.orsApi.entitys;

import java.io.Serializable;
import java.util.Objects;

import com.api.ors.orsApi.extraUtiles.EnumsDeItems.DamageType;
import com.api.ors.orsApi.extraUtiles.EnumsDeItems.Distance;
import com.api.ors.orsApi.extraUtiles.EnumsDeItems.ItemFamily;
import com.api.ors.orsApi.extraUtiles.EnumsDeItems.ItemShape;
import com.api.ors.orsApi.extraUtiles.EnumsDeItems.Rarity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_Item")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	private Long id_O;

	@Column(name = "name")
	@JsonProperty("nombre")
	private String name;

	@Enumerated(EnumType.STRING)
	private Rarity rarity;

	private String modEquipo;

	@Enumerated(EnumType.STRING)
	private ItemFamily itemFamily;

	@Enumerated(EnumType.STRING)
	private ItemShape itemShape;

	private double value;
	private double weight;

	private int basicDamage;

	@Enumerated(EnumType.STRING)
	private DamageType damageType;

	@Enumerated(EnumType.STRING)
	private Distance distance;

	private String consumEffect;
	private String descripcion;

	public Item() {

	}

	@Override
	public int hashCode() {
		return Objects.hash(id_O);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(id_O, other.id_O);
	}

	public String showInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n========== ITEM INFO ==========\n");
		sb.append("🔹 Nombre         : ").append(name).append("\n");
		sb.append("🔹 Rareza         : ").append(rarity).append("\n");
		sb.append("🔹 Familia        : ").append(itemFamily).append("\n");
		sb.append("🔹 Forma          : ").append(itemShape != null ? itemShape : "N/A").append("\n");
		sb.append("🔹 Valor          : ").append(value).append("\n");
		sb.append("🔹 Peso           : ").append(weight).append("\n");

		if (itemFamily.equals(ItemFamily.ITEM)) {
		} else {
			if (itemFamily.equals(ItemFamily.EQUIPMENT)) {
				sb.append("\n------ MODIFICADOR ------\n");
				sb.append("🔸 Mod Equipo     : ").append(modEquipo).append("\n");
			} else if (itemFamily.equals(ItemFamily.EDIBLE)) {
				sb.append("\n------ USO ------\n");
				sb.append("🔸 Efecto Consum  : ").append(consumEffect != null ? consumEffect : "Ninguno").append("\n");
			} else {
				sb.append("\n------ COMBATE ------\n");
				sb.append("🔸 Daño Base      : ").append(basicDamage).append("\n");
				sb.append("🔸 Tipo de Daño   : ").append(damageType != null ? damageType : "N/A").append("\n");
				sb.append("🔸 Distancia      : ").append(distance != null ? distance : "N/A").append("\n");
			}
		}

		sb.append("\n------ DESCRIPCIÓN ------\n");
		sb.append(descripcion != null ? descripcion : "Sin descripción").append("\n");

		sb.append("================================\n");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Item [name=" + name + "]";
	}

	// Constructor Total
	public Item(String name, Rarity rarity, String modEquipo, ItemFamily itemFamily, ItemShape itemShape, double value,
			double weight, int basicDamage, DamageType damageType, Distance distance, String consumEffect,
			String descripcion) {
		this.name = name;
		this.rarity = rarity;
		this.modEquipo = modEquipo;
		this.itemFamily = itemFamily;
		this.itemShape = itemShape;
		this.value = value;
		this.weight = weight;
		this.basicDamage = basicDamage;
		this.damageType = damageType;
		this.distance = distance;
		this.consumEffect = consumEffect;
		this.descripcion = descripcion;
	}

	// Constructor de Armas
	public Item(String name, Rarity rarity, ItemFamily itemFamily, ItemShape itemShape, double value, double weight,
			int basicDamage, DamageType damageType, Distance distance, String descripcion) {
		this.name = name;
		this.rarity = rarity;
		this.itemFamily = itemFamily;
		this.itemShape = itemShape;
		this.value = value;
		this.weight = weight;
		this.basicDamage = basicDamage;
		this.damageType = damageType;
		this.distance = distance;
		this.descripcion = descripcion;
	}

	// Constructor de Equipo
	public Item(String name, Rarity rarity, String modEquipo, ItemFamily itemFamily, ItemShape itemShape, double value,
			double weight, String descripcion) {
		this.name = name;
		this.rarity = rarity;
		this.modEquipo = modEquipo;
		this.itemFamily = itemFamily;
		this.itemShape = itemShape;
		this.value = value;
		this.weight = weight;
		this.descripcion = descripcion;
	}

	// Constructor de Consumible
	public Item(String name, Rarity rarity, ItemFamily itemFamily, ItemShape itemShape, double value, double weight,
			String consumEffect, String descripcion) {
		this.name = name;
		this.rarity = rarity;
		this.itemFamily = itemFamily;
		this.itemShape = itemShape;
		this.value = value;
		this.weight = weight;
		this.consumEffect = consumEffect;
		this.descripcion = descripcion;
	}

	// Constructor de Item o Municion
	public Item(String name, Rarity rarity, ItemFamily itemFamily, ItemShape itemShape, double value, double weight,
			String descripcion) {
		this.name = name;
		this.rarity = rarity;
		this.itemFamily = itemFamily;
		this.itemShape = itemShape;
		this.value = value;
		this.weight = weight;
		this.descripcion = descripcion;
	}

	public Long getId_O() {
		return id_O;
	}

	public void setId_O(Long id_O) {
		this.id_O = id_O;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public String getModEquipo() {
		return modEquipo;
	}

	public void setModEquipo(String modEquipo) {
		this.modEquipo = modEquipo;
	}

	public ItemFamily getItemFamily() {
		return itemFamily;
	}

	public void setItemFamily(ItemFamily itemFamily) {
		this.itemFamily = itemFamily;
	}

	public ItemShape getItemShape() {
		return itemShape;
	}

	public void setItemShape(ItemShape itemShape) {
		this.itemShape = itemShape;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getBasicDamage() {
		return basicDamage;
	}

	public void setBasicDamage(int basicDamage) {
		this.basicDamage = basicDamage;
	}

	public DamageType getDamageType() {
		return damageType;
	}

	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public String getConsumEffect() {
		return consumEffect;
	}

	public void setConsumEffect(String consumEffect) {
		this.consumEffect = consumEffect;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
