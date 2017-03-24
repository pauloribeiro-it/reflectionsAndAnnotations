package capitulo2.fields;

public class Utilitaria {
	public String repetir(String base, String divisor, Integer vezes) {
		String retorno = base;
		for (int i = 1; i < vezes; i++) {
			retorno += divisor + base;
		}
		return retorno;
	}
}
