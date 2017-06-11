package tests_dominio;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import dominio.Asesino;
import dominio.Guerrero;
import dominio.Hechicero;
import dominio.Humano;
import dominio.MyRandomStub;
import dominio.Orco;
import dominio.Personaje;

/**
 * Test de la clase Orco.
 */
public class TestOrco {
	private static final int SALUD_PRUEBA = 100;
	private static final int ENERGIA_PRUEBA = 5;

	/**
	 * Test de la habilidad Golpe Defensivo.
	 */
	@Test
	public void testGolpeDefensivo() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		Orco o = new Orco("Hernan", new Guerrero(), 1);
		h.setRandomGenerator(new MyRandomStub(MyRandomStub.HDOUBLE));
		o.setRandomGenerator(new MyRandomStub(MyRandomStub.HDOUBLE));
		Assert.assertTrue(h.getSalud() == 105);
		if (o.habilidadRaza1(h)) {
			Assert.assertTrue(h.getSalud() == 95);
		} else {
			Assert.assertTrue(o.getSalud() == 105);
		}

		HashMap<String, Object> datos = o.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, ENERGIA_PRUEBA);
		o.actualizar(datos);
		Assert.assertFalse(o.habilidadRaza1(h));
	}

	/**
	 * Test de la habilidad Mordisco de Vida.
	 */
	@Test
	public void testMordiscoDeVida() {
		Humano h = new Humano("Nico", SALUD_PRUEBA, 100, 55, 20, 30, new Hechicero(0.2, 0.3, 1.5), 0, 1, 1);
		Orco o = new Orco("Nico", SALUD_PRUEBA, 100, 80, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 1, 1);

		Assert.assertEquals(SALUD_PRUEBA, h.getSalud());
		HashMap<String, Object> datos = o.getTodo();
		datos.put(Personaje.ATTR_SALUD, SALUD_PRUEBA);
		o.actualizar(datos);

		if (o.habilidadRaza2(h)) {
			Assert.assertEquals(40, h.getSalud());
			Assert.assertEquals(100, o.getSalud());
		} else {
			Assert.assertEquals(100, o.getSalud());
			Assert.assertEquals(100, h.getSalud());
		}

		datos.put(Personaje.ATTR_ENERGIA, 0);
		o.actualizar(datos);
		Assert.assertFalse(o.habilidadRaza2(h));
	}
}
