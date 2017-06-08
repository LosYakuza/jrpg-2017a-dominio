package dominio;

/**
 * La clase NonPlayableCharacter representa a los personajes no no jugables (también llamados NPC o PNJ)
 */

public class NonPlayableCharacter extends Peleador {

	private final int dificultadAleatoria = -1;
	private final int dificultades = 3;
	private final int fuerzaInicial0 = 10;
	private final int fuerzaInicial1 = 20;
	private final int fuerzaInicial2 = 30;
	private final int saludInicial0 = 30;
	private final int saludInicial1 = 40;
	private final int saludInicial2 = 50;
	private final int defensaInicial0 = 2;
	private final int defensaInicial1 = 5;
	private final int defensaInicial2 = 4;
	private final int fuerzaAdicionalPorNivel0 = 3;
	private final int fuerzaAdicionalPorNivel1 = 6;
	private final int fuerzaAdicionalPorNivel2 = 10;
	private final int saludAdicionalPorNivel0 = 15;
	private final int saludAdicionalPorNivel1 = 20;
	private final int saludAdicionalPorNivel2 = 25;
	private final int defensaAdicionalPorNivel0 = 1;
	private final int defensaAdicionalPorNivel1 = 2;
	private final int defensaAdicionalPorNivel2 = 4;
	private final int multiplicadorDeExperiencia = 30;
	private final double probabilidadPorDefecto = 0.15;
	private final double golpeCriticoPorDefecto = 1.5;

	/**
	 * Constructor de la clase NonPlayableCharacter. La dificultad del NPC depende de si es aleatoria o no.
	 * La dificultad del NPC es la que determina su fuerza, salud y defensa.
	 *
	 * @param nombre nombre inicial del NPC
	 * @param nivel nivel inicial del NPC
	 * @param dificultadNPC dificultad asignada, de este valor depende la fuerza, salud y defensa del NPC
	 */
	public NonPlayableCharacter(final String nombre, final int nivel, final int dificultadNPC) {
		setNombre(nombre);
		setNivel(nivel);
		int dificultad;
		if (dificultadNPC == dificultadAleatoria) {
			dificultad = getRandomGenerator().nextInt(dificultades);
		} else {
			dificultad = dificultadNPC;
		}

		switch (dificultad) {
		case 0:
			setFuerza(fuerzaInicial0 + (nivel - 1) * fuerzaAdicionalPorNivel0);
			setSalud(saludInicial0 + (nivel - 1) * saludAdicionalPorNivel0);
			setDefensa(defensaInicial0 + (nivel - 1) * defensaAdicionalPorNivel0);
			break;
		case 1:
			setFuerza(fuerzaInicial1 + (nivel - 1) * fuerzaAdicionalPorNivel1);
			setSalud(saludInicial1 + (nivel - 1) * saludAdicionalPorNivel1);
			setDefensa(defensaInicial1 + (nivel - 1) * defensaAdicionalPorNivel1);
			break;
		case 2:
			setFuerza(fuerzaInicial2 + (nivel - 1) * fuerzaAdicionalPorNivel2);
			setSalud(saludInicial2 + (nivel - 1) * saludAdicionalPorNivel2);
			setDefensa(defensaInicial2 + (nivel - 1) * defensaAdicionalPorNivel2);
			break;
		default:
			break;

		}
	}

	@Override
	protected int multiplicadorExperiencia() {
		return multiplicadorDeExperiencia;
	}

	@Override
	public int getAtaque() {
		return getFuerza();
	}

	@Override
	public void setAtaque(final int ataque) {
		setFuerza(ataque);
	}

	/**
	 * Funcion no desarrollada.
	 * @param exp experiencia ganada
	 */
	public void ganarExperiencia(final int exp) {	}

	@Override
	protected double probabilidadEvitarDanoEnAtaque() {
		return probabilidadPorDefecto;
	}

	@Override
	protected int defensaAlSerAtacado() {
		return getDefensa() / 2;
	}

	@Override
	protected int golpeCritico() {
		return (int) (getAtaque() * golpeCriticoPorDefecto);
	}

	@Override
	protected double probabilidadGolpeCritico() {
		return probabilidadPorDefecto;
	}

	@Override
	public final boolean esAfectadoPorHechicero() {
		return false;
	}

	@Override
	public final boolean esAfectadoPorGuerrero() {
		return false;
	}

}
