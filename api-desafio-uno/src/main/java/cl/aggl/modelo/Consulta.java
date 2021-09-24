package cl.aggl.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Consulta
 */
@Validated
public class Consulta {
	
	  @JsonProperty("id")
	  private Long id = null;

	  @JsonProperty("fechaCreacion")
	  private LocalDate fechaCreacion = null;

	  @JsonProperty("fechaFin")
	  private LocalDate fechaFin = null;
	  
	  @JsonProperty("fechas")
	  @Valid
	  private List<LocalDate> fechas = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<LocalDate> getFechas() {
		return fechas;
	}

	public void setFechas(List<LocalDate> fechas) {
		this.fechas = fechas;
	}
	  
	  

}
