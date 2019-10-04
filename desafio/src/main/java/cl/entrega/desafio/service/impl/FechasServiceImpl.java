package cl.entrega.desafio.service.impl;

import cl.entrega.desafio.configuration.ErrorsConfiguration;
import cl.entrega.desafio.domain.Fechas;
import cl.entrega.desafio.exception.NegocioException;
import cl.entrega.desafio.service.FechasService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FechasServiceImpl implements FechasService {

    @Autowired
    ErrorsConfiguration errorsConfiguration;

    String line;

    @Override
    public Fechas findFechas() throws NegocioException, IOException, ParseException {
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "curl -X GET --header \"Accept: application/json\" \"http://127.0.0.1:8080/periodos/api\"");
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            line = r.readLine();

        } catch (Exception e) {
            log.error("Error en findFechas: " + e, e);
            throw new NegocioException(errorsConfiguration.getCodigosError("EX001"));
        }

        JSONObject json = new JSONObject(line);
        int id = (int) json.get("id");
        String fechaFin = (String) json.get("fechaFin");
        String fechaCreacion = (String) json.get("fechaCreacion");
        JSONArray fechas = json.getJSONArray("fechas");

        return getListaEntreFechas(id,fechaCreacion,fechaFin,fechas);
    }

    public Fechas getListaEntreFechas(int id, String fechaCreacion, String fechaFin, JSONArray fechas) throws ParseException {
        LocalDate start = LocalDate.parse(fechaCreacion);
        LocalDate end = LocalDate.parse(fechaFin);
        List<LocalDate> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start);
            start = start.plusMonths(1);
        }

        List<String> tempDate = new ArrayList(totalDates.size());
        for (LocalDate date : totalDates) {
            tempDate.add(date.toString()); //toString() or the appropriate method
        }

        List<String> tempDate2 = new ArrayList();
        for (int i=0;i<fechas.length();i++){
            tempDate2.add(fechas.getString(i));
        }

        List<String>  FechaFaltante = getSameVaue(tempDate, tempDate2);
        Fechas salida = new Fechas();
        salida.setId(id);
        salida.setFechaCreacion(fechaCreacion);
        salida.setFechaFin(fechaFin);
        salida.setFechas(tempDate2);
        salida.setFechaFaltante(FechaFaltante);
        return salida;
    }

    public static List<String> getSameVaue(List<String> tempDate,
                                           List<String> tempDate2) {
        if (tempDate.size() > tempDate2.size()) {
            tempDate.removeAll(tempDate2);
            System.out.println(tempDate.toString());
        }
        return tempDate;
    }
}