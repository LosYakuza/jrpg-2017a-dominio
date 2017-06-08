package dominio;

/**
 * La clase Humano es un Personaje que posee las habilidades Incentivar y Golpe Fatal.
 */

public class Humano extends Personaje {
	public static final int ENERGIA_MINIMA_PARA_HABILIDAD = 10;
	public static final int ADICIONAL_TOPE_HUMANO = 5;

	/**
	 * Constructor de la clase Humano.
	 * @param nombre nombre del humano.
	 * @param casta casta del humano.
	 * @param id id del humano.
	 */
	public Humano(final String nombre, final Casta casta, final int id) {
		super(nombre, casta, id);
	}

	/**
	 * Constructor de la clase Humano.
	 * @param nombre nombre del humano.
	 * @param salud salud del humano.
	 * @param energia energia del humano.
	 * @param fuerza fuerza del humano.
	 * @param destreza destreza del humano.
	 * @param inteligencia inteligencia del humano.
	 * @param casta casta del humano.
	 * @param experiencia experiencia del humano.
	 * @param nivel nivel del humano.
	 * @param idPersonaje id del humano.
	 */
	public Humano(final String nombre, final int salud, final int energia, final int fuerza, final int destreza,
			final int inteligencia, final Casta casta, final int experiencia, final int nivel,
			final int idPersonaje) {
		super(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, idPersonaje);
	}

	@Override
	protected String nombreRazaInicial() {
		return "Humano";
	}

	@Override
	protected void inicializarHabilidadesSegunRaza() {
		habilidadesRaza = new String[2];
		habilidadesRaza[0] = "Incentivar";
		habilidadesRaza[1] = "Golpe Fatal";
	}

	@Override
	protected int saludTopeInicial() {
		return super.saludTopeInicial() + ADICIONAL_TOPE_HUMANO;
	}

	@Override
	protected int energiaTopeInicial() {
		return super.energiaTopeInicial() + ADICIONAL_TOPE_HUMANO;
	}



	/**
	 * Se implementa la habilidad Incentivar.
	 * El personaje gasta energía para aumentar el ataque del personaje atacado según la magia de mi humano.
	 *
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return        true si el Humano tiene energía mayor a diez;
	 *                false en caso contrario.
	 */
	public boolean habilidadRaza1(final Peleable atacado) {
		if (this.getEnergia() > ENERGIA_MINIMA_PARA_HABILIDAD) {
			this.setEnergia(this.getEnergia() - ENERGIA_MINIMA_PARA_HABILIDAD);
			atacado.setAtaque(atacado.getAtaque() + this.getMagia());
			return true;
		}
		return false;
	}

	/**
	 * Se implementa la habilidad Golpe Fatal.
	 * El humano golpea con la intensidad correspondiente a la mitad de la vida del personaje atacado.
	 * Advertencia: si el humano no supera los 10 de energía, solo perderá energía y no atacará.
	 *
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return        true si el Humano tiene energía mayor a diez y el daño causado es mayor a cero;
	 *                false en caso contrario.
	 */
	public boolean habilidadRaza2(final Peleable atacado) {
		if (this.getEnergia() > ENERGIA_MINIMA_PARA_HABILIDAD) {
			if (atacado.serAtacado(atacado.getSalud() / 2) > 0) {
				this.setEnergia(this.getEnergia() / 2);
				return true;
			}
		}
		this.setEnergia(this.getEnergia() - ENERGIA_MINIMA_PARA_HABILIDAD);
		return false;
	}
}
