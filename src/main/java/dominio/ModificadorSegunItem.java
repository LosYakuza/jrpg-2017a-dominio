package dominio;

/**
 * Clase para el manejo de operaciones.
 */
public class ModificadorSegunItem {
	private final int maxValorSuma = 5;
	private final int maxValorPorcentaje = 20;
	private final int maxValorMultiplicacion = 2;

	private int valor;
	private Operacion operacion;

	/**
	 * Otorga la modificación que debe aplicarse al atributo según valor y operación.
	 * @param valorOriginal valor original del atributo.
	 * @return valor que se le añadirá al atributo.
	 */
	int getValor(final int valorOriginal) {
		return operacion.hacer(valorOriginal, valor);
	}

	/**
	 * Constructor ModificadorSegunItem.
	 * @param valor modificador
	 * @param operacion operacion a aplicar
	 */
	public ModificadorSegunItem(final int valor, final Operacion operacion) {
		this.valor = valor;
		this.operacion = operacion;
	}

	/**
	 * Constructor que utiliza valores random variando el tope de los valores según la operación.
	 * @param rnd random generator
	 * @param operacion operacion a aplicar
	 */
	public ModificadorSegunItem(final RandomGenerator rnd, final Operacion operacion) {
		switch (operacion) {
		case SUMA:
			this.valor = rnd.nextInt(maxValorSuma);
			break;
		case PORCENTAJE:
			this.valor = rnd.nextInt(maxValorPorcentaje);
			break;
		case MULTIPLICACION:
			this.valor = rnd.nextInt(maxValorMultiplicacion);
			break;
		default:
			break;
		}
		this.operacion = operacion;
	}

	/**
	 * Devuelve el valor del modificador.
	 * @return valor
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * Devuelve el id de la operacion.
	 * @return id
	 */
	public int getIdOperacion() {
		return Operacion.operacionId(this.operacion);
	}
}
