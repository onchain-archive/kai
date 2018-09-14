package com.abc.common.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.abc.common.util.LogWriter;

/**
 * @author liubo 获取node
 */
public class NodeDeploymentDescriptorListener implements ServletContextListener {
	/**
	 * WAS上下文获取服务器名称的属性名称
	 */
	private static final String WASATTRIBUTE = "com.ibm.websphere.servlet.application.host";

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		Object deploymentDescriptor = event.getServletContext().getAttribute(WASATTRIBUTE);

		if (deploymentDescriptor != null) {
			NodeDeploymentDescriptor.getInstance().setDeploymentDescriptor(deploymentDescriptor.toString());
			LogWriter.info(NodeDeploymentDescriptorListener.class, "WAS NodeDeploymentDescriptor is {}",
					deploymentDescriptor.toString());
		}
	}
}
