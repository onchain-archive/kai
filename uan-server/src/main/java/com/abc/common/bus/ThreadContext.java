package com.abc.common.bus;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.abc.common.util.BeanFactoryUtils;
import com.abc.common.util.JsonConvertor;

/**
 * 当前运行的线程的上下文
 * 
 * @author liubo
 */
public class ThreadContext {

	/**
	 * 存储属性
	 */
	private Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 线程局部变量，当前线程ThreadContext
	 */
	private static ThreadLocal<ThreadContext> context = new ThreadLocal<ThreadContext>() {
		@Override
		protected synchronized ThreadContext initialValue() {
			return new ThreadContext();
		}
	};

	/**
	 * 构造ThreadContext，避免被外部实例化
	 */
	private ThreadContext() {
	}

	/**
	 * 获取线程上下文对象
	 */
	public static ThreadContext getContext() {
		return (ThreadContext) context.get();
	}

	/**
	 * (non-Javadoc)
	 * @see plantix.core.context.Context#getAttribute(java.lang.String)
	 */
	public Object getAttribute(String key) {
		return map.get(key);
	}

	/**
	 * @return
	 * 
	 */
	public Map<String, Object> getAttributeMap() {
		return map;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see plantix.core.context.Context#setAttribute(java.lang.String,
	 * java.lang.Object)
	 */
	public void setAttribute(String key, Object value) {
		map.put(key, value);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see plantix.core.context.Context#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String key) {
		map.remove(key);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see plantix.core.context.Context#getAttributeNames()
	 */
	public Collection<String> getAttributeNames() {
		return map.keySet();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see plantix.core.context.Context#clear()
	 */
	public void clear() {
		map.clear();
	}
	
	/** 
	 * @Description: print
	 * @return
	 */ 
	public static String print() {
		return BeanFactoryUtils.getBean(JsonConvertor.class).toJson(ThreadContext.getContext().getAttributeNames());
	}
}
