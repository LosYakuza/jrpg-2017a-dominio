package dominio;

/**
 * La clase Guerrero es una Casta que posee como habilidades Ataque Doble, Aumentar Defensa e Ignorar Defensa.
 */
public class Guerrero extends Casta {

	public Guerrero(double prob_crit, double evasion, double daño_crit) {
		super(prob_crit, evasion, daño_crit);
		this.nombreCasta = "Guerrero";
	}

	public Guerrero() {
		super();
		this.nombreCasta = "Guerrero";

		habilidadesCasta = new String[3];
		habilidadesCasta[0] = "Ataque Doble";
		habilidadesCasta[1] = "Aumentar Defensa";
		habilidadesCasta[2] = "Ignorar Defensa";
	}

	/**
	 * Método que implementa la habilidad Ataque Doble. El personaje gasta energía para atacar con el doble de su propio ataque.
	 * Si el daño causado es mayor a cero, devuelve verdadero.
	 * Si la energía es menor o igual a diez, no ataca y devuelve falso.
	 * 
	 * @param caster Personaje que va a atacar
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return        true si caster tiene energía mayor a diez y el daño causado es mayor a cero;
	 *                false en caso contrario. 
	 */
	// Ataque Doble
	public boolean habilidad1(Personaje caster, Peleable atacado) { 
		if (caster.getEnergia() > 10) {
			caster.setEnergia(caster.getEnergia() - 10);
			if (atacado.serAtacado(caster.ataque * 2) > 0)
				return true;
		}
		return false;
	}
	
	/**
	 * Método que implementa la habilidad Aumentar Defensa. El personaje gasta energía para aumentar la defensa según su magia.
	 * Si la energía es menor o igual a diez, no sube su defensa y devuelve falso.
	 * 
	 * @param caster Personaje que va a aumentar su defensa
	 * @param atacado un objeto que implementa la interfaz Peleable, no es utilizado en este método (podría ser nulo)
	 * @return        true si caster tiene energía mayor a diez;
	 *                false en caso contrario.
	 */
	// Aumentar Defensa
	public boolean habilidad2(Personaje caster, Peleable atacado) { 
		if (caster.getEnergia() > 10) {
			caster.setEnergia(caster.getEnergia() - 10);
			caster.setDefensa(caster.getDefensa() + caster.magia);
			return true;
		}
		return false;
	}

	/**
	 * Método que implementa la habilidad Ignorar Defensa. El personaje gasta energía para atacar sin tener en cuenta la defensa del atacado (pasa a ser cero durante el ataque).
	 * Si el atacado es un Personaje y y el daño es mayor a cero, devuelve verdadero.
	 * Si la energía es menor o igual a diez, si el atacado no es un Personaje o si el daño es menor o igual a cero, devuelve falso.
	 * 
	 * @param caster Personaje que va a atacar
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return        true si caster tiene energía mayor a diez, el atacado es un Personaje y el daño causado es mayor a cero;
	 *                false en caso contrario.
	 */
	// Ignorar Defensa
	public boolean habilidad3(Personaje caster, Peleable atacado) { 
		if (caster.getEnergia() > 10) {
			caster.setEnergia(caster.getEnergia() - 10);
			if (atacado instanceof Personaje) {
				int defensa_original = ((Personaje) atacado).getDefensa();
				((Personaje) atacado).setDefensa(0);
				if (atacado.serAtacado(caster.ataque) > 0) {
					((Personaje) atacado).setDefensa(defensa_original);
					return true;
				}
			}

		}
		return false;
	}
}
