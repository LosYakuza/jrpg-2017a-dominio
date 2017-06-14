package dominio;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * La clase Personaje está desarrollada para que las clases que la hereden
 * posean todos los atributos y métodos necesarios para atacar y ser atacados
 * dependiendo de sus atributos (salud, energía, defensa, etc.), de su casta, de
 * su nivel, experiencia y habilidades.
 */
public abstract class Personaje extends Peleador implements Serializable {

	/**
	 * Atributos HASH
	 */
	public static final String ATTR_ENERGIA = "energia";
	public static final String ATTR_DESTREZA = "destreza";
	public static final String ATTR_INTELIGENCIA = "inteligencia";
	public static final String ATTR_CASTA = "casta";
	public static final String ATTR_EXPERIENCIA = "experiencia";
	public static final String ATTR_IDPERSONAJE = "idpersonaje";
	public static final String ATTR_ENERGIATOPE = "energiatope";
	public static final String ATTR_SALUDTOPE = "saludtope";

	private static final int NIVEL_MAXIMO = 100;
	private static final int AGREGADO_EXPERIENCIA = 50;
	private final int valorPorDefecto = 10;
	private final int valorTopeInicial = 100;
	private final int energiaMinimaParaHabilidad = 10;
	private final double incrementoDePuntos = 1.5;
	private final int valorMaximo = 200;
	private final int incSaludTope = 25;
	private final int incEnergiaTope = 20;
	private final int multiplicadorDeExperiencia = 40;
	private final int divisorDeDestreza = 1000;

	protected int energia;
	protected int ataque;
	protected int magia;

	protected String nombreRaza;

	protected int saludTope;
	protected int energiaTope;

	protected int destreza;
	protected int inteligencia;
	protected Casta casta;

	protected int x;
	protected int y;

	protected int experiencia;

	protected int idPersonaje;

	protected Alianza clan = null;
	public static int tablaDeNiveles[];

	protected String[] habilidadesRaza;

	/**
	 * Inicializa las habilidades dependiendo la raza que la implementa.
	 */
	protected abstract void inicializarHabilidadesSegunRaza();

	/**
	 * Devuelve las habilidades dependiendo de la Raza del personaje.
	 * @return habilidadesRaza habilidades de la Raza.
	 */
	public String[] getHabilidadesRaza() {
		return habilidadesRaza;
	}

	/**
	 * Devuelve las habilidades dependiendo la Casta del personaje.
	 * @return casta.getHabilidadesCasta habilidades de la Casta.
	 */
	public String[] getHabilidadesCasta() {
		return casta.getHabilidadesCasta();
	}

	/**
	 * Inicializa la tabla del nivel del personaje en 0.
	 */
	public static void cargarTablaNivel() {
		Personaje.tablaDeNiveles = new int[NIVEL_MAXIMO + 2];
		Personaje.tablaDeNiveles[0] = 0;
		Personaje.tablaDeNiveles[1] = 0;
		for (int i = 2; i < NIVEL_MAXIMO + 1; i++) {
			Personaje.tablaDeNiveles[i] = Personaje.tablaDeNiveles[i - 1] + AGREGADO_EXPERIENCIA;
		}
	}

	/**
	 * Constructor de la clase Personaje.
	 * @param nombre nombre del personaje.
	 * @param casta casta del personaje.
	 * @param id id del personaje.
	 * @param inventario inventario del personaje.
	 */
	public Personaje(final String nombre, final Casta casta, final int id, final LinkedList<Item> inventario) {
		super(inventario);
		setNombre(nombre);
		setCasta(casta);
		setIdPersonaje(id);
		setNombreRaza(nombreRazaInicial());
		inicializarHabilidadesSegunRaza();
		setExperiencia(0);
		setNivel(1);
		setFuerza(valorPorDefecto + casta.getFuerza());
		setInteligencia(valorPorDefecto + casta.getInteligencia());
		setDestreza(valorPorDefecto + casta.getDestreza());
		x = 0;
		y = 0;
		setSaludTope(this.saludTopeInicial());
		setSalud(getSaludTope());
		setEnergiaTope(energiaTopeInicial());
		setEnergia(getEnergiaTope());
		setAtaque(calcularPuntosDeAtaque());
		setDefensa(calcularPuntosDeDefensa());
		setMagia(calcularPuntosDeMagia());

	}

