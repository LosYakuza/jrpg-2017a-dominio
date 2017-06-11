package tests_dominio;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import dominio.Asesino;
import dominio.Humano;
import dominio.Item;
import dominio.Personaje;

/**
 * Test de la asignacion de experiencia y de la funcion Subir de Nivel.
 */
public class TestNivelExp {
	private static final int SALUD_PRUEBA = 100;

	/**
	 * Verifica que si no sube de nivel, la experiencia ganada la acumule.
	 */
	@Test
	public void testGanarExp() {
		Personaje.cargarTablaNivel();
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 1, 1,
				new LinkedList<Item>());
		h.ganarExperiencia(45);
		Assert.assertTrue(h.getExperiencia() == 45);
	}

	/**
	 * Verifica que si sube de nivel, a la experiencia acumulada se le reste la experiencia que necesita
	 * subir de nivel.
	 */
	@Test
	public void testSubirNivel() {
		Personaje.cargarTablaNivel();
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 1, 1,
				new LinkedList<Item>());
		h.ganarExperiencia(300);
		Assert.assertTrue(h.getNivel() == 4);
		Assert.assertTrue(h.getExperiencia() == 0);
	}

	/**
	 * Verifica que el personaje no pueda subir mas que hasta el nivel 100, y que la experiencia ganada de ahi
	 * en mas, se vaya acumulando.
	 */
	@Test
	public void testLevel100() {
		Personaje.cargarTablaNivel();
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 99, 1,
				new LinkedList<Item>());
		h.ganarExperiencia(10000);
		Assert.assertTrue(h.getNivel() == 100);
	}
}
