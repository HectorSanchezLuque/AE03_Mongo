package es.florida.AE03Mongo;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.codec.binary.Base64;
import org.bson.Document;
import org.bson.conversions.Bson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;

import java.awt.image.BufferedImage;

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

	/**
	 * Funció que rep un String i el converteix a SHA-256
	 * @param contr String que conté la contrassenya
	 * @return Retorna un String en la contrassenya convertuda a SHA-256
	 * @throws NoSuchAlgorithmException
	 */
	public String MongoConversor(String contr) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		BigInteger number = new BigInteger(1, md.digest(contr.getBytes(StandardCharsets.UTF_8)));
		StringBuilder hexString = new StringBuilder(number.toString(16));

		while (hexString.length() < 64) {
			hexString.insert(0, '0');
		}

		return hexString.toString();

	}

	/**
	 * Funció que comprova si l'usuari i la contrassenya introduïdes existeixen en la base de dades
	 * @param usuari String que conté el nom d'usuari
	 * @param contr String que conté la contrassenya
	 * @return Retorna True o False depenent de si està bé o no l'inici de sessió
	 */
	public Boolean MongoCompUser(String usuari, String contr) {

		MongoClient mongoClient = new MongoClient(this.ip, Integer.parseInt(this.port));
		MongoDatabase database = mongoClient.getDatabase(this.db);
		MongoCollection<Document> coleccion = database.getCollection(this.users);
		// CRUD operations
		System.out.println(contr);

		Bson query = and(Arrays.asList(eq("user", usuari), eq("pass", contr)));

		MongoCursor<Document> cursor = coleccion.find(query).iterator();

		if (cursor.hasNext()) {
			mongoClient.close();
			System.out.println("Usuario Correcto");
			return true;
		}

		mongoClient.close();
		System.out.println("Usuario Incorrecto");

		// Ponerlo en false para que autentifique
		// correctamente----------------------------------------
		return false;
	}
	
	/**
	 * Funció que fa una consulta a la base de dades i inicialitza tot el contingut d'aquesta en una variable
	 * @return Retorna un variable en tot el contingut que trobe en la base de dades
	 */
	public String MongoMostrarTot() {
		int cont = 0;
		MongoClient mongoClient = new MongoClient(this.ip, Integer.parseInt(this.port));
		MongoDatabase database = mongoClient.getDatabase(this.db);
		MongoCollection<Document> coleccion = database.getCollection(this.llibres);

		String mostr = "Id | Titol | Autor | Any de naiximent | Any de publicació | Editorial | Numero de pàgines \n";

		MongoCursor<Document> cursor = coleccion.find().iterator();

		while (cursor.hasNext()) {
			cont++;
			JSONParser parser = new JSONParser();
			JSONObject json;
			try {
				json = (JSONObject) parser.parse(cursor.next().toJson());

				if (json.containsKey("Id")) {

					mostr += json.get("Id") + " | ";

				}

				if (json.containsKey("Titulo")) {

					mostr += json.get("Titulo") + " | ";
				}

				if (json.containsKey("Autor")) {

					mostr += json.get("Autor") + " | ";
				}

				if (json.containsKey("Anyo_nacimiento")) {

					mostr += json.get("Anyo_nacimiento") + " | ";

				}

				if (json.containsKey("Anyo_publicacion")) {

					mostr += json.get("Anyo_publicacion") + " | ";
				}

				if (json.containsKey("Editorial")) {

					mostr += json.get("Editorial") + " | ";

				}

				if (json.containsKey("Numero_paginas")) {

					mostr += json.get("Numero_paginas") + " | ";

				}

				mostr += "\n\n";

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		mongoClient.close();
		mostr += "Documents trobats: " + cont;
		return mostr;

	}

	/**
	 * Funció que fa un insert a la base de dades en les dades que s'introdueixen en un JOptionPane
	 * @throws IOException
	 */
	public void MongoInsert() throws IOException {
		MongoClient mongoClient = new MongoClient(this.ip, Integer.parseInt(this.port));
		MongoDatabase database = mongoClient.getDatabase(this.db);
		MongoCollection<Document> coleccion = database.getCollection(this.llibres);

		// Cualquier Jtex deve estar en vista o controlador nunca en modelo,
		// el modelo es bakend no debe hacer nada de interface
		JTextField id = new JTextField();
		JTextField titol = new JTextField();
		JTextField autor = new JTextField();
		JTextField anyo_Naixement = new JTextField();
		JTextField anyo_Publicacio = new JTextField();
		JTextField editorial = new JTextField();
		JTextField pagines = new JTextField();
		JTextField imatge = new JTextField();
		JFileChooser arch = new JFileChooser();

		id.setText("");
		titol.setText("");
		autor.setText("");
		anyo_Naixement.setText("");
		anyo_Publicacio.setText("");
		editorial.setText("");
		pagines.setText("");
		imatge.setText("");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imatges", "jpg", "png");
		arch.setFileFilter(filter);

		Object[] message = { "ID", id, "Titol:", titol, "Autor:", autor, "Any de naixement:", anyo_Naixement,
				"Any de publicació:", anyo_Publicacio, "Editorial:", editorial, "Pagines:", pagines, "Imatge:", arch };
		int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);

		if (option != JOptionPane.CANCEL_OPTION) {
			// (int ident, String tit, String autr, int aN, int aP, String ed, int p, Image
			// im)
			Llibre l = new Llibre(Integer.parseInt(id.getText()), titol.getText(), autor.getText(),
					Integer.parseInt(anyo_Naixement.getText()), Integer.parseInt(anyo_Publicacio.getText()),
					editorial.getText(), Integer.parseInt(pagines.getText()));

			File f = new File(arch.getSelectedFile().toString());
			byte[] fileContent = Files.readAllBytes(f.toPath());
			String encodedString = Base64.encodeBase64String(fileContent);

			Document doc = new Document();

			doc.append("Id", l.getId());
			doc.append("Titulo", l.getTitol());
			doc.append("Autor", l.getAutor());
			doc.append("Anyo_nacimiento", l.getAnyo_Naixement());
			doc.append("Anyo_publicacion", l.getAnyo_Publicacio());
			doc.append("Editorial", l.getEditorial());
			doc.append("Numero_paginas", l.getPagines());
			doc.append("Thumbnail", encodedString);

			coleccion.insertOne(doc);
			mongoClient.close();
		} else {
			System.err.println("Operació cancelada");
			mongoClient.close();
		}

	}
	
	/**
	 * Funció que busca un document en la base de dades
	 * @param id Parametre pel qual busca el document
	 * @return Retorna un objecte Llibre inicialitzat en les dades que ha trobat a la base de dades
	 */
	public Llibre mongoRetornDoc(int id) {

		String titol = "";
		String autor = "";
		int anyo_Naixement = 0;
		int anyo_Publicacio = 0;
		String editorial = "";
		int pagines = 0;
		Image imatge = null;

		MongoClient mongoClient = new MongoClient(this.ip, Integer.parseInt(this.port));
		MongoDatabase database = mongoClient.getDatabase(this.db);
		MongoCollection<Document> coleccion = database.getCollection(this.llibres);

		Bson query = eq("Id", id);

		MongoCursor<Document> cursor = coleccion.find(query).iterator();

		while (cursor.hasNext()) {

			JSONParser parser = new JSONParser();
			JSONObject json;

			try {
				json = (JSONObject) parser.parse(cursor.next().toJson());

				if (json.containsKey("Titulo")) {

					titol = (String) json.get("Titulo");
				}

				if (json.containsKey("Autor")) {

					autor = (String) json.get("Autor");
				}

				if (json.containsKey("Anyo_nacimiento")) {

					anyo_Naixement = Integer.parseInt(json.get("Anyo_nacimiento").toString());

				}

				if (json.containsKey("Anyo_publicacion")) {

					anyo_Publicacio = Integer.parseInt(json.get("Anyo_publicacion").toString());
				}

				if (json.containsKey("Editorial")) {

					editorial = (String) json.get("Editorial");

				}

				if (json.containsKey("Numero_paginas")) {

					pagines = Integer.parseInt(json.get("Numero_paginas").toString());

				}

				if (json.containsKey("Thumbnail")) {

					byte[] btDataFile = Base64.decodeBase64(json.get("Thumbnail").toString());

					try {
						imatge = ImageIO.read(new ByteArrayInputStream(btDataFile));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		mongoClient.close();
		Llibre lib = new Llibre(id, titol, autor, anyo_Naixement, anyo_Publicacio, editorial, pagines, imatge);
		return lib;
	}
	
	/**
	 * Funció que realitza una consulta la base de dades
	 * @param camp Etiqueta que serveix per a fer la busqueda
	 * @param tipus Tipus de busqueda
	 * @param valor Valor de la busqueda
	 * @return Retorna un String en el resultat de la consulta
	 */
	public String MongoConsult(String camp, String tipus, String valor) {

		String mostr = "Id | Titol | Autor | Any de naiximent | Any de publicació | Editorial | Numero de pàgines \n";

		MongoClient mongoClient = new MongoClient(this.ip, Integer.parseInt(this.port));
		MongoDatabase database = mongoClient.getDatabase(this.db);
		MongoCollection<Document> coleccion = database.getCollection(this.llibres);
		Bson query = null;
		if (tipus.equals("igual")) {

			try {
				query = eq(camp, Integer.parseInt(valor));
			} catch (Exception exc) {
				
				query = eq(camp, valor);
			}

		} else if (tipus.equals("major o igual")) {

		
			
			try {
				query = gte(camp, Integer.parseInt(valor));
			} catch (Exception exc) {
				
				query = gte(camp, valor);
			}
			
		} else if (tipus.equals("menor o igual")) {
			
			try {
				query = lte(camp, Integer.parseInt(valor));
			} catch (Exception exc) {
				
				query = lte(camp, valor);
			}
		}

		MongoCursor<Document> cursor = coleccion.find(query).iterator();
		while (cursor.hasNext()) {

			JSONParser parser = new JSONParser();
			JSONObject json;
			try {
				json = (JSONObject) parser.parse(cursor.next().toJson());

				if (json.containsKey("Id")) {

					mostr += json.get("Id") + " | ";

				}

				if (json.containsKey("Titulo")) {

					mostr += json.get("Titulo") + " | ";
				}

				if (json.containsKey("Autor")) {

					mostr += json.get("Autor") + " | ";
				}

				if (json.containsKey("Anyo_nacimiento")) {

					mostr += json.get("Anyo_nacimiento") + " | ";

				}

				if (json.containsKey("Anyo_publicacion")) {

					mostr += json.get("Anyo_publicacion") + " | ";
				}

				if (json.containsKey("Editorial")) {

					mostr += json.get("Editorial") + " | ";

				}

				if (json.containsKey("Numero_paginas")) {

					mostr += json.get("Numero_paginas") + " | ";

				}

				mostr += "\n\n";

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		mongoClient.close();
		return mostr;

	}

	/**
	 * Funció que busca un document mitjaçant un JOptionPane on introdueix una ID i inicialitza un altre JOptionPane en le dades que troba i fa un update a la base de dades per a actualitzar l'objecte en les noves dades
	 * @throws IOException
	 */
	public void MongoActualitzar() throws IOException {

		JTextField id = new JTextField();
		id.setText("");

		MongoClient mongoClient = new MongoClient(this.ip, Integer.parseInt(this.port));
		MongoDatabase database = mongoClient.getDatabase(this.db);
		MongoCollection<Document> coleccion = database.getCollection(this.llibres);

		Object[] message = { "ID", id };
		int option = JOptionPane.showConfirmDialog(null, message, "Busqueda per ID", JOptionPane.OK_CANCEL_OPTION);

		Bson query = eq("Id", Integer.parseInt(id.getText()));
		MongoCursor<Document> cursor = coleccion.find(query).iterator();

		JTextField id2 = new JTextField();
		JTextField titol = new JTextField();
		JTextField autor = new JTextField();
		JTextField anyo_Naixement = new JTextField();
		JTextField anyo_Publicacio = new JTextField();
		JTextField editorial = new JTextField();
		JTextField pagines = new JTextField();
		JFileChooser arch = new JFileChooser();

		id2.setText("");
		titol.setText("");
		autor.setText("");
		anyo_Naixement.setText("");
		anyo_Publicacio.setText("");
		editorial.setText("");
		pagines.setText("");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imatges", "jpg", "png");
		arch.setFileFilter(filter);

		while (cursor.hasNext()) {

			JSONParser parser = new JSONParser();
			JSONObject json;
			try {
				json = (JSONObject) parser.parse(cursor.next().toJson());

				if (json.containsKey("Id")) {

					id2.setText(json.get("Id").toString());

				}

				if (json.containsKey("Titulo")) {

					titol.setText(json.get("Titulo").toString());
				}

				if (json.containsKey("Autor")) {

					autor.setText(json.get("Autor").toString());
				}

				if (json.containsKey("Anyo_nacimiento")) {

					anyo_Naixement.setText(json.get("Anyo_nacimiento").toString());
				}

				if (json.containsKey("Anyo_publicacion")) {

					anyo_Publicacio.setText(json.get("Anyo_publicacion").toString());
				}

				if (json.containsKey("Editorial")) {

					editorial.setText(json.get("Editorial").toString());
				}

				if (json.containsKey("Numero_paginas")) {

					pagines.setText(json.get("Numero_paginas").toString());
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Object[] message2 = { "ID", id, "Titol:", titol, "Autor:", autor, "Any de naixement:", anyo_Naixement,
				"Any de publicació:", anyo_Publicacio, "Editorial:", editorial, "Pagines:", pagines, "Imatge:", arch };
		int option2 = JOptionPane.showConfirmDialog(null, message2, "Login", JOptionPane.OK_CANCEL_OPTION);

		if (option2 == JOptionPane.OK_OPTION) {
			int option3 = JOptionPane.showConfirmDialog(null, "Està segur de que vol actualitzar aquest document?",
					"Advertencia", JOptionPane.OK_CANCEL_OPTION);

			if (option3 == JOptionPane.OK_OPTION) {

				Document doc = new Document();

				doc.append("Id", Integer.parseInt(id.getText()));
				doc.append("Titulo", titol.getText());
				doc.append("Autor", autor.getText());
				try {
					doc.append("Anyo_nacimiento", Integer.parseInt(anyo_Naixement.getText()));
				} catch (Exception e) {
					doc.append("Anyo_nacimiento", 0);
				}

				doc.append("Anyo_publicacion", Integer.parseInt(anyo_Publicacio.getText()));
				doc.append("Editorial", editorial.getText());
				doc.append("Numero_paginas", Integer.parseInt(pagines.getText()));

				try {
					File f = new File(arch.getSelectedFile().toString());
					byte[] fileContent = Files.readAllBytes(f.toPath());
					String encodedString = Base64.encodeBase64String(fileContent);
					doc.append("Thumbnail", encodedString);
				} catch (Exception e) {

				}

				coleccion.updateOne(eq("Id", Integer.parseInt(id.getText())), new Document("$set", doc));
				mongoClient.close();

			} else {
				mongoClient.close();
			}

		}
	}
	/**
	 * Funció que borra un document de la base de dades
	 * @param id ID del document que es desitja borrar
	 */
	public void mongoDelDoc(int id) {
		
		MongoClient mongoClient = new MongoClient(this.ip, Integer.parseInt(this.port));
		MongoDatabase database = mongoClient.getDatabase(this.db);
		MongoCollection<Document> coleccion = database.getCollection(this.llibres);

		coleccion.deleteOne(eq("Id", id));
		
		mongoClient.close();

	}
	
	/**
	 * Funció que borra tots del documents de la base de dades
	 * 
	 */
	public void  mongoDelCol() {
		
		
		MongoClient mongoClient = new MongoClient(this.ip, Integer.parseInt(this.port));
		MongoDatabase database = mongoClient.getDatabase(this.db);
		MongoCollection<Document> coleccion = database.getCollection(this.llibres);
		
		coleccion.drop();
		
		mongoClient.close();
	}
	
}
