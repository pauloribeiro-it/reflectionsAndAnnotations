package capitulo1.geradormapa;

public class Telefone {
	private String codigoPais;
	private String operadora;

	@NomePropriedade("codigoInternacional")
	public String getCodigoPais() {
		return codigoPais;
	}

	@Ignorar
	public String getOperadora() {
		return operadora;
	}
}
