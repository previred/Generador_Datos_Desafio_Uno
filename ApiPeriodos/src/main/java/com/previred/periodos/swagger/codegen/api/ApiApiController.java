package com.previred.periodos.swagger.codegen.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.periodos.servicio.PeriodosService;
import com.previred.periodos.swagger.codegen.model.Periodo;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
public class ApiApiController implements ApiApi {

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private PeriodosService periodosService;

    @org.springframework.beans.factory.annotation.Autowired
    public ApiApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.ofNullable(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.ofNullable(request);
    }
   
    // CONTROLADOR JQUEZADA
    
    @RequestMapping(value = "/periodos",
            produces = { "application/json" }, 
            method = RequestMethod.GET)
	public ResponseEntity<Periodo> fechas() throws Exception {   	    
			log.debug("Controlador que obtiene fechas aleatoreas y totales con faltantes");
		try {
			Periodo per = periodosService.getPeriodos();
			return new ResponseEntity<Periodo>(per, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
