package cl.aggl.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = { "cl.aggl.main", 
	    "cl.aggl.controller",
	    "cl.aggl.servicio"})
public class DesafioUnoApplication{
	
	@Bean
    public RestTemplate getresttemplate() {
        return new RestTemplate();
    }

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DesafioUnoApplication.class, args);		
	}
}
