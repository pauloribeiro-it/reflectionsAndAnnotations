package capitulo2.construtores;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UsoConstrutor {
	public UsoConstrutor(String s) {
		System.out.println("Construtor invocado com: " + s);
	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Class<UsoConstrutor> c = UsoConstrutor.class;
		Constructor<UsoConstrutor> constr = c.getConstructor(String.class);
		try {
			UsoConstrutor obj = constr.newInstance("teste");
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
		}catch(InvocationTargetException e2){
			System.out.println(
					"Exceção lançada pelo construtor: "+e2.getTargetException());
		}
		
	}
}
