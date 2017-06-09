package tests_dominio;

import org.junit.Test;

import dominio.*;

import java.util.LinkedList;

import org.junit.Assert;

public class TestEnemigosExperiencia {

	@Test
	public void testPjvsNPC() {

		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		NonPlayableCharacter npc = new NonPlayableCharacter("Gigante", 1, 0, new LinkedList<Item>());
		Personaje.cargarTablaNivel();
		Assert.assertTrue(h.getExperiencia() == 0);
		while (npc.estaVivo()) {
			h.atacar(npc);
		}
		h.ganarExperiencia(npc.otorgarExp());
		Assert.assertTrue(h.getExperiencia() == 30);
	}

	@Test
	public void testMasFuerteMasExperiencia() {
		NonPlayableCharacter npc = new NonPlayableCharacter("Gigante", 1, 0, new LinkedList<Item>());
		NonPlayableCharacter npc2 = new NonPlayableCharacter("Gigante", 2, 0, new LinkedList<Item>());

		Assert.assertTrue(npc.otorgarExp() < npc2.otorgarExp());
	}

	@Test
	public void testPjvsPj() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		Humano h2 = new Humano("Lautaro", new Guerrero(), 2, new LinkedList<Item>());
		Personaje.cargarTablaNivel();
		Assert.assertTrue(h.getExperiencia() == 0);
		Assert.assertTrue(h2.getExperiencia() == 0);

		while (h2.estaVivo()) {
			h.atacar(h2);
		}

		h.ganarExperiencia(h2.otorgarExp());
		Assert.assertTrue(h.getExperiencia() == 40);
		Assert.assertTrue(h2.getExperiencia() == 0);

	}
}
