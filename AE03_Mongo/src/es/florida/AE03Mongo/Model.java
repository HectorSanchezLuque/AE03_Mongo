package es.florida.AE03Mongo;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;



public class Model {
	
	String ip;
	String port;
	String db;
	String llibres;
	String users ;
	
	
	public Model() {
		JSONParser jsonParser = new JSONParser();
		try {
			FileReader reader = new FileReader("./src/connect.json");
			
			 Object obj = jsonParser.parse(reader);
			 
			 JSONObject jo = (JSONObject) obj;
			 
			 this.ip = (String) jo.get("ip");
			 this.port= (String) jo.get("port");
			 this.db = (String) jo.get("db");
			 this.llibres= (String) jo.get("llibres");
			this.users=(String) jo.get("users");
			
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