	/**
	 * Constructor de la clase Personaje indicando los valores de sus atributos.
	 * @param nombre nombre del personaje.
	 * @param salud salud del personaje.
	 * @param energia energia del personaje.
	 * @param fuerza fuerza del personaje.
	 * @param destreza destreza del personaje.
	 * @param inteligencia inteligencia del personaje.
	 * @param casta casta del personaje.
	 * @param experiencia experiencia del personaje.
	 * @param nivel nivel del personaje.
	 * @param idPersonaje id del personaje.
	 * @param inventario inventario del personaje.
	 */
	public Personaje(final String nombre, final int salud, final int energia, final int fuerza, final int destreza,
			final int inteligencia, final Casta casta, final int experiencia, final int nivel,
			final int idPersonaje, final LinkedList<Item> inventario) {
		super(inventario);
		setNombre(nombre);
		setSalud(salud);
		setEnergia(energia);
		setFuerza(fuerza);
		setDestreza(destreza);
		setInteligencia(inteligencia);
		setCasta(casta);
		setExperiencia(experiencia);
		setNivel(nivel);
		setSaludTope(getSalud());
		setEnergiaTope(getEnergia());
		setIdPersonaje(idPersonaje);
		setDefensa(calcularPuntosDeDefensa());
		setAtaque(calcularPuntosDeAtaque());
		setMagia(calcularPuntosDeMagia());
		setNombreRaza(nombreRazaInicial());
		inicializarHabilidadesSegunRaza();
	}

	/**
	 * Actualizar datos desde hashmap
	 * @param datos salud,fuerza,defensa,nombre,nivel
	 */
	public void actualizar(final HashMap<String, Object> datos) {
		super.actualizar(datos);
		setEnergia((Integer) datos.get(ATTR_ENERGIA));
		setDestreza((Integer) datos.get(ATTR_DESTREZA));
		setInteligencia((Integer) datos.get(ATTR_INTELIGENCIA));
		setCasta((Casta) datos.get(ATTR_CASTA));
		setExperiencia((Integer) datos.get(ATTR_EXPERIENCIA));
		setIdPersonaje((Integer) datos.get(ATTR_IDPERSONAJE));
		setEnergiaTope((Integer) datos.get(ATTR_ENERGIATOPE));
		setSaludTope((Integer) datos.get(ATTR_SALUDTOPE));
	}

	/**
	 * Devuelve hashmap con datos.
	 * @return datos en hashmap
	 */
	public HashMap<String, Object> getTodo() {
		HashMap<String, Object> datos = super.getTodo();
		datos.put(ATTR_ENERGIA, getEnergia());
		datos.put(ATTR_DESTREZA, getDestreza());
		datos.put(ATTR_INTELIGENCIA, getInteligencia());
		datos.put(ATTR_CASTA, getCasta());
		datos.put(ATTR_EXPERIENCIA, getExperiencia());
		datos.put(ATTR_IDPERSONAJE, getIdPersonaje());
		datos.put(ATTR_ENERGIATOPE, getEnergiaTope());
		datos.put(ATTR_SALUDTOPE, getSaludTope());
		return datos;
	}

	/**
	 * Devuelve el nombre de la Raza inicial del personaje.
	 * @return raza.
	 */
	protected abstract String nombreRazaInicial();

	/**
	 * Devuelve el nombre de la Raza actual del personaje.
	 * @return nombreRaza
	 */
	public String getNombreRaza() {
		return nombreRaza;
	}

	/**
	 * Carga en el atributo nombreRaza el nombre de la Raza del personaje.
	 * @param nombreRaza nombre de la raza.
	 */
	public void setNombreRaza(final String nombreRaza) {
		this.nombreRaza = nombreRaza;
	}

