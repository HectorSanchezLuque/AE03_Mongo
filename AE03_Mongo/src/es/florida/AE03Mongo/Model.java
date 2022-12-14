package es.florida.AE03Mongo;

import java.io.FileNotFoundException;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class Model {
	
	
	String ip;
	String port;
	String db;
	String coleccio;
	
	
	public Model() {
		JSONParser jsonParser = new JSONParser();
		try {
			FileReader reader = new FileReader("./src/connect.json");
			
			 Object obj = jsonParser.parse(reader);
			 
			 JSONObject jo = (JSONObject) obj;
			 
			 this.ip = (String) jo.get("ip");
			 this.port= (String) jo.get("port");
			 this.db = (String) jo.get("db");
			 this.coleccio= (String) jo.get("coleccio");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
