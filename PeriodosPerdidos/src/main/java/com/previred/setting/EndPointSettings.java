package com.previred.setting;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class EndPointSettings {

    @Value("${endpoint.periodos.url}")
    private String urlGetPeriodo;

}
