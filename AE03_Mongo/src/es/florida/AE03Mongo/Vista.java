package es.florida.AE03Mongo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vista extends JFrame {

	private JPanel contentPane;
	private JButton btnMostrarTodo;
	private JButton btnMostDoc;
	private JButton btnActualitzar;
	private JButton btnEsborrar;
	private JButton btnCrear;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public JButton getBtnMostrarTodo() {
		return btnMostrarTodo;
	}
	public void setBtnMostrarTodo(JButton btnMostrarTodo) {
		this.btnMostrarTodo = btnMostrarTodo;
	}
	public JButton getBtnMostDoc() {
		return btnMostDoc;
	}
	public void setBtnMostDoc(JButton btnMostDoc) {
		this.btnMostDoc = btnMostDoc;
	}
	public JButton getBtnActualitzar() {
		return btnActualitzar;
	}
	public void setBtnActualitzar(JButton btnActualitzar) {
		this.btnActualitzar = btnActualitzar;
	}
	public JButton getBtnEsborrar() {
		return btnEsborrar;
	}
	public void setBtnEsborrar(JButton btnEsborrar) {
		this.btnEsborrar = btnEsborrar;
	}
	public JButton getBtnCrear() {
		return btnCrear;
	}
	public void setBtnCrear(JButton btnCrear) {
		this.btnCrear = btnCrear;
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	/**
	 * Create the frame.
	 */
	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnMostrarTodo = new JButton("Mostrar tot");
		btnMostrarTodo.setBounds(16, 56, 157, 50);
		contentPane.add(btnMostrarTodo);
		
		btnMostDoc = new JButton("Mostrar Document");
		btnMostDoc.setBounds(16, 126, 157, 50);
		contentPane.add(btnMostDoc);
		
		btnActualitzar = new JButton("Actualitzar Document");
		btnActualitzar.setBounds(10, 197, 163, 50);
		contentPane.add(btnActualitzar);
		
		btnEsborrar = new JButton("Esborrar Document");
		btnEsborrar.setBounds(16, 271, 157, 50);
		contentPane.add(btnEsborrar);
		
		btnCrear = new JButton("Crear Document");
		btnCrear.setBounds(16, 344, 157, 50);
		contentPane.add(btnCrear);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(183, 11, 541, 439);
		contentPane.add(textArea);
		initComponents();
	}
	public void initComponents() {
		setResizable(false);
		setTitle("Aplicació");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
		}
}
