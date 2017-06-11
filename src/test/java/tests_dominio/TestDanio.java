package tests_dominio;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import dominio.Guerrero;
import dominio.Humano;
import dominio.Item;
import dominio.Orco;

/**
 * Test para los daños causados a un personaje.
 */
public class TestDanio {
	private static final int SALUD_PRUEBA = 100;

	/**
	 * Verifica que por mas que se le cause un daño al personaje mayor que su
	 * salud tope, la salud no baja de 0.
	 */
	@Test
	public void testAtaqueComunYLaSaludNoBajeDe0() {
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 100, 20, 30, new Guerrero(0.2, 0.3, 1.5), 0, 1, 1,
				new LinkedList<Item>());
		Orco o = new Orco("Nico", SALUD_PRUEBA, 100, 15, 0, 30, new Guerrero(0.2, 0, 1.5), 0, 1, 1,
				new LinkedList<Item>());

		Assert.assertTrue(o.getSalud() == SALUD_PRUEBA);
		if (h.atacar(o) != 0) {
			Assert.assertTrue(o.getSalud() == 0);
			h.atacar(o);
			Assert.assertTrue(o.getSalud() == 0);
			h.atacar(o);
			Assert.assertTrue(o.getSalud() == 0);
		} else {
			Assert.assertTrue(o.getSalud() == 0);
		}
	}

	/**
	 * Verifica que un personaje sin salud no pueda atacar.
	 */
	@Test
	public void testLosMuertosNoAtacan() {
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 25, 0, 30, new Guerrero(0.2, 0, 1.5), 0, 1, 1,
				new LinkedList<Item>());
		Orco o = new Orco("Nico", SALUD_PRUEBA, 100, 15, 0, 30, new Guerrero(0.2, 0, 1.5), 0, 1, 1,
				new LinkedList<Item>());

		h.atacar(o);
		h.atacar(o);
		h.atacar(o);
		h.atacar(o);

		o.atacar(h);
		Assert.assertEquals(SALUD_PRUEBA, h.getSalud());
	}
}