	/**
	 * Devuelve el valor del ataque del personaje.
	 * @return ataque
	 */
	public int getAtaque() {
		return ataque;
	}

	/**
	 * Carga el valor del ataque del personaje.
	 * @param ataque ataque del personaje.
	 */
	public void setAtaque(final int ataque) {
		this.ataque = ataque;
	}

	/**
	 * Devuelve el valor de la magia del personaje.
	 * @return magia magia del personaje.
	 */
	public int getMagia() {
		return magia;
	}

	/**
	 * Carga el valor de la magia del personaje.
	 * @param magia magia del personaje
	 */
	public void setMagia(final int magia) {
		this.magia = magia;
	}

	/**
	 * Devuelve el nombre de la Alianza del personaje.
	 * @return clan nombre de la alianza.
	 */
	public Alianza getClan() {
		return clan;
	}

	/**
	 * Carga en el atributo clan el nombre de la Alianza del personaje.
	 * @param clan nombre de la alianza.
	 */
	public void setClan(final Alianza clan) {
		this.clan = clan;
		clan.añadirPersonaje(this);
	}

	/**
	 * Devuelve el valor de la energia del personaje.
	 * @return energia energia del personaje.
	 */
	public int getEnergia() {
		return energia;
	}

	/**
	 * Carga el valor de la energia del personaje.
	 * @param energia energia del personaje.
	 */
	protected void setEnergia(final int energia) {
		this.energia = energia;
	}

	/**
	 * Devuelve el valor de la destreza del personaje.
	 * @return destreza destreza del personaje.
	 */
	public int getDestreza() {
		return destreza;
	}

	/**
	 * Carga el valor de la destreza del personaje.
	 * @param destreza destreza del personaje
	 */
	protected void setDestreza(final int destreza) {
		this.destreza = destreza;
	}

	/**
	 * Devuelve el valor de la inteligencia del personaje.
	 * @return inteligencia inteligencia del personaje
	 */
	public int getInteligencia() {
		return inteligencia;
	}

	/**
	 * Carga el valor de la inteligencia del personaje.
	 * @param inteligencia inteligencia del personaje
	 */
	protected void setInteligencia(final int inteligencia) {
		this.inteligencia = inteligencia;
	}

	/**
	 * Devuelve la Casta del personaje.
	 * @return casta casta del personaje.
	 */
	public Casta getCasta() {
		return casta;
	}

	/**
	 * Carga en el atributo casta la casta del personaje.
	 * @param casta casta del personaje.
	 */
	protected void setCasta(final Casta casta) {
		this.casta = casta;
	}

	/**
	 * Devuelve la experiencia del personaje.
	 * @return experiencia experiencia del personaje.
	 */
	public int getExperiencia() {
		return experiencia;
	}

	/**
	 * Carga la experiencia del personaje.
	 * @param experiencia experiencia del personaje.
	 */
	protected void setExperiencia(final int experiencia) {
		this.experiencia = experiencia;
	}

	/**
	 * Devuelve el Id del personaje.
	 * @return idPersonaje Id del personaje.
	 */
	public int getIdPersonaje() {
		return idPersonaje;
	}

	/**
	 * Carga el Id del personaje.
	 * @param idPersonaje Id del personaje.
	 */
	protected void setIdPersonaje(final int idPersonaje) {
		this.idPersonaje = idPersonaje;
	}

	/**
	 * Devuelve 100 como salud tope inicial
	 * @return 100.
	 */
	protected int saludTopeInicial() {
		return valorTopeInicial;
	}

	/**
	 * Devuelve la salud tope del personaje.
	 * @return saludTope salud tope
	 */
	public int getSaludTope() {
		return saludTope;
	}

	/**
	 * Carga la salud tope del personaje.
	 * @param saludTope salud tope
	 */
	protected void setSaludTope(final int saludTope) {
		this.saludTope = saludTope;
	}

	/**
	 * Devuelve 100 como energia tope inicial.
	 * @return 100.
	 */
	protected int energiaTopeInicial() {
		return valorTopeInicial;
	}

