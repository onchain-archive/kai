/**  
 * Title: UanServletApplication.java
 * Description: UanServletApplication
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.common.starter;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Title: UanServletApplication
 * @Description: UanServletApplication
 * @author Bo Liu
 * @date 2018-09-20
 */
public class UanServletApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(UanApplication.class);
	}

}
