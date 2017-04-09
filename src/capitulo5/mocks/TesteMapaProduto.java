package capitulo5.mocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import capitulo1.geradormapa.GeradorMapa;
import capitulo4.cglib.Produto;

public class TesteMapaProduto {
	@Test
	public void geracaoMapa() {
		Object o = getObjeto();
		Map<String, Object> mapa = GeradorMapa.gerarMapa(o);
		Map<String, Object> esperado = new HashMap<>();
		getConteudoMapa(esperado);
		for (String s : esperado.keySet()) {
			assertEquals(esperado.get(s), mapa.get(s));
		}
		List<String> excluidos = new ArrayList<>();
		for (String s : excluidos) {
			assertFalse(mapa.containsKey(s));
		}
	}

	protected Object getObjeto() {
		Produto p = new Produto();
		p.setNome("Tablet APX10");
		p.setPreco(500.00);
		p.setDescricao("10 pol, 16GB, 800x600");
		p.setCategoria("Eletrônicos");
		return p;
	}

	protected void getConteudoMapa(Map<String, Object> esperado) {
		esperado.put("nome", "Tablet APX10");
		esperado.put("preco", 500.00);
		esperado.put("tipo", "Eletrônicos");
	}

	protected void getExcluidos(List<String> excluidos) {
		excluidos.add("descricao");
		excluidos.add("categoria");
	}
}
