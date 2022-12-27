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


	

	/**
	 * @return retorna la id del llibre
	 */
	public int getId() {
		return id;
	}





	/**
	 * @return retorna el títol del llibre
	 */
	public String getTitol() {
		return titol;
	}




	/**
	 * @return retorna el autor del llibre
	 */
	public String getAutor() {
		return autor;
	}





	/**
	 * @return retorna el any de naixement del autor que a fet el llibre
	 */
	public int getAnyo_Naixement() {
		return anyo_Naixement;
	}




	/**
	 * @return retorna el any de públicació de llibre
	 */
	public int getAnyo_Publicacio() {
		return anyo_Publicacio;
	}




	/**
	 * @return retorna la editorial del LLibre
	 */
	public String getEditorial() {
		return editorial;
	}




	/**
	 * @return retorna les págines del llibre
	 */
	public int getPagines() {
		return pagines;
	}




	/**
	 * @return retorna la image del llibre
	 */
	public Image getImatge() {
		return imatge;
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
