package tests_dominio;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import dominio.Asesino;
import dominio.Elfo;
import dominio.Hechicero;
import dominio.Humano;
import dominio.Personaje;

/**
 * Test de la clase Humano.
 */
public class TestHumano {
	private static final int SALUD_PRUEBA = 100;
	private static final int ENERGIA_PRUEBA = 5;

	/**
	 * Test de la habilidad Incentivar.
	 */
	@Test
	public void testIncentivar() {
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 55, 20, 30, new Hechicero(0.2, 0.3, 1.5), 0, 1, 1);
		Elfo e = new Elfo("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1);

		Assert.assertEquals(37, e.getAtaque());
		h.habilidadRaza1(e);
		Assert.assertTrue(e.getAtaque() > 37);

		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, ENERGIA_PRUEBA);
		h.actualizar(datos);

		Assert.assertFalse(h.habilidadRaza1(e));
	}

	/**
	 * Test de la habilidad Golpe Fatal.
	 */
	@Test
	public void testGolpeFatal() {
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 55, 20, 30, new Hechicero(0.2, 0.3, 1.5), 0, 1, 1);
		Elfo e = new Elfo("Nico", SALUD_PRUEBA, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1);

		Assert.assertEquals(100, h.getEnergia());
		Assert.assertEquals(SALUD_PRUEBA, e.getSalud());
		if (h.habilidadRaza2(e)) {
			Assert.assertEquals(70, e.getSalud());
			Assert.assertEquals(50, h.getEnergia());
		} else {
			Assert.assertEquals(90, h.getEnergia());
			Assert.assertEquals(SALUD_PRUEBA, e.getSalud());
		}

		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, ENERGIA_PRUEBA);
		h.actualizar(datos);

		Assert.assertFalse(h.habilidadRaza2(e));
	}
}
