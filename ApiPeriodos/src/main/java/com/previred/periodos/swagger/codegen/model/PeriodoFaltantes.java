package com.previred.periodos.swagger.codegen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Periodo
 */
@Validated

public class PeriodoFaltantes extends Periodo {

  @JsonProperty("fechasFaltantes")
  @Valid
  private List<LocalDate> fechasFaltantes = null;



  public PeriodoFaltantes addFechasItem(LocalDate fechasItem) {
    if (this.fechasFaltantes == null) {
      this.fechasFaltantes = new ArrayList<>();
    }
    this.fechasFaltantes.add(fechasItem);
    return this;
  }

  /**
   * Get fechas
   * @return fechas
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PeriodoFaltantes periodo = (PeriodoFaltantes) o;
    return Objects.equals(this.fechasFaltantes, periodo.fechasFaltantes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fechasFaltantes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Periodo {\n");
    sb.append("    fechasFaltantes: ").append(toIndentedString(fechasFaltantes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

