package com.api.ors.orsApi.entitys;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_Inventory")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inventory implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@JsonUnwrapped
	private InventoryId idIn;

	@Column(name = "quantity")
	@JsonProperty("cantidad")
	private int quantity;

	@Column(name = "single_weight")
	@JsonProperty("peso_unitario")
	private double singleWeight;

	@Column(name = "object_name")
	@JsonProperty("nombre_objeto")
	private String objectName;

	public Inventory() {
	}

	public Inventory(Item i, PJ p) {
		idIn = new InventoryId(p, i);
	}

	public Inventory(InventoryId idIn, int quantity, double singleWeight, String objectName) {
		this.idIn = idIn;
		this.quantity = quantity;
		this.singleWeight = singleWeight;
		this.objectName = objectName;
	}

	public Inventory(Item i, PJ p, int quantity, double singleWeight, String objectName) {
		this.idIn = new InventoryId(p, i);
		this.quantity = quantity;
		this.singleWeight = singleWeight;
		this.objectName = objectName;
	}

	@Embeddable
	public static class InventoryId implements Serializable {

		private static final long serialVersionUID = 1L;

		@ManyToOne
		@JoinColumn(name = "id_Pj")
		@JsonBackReference
		private PJ pj;

		@ManyToOne
		@JoinColumn(name = "id_It")
		@JsonManagedReference
		private Item item;

		public InventoryId() {
		}

		public InventoryId(PJ pj, Item item) {
			this.pj = pj;
			this.item = item;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof InventoryId))
				return false;
			InventoryId that = (InventoryId) o;
			return Objects.equals(pj, that.pj) && Objects.equals(item, that.item);
		}

		@Override
		public int hashCode() {
			return Objects.hash(pj, item);
		}

		public PJ getPj() {
			return pj;
		}

		public void setPj(PJ pj) {
			this.pj = pj;
		}

		public Item getItem() {
			return item;
		}

		public void setItem(Item item) {
			this.item = item;
		}
	}

	// Getters y Setters
	public InventoryId getId() {
		return idIn;
	}

	public void setId(InventoryId id) {
		this.idIn = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSingleWeight() {
		return singleWeight;
	}

	public void setSingleWeight(double singleWeight) {
		this.singleWeight = singleWeight;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
}
