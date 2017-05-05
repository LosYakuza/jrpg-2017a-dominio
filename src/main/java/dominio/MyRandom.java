package dominio;

/**
 * La clase MyRandom provee métodos estáticos que sirven para la generación de números.
 */
public class MyRandom {

	/**
	 * Método estático que devuelve un flotante fijo de valor 0.49
	 *
	 * @return 0.49
	 */
	public static double nextDouble() {
		return 0.49;
	}

	/**
	 * Método estático que recibe un valor entero por parámetro y como resultado lo devuelve decrementado en uno.
	 *
	 * @param val entero a decrementar.
	 * @return    entero ingresado por parámetro decrementado en uno.
	 */
	public static int nextInt(final int val) {
		return val - 1;
	}

}
