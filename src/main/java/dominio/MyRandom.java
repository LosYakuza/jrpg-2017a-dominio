package dominio;
import java.util.Random;

/**
 * Devuelve valores random
 */
public class MyRandom extends RandomGenerator {
	private Random rnd;

	/**
	 * Constructor de la clase MyRandom.
	 */
	public MyRandom() {
		rnd = new Random();
	}

	@Override
	public double nextDouble() {
		return rnd.nextDouble();
	}

	@Override
	public int nextInt(final int max) {
		return rnd.nextInt(max);
	}

}
