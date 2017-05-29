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

public class TestHechicero {

	@Test
	public void testCurar() {
		Humano h = new Humano("Nico", 100, 100, 55, 20, 30, new Hechicero(0.2, 0.3, 1.5), 0, 1, 1);
		Elfo e = new Elfo("Nico", 100, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1);

		Assert.assertEquals(100, e.getSalud());
		
		HashMap<String, Object> datos = e.getTodo();
		datos.put(Personaje.ATTR_SALUD, 65);
		e.actualizar(datos);
		
		Assert.assertEquals(65, e.getSalud());
		h.habilidadCasta2(e);
		Assert.assertTrue(e.getSalud() > 65);
		
		datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, 5);
		h.actualizar(datos);

		Assert.assertFalse(h.habilidadCasta2(e));
		
		NonPlayableCharacter npc = new NonPlayableCharacter("Mica", 2, 1);
		int salud = npc.getSalud();
		datos.put(Personaje.ATTR_ENERGIA, 15);
		h.actualizar(datos);
		Assert.assertFalse(h.habilidadCasta2(npc));
		Assert.assertEquals(salud, npc.getSalud());
		Assert.assertEquals(5, h.getEnergia());	
	}

	@Test
	public void testBolaDeFuego() {
		Humano h = new Humano("Nico", 100, 100, 55, 20, 30, new Hechicero(0.2, 0.3, 1.5), 0, 1, 1);
		Elfo e = new Elfo("Nico", 100, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1);

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
	public void testRobarEnergia_y_Salud() {
		Humano h = new Humano("Nico", 100, 100, 55, 20, 50, new Hechicero(0.2, 0.3, 1.5), 0, 1, 1);
		Elfo e = new Elfo("Nico", 100, 100, 25, 20, 30, new Asesino(0.2, 0.3, 1.5), 0, 3, 1);

		Assert.assertEquals(100, e.getSalud());
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, 50);
		datos.put(Personaje.ATTR_SALUD, 50);
		h.actualizar(datos);
		if (h.habilidadCasta3(e)) {
			Assert.assertTrue(e.getSalud() < 100);
			Assert.assertTrue(h.getEnergia() > 50);
			Assert.assertTrue(h.getSalud() > 50);
		} else {
			Assert.assertEquals(50, h.getSalud());
			Assert.assertTrue(h.getEnergia() < 50);
			Assert.assertEquals(100, e.getSalud());
		}
		
		datos.put(Personaje.ATTR_ENERGIA, 5);
		h.actualizar(datos);
		Assert.assertFalse(h.habilidadCasta3(e));
		
		NonPlayableCharacter npc = new NonPlayableCharacter("Mica", 2, 1);
		int salud = npc.getSalud();
		datos.put(Personaje.ATTR_ENERGIA, 15);
		h.actualizar(datos);
		Assert.assertFalse(h.habilidadCasta3(npc));
		Assert.assertEquals(salud, npc.getSalud());
		Assert.assertEquals(5, h.getEnergia());	
	}
}
