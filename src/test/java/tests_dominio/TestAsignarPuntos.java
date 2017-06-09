package tests_dominio;

import org.junit.Test;

import dominio.*;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Assert;

public class TestAsignarPuntos {

	@Test
	public void testAumentarSalud_tope() {
		Personaje.cargarTablaNivel();

		Humano h = new Humano("Nicolas",new Guerrero(),1, new LinkedList<Item>());
		Assert.assertTrue(h.getSaludTope()==105);
		h.ganarExperiencia(50);
		Assert.assertTrue(h.getSaludTope()==130);
		}

	@Test
	public void testAumentarEnergia_tope() {
		Personaje.cargarTablaNivel();

		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		Assert.assertTrue(h.getEnergiaTope() == 105);
		h.ganarExperiencia(50);
		Assert.assertTrue(h.getEnergiaTope() == 125);
	}

	@Test
	public void testMasDe200Puntos() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_FUERZA, 199);
		datos.put(Personaje.ATTR_DESTREZA, 199);
		datos.put(Personaje.ATTR_INTELIGENCIA, 199);
		h.actualizar(datos);
		h.asignarPuntosSkills(2, 2, 2);
		Assert.assertTrue(h.getFuerza() == 199);
		Assert.assertTrue(h.getDestreza() == 199);
		Assert.assertTrue(h.getInteligencia() == 199);
	}
}
