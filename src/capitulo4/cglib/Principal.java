package capitulo4.cglib;

import java.util.List;


public class Principal {
	public static void main(String[] args) {
		Produto p = Historico.guardar(new Produto());
		p.setNome("Design Patterns com Java");
		p.setMarca("Casa do Código");
		p.setPreco(59.90);
		// blackfriday
		p.setPreco(49.90);
		// normal
		p.setPreco(59.90);
		// natal
		p.setPreco(54.90);
		List<Object> lista = ((RecuperadorHistorico) p).getHistorico("preco");
		for (Object valor : lista) {
			System.out.println(valor);
		}
	}
}