	/**
	 * Devuelve la energia tope del personaje.
	 * @return energiaTope energia tope.
	 */
	public int getEnergiaTope() {
		return energiaTope;
	}

	/**
	 * Carga la energia tope del personaje.
	 * @param energiaTope energia tope.
	 */
	protected void setEnergiaTope(final int energiaTope) {
		this.energiaTope = energiaTope;
	}

	@Override
	public int golpeCritico() {
		return (int) (this.ataque * this.getCasta().getDañoCritico());
	}

	/**
	 * Funcion vacia, no hace nada.
	 */
	public void despuesDeTurno() {

	}

	/**
	 * Indica si el personaje esta en condiciones de realizar un ataque.
	 * @return	true si la energia es mayor a la energia minima necesaria para atacar;
	 * 			false si la energia es menor a la energia minima necesaria para atacar.
	 */
	public boolean puedeAtacar() {
		return energia > energiaMinimaParaHabilidad;
	}

	/**
	 * Calcula los puntos de ataque dependiendo de la fuerza del personaje.
	 * @return el valor del ataque del personaje.
	 */
	public int calcularPuntosDeAtaque() {
		return (int) (this.getFuerza() * incrementoDePuntos);
	}

	/**
	 * Calcula los puntos de defensa, dependen de la destreza del personaje.
	 * @return el valor de la defensa del personaje.
	 */
	public int calcularPuntosDeDefensa() {
		return (int) (this.getDestreza());
	}

	/**
	 * Calcula los puntos de magia dependiendo de la inteligencia del personaje.
	 * @return el valor de la magia del personaje.
	 */
	public int calcularPuntosDeMagia() {
		return (int) (this.getInteligencia() * incrementoDePuntos);
	}

	/**
	 * Le asigna al personaje su salud tope.
	 */
	public void restablecerSalud() {
		setSalud(this.saludTope);
	}

	/**
	 * Le asigna al personaje su energía tope.
	 */
	public void restablecerEnergia() {
		this.energia = this.energiaTope;
	}

	/**
	 * Modifica los atributos del personaje dependiendo de sus puntos.
	 */
	public void modificarAtributos() {
		this.ataque = this.calcularPuntosDeAtaque();
		setDefensa(this.calcularPuntosDeDefensa());
		this.magia = this.calcularPuntosDeMagia();
	}

	/**
	 * Quita salud al personaje, si el daño es mayor a la defensa.
	 * @param danio daño causado al personaje.
	 * @return danioMenosDefensa daño final recibido.
	 */
	public int serRobadoSalud(final int danio) {
		int danioMenosDefensa = danio - getDefensa();
		if (danioMenosDefensa <= 0) {
			return 0;
		}
		if ((getSalud() - danioMenosDefensa) >= 0) {
			setSalud(getSalud() - danioMenosDefensa);
		} else {
			danioMenosDefensa = getSalud();
			setSalud(0);
		}
		return danioMenosDefensa;
	}

	/**
	 * Quita energía al personaje, si el daño es mayor a la defensa.
	 * @param danio daño causado al personaje.
	 * @return danio daño final recibido.
	 */
	public int serDesernegizado(final int danio) {
		int danioMenosDefensa = danio - getDefensa();
		if (danioMenosDefensa <= 0) {
			return 0;
		}
		if ((energia - danioMenosDefensa) >= 0) {
			energia -= danioMenosDefensa;
		} else {
			danioMenosDefensa = energia;
			energia = 0;
		}
		return danioMenosDefensa;
	}

	/**
	 * Suma salud al personaje.
	 * @param salud salud que se le suma al personaje.
	 */
	public void serCurado(final int salud) {
		if ((getSalud() + salud) <= this.saludTope) {
			setSalud(getSalud() + salud);
		} else {
			setSalud(this.saludTope);
		}
	}

	/**
	 * Suma energia al personaje.
	 * @param energy energia que se le suma al personaje.
	 */
	public void serEnergizado(final int energy) {
		if ((this.energia + energy) <= this.energiaTope) {
			this.energia += energy;
		} else {
			this.energia = this.energiaTope;
		}
	}

