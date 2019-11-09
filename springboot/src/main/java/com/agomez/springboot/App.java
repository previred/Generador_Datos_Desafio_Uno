package com.agomez.springboot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agomez.springboot.vo.PeriodosVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootApplication
@EnableAutoConfiguration
public class App {
	public static void main(String[] args) throws IOException {
		// Se dispone el servicio con Spring Boot para consumo via python, consumo POST
		// o utilizando alguna herramienta de pruebas unitarias (Ejemplo: SoapUI)
		SpringApplication.run(App.class, args);

		// Se realiza la petición al Api del Desafio
		String url = "http://127.0.0.1:8080/periodos/api";
		try {
			peticionHttpGet(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Metodo que realiza la peticion Http via GET al Api del Desafio */
	public static void peticionHttpGet(String urlParaVisitar) throws Exception {
		StringBuilder resultado = new StringBuilder();
		URL url = new URL(urlParaVisitar);

		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		conexion.setRequestProperty("content-type", "application/json");
		conexion.setRequestProperty("Accept", "application/json");

		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		String linea;

		while ((linea = rd.readLine()) != null) {
			resultado.append(linea);
		}

		JSONObject objetoJson = new JSONObject(resultado.toString());

		// Se envia el objeto JSON obtenido, al metodo que sealiza el parse de los
		// objetos y el calculo de las fechas faltantes
		generarArregloDeFechas(objetoJson);

	}

	/*
	 * Metodo que recibe objeo JSON, lo transforma y realiza la construccion de el
	 * listado de las fechas faltantes, para enviar como respuesta
	 */
	public static void generarArregloDeFechas(JSONObject objetoJson) throws IOException {
		long id = Long.valueOf(objetoJson.getInt("id"));
		LocalDate fechaCreacion = LocalDate.parse(objetoJson.getString("fechaCreacion"));
		LocalDate fechaFin = LocalDate.parse(objetoJson.getString("fechaFin"));
		JSONArray fechas = objetoJson.getJSONArray("fechas");

		List<LocalDate> fechasEnviadas = new ArrayList<LocalDate>();

		for (int i = 0; i < fechas.length(); i++) {
			fechasEnviadas.add(LocalDate.parse(fechas.getString(i)));
		}

		// Se obtiene lista de fechas comprendidas entre fecha de creacion y fecha fin
		List<LocalDate> periodosCompletos = Stream.iterate(fechaCreacion, date -> date.plusMonths(1))
				.limit(ChronoUnit.MONTHS.between(fechaCreacion, fechaFin.plusMonths(1))).collect(Collectors.toList());

		// Se obtiene lista de fechas faltantes
		List<LocalDate> fechasFaltantes = periodosCompletos.stream().filter(x -> !fechasEnviadas.contains(x))
				.collect(Collectors.toList());

		/*
		 * Datos para validar los valores para construir el JSON
		 * System.out.println("Id: " + id); System.out.println("Fecha de creación: " +
		 * fechaCreacion); System.out.println("Fecha fin: " + fechaFin);
		 * System.out.println("Fechas Enviadas: " + fechasEnviadas);
		 * System.out.println("Fechas Completas: " + periodosCompletos);
		 * System.out.println("Fechas Faltantes: " + fechasFaltantes);
		 */

		// Se construye el objeto para enviar el JSON
		PeriodosVO periodo = new PeriodosVO();
		periodo.setId(id);
		periodo.setFechaCreacion(fechaCreacion);
		periodo.setFechaFin(fechaFin);
		periodo.setFechas(fechasEnviadas);
		periodo.setFechasFaltantes(fechasFaltantes);

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		String json = mapper.writerWithDefaultPrettyPrinter().withView(PeriodosVO.class).writeValueAsString(periodo);
		System.out.println("***************************************************************************************");
		System.out.println("*******************		    SOLUCION AL DESAFIO 		  *******************");
		System.out.println("***************************************************************************************");
		System.out.println(json);

	}

}