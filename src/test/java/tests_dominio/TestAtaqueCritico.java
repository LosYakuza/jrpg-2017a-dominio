package tests_dominio;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import dominio.Guerrero;
import dominio.Humano;
import dominio.Item;

/**
 * Test de ataque critico.
 */
public class TestAtaqueCritico {
	private static final double INCREMENTO_GOLPE_CRITICO = 1.5;

	/**
	 * Verifica que un golpe critico tenga un 50% mas de daño.
	 */
	@Test
	public void testGolpeCrit() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		Assert.assertEquals(h.getAtaque() * INCREMENTO_GOLPE_CRITICO, h.golpeCritico(), 1);
	}
}
