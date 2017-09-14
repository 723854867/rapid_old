package org.rapid.util.common.consts.conveter.str;

import org.rapid.util.common.converter.ConstConverter;

/**
 * string 类型转换器，能将 string 类型转换成其他类型，value() 值表示默认值
 * 
 * @author ahab
 *
 * @param <T>
 */
public interface StrConstConverter<T> extends ConstConverter<String, T> {}
