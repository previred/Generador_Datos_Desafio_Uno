package com.previred.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.previred.domain.Periodo;
import com.previred.service.CalculateMissingService;
import com.previred.service.PeriodoDataService;

@RestController
public class Missing {
	
	@Autowired
	PeriodoDataService periodoDataService;
	
	@Autowired
	CalculateMissingService calculateMissingService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Missing.class);
	
	@GetMapping(value = "/missing", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Periodo> getMissing(@RequestHeader("Accept") String accept) {
		if (!StringUtils.isEmpty(accept) && accept.contains(MediaType.APPLICATION_JSON.toString())) {
			Periodo periodo = periodoDataService.getPeriodos();
			List<LocalDate> fechasFaltantes = calculateMissingService.calculateMissing(periodo);
			periodo.setFechasFaltantes(fechasFaltantes);
			return new ResponseEntity<>(periodo, HttpStatus.OK);
		} else {
			LOGGER.warn(
					"ObjectMapper or HttpServletRequest not configured in default DefaultApi interface so no example is generated");
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

}
