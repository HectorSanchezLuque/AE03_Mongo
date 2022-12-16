package es.florida.AE03Mongo;

import java.awt.Image;

public class Llibre {

	int id;

	String titol;

	int pagines;

	String editorial;

	Image imatge;

	public int getId() {
		return id;
	}

	public String getTitol() {
		return titol;
	}

	public int getPagines() {
		return pagines;
	}

	public String getEditorial() {
		return editorial;
	}

	public Image getImatge() {
		return imatge;
	}

	public Llibre(int id, String titol, int pagines, String editorial, Image imatge) {
		super();
		this.id = id;
		this.titol = titol;
		this.pagines = pagines;
		this.editorial = editorial;
		this.imatge = imatge;
	}
	
	
	

}
