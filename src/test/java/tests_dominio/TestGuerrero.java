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

public class TestGuerrero {

	@Test
	public void testDobleGolpe() {
		Humano h = new Humano("Nico", 100, 100, 25, 20, 30, new Guerrero(0.2, 0.3, 1.5), 0, 1, 1, new LinkedList<Item>());
		Elfo e = new Elfo("Nico", 100, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1, new LinkedList<Item>());

		Assert.assertEquals(100, e.getSalud());
		if (h.habilidadCasta1(e)) {
			Assert.assertTrue(e.getSalud() < 100);
		} else {
			Assert.assertEquals(100, e.getSalud());
		}
		
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, 5);
		h.actualizar(datos);
		Assert.assertFalse(h.habilidadCasta1(e));
	}

	@Test
	public void testAutoDefensa() {
		Humano h = new Humano("Nico", 100, 100, 25, 20, 30, new Guerrero(0.2, 0.3, 1.5), 0, 1, 1, new LinkedList<Item>());

		Assert.assertEquals(20, h.getDefensa());
		h.habilidadCasta2(null);
		Assert.assertEquals(65, h.getDefensa());
		
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, 5);
		h.actualizar(datos);
		Assert.assertFalse(h.habilidadCasta2(null));
	}

	@Test
	public void testIgnoraDefensa() {
		Humano h = new Humano("Nico", 100, 100, 25, 20, 30, new Guerrero(0.2, 0.3, 1.5), 0, 1, 1, new LinkedList<Item>());
		Elfo e = new Elfo("Nico", 100, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1, new LinkedList<Item>());

		Assert.assertEquals(100, e.getSalud());
		if (h.habilidadCasta3(e)) {
			Assert.assertTrue(e.getSalud() < 100);
		} else {
			Assert.assertEquals(100, e.getSalud());
		}
		
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, 5);
		h.actualizar(datos);
		Assert.assertFalse(h.habilidadCasta3(e));
		
		NonPlayableCharacter npc = new NonPlayableCharacter("Mica", 2, 1, new LinkedList<Item>());
		int salud = npc.getSalud();
		datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, 15);
		h.actualizar(datos);
		Assert.assertFalse(h.habilidadCasta3(npc));
		Assert.assertEquals(salud, npc.getSalud());
		Assert.assertEquals(5, h.getEnergia());	
	}

}
