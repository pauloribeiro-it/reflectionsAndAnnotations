package capitulo4.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class SystemPropertiesRetriever implements InvocationHandler {
	public static <E> E criar(Class<E> interf) {
		return (E) Proxy.newProxyInstance(interf.getClassLoader(), new Class<?>[] { interf },
				new SystemPropertiesRetriever());
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String[] split = separaPorMaiusculas(method.getName());
		String nomeProp = nomePropriedade(split);
		return System.getProperty(nomeProp);
	}

	public String[] separaPorMaiusculas(String nome) {
		List<String> lista = new ArrayList<>();
		String corrente = "";
		for (int i = 0; i < nome.length(); i++) {
			if (Character.isUpperCase(nome.charAt(i))) {
				lista.add(corrente);
				corrente = "";
				corrente += Character.toLowerCase(nome.charAt(i));
			} else {
				corrente += nome.charAt(i);
			}
		}
		lista.add(corrente);
		return lista.toArray(new String[lista.size()]);
	}

	public String nomePropriedade(String[] strs) {
		String nomeProp = "";
		for (int i = 1; i < strs.length; i++) {
			if (i != 1) {
				nomeProp += ".";
			}
			nomeProp += strs[i];
		}
		return nomeProp;
	}
}
