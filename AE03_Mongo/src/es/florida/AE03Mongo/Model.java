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
import java.util.Arrays;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.codec.binary.Base64;
import org.bson.Document;
import org.bson.conversions.Bson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
		return true;
	}

	public String MongoMostrarTot() {
		int cont = 0;
		MongoClient mongoClient = new MongoClient(this.ip, Integer.parseInt(this.port));
		MongoDatabase database = mongoClient.getDatabase(this.db);
		MongoCollection<Document> coleccion = database.getCollection(this.llibres);

		String mostr = "Id | Titulo | Autor | Anyo_nacimiento | Anyo_publicacion | Editorial | Numero_paginas \n";

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
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imatges","jpg","png");
        arch.setFileFilter(filter);

		Object[] message = {"ID", id, "Titol:", titol, "Autor:", autor, "Any de naixement:", anyo_Naixement,
				"Any de publicació:", anyo_Publicacio, "Editorial:", editorial, "Pagines:", pagines, "Imatge:", arch };
		int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);

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

	}

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

	public String MongoConsult(String camp, String tipus, int valor) {


		String cam_query = "";
		String mostr = "";

		if (camp.equals("Any de naixement")) {

			cam_query = "Anyo_nacimiento";
		} else if (camp.equals("Any de publicacio")) {

			cam_query = "Anyo_publicacion";

		} else if (camp.equals("Numero de pagines")) {

			cam_query = "Numero_paginas";

		}

		MongoClient mongoClient = new MongoClient(this.ip, Integer.parseInt(this.port));
		MongoDatabase database = mongoClient.getDatabase(this.db);
		MongoCollection<Document> coleccion = database.getCollection(this.llibres);
		Bson query = null;
		if (tipus.equals("igual")) {

			query = eq(cam_query, valor);
		} else if (tipus.equals("major o igual")) {

			query = gte(cam_query, valor);
		} else if (tipus.equals("menor o igual")) {
			query = lte(cam_query, valor);
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

}
