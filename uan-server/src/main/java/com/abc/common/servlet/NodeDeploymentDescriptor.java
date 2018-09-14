package com.abc.common.servlet;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.abc.common.UanException;
import com.abc.common.util.ConvertUtils;
import com.abc.common.util.JsonConvertor;

/**
 * 
 * @author liubo
 */
public class NodeDeploymentDescriptor {

	/**
	 * 服务器名称属性名常量
	 */
	public static final String DEPLOYMENT_DESCRIPTOR = "DEPLOYMENT_DESCRIPTOR";
	private static NodeDeploymentDescriptor instance = new NodeDeploymentDescriptor();

	private String deploymentDescriptor = null;
	private String id = "000";
	private String name = "UAN服务";
	private Date lastStartDate;
	private String app = "uan";
	private String version = "0.1.0";

	private boolean initFlag = false;

	private NodeDeploymentDescriptor() {
		init();
	}

	/**
	 * @return
	 * 
	 */
	public static NodeDeploymentDescriptor getInstance() {
		return instance;
	}

	/**
	 * 
	 */
	protected synchronized void init() {
		if (initFlag) {
			return;
		}

		InetAddress netAddress = null;
		try {
			netAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			throw new UanException("getLocalHost error in ServerInfoContext.init(), the server will not started.", e);
		}
		if (StringUtils.isEmpty(deploymentDescriptor)) {
			// 首先接受通过System.getProperty设置的deploymentDescriptor
			deploymentDescriptor = System.getProperty(NodeDeploymentDescriptor.DEPLOYMENT_DESCRIPTOR);
			if (StringUtils.isEmpty(deploymentDescriptor)) {
				// 如果取不到,则采纳操作系统定义的主机名
				deploymentDescriptor = netAddress.getHostName() + " " + netAddress.getHostAddress();
				if (StringUtils.isNotEmpty(deploymentDescriptor)) {
					System.setProperty(NodeDeploymentDescriptor.DEPLOYMENT_DESCRIPTOR, deploymentDescriptor);
				} else {
					throw new UanException("get ServerName error.the server will not started.");
				}
			}
		}
		initFlag = true;

		lastStartDate = new Date();
		id = ConvertUtils.intToString(generateNewNodeId());
		name = "undefined";
	}

	private int generateNewNodeId() {
		String num = getDeploymentDescriptor().replaceAll("[^0-9]", "");
		if (num.length() > 9) {
			num = num.substring(num.length() - 9);
		}
		return Integer.parseInt(num);
	}

	/**
	 */
	public void stop() {
		if (initFlag) {
			deploymentDescriptor = null;
			initFlag = false;
		}
	}

	/**
	 * @param deploymentDescriptor
	 *            the deploymentDescriptor to set
	 */
	public void setDeploymentDescriptor(String deploymentDescriptor) {
		this.deploymentDescriptor = deploymentDescriptor;
	}

	/**
	 * 
	 * @return
	 */
	public String getDeploymentDescriptor() {
		return deploymentDescriptor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastStartDate() {
		return lastStartDate;
	}

	public void setLastStartDate(Date lastStartDate) {
		this.lastStartDate = lastStartDate;
	}

	public boolean isInitFlag() {
		return initFlag;
	}

	public void setInitFlag(boolean initFlag) {
		this.initFlag = initFlag;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return
	 * 
	 */
	public String getAppServerInfo() {
		return deploymentDescriptor;
	}

	@Override
	public String toString() {
		return new JsonConvertor().toJson(this);
	}
}
