package es.florida.AE03Mongo;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
public class Model {
	
	
	String ip;
	String port;
	String db;
	String coleccio;
	
	
	public Model() {
		JSONParser jsonParser = new JSONParser();
		try {
			FileReader reader = new FileReader("./connect.json");
			
			 Object obj = jsonParser.parse(reader);
			 
			 JSONObject jo = (JSONObject) obj;
			 
			 this.ip = (String) jo.get("id");
			 this.port= (String) jo.get("port");
			 this.db = (String) jo.get("db");
			 this.coleccio= (String) jo.get("coleccio");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String MongoConversor(String contr) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        BigInteger number = new BigInteger(1, md.digest(contr.getBytes(StandardCharsets.UTF_8)));
        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
 
        return hexString.toString();
		
	}

	public Boolean MongoCompUser(String usuari, String contr) {
		
		return true;
	}
}
