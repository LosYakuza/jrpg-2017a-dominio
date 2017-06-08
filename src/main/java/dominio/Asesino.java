package dominio;

/**
 * La clase Asesino es una Casta que implementa las habilidades Golpe Crítico, Aumentar Evasión y Robar.
 */
public class Asesino extends Casta {
	public static final int CANT_HABILIDADES = 3;
	public static final int DESTREZA_ASESINO = 5;
	public static final int ENERGIA_MINIMA_PARA_HABILIDAD = 10;
	public static final double PROB_EVITAR_DANIO_ADICIONAL = 0.15;
	public static final double PROB_EVITAR_DANIO_MAX = 0.5;

	/**
	 * Constructor de la clase Asesino.
	 * @param probCrit probabilidad de golpe crítico
	 * @param evasion probabilidad de evitar daño.
	 * @param danioCrit daño crítico.
	 */
	public Asesino(final double probCrit, final double evasion, final double danioCrit) {
		super(probCrit, evasion, danioCrit);
		this.nombreCasta = "Asesino";
	}

	/**
	 * Constructor de la clase Asesino con habilidades de su casta y valores por defecto.
	 */
	public Asesino() {
		super();
		this.nombreCasta = "Asesino";
		habilidadesCasta = new String[CANT_HABILIDADES];
		habilidadesCasta[0] = "Golpe Critico";
		habilidadesCasta[1] = "Aumentar Evasion";
		habilidadesCasta[2] = "Robar";
	}

	/**
	 * Se implementa la habilidad Golpe Crítico.
	 * El personaje gasta energía para atacar con la potencia de un golpe crítico.
	 * @param caster Personaje que va a atacar.
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado.
	 * @return        true si caster tiene energía mayor a diez y el daño causado es mayor a cero;
	 *                false en caso contrario.
	 */
	public boolean habilidad1(final Personaje caster, final Peleable atacado) {
		if (caster.getEnergia() > ENERGIA_MINIMA_PARA_HABILIDAD) {
			caster.setEnergia(caster.getEnergia() - ENERGIA_MINIMA_PARA_HABILIDAD);
			if (atacado.serAtacado((int) (caster.ataque * caster.getCasta().getDanioCritico())) > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Se implementa la habilidad Aumentar Evasión.
	 * El personaje gasta energía para aumentar su probabilidad de evitar el daño (tiene un límite de 0.5)
	 * @param caster Personaje que va a atacar
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return        true si caster tiene energía mayor a diez y el daño causado es mayor a cero;
	 *                false en caso contrario.
	 */
	public boolean habilidad2(final Personaje caster, final Peleable atacado) {
		if (caster.getEnergia() > ENERGIA_MINIMA_PARA_HABILIDAD) {
			caster.setEnergia(caster.getEnergia() - ENERGIA_MINIMA_PARA_HABILIDAD);
			if (this.getProbabilidadEvitarDanio() + PROB_EVITAR_DANIO_ADICIONAL < PROB_EVITAR_DANIO_MAX) {
				this.probabilidadEvitarDaño += PROB_EVITAR_DANIO_ADICIONAL;
			} else {
				this.probabilidadEvitarDaño = PROB_EVITAR_DANIO_MAX;
			}
			return true;
		}
		return false;
	}

	/**
	 * Se implementa la habilidad Robar.
	 * @param caster Personaje que va a robar.
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser robado.
	 * @return false.
	 */
	public boolean habilidad3(final Personaje caster, final Peleable atacado) {
		return false;
	}

	@Override
	public int getDestreza() {
		return super.getDestreza() + DESTREZA_ASESINO;
	}
}
