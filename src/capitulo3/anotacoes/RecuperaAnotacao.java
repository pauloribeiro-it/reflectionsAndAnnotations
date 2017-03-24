package capitulo3.anotacoes;

@Metadado(nome = "classe", numero = 17)
public class RecuperaAnotacao {
	public static void main(String[] args) {
		Class<RecuperaAnotacao> c = RecuperaAnotacao.class;
		if (c.isAnnotationPresent(Metadado.class)) {
			Metadado m = c.getAnnotation(Metadado.class);
			System.out.println("Propriedade nome = " + m.nome());
			System.out.println("Propriedade numero =" + m.numero());
		}
	}
}
