package com.previred.periodos.swagger.codegen.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ApiException, inplenente metodos para el control de las excepciones
 * 
 * @author 
 *
 */
@Component
public class ApiException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code ;
	private String msg;
	
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    
    /**
     * Constructor sin Parametros
     */
    public ApiException () {
    	super();
    }
    
    
    /**
     * Permite configurar la respuesta en caso de un RuntimeException
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiResponseMessage> runtimeException(RuntimeException e, HttpServletRequest request ) {
    	ApiResponseMessage result = new ApiResponseMessage(1, "[RuntimeException] Clase :'"+  e.getClass().getName()+"' / Metodo: '" + request.getRequestURI() 
    	+"' / error: "+ e.getMessage());
		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
    /**
     * Permite configurar la respuesta en caso de un Exception
     * @param e
     * @param request
     * @return
     */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponseMessage> exception(Exception e, HttpServletRequest request ) {
		ApiResponseMessage result = new  ApiResponseMessage(1, "[Exception] Clase :'"+  e.getClass().getName()+"' / Metodo: '" + request.getRequestURI() 
    	+"' / error: "+ e.getMessage());
		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Permite configurar la respuesta en caso de un Throwable
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ApiResponseMessage> throwable(Throwable e, HttpServletRequest request ) {
		ApiResponseMessage result = new  ApiResponseMessage(1, "[Throwable] Clase :'"+  e.getClass().getName()+"' / Metodo: '" + request.getRequestURI() 
    	+"' / error: "+ e.getMessage());
		return new ResponseEntity<>(result, HttpStatus.NOT_IMPLEMENTED);
	}
}
