package com.previred.periodos.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * Clase Util para generacion de archivos de salida solicitado
 * @author Edwyn Mayorga
 *
 */
@Slf4j
@Component
public class GenerarArchivo {
	Logger log = LoggerFactory.getLogger(GenerarArchivo.class); 

	public GenerarArchivo() {
		// TODO Auto-generated constructor stub
	}
	@Autowired (required=true)
	private  FileProperties fileProperties;
	
	
	/**
	 * Permite la generacion de archivos dado el parametros datos.
	 * @param datos
	 */
	public  void generarArchivoSalida (String datos){
		 try {

			 StringBuilder sbRuta = new StringBuilder();
			 	sbRuta.append(fileProperties.getRutaArchivo());
			 	sbRuta.append(fileProperties.getNombreArchivo());
			 	sbRuta.append(fileProperties.getExtensionArchivo());

		    	
			 String contenido = datos;
			 File file = new File(sbRuta.toString());
			 
		     if (!file.exists()) {
		         file.createNewFile();
		     }
		     FileWriter fw = new FileWriter(file);
		     BufferedWriter bw = new BufferedWriter(fw);
		     bw.write(contenido);
		     bw.close();
		 } catch (IOException e) {
		     log.warn ("Ha ocurrido un error al generar el archivo ");
		     e.printStackTrace();
		   
		 }
	}

}
