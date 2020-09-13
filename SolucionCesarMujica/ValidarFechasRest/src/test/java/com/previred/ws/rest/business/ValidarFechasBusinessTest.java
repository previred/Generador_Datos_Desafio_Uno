package com.previred.ws.rest.business;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;
import com.previred.ws.rest.dao.PeriodosDAO;
import com.previred.ws.rest.util.FechasUtil;
import com.previred.ws.rest.vo.RespuestaValidacionFechasVO;

public class ValidarFechasBusinessTest {
	
	private PeriodosDAO periodosDAO = new PeriodosDAO();
	
	/**
	 * validarFechas metodo que se encarga de obtener el resultado de la diferencia entre dos arreglos de fechas
	 * @return
	 */
	@Test
	@Ignore
	public void validarFechas () {
		
		RespuestaValidacionFechasVO respuesta = new RespuestaValidacionFechasVO();
		//invocamos el metodo que consume el servicio Rest
		respuesta = periodosDAO.consultaPeriodo();
		
		if(respuesta != null && respuesta.getFechas() != null) {
			//obtenemos el rango total de fechas entre la fecha inicial y la fecha final
			List<String> fechasTotales = FechasUtil.fechasEntreFechas(FechasUtil.stringToDate(respuesta.getFechaCreacion()),FechasUtil.stringToDate(respuesta.getFechaFin()));
			//obtenemos las fechas aleatorias retornadas por el servicio rest en una Lista
			List<String> fechasRespuesta = Arrays.asList(respuesta.getFechas());
			//convertimos la lista de fechas enteras  en un flujo y filtramos los elementos que no se encuentren en la lita de fechas del servicio Rest
			List<String> fechasFaltantes = fechasTotales.stream().filter(fecha -> !fechasRespuesta.contains(fecha)).collect(Collectors.toList());
			String[] fechasFalt = new String[fechasFaltantes.size()];
			//convertimos el resultado en un Array y lo agregamos a la respuesta final
			respuesta.setFechasFaltantes(fechasFaltantes.toArray(fechasFalt));
			
		}
		Gson gson = new Gson();
		String respustaJson = gson.toJson(respuesta);
		assertNotNull(respustaJson);		
	}

}
