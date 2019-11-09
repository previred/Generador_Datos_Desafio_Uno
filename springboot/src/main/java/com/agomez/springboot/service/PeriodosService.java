package com.agomez.springboot.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agomez.springboot.vo.PeriodosVO;

@RestController
@RequestMapping(path = "/servicesREST/JP")
public class PeriodosService {

	@RequestMapping(method = RequestMethod.POST, path = "/validarPeriodos", consumes = "application/json", produces = "application/json")
	public @ResponseBody PeriodosVO validateUser(@RequestBody PeriodosVO periodos) throws Exception {
		PeriodosVO response = new PeriodosVO();

		try {
			// Se obtienen fechas de creacion y fecha fin enviadas vía JSON
			LocalDate fechaCreacion = periodos.getFechaCreacion();
			LocalDate fechaFin = periodos.getFechaFin();

			// Se obtiene lista de fechas comprendidas entre fecha de creacion y fecha fin
			List<LocalDate> periodosCompletos = Stream.iterate(fechaCreacion, date -> date.plusMonths(1))
					.limit(ChronoUnit.MONTHS.between(fechaCreacion, fechaFin.plusMonths(1)))
					.collect(Collectors.toList());

			// Se obtiene lista de fechas faltantes
			List<LocalDate> fechasEnviadas = periodos.getFechas();
			List<LocalDate> fechasFaltantes = periodosCompletos.stream().filter(x -> !fechasEnviadas.contains(x))
					.collect(Collectors.toList());

			// Se cargan los valores para el response
			response.setId(periodos.getId());
			response.setFechaCreacion(periodos.getFechaCreacion());
			response.setFechaFin(periodos.getFechaFin());
			response.setFechas(periodos.getFechas());
			response.setFechasFaltantes(fechasFaltantes);

		} catch (Exception e) {
			return response;
		}

		return response;
	}

}