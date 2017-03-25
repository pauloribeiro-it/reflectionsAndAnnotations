package capitulo3.anotacoes;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

@Metadado(nome="teste",numero=34)
@Anotacao(String.class)
public class ImprimeAnotacoes {
	
	public static void main(String[] args) throws Exception{
		imprimeAnotacoes(ImprimeAnotacoes.class);
	}
	
	public static void imprimeAnotacoes(AnnotatedElement ae) throws Exception {
		Annotation[] ans = ae.getAnnotations();
		for (Annotation a : ans) {
			Class<?> c = a.annotationType();
			System.out.println("@" + c.getName());
			for (Method m : c.getDeclaredMethods()) {
				Object o = m.invoke(a);
				System.out.println(" |->" + m.getName() + "=" + o);
			}
		}
	}
}
