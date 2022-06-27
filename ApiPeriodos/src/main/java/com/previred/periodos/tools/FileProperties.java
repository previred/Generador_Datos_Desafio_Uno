package com.previred.periodos.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * FileProperties: permite realizar la carga de las propiedades configuradas
 * en el application.properties 
 * 
 * @author Edwyn Mayorga
 *
 */
@ConfigurationProperties
public class FileProperties {
	
	
	@Value ("${prop.service.url}")
	private String urlServicio;

	@Value ("${prop.incluir.ultimo.mes}")
	private String incluirUltimoMEs ;
	
	@Value ("${prop.content.type}")
	private String accepterHeader;
	
	@Value ("${prop.ruta.archivo}")
	private String rutaArchivo ;

	@Value("${prop.nombre.archivo}")
	private String nombreArchivo;
	
	@Value ("${prop.extenion.archivo}")
	private String extensionArchivo;
	
	public String getUrlServicio() {
		return urlServicio;
	}
	

	public void setUrlServicio(String urlServicio) {
		this.urlServicio = urlServicio;
	}


	public String getIncluirUltimoMEs() {
		return incluirUltimoMEs;
	}


	public void setIncluirUltimoMEs(String incluirUltimoMEs) {
		this.incluirUltimoMEs = incluirUltimoMEs;
	}


	public String getAccepterHeader() {
		return accepterHeader;
	}


	public void setAccepterHeader(String accepterHeader) {
		this.accepterHeader = accepterHeader;
	}
	
	public String getRutaArchivo() {
		return rutaArchivo;
	}


	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}


	public String getNombreArchivo() {
		return nombreArchivo;
	}


	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}


	public String getExtensionArchivo() {
		return extensionArchivo;
	}


	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}
	

	

}
