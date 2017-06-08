package dominio;

/**
 * Devuelve valores random.
 */
public class MyRandomStub extends RandomGenerator {

	private double hDouble;
	public static final double HDOUBLE = 0.49;

	/**
	 * Constructor de la clase MyRandomStub.
	 * @param hDou numero de 0 a 1.
	 */
	public MyRandomStub(final double hDou) {
		this.hDouble = hDou;
	}

	@Override
	public double nextDouble() {
		return this.hDouble;
	}

	@Override
	public int nextInt(final int max) {
		return max - 1;
	}

}
