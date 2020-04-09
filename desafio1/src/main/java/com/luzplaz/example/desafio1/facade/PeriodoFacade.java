package com.luzplaz.example.desafio1.facade;

import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.luzplaz.example.desafio1.config.ApiConfig;
import com.luzplaz.example.desafio1.model.Periodo;

@Service
public class PeriodoFacade {

	@Autowired
	protected RestTemplate restTemplate;

	@Autowired
	protected ApiConfig apiConfig;
	
	
	private static final Logger log = LoggerFactory.getLogger(PeriodoFacade.class);

	/**
	 * Método que abstrae complejidad de llamado a servicio externo
	 * 
	 * @return
	 * @throws ResourceAccessException
	 * @throws HttpClientErrorException
	 * @throws URISyntaxException
	 */
	public Periodo getPeriodo() throws ResourceAccessException, HttpClientErrorException, URISyntaxException {

		Periodo periodo = null;

		// en .properties
		periodo = restTemplate.getForObject(apiConfig.serviceUrl(), Periodo.class);
			
		if (periodo != null && periodo.getFechas().size() > 0)
			log.info("Total de fechas: " + periodo.getFechas().size() + " -- Registro de fechas inicia en: "
					+ periodo.getFechas().get(0) + " y finaliza en:"
					+ periodo.getFechas().get((periodo.getFechas().size() - 1)));

		return periodo;
	}

}
