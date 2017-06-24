package dominio;

/**
 * Items a ser equipados por un Personaje (jugable o no).
 */
public class Item {
	private int idItem;
	private String nombre;
	private ModificadorSegunItem modSalud;
	private ModificadorSegunItem modFuerza;
	private ModificadorSegunItem modDestreza;
	private ModificadorSegunItem modInteligencia;
	private ModificadorSegunItem modEnergia;

	public static String[] nameItems = {"Amuleto1", "Amuleto2", "Anillo1", "Anillo2", "Anillo3",
			"Piedra", "Cuchi", "Ojo", "Metalit", "Papiro"};

	/**
	 * Constructor Item.
	 * @param rnd RandomGenerator
	 */
	public Item(final RandomGenerator rnd) {
		modSalud = new ModificadorSegunItem(rnd, Operacion.randomOperacion(rnd));
		modFuerza = new ModificadorSegunItem(rnd, Operacion.randomOperacion(rnd));
		modDestreza = new ModificadorSegunItem(rnd, Operacion.randomOperacion(rnd));
		modInteligencia = new ModificadorSegunItem(rnd, Operacion.randomOperacion(rnd));
		modEnergia = new ModificadorSegunItem(rnd, Operacion.randomOperacion(rnd));

		nombre = nameItems[rnd.nextInt(nameItems.length-1)];
	}

	/**
	 * Constructor Item.
	 * @param id id del item
	 * @param nombre nombre del item
	 * @param modifSalud modificador de salud
	 * @param modifFuerza modificador de fuerza
	 * @param modifDestreza modificador de destreza
	 * @param modifInteligencia modificador de inteligencia
	 * @param modifEnergia modificador de energía
	 */
	public Item(final int id, final String nombre,
			final ModificadorSegunItem modifSalud, final ModificadorSegunItem modifFuerza,
			final ModificadorSegunItem modifDestreza, final ModificadorSegunItem modifInteligencia,
			final ModificadorSegunItem modifEnergia) {
		this.idItem = id;
		this.nombre = nombre;
		this.modSalud = modifSalud;
		this.modFuerza = modifFuerza;
		this.modDestreza = modifDestreza;
		this.modInteligencia = modifInteligencia;
		this.modEnergia = modifEnergia;
	}

	/**
	 * Devuelve el valor a ser sumado a la fuerza.
	 * @param fuerzaOriginal fuerza del Peleador
	 * @return fuerza extra
	 */
	public int getModifFuerza(final int fuerzaOriginal) {
		return modFuerza.getValor(fuerzaOriginal) - fuerzaOriginal;
	}

	/**
	 * Devuelve el valor a ser sumado a la destreza.
	 * @param destrezaOriginal destreza del Peleador
	 * @return destreza extra
	 */
	public int getModifDestreza(final int destrezaOriginal) {
		return modDestreza.getValor(destrezaOriginal) - destrezaOriginal;
	}

	/**
	 * Devuelve el valor a ser sumado a la inteligencia.
	 * @param inteligenciaOriginal inteligencia del Peleador
	 * @return inteligencia extra
	 */
	public int getModifInteligencia(final int inteligenciaOriginal) {
		return modInteligencia.getValor(inteligenciaOriginal) - inteligenciaOriginal;
	}

	/**
	 * Devuelve el valor a ser sumado a la energia.
	 * @param energiaOriginal energia del Peleador
	 * @return energia extra
	 */
	public int getModifEnergia(final int energiaOriginal) {
		return modEnergia.getValor(energiaOriginal) - energiaOriginal;
	}

	/**
	 * Devuelve el valor a ser sumado a la salud.
	 * @param saludOriginal salud del Peleador
	 * @return salud extra
	 */
	public int getModifSalud(final int saludOriginal) {
		return modSalud.getValor(saludOriginal) - saludOriginal;
	}

	/**
	 * Devuelve el id del item.
	 * @return id
	 */
	public int getIdItem() {
		return idItem;
	}

	/**
	 * Setea el id del item.
	 * @param idItem id
	 */
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public ModificadorSegunItem getModSalud() {
		return modSalud;
	}

	public ModificadorSegunItem getModFuerza() {
		return modFuerza;
	}

	public ModificadorSegunItem getModDestreza() {
		return modDestreza;
	}

	public ModificadorSegunItem getModInteligencia() {
		return modInteligencia;
	}

	public ModificadorSegunItem getModEnergia() {
		return modEnergia;
	}

	/**
	 * Getter nombre
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
}
