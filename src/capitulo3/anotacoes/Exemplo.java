package capitulo3.anotacoes;

public class Exemplo {
	@Metadado
	private String atributo1;
	
	@Metadado(String.class)
	private String atributo2;
	
	@Metadado(value=String.class,tipo="ok")
	private String atributo3;
	
	@Metadado(String.class,tipo="OK")
	private String atributo4;
}
