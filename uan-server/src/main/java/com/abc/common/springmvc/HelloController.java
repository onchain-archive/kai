package com.abc.common.springmvc;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.common.AbstractController;
import com.abc.common.servlet.NodeDeploymentDescriptor;
import com.abc.common.util.LogWriter;

@RestController
@RequestMapping
public class HelloController extends AbstractController {

	@RequestMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
	public String hello() {

		String str = NodeDeploymentDescriptor.getInstance().toString();
		return str;

	}

	@RequestMapping(value = "/uan/*")
	public void uan() {
		LogWriter.info(HelloController.class, "一个uan的请求");
	}

}
