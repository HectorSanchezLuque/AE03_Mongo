package es.florida.AE03Mongo;

import java.awt.Image;

public class Llibre {

	int id;
	String titol;
	String autor;
	int anyo_Naixement;
	int anyo_Publicacio;
	String editorial;
	int pagines;
	Image imatge;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getTitol() {
		return titol;
	}



	public void setTitol(String titol) {
		this.titol = titol;
	}



	public String getAutor() {
		return autor;
	}



	public void setAutor(String autor) {
		this.autor = autor;
	}



	public int getAnyo_Naixement() {
		return anyo_Naixement;
	}



	public void setAnyo_Naixement(int anyo_Naixement) {
		this.anyo_Naixement = anyo_Naixement;
	}



	public int getAnyo_Publicacio() {
		return anyo_Publicacio;
	}



	public void setAnyo_Publicacio(int anyo_Publicacio) {
		this.anyo_Publicacio = anyo_Publicacio;
	}



	public String getEditorial() {
		return editorial;
	}



	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}



	public int getPagines() {
		return pagines;
	}



	public void setPagines(int pagines) {
		this.pagines = pagines;
	}



	public Image getImatge() {
		return imatge;
	}



	public void setImatge(Image imatge) {
		this.imatge = imatge;
	}



	public Llibre(int ident, String tit, String autr, int aN, int aP, String ed, int p) {
		super();
		id = ident;
		titol = tit;
		autor = autr;
		anyo_Naixement = aN;
		anyo_Publicacio = aP;
		editorial = ed;
		pagines = p;
		//imatge = im;
	}
	public Llibre(int ident, String tit, String autr, int aN, int aP, String ed, int p ,Image im) {
		super();
		id = ident;
		titol = tit;
		autor = autr;
		anyo_Naixement = aN;
		anyo_Publicacio = aP;
		editorial = ed;
		pagines = p;
		imatge = im;
	}
	
}
