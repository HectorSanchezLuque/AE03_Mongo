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
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;



public class Model {
	
	static 	MongoClient mongoClient;
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
		MongoCollection<Document> coleccion = mongoObrir();
		MongoCursor<Document> cursor = coleccion.find().iterator();
		while (cursor.hasNext()) {
			return true;
		}
		
		
		return false;
	}
	
	public MongoCollection<Document> mongoObrir() {
		
		mongoClient = new MongoClient(this.ip, Integer.parseInt(this.port));
		MongoDatabase database = mongoClient.getDatabase(this.db);
		MongoCollection<Document> coleccion = database.getCollection(this.users);
		
		return coleccion;
		
	}
	
	public void mongoTancar() {
		mongoClient.close();
		
	}
	
	
}
