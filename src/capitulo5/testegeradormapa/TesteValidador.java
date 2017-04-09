package capitulo5.testegeradormapa;

import static org.junit.Assert.*;

import org.junit.Test;

import capitulo2.validador.ValidacaoException;
import capitulo2.validador.ValidadorObjetos;

public class TesteValidador {
	@Test
	public void testInvocaMetodo() throws ValidacaoException {
		class ParaValidar {
			public boolean invocou = false;

			public void validarInformacao() {
				invocou = true;
			}
		}
		ValidadorObjetos v = new ValidadorObjetos();
		ParaValidar pv = new ParaValidar();
		v.validarObjeto(pv);
		assertTrue(pv.invocou);
	}

	@Test
	public void naoInvocaMetodo() throws ValidacaoException {
		class ParaValidar {
			public boolean invocouA = false;
			public boolean invocouB = false;

			public void validarParamErrado(String s) {
				invocouA = true;
			}

			public void nomeErrado(String s) {
				invocouB = true;
			}
		}
		ValidadorObjetos v = new ValidadorObjetos();
		ParaValidar pv = new ParaValidar();
		v.validarObjeto(pv);
		assertFalse(pv.invocouA);
		assertFalse(pv.invocouB);
	}
}
