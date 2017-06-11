package dominio;

import java.util.LinkedList;

/**
 * La clase Orco es un Personaje que posee dos habilidades de raza Golpe Defensa y Mordisco de Vida.
 */
public class Orco extends Personaje {
	private static final int ADICIONAL_SALUD_TOPE_ORCO = 10;
	private static final int ENERGIA_MINIMA_PARA_HABILIDAD = 10;

	/**
	 * Constructor de la clase Orco.
	 * @param nombre nombre del orco.
	 * @param casta casta del orco.
	 * @param id id del orco.
	 * @param inventario inventario del orco.
	 */
	public Orco(final String nombre, final Casta casta, final int id, final LinkedList<Item> inventario) {
		super(nombre, casta, id, inventario);
	}

	/**
	 * Constructor de la clase Orco.
	 * @param nombre nombre del orco.
	 * @param salud salud del orco.
	 * @param energia energia del orco.
	 * @param fuerza fuerza del orco.
	 * @param destreza destreza del orco.
	 * @param inteligencia inteligencia del orco.
	 * @param casta casta del orco.
	 * @param experiencia experiencia del orco.
	 * @param nivel nivel del orco.
	 * @param idPersonaje id del orco.
	 * @param inventario inventario del orco.
	 */
	public Orco(final String nombre, final int salud, final int energia, final int fuerza,
			final int destreza, final int inteligencia, final Casta casta, final int experiencia,
			final int nivel, final int idPersonaje, final LinkedList<Item> inventario) {
		super(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel,
				idPersonaje, inventario);
	}

	@Override
	protected String nombreRazaInicial() {
		return "Orco";
	}

	@Override
	protected void inicializarHabilidadesSegunRaza() {
		habilidadesRaza = new String[2];
		habilidadesRaza[0] = "Golpe Defensa";
		habilidadesRaza[1] = "Mordisco de Vida";
	}

	@Override
	protected int saludTopeInicial() {
		return super.saludTopeInicial() + ADICIONAL_SALUD_TOPE_ORCO;
	}

	/**
	 * Se implementa la habilidad Golpe Defensa.
	 * El personaje gasta energía para atacar con el doble de su defensa.
	 *
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado
	 * @return        true si el Orco tiene energía mayor a diez y el daño causado es mayor a cero;
	 *                false en caso contrario.
	 */
	public boolean habilidadRaza1(final Peleable atacado) {
		if (this.getEnergia() > ENERGIA_MINIMA_PARA_HABILIDAD) {
			this.setEnergia(this.getEnergia() - ENERGIA_MINIMA_PARA_HABILIDAD);
			if (atacado.serAtacado(this.getDefensa() * 2) > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Se implementa la habilidad Mordisco de Vida.
	 * El personaje gasta energía para atacar con su fuerza. Adicionalmente,
	 * recibe de salud el mismo valor que el daño causado.
	 *
	 * @param atacado un objeto que implementa la inferfaz Peleable, es aquel a ser atacado
	 * @return        true si el Orco tiene energía mayor a diez y el daño causado es mayor a cero;
	 *                false en caso contrario.
	 */
	public boolean habilidadRaza2(final Peleable atacado) {
		if (this.getEnergia() > ENERGIA_MINIMA_PARA_HABILIDAD) {
			this.setEnergia(this.getEnergia() - ENERGIA_MINIMA_PARA_HABILIDAD);
			int danioCausado = atacado.serAtacado(this.getFuerza());
			if (danioCausado > 0) {
				this.serCurado(danioCausado);
				return true;
			}
		}
		return false;
	}
}
