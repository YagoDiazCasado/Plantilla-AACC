package com.api.ors.orsApi.entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.api.ors.orsApi.extraUtiles.EnumsDeItems.BasicHitter;
import com.api.ors.orsApi.extraUtiles.EnumsDeItems.CharacterTypes;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "T_Pj", uniqueConstraints = @UniqueConstraint(columnNames = { "pj_name", "adventure_Name" }))
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PJ implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pj_name")
	@JsonProperty("nombre") // Puedes dejarlo como "name" si prefieres el original
	private String name;

	@ManyToOne
	@JoinColumn(name = "race_Name", unique = false)
	@JsonManagedReference // Si Race tiene @JsonBackReference en su List<PJ>
	private Race race;

	@Column(name = "power")
	@JsonProperty("poder")
	private String power;

	@Column(name = "character_Type")
	@Enumerated(EnumType.STRING)
	@JsonProperty("tipo_personaje")
	private CharacterTypes characterType;

	@ManyToOne
	@JoinColumn(name = "adventure_Name", unique = false)
	@JsonBackReference // Evita recursión infinita si Adventure tiene List<PJ>
	private Adventure adventure;

	@ManyToOne
	@JoinColumn(name = "equipped_Weapon", nullable = true, unique = false)
	@JsonManagedReference // Si Item tiene @JsonBackReference (por ejemplo en Equipment)
	private Item weapon;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "T_pj_bodytype", joinColumns = @JoinColumn(name = "pj_name"), inverseJoinColumns = @JoinColumn(name = "bodyType_name"))
	@JsonProperty("tipos_corporales")
	private Set<BodyType> bodyTypes = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "T_Pj_Skill", joinColumns = @JoinColumn(name = "pj_name"), inverseJoinColumns = @JoinColumn(name = "sk_name"))
	@JsonProperty("habilidades")
	private Set<Skill> skills = new HashSet<>();

	@OneToMany(mappedBy = "idIn.pj", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference // Si Inventory tiene @JsonBackReference a PJ
	private List<Inventory> inventario = new ArrayList<>();

	@OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonManagedReference // Si Equipment tiene @JsonBackReference
	private Equipment equipment;

	@Column(name = "basic_hitter")
	@Enumerated(EnumType.STRING)
	@JsonProperty("ataque_basico")
	private BasicHitter basicHitter = BasicHitter.FISTS;

	@Column(name = "inspiration_Points")
	@JsonProperty("puntos_inspiracion")
	private int inspirationPoints = 0;

	@Column(name = "able")
	@JsonProperty("activo")
	private boolean able = true;

	@Lob
	@Column(name = "portrait", nullable = true, columnDefinition = "MEDIUMBLOB")
	private byte[] profile;

	@Column(name = "atl")
	@JsonProperty("atlético")
	private int atl = 1;

	@Column(name = "str")
	@JsonProperty("fuerza")
	private int str = 1;

	@Column(name = "end")
	@JsonProperty("resistencia")
	private int end = 1;

	@Column(name = "min")
	@JsonProperty("mente")
	private int min = 1;

	@Column(name = "dex")
	@JsonProperty("destreza")
	private int dex = 1;

	@Transient
	@JsonIgnore
	private double modA, modS, modE, modM, modD;

	@Transient
	@JsonIgnore
	private List<Double> mods = new ArrayList<>();

	@JsonProperty("modificadores")
	public List<Double> getAllMods() {
		return List.of(modA, modS, modE, modM, modD);
	}

	@Column(name = "xpA")
	@JsonProperty("xp_atlético")
	private int xpA = 0;

	@Column(name = "xpS")
	@JsonProperty("xp_fuerza")
	private int xpS = 0;

	@Column(name = "xpE")
	@JsonProperty("xp_resistencia")
	private int xpE = 0;

	@Column(name = "xpM")
	@JsonProperty("xp_mente")
	private int xpM = 0;

	@Column(name = "xpD")
	@JsonProperty("xp_destreza")
	private int xpD = 0;

	@Column(name = "glimmers")
	@JsonProperty("glimmers")
	private double glimmers = 0;

	@Transient
	@JsonIgnore
	private double weight;

	@Column(name = "max_Carry")
	@JsonProperty("carga_max")
	private double maxCarry = 100;

	@Column(name = "current_Carry")
	@JsonProperty("carga_actual")
	private double currentCarry = 0.0;

	@Column(name = "weight_Lose")
	@JsonProperty("pérdida_peso")
	private double weightLose = 0;

	@Transient
	@JsonIgnore
	private int maxHp, maxActions, maxKcal;

	@Column(name = "current_Hp")
	@JsonProperty("hp_actual")
	private int hp = 1;

	@Column(name = "current_Ac")
	@JsonProperty("acciones_actuales")
	private int actions = 1;

	@Column(name = "current_Kc")
	@JsonProperty("kcal_actuales")
	private int kcal = 1;

	@Transient
	@JsonIgnore
	private String[] dices = { "atl", "str", "end", "min", "dex", "acr", "vas", "per", "cha" };

	@Transient
	@JsonIgnore
	private double leftStrong;

	@Transient
	@JsonIgnore
	private int speed, preception, charisma, acrobatics, balance_Lv, vaste, vaste_Distance;

	public PJ(String name, Race race, String power, byte[] profile, CharacterTypes characterType, Adventure adventure,
			boolean able, int atl, int str, int end, int min, int dex, double glimmers) {
		this.name = name;
		this.race = race;
		this.power = power;
		this.profile = profile;
		this.characterType = characterType;
		this.adventure = adventure;
		this.able = able;
		this.atl = atl;
		this.str = str;
		this.end = end;
		this.min = min;
		this.dex = dex;
		this.glimmers = glimmers;
	}

	@Override
	public String toString() {
		return name;
	}

	public String showInfo() {
		return "\n--------------------------------------------------\nPJ name=" + name + ", race=" + race.getName()
				+ ", power=" + power + ", \ncharacterType=" + characterType + ", adventure=" + adventure + ", weapon="
				+ weapon + ", \nbodyTypes=" + bodyTypes.toString() + ", \ninspirationPoints=" + inspirationPoints
				+ ", able=" + able + ", \natl=" + atl + ", \nstr=" + str + ", \nend=" + end + ", \nmin=" + min
				+ ", \ndex=" + dex + ", \nmodA=" + modA + ", modS=" + modS + ", modE=" + modE + ", modM=" + modM
				+ ", modD=" + modD + ", \nxpA=" + xpA + ", xpS=" + xpS + ", xpE=" + xpE + ", xpM=" + xpM + ", xpD="
				+ xpD + ", \nglimmers=" + glimmers + ", weight=" + weight + ", \nmaxCarry=" + maxCarry
				+ ", currentCarry=" + currentCarry + ", weightLose=" + weightLose + ", \nmaxHp=" + maxHp
				+ ", maxActions=" + maxActions + ", maxKcal=" + maxKcal + ", \nhp=" + hp + ", actions=" + actions
				+ ", kcal=" + kcal + ", \ndices=" + Arrays.toString(dices) + ", \nleftStrong=" + leftStrong
				+ ", \nspeed=" + speed + ", \npreception=" + preception + ", \ncharisma=" + charisma + ", \nacrobatics="
				+ acrobatics + ", \nbalance_Lv=" + balance_Lv + ", vaste=" + vaste + ", vaste_Distance="
				+ vaste_Distance + "]\n--------------------------------------------------\n\n";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		PJ otro = (PJ) obj;
		return this.name.equals(otro.name);
	}

	public PJ(String n) {
		this.name = n;
	}

	public PJ() {
		// TODO Auto-generated constructor stub
	}
	
	

	public BasicHitter getBasicHitter() {
		return basicHitter;
	}

	public void setBasicHitter(BasicHitter basicHitter) {
		this.basicHitter = basicHitter;
	}

	public int getInspirationPoints() {
		return inspirationPoints;
	}

	public void setInspirationPoints(int inspirationPoints) {
		this.inspirationPoints = inspirationPoints;
	}

	public boolean isAble() {
		return able;
	}

	public void setAble(boolean able) {
		this.able = able;
	}

	public int getAtl() {
		return atl;
	}

	public void setAtl(int atl) {
		this.atl = atl;
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getDex() {
		return dex;
	}

	public void setDex(int dex) {
		this.dex = dex;
	}

	public int getXpA() {
		return xpA;
	}

	public void setXpA(int xpA) {
		this.xpA = xpA;
	}

	public int getXpS() {
		return xpS;
	}

	public void setXpS(int xpS) {
		this.xpS = xpS;
	}

	public int getXpE() {
		return xpE;
	}

	public void setXpE(int xpE) {
		this.xpE = xpE;
	}

	public int getXpM() {
		return xpM;
	}

	public void setXpM(int xpM) {
		this.xpM = xpM;
	}

	public int getXpD() {
		return xpD;
	}

	public void setXpD(int xpD) {
		this.xpD = xpD;
	}

	public double getGlimmers() {
		return glimmers;
	}

	public void setGlimmers(double glimmers) {
		this.glimmers = glimmers;
	}

	public double getMaxCarry() {
		return maxCarry;
	}

	public void setMaxCarry(double maxCarry) {
		this.maxCarry = maxCarry;
	}

	public double getCurrentCarry() {
		return currentCarry;
	}

	public void setCurrentCarry(double currentCarry) {
		this.currentCarry = currentCarry;
	}

	public double getWeightLose() {
		return weightLose;
	}

	public void setWeightLose(double weightLose) {
		this.weightLose = weightLose;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getActions() {
		return actions;
	}

	public void setActions(int actions) {
		this.actions = actions;
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public CharacterTypes getCharacterType() {
		return characterType;
	}

	public void setCharacterType(CharacterTypes characterType) {
		this.characterType = characterType;
	}

	public Adventure getAdventure() {
		return adventure;
	}

	public void setAdventure(Adventure adventure) {
		this.adventure = adventure;
	}

	public Item getWeapon() {
		return weapon;
	}

	public void setWeapon(Item weapon) {
		this.weapon = weapon;
	}

	public Set<BodyType> getBodyTypes() {
		return bodyTypes;
	}

	public void setBodyTypes(Set<BodyType> bodyTypes) {
		this.bodyTypes = bodyTypes;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public List<Inventory> getInventario() {
		return inventario;
	}

	public void setInventario(List<Inventory> inventario) {
		this.inventario = inventario;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public byte[] getProfile() {
		return profile;
	}

	public void setProfile(byte[] profile) {
		this.profile = profile;
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
		return mods;
	}

	public void setMods(List<Double> mods) {
		this.mods = mods;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getMaxActions() {
		return maxActions;
	}

	public void setMaxActions(int maxActions) {
		this.maxActions = maxActions;
	}

	public int getMaxKcal() {
		return maxKcal;
	}

	public void setMaxKcal(int maxKcal) {
		this.maxKcal = maxKcal;
	}

	public String[] getDices() {
		return dices;
	}

	public void setDices(String[] dices) {
		this.dices = dices;
	}

	public double getLeftStrong() {
		return leftStrong;
	}

	public void setLeftStrong(double leftStrong) {
		this.leftStrong = leftStrong;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getPreception() {
		return preception;
	}

	public void setPreception(int preception) {
		this.preception = preception;
	}

	public int getCharisma() {
		return charisma;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	public int getAcrobatics() {
		return acrobatics;
	}

	public void setAcrobatics(int acrobatics) {
		this.acrobatics = acrobatics;
	}

	public int getBalance_Lv() {
		return balance_Lv;
	}

	public void setBalance_Lv(int balance_Lv) {
		this.balance_Lv = balance_Lv;
	}

	public int getVaste() {
		return vaste;
	}

	public void setVaste(int vaste) {
		this.vaste = vaste;
	}

	public int getVaste_Distance() {
		return vaste_Distance;
	}

	public void setVaste_Distance(int vaste_Distance) {
		this.vaste_Distance = vaste_Distance;
	}

}
