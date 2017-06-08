package dominio;

/**
 * La clase Hechicero es una Casta que posee como habilidades Bola de Fuego, Curar Aliado y Robar Energía y Salud.
 *
 */
public class Hechicero extends Casta {
	private final int cantHabilidades = 3;
	private final int energiaMinimaParaHabilidad = 10;
	private final int inteligenciaHechicero = 5;
	private final double incrementoMagico = 1.5;

	/**
	 * Constructor de la clase Hechicero pasando probabilidad de gompe critico, evasion y daño critico.
	 * @param probCrit probabilidad de golpe critico.
	 * @param evasion probabilidad de evadir golpe.
	 * @param danioCrit daño critico.
	 */
	public Hechicero(final double probCrit, final double evasion, final double danioCrit) {
		super(probCrit, evasion, danioCrit);
		this.nombreCasta = "Hechicero";
	}

	/**
	 * Constructor de la clase Hechicero.
	 */
	public Hechicero() {
		super();
		this.nombreCasta = "Hechicero";
		habilidadesCasta = new String[cantHabilidades];
		habilidadesCasta[0] = "Bola de Fuego";
		habilidadesCasta[1] = "Curar Aliado";
		habilidadesCasta[2] = "Robar Energia y Salud";
	}

	/**
	 * Se implementa la habilidad Bola de Fuego.
	 * El personaje gasta energía para atacar según sus puntos de magia, que varían según la inteligencia.
	 *
	 * @param caster Personaje que va a atacar
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return        true si el Personaje tiene energía mayor a diez y el daño causado es mayor a cero;
	 *                false en caso contrario.
	 */
	public boolean habilidad1(final Personaje caster, final Peleable atacado) {
		if (caster.getEnergia() > energiaMinimaParaHabilidad) {
			caster.setEnergia(caster.getEnergia() - energiaMinimaParaHabilidad);
			if (atacado.serAtacado((int) (caster.calcularPuntosDeMagia() * incrementoMagico)) > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Se implementa la habilidad Curar Aliado.
	 * El personaje gasta energía para curar a un personaje aliado según sus puntos de magia.
	 *
	 * @param caster Personaje que va a curar
	 * @param aliado un objeto que implementa la interfaz Peleable, es aquel a ser curado
	 * @return       true si caster tiene energía mayor a diez y el aliado es un Personaje;
	 *               false en caso contrario.
	 */
	public boolean habilidad2(final Personaje caster, final Peleable aliado) {
		if (caster.getEnergia() > energiaMinimaParaHabilidad) {
			caster.setEnergia(caster.getEnergia() - energiaMinimaParaHabilidad);
			if (aliado.esAfectadoPorHechicero()) {
				((Personaje) aliado).serCurado(caster.calcularPuntosDeMagia());
				return true;
			}
		}
		return false;
	}

	/**
	 * Se implementa la habilidad Robar Energía y Salud.
	 * El personaje gasta energía para reducir la energía y la salud del personaje atacado según sus puntos de magia
	 * La energía y la salud quitada, la toma el Hechicero.
	 *
	 * @param caster Personaje que va a robar energía y salud
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return        true en caso de que caster tenga energía mayor a diez y el atacado es Personaje;
	 *                false en caso contrario.
	 */
	public boolean habilidad3(final Personaje caster, final Peleable atacado) {
		if (caster.getEnergia() > energiaMinimaParaHabilidad) {
			caster.setEnergia(caster.getEnergia() - energiaMinimaParaHabilidad);
			if (atacado.esAfectadoPorHechicero()) {
				int eRobada = ((Personaje) atacado).serDesernegizado(caster.calcularPuntosDeMagia());
				int sRobada = ((Personaje) atacado).serRobadoSalud(caster.calcularPuntosDeMagia() / 2);
				caster.serEnergizado(eRobada);
				caster.serCurado(sRobada);
				return true;
			}

		}
		return false;
	}

	@Override
	public int getInteligencia() {
		return super.getInteligencia() + inteligenciaHechicero;
	}
}
