package tests_dominio;

import org.junit.Test;

import dominio.Guerrero;
import dominio.Humano;
import dominio.NonPlayableCharacter;
import dominio.Personaje;

import org.junit.Assert;

/**
 * Test para verificar que los personajes y NPC's den la experiencia correcta
 * despues de morir.
 */
public class TestEnemigosExperiencia {

	/**
	 * Verifica que el NPC de la experiencia que corresponde cuando el personaje
	 * lo mata.
	 */
	@Test
	public void testPjvsNPC() {
		final int experienciaNPC = 30;
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		NonPlayableCharacter npc = new NonPlayableCharacter("Gigante", 1, 0);
		Personaje.cargarTablaNivel();
		Assert.assertTrue(h.getExperiencia() == 0);
		while (npc.estaVivo()) {
			h.atacar(npc);
		}
		h.ganarExperiencia(npc.otorgarExp());
		Assert.assertTrue(h.getExperiencia() == experienciaNPC);
	}

	/**
	 * Verifica que un NPC con mayor nivel de mayor experiencia.
	 */
	@Test
	public void testMasFuerteMasExperiencia() {
		NonPlayableCharacter npc = new NonPlayableCharacter("Gigante", 1, 0);
		NonPlayableCharacter npc2 = new NonPlayableCharacter("Gigante", 2, 0);

		Assert.assertTrue(npc.otorgarExp() < npc2.otorgarExp());
	}

	/**
	 * Verifica que un personaje le de experiencia a otro si es derrotado.
	 */
	@Test
	public void testPjvsPj() {
		final int experienciaPj = 40;
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		Humano h2 = new Humano("Lautaro", new Guerrero(), 2);
		Personaje.cargarTablaNivel();
		Assert.assertTrue(h.getExperiencia() == 0);
		Assert.assertTrue(h2.getExperiencia() == 0);

		while (h2.estaVivo()) {
			h.atacar(h2);
		}

		h.ganarExperiencia(h2.otorgarExp());
		Assert.assertTrue(h.getExperiencia() == experienciaPj);
		Assert.assertTrue(h2.getExperiencia() == 0);

	}
}
