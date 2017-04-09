package capitulo5.testegeradormapa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Map;

import org.junit.Test;

import capitulo1.geradormapa.GeradorMapa;
import capitulo1.geradormapa.Ignorar;
import capitulo1.geradormapa.NomePropriedade;

public class TesteGeradorMapa {

	@Test
	public void mapaDeClasseSimples() {
		class Bean {
			private String prop1;
			private int prop2;

			public String getProp1() {
				return prop1;
			}

			public void setProp1(String prop1) {
				this.prop1 = prop1;
			}

			public int getProp2() {
				return prop2;
			}

			public void setProp2(int prop2) {
				this.prop2 = prop2;
			}
		}

		Bean b = new Bean();
		b.setProp1("teste");
		b.setProp2(25);
		Map<String, Object> mapa = GeradorMapa.gerarMapa(b);
		assertEquals("teste", mapa.get("prop1"));
		assertEquals(25, mapa.get("prop2"));
	}

	public void ignoraPropriedade() {
		class Bean {
			private String prop1;
			private int prop2;

			@Ignorar
			public String getProp1() {
				return prop1;
			}

			public void setProp1(String prop1) {
				this.prop1 = prop1;
			}

			public int getProp2() {
				return prop2;
			}

			public void setProp2(int prop2) {
				this.prop2 = prop2;
			}
		}
		Bean b = new Bean();
		b.setProp1("teste");
		b.setProp2(25);
		Map<String, Object> mapa = GeradorMapa.gerarMapa(b);
		assertFalse(mapa.containsKey("prop1"));
		assertEquals(25, mapa.get("prop2"));
	}

	@Test
	public void mudaNomePropriedade() {
		class Bean {
			private String prop1;
			private int prop2;

			public String getProp1() {
				return prop1;
			}

			public void setProp1(String prop1) {
				this.prop1 = prop1;
			}

			@NomePropriedade("propriedade2")
			public int getProp2() {
				return prop2;
			}

			public void setProp2(int prop2) {
				this.prop2 = prop2;
			}
		}

		Bean b = new Bean();
		b.setProp1("teste");
		b.setProp2(25);
		Map<String, Object> mapa = GeradorMapa.gerarMapa(b);
		assertEquals("teste", mapa.get("prop1"));
		assertEquals(25, mapa.get("propriedade2"));
		assertFalse(mapa.containsKey("prop2"));
	}
}
