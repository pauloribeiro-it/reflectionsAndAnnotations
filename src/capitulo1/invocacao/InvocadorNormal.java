package capitulo1.invocacao;

public class InvocadorNormal implements InvocadorMetodo {

	public void invocadorMetodo(int vezes) {
		ClasseTeste ct = new ClasseTeste();
		for (int i = 0; i < vezes; i++) {
			ct.metodoVazio();
		}
	}

}
