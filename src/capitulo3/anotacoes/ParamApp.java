package capitulo3.anotacoes;

public class ParamApp {
	@Parametro("-p")
	private boolean possui;
	@Parametro("-n")
	private boolean naoPossui;
	@Parametro("-a")
	private String[] arquivos;
	@Parametro("-s")
	private String saida;
}
