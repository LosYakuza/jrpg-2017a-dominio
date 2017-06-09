package dominio;

/**
 * Enum para determinar si la operación a realizar debe ser porcentual, suma o
 * multiplicación.
 */
public enum Operacion {
	SUMA, PORCENTAJE, MULTIPLICACION;

	private static final int CANT_OPERACIONES = 3;
	private static final int ID_SUMA = 1;
	private static final int ID_PORCENTAJE = 2;
	private static final int ID_MULTIPLICACION = 3;
	

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
		case ID_SUMA:
			return Operacion.SUMA;
		case ID_PORCENTAJE:
			return Operacion.PORCENTAJE;
		case ID_MULTIPLICACION:
			return Operacion.MULTIPLICACION;
		default:
			return Operacion.SUMA; //Sino hay que hacer throw e irlo manejando.
		}
	}

	public static int operacionId(Operacion operacion) {
		switch (operacion) {
		case SUMA:
			return ID_SUMA;
		case PORCENTAJE:
			return ID_PORCENTAJE;
		case MULTIPLICACION:
			return ID_MULTIPLICACION;
		default:
			return 0;
		}
	}
	
}
