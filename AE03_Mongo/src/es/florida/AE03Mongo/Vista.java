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
	

	/**
	 * @return retorna el botò de Mostrar tot
	 */
	public JButton getBtnMostrarTodo() {
		return btnMostrarTodo;
	}

	/**
	 * @return retorna el botò de tancar la conexió
	 */
	public JButton getBtnTancarConexio() {
		return btnTancarConexio;
	}
	
	/**
	 * @return retorna el botò de fer una consulta
	 */
	public JButton getBtnConsulta() {
		return btnConsulta;
	}

	/**
	 * @return retorna el botò de 
	 */
	public JButton getBtnMostDoc() {
		return btnMostDoc;
	}


	/**
	 * @return retorna el botò de actulizar un llibre
	 */
	public JButton getBtnActualitzar() {
		return btnActualitzar;
	}


	/**
	 * @return retorna el botò de esborrar un llibre
	 */
	public JButton getBtnEsborrar() {
		return btnEsborrar;
	}


	/**
	 * @return retorna el botò de crear un llibre
	 */
	public JButton getBtnCrear() {
		return btnCrear;
	}

	/**
	 * @return retorna el Area de text per a mostrar informació
	 */
	public JTextArea getTextArea() {
		return textArea;
	}


	/**
	 * @return retorna el botò de boora tota un coleció com la coleció en si
	 */
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

	/*
	 * Inicializar la interface
	 */
	public void initComponents() {
		setResizable(false);
		setTitle("Aplicació");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

	}
}
