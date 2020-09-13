package com.previred.ws.rest.dao;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;
import com.previred.ws.rest.vo.RespuestaValidacionFechasVO;

public class PeriodosDAOTest {
	
	/**
	 * metodo que consume un servicio Rest y retorna su respuesta
	 * @return
	 */
	@Test
	@Ignore
	public void consultaPeriodo (){
		RespuestaValidacionFechasVO respuesta = new RespuestaValidacionFechasVO();	
		try {
			//nos conectamos a la URL del servicio Rest via GET
            URL url = new URL("http://127.0.0.1:8080/periodos/api");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                Gson g = new Gson(); 
                //obtenemos el objeto Json y lo convertimos a POJO
                respuesta = g.fromJson(output, RespuestaValidacionFechasVO.class);
            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Ha ocurrido una Exception :- " + e);
        }		
		assertNotNull(respuesta.getFechas());		
	}

}
