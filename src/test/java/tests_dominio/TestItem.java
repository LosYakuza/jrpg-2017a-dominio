package tests_dominio;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import dominio.Elfo;
import dominio.Guerrero;
import dominio.Item;
import dominio.ModificadorSegunItem;
import dominio.MyRandom;
import dominio.MyRandomStub;
import dominio.Operacion;
import dominio.Personaje;

/**
 * Clase test para Item, ModificadorSegunItem y Operacion.
 */
public class TestItem {

	/**
	 * Test clase Item y ModificadorSegunItem dado un "random".
	 */
	@Test
	public void testItemUsingRandomStub() {
		final double rndDouble = 0.5;
		final int valorModificador = 10;

		Item item = new Item(new MyRandomStub(rndDouble));
		//No hago mucho test porque no tiene sentido. El random stub devuelve un valor fijo.
		//Sirve más para ver que la funcionalidad de random no esté rota.
		//Devuelve operación multiplicación.
		Assert.assertEquals(1, item.getModSalud().getValor());
		Assert.assertEquals(0, item.getModifSalud(valorModificador));

		Assert.assertEquals(1, item.getModFuerza().getValor());
		Assert.assertEquals(0, item.getModifFuerza(valorModificador));

		Assert.assertEquals(1, item.getModDestreza().getValor());
		Assert.assertEquals(0, item.getModifDestreza(valorModificador));

		Assert.assertEquals(1, item.getModInteligencia().getValor());
		Assert.assertEquals(0, item.getModifInteligencia(valorModificador));

		Assert.assertEquals(1, item.getModEnergia().getValor());
		Assert.assertEquals(0, item.getModifEnergia(valorModificador));
	}

	/**
	 * Test items con valores variados, se verifica el incremento en el Personaje
	 */
	@Test
	public void testItem() {
		final int cinco = 5;
		final int diez = 10;

		ModificadorSegunItem modSalud = new ModificadorSegunItem(cinco, Operacion.SUMA);
		ModificadorSegunItem modFuerza = new ModificadorSegunItem(2, Operacion.MULTIPLICACION);
		ModificadorSegunItem modDestreza = new ModificadorSegunItem(diez, Operacion.PORCENTAJE);
		ModificadorSegunItem modInteligencia = new ModificadorSegunItem(0, Operacion.SUMA);
		ModificadorSegunItem modEnergia = new ModificadorSegunItem(1, Operacion.SUMA);
		Item item = new Item(1, Item.nameItems[1], modSalud, modFuerza, modDestreza,
				modInteligencia, modEnergia);

		Item item2 = new Item(1, "Amuleto1",
				new ModificadorSegunItem(1, Operacion.SUMA),
				new ModificadorSegunItem(1, Operacion.SUMA),
				new ModificadorSegunItem(1, Operacion.SUMA),
				new ModificadorSegunItem(1, Operacion.SUMA),
				new ModificadorSegunItem(1, Operacion.SUMA));


		Assert.assertEquals("Amuleto2", item.getNombre());
		Assert.assertEquals(1, item.getIdItem());

		final int salud = 50;
		final int energia = 70;
		final int fuerza = 20;
		final int destreza = 20;
		final int inteligencia = 20;
		LinkedList<Item> inventario = new LinkedList<Item>();
		inventario.add(item);
		inventario.add(item2);

		Elfo elfo = new Elfo("Mica", salud, energia, fuerza, destreza, inteligencia, new Guerrero(),
				0, 1, 1, inventario);

		Assert.assertEquals(salud + cinco + 1, elfo.getSaludTope());
		Assert.assertEquals(salud + cinco + 1, elfo.getSalud());
		Assert.assertEquals(fuerza * 2 + 1, elfo.getFuerza());
		Assert.assertEquals(destreza + 1 + 2, elfo.getDestreza());
		Assert.assertEquals(inteligencia + 1, elfo.getInteligencia());
		Assert.assertEquals(energia + 1 + 1, elfo.getEnergiaTope());
		Assert.assertEquals(energia + 1 + 1, elfo.getEnergia());

		Assert.assertEquals(salud, elfo.getSaludTopeBase());
		Assert.assertEquals(fuerza, elfo.getFuerzaBase());
		Assert.assertEquals(destreza, elfo.getDestrezaBase());
		Assert.assertEquals(inteligencia, elfo.getInteligenciaBase());
		Assert.assertEquals(energia, elfo.getEnergiaTopeBase());
	}

	/**
	 * Test para verificar que el inventario tenga hasta 9 ítems.
	 */
	@Test
	public void testMaximoNueveItems() {
		LinkedList<Item> inventario = new LinkedList<Item>();
		MyRandom rnd = new MyRandom();

		Item item1 = new Item(rnd);
		Item item2 = new Item(rnd);
		Item item3 = new Item(rnd);
		Item item4 = new Item(rnd);
		Item item5 = new Item(rnd);
		Item item6 = new Item(rnd);
		Item item7 = new Item(rnd);
		Item item8 = new Item(rnd);
		Item item9 = new Item(rnd);
		Item item10 = new Item(rnd);

		Elfo elfo = new Elfo("Mica", new Guerrero(), 1, inventario);
		elfo.guardarItemEnInventario(item1);
		elfo.guardarItemEnInventario(item2);
		elfo.guardarItemEnInventario(item3);
		elfo.guardarItemEnInventario(item4);
		elfo.guardarItemEnInventario(item5);
		elfo.guardarItemEnInventario(item6);
		elfo.guardarItemEnInventario(item7);
		elfo.guardarItemEnInventario(item8);

		//9 ítems guardados.
		elfo.guardarItemEnInventario(item9);
		Assert.assertEquals(Personaje.CANT_MAX_ITEMS, elfo.getInventario().size());

		//No debería guardar el décimo ítem que reciba.
		elfo.guardarItemEnInventario(item10);
		Assert.assertEquals(Personaje.CANT_MAX_ITEMS, elfo.getInventario().size());
	}
}
