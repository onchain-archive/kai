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

import java.util.ArrayList;
import java.util.List;

import com.abc.common.UanException;

/**
 * @author
 * 
 */
public class ExceptionUtils {

	public static final String CAUSE = "  Caused by: ";

	public static int STACKTRACE_DEEP = 10;

	/**
	 * 
	 * 
	 */
	public static String toString(Throwable e) {
		StringBuilder sb = new StringBuilder(e.getClass().getName()).append(": ").append(getMessage(e));
		Throwable tmp = e;
		while (tmp != null) {
			sb.append(toString(e.getStackTrace()));
			// 获得cause信息
			if ((tmp.getCause() != null) && (tmp != tmp.getCause())) {
				tmp = tmp.getCause();
				sb.append(CAUSE).append(tmp.getClass().getName()).append(": ").append(tmp.getMessage());
			} else {
				tmp = null;
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * 
	 */
	public static String toString(StackTraceElement[] stes) {
		StringBuilder sb = new StringBuilder("\n");
		int i = 0;
		for (StackTraceElement ste : stes) {
			if (i > STACKTRACE_DEEP) {
				break;
			}
			i++;
			sb.append("        at  ").append(ste.toString()).append("\n");

		}
		return sb.toString();
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public static String genKeyMessage(List<String> list) {
		StringBuilder result = new StringBuilder();

		if (list.size() == 1) {
			result.append((list.get(0) == null) ? "" : list.get(0));
		} else if (list.size() == 2) {
			result.append(list.get(0)).append(CAUSE);
			if (list.get(1) == null) {
				result.append("none");
			} else {
				result.append(list.get(1));
			}
		} else {
			throw new UanException("参数列表只能包含1至2条信息");
		}

		return (result.toString().indexOf("\n") > 0) ? result.substring(0, result.toString().indexOf("\n"))
				: result.toString();
	}

	/**
	 * 
	 * 
	 */
	public static String getMessage(Throwable e) {
		List<Throwable> list = getKeyException(e);
		List<String> ms = new ArrayList<String>();

		if (list.size() == 0) {
			ms.add("无错误信息，请查看明细信息。");
		} else if (list.size() > 2) {
			LogWriter.error(ExceptionUtils.class, "异常信息解析错误，请检查日志", e);
			throw new UanException("异常信息解析错误，请检查日志");
		} else {
			for (Throwable t : list) {
				if (t != null) {
					ms.add(t.getMessage());
				}
			}
		}

		return genKeyMessage(ms);
	}

	/**
	 * 
	 * 
	 */
	public static List<Throwable> getKeyException(Throwable e) {
		List<Throwable> ts = new ArrayList<Throwable>();
		if (e instanceof UanException || UanException.class.isAssignableFrom(e.getClass())) {
			ts.add((UanException) e);
			Throwable cause = e.getCause();
			if (cause != null) {
				List<Throwable> cs = getKeyException(cause);
				ts.add(0, (cs.size() == 1) ? cs.get(0) : cs.get(1));
			} else {
				ts.add(null);
			}
		} else {
			Throwable cause = e.getCause();
			if (cause == null) {
				ts.add(e);
			} else {
				ts.add(getKeyException(cause).get(0));
			}
		}
		return ts;
	}

}
