package com.previred.comparatorPeriod;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author: Jorge Godoy (JG)
 */
@Data
public class Period {

    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("fechaCreacion")
    private LocalDate fechaCreacion = null;

    @JsonProperty("fechaFin")
    private LocalDate fechaFin = null;

    @JsonProperty("fechas")
    private List<LocalDate> fechas = null;

    @JsonProperty("fechasFaltantes")
    private List<LocalDate> fechasFaltantes = null;


}