	/**
	 * Crea una alianza.
	 * @param nombreAlianza nombre de la alianza.
	 */
	public void crearAlianza(final String nombreAlianza) {
		this.clan = new Alianza(nombreAlianza);
		this.clan.añadirPersonaje(this);
	}

	/**
	 * El personaje sale de la alianza.
	 */
	public void salirDeAlianza() {
		if (this.clan != null) {
			this.clan.eliminarPersonaje(this);
			this.clan = null;
		}
	}

	/**
	 * Agrega un personaje a la alianza si no tiene una.
	 * Si no existe una alianza se crea una con el nombre Alianza 1.
	 * @param nuevoAliado personaje al cual se lo invita a la alianza.
	 * @return	true si el personaje no tiene alianza.
	 * 			false si el personaje ya tiene alianza.
	 */
	public boolean aliar(final Personaje nuevoAliado) {
		if (this.clan == null) {
			Alianza a = new Alianza("Alianza 1");
			this.clan = a;
			a.añadirPersonaje(this);
		}

		if (nuevoAliado.clan == null) {
			nuevoAliado.clan = this.clan;
			this.clan.añadirPersonaje(nuevoAliado);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Suma nuevos valores a la fuerza, destreza e inteligencia del personaje.
	 * @param fuerza fuerza que se suma.
	 * @param dest destreza que se suma.
	 * @param inte inteligencia que se suma.
	 */
	public void asignarPuntosSkills(final int fuerza, final int dest, final int inte) {
		if (getFuerza() + fuerza <= valorMaximo) {
			setFuerza(getFuerza() + fuerza);
		}
		if (getDestreza() + dest <= valorMaximo) {
			setDestreza(getDestreza() + dest);
		}
		if (getInteligencia() + inte <= valorMaximo) {
			setInteligencia(getInteligencia() + inte);
		}
		this.modificarAtributos();
	}

	/**
	 * Incrementa el nivel del personaje en 1 y resetea el acumulador de experiencia.
	 * Incrementa la salud tope y la energia tope.
	 */
	public void subirNivel() {

		int acumuladorExperiencia = 0;
		if (getNivel() == NIVEL_MAXIMO) {
			return;
		}
		while (getNivel() != NIVEL_MAXIMO && (this.experiencia >= Personaje.tablaDeNiveles[getNivel() + 1]
				+ acumuladorExperiencia)) {
			acumuladorExperiencia += Personaje.tablaDeNiveles[getNivel() + 1];
			setNivel(getNivel() + 1);
			this.modificarAtributos();
			this.saludTope += incSaludTope;
			this.energiaTope += incEnergiaTope;
		}
		this.experiencia -= acumuladorExperiencia;
	}

	/**
	 * Suma experiencia a la experiencia del personaje.
	 * @param exp experiencia que se suma.
	 * @return	true si el personaje sube de nivel.
	 * 			false si el personaje no sube de nivel.
	 */
	public boolean ganarExperiencia(final int exp) {
		this.experiencia += exp;

		if (experiencia >= Personaje.tablaDeNiveles[getNivel() + 1]) {
			subirNivel();
			return true;
		}
		return false;
	}

	/**
	 * Indica cuánto se va a multiplicar la experiencia.
	 * @return multiplicador de experiencia.
	 */
	@Override
	protected int multiplicadorExperiencia() {
		return multiplicadorDeExperiencia;
	}

	/**
	 * Devuelve la probabilidad de la casta de evitar daño en ataque.
	 * @return probabilidad de evitar daño segun casta.
	 */
	@Override
	protected double probabilidadEvitarDanoEnAtaque() {
		return this.getCasta().getProbabilidadEvitarDaño();
	}

	/**
	 * Devuelve la defensa involucrada al momento de ser atacado.
	 * @return defensa del personaje.
	 */
	@Override
	protected int defensaAlSerAtacado() {
		return getDefensa();
	}

	/**
	 * Disminuye la salid del personaje. El valor mínimo de la salud es cero.
	 * @param danio daño a quitar al personaje
	 * @return devuelve el daño causado si es mayor a cero; caso contrario devuelve 0.
	 */
	@Override
	protected int quitarVidaSegunDano(final int danio) {
		int danioARestar = danio;
		if (danioARestar >= 0) {
			if (getSalud() <= danioARestar) {
				danioARestar = getSalud();
				setSalud(0);
			} else {
				super.quitarVidaSegunDano(danioARestar);
			}
			return danioARestar;
		} else {
			return 0;
		}
	}

	@Override
	protected double probabilidadGolpeCritico() {
		return this.casta.getProbabilidadGolpeCritico() + this.destreza / divisorDeDestreza;
	}

	@Override
	protected boolean puedoAtacar(final boolean atacadoEstaVivo) {
		return this.estaVivo() && atacadoEstaVivo;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * Calcula la distancia entre el personaje y su oponente.
	 * @param oponente personaje a quien se enfrenta.
	 * @return devuelve la distancia entre ambos.
	 */
	public double distanciaCon(final Personaje oponente) {
		return Math.sqrt(Math.pow(this.x - oponente.x, 2) + Math.pow(this.y - oponente.y, 2));
	}

	/**
	 * Realiza la habilidadCasta1 sobre el personaje atacado.
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado.
	 * @return	true si la habilidad se realiza.
	 * 			false si la habilidad no se realiza.
	 */
	public boolean habilidadCasta1(final Peleable atacado) {
		return this.getCasta().habilidad1(this, atacado);
	}

	/**
	 * Realiza la habilidadCasta2 sobre el personaje atacado.
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado.
	 * @return	true si la habilidad se realiza.
	 * 			false si la habilidad no se realiza.
	 */
	public boolean habilidadCasta2(final Peleable atacado) {
		return this.getCasta().habilidad2(this, atacado);
	}

	/**
	 * Realiza la habilidadCasta3 sobre el personaje atacado.
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado.
	 * @return	true si la habilidad se realiza.
	 * 			false si la habilidad no se realiza.
	 */
	public boolean habilidadCasta3(final Peleable atacado) {
		return this.getCasta().habilidad3(this, atacado);
	}

	/**
	 * Realiza la habilidadRaza1 sobre el personaje atacado.
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado.
	 * @return	true si la habilidad se realiza.
	 * 			false si la habilidad no se realiza.
	 */
	public abstract boolean habilidadRaza1(Peleable atacado);

	/**
	 * Realiza la habilidadRaza1 sobre el personaje atacado.
	 * @param atacado un objeto que implementa la interfaz Peleable, es aquel a ser atacado.
	 * @return	true si la habilidad se realiza.
	 * 			false si la habilidad no se realiza.
	 */
	public abstract boolean habilidadRaza2(Peleable atacado);

	@Override
	public final boolean esAfectadoPorHechicero() {
		return true;
	}

	@Override
	public final boolean esAfectadoPorGuerrero() {
		return true;
	}

	@Override
	public final void addBonusSegunItems() {
		super.addBonusSegunItems();
		int acumDestreza = 0;
		int acumInteligencia = 0;
		int acumEnergia = 0;

		for (Item item : getInventario()) {
			acumDestreza += item.getModifDestreza(destreza);
			acumInteligencia += item.getModifInteligencia(inteligencia);
			acumEnergia += item.getModifEnergia(energia);
		}

		destreza += acumDestreza;
		inteligencia += acumInteligencia;
		energia += acumEnergia;

		saludTope = getSalud();
		energiaTope = getEnergia();
	}

	@Override
	public void removeBonusSegunItems() {
		super.removeBonusSegunItems();
		int acumDestreza = 0;
		int acumInteligencia = 0;
		int acumEnergia = 0;

		for (Item item : getInventario()) {
			acumDestreza += item.getModifDestreza(destreza);
			acumInteligencia += item.getModifInteligencia(inteligencia);
			acumEnergia += item.getModifEnergia(energia);
		}

		destreza -= acumDestreza;
		inteligencia -= acumInteligencia;
		energia -= acumEnergia;

		energiaTope = getEnergia();
		saludTope = getSalud();
	}
}
