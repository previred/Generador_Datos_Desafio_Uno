package com.previred.comparatorPeriod;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: Jorge Godoy (JG)
 */
@Data
public class Response {
    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("fechaCreacion")
    private String fechaCreacion = null;

    @JsonProperty("fechaFin")
    private String fechaFin = null;

    @JsonProperty("fechas")
    private List<String> fechas = null;

    @JsonProperty("fechasFaltantes")
    private List<String> fechasFaltantes = null;
}
