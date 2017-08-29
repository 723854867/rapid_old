package org.rapid.data.storage.mapper;

import java.util.Collection;
import java.util.Map;

import org.rapid.util.common.model.UniqueModel;

/**
 * 将普通 java 对象映射到存储系统(redis、mongodb、database)的映射类
 * 
 * @author ahab
 *
 * @param <KEY>		主键类型
 * @param <ENTITY>	表所对应的 java pojo 类型
 */
public interface Mapper<KEY, MODEL extends UniqueModel<KEY>>  {

	/**
	 * 插入数据
	 * 
	 * @param model
	 */
	void insert(MODEL model);
	
	/**
	 * 获取所有的数据
	 * 
	 * @return
	 */
	Map<KEY, MODEL> getAll();
	
	/**
	 * 获取指定主键
	 * 
	 * @return
	 */
	MODEL getByKey(KEY key);
	
	/**
	 * 获取多个主键
	 * 
	 * @return
	 */
	Map<KEY, MODEL> getByKeys(Collection<KEY> keys);
	
	/**
	 * 修改
	 * 
	 * @param model
	 */
	void update(MODEL model);
	
	/**
	 * 删除
	 * 
	 * @param key
	 */
	void delete(KEY key);
}
