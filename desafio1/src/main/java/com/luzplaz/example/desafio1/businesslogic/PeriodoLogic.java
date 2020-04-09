package com.luzplaz.example.desafio1.businesslogic;

import java.net.URISyntaxException;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.luzplaz.example.desafio1.model.Periodo;

public interface PeriodoLogic {

	/**
	 * Método para obtener el Periodo completo
	 * 
	 * @return
	 * @throws ResourceAccessException
	 * @throws HttpClientErrorException
	 * @throws URISyntaxException
	 */
	public Periodo obtenerPeriodoCompleto()
			throws ResourceAccessException, HttpClientErrorException, URISyntaxException;
}
