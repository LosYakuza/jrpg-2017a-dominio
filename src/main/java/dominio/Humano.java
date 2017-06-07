package dominio;

import java.util.LinkedList;

/**
 * La clase Humano es un Personaje que posee las habilidades Incentivar y Golpe Fatal.
 */

public class Humano extends Personaje {

	public Humano(String nombre, Casta casta, int id, LinkedList<Item> inventario) {
		super(nombre, casta, id, inventario);
	}
	
	public Humano(String nombre, int salud, int energia, int fuerza, int destreza, int inteligencia, Casta casta,
			int experiencia, int nivel, int idPersonaje, LinkedList<Item> inventario) {
		super(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, idPersonaje, inventario);
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
		return super.saludTopeInicial() + 5;
	}
	
	@Override
	protected int energiaTopeInicial() {
		return super.energiaTopeInicial() + 5;
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
		if (this.getEnergia() > 10) {
			this.setEnergia(this.getEnergia() - 10);
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
		if (this.getEnergia() > 10) {
			if (atacado.serAtacado(atacado.getSalud() / 2) > 0) {
				this.setEnergia(this.getEnergia() / 2);
				return true;
			}
		}
		this.setEnergia(this.getEnergia() - 10);
		return false;
	}
}
