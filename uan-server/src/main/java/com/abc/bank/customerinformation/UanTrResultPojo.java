package com.abc.bank.customerinformation;

import com.abc.common.AbstractPojo;

public class UanTrResultPojo extends AbstractPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2909932337840593542L;

	private String masterName;
	private String cardNum;
	private String info;

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
