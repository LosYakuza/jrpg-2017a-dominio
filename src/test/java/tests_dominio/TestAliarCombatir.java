package tests_dominio;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;
import dominio.*;

/**
 * Test de los metodos invitar a alianza y atacar.
 */
public class TestAliarCombatir {
	private static final int NIVEL_NPC = 3;
	private static final int DIFICULTAD_NPC = 1;
	private static final int SALUD_NPC = 80;
	private static final int SALUD_HUMANO = 105;
	private static final int SALUD_ORCO = 110;
	private static final int DESTREZA_1000000 = 1000000;
	private static final int ATAQUE_30 = 30;
	private static final int ATAQUE_60 = 60;
	private static final int ATAQUE_100 = 100;
	private static final int ATAQUE_180 = 180;
	private static final int ATAQUE_500 = 500;
	private static final double PROB_EVITAR_DANIO_MUY_BAJA = 0.1;
	private static final int SALUD_20 = 20;
	private static final int DEFENSA_200 = 200;
	private static final int DEFENSA_160 = 160;

	/**
	 * Verifica que el metodo aliar de la clase personaje cree una alianza si el
	 * objeto que lo implementa no tiene una alianza, Tambien verifica que el
	 * objeto que se envia por parametro ingrese a la alianza. Ademas, verifica
	 * que si un personaje que no tenia alianza crea una, se va a encontrar en
	 * esa alianza.
	 */
	@Test
	public void testCrearAlianza() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		Humano h2 = new Humano("Lautaro", new Guerrero(), 1, new LinkedList<Item>());
		Humano h3 = new Humano("Mica", new Guerrero(), 1, new LinkedList<Item>());

		Assert.assertNull(h.getClan());
		Assert.assertNull(h2.getClan());
		h.aliar(h2);
		Assert.assertNotNull(h.getClan());
		Assert.assertNotNull(h2.getClan());

