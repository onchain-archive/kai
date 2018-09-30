/**  
 * Title: UanContractBCPojo.java
 * Description: UanContractBCPojo
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
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
