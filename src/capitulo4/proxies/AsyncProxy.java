package capitulo4.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AsyncProxy implements InvocationHandler {

	private Object obj;

	private AsyncProxy(Object obj) {
		this.obj = obj;
	}

	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
		if(method.getReturnType() == void.class){
			new Thread(){
				@Override
				public void run() {
					try {
						method.invoke(obj,args);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}.start();
			return null;
		}else{
			return method.invoke(obj,args);
		}
			
	}

	public static Object criarProxy(Object obj) {
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
				new AsyncProxy(obj));
	}

}
