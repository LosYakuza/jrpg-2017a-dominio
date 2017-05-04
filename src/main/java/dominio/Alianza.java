package dominio;

import java.util.LinkedList;

/**
 * La clase Alianza está desarrollada para añadir, eliminar y obtener personajes de la lista de los aliados.
 */
public class Alianza {

	private String nombre;
	private LinkedList<Personaje> aliados;

	/**
	 * Constructor de la clase Alianza
	 * @param nombre nombre de la Alianza
	 */
	public Alianza(final String nombre) {
		this.nombre = nombre;
		this.aliados = new LinkedList<Personaje>();
	}

	public LinkedList<Personaje> getAliados() {
		return aliados;
	}

	public void setAliados(final LinkedList<Personaje> aliados) {
		this.aliados = aliados;
	}

	public String obtenerNombre(){
		return nombre;
	}

	/**
	 * Se elimina un personaje de la alianza.
	 *
	 * @param pj Personaje a remover de la lista de aliados.
	 */
	public void eliminarPersonaje(final Personaje pj){
		aliados.remove(pj);
	}

	/**
	 * Se añade un personaje a la alianza.
	 *
	 * @param pj Personaje a agregar en la lista de aliados.
	 */
	public void añadirPersonaje(final Personaje pj){
		aliados.add(pj);
	}
}
