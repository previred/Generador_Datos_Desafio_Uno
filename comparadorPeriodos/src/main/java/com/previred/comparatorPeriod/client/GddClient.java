package com.previred.comparatorPeriod.client;

import com.previred.comparatorPeriod.Period;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author: Jorge Godoy (JG)
 */
@Component
public class GddClient {

    @Value("${gdd.uri}")
    private String uri;

    public Period getPeriod() {
        return new RestTemplate().getForObject(uri, Period.class);
    }
}
