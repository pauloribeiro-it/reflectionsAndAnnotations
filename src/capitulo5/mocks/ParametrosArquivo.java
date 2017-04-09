package capitulo5.mocks;

import capitulo3.anotacoes.Parametro;

public class ParametrosArquivo {
	@Parametro("-time")
	private int timeout;
	@Parametro("-in")
	private String[] arquivosEntrada;
	@Parametro("-out")
	private String arquivoSaida;

	public void setTimeout(String timeout) {
		this.timeout = Integer.parseInt(timeout);
	}

	public String getArquivoSaida() {
		return arquivoSaida;
	}

	public void setArquivoSaida(String arquivoSaida) {
		this.arquivoSaida = arquivoSaida;
	}

	public String[] getArquivosEntrada() {
		return arquivosEntrada;
	}

	public void setArquivosEntrada(String[] arquivosEntrada) {
		this.arquivosEntrada = arquivosEntrada;
	}
	
	public int getTimeout() {
		return timeout;
	}
}
