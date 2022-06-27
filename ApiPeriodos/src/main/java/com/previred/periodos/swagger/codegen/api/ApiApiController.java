package com.previred.periodos.swagger.codegen.api;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.periodos.servicio.PeriodosFaltantesService;
import com.previred.periodos.servicio.PeriodosService;
import com.previred.periodos.swagger.codegen.model.Periodo;
import com.previred.periodos.swagger.codegen.model.PeriodoResponse;
import com.previred.periodos.tools.FileProperties;
import com.previred.periodos.tools.GenerarArchivo;

@Controller
public class ApiApiController implements ApiApi {

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private PeriodosService periodosService;
    
    @Autowired
    private PeriodosFaltantesService periodosFaltantesService;
    
    @Autowired 
	private  FileProperties fileProperties;
	
	@Autowired
	private GenerarArchivo generarArchivoSalida;
	
	@Autowired (required=true)
	private ApiException apiException;
    

    //@org.springframework.beans.factory.annotation.Autowired //, RestTemplate restTemplate
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
    
    
  
    @Override
    public ResponseEntity<Periodo> periodos() {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains(fileProperties.getAccepterHeader())) {
                try {
                    Periodo detalle = periodosService.getPeriodos();
                    ResponseEntity<Periodo> respuesta = new ResponseEntity<>(detalle, HttpStatus.OK);
                    return respuesta;
                } catch (Exception e) {
                    log.error("Couldn't serialize response for content type application/xml", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default DefaultApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    
    @Override
    public ResponseEntity<PeriodoResponse> periodosFaltantes(){
    	
    	if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
    		if (getAcceptHeader().get().contains(fileProperties.getAccepterHeader())) {
    				// Se consume el servicio  GDD
    				ResponseEntity<Periodo> periodo = new RestTemplate().getForEntity(fileProperties.getUrlServicio(), Periodo.class);
    				//periodo.getBody().setFechas(null); //TODO: Se empleo para simular un error al recorrer el arreglo
        			PeriodoResponse periodoResponse = periodosFaltantesService.generaPeriodosFaltantes(periodo.getBody());
    				ResponseEntity<PeriodoResponse> respuesta = new  ResponseEntity<PeriodoResponse>(periodoResponse,  HttpStatus.OK);
    				
    				//Generar Archivo
    				System.out.println("respuesta : "+respuesta);
    				generarArchivoSalida.generarArchivoSalida(respuesta.toString());

    				return respuesta;		
    		}else {
    			log.warn("Conten-Type no encontrado");
    		}
    	}else {
    		log.warn("ObjectMapper or HttpServletRequest con problemas de configuracion");
    		
    	}
    	
    	System.out.println("LLega aqui SSSSSSS"); 
    	
    	return new ResponseEntity <> (HttpStatus.NOT_IMPLEMENTED);
    }
    
    
    /**
   	 * Permite Capturar la Exception RuntimeException y retorna  un 
   	 * ResponseEntity con informacion del error.
   	 */
    @ExceptionHandler(RuntimeException.class)
    @Override
    public ResponseEntity<ApiResponseMessage> metodoRuntimeException(RuntimeException e, HttpServletRequest request) {
    	return apiException.runtimeException(e,  request);
   	}
    
    /**
     * Permite Capturar Exception y retorna un 
     * ResponseEntyty con informacion del error
     */
    @ExceptionHandler(Exception.class)
    @Override
    public ResponseEntity<ApiResponseMessage> metodoException (Exception e, HttpServletRequest request  ) {
    	return apiException.exception(e, request );
    	
   	}
    
    
    /**
     * Permite Capturar Throwable y retorna un 
     * ResponseEntyty con informacion del error
     */
    @ExceptionHandler(Throwable.class)
    @Override
    public ResponseEntity<ApiResponseMessage> metodoThrowable (Throwable e, HttpServletRequest request  ) {
    	return apiException.throwable(e, request);
    	
   	}
    
   
    
}
