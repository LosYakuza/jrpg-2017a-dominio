package tests_dominio;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import dominio.Asesino;
import dominio.Casta;
import dominio.Guerrero;
import dominio.Hechicero;
import dominio.Humano;
import dominio.Personaje;

/**
 * Test de la clase Casta.
 */
public class TestCasta {

	/**
	 * Verifica que los valores de las distintas castas sean los correctos.
	 */
	@Test
	public void testCasta() {
		final int danioCriticoPrueba = 100;
		final int probEvitarDanioPrueba = 150;
		final int probGolpeCriticoPrueba = 160;
		final double centecima = 0.01;

		Casta a = new Asesino();
		Casta g = new Guerrero();
		Casta h = new Hechicero();

		a.setDañoCritico(danioCriticoPrueba);
		Assert.assertEquals(danioCriticoPrueba, a.getDañoCritico(), centecima);

		g.setProbabilidadEvitarDaño(probEvitarDanioPrueba);
		Assert.assertEquals(probEvitarDanioPrueba, g.getProbabilidadEvitarDaño(), centecima);

		h.setProbabilidadGolpeCritico(probGolpeCriticoPrueba);
		Assert.assertEquals(probGolpeCriticoPrueba, h.getProbabilidadGolpeCritico(), centecima);

		Assert.assertEquals("Asesino", a.getNombreCasta());
		Assert.assertEquals("Guerrero", g.getNombreCasta());
		Assert.assertEquals("Hechicero", h.getNombreCasta());

		Personaje a1 = new Humano("Mica", new Asesino(), 1);
		Assert.assertEquals("Asesino", a1.getCasta().getNombreCasta());
		HashMap<String, Object> datos = a1.getTodo();
		datos.put(Personaje.ATTR_CASTA, new Guerrero());
		a1.actualizar(datos);
		Assert.assertEquals("Guerrero", a1.getCasta().getNombreCasta());
	}

	/**
	 * Verifica que las habilidades de las tres castas sean correctas.
	 */
	@Test
	public void testArrayHabilidadesCasta() {
		final int cantHabilidades = 3;

		Personaje a = new Humano("Mica", new Asesino(), 1);
		Personaje g = new Humano("Mica", new Guerrero(), 1);
		Personaje h = new Humano("Mica", new Hechicero(), 1);

		Assert.assertEquals(cantHabilidades, a.getHabilidadesCasta().length);
		Assert.assertEquals("Golpe Critico", a.getHabilidadesCasta()[0]);
		Assert.assertEquals("Aumentar Evasion", a.getHabilidadesCasta()[1]);
		Assert.assertEquals("Robar", a.getHabilidadesCasta()[2]);

		Assert.assertEquals(cantHabilidades, g.getHabilidadesCasta().length);
		Assert.assertEquals("Ataque Doble", g.getHabilidadesCasta()[0]);
		Assert.assertEquals("Aumentar Defensa", g.getHabilidadesCasta()[1]);
		Assert.assertEquals("Ignorar Defensa", g.getHabilidadesCasta()[2]);

		Assert.assertEquals(cantHabilidades, h.getHabilidadesCasta().length);
		Assert.assertEquals("Bola de Fuego", h.getHabilidadesCasta()[0]);
		Assert.assertEquals("Curar Aliado", h.getHabilidadesCasta()[1]);
		Assert.assertEquals("Robar Energia y Salud", h.getHabilidadesCasta()[2]);
	}
}
