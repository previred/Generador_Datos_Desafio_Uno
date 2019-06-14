package main.java.com.previred.periodos.actions;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class Context {
	
	public static String getJsonString() {
		
		String jsonMsg = new String();
		BufferedReader br;
		
		if (getConnection()!= null) {
			HttpURLConnection con = getConnection();
			InputStreamReader insr;
			try {
				insr = new InputStreamReader(con.getInputStream());
				br = new BufferedReader(insr);
				return jsonMsg = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsonMsg;
		
	}
	
	public static HttpURLConnection getConnection() {
		URL url;
		HttpURLConnection connection = null;
		try {
			url = new URL("http://127.0.0.1:8080/periodos/api");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			if (connection.getResponseCode() != 200)
				throw new RuntimeException("Failed : HTTP Error code : " + connection.getResponseCode());
			
			return connection;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
		
		return null;
	}
	
	public static List<String> getResultList(Map<String, List> mapIn) {
		
		List<String> listTmp = new ArrayList<>();
		Iterator<String> itResponse = mapIn.keySet().iterator();
		
		while(itResponse.hasNext()){
			String key = itResponse.next();
			List<String> values = mapIn.get(key);
			for (String month : values) {
				StringBuffer sb = new StringBuffer(key);
				sb.append("-").append(month).append("-1");
				listTmp.add(sb.toString());
			}
		}
		return listTmp;
	}
	
	public static Map<String, List> getResultMap(Map<String, List> map, List<String> monthsYear) {
		
		Map<String, List> mapResult = new TreeMap<String, List>();
		Iterator<String> ite2 = map.keySet().iterator();
		while(ite2.hasNext()){
			List<String> copyListMonths = new ArrayList<String>(monthsYear);
			String key = ite2.next();
			List<String> values = map.get(key);
			
			Iterator<String> itr = copyListMonths.iterator();
			while (itr.hasNext()) {
				String month = itr.next();
				for (String monthMap : values) {
					if (month.equals(monthMap)) {
						itr.remove();
					}
				}
			}
			
			mapResult.put(key, copyListMonths);
		}
		
		return mapResult;
	}
	
	public static void createJsonFile(String jsonFile, String fileDir) {
		try (
			FileWriter file = new FileWriter(fileDir)) {
			file.write(jsonFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
