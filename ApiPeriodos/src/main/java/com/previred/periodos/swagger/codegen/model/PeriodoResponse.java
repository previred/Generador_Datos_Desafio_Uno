package com.previred.periodos.swagger.codegen.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.previred.periodos.tools.UtilTransform;

import io.swagger.annotations.ApiModelProperty;


/**
 * Clase PeriodoResponse, objeto response para el sercisio PeriodosFaltantesService 
 * 
 * @author Edwyn Mayorga
 *
 */
public class PeriodoResponse {

	public PeriodoResponse () {
		this.id = 1;
		this.fechaCreacion = LocalDate.now();
		this.fechaFin =  LocalDate.now();
		this.fechas = new ArrayList<LocalDate>();
		this.fechasFaltantes  = new ArrayList<LocalDate>();
	}
	
	@JsonProperty ("id")
	private long id;
	
	@JsonProperty ("fechaCreacion")
	private LocalDate fechaCreacion; 
	
	@JsonProperty ("fechaFin")
	private LocalDate fechaFin;
	
	@JsonProperty ("fechas")
	private List<LocalDate> fechas ;
	
	@JsonProperty ("fechasFaltantes")
	private List<LocalDate> fechasFaltantes;

	
	/**
	  * Get id
	  * @return id
	**/
	@ApiModelProperty(value = "")
	@Valid
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	
	/**
	  * Get fechaCreacion
	  * @return fechaCreacion
	**/
	@ApiModelProperty(value = "")
	@Valid
	  
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	/**
	  * Get fechaFin
	  * @return fechaFin
	**/
	@ApiModelProperty(value = "")
	@Valid
	
	public LocalDate getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	  * Get fechas
	  * @return fechas
	**/
	@ApiModelProperty(value = "")
	@Valid
	public List<LocalDate> getFechas() {
		return fechas;
}


	public void setFechas(List<LocalDate> fechas) {
		this.fechas = fechas;
	}

	/**
	  * Get fechasFaltantes
	  * @return fechasFaltantes
	**/
	@ApiModelProperty(value = "")
	@Valid
	public List<LocalDate> getFechasFaltantes() {
		return fechasFaltantes;
	}


	public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}
	
	
	
	
	@Override
	  public int hashCode() {
	    return Objects.hash(id, fechaCreacion, fechaFin,fechas,fechasFaltantes);
	  }
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("class PeriodoResponse {\n");
	    
	    sb.append("    id: ").append(UtilTransform.toIndentedString(id)).append("\n");
	    sb.append("    fechaCreacion: ").append(UtilTransform.toIndentedString(fechaCreacion)).append("\n");
	    sb.append("    fechaFin: ").append(UtilTransform.toIndentedString(fechaFin)).append("\n");
	    sb.append("    fechas: ").append(UtilTransform.toIndentedString(fechas)).append("\n");
	    sb.append("    fechasFaltantes:	").append(UtilTransform.toIndentedString(fechasFaltantes)).append("\n");
	    sb.append("}");
	    return sb.toString();
	  }
	

}
