package dominio;

/**
 * Ofertas del mercado.
 */
public class OfertaMercado {
	private int idOferta;
	private int idItem;
	private int idPersonaje;
	private String nameItemRequerido;

	/**
	 * Constructor parametrizado.
	 * @param id identificador de la oferta
	 * @param idItem identificador del item
	 * @param nameItemRequerido nombre del objeto que se quiere
	 * @param idPersonaje id del personaje que publicó la oferta
	 */
	public OfertaMercado(final int id, final int idItem, final String nameItemRequerido, final int idPersonaje) {
		this.idOferta = id;
		this.idItem = idItem;
		this.nameItemRequerido = nameItemRequerido;
		this.idPersonaje = idPersonaje;
	}

	/**
	 * Getter id oferta
	 * @return id oferta
	 */
	public int getIdOferta() {
		return idOferta;
	}
	/**
	 * Setter id oferta
	 * @param idOferta id
	 */
	public void setIdOferta(final int idOferta) {
		this.idOferta = idOferta;
	}
	/**
	 * Getter id item
	 * @return id item
	 */
	public int getIdItem() {
		return idItem;
	}
	/**
	 * Setter id item
	 * @param idItem id
	 */
	public void setIdItem(final int idItem) {
		this.idItem = idItem;
	}
	/**
	 * Getter id personaje
	 * @return id personaje
	 */
	public int getIdPersonaje() {
		return idPersonaje;
	}
	/**
	 * Setter id personaje
	 * @param idPersonaje id
	 */
	public void setIdPersonaje(final int idPersonaje) {
		this.idPersonaje = idPersonaje;
	}
	/**
	 * Getter name item requerido
	 * @return name item requerido
	 */
	public String getNameItemRequerido() {
		return nameItemRequerido;
	}
	/**
	 * Setter name item requerido
	 * @param nameItemRequerido nombre del item
	 */
	public void setNameItemRequerido(final String nameItemRequerido) {
		this.nameItemRequerido = nameItemRequerido;
	}

}
