/**  
 * Title: BeanFactoryUtils.java
 * Description: BeanFactoryUtils
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.access.BootstrapException;
import org.springframework.stereotype.Component;

/**
 * Title: BeanFactoryUtils
 * @Description: BeanFactoryUtils
 * @author Bo Liu
 * @date 2018-09-20
 */
@Component("beanFactoryUtils")
public class BeanFactoryUtils implements BeanFactoryAware {

	private static BeanFactory FACTORY;

	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		FACTORY = arg0;
	}

	/** 
	 * @Description: getBean
	 * @param beanName
	 * @return BeansException  T
	 * @throws BootstrapException
	 */ 
	public static <T> T getBean(String beanName) throws BeansException {
		if (FACTORY == null) {
			throw new BootstrapException("spring工厂未能初始化，请检查BeanFactoryUtils");
		}
		Object bean = FACTORY.getBean(beanName);
		return (T) bean;
	}

	/** 
	 * @Description: getBean
	 * @param beanClass
	 * @return BeansException  T
	 * @throws 
	 */ 
	public static <T> T getBean(Class<T> beanClass) throws BeansException {
		Object bean = null;
		try {
			bean = BeanFactoryUtils.getBean(beanClass.getName());
		} catch (BeansException e) {
			bean = FACTORY.getBean(beanClass);
		}
		return (T) bean;
	}

}
