package com.previred.comparatorPeriod.service;

import com.previred.comparatorPeriod.Period;
import com.previred.comparatorPeriod.Response;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author: Jorge Godoy (JG)
 */
@Service
public class ComparatorService {

    public Response setMissingDate(Period period) {
        period.setFechasFaltantes(new ArrayList<>());
        LocalDate nextDate = period.getFechaCreacion().withDayOfMonth(1).plusMonths(1);
        boolean end = true;
        do{
            if(!period.getFechas().contains(nextDate)){
                period.getFechasFaltantes().add(nextDate);
            }
            if (nextDate.isEqual(period.getFechaFin())) {
                end = false;
            }
            nextDate = nextDate.withDayOfMonth(1).plusMonths(1);
        }while (end);

        return getResponse(period);
    }

    private Response getResponse(Period period) {
        Response response = new Response();
        response.setId(period.getId());
        response.setFechaCreacion(period.getFechaCreacion().toString());
        response.setFechaFin(period.getFechaFin().toString());
        response.setFechas(new ArrayList<>());
        for (LocalDate date : period.getFechas()){
            response.getFechas().add(date.toString());
        }
        response.setFechasFaltantes(new ArrayList<>());
        for (LocalDate date : period.getFechasFaltantes()){
            response.getFechasFaltantes().add(date.toString());
        }
        return response;
    }


}
