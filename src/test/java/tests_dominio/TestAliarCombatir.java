package tests_dominio;
import org.junit.Assert;
import org.junit.Test;
import dominio.*;

public class TestAliarCombatir {

	@Test
	public void testCrearAlianza() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		Humano h2 = new Humano("Lautaro", new Guerrero(), 1);

		Assert.assertNull(h.getClan());
		Assert.assertNull(h2.getClan());
		h.aliar(h2);
		Assert.assertNotNull(h.getClan());
		Assert.assertNotNull(h2.getClan());
	}

	@Test
	public void testDañar() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		Humano h2 = new Humano("Lautaro", new Asesino(), 1);

		Assert.assertTrue(h2.getSalud() == 105);
		if (h.atacar(h2) != 0) {
			Assert.assertTrue(h2.getSalud() < 105);
		} else {
			Assert.assertTrue(h2.getSalud() == 105);
		}


		NonPlayableCharacter npc = new NonPlayableCharacter("Agatha", 3, 1);
		Elfo e = new Elfo("Bemille", new Guerrero(), 1);

		Assert.assertTrue(npc.getSalud() == 80);
		if (e.atacar(npc) != 0) {
			Assert.assertTrue(npc.getSalud() < 80);
		} else {
			Assert.assertTrue(npc.getSalud() == 80);
		}

		NonPlayableCharacter npc2 = new NonPlayableCharacter("Nicolas", 3, 1);
		Orco o = new Orco("Lautaro", new Hechicero(), 1);

		Assert.assertTrue(o.getSalud() == 110);
		if (npc2.atacar(o) != 0) {
			Assert.assertTrue(o.getSalud() < 110);
		} else {
			Assert.assertTrue(o.getSalud() == 110);
		}
	}

	@Test
	public void testAliar() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		Humano h2 = new Humano("Lautaro", new Guerrero(), 1);
		Alianza a1 = new Alianza("Los CacheFC");

		Assert.assertNull(h2.getClan());
		Assert.assertNull(h.getClan());
		h.setClan(a1);
		Assert.assertNotNull(h.getClan());
		h.aliar(h2);
		Assert.assertTrue(h.getClan() == h2.getClan());
	}

}
