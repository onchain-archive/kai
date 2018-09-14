package com.abc.bank.uancontract;

import com.abc.common.AbstractPojo;

public class UanContractBCPojo extends AbstractPojo {

	/**
	 * 
	 */
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
