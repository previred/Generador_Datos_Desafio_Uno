package com.previred.ws.rest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class FechasUtil {
	
	/**
	 * stringToDate metodo utilitario para convertir String a fechas en formato yyyy-MM-dd
	 * @param fechaString
	 * @return
	 */
	public static Calendar stringToDate(String fechaString){
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
		return calendario;
	}
	
	/**
	 * calendarToString metodo utilitario para convertir fechas en formato Calendar a String yyyy-MM-dd 
	 * @param fechaCal
	 * @return
	 */
	public static String calendarToString(Calendar fechaCal){
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		return formato.format(fechaCal.getTime());
	}

	/**
	 * fechasEntreFechas metodo que retorna una Lista con un rango de fechas mensuales entre 2 fechas
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	public static List<String> fechasEntreFechas(Calendar fechaInicio, Calendar fechaFin){		
		Calendar calendario = GregorianCalendar.getInstance();
		calendario.setTime(fechaInicio.getTime());
		List<String> fechasEnRango = new ArrayList<String>();
		//agregamos un mes mas a la fecha fin para que se incluya en la iteracion del while
		fechaFin.add(Calendar.MONTH, 1);
        while (calendario.before(fechaFin)) {
        	fechasEnRango.add(FechasUtil.calendarToString(calendario));
        	calendario.add(Calendar.MONTH, 1);
        }        
		return fechasEnRango;
	}
	
	
	
	
	
	

}
