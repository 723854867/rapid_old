package org.rapid.util.common.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.rapid.util.common.model.UniqueModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Cache<ID, VALUE extends UniqueModel<ID>> implements ICache<ID, VALUE> {
	
	private static final Logger logger = LoggerFactory.getLogger(Cache.class);
	
	protected String name;
	protected Map<ID, VALUE> cache = new ConcurrentHashMap<ID, VALUE>();
	
	protected Cache(String name) {
		this.name = name;
	}
	
	@Override
	public String name() {
		return this.name;
	}
	
	@Override
	public void reload() {
		Map<ID, VALUE> temp = new ConcurrentHashMap<ID, VALUE>(this.cache);
		try {
			load();
			temp.clear();
			temp = null;
		} catch (Exception e) {
			this.cache.clear();
			this.cache = temp;
			logger.warn("Cache {} reload failure!", name, e);
		}
	}

	@Override
	public void dispose() {
		this.cache.clear();
		logger.info("Cache {} dispose!", name);
	}

	@Override
	public VALUE getById(ID id) {
		return cache.get(id);
	}

	@Override
	public Map<ID, VALUE> getAll() {
		return new HashMap<ID, VALUE>(cache);
	}
	
	@Override
	public Map<ID, VALUE> getByIds(Collection<ID> ids) {
		Map<ID, VALUE> map = new HashMap<ID, VALUE>();
		for (ID id : ids) {
			VALUE value = cache.get(id);
			if (null != value)
				map.put(id, value);
		}
		return map;
	}

	@Override
	public Map<ID, VALUE> getByProperties(String property, Object value) {
		return null;
	}

	@Override
	public Map<ID, VALUE> getByProperties(Map<String, Object> params) {
		return null;
	}
}
