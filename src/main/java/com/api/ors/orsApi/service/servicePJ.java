package com.api.ors.orsApi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.api.ors.orsApi.entitys.Adventure;
import com.api.ors.orsApi.entitys.BodyType;
import com.api.ors.orsApi.entitys.Equipment;
import com.api.ors.orsApi.entitys.PJ;
import com.api.ors.orsApi.extraUtiles.EnumsDeItems.BasicHitter;
import com.api.ors.orsApi.extraUtiles.EnumsDeItems.CharacterTypes;
import com.api.ors.orsApi.repository.EntidadDao;
import com.api.ors.orsApi.repository.InvDao;

import jakarta.transaction.Transactional;

//En la aplicación, sólo habría que cambiar el contenido de pjUtil a llamadas http a este ServicePJ 
//y todo debería seguri funcionando igual

public class servicePJ {

	static EntidadDao pjDao = new EntidadDao(PJ.class);
	static EntidadDao pjEqDao = new EntidadDao(Equipment.class);
	static InvDao invDao = new InvDao();

	public static List<PJ> getCompletePJs(Adventure adventure, boolean dm) throws Exception {
		List<PJ> toos = new ArrayList<PJ>();
		List<PJ> temporal = pjDao.obtenerTodos();
		for (PJ k : temporal.stream().filter(pj -> pj.getAdventure().getName().equals(adventure.getName()))
				.collect(Collectors.toList())) {
			if (dm) {
				update(k);
				toos.add(k);
			} else {
				if (k.getCharacterType().equals(CharacterTypes.PARTY) && k.isAble()) {
					update(k);
					toos.add(k);
				}
			}
		}
		return toos;
	}

	public static synchronized void update(PJ pj) throws Exception { // Updatea los valores y GUARDA en la bbdd
		System.out.println("GUARDADO ---------------------------------------------------------------------------");
		setBasicHitter(pj);
		setMods(pj);
		setLevelCheck(pj);
		setSecondaryStats(pj);
		pjDao.actualiza(pj);
	}

	public static void setBasicHitter(PJ pj) {
		switch (calculMayorStat(pj)) {
		case "atl":
			pj.setBasicHitter(BasicHitter.KNEES);
			break;
		case "str":
			pj.setBasicHitter(BasicHitter.FISTS);
			break;
		case "end":
			pj.setBasicHitter(BasicHitter.ELBOWS);
			break;
		case "min":
			pj.setBasicHitter(BasicHitter.FINGERS);
			break;
		case "dex":
			pj.setBasicHitter(BasicHitter.BLADEHANDS);
			break;
		case "acr":
			pj.setBasicHitter(BasicHitter.LEGS);
			break;
		case "per":
			pj.setBasicHitter(BasicHitter.BLADEHANDS);
			break;
		case "cha":
			pj.setBasicHitter(BasicHitter.FISTS);
			break;
		case "balance":
			pj.setBasicHitter(BasicHitter.FISTS);
			break;
		}
	}

	private static String calculMayorStat(PJ pj) {
		Map<String, Integer> stats = new HashMap<>();
		stats.put("atl", pj.getAtl());
		stats.put("str", pj.getStr());
		stats.put("end", pj.getEnd());
		stats.put("min", pj.getMin());
		stats.put("dex", pj.getDex());
		stats.put("acr", pj.getAcrobatics());
		stats.put("per", pj.getPreception());
		stats.put("cha", pj.getCharisma());
		return stats.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("balance");
		// En caso de que todos sean iguales devuelve ninguno.
	}

