package dominio;

import java.io.Serializable;

/**
 * La clase Casta posee los atributos necesarios para acceder al daño crítico y a las probabilidades de esquivar ataques y de hacer un golpe crítico.
 */

public abstract class Casta implements Serializable {
	protected double probabilidadGolpeCritico;
	protected double probabilidadEvitarDaño;
	protected double dañoCritico;
	protected String nombreCasta;

	protected String[] habilidadesCasta;

	/**
	 * Constructor con valores defecto de la clase Casta
	 */
	public Casta() {
		this.probabilidadGolpeCritico = 0.2;
		this.probabilidadEvitarDaño = 0.2;
		this.dañoCritico = 1.5;
	}

	/**
	 * Constructor de la clase Casta. Se asignan los valores pasados por parámetros a su atributo correspondiente.
	 * 
	 * @param prob_crit probabilidad de golpe crítico.
	 * @param evasion probabilidad de evitar daño.
	 * @param daño_crit daño crítico.
	 */
	public Casta(final double prob_crit, final double evasion, final double daño_crit) {
		this.probabilidadGolpeCritico = prob_crit;
		this.probabilidadEvitarDaño = evasion;
		this.dañoCritico = daño_crit;
	}

	public abstract boolean habilidad1(Personaje caster, Peleable atacado);

	public abstract boolean habilidad2(Personaje caster, Peleable atacado);

	public abstract boolean habilidad3(Personaje caster, Peleable atacado);

	public String getNombreCasta() {
		return this.nombreCasta;
	}

	public String[] getHabilidadesCasta() {
		return habilidadesCasta;
	}

	public double getProbabilidadGolpeCritico() {
		return probabilidadGolpeCritico;
	}

	public void setProbabilidadGolpeCritico(double probabilidadGolpeCritico) {
		this.probabilidadGolpeCritico = probabilidadGolpeCritico;
	}

	public double getProbabilidadEvitarDaño() {
		return probabilidadEvitarDaño;
	}

	public void setProbabilidadEvitarDaño(double probabilidadEvitarDaño) {
		this.probabilidadEvitarDaño = probabilidadEvitarDaño;
	}

	public double getDañoCritico() {
		return dañoCritico;
	}

	public void setDañoCritico(double dañoCritico) {
		this.dañoCritico = dañoCritico;
	}

	public int fuerzaSegunCasta() {
		return 0;
	}

	public int inteligenciaSegunCasta() {
		return 0;
	}

	public int destrezaSegunCasta() {
		return 0;
	}
}
