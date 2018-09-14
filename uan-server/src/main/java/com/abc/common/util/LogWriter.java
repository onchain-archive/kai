package com.abc.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogWriter {

	public static void trace(Class<?> cls, String msg, Object... params) {
		Logger logger = LoggerFactory.getLogger(cls);
		if (logger.isTraceEnabled()) {
			logger.trace(msg, params);
		}
	}

	public static void info(Class<?> cls, String msg, Object... params) {
		Logger logger = LoggerFactory.getLogger(cls);
		if (logger.isInfoEnabled()) {
			logger.info(msg, params);
		}
	}

	public static void error(Class<?> cls, String msg, Throwable exception) {
		Logger logger = LoggerFactory.getLogger(cls);
		if (logger.isErrorEnabled()) {
			logger.error(msg, exception);
		}
	}

}
