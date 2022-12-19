package es.florida.AE03Mongo;

import java.awt.HeadlessException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Controlador {
	private Vista vista;
	private Model modelo;

	// El constructor rep instancia de la vista i del Model

	public Controlador(Vista vista, Model modelo)
			throws HeadlessException, NoSuchAlgorithmException, UnsupportedEncodingException {
		this.vista = vista;
		this.modelo = modelo;
		initEventHandlers();

	}
	  void MongoUserCon() {
			
		JTextField username = new JTextField();
		JTextField password = new JPasswordField();
		boolean login = true;
		try {
		while (login) {
			
			username.setText("");
			password.setText("");
			
			Object[] message = { "Usuari:", username, "Contrassenya:", password };
			int option = JOptionPane.showConfirmDialog(null, message, "Inici de sessió", JOptionPane.OK_CANCEL_OPTION);
			if (modelo.MongoCompUser(username.getText(), modelo.MongoConversor(password.getText())) && option == 0) {
				
				login = false;

			} else if (option == -1 || option == 2) {

				login = false;
				vista.dispatchEvent(new WindowEvent(vista, WindowEvent.WINDOW_CLOSING));

			}

		}
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
		
		
		
	}

	public void initEventHandlers() throws HeadlessException, NoSuchAlgorithmException, UnsupportedEncodingException {
		MongoUserCon();
		
		vista.getBtnCrear().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.MongoInsert();
			}
		});
		
		vista.getBtnMostrarTodo().addActionListener(
				
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						vista.getTextArea().setText(modelo.MongoMostrarTot());
					}
				}
				);
		
		vista.getBtnMostDoc().addActionListener(new ActionListener() {
			
			
			
			
			public void actionPerformed(ActionEvent e) {
				JTextField id = new JTextField();
				id.setText("");
				
				Object[] message = { "Indica Id del llibre a mostrar", id};
				
				int option = JOptionPane.showConfirmDialog(null, message, "Mostrar un llibre", JOptionPane.OK_CANCEL_OPTION);
				
				
				System.out.println(id.getText());
				//indicar la funcio que retorna tot un llibre;
			}
			
			
		}
				
				
				);
	}
}
