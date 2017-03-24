package capitulo2.construtores;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FornecedorImplementacoes {
	private Map<Class<?>, Class<?>> implementacoes = new HashMap<>();

	public FornecedorImplementacoes(String nomeArquivo) throws Exception {
		Properties p = new Properties();
		p.load(new FileInputStream(nomeArquivo));
		for (Object interf : p.keySet()) {
			Class<?> interfType = Class.forName(interf.toString());
			Class<?> implType = Class.forName(p.get(interf).toString());
			if (!isAbstracaoEImplementacao(interfType, implType)) {
				throw new Exception("Erro na configuração do arquivo " + nomeArquivo + " : " + interfType.getName()
						+ " não é abstração de " + implType.getName());
			}
			implementacoes.put(interfType, implType);
		}
	}

	public Class<?> getImplementacao(Class<?> interf) {
		return implementacoes.get(interf);
	}

	public static void main(String[] args) throws Exception {
		try {
			FornecedorImplementacoes f = new FornecedorImplementacoes("implementacoes.prop");
			Class<?> impl = f.getImplementacao(DAO.class);
			System.out.println("Implementação recuperada: " + impl.getName());
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Problemas ao obter implementacões:" + e.getMessage());
		}
	}

	private Constructor<?> acharConstrutor(Class<?> c, Object... objs) throws Exception {
		for (Constructor<?> constr : c.getConstructors()) {
			Class<?>[] params = constr.getParameterTypes();
			if (params.length == objs.length) {
				boolean erro = false;
				for (int i = 0; i < objs.length && !erro; i++) {
					if (!params[i].isInstance(objs[i]))
						erro = true;
				}
				if (!erro)
					return constr;
			}
		}
		throw new Exception("Construtor não encontrado");
	}

	public Object criarInstancia(Class<?> interf, Object... objs) throws Exception {
		Class<?> impl = getImplementacao(interf);
		Constructor<?> constr = acharConstrutor(impl, objs);
		return constr.newInstance(objs);
	}

	private boolean isInterfaceOuAbstract(Class<?> c) {
		return c.isInterface() || Modifier.isAbstract(c.getModifiers());
	}

	private boolean isAbstracaoEImplementacao(Class<?> interf, Class<?> impl) {
		return isInterfaceOuAbstract(interf) && !isInterfaceOuAbstract(impl) && interf.isAssignableFrom(impl);
	}
}
