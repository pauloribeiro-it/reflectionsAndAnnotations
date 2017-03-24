package capitulo2.fields;

import java.lang.reflect.Field;

public class EscrevendoLendoAtributos {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		ExemploClasse instancia = new ExemploClasse();
		Class<?> clazz = instancia.getClass();
		escreverLerAtributo(clazz.getField("publico"), instancia);
		escreverLerAtributo(clazz.getDeclaredField("privado"), instancia);
		escreverLerAtributo(clazz.getField("estatico"), null);
	}

	public static void escreverLerAtributo(Field f, Object instancia) {
		try {
			f.set(instancia, f.getName());
			Object valor = f.get(instancia);
			System.out.println("Escrito e lido o atributo = " + valor);
		} catch (IllegalArgumentException e) {
			System.out.println("Problemas ao acessar atributo " + f.getName() + ": " + e.getMessage());
		} catch (IllegalAccessException e) {
			System.out.println("Problemas de acesso no atributo " + f.getName() + ": " + e.getMessage());
		}
	}
}
