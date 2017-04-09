package capitulo5.classmock;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import capitulo1.geradormapa.GeradorMapa;
import capitulo1.geradormapa.Ignorar;
import capitulo1.geradormapa.NomePropriedade;
import net.sf.classmock.ClassMock;
import net.sf.classmock.ClassMockUtils;
import net.sf.classmock.Location;

public class TesteGeradorMapaClassMock {
	private ClassMock mockClass;

	@Before
	public void criarMockClass() {
		mockClass = new ClassMock("Bean");
		mockClass.addProperty("prop1", String.class).addProperty("prop2", int.class);
	}

	@Test
	public void mapaDeClasseSimples() {
		Object instance = ClassMockUtils.newInstance(mockClass);
		ClassMockUtils.set(instance, "prop1", "teste");
		ClassMockUtils.set(instance, "prop2", 25);
		Map<String, Object> mapa = GeradorMapa.gerarMapa(instance);
		assertEquals("teste", mapa.get("prop1"));
		assertEquals(25, mapa.get("prop2"));
	}

	@Test
	public void ignoraPropriedade() {
		mockClass.addAnnotation("prop1", Ignorar.class, Location.GETTER);
		Object instance = ClassMockUtils.newInstance(mockClass);
		ClassMockUtils.set(instance, "prop1", "teste");
		ClassMockUtils.set(instance, "prop2", 25);
		Map<String, Object> mapa = GeradorMapa.gerarMapa(instance);
		assertFalse(mapa.containsKey("prop1"));
		assertEquals(25, mapa.get("prop2"));
	}

	@Test
	public void mudaNomePropriedade() {
		mockClass.addAnnotation("prop2", NomePropriedade.class, Location.GETTER, "propriedade2");
		Object instance = ClassMockUtils.newInstance(mockClass);
		ClassMockUtils.set(instance, "prop1", "teste");
		ClassMockUtils.set(instance, "prop2", 25);
		Map<String, Object> mapa = GeradorMapa.gerarMapa(instance);
		assertEquals("teste", mapa.get("prop1"));
		assertEquals(25, mapa.get("propriedade2"));
		assertFalse(mapa.containsKey("prop2"));
	}
}
