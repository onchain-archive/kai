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
