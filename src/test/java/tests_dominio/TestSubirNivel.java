package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.Guerrero;
import dominio.Humano;
import dominio.Personaje;

/**
 * Test para verificar que el personaje suba de nivel correctamente.
 */
public class TestSubirNivel {

	/**
	 * Verifica que al ganar 50 de experiencia siendo nivel 1 suba al nivel 2.
	 */
	@Test
	public void testSubirdeNivel() {
		Personaje.cargarTablaNivel();
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		Assert.assertTrue(h.getNivel() == 1);
		h.ganarExperiencia(50);
		Assert.assertTrue(h.getNivel() == 2);
	}

	/**
	 * Verifica que por mas que la experiencia ganada sea suficiente para subir a mas niveles no pase el nivel 100.
	 */
	@Test
	public void testNivel100() {
		Personaje.cargarTablaNivel();
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		h.ganarExperiencia(300000);
		Assert.assertTrue(h.getNivel() == 100);
		h.subirNivel();
		Assert.assertTrue(h.getNivel() == 100);

	}

	/**
	 * Verifica que al ganar 150 de experiencia siendo nivel 1, suba al nivel 3.
	 */
	@Test
	public void testGanarMuchaExp() {
		Personaje.cargarTablaNivel();
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		Assert.assertTrue(h.getNivel() == 1);
		h.ganarExperiencia(150);
		Assert.assertTrue(h.getNivel() == 3);
	}
}
