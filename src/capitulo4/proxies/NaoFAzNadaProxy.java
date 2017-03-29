package capitulo4.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NaoFAzNadaProxy implements InvocationHandler{
	private Object obj;

	private NaoFAzNadaProxy(Object obj) {
		this.obj = obj;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			return method.invoke(obj, args);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
	}
}
