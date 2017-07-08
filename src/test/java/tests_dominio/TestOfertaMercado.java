package tests_dominio;

import org.junit.Assert;
import org.junit.Test;

import dominio.Item;
import dominio.OfertaMercado;

/**
 * Clase test oferta mercado
 */
public class TestOfertaMercado {

	/**
	 * Test de getters y setters para que no impacte en Coverage
	 */
	@Test
	public void test() {
		OfertaMercado om = new OfertaMercado(1, 1, Item.nameItems[0], 1);
		om.setIdItem(1);
		om.setIdOferta(1);
		om.setIdPersonaje(1);
		om.setNameItemRequerido(Item.nameItems[0]);
		Assert.assertEquals(1, om.getIdItem());
		Assert.assertEquals(1, om.getIdOferta());
		Assert.assertEquals(1, om.getIdPersonaje());
		Assert.assertEquals(Item.nameItems[0], om.getNameItemRequerido());
	}
}
