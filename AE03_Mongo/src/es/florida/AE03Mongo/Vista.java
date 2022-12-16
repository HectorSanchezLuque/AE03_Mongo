package es.florida.AE03Mongo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Vista extends JFrame {

	private JPanel contentPane;

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
		
		JButton btnMostrarTodo = new JButton("Mostrar tot");
		btnMostrarTodo.setBounds(16, 56, 157, 50);
		contentPane.add(btnMostrarTodo);
		
		JButton btnMostDoc = new JButton("Mostrar Document");
		btnMostDoc.setBounds(16, 126, 157, 50);
		contentPane.add(btnMostDoc);
		
		JButton btnActualitzar = new JButton("Actualitzar Document");
		btnActualitzar.setBounds(10, 197, 163, 50);
		contentPane.add(btnActualitzar);
		
		JButton btnEsborrar = new JButton("Esborrar Document");
		btnEsborrar.setBounds(16, 271, 157, 50);
		contentPane.add(btnEsborrar);
		
		JButton btnCrear = new JButton("Crear Document");
		btnCrear.setBounds(16, 344, 157, 50);
		contentPane.add(btnCrear);
		
		JTextArea textArea = new JTextArea();
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
