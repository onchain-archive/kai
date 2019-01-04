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
package com.abc.bank.uancontract;

import com.abc.common.AbstractPojo;

/**
 * Title: UanContractBCPojo
 * @Description: UanContractBCPojo
 * @author Bo Liu
 * @date 2018-09-20
*/
public class UanContractBCPojo extends AbstractPojo {

	private static final long serialVersionUID = 6452977660578513308L;

	private String masterId;
	private String content;

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
