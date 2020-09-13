package com.previred.ws.rest.util;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class FechasUtilTest {
	
	/**
	 * stringToDate metodo utilitario para convertir String a fechas en formato yyyy-MM-dd
	 * @param fechaString
	 * @return
	 */
	@Test
	@Ignore
	public void stringToDate(){
		String fechaString = "2020-12-04";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Calendar calendario = null;
        try {

            date = formatter.parse(fechaString);
            calendario = GregorianCalendar.getInstance();
            calendario.setTime(date);

        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error en Parseo de Fecha: "+fechaString);
        }
        assertNotNull(calendario);
	}
	
	/**
	 * calendarToString metodo utilitario para convertir fechas en formato Calendar a String yyyy-MM-dd 
	 * @param fechaCal
	 * @return
	 */
	@Test
	@Ignore
	public void calendarToString(){
		Calendar fechaCal = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String fechaString =  formato.format(fechaCal.getTime());
		assertNotNull(fechaString);
		
	}

	/**
	 * fechasEntreFechas metodo que retorna una Lista con un rango de fechas mensuales entre 2 fechas
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	@Test
	@Ignore
	public void fechasEntreFechas(){
		Calendar fechaInicio = FechasUtil.stringToDate("2020-01-01");
		Calendar fechaFin = FechasUtil.stringToDate("2020-05-01");		
		
		Calendar calendario = GregorianCalendar.getInstance();
		calendario.setTime(fechaInicio.getTime());
		List<String> fechasEnRango = new ArrayList<String>();
		//agregamos un mes mas a la fecha fin para que se incluya en la iteracion del while
		fechaFin.add(Calendar.MONTH, 1);
        while (calendario.before(fechaFin)) {
        	fechasEnRango.add(FechasUtil.calendarToString(calendario));
        	calendario.add(Calendar.MONTH, 1);
        }        
		assertNotNull(fechasEnRango);
	}
	
	
	
	
	
	

}
