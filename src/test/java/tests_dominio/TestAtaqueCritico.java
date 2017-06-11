package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.Guerrero;
import dominio.Humano;

/**
 * Test de ataque critico.
 */
public class TestAtaqueCritico {
	private static final double INCREMENTO_GOLPE_CRITICO = 1.5;

	/**
	 * Verifica que un golpe critico tenga un 50% mas de daño.
	 */
	@Test
	public void testgolpeCrit() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		Assert.assertEquals(h.getAtaque() * INCREMENTO_GOLPE_CRITICO, h.golpeCritico(), 1);
	}
}
