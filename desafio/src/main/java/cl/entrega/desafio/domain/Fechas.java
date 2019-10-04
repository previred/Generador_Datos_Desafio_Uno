package cl.entrega.desafio.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class Fechas {

    @JsonProperty("id")
    private int id;

    @JsonProperty("fechaCreacion")
    private String fechaCreacion;

    @JsonProperty("fechaFin")
    private String fechaFin;

    @JsonProperty("fechas")
    private List<String> fechas;

    @JsonProperty("fechaFaltante")
    private List<String>  fechaFaltante;
}
