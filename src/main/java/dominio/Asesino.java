package dominio;

/**
 * La clase Asesino es una Casta que implementa las habilidades Golpe Crítico, Aumentar Evasión y Robar.
 */
public class Asesino extends Casta {

	public Asesino(double prob_crit, double evasion, double daño_crit) {
		super(prob_crit, evasion, daño_crit);
		this.nombreCasta = "Asesino";
	}

	public Asesino() {
		super();
		this.nombreCasta = "Asesino";
		habilidadesCasta = new String[3];
		habilidadesCasta[0] = "Golpe Critico";
		habilidadesCasta[1] = "Aumentar Evasion";
		habilidadesCasta[2] = "Robar";
	}

	/**
	 * Se implementa la habilidad Golpe Crítico.
	 * El personaje gasta energía para atacar con la potencia de un golpe crítico.
	 *
	 * @param caster Personaje que va a atacar
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return        true si caster tiene energía mayor a diez y el daño causado es mayor a cero;
	 *                false en caso contrario.
	 */
	public boolean habilidad1(final Personaje caster, final Peleable atacado) {
		if (caster.getEnergia() > 10) {
			caster.setEnergia(caster.getEnergia() - 10);
			if (atacado.serAtacado((int) (caster.ataque * caster.getCasta().getDañoCritico())) > 0)
				return true;
		}
		return false;
	}

	/**
	 * Se implementa la habilidad Aumentar Evasión.
	 * El personaje gasta energía para aumentar su probabilidad de evitar el daño (tiene un límite de 0.5)
	 *
	 * @param caster Personaje que va a atacar
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return        true si caster tiene energía mayor a diez y el daño causado es mayor a cero;
	 *                false en caso contrario.
	 */
	public boolean habilidad2(final Personaje caster, final Peleable atacado) {
		if (caster.getEnergia() > 10) {
			caster.setEnergia(caster.getEnergia() - 10);
			if (this.getProbabilidadEvitarDaño() + 0.15 < 0.5) {
				this.probabilidadEvitarDaño += 0.15;
			} else {
				this.probabilidadEvitarDaño = 0.5;
			}
			return true;
		}
		return false;
	}

	// Robar
	public boolean habilidad3(final Personaje caster, final Peleable atacado) {
		return false;
	}
}
