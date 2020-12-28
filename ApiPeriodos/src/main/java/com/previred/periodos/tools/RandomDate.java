package com.previred.periodos.tools;

import java.io.Console;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Generador de fechas aleatorio
 * @author mgonzalez@previred.com
 */
public class RandomDate {
    private final LocalDate minDate;
    private final LocalDate maxDate;
    private final Random random;

    /**
     * Inicializa el rango de fecha a generar
     * @param minDate Fecha inicial
     * @param maxDate Fecha final
     */
    public RandomDate(LocalDate minDate, LocalDate maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
        this.random = new Random();
    }

    /**
     * Genera una fecha aleatoria entre la fecha inicializada como inicial y final.
     * La fecha generada es el primer día del mes seleccionado
     * @return 
     */
    public LocalDate nextDate() {
        int minDay = (int) minDate.toEpochDay();
        int maxDay = (int) maxDate.toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate.withDayOfMonth(1);
    }

    @Override
    public String toString() {
        return "RandomDate{" +
                "maxDate=" + maxDate +
                ", minDate=" + minDate +
                '}';
    }
    /**
     * Genera una Lista con las fechas faltantes y totales.
     * @return 
     */
    public List<LocalDate> range(){
    List<LocalDate> dates = Stream.iterate(minDate, date -> date.plusMonths(1).withDayOfMonth(1))
    	       .limit(ChronoUnit.MONTHS.between(minDate, maxDate))
    	       .collect(Collectors.toList());
    return dates;
    }
    
}
