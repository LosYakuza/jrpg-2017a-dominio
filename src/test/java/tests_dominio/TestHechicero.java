package tests_dominio;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import dominio.Asesino;
import dominio.Elfo;
import dominio.Hechicero;
import dominio.Humano;
import dominio.NonPlayableCharacter;
import dominio.Personaje;

/**
 * Test de la clase Hechicero.
 */
public class TestHechicero {
	private static final int SALUD_PRUEBA = 100;
	private static final int ENERGIA_PRUEBA = 5;

	/**
	 * Test de la habilidad Curar.
	 */
	@Test
	public void testCurar() {
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 55, 20, 30, new Hechicero(0.2, 0.3, 1.5), 0, 1, 1);
		Elfo e = new Elfo("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1);

		Assert.assertEquals(SALUD_PRUEBA, e.getSalud());

		HashMap<String, Object> datos = e.getTodo();
		datos.put(Personaje.ATTR_SALUD, 65);
		e.actualizar(datos);

		Assert.assertEquals(65, e.getSalud());
		h.habilidadCasta2(e);
		Assert.assertTrue(e.getSalud() > 65);

		datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, ENERGIA_PRUEBA);
		h.actualizar(datos);

		Assert.assertFalse(h.habilidadCasta2(e));

		NonPlayableCharacter npc = new NonPlayableCharacter("Mica", 2, 1);
		int salud = npc.getSalud();
		datos.put(Personaje.ATTR_ENERGIA, 15);
		h.actualizar(datos);
		Assert.assertFalse(h.habilidadCasta2(npc));
		Assert.assertEquals(salud, npc.getSalud());
		Assert.assertEquals(ENERGIA_PRUEBA, h.getEnergia());
	}

	/**
	 * Test de la habilidad Bola de Fuego.
	 */
	@Test
	public void testBolaDeFuego() {
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 55, 20, 30, new Hechicero(0.2, 0.3, 1.5), 0, 1, 1);
		Elfo e = new Elfo("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1);

		Assert.assertEquals(SALUD_PRUEBA, e.getSalud());
		if (h.habilidadCasta1(e)) {
			Assert.assertTrue(e.getSalud() < SALUD_PRUEBA);
		} else {
			Assert.assertEquals(SALUD_PRUEBA, e.getSalud());
		}

		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, ENERGIA_PRUEBA);
		h.actualizar(datos);

		Assert.assertFalse(h.habilidadCasta1(e));
	}

	/**
	 * Test de la habilidad Robar Energia y Salud.
	 */
	@Test
	public void testRobarEnergiaYSalud() {
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 55, 20, 50, new Hechicero(0.2, 0.3, 1.5), 0, 1, 1);
		Elfo e = new Elfo("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1);

		Assert.assertEquals(SALUD_PRUEBA, e.getSalud());
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, 50);
		datos.put(Personaje.ATTR_SALUD, 50);
		h.actualizar(datos);
		if (h.habilidadCasta3(e)) {
			Assert.assertTrue(e.getSalud() < SALUD_PRUEBA);
			Assert.assertTrue(h.getEnergia() > 50);
			Assert.assertTrue(h.getSalud() > 50);
		} else {
			Assert.assertEquals(50, h.getSalud());
			Assert.assertTrue(h.getEnergia() < 50);
			Assert.assertEquals(SALUD_PRUEBA, e.getSalud());
		}

		datos.put(Personaje.ATTR_ENERGIA, ENERGIA_PRUEBA);
		h.actualizar(datos);
		Assert.assertFalse(h.habilidadCasta3(e));

		NonPlayableCharacter npc = new NonPlayableCharacter("Mica", 2, 1);
		int salud = npc.getSalud();
		datos.put(Personaje.ATTR_ENERGIA, 15);
		h.actualizar(datos);
		Assert.assertFalse(h.habilidadCasta3(npc));
		Assert.assertEquals(salud, npc.getSalud());
		Assert.assertEquals(ENERGIA_PRUEBA, h.getEnergia());
	}
}
