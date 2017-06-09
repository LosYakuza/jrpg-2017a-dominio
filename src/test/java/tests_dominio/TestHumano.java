package tests_dominio;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import dominio.Asesino;
import dominio.Elfo;
import dominio.Hechicero;
import dominio.Humano;
import dominio.Item;
import dominio.Personaje;

public class TestHumano {

	@Test
	public void testIncentivar() {
		Humano h = new Humano("Nico", 100, 100, 55, 20, 30, new Hechicero(0.2, 0.3, 1.5), 0, 1, 1, new LinkedList<Item>());
		Elfo e = new Elfo("Nico", 100, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1, new LinkedList<Item>());

		Assert.assertEquals(37, e.getAtaque());
		h.habilidadRaza1(e);
		Assert.assertTrue(e.getAtaque() > 37);
		
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, 5);
		h.actualizar(datos);
		
		Assert.assertFalse(h.habilidadRaza1(e));
	}

	@Test
	public void testGolpeFatal() {
		Humano h = new Humano("Nico", 100, 100, 55, 20, 30, new Hechicero(0.2, 0.3, 1.5), 0, 1, 1, new LinkedList<Item>());
		Elfo e = new Elfo("Nico", 100, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1, new LinkedList<Item>());

		Assert.assertEquals(100, h.getEnergia());
		Assert.assertEquals(100, e.getSalud());
		if (h.habilidadRaza2(e)) {
			Assert.assertEquals(70, e.getSalud());
			Assert.assertEquals(50, h.getEnergia());
		} else {
			Assert.assertEquals(90, h.getEnergia());
			Assert.assertEquals(100, e.getSalud());
		}
		
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, 5);
		h.actualizar(datos);
		
		Assert.assertFalse(h.habilidadRaza2(e));
	}
}
