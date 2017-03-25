package capitulo3.anotacoes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapeamentoParametros<E> {
	private Map<String, Field> parametros;
	private Class<E> clazz;

	public MapeamentoParametros(Class<E> c) {
		parametros = new HashMap<>();
		clazz = c;
		Class<?> current = c;
		while (current != Object.class) {
			for (Field f : current.getDeclaredFields()) {
				if (f.isAnnotationPresent(Parametro.class)) {
					Parametro p = f.getAnnotation(Parametro.class);
					parametros.put(p.value(), f);
				}
			}
			current = current.getSuperclass();
		}
	}

	private Method getSetter(Field f) {
		String nomeMetodo = "set" + Character.toUpperCase(f.getName().charAt(0)) + f.getName().substring(1);
		for (Method m : clazz.getMethods()) {
			if (m.getName().equals(nomeMetodo))
				return m;
		}
		throw new MapeamentoException(nomeMetodo + "() não encontrado");
	}

	private void inserir(E instancia, String nomeParametro, List<String> valores) {
		Field f = parametros.get(nomeParametro);
		if (f == null) {
			throw new MapeamentoException(nomeParametro + ": parâmetro não previsto");
		}
		Method m = getSetter(f);
		Object valor = recuperarValor(nomeParametro, valores, m);
		try {
			m.invoke(instancia, valor);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new MapeamentoException("Problemas ao invocar " + m.getName());
		}
	}

	private Object recuperarValor(String nomeParametro, List<String> valores, Method m) {
		if (m.getParameterTypes()[0] == boolean.class || m.getParameterTypes()[0] == Boolean.class) {
			if (valores.size() > 0) {
				throw new MapeamentoException(nomeParametro + " não pode possuir valor");
			} else {
				return true;
			}
		} else if (m.getParameterTypes()[0].isArray()) {
			return valores.toArray(new String[valores.size()]);
		} else {
			if (valores.size() != 1) {
				throw new MapeamentoException(nomeParametro + " só pode possuir um valor");
			} else {
				return valores.get(0);
			}
		}
	}

	public E mapear(String[] args) {
		String nomeParametro = null;
		List<String> valores = new ArrayList<>();
		E instancia = null;
		try {
			instancia = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new MapeamentoException(clazz.getName() + " não pode ser instanciada");
		}
		for (int i = 0; i < args.length; i++) {
			String token = args[i];
			if (token.startsWith("-")) {
				nomeParametro = token;
			} else {
				valores.add(token);
			}
			if (args.length == i + 1 || args[i + 1].startsWith("-")) {
				inserir(instancia, nomeParametro, valores);
				nomeParametro = null;
				valores.clear();
			}
		}
		return instancia;
	}

}
