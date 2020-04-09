package com.luzplaz.example.desafio1.businesslogic;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.luzplaz.example.desafio1.facade.PeriodoFacade;
import com.luzplaz.example.desafio1.model.Periodo;

/**
 * @author Luz Plaz
 *
 */
@Service
public class PeriodoLogicImpl implements PeriodoLogic {

	@Autowired
	public PeriodoFacade periodoFacade;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public PeriodoLogicImpl(PeriodoFacade periodoFacade) {
		super();
		this.periodoFacade = periodoFacade;
	}

	/**
	 * 
	 */
	public Periodo obtenerPeriodoCompleto()
			throws ResourceAccessException, HttpClientErrorException, URISyntaxException {

		Periodo periodoCompleto = new Periodo();
		Periodo periodo = periodoFacade.getPeriodo();

		log.info("-- Periodo entregado:" + periodo);
		
		if (periodo != null) {

			periodoCompleto = periodo;
			List<LocalDate> fechasFaltantes = new ArrayList<LocalDate>();

			if (periodo.getFechas() != null && periodo.getFechas().size() > 0) {

				// Si la fecha de creación no esta en las fechas, se agrega a faltantes y se
				// validan las siguientes
				if (!periodo.getFechas().contains(periodo.getFechaCreacion())) {

					cargaFechaFaltante(periodo.getFechaCreacion(), periodo.getFechaFin(), periodo.getFechas(),
							fechasFaltantes);

				} else {

					cargaFechaFaltante(periodo.getFechaCreacion().plusMonths(1), periodo.getFechaFin(),
							periodo.getFechas(), fechasFaltantes);
				}

				// Si la lista de fechas no contiene la fecha fin
				if (!periodo.getFechas().contains(periodo.getFechaFin())
						|| !fechasFaltantes.contains(periodo.getFechaFin())) {
					fechasFaltantes.add(periodo.getFechaFin());
				}

			}

			periodoCompleto.setFechasFaltantes(fechasFaltantes);
			log.info("-- Registros faltantes:" + periodoCompleto.getFechasFaltantes().size());
		}

		return periodoCompleto;
	}

	/**
	 * Método para cargar las fechas faltantes
	 * 
	 * @param fechaInicio
	 * @param fechas
	 * @param fechasNuevas
	 */
	private void cargaFechaFaltante(LocalDate fechaInicio, LocalDate fechaFin, List<LocalDate> fechas,
			List<LocalDate> fechasNuevas) {

		LocalDate fechaSiguiente = null;

		// Determina fecha si continene el valor de fecha de inicio y determina fecha
		// siguiente para validar
		if (fechas.contains(fechaInicio)) {

			fechaSiguiente = fechaInicio.plusMonths(1);
			cargaFechaFaltante(fechaSiguiente, fechaFin, fechas, fechasNuevas);

		} else {

			fechaSiguiente = fechaInicio;

			if (!fechas.contains(fechaSiguiente)
					&& ((fechaSiguiente.isBefore(fechaFin)) || (fechasNuevas.size() <= fechas.size()))) {

				fechasNuevas.add(fechaSiguiente);
				fechaSiguiente = fechaSiguiente.plusMonths(1);
				cargaFechaFaltante(fechaSiguiente, fechaFin, fechas, fechasNuevas);
			}

		}

	}
}
