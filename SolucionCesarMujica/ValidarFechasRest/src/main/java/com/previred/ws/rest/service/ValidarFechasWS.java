package com.previred.ws.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.previred.ws.rest.business.ValidarFechasBusiness;
import com.previred.ws.rest.vo.RespuestaValidacionFechasVO;

@Path("/ValidarFechasPrevired")
public class ValidarFechasWS {
	
	@GET
	@Path("/validarFechasJSON")
	@Produces({MediaType.APPLICATION_JSON})
	public RespuestaValidacionFechasVO validarFechasJSON () {
		//esto deberia ser una injeccion de dependencias
		ValidarFechasBusiness validaFechaBusiness = new ValidarFechasBusiness();
		//se invoca la clase de negocios con la logica que valida las fechas
		return validaFechaBusiness.validarFechas();
	}

}
