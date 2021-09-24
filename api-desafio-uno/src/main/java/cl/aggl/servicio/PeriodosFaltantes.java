package cl.aggl.servicio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cl.aggl.modelo.Consulta;
import cl.aggl.modelo.Respuesta;

@Service
public class PeriodosFaltantes {
	
	Logger log = LoggerFactory.getLogger(PeriodosFaltantes.class);	

	public Respuesta setPeriodosFaltantes(Consulta cons) {		
		Respuesta resp = new Respuesta();
		resp.setId(cons.getId());
		resp.setFechaCreacion(cons.getFechaCreacion());
		resp.setFechaFin(cons.getFechaFin());
		resp.setFechas(cons.getFechas().stream()
                .sorted()
                .collect(Collectors.toList()));		
		
		long numOfDays = ChronoUnit.DAYS.between(cons.getFechaCreacion(), cons.getFechaFin());        
		List<LocalDate> listaTotal = Stream.iterate(cons.getFechaCreacion(), date -> date.plusDays(1))
		                                    .limit(numOfDays)
		                                    .collect(Collectors.toList());
		
		Set<LocalDate> fechasTotales = new HashSet();
		for (LocalDate localDate : listaTotal) {
			fechasTotales.add(LocalDate.of(localDate.getYear()
					         , localDate.getMonthValue()
					         , localDate.getDayOfMonth()));						
		}	
		
		Set<LocalDate> fechasFaltantes = new HashSet();
		fechasFaltantes.stream().sorted()
        						.collect(Collectors.toList());
		for (LocalDate localDate : fechasTotales) {
			if(!cons.getFechas().contains(localDate))
				if(localDate.getDayOfMonth()==1)
					fechasFaltantes.add(localDate);
		}
		
		resp.setFechasFaltates(fechasFaltantes.stream().sorted()
				.collect(Collectors.toList()));
		
		return resp;
	}
	
	 

}
