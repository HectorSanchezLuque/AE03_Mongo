package es.florida.AE03Mongo;

import java.awt.HeadlessException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Principal {

	public static void main(String[] args) throws HeadlessException, NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
		Vista vista = new Vista();
		Model modelo = new Model();
		Controlador controlador = new Controlador(vista, modelo);
	
		
		
	}

}
