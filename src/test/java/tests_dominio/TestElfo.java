package tests_dominio;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import dominio.Asesino;
import dominio.Elfo;
import dominio.Humano;
import dominio.Item;
import dominio.Personaje;

/**
 * Test de la clase Elfo.
 */
public class TestElfo {
	private static final int SALUD_PRUEBA = 100;
	private static final int ENERGIA_PRUEBA = 5;

	/**
	 * Verifica que el elfo no pueda realizar su habilidad 2 si no tiene la
	 * energia suficiente.
	 */
	@Test
	public void testGolpeLevel() {
		Elfo e = new Elfo("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1,
				new LinkedList<Item>());
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 1, 1,
				new LinkedList<Item>());

		Assert.assertTrue(h.getSalud() == SALUD_PRUEBA);
		if (e.habilidadRaza1(h)) {
			Assert.assertTrue(h.getSalud() < SALUD_PRUEBA);
		} else {
			Assert.assertTrue(h.getSalud() == SALUD_PRUEBA);
		}

		HashMap<String, Object> datos = e.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, ENERGIA_PRUEBA);
		e.actualizar(datos);

		Assert.assertFalse(e.habilidadRaza1(h));
	}

	/**
	 * Verifica que el elfo no pueda realizar su habilidad 2 si no tiene la
	 * energia suficiente.
	 */
	@Test
	public void testAtaqueBosque() {
		Elfo e = new Elfo("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1,
				new LinkedList<Item>());
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 1, 1,
				new LinkedList<Item>());

		Assert.assertTrue(h.getSalud() == SALUD_PRUEBA);
		if (e.habilidadRaza2(h)) {
			Assert.assertTrue(h.getSalud() < SALUD_PRUEBA);
		} else {
			Assert.assertTrue(h.getSalud() == SALUD_PRUEBA);
		}

		HashMap<String, Object> datos = e.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, ENERGIA_PRUEBA);
		e.actualizar(datos);
		Assert.assertFalse(e.habilidadRaza2(h));
	}
}
