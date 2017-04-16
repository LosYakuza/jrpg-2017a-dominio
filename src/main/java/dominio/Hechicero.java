package dominio;

/**
 * La clase Hechicero es una Casta que posee como habilidades Bola de Fuego, Curar Aliado y Robar Energía y Salud.
 *
 */
public class Hechicero extends Casta {

	public Hechicero(double prob_crit, double evasion, double daño_crit) {
		super(prob_crit, evasion, daño_crit);
		this.nombreCasta = "Hechicero";
	}

	public Hechicero() {
		super();
		this.nombreCasta = "Hechicero";
		habilidadesCasta = new String[3];
		habilidadesCasta[0] = "Bola de Fuego";
		habilidadesCasta[1] = "Curar Aliado";
		habilidadesCasta[2] = "Robar Energia y Salud";
	}

	/**
	 * Método que implementa la habilidad Bola de Fuego. El personaje gasta energía para atacar según los puntos de magia (dependen de la inteligencia). 
	 * Si el daño es mayor a cero, devuelve verdadero.
	 * Si la energía es menor a diez o el daño es menor o igual a cero, devuelve falso. 
	 * 
	 * @param caster Personaje que va a atacar
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return        true si el Personaje tiene energía mayor a diez y el daño causado es mayor a cero;
	 *                false en caso contrario.
	 */
	// Bola de Fuego
	public boolean habilidad1(Personaje caster, Peleable atacado) {
		if (caster.getEnergia() > 10) {
			caster.setEnergia(caster.getEnergia() - 10);
			if (atacado.serAtacado((int) (caster.calcularPuntosDeMagia() * 1.5)) > 0)
				return true;
		}
		return false;
	}

	/**
	 * Método que implementa la habilidad Curar Aliado. El personaje gasta energía para curar a un personaje aliado según sus puntos de magia.
	 * Si el aliado no es un Personaje o si la energía es menor o igual a diez, no cura al aliado y devuelve falso.
	 * 
	 * @param caster Personaje que va a curar
	 * @param aliado un objeto que implementa la interfaz Peleable, es aquel a ser curado
	 * @return       true si caster tiene energía mayor a diez y el aliado es un Personaje;
	 *               false en caso contrario.
	 */
	// Curar Aliado
	public boolean habilidad2(Personaje caster, Peleable aliado) {
		if (caster.getEnergia() > 10) {
			caster.setEnergia(caster.getEnergia() - 10);
			if (aliado instanceof Personaje) {
				((Personaje) aliado).serCurado(caster.calcularPuntosDeMagia());
				return true;
			}
		}
		return false;
	}

	/**
	 * Método que implementa la habilidad Robar Energía y Salud. El personaje gasta energía para reducir la energía y la salud del personaje atacado según sus puntos de magia.
	 * La energía y la salud quitada, la toma el Hechicero. 
	 * Si la energía es menor o igual a cero o si el atacado no es un personaje, no roba energía y salud y devuelve falso.
	 * 
	 * @param caster Personaje que va a robar energía y salud
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return        true en caso de que caster tenga energía mayor a diez y el atacado es Personaje;
	 *                false en caso contrario.
	 */
	// Robar Energia y Salud
	public boolean habilidad3(Personaje caster, Peleable atacado) {
		if (caster.getEnergia() > 10) {
			caster.setEnergia(caster.getEnergia() - 10);
			if (atacado instanceof Personaje) {
				int energia_robada = ((Personaje) atacado).serDesernegizado(caster.calcularPuntosDeMagia());
				int salud_robada = ((Personaje) atacado).serRobadoSalud(caster.calcularPuntosDeMagia() / 2);
				caster.serEnergizado(energia_robada);
				caster.serCurado(salud_robada);
				return true;
			}

		}
		return false;
	}
}
