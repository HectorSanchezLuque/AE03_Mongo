package es.florida.AE03Mongo;

import java.io.FileNotFoundException;


import java.io.FileReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;

public class Model {

	String ip;
	String port;
	String db;
	String llibres;
	String users;

	public Model() {
		JSONParser jsonParser = new JSONParser();
		try {
			FileReader reader = new FileReader("./src/connect.json");

			Object obj = jsonParser.parse(reader);

			JSONObject jo = (JSONObject) obj;

			this.ip = (String) jo.get("ip");
			this.port = (String) jo.get("port");
			this.db = (String) jo.get("db");
			this.llibres = (String) jo.get("llibres");
			this.users = (String) jo.get("users");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String MongoConversor(String contr) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		BigInteger number = new BigInteger(1, md.digest(contr.getBytes(StandardCharsets.UTF_8)));
		StringBuilder hexString = new StringBuilder(number.toString(16));

		while (hexString.length() < 64) {
			hexString.insert(0, '0');
		}

		return hexString.toString();

	}


	public Boolean MongoCompUser(String usuari, String contr) {

		MongoClient mongoClient = new MongoClient(this.ip, Integer.parseInt(this.port));
		MongoDatabase database = mongoClient.getDatabase(this.db);
		MongoCollection<Document> coleccion = database.getCollection(this.users);
		// CRUD operations
		
		Bson query = and(Arrays.asList(eq("usuari",usuari ), eq("contrasenya", contr)));
		
		MongoCursor<Document> cursor = coleccion.find(query).iterator();
		
		if (cursor.hasNext()) {
			mongoClient.close();
			System.out.println("Usuario Correcto");
			return true;
		}
		
		

		
		mongoClient.close();
		System.out.println("Usuario Incorrecto");
		return false;
	}

	public void MongoInsert() {
		MongoClient mongoClient = new MongoClient(this.ip, Integer.parseInt(this.port));
		MongoDatabase database = mongoClient.getDatabase(this.db);
		MongoCollection<Document> coleccion = database.getCollection(this.llibres);
		
		JTextField titol = new JTextField();
		JTextField pagines = new JTextField();
		JTextField editorial = new JTextField();
			
			titol.setText("");
			pagines.setText("");
			editorial.setText("");
			
			Object[] message = { "Titol:", titol, "Pagines:", pagines, "Editorial:", editorial };
			int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
			
		
		Llibre l = new Llibre(titol.getText(), Integer.parseInt(pagines.getText()), editorial.getText());
		Document doc = new Document();

		doc.append("titol", l.getTitol());
		doc.append("pagines", l.getPagines());
		doc.append("editorial", l.getEditorial());
		doc.append("imatge", l.getImatge());
		
		coleccion.insertOne(doc);
		
	}
}
