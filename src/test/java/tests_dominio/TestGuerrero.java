package tests_dominio;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import dominio.Asesino;
import dominio.Elfo;
import dominio.Guerrero;
import dominio.Humano;
import dominio.Item;
import dominio.NonPlayableCharacter;
import dominio.Personaje;

/**
 * Test de la clase Guerrero.
 */
public class TestGuerrero {
	private static final int SALUD_PRUEBA = 100;
	private static final int ENERGIA_PRUEBA = 5;

	/**
	 * Test de la habilidad Doble Golpe.
	 */
	@Test
	public void testDobleGolpe() {
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Guerrero(0.2, 0.3, 1.5), 0, 1, 1,
				new LinkedList<Item>());
		Elfo e = new Elfo("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1,
				new LinkedList<Item>());

		Assert.assertEquals(100, e.getSalud());
		if (h.habilidadCasta1(e)) {
			Assert.assertTrue(e.getSalud() < 100);
		} else {
			Assert.assertEquals(100, e.getSalud());
		}

		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, ENERGIA_PRUEBA);
		h.actualizar(datos);
		Assert.assertFalse(h.habilidadCasta1(e));
	}

	/**
	 * Test de la habilidad Auto Defensa.
	 */
	@Test
	public void testAutoDefensa() {
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Guerrero(0.2, 0.3, 1.5), 0, 1, 1,
				new LinkedList<Item>());

		Assert.assertEquals(20, h.getDefensa());
		h.habilidadCasta2(null);
		Assert.assertEquals(65, h.getDefensa());

		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, ENERGIA_PRUEBA);
		h.actualizar(datos);
		Assert.assertFalse(h.habilidadCasta2(null));
	}

	/**
	 * Test de la habilidad Ignorar Defensa.
	 */
	@Test
	public void testIgnoraDefensa() {
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Guerrero(0.2, 0.3, 1.5), 0, 1, 1,
				new LinkedList<Item>());
		Elfo e = new Elfo("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1,
				new LinkedList<Item>());

		Assert.assertEquals(SALUD_PRUEBA, e.getSalud());
		if (h.habilidadCasta3(e)) {
			Assert.assertTrue(e.getSalud() < SALUD_PRUEBA);
		} else {
			Assert.assertEquals(SALUD_PRUEBA, e.getSalud());
		}

		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, ENERGIA_PRUEBA);
		h.actualizar(datos);
		Assert.assertFalse(h.habilidadCasta3(e));

		NonPlayableCharacter npc = new NonPlayableCharacter("Mica", 2, 1, new LinkedList<Item>());
		int salud = npc.getSalud();
		datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, 15);
		h.actualizar(datos);
		Assert.assertFalse(h.habilidadCasta3(npc));
		Assert.assertEquals(salud, npc.getSalud());
		Assert.assertEquals(ENERGIA_PRUEBA, h.getEnergia());
	}

}