		h3.crearAlianza("La ballena azul");
		Assert.assertNotNull(h3.getClan());
	}

	/**
	 * Verifica que no pueda invitarse a un personaje que ya se encuentra dentro
	 * de la alianza. Verifica que un personaje que es invitado pertenezca a la
	 * misma alianza que quien lo invito.
	 */
	@Test
	public void testAliar() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		Humano h2 = new Humano("Lautaro", new Guerrero(), 1, new LinkedList<Item>());
		Humano h3 = new Humano("Mica", new Guerrero(), 1, new LinkedList<Item>());
		Alianza a1 = new Alianza("Los CacheFC");

		Assert.assertNull(h2.getClan());
		Assert.assertNull(h.getClan());
		h.setClan(a1);
		Assert.assertNotNull(h.getClan());
		h.aliar(h2);
		Assert.assertEquals(h.getClan(), h2.getClan());

		Assert.assertNull(h3.getClan());
		h3.setClan(a1);
		Assert.assertFalse(h.aliar(h3));
	}

	/**
	 * Verifica que si un personaje sale de la alianza no tenga alianza y que no
	 * este en la lista de aliados.
	 */
	@Test
	public void testSalirDeAlianza() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		Humano h2 = new Humano("Lautaro", new Guerrero(), 1, new LinkedList<Item>());
		Humano h3 = new Humano("Mica", new Guerrero(), 1, new LinkedList<Item>());
		Alianza a1 = new Alianza("Los CacheFC");

		h.setClan(a1);
		h2.setClan(a1);

		Assert.assertEquals(2, a1.getAliados().size());
		h.salirDeAlianza();
		Assert.assertEquals(1, a1.getAliados().size());
		Assert.assertEquals(h2, a1.getAliados().get(0));

		Assert.assertNull(h3.getClan());
		h3.salirDeAlianza();
		Assert.assertNull(h3.getClan());
	}

	/**
	 * Verifica que si un personaje es atacado su salud disminuye. Verifica que
	 * la vida de un NPC disminuya al ser atacado por un personaje. Verifica que
	 * la vida de un personaje disminuya si es atacado por un NPC.
	 */
	@Test
	public void testDaniar() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		Humano h2 = new Humano("Lautaro", new Asesino(), 1, new LinkedList<Item>());
		h.setRandomGenerator(new MyRandomStub(MyRandomStub.HDOUBLE));
		h2.setRandomGenerator(new MyRandomStub(MyRandomStub.HDOUBLE));
		Assert.assertEquals(SALUD_HUMANO, h2.getSalud());
		System.out.println(h2.getSalud());
		if (h.atacar(h2) != 0) {
			Assert.assertTrue(h2.getSalud() < SALUD_HUMANO);
		} else {
			Assert.assertEquals(SALUD_HUMANO, h2.getSalud());
		}

		NonPlayableCharacter npc = new NonPlayableCharacter("Agatha", NIVEL_NPC, DIFICULTAD_NPC,
				new LinkedList<Item>());
		Elfo e = new Elfo("Bemille", new Guerrero(), 1, new LinkedList<Item>());

		e.setRandomGenerator(new MyRandomStub(MyRandomStub.HDOUBLE));
		Assert.assertEquals(SALUD_NPC, npc.getSalud());
		if (e.atacar(npc) != 0) {
			Assert.assertTrue(npc.getSalud() < SALUD_NPC);
		} else {
			Assert.assertEquals(SALUD_NPC, npc.getSalud());
		}

		NonPlayableCharacter npc2 = new NonPlayableCharacter("Nicolas", NIVEL_NPC, DIFICULTAD_NPC,
				new LinkedList<Item>());
		Orco o = new Orco("Lautaro", new Hechicero(), 1, new LinkedList<Item>());
		npc2.setRandomGenerator(new MyRandomStub(MyRandomStub.HDOUBLE));
		Assert.assertEquals(SALUD_ORCO, o.getSalud());
		if (npc2.atacar(o) != 0) {
			Assert.assertTrue(o.getSalud() < SALUD_ORCO);
		} else {
			Assert.assertEquals(SALUD_ORCO, o.getSalud());
		}

		e.getCasta().setProbabilidadGolpeCritico(1);
		HashMap<String, Object> datos = e.getTodo();
		datos.put("destreza", DESTREZA_1000000);
		e.actualizar(datos);
		Assert.assertEquals(SALUD_NPC, npc2.getSalud());
		if (e.atacar(npc2) != 0) {
			Assert.assertTrue(npc2.getSalud() < SALUD_NPC);
		} else {
			Assert.assertEquals(SALUD_NPC, npc2.getSalud());
		}

	}

	/**
	 * Verifica que si un personaje evita el ataque su salud no disminuya.
	 * Verifica que si la salud de un personaje es mayor al daño que le
	 * inflingen, no recibe daño. Verifica que si un personaje recibe un daño
	 * mayor a su salud total, su salud resultante es 0. Idem todo lo anterior
	 * con un NPC que es dañado.
	 */
	@Test
	public void testSerDaniado() {
		Humano h = new Humano("Nicolas", new Guerrero(), 1, new LinkedList<Item>());
		NonPlayableCharacter npc = new NonPlayableCharacter("Agatha", NIVEL_NPC, DIFICULTAD_NPC,
				new LinkedList<Item>());
		npc.setRandomGenerator(new MyRandomStub(MyRandomStub.HDOUBLE));
		h.setRandomGenerator(new MyRandomStub(MyRandomStub.HDOUBLE));
		h.getCasta().setProbabilidadEvitarDaño(1);
		Assert.assertEquals(0, h.serAtacado(ATAQUE_30));
		HashMap<String, Object> datos = h.getTodo();
		datos.put(Personaje.ATTR_DEFENSA, DEFENSA_200);
		h.actualizar(datos);
		h.getCasta().setProbabilidadEvitarDaño(PROB_EVITAR_DANIO_MUY_BAJA);
		Assert.assertEquals(0, h.serAtacado(ATAQUE_100));

		datos.put(Personaje.ATTR_SALUD, SALUD_20);
		h.actualizar(datos);
		h.serAtacado(ATAQUE_500);
		Assert.assertEquals(0, h.getSalud());

		datos = npc.getTodo();
		datos.put(Personaje.ATTR_DEFENSA, DEFENSA_160);
		npc.actualizar(datos);
		Assert.assertEquals(0, npc.serAtacado(ATAQUE_60));

		datos.put(Personaje.ATTR_SALUD, SALUD_20);
		npc.actualizar(datos);
		npc.serAtacado(ATAQUE_180);
		Assert.assertEquals(-SALUD_NPC, npc.getSalud());
	}

}
