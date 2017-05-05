package tests_dominio;

import org.junit.Assert;
import org.junit.Test;
import dominio.*;

public class TestAliarCombatir {

	@Test
	public void testCrearAlianza() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		Humano h2 = new Humano("Lautaro", new Guerrero(), 1);
		Humano h3 = new Humano("Mica", new Guerrero(), 1);

		Assert.assertNull(h.getClan());
		Assert.assertNull(h2.getClan());
		h.aliar(h2);
		Assert.assertNotNull(h.getClan());
		Assert.assertNotNull(h2.getClan());
		
		h3.crearAlianza("La ballena azul");
		Assert.assertNotNull(h3.getClan());
	}

	@Test
	public void testAliar() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		Humano h2 = new Humano("Lautaro", new Guerrero(), 1);
		Humano h3 = new Humano("Mica", new Guerrero(), 1);
		Alianza a1 = new Alianza("Los CacheFC");
		
		Assert.assertNull(h2.getClan());
		Assert.assertNull(h.getClan());
		h.setClan(a1);
		Assert.assertNotNull(h.getClan());
		h.aliar(h2);
		Assert.assertEquals(h.getClan(), h2.getClan());
		
		Assert.assertNull(h3.getClan());
		h3.setClan(a1);
		Assert.assertFalse(h.aliar(h3));
	}

	@Test
	public void testSalirDeAlianza() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		Humano h2 = new Humano("Lautaro", new Guerrero(), 1);
		Humano h3 = new Humano("Mica", new Guerrero(), 1);
		Alianza a1 = new Alianza("Los CacheFC");
		
		h.setClan(a1);
		h2.setClan(a1);
		
		Assert.assertEquals(2, a1.getAliados().size());
		h.salirDeAlianza();
		Assert.assertEquals(1, a1.getAliados().size());
		Assert.assertEquals(h2, a1.getAliados().get(0));
		
		Assert.assertNull(h3.getClan());
		h3.salirDeAlianza();
		Assert.assertNull(h3.getClan());
	}

	@Test
	public void testDañar() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		Humano h2 = new Humano("Lautaro", new Asesino(), 1);

		Assert.assertEquals(105, h2.getSalud());
		if (h.atacar(h2) != 0) {
			Assert.assertTrue(h2.getSalud() < 105);
		} else {
			Assert.assertEquals(105, h2.getSalud());
		}


		NonPlayableCharacter npc = new NonPlayableCharacter("Agatha", 3, 1);
		Elfo e = new Elfo("Bemille", new Guerrero(), 1);

		Assert.assertEquals(80, npc.getSalud());
		if (e.atacar(npc) != 0) {
			Assert.assertTrue(npc.getSalud() < 80);
		} else {
			Assert.assertEquals(80, npc.getSalud());
		}

		NonPlayableCharacter npc2 = new NonPlayableCharacter("Nicolas", 3, 1);
		Orco o = new Orco("Lautaro", new Hechicero(), 1);

		Assert.assertEquals(110, o.getSalud());
		if (npc2.atacar(o) != 0) {
			Assert.assertTrue(o.getSalud() < 110);
		} else {
			Assert.assertEquals(110, o.getSalud());
		}
		
		e.getCasta().setProbabilidadGolpeCritico(1);
		e.setDestreza(1000000);
		Assert.assertEquals(80, npc2.getSalud());
		if (e.atacar(npc2) != 0) {
			Assert.assertTrue(npc2.getSalud() < 80);
		} else {
			Assert.assertEquals(80, npc2.getSalud());
		}
		
	}

	@Test
	public void testSerDañado() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		NonPlayableCharacter npc = new NonPlayableCharacter("Agatha", 3, 1);
		
		h.getCasta().setProbabilidadEvitarDaño(1);
		Assert.assertEquals(0, h.serAtacado(30));
		
		h.setDefensa(200);
		h.getCasta().setProbabilidadEvitarDaño(0.1);
		Assert.assertEquals(0, h.serAtacado(100));
		
		h.setSalud(20);
		h.serAtacado(500);
		Assert.assertEquals(0, h.getSalud());
		
		npc.setDefensa(160);
		Assert.assertEquals(0, npc.serAtacado(60));
		
		npc.setSalud(20);
		npc.serAtacado(180);
		Assert.assertEquals(-80, npc.getSalud());
	}

}
