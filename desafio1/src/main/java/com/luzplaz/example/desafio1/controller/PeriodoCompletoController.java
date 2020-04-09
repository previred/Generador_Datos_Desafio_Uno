package com.luzplaz.example.desafio1.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.luzplaz.example.desafio1.businesslogic.PeriodoLogic;
import com.luzplaz.example.desafio1.model.Periodo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api")
@Api(value = "periodo microservice", description = "Esta API completa el periodo")
@CrossOrigin
public class PeriodoCompletoController {

	@Autowired
	private PeriodoLogic periodoLogic;

	@Autowired
	public PeriodoCompletoController(PeriodoLogic periodoLogic) {
		// TODO Auto-generated constructor stub
		super();
		this.periodoLogic = periodoLogic;
	}

	@RequestMapping(path = "periodo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Periodo Completo", notes = "Retorna un periodo completo")
	public ResponseEntity<Periodo> getPeriodo()
			throws ResourceAccessException, HttpClientErrorException, URISyntaxException {

		Periodo periodoCompleto = new Periodo();
		periodoCompleto = periodoLogic.obtenerPeriodoCompleto();

		return new ResponseEntity<>(periodoCompleto, HttpStatus.OK);
	}
}
