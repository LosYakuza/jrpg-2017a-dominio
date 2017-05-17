package dominio;
import java.util.Random;

/**
 * Devuelve valores random
 */
public class MyRandom extends RandomGenerator{

	private Random rnd;
	
	public MyRandom() {
		rnd = new Random();
	}
	
	@Override
	public double nextDouble() {
		return rnd.nextDouble();
	}

	@Override
	public int nextInt(int max) {
		return rnd.nextInt(max);
	}

}
