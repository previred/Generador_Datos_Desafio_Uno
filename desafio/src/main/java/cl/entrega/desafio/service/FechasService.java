package cl.entrega.desafio.service;

import cl.entrega.desafio.domain.Fechas;
import cl.entrega.desafio.exception.NegocioException;
import java.io.IOException;
import java.text.ParseException;

public interface FechasService {

    Fechas findFechas() throws NegocioException, IOException, ParseException;

}
