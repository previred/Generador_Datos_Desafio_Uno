package com.previred.ws.rest.vo;

import java.io.Serializable;

public class RespuestaValidacionFechasVO implements Serializable{
	
	private static final long serialVersionUID = -965933776608825374L;
	private int id;
    private String fechaCreacion;
    private String fechaFin;
    private String[] fechas;
    private String[] fechasFaltantes;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String[] getFechas() {
		return fechas;
	}
	public void setFechas(String[] fechas) {
		this.fechas = fechas;
	}
	public String[] getFechasFaltantes() {
		return fechasFaltantes;
	}
	public void setFechasFaltantes(String[] fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}

}

