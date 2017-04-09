package capitulo5.testeproxydinamico;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import capitulo4.cachedproxy.CacheProxy;

public class TesteCacheProxy {
	@Rule
	public JUnitRuleMockery ctx = new JUnitRuleMockery();

	@Test
	public void cacheSimples() {
		final CacheMe mock = ctx.mock(CacheMe.class);
		CacheMe proxy = (CacheMe) CacheProxy.criar(mock);
		ctx.checking(new Expectations() {
			{
				oneOf(mock).metodoComCache(1);
				will(returnValue(10));
				oneOf(mock).metodoComCache(2);
				will(returnValue(20));
			}
		});
		assertEquals(10, proxy.metodoComCache(1));
		assertEquals(20, proxy.metodoComCache(2));
		assertEquals(10, proxy.metodoComCache(1));
		assertEquals(20, proxy.metodoComCache(2));
	}

	@Test
	public void invalidaCache() {
		final CacheMe mock = ctx.mock(CacheMe.class);
		CacheMe proxy = (CacheMe) CacheProxy.criar(mock);
		ctx.checking(new Expectations() {
			{
				exactly(2).of(mock).metodoComCache(1);
				will(onConsecutiveCalls(returnValue(10), returnValue(20)));
				oneOf(mock).anulaCache();
			}
		});
		assertEquals(10, proxy.metodoComCache(1));
		assertEquals(10, proxy.metodoComCache(1));
		proxy.anulaCache();
		assertEquals(20, proxy.metodoComCache(1));
		assertEquals(20, proxy.metodoComCache(1));
	}
}
