package cl.entrega.desafio.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "errores")
@Setter
@Getter
public class ErrorsConfiguration {

    private Map<String, String> codigosError = new HashMap<>();

    /**
     * Obtiene errores.
     * @param codigo La constante repectiva
     * @return El valor de la condicion
     */
    public String getCodigosError(String codigo) {
        return codigosError.get(codigo);
    }
}