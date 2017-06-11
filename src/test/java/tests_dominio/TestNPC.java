package tests_dominio;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import dominio.Item;
import dominio.NonPlayableCharacter;

/**
 * Test de la clase NPC.
 */
public class TestNPC {

	/**
	 * Test del metodo ortorgarExp.
	 */
	@Test
	public void testOtorgarExp() {
		NonPlayableCharacter npc = new NonPlayableCharacter("Gigante", 1, -1, new LinkedList<Item>());
		Assert.assertEquals(30, npc.otorgarExp());
	}

	/**
	 * Verifica que los stats sean los correspondientes por cada dificultad.
	 */
	@Test
	public void testStatsPorDificultad() {
		NonPlayableCharacter npc0 = new NonPlayableCharacter("Mica", 1, 0, new LinkedList<Item>());
		NonPlayableCharacter npc1 = new NonPlayableCharacter("Mica", 1, 1, new LinkedList<Item>());
		NonPlayableCharacter npc2 = new NonPlayableCharacter("Mica", 1, 2, new LinkedList<Item>());
		NonPlayableCharacter npc3 = new NonPlayableCharacter("Mica", 1, 3, new LinkedList<Item>());

		Assert.assertEquals(10, npc0.getFuerza());
		Assert.assertEquals(30, npc0.getSalud());
		Assert.assertEquals(2, npc0.getDefensa());

		Assert.assertEquals(20, npc1.getFuerza());
		Assert.assertEquals(40, npc1.getSalud());
		Assert.assertEquals(5, npc1.getDefensa());

		Assert.assertEquals(30, npc2.getFuerza());
		Assert.assertEquals(50, npc2.getSalud());
		Assert.assertEquals(4, npc2.getDefensa());

		Assert.assertEquals(0, npc3.getFuerza());
		Assert.assertEquals(0, npc3.getSalud());
		Assert.assertEquals(0, npc3.getDefensa());
	}

	/**
	 * Verifica que el NPC pueda atacar.
	 */
	@Test
	public void testAtaque() {
		NonPlayableCharacter npc = new NonPlayableCharacter("Mica", 1, 0, new LinkedList<Item>());
		npc.setAtaque(100);
		Assert.assertEquals(100, npc.getAtaque());
		Assert.assertEquals(npc.getFuerza(), npc.getAtaque());
	}
}
