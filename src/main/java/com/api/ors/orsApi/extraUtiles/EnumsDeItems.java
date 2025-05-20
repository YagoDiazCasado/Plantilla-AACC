package com.api.ors.orsApi.extraUtiles;

public class EnumsDeItems {
	public enum Rarity {
		SSULR, ULR, LR, UR, SSR, SR, S, A, B, C, D, E, F, G, H
	}

	public enum ItemFamily {
		ITEM, EDIBLE, RANGEWEAPON, MELEWEAPON, AMMO, EQUIPMENT, RACE, BODYTYPE
	}

	public enum DamageType {
		CUT, STAB, TERMAL, EXPLOSIVE, HIT
	}

	public enum Distance {
		MELEE, RANGED, MIXED
	}

	public enum ItemShape {
		SWORD, AXE, BOW, CROSSBOW, SPEAR, DAGGER, STAFF, TELESCOPE, RIFLE, MISC, CANON, HEAD, CHEST, EXTRA, LEGS, FEET,
		AMMO
	}

	public enum CharacterTypes {
		NPC, PARTY
	}

	public enum BasicHitter {
		FISTS, LEGS, BLADEHANDS, FINGERS, KNEES, ELBOWS
	}

}
