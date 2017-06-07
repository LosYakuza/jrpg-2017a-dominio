package tests_dominio;

import org.junit.Assert;
import org.junit.Test;
import dominio.*;
import java.util.LinkedList;

public class TestAlianza {

	@Test
	public void testAlianza() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		Humano h2 = new Humano("Lautaro", new Guerrero(), 1, new LinkedList<Item>());
		String nomAlianza = "La ballena azul";
		Alianza a1 = new Alianza(nomAlianza);
		
		LinkedList<Personaje> listaAliados = new LinkedList<Personaje>();
		listaAliados.add(h);
		listaAliados.add(h2);
		
		a1.añadirPersonaje(h);
		a1.añadirPersonaje(h2);
		Assert.assertEquals(listaAliados, a1.getAliados());
		
		Assert.assertEquals(nomAlianza, a1.obtenerNombre());
	}

}
