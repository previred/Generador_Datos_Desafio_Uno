package cl.entrega.desafio.controller;

import cl.entrega.desafio.domain.Fechas;
import cl.entrega.desafio.service.FechasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

@RestController
public class FechasController {

    @Autowired
    private FechasService fechasService;

    @GetMapping("/fechas")
    @ResponseBody
    public Fechas findFechas() throws IOException, ParseException {

        return fechasService.findFechas();
    }
}