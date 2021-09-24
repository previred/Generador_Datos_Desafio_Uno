package cl.aggl.main;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class DesafioUnoServletInitializer extends SpringBootServletInitializer{
	
	@Override
  	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
  		return application.sources(DesafioUnoApplication.class);
  	}

}
