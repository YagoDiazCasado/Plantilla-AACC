package com.api.ors.orsApi.entitys;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_Race")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Race implements Serializable {
	private static final long serialVersionUID = -4429746538869145017L;
	@Id
	@JsonProperty("nombre")
	private String name;

	@JsonProperty("mod_atlético")
	private double modA;

	@JsonProperty("mod_fuerza")
	private double modS;

	@JsonProperty("mod_resistencia")
	private double modE;

	@JsonProperty("mod_mente")
	private double modM;

	@JsonProperty("mod_destreza")
	private double modD;

	@Column(name = "base_Weight")
	private double baseWeight;

	public Race(String string) {
		this.name = string;
	}

	public Race() {
		// TODO Auto-generated constructor stub
	}

	public Race(String string, double d, double e, double f, double h, double g, double w) {
		this.name = string;
		this.modA = d;
		this.modS = e;
		this.modE = f;
		this.modD = g;
		this.modM = h;
		this.baseWeight = w;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getModA() {
		return modA;
	}

	public void setModA(double modA) {
		this.modA = modA;
	}

	public double getModS() {
		return modS;
	}

	public void setModS(double modS) {
		this.modS = modS;
	}

	public double getModE() {
		return modE;
	}

	public void setModE(double modE) {
		this.modE = modE;
	}

	public double getModM() {
		return modM;
	}

	public void setModM(double modM) {
		this.modM = modM;
	}

	public double getModD() {
		return modD;
	}

	public void setModD(double modD) {
		this.modD = modD;
	}

	public double getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(double base_Weight) {
		this.baseWeight = base_Weight;
	}

}
