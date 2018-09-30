/**  
 * Title: CfgValueHandler.java
 * Description: CfgValueHandler
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Title: CfgValueHandler
 * @Description: CfgValueHandler
 * @author Bo Liu
 * @date 2018-09-20
 */
@Component
public class CfgValueHandler {

	@Value("${uan.url.exclusions}")
	private String urlExclusions;

	@Value("${uan.login.url}")
	private String loginUrl;

	@Value("${uan.app.id}")
	private String appId;

	@Value("${uan.blockchain.url}")
	private String blockChainUrl;

	@Value("${uan.visualrecognition.version}")
	private String visualRecognitionVersion;
	@Value("${uan.visualrecognition.endpoint}")
	private String visualRecognitionEndPoint;
	@Value("${uan.visualrecognition.iamapikey}")
	private String visualRecognitionIamApiKey;

	public String getUrlExclusions() {
		return urlExclusions;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public String getAppId() {
		return appId;
	}

	public String getBlockChainUrl() {
		return blockChainUrl;
	}

	public String getVisualRecognitionVersion() {
		return visualRecognitionVersion;
	}

	public String getVisualRecognitionEndPoint() {
		return visualRecognitionEndPoint;
	}

	public String getVisualRecognitionIamApiKey() {
		return visualRecognitionIamApiKey;
	}

}
