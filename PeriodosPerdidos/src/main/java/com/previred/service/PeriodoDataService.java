package com.previred.service;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.previred.domain.Periodo;
import com.previred.setting.EndPointSettings;

@Service
public class PeriodoDataService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	EndPointSettings endPointSettings;

	private HttpHeaders headers = new HttpHeaders();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PeriodoDataService.class);
	
	/**
	 * Get Periodos from api
	 * 
	 * @return Periodo
	 */
	public Periodo getPeriodos() {
		Periodo rs = null;
		ResponseEntity<Periodo> response = null;
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		try {
			response = restTemplate.exchange(endPointSettings.getUrlGetPeriodo(), HttpMethod.GET, entity, Periodo.class);
			rs = response.getBody();
		} catch (HttpStatusCodeException e) {
			LOGGER.error(e.getMessage());
		}
		return rs;
	}
	
}
