package dominio;

/**
 * La clase NonPlayableCharacter representa a los personajes no no jugables (también llamados NPC o PNJ)
 */

public class NonPlayableCharacter extends Peleador {

	private static final int DIFICULTAD_ALEATORIA = -1;
	private static final int DIFICULTADES = 3;
	private static final int FUERZA_INICIAL_0 = 10;
	private static final int FUERZA_INICIAL_1 = 20;
	private static final int FUERZA_INICIAL_2 = 30;
	private static final int SALUD_INICIAL_0 = 30;
	private static final int SALUD_INICIAL_1 = 40;
	private static final int SALUD_INICIAL_2 = 50;
	private static final int DEFENSA_INICIAL_0 = 2;
	private static final int DEFENSA_INICIAL_1 = 5;
	private static final int DEFENSA_INICIAL_2 = 4;
	private static final int FUERZA_ADICIONAL_POR_NIVEL_0 = 3;
	private static final int FUERZA_ADICIONAL_POR_NIVEL_1 = 6;
	private static final int FUERZA_ADICIONAL_POR_NIVEL_2 = 10;
	private static final int SALUD_ADICIONAL_POR_NIVEL_0 = 15;
	private static final int SALUD_ADICIONAL_POR_NIVEL_1 = 20;
	private static final int SALUD_ADICIONAL_POR_NIVEL_2 = 25;
	private static final int DEFENSA_ADICIONAL_POR_NIVEL_0 = 1;
	private static final int DEFENSA_ADICIONAL_POR_NIVEL_1 = 2;
	private static final int DEFENSA_ADICIONAL_POR_NIVEL_2 = 4;
	private static final int MULTIPLICADOR_DE_EXPERIENCIA = 30;
	private static final double PROBABILIDAD_POR_DEFECTO = 0.15;
	private static final double GOLPE_CRITICO_POR_DEFECTO = 1.5;

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
		if (dificultadNPC == DIFICULTAD_ALEATORIA) {
			dificultad = getRandomGenerator().nextInt(DIFICULTADES);
		} else {
			dificultad = dificultadNPC;
		}

		switch (dificultad) {
		case 0:
			setFuerza(FUERZA_INICIAL_0 + (nivel - 1) * FUERZA_ADICIONAL_POR_NIVEL_0);
			setSalud(SALUD_INICIAL_0 + (nivel - 1) * SALUD_ADICIONAL_POR_NIVEL_0);
			setDefensa(DEFENSA_INICIAL_0 + (nivel - 1) * DEFENSA_ADICIONAL_POR_NIVEL_0);
			break;
		case 1:
			setFuerza(FUERZA_INICIAL_1 + (nivel - 1) * FUERZA_ADICIONAL_POR_NIVEL_1);
			setSalud(SALUD_INICIAL_1 + (nivel - 1) * SALUD_ADICIONAL_POR_NIVEL_1);
			setDefensa(DEFENSA_INICIAL_1 + (nivel - 1) * DEFENSA_ADICIONAL_POR_NIVEL_1);
			break;
		case 2:
			setFuerza(FUERZA_INICIAL_2 + (nivel - 1) * FUERZA_ADICIONAL_POR_NIVEL_2);
			setSalud(SALUD_INICIAL_2 + (nivel - 1) * SALUD_ADICIONAL_POR_NIVEL_2);
			setDefensa(DEFENSA_INICIAL_2 + (nivel - 1) * DEFENSA_ADICIONAL_POR_NIVEL_2);
			break;
		default:
			break;

		}
	}

	@Override
	protected int multiplicadorExperiencia() {
		return MULTIPLICADOR_DE_EXPERIENCIA;
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
		return PROBABILIDAD_POR_DEFECTO;
	}

	@Override
	protected int defensaAlSerAtacado() {
		return getDefensa() / 2;
	}

	@Override
	protected int golpeCritico() {
		return (int) (getAtaque() * GOLPE_CRITICO_POR_DEFECTO);
	}

	@Override
	protected double probabilidadGolpeCritico() {
		return PROBABILIDAD_POR_DEFECTO;
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
