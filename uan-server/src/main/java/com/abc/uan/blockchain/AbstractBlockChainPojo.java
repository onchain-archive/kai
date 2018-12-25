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

/**  
 * Title: AbstractBlockChainPojo.java
 * Description: AbstractBlockChainPojo
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.uan.blockchain;

import com.abc.common.AbstractPojo;
import com.abc.common.util.BeanFactoryUtils;
import com.abc.common.util.UUIDNumberGenerator;

/**
 * Title: AbstractBlockChainPojo
 * @Description: AbstractBlockChainPojo
 * @author Bo Liu
 * @date 2018-09-20
 */
public abstract class AbstractBlockChainPojo extends AbstractPojo {

	private static final long serialVersionUID = -4217343769872941022L;

	private String serialNumber = BeanFactoryUtils.getBean(UUIDNumberGenerator.class).generate();
	private String trType = "None";
	private String trTime = "None";
	private String trDetail = "None";

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getTrType() {
		return trType;
	}

	public void setTrType(String trType) {
		this.trType = trType;
	}

	public String getTrTime() {
		return trTime;
	}

	public void setTrTime(String trTime) {
		this.trTime = trTime;
	}

	public String getTrDetail() {
		return trDetail;
	}

	public void setTrDetail(String trDetail) {
		this.trDetail = trDetail;
	}

}
