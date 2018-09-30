/**  
 * Title: UanTrResultPojo.java
 * Description: UanTrResultPojo
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.bank.customerinformation;

import com.abc.common.AbstractPojo;

/**
 * Title: UanTrResultPojo
 * @Description: UanTrResultPojo
 * @author Bo Liu
 * @date 2018-09-20
*/
public class UanTrResultPojo extends AbstractPojo {

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
