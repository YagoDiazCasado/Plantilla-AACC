package com.api.ors.orsApi.entitys;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_Skill")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Skill implements Serializable {
	private static final long serialVersionUID = 9153930450717525496L;

	@Id
	@Column(name = "sk_name", nullable = false)
	@JsonProperty("nombre") // Alias más claro para el frontend
	private String name;

	@Column(name = "cost", nullable = false)
	@JsonProperty("coste") // Alias en español opcional
	private int cost;

	@Column(name = "type", nullable = false)
	@JsonProperty("tipo")
	private String type;

	@Column(name = "mainAction", nullable = false)
	@JsonProperty("accion_principal")
	private int mainAction;

	@Column(name = "race", nullable = false)
	@JsonProperty("raza")
	private String race;

	@Column(name = "power", nullable = false)
	@JsonProperty("poder")
	private String power;

	public Skill() {
	}

	public Skill(String name, int cost, String type, int mainAction, String race, String power) {
		this.name = name;
		this.cost = cost;
		this.type = type;
		this.mainAction = mainAction;
		this.race = race;
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMainAction() {
		return mainAction;
	}

	public void setMainAction(int mainAction) {
		this.mainAction = mainAction;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	// toString para depuración
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		return Objects.equals(name, other.name);
	}

}
