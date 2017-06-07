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
import dominio.Personaje;

public class TestElfo {

	@Test
	public void testGolpeLevel() {
		Elfo e = new Elfo("Nico", 100, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1, new LinkedList<Item>());
		Humano h = new Humano("Nico", 100, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 1, 1, new LinkedList<Item>());

		Assert.assertTrue(h.getSalud() == 100);
		if (e.habilidadRaza1(h)) {
			Assert.assertTrue(h.getSalud() < 100);
		} else {
			Assert.assertTrue(h.getSalud() == 100);
		}

		HashMap<String, Object> datos = e.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, 5);
		e.actualizar(datos);

		Assert.assertFalse(e.habilidadRaza1(h));
	}

	@Test
	public void testAtaqueBosque() {
		Elfo e = new Elfo("Nico", 100, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1, new LinkedList<Item>());
		Humano h = new Humano("Nico", 100, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 1, 1, new LinkedList<Item>());

		Assert.assertTrue(h.getSalud() == 100);
		if (e.habilidadRaza2(h)) {
			Assert.assertTrue(h.getSalud() < 100);
		} else {
			Assert.assertTrue(h.getSalud() == 100);
		}

		HashMap<String, Object> datos = e.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, 5);
		e.actualizar(datos);
		Assert.assertFalse(e.habilidadRaza2(h));
	}
}
