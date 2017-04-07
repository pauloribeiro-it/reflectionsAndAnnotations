package capitulo5.teste;

import static org.junit.Assert.*;

import org.junit.Test;

import capitulo2.fields.Validador;
import capitulo2.validador.ValidacaoException;
import capitulo2.validador.ValidadorObjetos;

public class ValidadorTeste implements Validador {
	public boolean invocou;

	public void validar(Object o) throws Exception {
		invocou = true;
	}

	@Test
	public void invocaAtributos() throws ValidacaoException {
		class ParaValidar {
			public ValidadorTeste v1 = new ValidadorTeste();
			public Validador v2 = new ValidadorTeste();
			public Object v3 = new ValidadorTeste();
		}
		ValidadorObjetos v = new ValidadorObjetos();
		ParaValidar pv = new ParaValidar();
		v.validarObjeto(pv);
		assertTrue(pv.v1.invocou);
		assertTrue(((ValidadorTeste) pv.v2).invocou);
		assertFalse(((ValidadorTeste) pv.v3).invocou);
	}
}
