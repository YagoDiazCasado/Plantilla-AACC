package com.api.ors.orsApi.entitys;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "T_Body_type")
@JsonInclude(JsonInclude.Include.NON_NULL) // Omitir campos nulos en el JSON
public class BodyType {

	@Id
    @Column(name = "bodyType_name")
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
	@Transient
	private List<Double> mods = new ArrayList<Double>();

	public BodyType() {

	}

	public void fillMods() {

	}

	public BodyType(String name, double d, double e, double f, double g, double i) {
		this.name = name;
		this.modA = d;
		this.modS = e;
		this.modE = f;
		this.modD = i;
		this.modM = g;
	}

	@Override
	public String toString() {
		return "BodyType [name=" + name + ", modA=" + modA + ", modS=" + modS + ", modE=" + modE + ", modM=" + modM
				+ ", modD=" + modD + ", mods=" + mods + "]";
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

	public List<Double> getMods() {
		mods.clear();
		mods.add(modA);
		mods.add(modS);
		mods.add(modE);
		mods.add(modM);
		mods.add(modD);
		return mods;
	}

	public void setMods(List<Double> mods) {
		this.mods = mods;
	}

}
