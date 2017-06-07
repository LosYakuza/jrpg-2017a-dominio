package tests_dominio;

import org.junit.Test;

import dominio.*;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Assert;

public class TestAtributos {

	@Test
	public void testIncrementarFuerza() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());

		Assert.assertTrue(h.getAtaque() == 22);
		h.asignarPuntosSkills(10, 0, 0);
		Assert.assertTrue(h.getAtaque() > 22);
	}

	@Test
	public void testIncrementarDestreza() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());

		Assert.assertTrue(h.getDefensa() == 10);
		h.asignarPuntosSkills(0, 10, 0);
		Assert.assertTrue(h.getDefensa() > 10);
	}

	@Test
	public void testIncrementarInteligencia() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		Assert.assertTrue(h.getMagia() == 15);
		h.asignarPuntosSkills(0, 0, 10);
		Assert.assertTrue(h.getMagia() > 15);
	}
	
	@Test
	public void testSetters() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		
		h.setNombreRaza("Ballena");
		Assert.assertEquals("Ballena", h.getNombreRaza());
		
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_EXPERIENCIA, 15);
		datos.put(Personaje.ATTR_IDPERSONAJE, 4);
		datos.put(Personaje.ATTR_SALUDTOPE, 400);
		datos.put(Personaje.ATTR_SALUD, 200);
		datos.put(Personaje.ATTR_ENERGIATOPE, 500);
		datos.put(Personaje.ATTR_ENERGIA, 20);
		h.actualizar(datos);
		
		h.setMagia(3);
		Assert.assertEquals(3, h.getMagia());
		
		Assert.assertEquals(15, h.getExperiencia());
		
		Assert.assertEquals(4, h.getIdPersonaje());
		
		Assert.assertEquals(400, h.getSaludTope());

		Assert.assertEquals(200, h.getSalud());
		h.restablecerSalud();
		Assert.assertEquals(400, h.getSalud());
		
		Assert.assertEquals(500, h.getEnergiaTope());
		Assert.assertEquals(20, h.getEnergia());
		h.restablecerEnergia();
		Assert.assertEquals(500, h.getEnergia());
	}

	@Test
	public void testPuedeAtacar() {
		Humano h = new Humano("Mica", new Guerrero(), 1, new LinkedList<Item>());
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIA, 12);
		h.actualizar(datos);
		Assert.assertTrue(h.puedeAtacar());
		datos.put(Personaje.ATTR_ENERGIA, 8);
		h.actualizar(datos);
		Assert.assertFalse(h.puedeAtacar());
	}
	
	@Test
	public void testSerRobadoSalud() {
		Humano h = new Humano("Mica", new Guerrero(), 1, new LinkedList<Item>());
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_SALUD, 10);
		datos.put(Personaje.ATTR_DEFENSA, 40);
		h.actualizar(datos);
		Assert.assertEquals(0, h.serRobadoSalud(30));
		datos = h.getTodo();
		datos.put(Personaje.ATTR_DEFENSA, 10);
		h.actualizar(datos);
		Assert.assertEquals(10, h.serRobadoSalud(30));
		Assert.assertEquals(0, h.getSalud());
	}
	
	@Test
	public void testSerDesernegizado() {
		Humano h = new Humano("Mica", new Guerrero(), 1, new LinkedList<Item>());
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_DEFENSA, 0);
		h.actualizar(datos);
		Assert.assertEquals(0, h.serDesernegizado(-1));
		datos.put(Personaje.ATTR_ENERGIA, 10);
		h.actualizar(datos);
		Assert.assertEquals(10, h.serDesernegizado(20));
		Assert.assertEquals(0, h.getEnergia());
	}
	
	@Test
	public void testSerEnergizado() {
		Humano h = new Humano("Mica", new Guerrero(), 1, new LinkedList<Item>());
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_ENERGIATOPE, 200);
		datos.put(Personaje.ATTR_ENERGIA, 190);
		h.actualizar(datos);
		Assert.assertEquals(190, h.getEnergia());
		h.serEnergizado(20);
		Assert.assertEquals(200, h.getEnergia());
	}
}
