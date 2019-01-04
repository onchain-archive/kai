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
