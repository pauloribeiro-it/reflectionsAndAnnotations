package capitulo5.mocks;

import org.junit.Test;

import capitulo3.anotacoes.MapeamentoException;
import capitulo3.anotacoes.MapeamentoParametros;
import capitulo3.anotacoes.Parametro;

public class TesteMapeamentoParametros {
	@Test(expected = MapeamentoException.class)
	public void testTipoErrado() {
		class Param {
			@Parametro("-i")
			private int info;

			public int getInfo() {
				return info;
			}

			public void setInfo(int info) {
				this.info = info;
			}
		}
		String[] a = { "-i", "23" };
		MapeamentoParametros<Param> map = new MapeamentoParametros<>(Param.class);
		Param p = map.mapear(a);
	}

	@Test(expected = MapeamentoException.class)
	public void testQuantidadeParamErrado() {
		class Param {
			@Parametro("-i")
			private String[] info;

			public String[] getInfo() {
				return info;
			}

			public void setInfo(String infoA, String infoB) {
				this.info = new String[] { infoA, infoB };
			}
		}
		String[] a = { "-i", "textoA", "textoB" };
		MapeamentoParametros<Param> map = new MapeamentoParametros<>(Param.class);
		Param p = map.mapear(a);
	}
}
