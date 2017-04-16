package dominio;

import java.util.LinkedList;

/**
 * La clase Alianza está desarrollada para añadir, eliminar y obtener personajes de la lista de los aliados.
 */
public class Alianza {

	String nombre;
	LinkedList<Personaje> aliados;

	/**
	 * Constructor de la clase Alianza
	 * @param nombre nombre de la Alianza
	 */
	public Alianza(String nombre) {
		this.nombre = nombre;
		this.aliados = new LinkedList <Personaje>();
	}

	public LinkedList<Personaje> getAliados() {
		return aliados;
	}

	public void setAliados(LinkedList<Personaje> aliados) {
		this.aliados = aliados;
	}

	public String obtenerNombre(){
		return nombre;
	}
	
	/**
	 * Método que elimina un personaje de la alianza
	 * 
	 * @param pj Personaje a eliminar de la lista de aliados.
	 */
	public void eliminarPersonaje(Personaje pj){
		aliados.remove(pj);
	}
	
	/**
	 * Método que añade un personaje a la alianza
	 * 
	 * @param pj Personaje a añadir en la lista de aliados.
	 */
	public void añadirPersonaje(Personaje pj){
		aliados.add(pj);
	}
}
