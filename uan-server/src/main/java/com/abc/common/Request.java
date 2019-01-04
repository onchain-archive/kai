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
package com.abc.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.abc.common.bus.RequestBus;
import com.abc.common.bus.ThreadContext;
import com.abc.common.util.JsonConvertor;
import com.abc.common.util.LogWriter;
import com.alibaba.fastjson.JSONObject;

/**
 * @author 011893494
 * 
 * 
 * 
 */
public class Request<T> extends AbstractInputHeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2283880601113167596L;
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static Request create(Object requestObj, Request request, HttpServletRequest req) {
		if (StringUtils.isNotEmpty(request.getUserId())) {
			((RequestBus) ThreadContext.getContext().getAttribute(RequestBus.REQUEST_BUS)).getAttributes()
					.put(RequestBus.USER, request.getUserId());
		}
		request.setData(requestObj);
		return request;
	}

	public static Request create(String requestStr, HttpServletRequest req, Class dataClass) {
		LogWriter.info(Request.class, "请求报文：" + requestStr);
		if (requestStr == null) {
			throw new UanException("缺少输入报文");
		}
		JsonConvertor jsonConvertor = new JsonConvertor();
		Request request = jsonConvertor.toObject(requestStr, Request.class);
		if (StringUtils.isNotEmpty(request.getUserId())) {
			((RequestBus) ThreadContext.getContext().getAttribute(RequestBus.REQUEST_BUS)).getAttributes()
					.put(RequestBus.USER, request.getUserId());
		}
		if (dataClass == null) {
			return request;
		}
		JSONObject jsonobj = JSONObject.parseObject(requestStr);
		Object obj = jsonobj.get("data");
		if (obj.toString().startsWith("[")) {
			request.setData(JSONObject.parseArray(obj.toString(), new Class[] { dataClass }).toArray());
		} else {
			request.setData(JSONObject.parseObject(obj.toString(), dataClass));
		}

		return request;

	}
}