	public static void setSecondaryStats(PJ pj) throws Exception {

		double modA = pj.getModA();
		double modE = pj.getModE();
		double modS = pj.getModS();
		double modM = pj.getModM();
		double modD = pj.getModD();
		int atl = pj.getAtl();
		int str = pj.getStr();
		int end = pj.getEnd();
		int min = pj.getMin();
		int dex = pj.getDex();
		int hp = pj.getHp();
		int actions = pj.getActions();
		int kcal = pj.getKcal();

		///////////////// PESO
		////////////////////////

		pj.setCurrentCarry(invDao.geCurrentCarry(pj));
		pj.setWeight(pj.getWeight() + pj.getWeightLose());
		pj.setMaxCarry(Math.max(pj.getStr() * (3 - (pj.getWeight() / 95)), pj.getWeight() / 5));
		pj.setLeftStrong(pj.getMaxCarry() - pj.getCurrentCarry());
		Double weightBalance = (pj.getLeftStrong() / pj.getMaxCarry());

		///////////// LIVING
		////////////////////////

		pj.setMaxHp((int) (((end * 2.2) + (str * 0.5)) / (1 + modE)));
		pj.setMaxActions((int) ((atl + dex + str) / (1.5 + modA + (modS / 2))));
		pj.setMaxKcal((int) (((end + atl + str) * (pj.getWeight() / 60.0)) + (1800 / (1 + modS))));

		///////////// EXTRA STATS
		////////////////////////

		double factorAgilidad = weightBalance * (1 / (1 + modS));
		pj.setAcrobatics((int) ((pj.getActions() * factorAgilidad) / (1 + modA)));
		pj.setPreception((int) ((min * 1.2 + dex * 0.5) / (1 + modD)));
		double carismaBase = (min + (atl / 2.0)) / (1 + (pj.getWeight() / 80.0));
		pj.setCharisma((int) (carismaBase / (1 + modM)));

		///////////// VELOCIDAD
		////////////////////////

		double base = (atl + (str * 0.7)) / 3.3;
		double carga = Math.max(pj.getCurrentCarry() - (end * 0.7), 0);
		double penalizacion = carga * 0.6;
		double bonus = (pj.getActions() * 0.8) + (pj.getKcal() / 130.0);
		double velocidadFinal = base - penalizacion + bonus;
		velocidadFinal = Math.max(0.5, velocidadFinal);
		pj.setSpeed((int) velocidadFinal);

		////////////////////////////// JODIDO VASTE
		//////////////////////////////////////////

		int media = (atl + str + end + min + dex) / 5;
		double desviacion = Math.pow(atl - media, 2) + Math.pow(str - media, 2) + Math.pow(end - media, 2)
				+ Math.pow(min - media, 2) + Math.pow(dex - media, 2);
		int balanceLv = (int) Math.sqrt(desviacion / 5);

		pj.setBalance_Lv(balanceLv);
		double vaste = (((actions + hp) * pj.getPreception()) / (55.0 + Math.abs(balanceLv - media) * 2.0 + 1)
				+ (kcal / 40.0)) / 2.0;
		pj.setVaste((int) vaste);
		double vasteDistance = ((pj.getPreception() * pj.getSpeed()) + 1.0) / Math.max(vaste / 2.0, 1.0);
		pj.setVaste_Distance((int) vasteDistance);

	}

