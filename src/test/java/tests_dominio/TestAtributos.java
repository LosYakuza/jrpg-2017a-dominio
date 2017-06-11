package tests_dominio;

import org.junit.Test;

import dominio.Guerrero;
import dominio.Humano;
import dominio.Item;
import dominio.Personaje;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Assert;

/**
 * Test de los atributos del personaje.
 */
public class TestAtributos {
	private static final int INCREMENTO = 10;

	/**
	 * Verifica que al incrementar la fuerza se incremente el ataque del
	 * personaje.
	 */
	@Test
	public void testIncrementarFuerza() {
		final int ataqueHumano = 22;
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());

		Assert.assertTrue(h.getAtaque() == ataqueHumano);
		h.asignarPuntosSkills(INCREMENTO, 0, 0);
		Assert.assertTrue(h.getAtaque() > ataqueHumano);
	}

	/**
	 * Verifica que al incrementar la destreza se incremente la defensa del
	 * personaje.
	 */
	@Test
	public void testIncrementarDestreza() {
		final int defensaHumano = 10;

		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		Assert.assertTrue(h.getDefensa() == defensaHumano);
		h.asignarPuntosSkills(0, INCREMENTO, 0);
		Assert.assertTrue(h.getDefensa() > defensaHumano);
	}

	/**
	 * Verifica que al incrementar la inteligencia se incremente la magia del
	 * personaje.
	 */
	@Test
	public void testIncrementarInteligencia() {
		final int magiaHumano = 15;

		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		Assert.assertTrue(h.getMagia() == magiaHumano);
		h.asignarPuntosSkills(0, 0, INCREMENTO);
		Assert.assertTrue(h.getMagia() > magiaHumano);
	}

	/**
	 * Verifica que los setters de los atributos del personaje funcionen bien.
	 */
	@Test
	public void testSetters() {
		final int experienciaPrueba = 15;
		final int idPrueba = 4;
		final int saludTopePrueba = 400;
		final int saludPrueba = 200;
		final int energiaTopePrueba = 500;
		final int energiaPrueba = 20;
		final int magiaSetPrueba = 3;

		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		h.setNombreRaza("Ballena");
		Assert.assertEquals("Ballena", h.getNombreRaza());

		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_EXPERIENCIA, experienciaPrueba);
		datos.put(Personaje.ATTR_IDPERSONAJE, idPrueba);
		datos.put(Personaje.ATTR_SALUDTOPE, saludTopePrueba);
		datos.put(Personaje.ATTR_SALUD, saludPrueba);
		datos.put(Personaje.ATTR_ENERGIATOPE, energiaTopePrueba);
		datos.put(Personaje.ATTR_ENERGIA, energiaPrueba);
		h.actualizar(datos);

		h.setMagia(magiaSetPrueba);
		Assert.assertEquals(magiaSetPrueba, h.getMagia());

		Assert.assertEquals(experienciaPrueba, h.getExperiencia());

		Assert.assertEquals(idPrueba, h.getIdPersonaje());

		Assert.assertEquals(saludTopePrueba, h.getSaludTope());

		Assert.assertEquals(saludPrueba, h.getSalud());
		h.restablecerSalud();
		Assert.assertEquals(saludTopePrueba, h.getSalud());

		Assert.assertEquals(energiaTopePrueba, h.getEnergiaTope());
		Assert.assertEquals(energiaPrueba, h.getEnergia());
		h.restablecerEnergia();
		Assert.assertEquals(energiaTopePrueba, h.getEnergia());
	}

	/**
	 * Verifica que no pueda atacar si tiene energia menor a 10 y que si pueda
	 * cuando sea mayor a 10.
	 */
	@Test
	public void testPuedeAtacar() {
		final int energiaPrueba12 = 12;
		final int energiaPrueba8 = 8;

		Humano h = new Humano("Mica", new Guerrero(), 1, new LinkedList<Item>());
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, energiaPrueba12);
		h.actualizar(datos);
		Assert.assertTrue(h.puedeAtacar());
		datos.put(Personaje.ATTR_ENERGIA, energiaPrueba8);
		h.actualizar(datos);
		Assert.assertFalse(h.puedeAtacar());
	}

	/**
	 * Verifica que la salud que le roban sea igual a el daño menos la defensa
	 * del personaje, si el daño es menor que la defensa roba 0 de salud.
	 */
	@Test
	public void testSerRobadoSalud() {
		final int saludPrueba = 10;
		final int defensaPrueba = 40;
		final int roboSalud = 30;
		final int defensaPrueba2 = 10;
		final int resultado = 10;

		Humano h = new Humano("Mica", new Guerrero(), 1, new LinkedList<Item>());
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_SALUD, saludPrueba);
		datos.put(Personaje.ATTR_DEFENSA, defensaPrueba);
		h.actualizar(datos);
		Assert.assertEquals(0, h.serRobadoSalud(roboSalud));
		datos = h.getTodo();
		datos.put(Personaje.ATTR_DEFENSA, defensaPrueba2);
		h.actualizar(datos);
		Assert.assertEquals(resultado, h.serRobadoSalud(roboSalud));
		Assert.assertEquals(0, h.getSalud());
	}

	/**
	 * Verifica que la energia que le roban sea igual a el daño menos la defensa
	 * del personaje, si el daño es menor que la defensa, quita 0 de energia.
	 */
	@Test
	public void testSerDesernegizado() {
		final int energiaPrueba = 10;
		final int desernegiza = 20;
		Humano h = new Humano("Mica", new Guerrero(), 1, new LinkedList<Item>());
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_DEFENSA, 0);
		h.actualizar(datos);
		Assert.assertEquals(0, h.serDesernegizado(-1));
		datos.put(Personaje.ATTR_ENERGIA, energiaPrueba);
		h.actualizar(datos);
		Assert.assertEquals(energiaPrueba, h.serDesernegizado(desernegiza));
		Assert.assertEquals(0, h.getEnergia());
	}

	/**
	 * Verifica que la funcion serEnergizado funcione correctamente, sin
	 * sobrepasar el tope de energia.
	 */
	@Test
	public void testSerEnergizado() {
		final int energiaTopePrueba = 200;
		final int energiaPrueba = 190;
		final int masEnergia = 20;
		Humano h = new Humano("Mica", new Guerrero(), 1, new LinkedList<Item>());
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIATOPE, energiaTopePrueba);
		datos.put(Personaje.ATTR_ENERGIA, energiaPrueba);
		h.actualizar(datos);
		Assert.assertEquals(energiaPrueba, h.getEnergia());
		h.serEnergizado(masEnergia);
		Assert.assertEquals(energiaTopePrueba, h.getEnergia());
	}
}
