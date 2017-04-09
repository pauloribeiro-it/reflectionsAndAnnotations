package capitulo5.mocks;

import static org.junit.Assert.*;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import capitulo2.fields.Validador;
import capitulo2.validador.ValidacaoException;
import capitulo2.validador.ValidadorObjetos;

public class TesteValidador {
	@Rule
	public JUnitRuleMockery ctx = new JUnitRuleMockery();

	@Test
	public void invocaAtributosJMock() throws Exception {
		class ParaValidar {
			public Validador v1 = ctx.mock(Validador.class, "v1");
			public Object v2 = ctx.mock(Validador.class, "v2");
		}
		ValidadorObjetos v = new ValidadorObjetos();
		final ParaValidar pv = new ParaValidar();
		ctx.checking(new Expectations() {
			{
				oneOf(pv.v1).validar(pv);
				never(((Validador) pv.v2)).validar(pv);
			}
		});
		v.validarObjeto(pv);
	}

	@Test
	public void capturaExcecoes() throws Exception {
		class ParaValidar {
			public Validador v = ctx.mock(Validador.class);

			public void validarInformacao() throws Exception {
				throw new Exception("mensagem A");
			}
		}
		ValidadorObjetos v = new ValidadorObjetos();
		final ParaValidar pv = new ParaValidar();
		ctx.checking(new Expectations() {
			{
				oneOf(pv.v).validar(pv);
				will(throwException(new Exception("mensagem B")));
			}
		});
		try {
			v.validarObjeto(pv);
			fail();
		} catch (ValidacaoException e) {
			List<Exception> erros = e.getErros();
			assertEquals("mensagem A", erros.get(0).getMessage());
			assertEquals("mensagem B", erros.get(1).getMessage());
		}
	}
}
