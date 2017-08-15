package org.rapid.data.storage.mapper;

import java.util.Collection;

import org.rapid.util.common.model.UniqueModel;

/**
 * database 和 java 之间的对象映射类
 * 
 * @author ahab
 *
 * @param <KEY>
 * @param <MODEL>
 */
public interface DBMapper<KEY, MODEL extends UniqueModel<KEY>> extends Mapper<KEY, MODEL> {

	void deleteByKeys(Collection<KEY> keys);
	
	void replace(Collection<MODEL> collection);
}
