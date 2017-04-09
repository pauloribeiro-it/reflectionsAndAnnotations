package capitulo5.testeproxydinamico;

import capitulo4.cachedproxy.Cache;
import capitulo4.cachedproxy.InvalidaCache;

public interface CacheMe {
	@Cache
	int metodoComCache(int param);

	@InvalidaCache
	void anulaCache();
}
