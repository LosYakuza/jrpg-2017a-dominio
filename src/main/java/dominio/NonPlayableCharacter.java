package dominio;

import java.util.LinkedList;

/**
 * La clase NonPlayableCharacter representa a los personajes no no jugables (también llamados NPC o PNJ)
 */

public class NonPlayableCharacter extends Peleador {

	private static final int dificultadAleatoria = -1;

	/**
	 * Constructor de la clase NonPlayableCharacter. La dificultad del NPC depende de si es aleatoria o no.
	 * La dificultad del NPC es la que determina su fuerza, salud y defensa.
	 *
	 * @param nombre nombre inicial del NPC
	 * @param nivel nivel inicial del NPC
	 * @param dificultadNPC dificultad asignada, de este valor depende la fuerza, salud y defensa del NPC
	 * @param inventario lista de ítems del NPC
	 */
	public NonPlayableCharacter(String nombre, int nivel, int dificultadNPC, 
			LinkedList<Item> inventario) {
		super(inventario);
		setNombre(nombre);
		setNivel(nivel);
		int dificultad;
		if (dificultadNPC == dificultadAleatoria) {
			dificultad = getRandomGenerator().nextInt(3);
		} else {
			dificultad = dificultadNPC;
		}

		switch (dificultad) {
		case 0:
			setFuerza(10 + (nivel - 1) * 3);// 30%
			setSalud(30 + (nivel - 1) * 15);
			setDefensa(2 + (nivel - 1) * 1);
			break;
		case 1:
			setFuerza(20 + (nivel - 1) * 6);// 50%
			setSalud(40 + (nivel - 1) * 20);
			setDefensa(5 + (nivel - 1) * 2);
			break;
		case 2:
			setFuerza(30 + (nivel - 1) * 10);// 50%
			setSalud(50 + (nivel - 1) * 25);
			setDefensa(4 + (nivel - 1) * 4);
			break;
		default:
			break;

		}
	}

	@Override
	protected int multiplicadorExperiencia() {
		return 30;
	}

	@Override
	public int getAtaque() {
		return getFuerza();
	}

	@Override
	public void setAtaque(final int ataque) {
		setFuerza(ataque);
	}

	public void ganarExperiencia(final int exp) {	}

	@Override
	protected double probabilidadEvitarDanoEnAtaque() {
		return 0.15;
	}

	@Override
	protected int defensaAlSerAtacado() {
		return getDefensa() / 2;
	}

	@Override
	protected int golpeCritico() {
		return (int) (getAtaque() * 1.5);
	}

	@Override
	protected double probabilidadGolpeCritico() {
		return 0.15;
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
