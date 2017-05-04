package dominio;

/**
 * La clase Elfo es un Personaje que posee las habilidades Golpe Level y Ataque Bosque.
 */

public class Elfo extends Personaje {

	public Elfo(String nombre, Casta casta, int id) {
		super(nombre, casta, id);
	}

	public Elfo(String nombre, int salud, int energia, int fuerza, int destreza, int inteligencia, Casta casta,
			int experiencia, int nivel, int idPersonaje) {
		super(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, idPersonaje);
	}

	@Override
	protected String nombreRazaInicial() {
		return "Elfo";
	}

	@Override
	protected void inicializarHabilidadesSegunRaza() {
		habilidadesRaza = new String[2];
		habilidadesRaza[0] = "Golpe Level";
		habilidadesRaza[1] = "Ataque Bosque";
	}

	@Override
	protected int energiaTopeInicial() {
		return super.energiaTopeInicial() + 10;
	}

	/**
	 * Se implementa la habilidad Golpe Level.
	 * El personaje gasta energía para atacar con la intensidad de la fuerza y el nivel del personaje multiplicado por diez.
	 *
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return        true si el Elfo tiene energía mayor a diez y el daño causado es mayor a cero;
	 *                false en caso contrario.
	 */
	// Golpe Level
	public boolean habilidadRaza1(final Peleable atacado) {
		if (this.getEnergia() > 10) {
			this.setEnergia(this.getEnergia() - 10);
			if (atacado.serAtacado(getFuerza() + getNivel() * 10) > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Se implementa la habilidad Ataque Bosque.
	 * El personaje gasta energía para atacar con el poder de su magia.
	 *
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return        true si el Elfo tiene energía mayor a diez y el daño causado es mayor a cero;
	 *                false en caso contrario.
	 */
	// Ataque Bosque
	public boolean habilidadRaza2(final Peleable atacado) {
		if (this.getEnergia() > 10) {
			this.setEnergia(this.getEnergia() - 10);
			if (atacado.serAtacado((int) (this.magia)) > 0) {
				return true;
			}
		}
		return false;
	}
}
