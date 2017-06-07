package tests_dominio;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import dominio.Asesino;
import dominio.Humano;
import dominio.Item;
import dominio.ModificadorSegunItem;
import dominio.MyRandomStub;
import dominio.Operacion;
import dominio.Personaje;

/**
 * Clase test para Item, ModificadorSegunItem y Operacion.
 */
public class TestItem {

	@Test
	public void testItem() {
		Personaje a = new Humano("Mica", new Asesino(10,10,10), 1, new LinkedList<Item>());

		ModificadorSegunItem modSuma = new ModificadorSegunItem(10, Operacion.SUMA);
		ModificadorSegunItem modMultiplicacion = new ModificadorSegunItem(2, Operacion.MULTIPLICACION);
		ModificadorSegunItem modPorcentaje = new ModificadorSegunItem(20, Operacion.PORCENTAJE);

		Assert.assertEquals(105, a.getSalud());

		a.guardarItemEnInventario(new Item(modSuma));
		Assert.assertEquals(115, a.getSalud());
		a.guardarItemEnInventario(new Item(modSuma));
		Assert.assertEquals(125, a.getSalud());

		a.guardarItemEnInventario(new Item(modPorcentaje));
		Assert.assertEquals(146, a.getSalud());

		a.guardarItemEnInventario(new Item(modMultiplicacion));
		Assert.assertEquals(251, a.getSalud());
	}
}
