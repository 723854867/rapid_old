package org.rapid.util.common.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;

import javax.activation.UnsupportedDataTypeException;

import org.apache.http.Consts;
import org.rapid.util.common.model.UniqueModel;
import org.rapid.util.common.serializer.SerializeUtil;
import org.rapid.util.io.ResourceUtil;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

/**
 * 读取json文件的cache
 * 
 * @author ahab
 *
 * @param <ID>
 * @param <VALUE>
 */
public class JsonCache<ID, VALUE extends UniqueModel<ID>> extends Cache<ID, VALUE> {
	
	private Class<VALUE> clazz;
	private String jsonFileLocation;

	public JsonCache(String name, String jsonFileLocation) {
		super(name);
		Type superType = getClass().getGenericSuperclass();   
		Type[] generics = ((ParameterizedType) superType).getActualTypeArguments();  
		this.clazz = (Class<VALUE>) generics[1];
		this.jsonFileLocation = jsonFileLocation;
	}

	@Override
	public void load() throws Exception {
		File file = ResourceUtil.getFile(jsonFileLocation);
		FileInputStream in = new FileInputStream(file);
		JsonReader reader = new JsonReader(new BufferedReader(new InputStreamReader(in, Consts.UTF_8)));
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(reader);
		if (!element.isJsonArray()) 
			throw new UnsupportedDataTypeException("Cache only support json array!");
		JsonArray array = element.getAsJsonArray();
		Iterator<JsonElement> iterator = array.iterator();
		while (iterator.hasNext()) {
			element = iterator.next();
			if (!element.isJsonObject()) 
				continue;
			VALUE value = SerializeUtil.JsonUtil.GSON.fromJson(element, clazz);
			cache.put(value.key(), value);
		}
	}
}
