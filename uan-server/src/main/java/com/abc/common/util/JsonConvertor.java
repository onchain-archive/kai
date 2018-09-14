package com.abc.common.util;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.abc.common.UanException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

@Component
public class JsonConvertor {
	public String toJson(Object source) {
		if (source == null) {
			return "";
		}
		try {
			return JSON.toJSONString(source);
		} catch (Exception e) {
			throw new UanException("Object转换Json错误", e);
		}
	}

	public <T> T toObject(String source, Class<T> cls) {
		try {
			return JSON.parseObject(source, cls);
		} catch (Exception e) {
			throw new UanException("Json转换Object错误", e);
		}
	}

	public <T> T toObject(String source, Class<T> cls, Class elementCls) {
		if (elementCls == null) {
			return toObject(source, cls);
		}
		try {
			if (Map.class.isAssignableFrom(cls)) {
				Map tmp = JSON.parseObject(source, new TypeReference<Map<Object, Object>>() {
				});
				return (T) tmp;
			} else if (Iterable.class.isAssignableFrom(cls)) {
				return (T) JSON.parseArray(source, elementCls);
			} else {
				throw new UanException("不支持非Map和Iterable的子类");
			}
		} catch (Exception e) {
			throw new UanException("Json转换Object错误", e);
		}
	}

}
