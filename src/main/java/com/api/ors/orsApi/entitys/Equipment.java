package com.api.ors.orsApi.entitys;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "T_Equipment")
@JsonInclude(JsonInclude.Include.NON_NULL) // No incluir nulos en JSON
public class Equipment {

	@Id
	@OneToOne
	@MapsId
	@JoinColumn(name = "owner_id")
	@JsonBackReference // ⚠️ evita recursión infinita si PJ tiene @JsonManagedReference
	private PJ owner;

	@ManyToOne
	@JoinColumn(name = "head_item_id", nullable = true)
	@JsonProperty("casco")
	private Item head;

	@ManyToOne
	@JoinColumn(name = "chest_item_id", nullable = true)
	@JsonProperty("pecho")
	private Item chest;

	@ManyToOne
	@JoinColumn(name = "legs_item_id", nullable = true)
	@JsonProperty("piernas")
	private Item legs;

	@ManyToOne
	@JoinColumn(name = "feet_item_id", nullable = true)
	@JsonProperty("pies")
	private Item feet;

	@ManyToOne
	@JoinColumn(name = "extra_id", nullable = true)
	@JsonProperty("extra_1")
	private Item extra1;

	@ManyToOne
	@JoinColumn(name = "extra2_id", nullable = true)
	@JsonProperty("extra_2")
	private Item extra2;

	@ManyToOne
	@JoinColumn(name = "extra3_id", nullable = true)
	@JsonProperty("extra_3")
	private Item extra3;

	@Transient
	@JsonIgnore
	private List<Item> equip = new ArrayList<>();

	public Equipment() {
	}

	public Equipment(PJ owner, Item head, Item chest, Item legs, Item feet) {
		this.owner = owner;
		this.head = head;
		this.chest = chest;
		this.legs = legs;
		this.feet = feet;
		equip.add(chest);
		equip.add(head);
		equip.add(legs);
		equip.add(feet);
		equip.add(extra1);
		equip.add(extra2);
		equip.add(extra3);
	}

	public Equipment(PJ pj) {
		this.owner = pj;
	}

	// Getters y setters

	public PJ getOwner() {
		return owner;
	}

	public List<Item> getEquip() {
		equip.clear();
		if (chest != null)
			equip.add(chest);
		if (head != null)
			equip.add(head);
		if (legs != null)
			equip.add(legs);
		if (feet != null)
			equip.add(feet);
		if (extra1 != null)
			equip.add(extra1);
		if (extra2 != null)
			equip.add(extra2);
		if (extra3 != null)
			equip.add(extra3);
		return equip;
	}

	public void setEquip(List<Item> equip) {
		this.equip = equip;
	}

	public void setOwner(PJ owner) {
		this.owner = owner;
	}

	public Item getHead() {
		return head;
	}

	public Item getExtra1() {
		return extra1;
	}

	public void setExtra1(Item extra1) {
		this.extra1 = extra1;
	}

	public Item getExtra2() {
		return extra2;
	}

	public void setExtra2(Item extra2) {
		this.extra2 = extra2;
	}

	public Item getExtra3() {
		return extra3;
	}

	public void setExtra3(Item extra3) {
		this.extra3 = extra3;
	}

	public void setHead(Item head) {
		this.head = head;
	}

	public Item getChest() {
		return chest;
	}

	public void setChest(Item chest) {
		this.chest = chest;
	}

	public Item getLegs() {
		return legs;
	}

	public void setLegs(Item legs) {
		this.legs = legs;
	}

	public Item getFeet() {
		return feet;
	}

	public void setFeet(Item feet) {
		this.feet = feet;
	}

	@Override
	public String toString() {
		return "Equipment{" + "owner=" + owner + ", head=" + head + ", chest=" + chest + ", legs=" + legs + ", feet="
				+ feet + '}';
	}

	@Override
	public int hashCode() {
		return Objects.hash(owner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipment other = (Equipment) obj;
		return Objects.equals(owner, other.owner);
	}

}
