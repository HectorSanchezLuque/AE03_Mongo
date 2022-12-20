package es.florida.AE03Mongo;

import java.awt.HeadlessException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
				int option = JOptionPane.showConfirmDialog(null, message, "Inici de sessió",
						JOptionPane.OK_CANCEL_OPTION);
				if (modelo.MongoCompUser(username.getText(), modelo.MongoConversor(password.getText()))
						&& option == 0) {

					login = false;

				} else if (option == -1 || option == 2) {

					login = false;
					vista.dispatchEvent(new WindowEvent(vista, WindowEvent.WINDOW_CLOSING));

				}

			}
		} catch (Exception ex) {

			ex.printStackTrace();
		}

	}

	public void initEventHandlers() throws HeadlessException, NoSuchAlgorithmException, UnsupportedEncodingException {
		MongoUserCon();

		vista.getBtnCrear().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modelo.MongoInsert();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		vista.getBtnMostrarTodo().addActionListener(

				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						vista.getTextArea().setText(modelo.MongoMostrarTot());
					}
				});

		vista.getBtnMostDoc().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JTextField id = new JTextField();
				id.setText("");

				Object[] message = { "Indica Id del llibre a mostrar", id };

				int option = JOptionPane.showConfirmDialog(null, message, "Mostrar un llibre",
						JOptionPane.OK_CANCEL_OPTION);

				System.out.println(id.getText());

				Llibre lib = modelo.mongoRetornDoc(Integer.parseInt(id.getText()));

				vista.getTextArea()
						.setText("Titol: " + lib.getTitol() + "\n" + "Autor: " + lib.getAutor() + "\n"
								+ "Any de Naixement: " + lib.getAnyo_Naixement() + "\n" + "Any de publicació: "
								+ lib.getAnyo_Publicacio() + "\n" + "Editorial: " + lib.getEditorial() + "\n"
								+ "Número de pàgines: " + lib.getPagines());
				ImageIcon imatgeIcona = new ImageIcon(lib.getImatge());

				JOptionPane.showMessageDialog(null, "", "", JOptionPane.INFORMATION_MESSAGE, imatgeIcona);

			}

		}

		);

		vista.getBtnConsulta().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Object[] capm = { "Id", "Any de naixement", "Any de publicacio", "Numero de pagines" };
				Object[] tipus = { "igual", "major o igual", "menor o igual" };

			
				JComboBox Jcamp = new JComboBox(capm);
				JComboBox Jtipus = new JComboBox(tipus);

				JPanel container = new JPanel();
				container.add(Jcamp);
				container.add(Jtipus);
			
				try {
				int valor =  Integer.parseInt(JOptionPane.showInputDialog(null, container, "Indica Consulta",
						JOptionPane.QUESTION_MESSAGE));
				
				System.out.println("Selection: " + Jcamp.getSelectedItem() + " | " + Jtipus.getSelectedItem() + ":"+ valor);
				vista.getTextArea().setText(modelo.MongoConsult(Jcamp.getSelectedItem().toString(), Jtipus.getSelectedItem().toString(), valor) );

				}catch(Exception E) {
					
				};
				
				
				
			}
			});
		vista.getBtnActualitzar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modelo.MongoActualitzar();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
