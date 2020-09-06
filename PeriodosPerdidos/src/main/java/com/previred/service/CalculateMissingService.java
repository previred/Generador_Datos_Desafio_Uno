package com.previred.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.previred.domain.Periodo;

@Service
public class CalculateMissingService {
	
	/**
	 * Calculate Missing Dates
	 * 
	 * @param periodoList LocalDate
	 * @return List LocalDates
	 */
	public List<LocalDate> calculateMissing(Periodo periodo) {
		List<LocalDate> faltantes = new ArrayList<>();

		LocalDate date = periodo.getFechaCreacion();

		while (date.isBefore(periodo.getFechaFin().plusMonths(1))) {
			if (!periodo.getFechas().contains(date)) {
				faltantes.add(date);
			}
			date = date.plusMonths(1);
		}

		return faltantes;
	}
	
}
