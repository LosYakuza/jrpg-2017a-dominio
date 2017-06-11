package tests_dominio;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import dominio.*;

/**
 * Test de la clase Personaje.
 */
public class TestPersonaje {

	/**
	 * Verifica que se pueda crear un humano de las castas asesino, guerrero y hechicero y que
	 * se puedan cargar sus atributos.
	 */
	@Test
	public void testHumano() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		Assert.assertTrue(h.getSalud() == 105);
		Assert.assertTrue(h.getEnergia() == 105);
		Assert.assertTrue(h.getFuerza() == 15);
		Assert.assertTrue(h.getDestreza() == 10);
		Assert.assertTrue(h.getInteligencia() == 10);
		Assert.assertTrue(h.getNombreRaza() == "Humano");
		Assert.assertTrue(h.getHabilidadesRaza().length == 2);
		Assert.assertTrue(h.getHabilidadesRaza()[0] == "Incentivar");
		Assert.assertTrue(h.getHabilidadesRaza()[1] == "Golpe Fatal");

		Humano h2 = new Humano("Lautaro", new Hechicero(), 2, new LinkedList<Item>());
		Assert.assertTrue(h2.getSalud() == 105);
		Assert.assertTrue(h2.getEnergia() == 105);
		Assert.assertTrue(h2.getFuerza() == 10);
		Assert.assertTrue(h2.getDestreza() == 10);
		Assert.assertTrue(h2.getInteligencia() == 15);
		Assert.assertTrue(h2.getNombreRaza() == "Humano");
		Assert.assertTrue(h2.getHabilidadesRaza().length == 2);
		Assert.assertTrue(h2.getHabilidadesRaza()[0] == "Incentivar");
		Assert.assertTrue(h2.getHabilidadesRaza()[1] == "Golpe Fatal");

		Humano h3 = new Humano("Hernan", new Asesino(), 3, new LinkedList<Item>());
		Assert.assertTrue(h3.getSalud() == 105);
		Assert.assertTrue(h3.getEnergia() == 105);
		Assert.assertTrue(h3.getFuerza() == 10);
		Assert.assertTrue(h3.getDestreza() == 15);
		Assert.assertTrue(h3.getInteligencia() == 10);
		Assert.assertTrue(h3.getNombreRaza() == "Humano");
		Assert.assertTrue(h3.getHabilidadesRaza().length == 2);
		Assert.assertTrue(h3.getHabilidadesRaza()[0] == "Incentivar");
		Assert.assertTrue(h3.getHabilidadesRaza()[1] == "Golpe Fatal");
	}

	/**
	 * Verifica que se pueda crear un elfo de las castas asesino, guerrero y hechicero y que
	 * se puedan cargar sus atributos.
	 */
	@Test
	public void testElfo() {
		Elfo e = new Elfo("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		Assert.assertTrue(e.getSalud() == 100);
		Assert.assertTrue(e.getEnergia() == 110);
		Assert.assertTrue(e.getFuerza() == 15);
		Assert.assertTrue(e.getDestreza() == 10);
		Assert.assertTrue(e.getInteligencia() == 10);
		Assert.assertTrue(e.getNombreRaza() == "Elfo");
		Assert.assertTrue(e.getHabilidadesRaza().length == 2);
		Assert.assertTrue(e.getHabilidadesRaza()[0] == "Golpe Level");
		Assert.assertTrue(e.getHabilidadesRaza()[1] == "Ataque Bosque");

		Elfo e2 = new Elfo("Lautaro", new Hechicero(), 2, new LinkedList<Item>());
		Assert.assertTrue(e2.getSalud() == 100);
		Assert.assertTrue(e2.getEnergia() == 110);
		Assert.assertTrue(e2.getFuerza() == 10);
		Assert.assertTrue(e2.getDestreza() == 10);
		Assert.assertTrue(e2.getInteligencia() == 15);
		Assert.assertTrue(e2.getNombreRaza() == "Elfo");
		Assert.assertTrue(e2.getHabilidadesRaza().length == 2);
		Assert.assertTrue(e2.getHabilidadesRaza()[0] == "Golpe Level");
		Assert.assertTrue(e2.getHabilidadesRaza()[1] == "Ataque Bosque");

		Elfo e3 = new Elfo("Hernan", new Asesino(), 3, new LinkedList<Item>());
		Assert.assertTrue(e3.getSalud() == 100);
		Assert.assertTrue(e3.getEnergia() == 110);
		Assert.assertTrue(e3.getFuerza() == 10);
		Assert.assertTrue(e3.getDestreza() == 15);
		Assert.assertTrue(e3.getInteligencia() == 10);
		Assert.assertTrue(e3.getNombreRaza() == "Elfo");
		Assert.assertTrue(e3.getHabilidadesRaza().length == 2);
		Assert.assertTrue(e3.getHabilidadesRaza()[0] == "Golpe Level");
		Assert.assertTrue(e3.getHabilidadesRaza()[1] == "Ataque Bosque");
	}

	/**
	 * Verifica que se pueda crear un orco de las castas asesino, guerrero y hechicero y que
	 * se puedan cargar sus atributos.
	 */
	@Test
	public void testOrco() {
		Orco o = new Orco("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		Assert.assertTrue(o.getSalud() == 110);
		Assert.assertTrue(o.getEnergia() == 100);
		Assert.assertTrue(o.getFuerza() == 15);
		Assert.assertTrue(o.getDestreza() == 10);
		Assert.assertTrue(o.getInteligencia() == 10);
		Assert.assertTrue(o.getNombreRaza() == "Orco");
		Assert.assertTrue(o.getHabilidadesRaza().length == 2);
		Assert.assertTrue(o.getHabilidadesRaza()[0] == "Golpe Defensa");
		Assert.assertTrue(o.getHabilidadesRaza()[1] == "Mordisco de Vida");

		Orco o2 = new Orco("Lautaro", new Hechicero(), 2, new LinkedList<Item>());
		Assert.assertTrue(o2.getSalud() == 110);
		Assert.assertTrue(o2.getEnergia() == 100);
		Assert.assertTrue(o2.getFuerza() == 10);
		Assert.assertTrue(o2.getDestreza() == 10);
		Assert.assertTrue(o2.getInteligencia() == 15);
		Assert.assertTrue(o2.getNombreRaza() == "Orco");
		Assert.assertTrue(o2.getHabilidadesRaza().length == 2);
		Assert.assertTrue(o2.getHabilidadesRaza()[0] == "Golpe Defensa");
		Assert.assertTrue(o2.getHabilidadesRaza()[1] == "Mordisco de Vida");

		Orco o3 = new Orco("Hernan", new Asesino(), 3, new LinkedList<Item>());
		Assert.assertTrue(o3.getSalud() == 110);
		Assert.assertTrue(o3.getEnergia() == 100);
		Assert.assertTrue(o3.getFuerza() == 10);
		Assert.assertTrue(o3.getDestreza() == 15);
		Assert.assertTrue(o3.getInteligencia() == 10);
		Assert.assertTrue(o3.getNombreRaza() == "Orco");
		Assert.assertTrue(o3.getHabilidadesRaza().length == 2);
		Assert.assertTrue(o3.getHabilidadesRaza()[0] == "Golpe Defensa");
		Assert.assertTrue(o3.getHabilidadesRaza()[1] == "Mordisco de Vida");
	}
}
