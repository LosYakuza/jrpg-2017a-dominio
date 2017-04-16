package dominio;

/**
 * La clase Orco es un Personaje que posee dos habilidades de raza Golpe Defensa y Mordisco de Vida.
 */
public class Orco extends Personaje {

	public Orco(String nombre, Casta casta, int id) {
		super(nombre, casta, id);
		saludTope += 10;
		salud = saludTope;
		energia = energiaTope;
		nombreRaza = "Orco";

		habilidadesRaza = new String[2];
		habilidadesRaza[0] = "Golpe Defensa";
		habilidadesRaza[1] = "Mordisco de Vida";
	}

	public Orco(String nombre, int salud, int energia, int fuerza, int destreza, int inteligencia, Casta casta,
			int experiencia, int nivel, int idPersonaje) {
		super(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, idPersonaje);
		nombreRaza = "Orco";

		habilidadesRaza = new String[2];
		habilidadesRaza[0] = "Golpe Defensa";
		habilidadesRaza[1] = "Mordisco de Vida";
	}

	/**
	 * El método implementa la habilidad Golpe Defensa. La misma gasta energía para atacar con el doble de su defensa.
	 * Si el daño causado es mayor a cero, devuelve verdadero.
	 * 
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return        true si el Orco tiene energía mayor a diez y el daño causado es mayor a cero;
	 *                false en caso contrario.
	 */
	// Golpe Defensa
	public boolean habilidadRaza1(Peleable atacado) {
		if (this.getEnergia() > 10) {
			this.setEnergia(this.getEnergia() - 10);
			if (atacado.serAtacado(this.getDefensa() * 2) > 0)
				return true;
		}
		return false;
	}

	/**
	 * El método implementa la habilidad Mordisco de Vida. La misma gasta energía para atacar con la fuerza del personaje.
	 * Si el daño causado es mayor a cero, el personaje recibe el mismo número en salud y devuelve verdadero.
	 * 
	 * @param atacado un objeto que implementa la inferfaz Peleable, es aquel a ser atacado
	 * @return        true si el Orco tiene energía mayor a diez y el daño causado es mayor a cero;
	 *                false en caso contrario.
	 */
	// Mordisco de Vida
	public boolean habilidadRaza2(Peleable atacado) {
		if (this.getEnergia() > 10) {
			this.setEnergia(this.getEnergia() - 10);
			int daño_causado = atacado.serAtacado(this.getFuerza());
			if (daño_causado > 0) {
				this.serCurado(daño_causado);
				return true;
			}
		}
		return false;
	}
}
