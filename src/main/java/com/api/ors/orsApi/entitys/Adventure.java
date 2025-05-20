package com.api.ors.orsApi.entitys;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "T_Adventure")
@JsonInclude(JsonInclude.Include.NON_NULL) // No mostrar campos nulos
public class Adventure {

	@Id
	@Column(name = "adventure_Name")
	@JsonProperty("nombre") // Alias más amigable para el campo en JSON
	private String adventureName;

	private String pasword;

	public Adventure() {
	}

	public Adventure(String nom, String p) {
		this.adventureName = nom;
		this.pasword = p;
	}

	public String getName() {
		return adventureName;
	}

	public void setName(String name) {
		this.adventureName = name;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	@Override
	public String toString() {
		return adventureName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adventureName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Adventure other = (Adventure) obj;
		return Objects.equals(adventureName, other.adventureName);
	}
}
