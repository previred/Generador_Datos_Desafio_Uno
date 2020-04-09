package com.luzplaz.example.desafio1.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	@Value("${rootpom}")
	String rootPom; 
	
	// http://localhost:9090/swagger-ui.html#/
	
	/**
	 * Publish a bean to generate swagger2 endpoints
	 * 
	 * @return a swagger configuration bean
	 * @throws XmlPullParserException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@Bean
	public Docket serviceInstanceApi() throws FileNotFoundException, IOException, XmlPullParserException {

		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model;
		if ((new File("pom.xml")).exists())
			model = reader.read(new FileReader("pom.xml"));
		else
			model = reader.read(new InputStreamReader(SwaggerConfiguration.class.getResourceAsStream(rootPom)));
		
		List<ResponseMessage> messages = new ArrayList<>();
		messages.add(new ResponseMessageBuilder().code(500).message(HttpStatus.INTERNAL_SERVER_ERROR.name())
			.responseModel(new ModelRef("Error")).build());
		messages.add(new ResponseMessageBuilder().code(404).message(HttpStatus.NOT_FOUND.name()).build());
		messages.add(new ResponseMessageBuilder().code(409).message(HttpStatus.CONFLICT.name() + " in rules business")
			.build());

		// model.getProperties().getProperty("docker.image.prefix")
		return new Docket(DocumentationType.SWAGGER_2).groupName(model.getArtifactId()).apiInfo(
			serviceInstanceApiInfo(model)).select()
			//.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
			//.apis(RequestHandlerSelectors.basePackage(model.getGroupId())) // filter to controllers
			//.paths(PathSelectors.any())
			//.paths(paths())
			.paths(PathSelectors.regex("/api/.*")) //filter to path
			.build()
			.pathMapping("/")
			// .genericModelSubstitutes(ResponseEntity.class)
			.useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.GET, messages) 
			;

	}

	/**
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	private ApiInfo serviceInstanceApiInfo(Model model) throws FileNotFoundException, IOException, XmlPullParserException {

		return new ApiInfoBuilder()
			.title("Periodo")
			.description("Microservice for Periodo")
			.version(model.getVersion())
			//.termsOfServiceUrl("https://source.luzplaz.com/")
			.license("Luz Plaz " + model.getArtifactId() + " licensing")
			// .licenseUrl("https://help.github.com/articles/open-source-licensing/")
			.contact(new Contact("Back-end Luz Plaz", "www.example.com", "luzplaz@gmail.com"))
			.build();
	}
	
	/**
	 * Documentation appearence 
	 * @return
	 */
	@Bean
	UiConfiguration uiConfig() {
	    return new UiConfiguration("validatorUrl", "list", "alpha", "schema",
	            UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
	}
	
	/**
	 * Method to Only select apis that matches the given Predicates.
	 * @return
	 */
	private Predicate<String> paths() {
		// Match all paths except /error
		return Predicates.and(
			//PathSelectors.regex("/.*"), 
			PathSelectors.ant("/api/*"),
			//PathSelectors.regex("/api/services/*"),
			Predicates.not(PathSelectors.regex("/error.*")));
	}
}
