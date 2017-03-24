package capitulo2.validador;

import java.util.List;

public class ValidacaoException extends Exception {

	private static final long serialVersionUID = 8539232512813065873L;
	private List<Exception> erros;

	public ValidacaoException(List<Exception> erros) {
		this.erros = erros;
	}

	public List<Exception> getErros() {
		return erros;
	}
}
