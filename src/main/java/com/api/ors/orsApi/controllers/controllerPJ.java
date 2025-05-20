package com.api.ors.orsApi.controllers;

import com.api.ors.orsApi.entitys.Adventure;
import com.api.ors.orsApi.entitys.PJ;
import com.api.ors.orsApi.service.servicePJ;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pj")
public class controllerPJ {

	@PostMapping("/completos")
	public List<PJ> getCompletos(@RequestBody Adventure adventure, @RequestParam(defaultValue = "false") boolean dm)
			throws Exception {
		return servicePJ.getCompletePJs(adventure, dm);
	}

	@PutMapping("/update")
	public void actualizarPJ(@RequestBody PJ pj) throws Exception {
		servicePJ.update(pj);
	}

	@PostMapping("/calcular/secondary-stats")
	public void calcularSecondaryStats(@RequestBody PJ pj) throws Exception {
		servicePJ.setSecondaryStats(pj);
	}

	@PostMapping("/calcular/mods")
	public void calcularMods(@RequestBody PJ pj) throws Exception {
		servicePJ.setMods(pj);
	}

	@PostMapping("/calcular/basic-hitter")
	public void calcularGolpeBasico(@RequestBody PJ pj) {
		servicePJ.setBasicHitter(pj);
	}

	@PostMapping("/calcular/level-check")
	public void calcularNivel(@RequestBody PJ pj) {
		servicePJ.setLevelCheck(pj);
	}

	@GetMapping("/stat")
	public int obtenerStat(@RequestParam String type, @RequestBody PJ pj) {
		return servicePJ.getStat(type, pj);
	}
}
