package cl.aggl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cl.aggl.modelo.Consulta;
import cl.aggl.modelo.Respuesta;
import cl.aggl.servicio.PeriodosFaltantes;

@RestController
public class PeriodoController {
	
	Logger log = LoggerFactory.getLogger(PeriodoController.class);	

	@Autowired
	private PeriodosFaltantes periodosFaltantes;
	
	@Autowired
    RestTemplate restTemplate;	
	
	@RequestMapping(value = "/periodosFaltanes",produces = { "application/json" },method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Respuesta> respuestaDesafio(){		
		ResponseEntity<Consulta> consulta = restTemplate
				                             .getForEntity("http://localhost:8080/periodos/api", Consulta.class);
		return new ResponseEntity<>(periodosFaltantes.setPeriodosFaltantes(consulta.getBody()), HttpStatus.OK);		
	}
	
	

}
