package org.rapid.util.common.cache;

import java.util.Collection;
import java.util.Map;

import org.rapid.util.common.model.UniqueModel;

/**
 * 一级缓存，直接加载入内存，适合存放数据量不大、变动不频繁但是使用频繁的数据，比如说全局配置文件
 * 
 * @author ahab
 *
 * @param <ID> 缓存主键类型
 * @param <VALUE> 存储的对象类型
 */
public interface ICache<ID, VALUE extends UniqueModel<ID>> {
	
	String name();

	void load() throws Exception;
	
	void reload();
	
	void dispose();
	
	VALUE getById(ID id);
	
	Map<ID, VALUE> getAll();
	
	Map<ID, VALUE> getByIds(Collection<ID> ids);
	
	Map<ID, VALUE> getByProperties(String property, Object value);
	
	Map<ID, VALUE> getByProperties(Map<String, Object> params);
}
