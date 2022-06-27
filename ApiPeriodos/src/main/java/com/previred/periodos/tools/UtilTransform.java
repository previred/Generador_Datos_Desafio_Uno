package com.previred.periodos.tools;


/**
 * UtilTransform clase para definicion de transformaciones de datos
 * Se traen estos metodos de la clase Periodo Servicio
 * @author Edwyn Mayorga
 *
 */
public class  UtilTransform {
	
	public UtilTransform() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Convierte un objeto Object a String con 4 caracteres blancos al inicio 
	 * de cada linea (Excepto la Primera linea)
	 * @param o
	 * @return
	 */
	public static String toIndentedString(Object o) {
	    if (o == null) {
	      return "null";
	    }
	    return o.toString().replace("\n", "\n    ");
	  }

}
