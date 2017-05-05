package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.*;

public class TestCasta {

	@Test
	public void testCasta() {
		Casta a = new Asesino();
		Casta g = new Guerrero();
		Casta h = new Hechicero();

		a.setDañoCritico(100);
		Assert.assertEquals(100, a.getDañoCritico(), 0.01);
		
		g.setProbabilidadEvitarDaño(150);
		Assert.assertEquals(150, g.getProbabilidadEvitarDaño(), 0.01);
		
		h.setProbabilidadGolpeCritico(160);
		Assert.assertEquals(160, h.getProbabilidadGolpeCritico(), 0.01);
		
		Assert.assertEquals("Asesino", a.getNombreCasta());
		Assert.assertEquals("Guerrero", g.getNombreCasta());
		Assert.assertEquals("Hechicero", h.getNombreCasta());
		
		Personaje a1 = new Humano("Mica", new Asesino(), 1);
		Assert.assertEquals("Asesino", a1.getCasta().getNombreCasta());
		a1.setCasta(new Guerrero());
		Assert.assertEquals("Guerrero", a1.getCasta().getNombreCasta());
	}
	
	@Test
	public void testArrayHabilidadesCasta() {
		Personaje a = new Humano("Mica", new Asesino(), 1);
		Personaje g = new Humano("Mica", new Guerrero(), 1);
		Personaje h = new Humano("Mica", new Hechicero(), 1);
		
		Assert.assertEquals(3, a.getHabilidadesCasta().length);
		Assert.assertEquals("Golpe Critico", a.getHabilidadesCasta()[0]);
		Assert.assertEquals("Aumentar Evasion", a.getHabilidadesCasta()[1]);
		Assert.assertEquals("Robar", a.getHabilidadesCasta()[2]);
		
		Assert.assertEquals(3, g.getHabilidadesCasta().length);
		Assert.assertEquals("Ataque Doble", g.getHabilidadesCasta()[0]);
		Assert.assertEquals("Aumentar Defensa", g.getHabilidadesCasta()[1]);
		Assert.assertEquals("Ignorar Defensa", g.getHabilidadesCasta()[2]);
		
		Assert.assertEquals(3, h.getHabilidadesCasta().length);
		Assert.assertEquals("Bola de Fuego", h.getHabilidadesCasta()[0]);
		Assert.assertEquals("Curar Aliado", h.getHabilidadesCasta()[1]);
		Assert.assertEquals("Robar Energia y Salud", h.getHabilidadesCasta()[2]);
	}
}
