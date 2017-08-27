package org.rapid.util.common.cache;

import java.util.Collection;
import java.util.Map;

import org.rapid.util.common.model.UniqueModel;

public interface ICacheService<CACHE extends ICache<?, ?>> {

	void init();
	
	void dispose();
	
	CACHE getCache(String name);
	
	<ID, VALUE extends UniqueModel<ID>> VALUE getById(String name, ID id);
	
	<ID, VALUE extends UniqueModel<ID>> Map<ID, VALUE> getAll(String name); 
	
	<ID, VALUE extends UniqueModel<ID>> Map<ID, VALUE> getByIds(String name, Collection<ID> ids);
	
	<ID, VALUE extends UniqueModel<ID>> Map<ID, VALUE>getByProperties(String name, String property, Object value); 
	
	<ID, VALUE extends UniqueModel<ID>> Map<ID, VALUE> getByProperties(String name, Map<String, Object> params);
}
