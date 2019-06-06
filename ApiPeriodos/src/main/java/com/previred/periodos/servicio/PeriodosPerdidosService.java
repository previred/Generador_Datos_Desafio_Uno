package com.previred.periodos.servicio;

import com.previred.periodos.swagger.codegen.model.Periodo;
import com.previred.periodos.tools.RandomDate;

import static java.time.Period.between;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

/**
 *
 * @author mgonzalez@previred.com
 */
@Service
public class PeriodosPerdidosService {

    private final static int MIN = 90;
    private final static int MAX = 100;

    /**
     * Genera un Objetos periodos, los rangos de fechas van de 1980 a 2019 el rango
     * de lista de fechas en el periodo va desde 90 a 100
     *
     * @return
     */
    public Periodo getPeriodosPerdidos(String fechaIncio, String fechaFinal) {
        // Se asguran almenos 100 fechas distintas 2004 - 1013
        LocalDate fechaInicioAux = LocalDate.parse(fechaIncio);
        LocalDate fechaFinalAux = LocalDate.parse(fechaFinal);
        Periodo periodo = new Periodo();
        periodo.setId(1L);
        periodo.setFechaCreacion(fechaInicioAux);
        periodo.setFechaFin(fechaFinalAux);
        RandomDate fechaPeriodos = new RandomDate(periodo.getFechaCreacion(), periodo.getFechaFin());
        Random aleatorio = new Random();
        int cantidadPeriodos = aleatorio.nextInt((MAX - MIN) + 1) + MIN;
        Set<LocalDate> rangoDeFechasCompleto = new HashSet();
        rangoDeFechasCompleto.add(fechaInicioAux);
        for (int i = 0; i <= ChronoUnit.MONTHS.between(fechaInicioAux, fechaFinalAux); i++) {
            if(rangoDeFechasCompleto.size() <= MAX-1){
            rangoDeFechasCompleto.add(fechaInicioAux.plusMonths(i));
            }
        }
        periodo.setFechasFaltantes(rangoDeFechasCompleto.stream().sorted().collect(Collectors.toList()));
        Set<LocalDate> fechas = new HashSet();
        while (fechas.size() <= cantidadPeriodos) {
            fechas.add(fechaPeriodos.nextDate());
        }
        periodo.setFechas(fechas.stream().sorted().collect(Collectors.toList()));
        List<LocalDate> filtro = getFilterOutput(periodo.getFechasFaltantes(), periodo.getFechas());
        periodo.setFechasFaltantes(filtro.stream().sorted().collect(Collectors.toList()));
        return periodo;
    }
    /** 
     * metodo que compara las litas y devuelve los objetos no encontrados.
     * @return
    */
    private static List<LocalDate> getFilterOutput(List<LocalDate> dates, List<LocalDate> filter) {
        List<LocalDate> result = new ArrayList<>();
            for (LocalDate dateAux : dates) {
                if(!filter.contains(dateAux)){
                    result.add(dateAux);
                    System.out.println(dateAux);
                }
            }
        
        return result;
    }
}
