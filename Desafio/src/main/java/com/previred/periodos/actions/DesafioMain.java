package main.java.com.previred.periodos.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DesafioMain extends Context {
	
	private static final String ID_JSON = "id";
	private static final String CREATION_DATE = "fechaCreacion";
	private static final String END_DATE = "fechaFin";
	private static final String DATES_RECEIVED = "fechas";
	private static final String MISSING_DATES = "fechas faltantes";
	
	public static void main(String[] args) {
		String filePath;
		if (args.length > 1) {
			System.out.println("Parametros no admitidos");
			return;
		}else if (args.length == 0) {
			System.out.println("Parametros insuficientes");
			return;
		}else {
			filePath = args[0];
		}
		
		String yearNew, monthNew;
		String yearTemp = "1900";
		
		try {
			
			String msgJson = getJsonString();
			if (msgJson == null || msgJson.isEmpty()) {
				return;
			}
			
			String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
			List<String> listMonths = Arrays.asList(months);
			
			JSONParser jsonParser = new JSONParser();
			Object object = jsonParser.parse(msgJson);
			JSONObject jsonObject = (JSONObject) object;
			
			long id = (Long) jsonObject.get(ID_JSON);
			
			String creationDate = (String) jsonObject.get(CREATION_DATE);
			String creationYear = creationDate.substring(0, 4);
			
			String endDate = (String) jsonObject.get(END_DATE);
			
			JSONArray datesReceived = (JSONArray) jsonObject.get(DATES_RECEIVED);			
			
			Map<String, List> map = new TreeMap<String, List>();
			Iterator<String> iterator2 = datesReceived.iterator();

			while (iterator2.hasNext()) {
				
				String f = iterator2.next();
				yearNew = f.substring(0, 4);
				monthNew = f.substring(5, 7);
				
				if (creationYear.equals(yearNew)) {
					fill(yearTemp, yearNew, monthNew, map);
				}else {
					if (yearTemp.equals(yearNew)) {
						fill(yearTemp, yearNew, monthNew, map);
					}else {
						fill(yearTemp, yearNew, monthNew, map);
					}
				}
			}//EndWhile
			
			Map<String, List> mapResponse = getResultMap(map, listMonths);
			
			JSONArray listMissingDates = new JSONArray();
			listMissingDates.addAll(getResultList(mapResponse));
			
			createJsonFile(getJson(id, creationDate, endDate, datesReceived, listMissingDates), filePath);
			
			//connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Test:- " + e);
		}
		
		System.out.println("done!");
	}
	
	public static String getJson(long id, String creationDay, String endDay, JSONArray daysReceived, JSONArray daysMissing) {
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put(ID_JSON, id);
		jsonResponse.put(CREATION_DATE, creationDay);
		jsonResponse.put(END_DATE, endDay);
		jsonResponse.put(DATES_RECEIVED, daysReceived);
		jsonResponse.put(MISSING_DATES, daysMissing);
		return jsonResponse.toJSONString();
	}
	
	public static void fill(String yearTmp, String year, String month, Map<String, List> mp) {
		yearTmp = year;
		if (mp.get(yearTmp) == null) {
			List<String> listValues = new ArrayList<>();
			listValues.add(month);
			mp.put(yearTmp, listValues);
		}else {
			List<String> listVal = mp.get(yearTmp);
			listVal.add(month);
			mp.put(yearTmp, listVal);
		}
	}
	

}
