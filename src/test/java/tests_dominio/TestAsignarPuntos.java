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
 * Test de incremento de los puntos de un personaje.
 */
public class TestAsignarPuntos {

	private static final int SALUD_HUMANO_NIVEL_1 = 105;
	private static final int SALUD_HUMANO_NIVEL_2 = 130;
	private static final int ENERGIA_HUMANO_NIVEL_1 = 105;
	private static final int ENERGIA_HUMANO_NIVEL_2 = 125;
	private static final int EXPERIENCIA_50 = 50;
	private static final int PUNTOS_199 = 199;

	/**
	 * Verifica que los puntos de salud aumenten cuando el personaje sube de
	 * nivel.
	 */
	@Test
	public void testAumentarSaludTope() {
		Personaje.cargarTablaNivel();
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		Assert.assertTrue(h.getSaludTope() == SALUD_HUMANO_NIVEL_1);
		h.ganarExperiencia(EXPERIENCIA_50);
		Assert.assertTrue(h.getSaludTope() == SALUD_HUMANO_NIVEL_2);
	}

	/**
	 * Verifica que los puntos de energia aumenten cuando el personaje sube de
	 * nivel.
	 */
	@Test
	public void testAumentarEnergiaTope() {
		Personaje.cargarTablaNivel();
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		Assert.assertTrue(h.getEnergiaTope() == ENERGIA_HUMANO_NIVEL_1);
		h.ganarExperiencia(EXPERIENCIA_50);
		Assert.assertTrue(h.getEnergiaTope() == ENERGIA_HUMANO_NIVEL_2);
	}

	/**
	 * Verifica que los puntos de fuerza, destreza e inteligencia sean menores a
	 * 200.
	 */
	@Test
	public void testMasDe200Puntos() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_FUERZA, PUNTOS_199);
		datos.put(Personaje.ATTR_DESTREZA, PUNTOS_199);
		datos.put(Personaje.ATTR_INTELIGENCIA, PUNTOS_199);
		h.actualizar(datos);
		h.asignarPuntosSkills(2, 2, 2);
		Assert.assertTrue(h.getFuerza() == PUNTOS_199);
		Assert.assertTrue(h.getDestreza() == PUNTOS_199);
		Assert.assertTrue(h.getInteligencia() == PUNTOS_199);
	}
}
