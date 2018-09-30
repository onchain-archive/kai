/**  
 * Title: LogWriter.java
 * Description: LogWriter
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
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
