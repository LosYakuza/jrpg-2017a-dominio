package dominio;

/**
 * Enum para determinar si la operación a realizar debe ser porcentual, suma o
 * multiplicación.
 */
public enum Operacion {
	SUMA, PORCENTAJE, MULTIPLICACION;

	private static final int CANT_OPERACIONES = 3;

	@Override
	public String toString() {
		// podria overridear para que devuelve x, +, % y ver si se puede usar gráficamente
		return super.toString();
	}

	/**
	 * Hace la operación.
	 * @param a primer valor
	 * @param b segundo valor
	 * @return resultado
	 */
	public int hacer(final int a, final int b) {
		int result = 0;

		switch (this) {
		case SUMA:
			result = a + b;
			break;
		case PORCENTAJE:
			result = (int) (a * ((double) b / 100 + 1));
			break;
		case MULTIPLICACION:
			result = a * b;
			break;
		default:
			result = 0;
			break;
		}
		return result;
	}

	/**
	 * Devuelve una operación random.
	 * @param rnd RandomGenerator usado
	 * @return Operacion
	 */
	public static Operacion randomOperacion(final RandomGenerator rnd)  {
		return values()[rnd.nextInt(CANT_OPERACIONES)];
	}

	/**
	 * Devuelve el enum operación según el id en la base de datos.
	 * @param id id según base de datos
	 * @return enum operación
	 */
	public static Operacion operacionSegunId(final int id) {
		switch (id) {
		case 1:
			return Operacion.SUMA;
		case 2:
			return Operacion.PORCENTAJE;
		case 3:
			return Operacion.MULTIPLICACION;
		default:
			return Operacion.SUMA; //Sino hay que hacer throw e irlo manejando.
		}
	}
}
