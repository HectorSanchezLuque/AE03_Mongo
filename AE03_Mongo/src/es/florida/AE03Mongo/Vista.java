package es.florida.AE03Mongo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;

public class Vista extends JFrame {

	private JPanel contentPane;
	private JButton btnTancarConexio;
	private JButton btnMostrarTodo;
	private JButton btnMostDoc;
	private JButton btnActualitzar;
	private JButton btnEsborrar;
	private JButton btnCrear;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JButton btnConsulta;
	private JButton btn_borrCol;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Vista frame = new Vista();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	public JButton getBtnMostrarTodo() {
		return btnMostrarTodo;
	}

	public JButton getBtnTancarConexio() {
		return btnTancarConexio;
	}

	public void setBtnTancarConexio(JButton btnTancarConexio) {
		this.btnTancarConexio = btnTancarConexio;
	}

	public JButton getBtnConsulta() {
		return btnConsulta;
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

	public JButton getBtn_borrCol() {
		return btn_borrCol;
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
		btnMostrarTodo.setBounds(16, 11, 157, 50);
		contentPane.add(btnMostrarTodo);

		btnMostDoc = new JButton("Mostrar Document");
		btnMostDoc.setBounds(16, 72, 157, 50);
		contentPane.add(btnMostDoc);

		btnActualitzar = new JButton("Actualitzar Document");
		btnActualitzar.setBounds(10, 133, 163, 50);
		contentPane.add(btnActualitzar);

		btnEsborrar = new JButton("Esborrar Document");
		btnEsborrar.setBounds(16, 194, 157, 50);
		contentPane.add(btnEsborrar);

		btnCrear = new JButton("Crear Document");
		btnCrear.setBounds(16, 255, 157, 50);
		contentPane.add(btnCrear);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(183, 11, 541, 439);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);

		btnConsulta = new JButton("Realitzar Consulta");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsulta.setBounds(16, 330, 157, 50);
		contentPane.add(btnConsulta);

		btn_borrCol = new JButton("Esborrar-ho Tot");
		btn_borrCol.setBackground(Color.RED);
		btn_borrCol.setBounds(27, 429, 126, 21);
		contentPane.add(btn_borrCol);
		
		btnTancarConexio = new JButton("Tancar conexió");
		btnTancarConexio.setBounds(26, 391, 127, 27);
		contentPane.add(btnTancarConexio);

		initComponents();

	}

	public void initComponents() {
		setResizable(false);
		setTitle("Aplicació");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

	}
}
