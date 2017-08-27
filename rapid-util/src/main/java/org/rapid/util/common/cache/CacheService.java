package org.rapid.util.common.cache;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.rapid.util.common.model.UniqueModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheService<CACHE extends ICache<?, ?>> implements ICacheService<CACHE> {
	
	private static final Logger logger = LoggerFactory.getLogger(CacheService.class);
	
	private Map<String, CACHE> caches = new ConcurrentHashMap<String, CACHE>();

	@Override
	public void dispose() {
		this.caches.clear();
		logger.info("{} cache were disposed from CacheService!", caches.size());
	}
	
	protected void addCache(CACHE cache) {
		caches.put(cache.name(), cache);
	}
	
	@Override
	public void init() {
		for (CACHE cache : caches.values())
			try {
				cache.load();
			} catch (Exception e) {
				logger.warn("Cache {} load failure, system will closed...", cache.name(), e);
				System.exit(1);
			}
		logger.info("Cache service initialize success, total {} caches loaded!", caches.size());
	}
	
	@Override
	public CACHE getCache(String name) {
		return (CACHE) caches.get(name);
	}

	@Override
	public <ID, VALUE extends UniqueModel<ID>> VALUE getById(String name, ID id) {
		ICache<ID, VALUE> cache = _getCache(name);
		return cache.getById(id);
	}
	
	@Override
	public <ID, VALUE extends UniqueModel<ID>> Map<ID, VALUE> getAll(String name) {
		ICache<ID, VALUE> cache = _getCache(name);
		return cache.getAll();
	}
	
	@Override
	public <ID, VALUE extends UniqueModel<ID>> Map<ID, VALUE> getByIds(String name, Collection<ID> ids) {
		ICache<ID, VALUE> cache = _getCache(name);
		return cache.getByIds(ids);
	}

	@Override
	public <ID, VALUE extends UniqueModel<ID>> Map<ID, VALUE>getByProperties(String name, String property, Object value) {
		ICache<ID, VALUE> cache = _getCache(name);
		return cache.getByProperties(property, value);
	}

	@Override
	public <ID, VALUE extends UniqueModel<ID>> Map<ID, VALUE> getByProperties(String name, Map<String, Object> params) {
		ICache<ID, VALUE> cache = _getCache(name);
		return cache.getByProperties(params);
	}

	private <ID, VALUE extends UniqueModel<ID>> ICache<ID, VALUE> _getCache(String name) {
		return (ICache<ID, VALUE>) caches.get(name);
	}
	
	public void setCaches(Map<String, CACHE> caches) {
		this.caches = caches;
	}
}
