package capitulo5.classmock;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import capitulo4.cachedproxy.Cache;
import capitulo4.cachedproxy.CacheProxy;
import net.sf.classmock.ClassMock;
import net.sf.classmock.ClassMockUtils;

public class TesteCacheProxyClassMock {
	@Rule
	public JUnitRuleMockery ctx = new JUnitRuleMockery();

	@Test
	public void cacheSimples() throws Throwable {
		ClassMock cm = new ClassMock("CacheMe", true);
		cm.addAbstractMethod(int.class, "comCache", int.class).addMethodAnnotation("comCache", Cache.class);
		Class<?> interf = cm.createClass();
		final Object mock = ctx.mock(interf);
		Object proxy = CacheProxy.criar(mock);
		ctx.checking(new Expectations() {
			{
				ClassMockUtils.invoke(one(mock), "comCache", 1);
				will(returnValue(10));
			}
		});
		assertEquals(10, ClassMockUtils.invoke(proxy, "comCache", 1));
		assertEquals(10, ClassMockUtils.invoke(proxy, "comCache", 1));
	}
}
