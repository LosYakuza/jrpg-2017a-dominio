package dominio;

/**
 * La clase Guerrero es una Casta que posee como habilidades Ataque Doble, Aumentar Defensa e Ignorar Defensa.
 */
public class Guerrero extends Casta {
	private final int cantHabilidades = 3;
	private final int energiaMinimaParaHabilidad = 10;
	private final int fuerzaGuerrero = 5;

	/**
	 * Constructor de la clase Guerrero pasando probabilidad de golpe critico, evasion y daño critico.
	 * @param probCrit probabilidad de golpe critico.
	 * @param evasion probabilidad de evadir golpe.
	 * @param danioCrit daño critico.
	 */
	public Guerrero(final double probCrit, final double evasion, final double danioCrit) {
		super(probCrit, evasion, danioCrit);
		this.nombreCasta = "Guerrero";
	}

	/**
	 * Constructor de la clase Guerrero.
	 */
	public Guerrero() {
		super();
		this.nombreCasta = "Guerrero";

		habilidadesCasta = new String[cantHabilidades];
		habilidadesCasta[0] = "Ataque Doble";
		habilidadesCasta[1] = "Aumentar Defensa";
		habilidadesCasta[2] = "Ignorar Defensa";
	}

	/**
	 * Se implementa la habilidad Ataque Doble.
	 * El personaje gasta energía para atacar con el doble de su propio ataque.
	 *
	 * @param caster Personaje que va a atacar
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return        true si caster tiene energía mayor a diez y el daño causado es mayor a cero;
	 *                false en caso contrario.
	 */
	public boolean habilidad1(final Personaje caster, final Peleable atacado) {
		if (caster.getEnergia() > energiaMinimaParaHabilidad) {
			caster.setEnergia(caster.getEnergia() - energiaMinimaParaHabilidad);
			if (atacado.serAtacado(caster.ataque * 2) > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Se implementa la habilidad Aumentar Defensa.
	 * El personaje gasta energía para aumentar la defensa según su magia.
	 * @param caster Personaje que va a aumentar su defensa
	 * @param atacado un objeto que implementa la interfaz Peleable, no es utilizado en este método(podría ser nulo)
	 * @return        true si caster tiene energía mayor a diez;
	 *                false en caso contrario.
	 */
	public boolean habilidad2(final Personaje caster, final Peleable atacado) {
		if (caster.getEnergia() > energiaMinimaParaHabilidad) {
			caster.setEnergia(caster.getEnergia() - energiaMinimaParaHabilidad);
			caster.setDefensa(caster.getDefensa() + caster.magia);
			return true;
		}
		return false;
	}

	/**
	 * Se implementa la habilidad Ignorar Defensa.
	 * El personaje gasta energía para atacar sin tener en cuenta la defensa del atacado.
	 *
	 * @param caster Personaje que va a atacar
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return	true si caster tiene energía mayor a diez, el atacado es un Personaje
	 * 				y el daño causado es mayor a cero;
	 *          false en caso contrario.
	 */
	public boolean habilidad3(final Personaje caster, final Peleable atacado) {
		if (caster.getEnergia() > energiaMinimaParaHabilidad) {
			caster.setEnergia(caster.getEnergia() - energiaMinimaParaHabilidad);
			if (atacado.esAfectadoPorGuerrero()) {
				int defensaOriginal = ((Personaje) atacado).getDefensa();
				((Personaje) atacado).setDefensa(0);
				if (atacado.serAtacado(caster.ataque) > 0) {
					((Personaje) atacado).setDefensa(defensaOriginal);
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public int getFuerza() {
		return super.getFuerza() + fuerzaGuerrero;
	}
}
