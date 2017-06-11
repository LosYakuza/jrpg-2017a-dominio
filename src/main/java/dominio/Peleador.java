package dominio;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Clase de todos los peleadores
 *
 */
public abstract class Peleador implements Peleable {

	/**
	 * Atributos HASH
	 */
	public static final String ATTR_SALUD = "salud";
	public static final String ATTR_FUERZA = "fuerza";
	public static final String ATTR_DEFENSA = "defensa";
	public static final String ATTR_NOMBRE = "nombre";
	public static final String ATTR_NIVEL = "nivel";

	private int salud;
	private int fuerza;
	private int defensa;
	private String nombre;
	private int nivel;
	private LinkedList<Item> inventario;
	private RandomGenerator rnd;

	/**
	 * Actualizar datos desde hashmap
	 * @param datos salud,fuerza,defensa,nombre,nivel
	 */
	public void actualizar(final HashMap<String, Object> datos) {
		setSalud((Integer) datos.get(ATTR_SALUD));
		setFuerza((Integer) datos.get(ATTR_FUERZA));
		setDefensa((Integer) datos.get(ATTR_DEFENSA));
		setNombre((String) datos.get(ATTR_NOMBRE));
		setNivel((Integer) datos.get(ATTR_NIVEL));
	}

	/**
	 * Devuelve hashmap con datos.
	 * @return datos en hashmap
	 */
	public HashMap<String, Object> getTodo() {
		HashMap<String, Object> datos = new HashMap<>();
		datos.put(ATTR_SALUD, getSalud());
		datos.put(ATTR_FUERZA, getFuerza());
		datos.put(ATTR_DEFENSA, getDefensa());
		datos.put(ATTR_NOMBRE, getNombre());
		datos.put(ATTR_NIVEL, getNivel());
		return datos;
	}

	/**
	 * Constructor por defecto
	 * Carga myRandom.
	 * @param inventario lista de ítems del peleador
	 */
	public Peleador(final LinkedList<Item> inventario) {
		setRandomGenerator(new MyRandom());
		setInventario(inventario);
	}

	/**
	 * Carga el generador de aleatorios.
	 * @param rg generador
	 */
	public void setRandomGenerator(final RandomGenerator rg) {
		this.rnd = rg;
	}

	/**
	 * Devuelve generador random en uso.
	 * @return randomgenerator
	 */
	public RandomGenerator getRandomGenerator() {
		return this.rnd;
	}

	/**
	 * Getter salud.
	 * @return salud
	 */
	public int getSalud() {
		return salud;
	}

	/**
	 * Setter Salud.
	 * @param salud salud
	 */
	protected void setSalud(final int salud) {
		this.salud = salud;
	}

	/**
	 * Getter Fuerza.
	 * @return fuerza
	 */
	public int getFuerza() {
		return fuerza;
	}

	/**
	 * Setter fuerza.
	 * @param fuerza fuerza
	 */
	protected void setFuerza(final int fuerza) {
		this.fuerza = fuerza;
	}

	/**
	 * Getter defensa.
	 * @return defensa
	 */
	public int getDefensa() {
		return defensa;
	}

	/**
	 * Setter defenza.
	 * @param defensa defensa
	 */
	protected void setDefensa(final int defensa) {
		this.defensa = defensa;
	}

	/**
	 * Getter nombre.
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter nombre.
	 * @param nombre nombre
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter nivel.
	 * @return nivel
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * Setter nivel.
	 * @param nivel nivel
	 */
	protected void setNivel(final int nivel) {
		this.nivel = nivel;
	}

	/**
	 * Getter inventario.
	 * @return lista de items.
	 */
	public LinkedList<Item> getInventario() {
		return this.inventario;
	}

	/**
	 * Setter inventario.
	 * @param inventario lista de items
	 */
	public void setInventario(final LinkedList<Item> inventario) {
		this.inventario = inventario;
	}

	/**
	 * Esta vivo.
	 * @return true si esta vivo
	 */
	public boolean estaVivo() {
		return salud > 0;
	}

	/**
	 * Accion despues de turno.
	 */
	public void despuesDeTurno() {
	}

