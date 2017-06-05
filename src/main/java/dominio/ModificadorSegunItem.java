package dominio;

/**
 * Clase para el manejo de operaciones.
 */
public class ModificadorSegunItem {
	private final int maxValorSuma = 50;
	private final int maxValorPorcentaje = 70;
	private final int maxValorMultiplicacion = 3;

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

}
