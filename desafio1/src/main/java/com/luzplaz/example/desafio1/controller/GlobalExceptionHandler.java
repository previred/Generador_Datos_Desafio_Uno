package com.luzplaz.example.desafio1.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public @ResponseBody ExceptionJSONInfo handleException(HttpServletRequest request, Exception ex){
		
		ExceptionJSONInfo response = new ExceptionJSONInfo();
		response.setPath(request.getRequestURI());
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setException(ex.getClass().getName());
		log.error(ex.getMessage());
		
		return response;
	}
	
	/**
	 * 
	 * @author Luz Plaz
	 *
	 */
	private class ExceptionJSONInfo {

		private String path;
		private String message;
		private Integer status; 
		private String exception;
	    
		public String getPath() {
			return path;
		}
		public void setPath(String url) {
			this.path = url;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public String getException() {
			return exception;
		}
		public void setException(String exception) {
			this.exception = exception;
		}
	}
}
