package capitulo4.cachedproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {

	private Object obj;
	private Map<String, Object> cache = new HashMap<>();

	public CacheProxy(Object obj) {
		this.obj = obj;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			if (method.isAnnotationPresent(InvalidaCache.class)) {
				cache.clear();
			} else if (method.isAnnotationPresent(Cache.class)) {
				String chave = gerarChave(method, args);
				if (cache.containsKey(chave)) {
					return cache.get(chave);
				} else {
					Object retorno = method.invoke(obj, args);
					cache.put(chave, retorno);
					return retorno;
				}
			}
			return method.invoke(obj, args);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
	}

	public static Object criar(Object obj) {
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
				new CacheProxy(obj));
	}

	private String gerarChave(Method method, Object[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append(method.getName());
		for (int i = 0; i < args.length; i++)
			sb.append(args[i]);
		return sb.toString();
	}
}
