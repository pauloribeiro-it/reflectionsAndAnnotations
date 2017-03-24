package capitulo2.validador;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import capitulo2.fields.Validador;

public class ValidadorObjetos {
	public void validarObjeto(Object obj) throws ValidacaoException {
		Class<?> clazz = obj.getClass();
		List<Exception> erros = new ArrayList<>();
		erros.addAll(chamarMetodosValidacao(obj, clazz));
		erros.addAll(chamarValidadores(obj, clazz));
		if (erros.size() > 0) {
			throw new ValidacaoException(erros);
		}
	}

	private List<Exception> chamarValidadores(Object obj, Class<?> clazz) {
		List<Exception> erros = new ArrayList<>();
		for (Field f : clazz.getFields()) {
			if (Validador.class.isAssignableFrom(f.getType())) {
				try {
					Validador v = (Validador) f.get(obj);
					v.validar(obj);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (Exception e) {
					erros.add(e);
				}
			}
		}
		return erros;
	}

	private List<Exception> chamarMetodosValidacao(Object obj, Class<?> clazz) {
		List<Exception> erros = new ArrayList<>();
		for (Method m : clazz.getMethods()) {
			if (m.getName().startsWith("validar") && m.getParameterTypes().length == 0) {
				try {
					m.invoke(obj);
				} catch (IllegalAccessException | IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					erros.add((Exception) e.getTargetException());
				}
			}
		}
		return erros;
	}
}