	/**
	 * Da experiencia al personaje.
	 * @return nivel multiplicado
	 */
	public int otorgarExp() {
		return this.nivel * multiplicadorExperiencia();
	}

	/**
	 * Calcula el multiplicador de experiencia para otorgar EXP.
	 * @return multiplicador
	 */
	protected abstract int multiplicadorExperiencia();

	/**
	 * Recibir daño evaluando probabilidad de evasión y defensa
	 *
	 * @param dano daño inicial que el atacado sufrirá, puede verse modificado
	 * @return daño sufrido, es equivalente a cuánto disminuyó su salud
	 */
	public int serAtacado(final int dano) {
		int danoCalc = dano;
		//addBonusSegunItems();
		if (rnd.nextDouble() >= probabilidadEvitarDanoEnAtaque()) {
			danoCalc -= defensaAlSerAtacado();
			danoCalc = quitarVidaSegunDano(danoCalc);
		} else {
			danoCalc = 0;
		}
		//removeBonusSegunItems();
		return danoCalc;
	}

	/**
	 * Modifica los atributos del Peleador según los ítems que lleve equipado.
	 */
	public void addBonusSegunItems() {
		int acumSalud = 0;
		int acumFuerza = 0;

		for (Item item : inventario) {
			acumSalud += item.getModifSalud(salud);
			acumFuerza += item.getModifFuerza(fuerza);
		}

		salud += acumSalud;
		fuerza += acumFuerza;
	}

	/**
	 * Remueve los modificadores de los ítems.
	 * Vuelve los atributos del Peleador a su estado original.
	 */
	public void removeBonusSegunItems() {
		int acumSalud = 0;
		int acumFuerza = 0;

		for (Item item : inventario) {
			acumSalud += item.getModifSalud(salud);
			acumFuerza += item.getModifFuerza(fuerza);
		}

		salud -= acumSalud;
		fuerza -= acumFuerza;
	}

	/**
	 * Cacula probabilidad de evitar daño.
	 * @return probabilidad resultante.
	 */
	protected abstract double probabilidadEvitarDanoEnAtaque();

	/**
	 * Calcula defensa al ser atacado por otro peleador
	 * @return defensa resultante
	 */
	protected abstract int defensaAlSerAtacado();

	/**
	 * Disminuye la salud según el daño.
	 * @param dano daño a quitar al personaje
	 * @return     devuelve el daño causado si es mayor a cero;
	 *             caso contrario devuelve 0.
	 */
	protected int quitarVidaSegunDano(final int dano) {
		if (dano > 0) {
			salud -= dano;
			return dano;
		} else {
			return 0;
		}
	};

	/**
	 * Causar daño al atacado evaluando la probabilidad de golpe crítico y su ataque.
	 *
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado.
	 * @return daño causado.
	 */
	public int atacar(final Peleable atacado) {
		if (puedoAtacar(atacado.estaVivo())) {
			if (rnd.nextDouble() <= probabilidadGolpeCritico()) {
				return atacado.serAtacado(this.golpeCritico());
			} else {
				return atacado.serAtacado(getAtaque());
			}
		}
		return 0;
	}

	/**
	 * Daño de golpe critico.
	 * @return impacto de golpe critico
	 */
	protected abstract int golpeCritico();

	/**
	 * Calcula probabilida de efectuar un golpe critico.
	 * @return probabilidad de daño
	 */
	protected abstract double probabilidadGolpeCritico();

	/**
	 * Verifica si puede atacar.
	 * @param atacadoEstaVivo atacado es peleador vivo
	 * @return true si puede atacar
	 */
	protected boolean puedoAtacar(final boolean atacadoEstaVivo) {
		return true;
	}

	/**
	 * Indica si es afectado por un hechicero.
	 * @return true si es afectado;
	 *         false en caso contrario.
	 */
	public abstract boolean esAfectadoPorHechicero();

	/**
	 * Indica si es afectado por un guerrero.
	 * @return true si es afectado;
	 *         false en caso contrario.
	 */
	public abstract boolean esAfectadoPorGuerrero();

	/**
	 * Guarda el item en el inventario.
	 * @param item a guardar
	 */
	public void guardarItemEnInventario(final Item item) {
		this.inventario.add(item);
	}
}
