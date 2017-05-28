package dominio;

import java.io.Serializable;
import java.util.HashMap;

/**
 * La clase Personaje está desarrollada para que las clases que la hereden
 * posean todos los atributos y métodos necesarios para atacar y ser atacados
 * dependiendo de sus atributos (salud, energía, defensa, etc.), de su casta, de
 * su nivel, experiencia y habilidades.
 */
public abstract class Personaje extends Peleador implements Serializable {

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

	protected abstract void inicializarHabilidadesSegunRaza();

	public String[] getHabilidadesRaza() {
		return habilidadesRaza;
	}

	public String[] getHabilidadesCasta() {
		return casta.getHabilidadesCasta();
	}

	public static void cargarTablaNivel() {
		Personaje.tablaDeNiveles = new int[101];
		Personaje.tablaDeNiveles[0] = 0;
		Personaje.tablaDeNiveles[1] = 0;
		for (int i = 2; i < 101; i++)
			Personaje.tablaDeNiveles[i] = Personaje.tablaDeNiveles[i - 1] + 50;
	}

	public Personaje(String nombre, Casta casta, int id) {
		setNombre(nombre);
		setCasta(casta);
		setIdPersonaje(id);
		setNombreRaza(nombreRazaInicial());
		inicializarHabilidadesSegunRaza();
		setExperiencia(0);
		setNivel(1);
		setFuerza(10 + casta.getFuerza());
		setInteligencia(10 + casta.getInteligencia());
		setDestreza(10 + casta.getDestreza());
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

	public Personaje(String nombre, int salud, int energia, int fuerza, int destreza, int inteligencia, Casta casta,
			int experiencia, int nivel, int idPersonaje) {
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
	}

	/**
	 * Actualizar datos desde hashmap
	 * @param datos salud,fuerza,defensa,nombre,nivel
	 */
	public void actualizar(final HashMap<String, Object> datos) {
		super.actualizar(datos);
		setEnergia((Integer) datos.get("energia"));
		setDestreza((Integer) datos.get("destreza"));
		setInteligencia((Integer) datos.get("inteligencia"));
		setCasta((Casta) datos.get("casta"));
		setExperiencia((Integer) datos.get("experiencia"));
		setIdPersonaje((Integer) datos.get("idpersonaje"));
		setEnergiaTope((Integer) datos.get("energiatope"));
		setSaludTope((Integer) datos.get("saludtope"));
	}

	/**
	 * Devuelve hashmap con datos.
	 * @return datos en hashmap
	 */
	public HashMap<String, Object> getTodo() {
		HashMap<String, Object> datos = super.getTodo();
		datos.put("energia", getEnergia());
		datos.put("destreza", getDestreza());
		datos.put("inteligencia", getInteligencia());
		datos.put("casta", getCasta());
		datos.put("experiencia", getExperiencia());
		datos.put("idpersonaje", getIdPersonaje());
		datos.put("energiatope", getEnergiaTope());
		datos.put("saludtope", getSaludTope());
		return datos;
	}
	
	protected abstract String nombreRazaInicial();

	public String getNombreRaza() {
		return nombreRaza;
	}

	public void setNombreRaza(String nombreRaza) {
		this.nombreRaza = nombreRaza;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getMagia() {
		return magia;
	}

	public void setMagia(int magia) {
		this.magia = magia;
	}

	public Alianza getClan() {
		return clan;
	}

	public void setClan(Alianza clan) {
		this.clan = clan;
		clan.añadirPersonaje(this);
	}

	public int getEnergia() {
		return energia;
	}

	/**
	 * Setter energia.
	 * @param energia energia
	 */
	protected void setEnergia(final int energia) {
		this.energia = energia;
	}

	public int getDestreza() {
		return destreza;
	}

	/**
	 * Setter destreza.
	 * @param destreza destreza
	 */
	protected void setDestreza(final int destreza) {
		this.destreza = destreza;
	}

	public int getInteligencia() {
		return inteligencia;
	}

	/**
	 * Setter inteligencia.
	 * @param inteligencia inteligencia
	 */
	protected void setInteligencia(final int inteligencia) {
		this.inteligencia = inteligencia;
	}

	public Casta getCasta() {
		return casta;
	}

	/**
	 * Setter Casta.
	 * @param casta casta
	 */
	protected void setCasta(final Casta casta) {
		this.casta = casta;
	}

	public int getExperiencia() {
		return experiencia;
	}

	/**
	 * Setter experiencia.
	 * @param experiencia experiencia
	 */
	protected void setExperiencia(final int experiencia) {
		this.experiencia = experiencia;
	}

	public int getIdPersonaje() {
		return idPersonaje;
	}

	/**
	 * Setter Id personaje
	 * @param idPersonaje idpersonaje
	 */
	protected void setIdPersonaje(final int idPersonaje) {
		this.idPersonaje = idPersonaje;
	}

	protected int saludTopeInicial() {
		return 100;
	}

	public int getSaludTope() {
		return saludTope;
	}

	/**
	 * Setter salud tope.
	 * @param saludTope salud tope
	 */
	protected void setSaludTope(final int saludTope) {
		this.saludTope = saludTope;
	}

	protected int energiaTopeInicial() {
		return 100;
	}

	public int getEnergiaTope() {
		return energiaTope;
	}

	/**
	 * Setter energia tope.
	 * @param energiaTope energia tope
	 */
	protected void setEnergiaTope(final int energiaTope) {
		this.energiaTope = energiaTope;
	}

	@Override
	public int golpeCritico() {
		return (int) (this.ataque * this.getCasta().getDañoCritico());
	}

	public void despuesDeTurno() {

	}

	public boolean puedeAtacar() {
		return energia > 10;
	}

	/**
	 * Calcula los puntos de ataque dependiendo de la fuerza del personaje.
	 * 
	 * @return
	 */
	public int calcularPuntosDeAtaque() {
		return (int) (this.getFuerza() * 1.5);
	}

	/**
	 * Calcula los puntos de defensa, dependen de la destreza del personaje.
	 * 
	 * @return
	 */
	public int calcularPuntosDeDefensa() {
		return (int) (this.getDestreza());
	}

	/**
	 * Calcula los puntos de magia dependiendo de la inteligencia del personaje.
	 * 
	 * @return
	 */
	public int calcularPuntosDeMagia() {
		return (int) (this.getInteligencia() * 1.5);
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
	 * 
	 * @param daño
	 * @return
	 */
	public int serRobadoSalud(int daño) {
		daño -= getDefensa();
		if (daño <= 0) {
			return 0;
		}
		if ((getSalud() - daño) >= 0) {
			setSalud(getSalud() - daño);
		} else {
			daño = getSalud();
			setSalud(0);
		}
		return daño;
	}

	/**
	 * Quita energía al personaje, si el daño es mayor a la defensa.
	 * 
	 * @param daño
	 * @return
	 */
	public int serDesernegizado(int daño) {
		daño -= getDefensa();
		if (daño <= 0) {
			return 0;
		}
		if ((energia - daño) >= 0) {
			energia -= daño;
		} else {
			daño = energia;
			energia = 0;
		}
		return daño;
	}

	/**
	 * Suma salud al personaje.
	 * 
	 * @param salud
	 */
	public void serCurado(final int salud) {
		if ((getSalud() + salud) <= this.saludTope) {
			setSalud(getSalud() + salud);
		} else {
			setSalud(this.saludTope);
		}
	}

	public void serEnergizado(int energia) {
		if ((this.energia + energia) <= this.energiaTope) {
			this.energia += energia;
		} else {
			this.energia = this.energiaTope;
		}
	}

	public void crearAlianza(final String nombre_alianza) {
		this.clan = new Alianza(nombre_alianza);
		this.clan.añadirPersonaje(this);
	}

	public void salirDeAlianza() {
		if (this.clan != null) {
			this.clan.eliminarPersonaje(this);
			this.clan = null;
		}
	}

	public boolean aliar(final Personaje nuevo_aliado) {
		if (this.clan == null) {
			Alianza a = new Alianza("Alianza 1");
			this.clan = a;
			a.añadirPersonaje(this);
		}

		if (nuevo_aliado.clan == null) {
			nuevo_aliado.clan = this.clan;
			this.clan.añadirPersonaje(nuevo_aliado);
			return true;
		} else {
			return false;
		}
	}

	public void asignarPuntosSkills(int fuerza, int destreza, int inteligencia) {
		if (getFuerza() + fuerza <= 200) {
			setFuerza(getFuerza() + fuerza);
		}
		if (this.destreza + destreza <= 200) {
			this.destreza += destreza;
		}
		if (this.inteligencia + inteligencia <= 200) {
			this.inteligencia += inteligencia;
		}
		this.modificarAtributos();
	}

	public void subirNivel() {

		int acumuladorExperiencia = 0;
		if (getNivel() == 100) {
			return;
		}
		while (getNivel() != 100
				&& (this.experiencia >= Personaje.tablaDeNiveles[getNivel() + 1] + acumuladorExperiencia)) {
			acumuladorExperiencia += Personaje.tablaDeNiveles[getNivel() + 1];
			setNivel(getNivel() + 1);
			this.modificarAtributos();
			this.saludTope += 25;
			this.energiaTope += 20;
		}
		this.experiencia -= acumuladorExperiencia;
	}

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
	 * 
	 * @return
	 */
	@Override
	protected int multiplicadorExperiencia() {
		return 40;
	}

	/**
	 * Devuelve la probabilidad de la casta de evitar daño en ataque.
	 * 
	 * @return
	 */
	@Override
	protected double probabilidadEvitarDanoEnAtaque() {
		return this.getCasta().getProbabilidadEvitarDaño();
	}

	/**
	 * Devuelve la defensa involucrada al momento de ser atacado.
	 * 
	 * @return
	 */
	@Override
	protected int defensaAlSerAtacado() {
		return getDefensa();
	}

	/**
	 * Disminuye la salid del personaje. El valor mínimo de la salud es cero.
	 *
	 * @param daño
	 *            daño a quitar al personaje
	 * @return devuelve el daño causado si es mayor a cero; caso contrario
	 *         devuelve 0.
	 */
	@Override
	protected int quitarVidaSegunDano(int daño) {
		if (daño >= 0) {
			if (getSalud() <= daño) {
				daño = getSalud();
				setSalud(0);
			} else {
				super.quitarVidaSegunDano(daño);
			}
			return daño;
		} else {
			return 0;
		}
	}

	@Override
	protected double probabilidadGolpeCritico() {
		return this.casta.getProbabilidadGolpeCritico() + this.destreza / 1000;
	}

	@Override
	protected boolean puedoAtacar(final boolean atacadoEstaVivo) {
		return this.estaVivo() && atacadoEstaVivo;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public double distanciaCon(final Personaje p) {
		return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
	}

	public boolean habilidadCasta1(final Peleable atacado) {
		return this.getCasta().habilidad1(this, atacado);
	}

	public boolean habilidadCasta2(final Peleable atacado) {
		return this.getCasta().habilidad2(this, atacado);
	}

	public boolean habilidadCasta3(final Peleable atacado) {
		return this.getCasta().habilidad3(this, atacado);
	}

	public abstract boolean habilidadRaza1(Peleable atacado);

	public abstract boolean habilidadRaza2(Peleable atacado);

	@Override
	public final boolean esAfectadoPorHechicero() {
		return true;
	}

	@Override
	public final boolean esAfectadoPorGuerrero() {
		return true;
	}
}
