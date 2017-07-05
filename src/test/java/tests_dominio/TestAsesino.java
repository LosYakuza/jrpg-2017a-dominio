package tests_dominio;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import dominio.Asesino;
import dominio.Hechicero;
import dominio.Humano;
import dominio.Item;
import dominio.MyRandomStub;
import dominio.Personaje;
import dominio.RandomGenerator;

/**
 * Test de la clase Asesino.
 */
public class TestAsesino {
	private static final int SALUD_HUMANO_NIVEL_1 = 105;
	private static final int SALUD_HUMANO_NIVEL_1_DANIADO = 93;
	private static final int SALUD_PRUEBA = 100;
	private static final int ENERGIA_PRUEBA = 5;

	/**
	 * Test de la habilidad robar.
	 */
	@Test
	public void testRobar() {
		Humano h = new Humano("Nicolas", new Asesino(), 1, new LinkedList<Item>());
		Humano h2 = new Humano("Lautaro", new Hechicero(), 2, new LinkedList<Item>());
		Assert.assertFalse(h.habilidadCasta3(h2));
	}

	/**
	 * Test de la habilidad golpe critico.
	 */
	@Test
	public void testCritico() {
		RandomGenerator rnd = new MyRandomStub(MyRandomStub.HDOUBLE);
		Humano h = new Humano("Nicolas", new Asesino(), 1, new LinkedList<Item>());
		h.setRandomGenerator(rnd);
		Humano h2 = new Humano("Lautaro", new Hechicero(), 2, new LinkedList<Item>());
		h2.setRandomGenerator(rnd);

		Assert.assertEquals(SALUD_HUMANO_NIVEL_1, h2.getSalud());

		Assert.assertTrue(h.habilidadCasta1(h2));
		Assert.assertEquals(SALUD_HUMANO_NIVEL_1_DANIADO, h2.getSalud());

		h2.setRandomGenerator(new MyRandomStub(0)); // Para que el ataque falle
		Assert.assertFalse(h.habilidadCasta1(h2));

		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, ENERGIA_PRUEBA);
		h.actualizar(datos);
		Assert.assertFalse(h.habilidadCasta1(h2));
	}

	/**
	 * Test de la habilidad aumentar evasion.
	 */
	@Test
	public void testProbEvasion() {
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Asesino(0.2, 0, 1.5), 0, 1, 1,
				new LinkedList<Item>());

		Assert.assertEquals(0.0, h.getCasta().getProbabilidadEvitarDaño(), 0.01);
		h.habilidadCasta2(null);
		Assert.assertEquals(0.05, h.getCasta().getProbabilidadEvitarDaño(), 0.01);
		h.habilidadCasta2(null);
		Assert.assertEquals(0.1, h.getCasta().getProbabilidadEvitarDaño(), 0.01);

		h.habilidadCasta2(null);
		h.habilidadCasta2(null);
		h.habilidadCasta2(null);
		Assert.assertEquals(0.2, h.getCasta().getProbabilidadEvitarDaño(), 0.01);

		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, ENERGIA_PRUEBA);
		h.actualizar(datos);
		Assert.assertFalse(h.habilidadCasta2(null));
	}
}
