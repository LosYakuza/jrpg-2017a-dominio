package dominio;

import java.io.Serializable;

/**
 * La clase Casta posee los atributos necesarios para acceder al daño crítico
 * y a las probabilidades de esquivar ataques y de hacer un golpe crítico.
 */

public abstract class Casta implements Serializable {
	private final double probabilidadPorDefecto = 0.2;
	private final double golpeCriticoPorDefecto = 1.5;

	protected double probabilidadGolpeCritico;
	protected double probabilidadEvitarDaño;
	protected double dañoCritico;
	protected String nombreCasta;

	protected String[] habilidadesCasta;

	/**
	 * Constructor con valores defecto de la clase Casta
	 */
	public Casta() {
		this.probabilidadGolpeCritico = probabilidadPorDefecto;
		this.probabilidadEvitarDaño = probabilidadPorDefecto;
		this.dañoCritico = golpeCriticoPorDefecto;
	}

	/**
	 * Constructor de la clase Casta. Se asignan los valores pasados por parámetros a su atributo correspondiente.
	 * @param probCrit probabilidad de golpe crítico.
	 * @param evasion probabilidad de evitar daño.
	 * @param danioCrit daño crítico.
	 */
	public Casta(final double probCrit, final double evasion, final double danioCrit) {
		this.probabilidadGolpeCritico = probCrit;
		this.probabilidadEvitarDaño = evasion;
		this.dañoCritico = danioCrit;
	}

	/**
	 * La habilidad 1 va a ser implementada por cada Casta.
	 * @param caster Personaje que va reallizar la habilidad.
	 * @param atacado un objeto que implementa la interfaz Peleable, va a recibir la habilidad.
	 * @return true o false dependiendo de la implementacion.
	 */
	public abstract boolean habilidad1(Personaje caster, Peleable atacado);

	/**
	 * La habilidad 2 va a ser implementada por cada Casta.
	 * @param caster Personaje que va reallizar la habilidad.
	 * @param atacado un objeto que implementa la interfaz Peleable, va a recibir la habilidad.
	 * @return true o false dependiendo de la implementacion.
	 */
	public abstract boolean habilidad2(Personaje caster, Peleable atacado);

	/**
	 * La habilidad 3 va a ser implementada por cada Casta.
	 * @param caster Personaje que va reallizar la habilidad.
	 * @param atacado un objeto que implementa la interfaz Peleable, va a recibir la habilidad.
	 * @return true o false dependiendo de la implementacion.
	 */
	public abstract boolean habilidad3(Personaje caster, Peleable atacado);

	/**
	 * Devuelve el nombre de la Casta.
	 * @return nombreCasta nombre de la casta.
	 */
	public String getNombreCasta() {
		return this.nombreCasta;
	}

	/**
	 * Devuelve las habilidades de la Casta.
	 * @return habilidadesCasta habilidades de la casta.
	 */
	public String[] getHabilidadesCasta() {
		return habilidadesCasta;
	}

	/**
	 * Devuelve la probabilidad de golpe crítico.
	 * @return probabilidadGolpeCritico.
	 */
	public double getProbabilidadGolpeCritico() {
		return probabilidadGolpeCritico;
	}

	/**
	 * Carga en el atributo probabilidadGolpeCritico un double correspondiente al parametro.
	 * @param probabilidadGolpeCritico double con la probabilidad de golpe critico.
	 */
	public void setProbabilidadGolpeCritico(final double probabilidadGolpeCritico) {
		this.probabilidadGolpeCritico = probabilidadGolpeCritico;
	}

	/**
	 * Devuelve la probabilidad de evitar daño.
	 * @return probabilidadEvitarDaño probabilidad de evitar daño.
	 */
	public double getProbabilidadEvitarDaño() {
		return probabilidadEvitarDaño;
	}

	/**
	 * Carga en el atributo probabilidadEvitarDaño un double correspondiente al parametro.
	 * @param probabilidadEvitarDanio probabilidad de evitar daño.
	 */
	public void setProbabilidadEvitarDaño(final double probabilidadEvitarDanio) {
		this.probabilidadEvitarDaño = probabilidadEvitarDanio;
	}

	/**
	 * Devuelve el daño critico
	 * @return dañoCritico daño critico.
	 */
	public double getDañoCritico() {
		return dañoCritico;
	}

	/**
	 * Carga en el atributo dañoCritico un double correspondiente al parametro.
	 * @param danioCritico daño critico.
	 */
	public void setDañoCritico(final double danioCritico) {
		this.dañoCritico = danioCritico;
	}

	/**
	 * Devuelve la fuerza, es sobreescrito dependiendo la Casta.
	 * @return 0.
	 */
	public int getFuerza() {
		return 0;
	}

	/**
	 * Devuelve la inteligencia, es sobreescrito dependiendo la Casta.
	 * @return 0.
	 */
	public int getInteligencia() {
		return 0;
	}

	/**
	 * Devuelve la destreza, es sobreescrito dependiendo la Casta.
	 * @return 0.
	 */
	public int getDestreza() {
		return 0;
	}
}
