/*
 * Copyright 2018 Liu Bo
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.abc.common.util;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.abc.common.UanException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * Title: JsonConvertor
 * @Description: JsonConvertor
 * @author Bo Liu
 * @date 2018-09-20
 */
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

	/** 
	 * @Description: toObject
	 * @param source
	 * @param cls
	 * @return T
	 * @throws UanException
	 */ 
	public <T> T toObject(String source, Class<T> cls) {
		try {
			return JSON.parseObject(source, cls);
		} catch (Exception e) {
			throw new UanException("Json转换Object错误", e);
		}
	}

	/** 
	 * @Description: toObject
	 * @param source
	 * @param cls
	 * @param elementCls
	 * @return T
	 * @throws UanException
	 */ 
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
