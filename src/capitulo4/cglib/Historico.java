package capitulo4.cglib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class Historico implements MethodInterceptor {
	private Object encapsulated;
	private Map<String, List<Object>> historico = new HashMap<>();

	public Historico(Object encapsulated) {
		this.encapsulated = encapsulated;
	}

	public Object intercept(Object obj, Method m, Object[] args, MethodProxy proxy) throws Throwable {
		if (m.getDeclaringClass().equals(RecuperadorHistorico.class)) {
			return historico.get(args[0]);
		}
		if (m.getName().startsWith("set") && m.getParameterTypes().length == 1) {
			String prop = deSetterParaPropriedade(m.getName());
			List<Object> list = null;
			if (!historico.containsKey(prop)) {
				list = new ArrayList<Object>();
				historico.put(prop, list);
			} else {
				list = historico.get(prop);
			}
			list.add(args[0]);
		}
		try {
			return m.invoke(encapsulated, args);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
	}

	private static String deSetterParaPropriedade(String nomeSetter) {
		StringBuffer retorno = new StringBuffer();
		retorno.append(nomeSetter.substring(3, 4).toLowerCase());
		retorno.append(nomeSetter.substring(4));
		return retorno.toString();
	}

	public static <E> E guardar(E obj) {
		try {
			Historico proxy = new Historico(obj);
			Enhancer e = new Enhancer();
			e.setSuperclass(obj.getClass());
			e.setInterfaces(new Class[] { RecuperadorHistorico.class });
			e.setCallback(proxy);
			return (E) e.create();
		} catch (Throwable e) {
			throw new Error(e.getMessage());
		}
	}

	
}
