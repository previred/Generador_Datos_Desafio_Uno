package com.previred.periodos.servicio;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.previred.periodos.swagger.codegen.model.Periodo;
import com.previred.periodos.swagger.codegen.model.PeriodoResponse;
import com.previred.periodos.tools.FileProperties;

/**
 * clase contien los metodos para el funcionamiento de /apiPeriodosFaltante
 * 
 * @author Edwyn Mayorga
 *
 */

@Service
public class PeriodosFaltantesService {
		Logger log =  LoggerFactory.getLogger(PeriodosFaltantesService.class);
	
	
	 	@Autowired
		private FileProperties fileProperties;

		private PeriodoResponse  periodoResponse;
		private List<LocalDate> fechasFaltantes;
		private LocalDate fechaInicio;
		private LocalDate fechaFin;
		private Period period;
		
		
		/**
		 * Este metodo genera los periodos faltantes a partir del 
		 * 
		 * @param periodo
		 * @return
		 * @throws DateTimeException
		 */
		public PeriodoResponse generaPeriodosFaltantes (Periodo periodo) throws DateTimeException {
				
				periodoResponse = new PeriodoResponse();
				fechaInicio = periodo.getFechaCreacion();
				fechaFin = periodo.getFechaFin();
				fechasFaltantes = new ArrayList<LocalDate> ();
				
				period = getPeriod(fechaInicio, fechaFin);
				
				// Se incrementa el mes en +1 para que incluya el mes final 
				int meses = (period.getYears() * 12) + period.getMonths()+ Integer.parseInt(fileProperties.getIncluirUltimoMEs());
				log.info("Cantidad meses es Totales: " +meses+ " Cantidad de Fechas Servicio GDD: " + periodo.getFechas().size());
				
				int anio = fechaInicio.getYear();
				int k = fechaInicio.getMonthValue();
		        long fechaNumero ;

				for (int i = 1; i<=meses ; i++) {
					
					fechaInicio = LocalDate.of(anio, k, 1); 
					fechaNumero = fechaInicio.toEpochDay();
					if (k == 12) {
						k = 0;
						anio = anio +1;
					}
					k= k+1;
					if (! periodo.getFechas().contains(LocalDate.ofEpochDay(fechaNumero))) {
						fechasFaltantes.add(LocalDate.ofEpochDay(fechaNumero));
					}
						
					
				
				}
				periodoResponse.setFechaCreacion(periodo.getFechaCreacion());
				periodoResponse.setFechaFin(periodo.getFechaFin());
				periodoResponse.setFechas(periodo.getFechas());
				periodoResponse.setFechasFaltantes(fechasFaltantes);
				
			return periodoResponse;
			
		}
		
		
		
		/**
		 * Retorna la cantidad de anios y meses que hay entre dos fechas
		 * @param dob
		 * @param now
		 * @return
		 */
		private static Period getPeriod(LocalDate fecheDesde, LocalDate fechaHasta) {
	        return Period.between(fecheDesde, fechaHasta);
	    }

}
