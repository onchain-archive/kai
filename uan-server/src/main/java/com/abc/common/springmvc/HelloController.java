/**  
 * Title: HelloController.java
 * Description: HelloController
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.common.springmvc;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.common.AbstractController;
import com.abc.common.servlet.NodeDeploymentDescriptor;
import com.abc.common.util.LogWriter;

/**
 * Title: HelloController
 * @Description: HelloController
 * @author Bo Liu
 * @date 2018-09-20
 */
@RestController
@RequestMapping
public class HelloController extends AbstractController {

	/** 
	 * @Description: hello
	 * @return  String
	 * @throws 
	 */ 
	@RequestMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
	public String hello() {

		String str = NodeDeploymentDescriptor.getInstance().toString();
		return str;

	}

	/** 
	 * @Description: uan 
	 * @throws 
	 */ 
	@RequestMapping(value = "/uan/*")
	public void uan() {
		LogWriter.info(HelloController.class, "一个uan的请求");
	}

}
