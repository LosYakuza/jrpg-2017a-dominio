package tests_dominio;

import org.junit.Test;

import dominio.*;
import org.junit.Assert;

public class TestAtributos {

	@Test
	public void testIncrementarFuerza() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1);

		Assert.assertTrue(h.getAtaque() == 22);
		h.asignarPuntosSkills(10, 0, 0);
		Assert.assertTrue(h.getAtaque() > 22);
	}

	@Test
	public void testIncrementarDestreza() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1);

		Assert.assertTrue(h.getDefensa() == 10);
		h.asignarPuntosSkills(0, 10, 0);
		Assert.assertTrue(h.getDefensa() > 10);
	}

	@Test
	public void testIncrementarInteligencia() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		Assert.assertTrue(h.getMagia() == 15);
		h.asignarPuntosSkills(0, 0, 10);
		Assert.assertTrue(h.getMagia() > 15);
	}
	
	@Test
	public void testSetters() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1);
		
		h.setNombreRaza("Ballena");
		Assert.assertEquals("Ballena", h.getNombreRaza());
		
		h.setMagia(3);
		Assert.assertEquals(3, h.getMagia());
		
		h.setExperiencia(15);
		Assert.assertEquals(15, h.getExperiencia());
		
		h.setIdPersonaje(4);
		Assert.assertEquals(4, h.getIdPersonaje());
		
		h.setSaludTope(400);
		Assert.assertEquals(400, h.getSaludTope());
		h.setSalud(200);
		Assert.assertEquals(200, h.getSalud());
		h.restablecerSalud();
		Assert.assertEquals(400, h.getSalud());
		
		h.setEnergiaTope(500);
		Assert.assertEquals(500, h.getEnergiaTope());
		h.setEnergia(20);
		Assert.assertEquals(20, h.getEnergia());
		h.restablecerEnergia();
		Assert.assertEquals(500, h.getEnergia());
	}

	@Test
	public void testPuedeAtacar() {
		Humano h = new Humano("Mica", new Guerrero(), 1);
		h.setEnergia(12);
		Assert.assertTrue(h.puedeAtacar());
		h.setEnergia(8);
		Assert.assertFalse(h.puedeAtacar());
	}
	
	@Test
	public void testSerRobadoSalud() {
		Humano h = new Humano("Mica", new Guerrero(), 1);
		h.setSalud(10);
		h.setDefensa(40);
		Assert.assertEquals(0, h.serRobadoSalud(30));
		
		h.setDefensa(10);
		Assert.assertEquals(10, h.serRobadoSalud(30));
		Assert.assertEquals(0, h.getSalud());
	}
	
	@Test
	public void testSerDesernegizado() {
		Humano h = new Humano("Mica", new Guerrero(), 1);
		h.setDefensa(0);
		Assert.assertEquals(0, h.serDesernegizado(-1));
		
		h.setEnergia(10);
		Assert.assertEquals(10, h.serDesernegizado(20));
		Assert.assertEquals(0, h.getEnergia());
	}
	
	@Test
	public void testSerEnergizado() {
		Humano h = new Humano("Mica", new Guerrero(), 1);
		h.setEnergiaTope(200);
		h.setEnergia(190);
		Assert.assertEquals(190, h.getEnergia());
		h.serEnergizado(20);
		Assert.assertEquals(200, h.getEnergia());
	}
}
