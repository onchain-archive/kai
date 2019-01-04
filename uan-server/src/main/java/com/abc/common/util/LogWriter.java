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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title: LogWriter
 * @Description: LogWriter
 * @author Bo Liu
 * @date 2018-09-20
 */
public class LogWriter {

	/** 
	 * @Description: trace
	 * @param cls
	 * @param msg
	 * @param params
	 */ 
	public static void trace(Class<?> cls, String msg, Object... params) {
		Logger logger = LoggerFactory.getLogger(cls);
		if (logger.isTraceEnabled()) {
			logger.trace(msg, params);
		}
	}

	/** 
	 * @Description: info
	 * @param cls
	 * @param msg
	 * @param params
	 */ 
	public static void info(Class<?> cls, String msg, Object... params) {
		Logger logger = LoggerFactory.getLogger(cls);
		if (logger.isInfoEnabled()) {
			logger.info(msg, params);
		}
	}

	/** 
	 * @Description: error
	 * @param cls
	 * @param msg
	 * @param exception
	 */ 
	public static void error(Class<?> cls, String msg, Throwable exception) {
		Logger logger = LoggerFactory.getLogger(cls);
		if (logger.isErrorEnabled()) {
			logger.error(msg, exception);
		}
	}

}
