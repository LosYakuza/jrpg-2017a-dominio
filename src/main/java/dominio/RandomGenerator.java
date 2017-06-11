package dominio;

/**
 * Generador de numeros randm
 */
public abstract class RandomGenerator {
	/**
	 * Flotante al azar.
	 * @return devuelve un flotante al azar.
	 */
	public abstract double nextDouble();

	/**
	 * Devuelve un entero al azar menor al maximo.
	 * @param max entero maximo.
	 * @return    entero ingresado por parámetro decrementado en uno.
	 */
	public abstract int nextInt(final int max);
}
