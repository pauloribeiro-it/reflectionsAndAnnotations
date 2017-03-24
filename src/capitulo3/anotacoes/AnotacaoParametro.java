package capitulo3.anotacoes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AnotacaoParametro {
	
	public static Object invocarMetodo(Method m, Object obj, Map<String, Object> info) throws Exception {
		Annotation[][] paramAnnot = m.getParameterAnnotations();
		Object[] paramValues = new Object[paramAnnot.length];
		for (int i = 0; i < paramValues.length; i++) {
			String name = getNomeParametro(paramAnnot[i]);
			paramValues[i] = info.get(name);
		}
		return m.invoke(obj, paramValues);
	}

	public static String getNomeParametro(Annotation[] ans) {
		for (Annotation a : ans) {
			if (a instanceof Param) {
				return ((Param) a).value();
			}
		}
		throw new RuntimeException("Anotação @Param não encontrada");
	}

	public static void main(String[] args) throws Exception {
		Map<String, Object> info = new HashMap<>();
		info.put("inteiro", 13);
		info.put("numero", 23);
		info.put("string", "OK");
		info.put("texto", "NOK");
		AnotacaoParametro ap = new AnotacaoParametro();
		Method m = ap.getClass().getMethod("metodo", Integer.class, String.class);
		invocarMetodo(m, ap, info);
	}

	public void metodo(@Param("inteiro") Integer i, @Param("texto") String s) {
		System.out.println("Parametro inteiro = " + i);
		System.out.println("Parametro texto = " + s);
	}
}
