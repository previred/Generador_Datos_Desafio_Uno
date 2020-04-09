package com.luzplaz.example.desafio1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiConfig {

	@Value("${serviceUrl}")
	public String serviceUrl; 
	
	public String serviceUrl() {
		return this.serviceUrl;
	}
	
	@Bean
   // @LoadBalanced
    RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
    	//RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
    	//restTemplate.setInterceptors(Collections.singletonList(new XUserAgentAuthorizationInterceptor()));
    	return restTemplate;

	}
    
	private ClientHttpRequestFactory clientHttpRequestFactory() {
	       HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
	       //factory.setReadTimeout(readTimeout);
	       //factory.setConnectTimeout(connecttimeout);
	       return factory;
	   }
}
