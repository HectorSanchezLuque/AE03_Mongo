package es.florida.AE03Mongo;

import java.awt.Image;
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
		System.out.println(contr);

		Bson query = and(Arrays.asList(eq("usuari",usuari ), eq("contrasenya", contr)));
		
		MongoCursor<Document> cursor = coleccion.find(query).iterator();
		
		if (cursor.hasNext()) {
			mongoClient.close();
			System.out.println("Usuario Correcto");
			return true;
		}
		
		
		
		

		
		mongoClient.close();
		System.out.println("Usuario Incorrecto");
		
		//aí
		return true;
	}
	
	public  String MongoMostrarTodo() {
		

		MongoClient mongoClient = new MongoClient(this.ip, Integer.parseInt(this.port));
		MongoDatabase database = mongoClient.getDatabase(this.db);
		MongoCollection<Document> coleccion = database.getCollection(this.llibres);
		
		String contingut="";
		
		MongoCursor<Document> cursor = coleccion.find().iterator();
		
		while (cursor.hasNext()) {
			contingut += cursor.next().toJson()+"\n";
		
		}

		mongoClient.close();
		
		return contingut;
	}

	public void MongoInsert() {
		MongoClient mongoClient = new MongoClient(this.ip, Integer.parseInt(this.port));
		MongoDatabase database = mongoClient.getDatabase(this.db);
		MongoCollection<Document> coleccion = database.getCollection(this.llibres);
		
		JTextField id = new JTextField();
		JTextField titol = new JTextField();
		JTextField autor = new JTextField();
		JTextField anyo_Naixement = new JTextField();
		JTextField anyo_Publicacio = new JTextField();
		JTextField editorial = new JTextField();
		JTextField pagines = new JTextField();
		JTextField imatge = new JTextField();
			
			id.setText("");
			titol.setText("");
			autor.setText("");
			anyo_Naixement.setText("");
			anyo_Publicacio.setText("");
			editorial.setText("");
			pagines.setText("");
			imatge.setText("");
			
			Object[] message = { "Titol:", titol, "Autor:", autor, "Any de naixement:", anyo_Naixement, "Any de publicació:", anyo_Publicacio, "Editoria:", editorial, "Pagines:", pagines, "Imatge:", imatge  };
			int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
			
			
		// (int ident, String tit, String autr, int aN, int aP, String ed, int p, Image im)
		Llibre l = new Llibre(Integer.parseInt(id.getText()), titol.getText(), autor.getText(), Integer.parseInt(anyo_Naixement.getText()), Integer.parseInt(anyo_Publicacio.getText()), editorial.getText(), Integer.parseInt(pagines.getText()));
		Document doc = new Document();

		doc.append("Id", l.getId());
		doc.append("Titulo", l.getTitol());
		doc.append("Autor", l.getAutor());
		doc.append("Anyo_nacimiento", l.getAnyo_Naixement());
		doc.append("Anyo_publicacion", l.getAnyo_Publicacio());
		doc.append("Editorial", l.getEditorial());
		doc.append("Numero_paginas", l.getPagines());
		doc.append("Thumbnail", l.getImatge());
		
		coleccion.insertOne(doc);
		
	}
}
