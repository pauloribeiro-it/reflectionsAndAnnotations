package capitulo2.fields;

import java.lang.reflect.Method;
import java.util.Scanner;

public class ExecutaMetodo {
	private static Method acharMetodoPeloNome(Class<?> c, String nome) throws Exception {
		for (Method m : c.getMethods()) {
			if (m.getName().equals(nome)) {
				return m;
			}
		}
		throw new Exception("Método " + nome + " não encontrado");
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Entre com o nome da classe " + "com método que deseja executar:");
		Scanner in = new Scanner(System.in);
		String nomeClasse = in.nextLine();
		Class<?> c = Class.forName(nomeClasse);
		System.out.println("Entre com o nome do método:");
		String nomeMetodo = in.nextLine();
		Method m = acharMetodoPeloNome(c, nomeMetodo);
		Object[] params = new Object[m.getParameterTypes().length];
		for (int i = 0; i < params.length; i++) {
			Class<?> paramType = m.getParameterTypes()[i];
			System.out.println("Parametro " + (i + 1) + " (" + paramType.getName() + ")");
			String valor = in.nextLine();
			params[i] = paramType.getConstructor(String.class).newInstance(valor);
		}
		Object retorno = m.invoke(c.newInstance(), params);
		System.out.println("O método retornou: " + retorno);
		in.close();
	}
}