	public static void setMods(PJ pj) throws Exception {
		// Raza
		pj.setWeight(pj.getRace().getBaseWeight() + ((pj.getStr() + pj.getEnd()) / pj.getAtl()) * 5);
		pj.setModA(pj.getRace().getModA());
		pj.setModS(pj.getRace().getModS());
		pj.setModE(pj.getRace().getModE());
		pj.setModM(pj.getRace().getModM());
		pj.setModD(pj.getRace().getModD());
		// Body Types
		Set<BodyType> cosas = pj.getBodyTypes(); // Tengo mis dudas de si las otras clases se crean a al vez
		if (cosas != null) {
			for (BodyType bt : cosas) { // Si tiene más de uno, lo añade tambien
				System.out.println("BodyType en pertenencia: " + bt);
				pj.setModA(pj.getModA() + bt.getModA());
				pj.setModS(pj.getModS() + bt.getModS());
				pj.setModE(pj.getModE() + bt.getModE());
				pj.setModM(pj.getModM() + bt.getModM());
				pj.setModD(pj.getModD() + bt.getModD());
			}
		}
		// Equipamiento
		try {
			Equipment equipo = (Equipment) pjEqDao.obtenerPorId(pj.getName());
			List<String[]> partes = new ArrayList<String[]>();
			if (equipo.getChest() != null) {
				partes.add(equipo.getChest().getModEquipo().split(" "));
			}
			if (equipo.getLegs() != null) {
				partes.add(equipo.getLegs().getModEquipo().split(" "));
			}
			if (equipo.getFeet() != null) {
				partes.add(equipo.getFeet().getModEquipo().split(" "));
			}
			if (equipo.getHead() != null) {
				partes.add(equipo.getHead().getModEquipo().split(" "));
			}
			if (equipo.getExtra1() != null) {
				partes.add(equipo.getExtra1().getModEquipo().split(" "));
			}
			if (equipo.getExtra2() != null) {
				partes.add(equipo.getExtra2().getModEquipo().split(" "));
			}
			if (equipo.getExtra3() != null) {
				partes.add(equipo.getExtra3().getModEquipo().split(" "));
			}

			for (String[] p : partes) {
				pj.setModA(pj.getModA() + Double.parseDouble(p[0]));
				pj.setModS(pj.getModS() + Double.parseDouble(p[1]));
				pj.setModE(pj.getModE() + Double.parseDouble(p[2]));
				pj.setModM(pj.getModM() + Double.parseDouble(p[3]));
				pj.setModD(pj.getModD() + Double.parseDouble(p[4]));
			}
		} catch (Exception e) {
			// lo inserta si es la primera vez que locreamos para que pueda crear el equipo
			pjDao.actualiza(pj);
			PJ p = (PJ) pjDao.obtenerPorId(pj.getName());
			Equipment eq = new Equipment(p);
			System.out.println("Propietario del nuevo equipo: " + eq.getOwner());
			pjEqDao.insertar(eq);
			pj.setEquipment(eq);
		}

		if (pj.getWeapon() != null && pj.getWeapon().getModEquipo() != null) {
			String[] p = pj.getWeapon().getModEquipo().split(" ");
			pj.setModA(pj.getModA() + Double.parseDouble(p[0]));
			pj.setModS(pj.getModS() + Double.parseDouble(p[1]));
			pj.setModE(pj.getModE() + Double.parseDouble(p[2]));
			pj.setModM(pj.getModM() + Double.parseDouble(p[3]));
			pj.setModD(pj.getModD() + Double.parseDouble(p[4]));
		}

		// Ajustamos para que nunca sean 0
		pj.setModA(Math.max(pj.getModA(), 0.01));
		pj.setModS(Math.max(pj.getModS(), 0.01));
		pj.setModE(Math.max(pj.getModE(), 0.01));
		pj.setModM(Math.max(pj.getModM(), 0.01));
		pj.setModD(Math.max(pj.getModD(), 0.01));

		// CON ESTO LOS MODIFICADORES DEBERÍAN QUEDAR AJUSTADOS AL EQUIPO, RAZA Y BODY
		// TYPES
	}

	public static void setLevelCheck(PJ pj) {
		try {
			int o = pj.getAtl();
			double a = ((pj.getModA() * 2) * pj.getAtl() <= pj.getXpA()) ? pj.getAtl() + 1 : pj.getAtl();
			pj.setAtl((int) a);
			pj.setXpA((int) ((o != pj.getAtl()) ? 0 : pj.getXpA()));
			o = pj.getStr();
			double s = ((pj.getModS() * 2) * pj.getStr() <= pj.getXpS()) ? pj.getStr() + 1 : pj.getStr();
			pj.setStr((int) s);
			pj.setXpS((int) ((o != pj.getStr()) ? 0 : pj.getXpS()));
			o = pj.getEnd();
			double e = ((pj.getModE() * 2) * pj.getEnd() <= pj.getXpE()) ? pj.getEnd() + 1 : pj.getEnd();
			pj.setEnd((int) e);
			pj.setXpE((int) ((o != pj.getEnd()) ? 0 : pj.getXpE()));
			o = pj.getMin();
			double m = ((pj.getModM() * 2) * pj.getMin() <= pj.getXpM()) ? pj.getMin() + 1 : pj.getMin();
			pj.setMin((int) m);
			pj.setXpM((int) ((o != pj.getMin()) ? 0 : pj.getXpM()));
			o = pj.getDex();
			double d = ((pj.getModD() * 2) * pj.getDex() <= pj.getXpD()) ? pj.getDex() + 1 : pj.getDex();
			pj.setDex((int) d);
			pj.setXpD((int) ((o != pj.getDex()) ? 0 : pj.getXpD()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static Equipment getEquipment(PJ pj) {
		return (Equipment) pjEqDao.obtenerPorId(pj);
	}

	public static int getStat(String type, PJ pj) {
		switch (type) {
		case "alt":
			return pj.getAtl();
		case "str":
			return pj.getStr();
		case "end":
			return pj.getEnd();
		case "min":
			return pj.getMin();
		case "dex":
			return pj.getDex();
		case "acr":
			return pj.getAcrobatics();
		case "vas":
			return pj.getVaste();
		case "per":
			return pj.getPreception();
		case "cha":
			return pj.getCharisma();
		}
		return 0;
	}

}
