package dominio;

/**
 * Interfaz que implementa un personaje Peleable para pelear contra otro.
 */
public interface Peleable {

	/**
	 * Calcula el daño que recibe el personaje atacado.
	 * @param danio daño que recibe el personaje atacado.
	 * @return daño total infligido.
	 */
	int serAtacado(int danio);

	/**
	 * Devuelve la salud del personaje peleable.
	 * @return salud del personaje peleable.
	 */
	int getSalud();

	/**
	 * No esta implementada.
	 */
	void despuesDeTurno();

	/**
	 * Causar daño al atacado evaluando la probabilidad de golpe crítico y su ataque.
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado.
	 * @return daño causado.
	 */
	int atacar(Peleable atacado);

	/**
	 * Da experiencia al personaje.
	 * @return nivel multiplicado
 	 */
	int otorgarExp();

	/**
	 * Devuelve el valor del ataque.
	 * @return ataque.
	 */
	int getAtaque();

	/**
	 * Carga el valor del ataque del personaje.
	 * @param ataque ataque del personaje.
	 */
	void setAtaque(int ataque);

	/**
	 * Esta vivo.
	 * @return true si esta vivo.
	 */
	boolean estaVivo();

	/**
	 * Devuelve el nombre del personaje.
	 * @return nombre nombre del personaje.
	 */
	String getNombre();

	/**
	 * Indica si el personaje es afectado por hechicero.
	 * @return true si es afectado por hechicero.
	 */
	boolean esAfectadoPorHechicero();

	/**
	 * Indica si el personaje es afectado por guerrero.
	 * @return true si es afectado por guerrero.
	 */
	boolean esAfectadoPorGuerrero();
}
