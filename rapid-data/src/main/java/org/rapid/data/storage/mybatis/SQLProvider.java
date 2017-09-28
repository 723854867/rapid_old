package org.rapid.data.storage.mybatis;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.ibatis.jdbc.SQL;
import org.rapid.util.lang.StringUtil;
import org.rapid.util.reflect.BeanUtils;
import org.rapid.util.reflect.ClassUtil;

public class SQLProvider {
	
	protected static final String COLLECTION					= "collection";
	
	protected String table;
	protected String keyCol;
	protected boolean useGeneratedKeys;													// 是否自动生成主键默认自动生成主键
	protected Set<String> updateExclusive = new HashSet<String>();						// 不需要更新的字段
	
	protected SQLProvider(String table, String keyCol) {
		this(table, keyCol, true);
	}
	
	protected SQLProvider(String table, String keyCol, boolean useGeneratedKeys) {
		this.table = table;
		this.keyCol = keyCol;
		this.useGeneratedKeys = useGeneratedKeys;
	}
	
	public String insert(Object entity) {
		Map<String, Object> params = BeanUtils.beanToMap(entity, false);
		SQL sql = new SQL();
		sql.INSERT_INTO(table);
		a : for (Entry<String, Object> entry : params.entrySet()) {
			String col = StringUtil.camel2Underline(entry.getKey());
			if (useGeneratedKeys && keyCol.equals(col))					// 如果主键是自动生成的则要忽略主键
				continue;
			for (String ncol : updateExclusive) {
				if (col.equals(ncol))
					continue a;
			}
			sql.VALUES("`" + col + "`", "#{" + entry.getKey() + "}");
		}
		return sql.toString();
	}
	
	public String getAll() {
		return new SQL() {
			{
				SELECT("*");
				FROM(table);
			}
		}.toString();
	}
	
	public String getByKey() {
		return new SQL() {
			{
				SELECT("*");
				FROM(table);
				WHERE(keyCol + "=#{key}");
			}
		}.toString();
	}
	
	public String getByKeys(Map<String, Object> params) {
		Collection<?> keys = (Collection<?>) params.get(COLLECTION);
		StringBuilder sql = new StringBuilder("SELECT * FROM ");
		sql.append(table).append("  WHERE `").append(keyCol).append("` IN(");
		for (Object key : keys) {
			if (ClassUtil.isNumber(key))
				sql.append(key).append(",");
			else
				sql.append("'").append(key).append("',");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		return sql.toString();
	}
	
	public String update(Object entity) {
		Map<String, Object> params = BeanUtils.beanToMap(entity, true);			// null 值也要包括进来，全属性更新
		SQL sql = new SQL();
		sql.UPDATE(table);
		String keyCamelCol = null;
		a : for (Entry<String, Object> entry : params.entrySet()) {
			String col = StringUtil.camel2Underline(entry.getKey());
			if (keyCol.equals(col))	{									// 主键不在更新之列
				keyCamelCol = entry.getKey();
				continue;
			}
			for (String ncol : updateExclusive) {
				if (col.equals(ncol))
					continue a;
			}
			sql.SET("`" + col + "`=#{" + entry.getKey() + "}");
		}
		sql.WHERE("`" + keyCol + "`=#{" + keyCamelCol + "}");
		return sql.toString();
	}
	
	public String replace(Map<String, Object> params) {
		Collection<?> models = (Collection<?>) params.get(COLLECTION);
		StringBuilder sql = new StringBuilder("REPLACE INTO `");
		sql.append(table).append("`(");
		String[] cols = BeanUtils.beanToMap(models.iterator().next(), true).keySet().toArray(new String[]{});
		for (int idx =0; idx < cols.length; idx++) {
			sql.append("`").append(StringUtil.camel2Underline(cols[idx])).append("`,");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(") VALUES");
		for (Object entity : models) {
			sql.append("(");
			Map<String, Object> values = BeanUtils.beanToMap(entity, true);			// null 值也要包括进来，全属性更新
			for (int idx =0; idx < cols.length; idx++) {
				Object value = values.get(cols[idx]);
				if (ClassUtil.isNumber(value)) 
					sql.append(value.toString()).append(",");
				else
					sql.append("'").append(value.toString()).append("'").append(",");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append("),");
		}
		sql.deleteCharAt(sql.length() - 1);
		return sql.toString();
	}
	
	public String delete() {
		return new SQL() {
			{
				DELETE_FROM(table);
				WHERE("`" + keyCol + "`=#{key}");
			}
		}.toString();
	}
	
	public String deleteByKeys(Map<String, Object> params) {
		Collection<?> keys = (Collection<?>) params.get(COLLECTION);
		StringBuilder sql = new StringBuilder("DELETE FROM ");
		sql.append(table).append("  WHERE `").append(keyCol).append("` IN(");
		for (Object key : keys) {
			if (ClassUtil.isNumber(key))
				sql.append(key).append(",");
			else
				sql.append("'").append(key).append("',");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		return sql.toString();
	}
	
	protected void addNoUpdateCol(String... cols) {
		for (String col : cols)
			this.updateExclusive.add(col);
	}
}
