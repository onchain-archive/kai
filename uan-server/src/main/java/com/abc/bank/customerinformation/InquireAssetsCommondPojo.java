/**  
 * Title: InquireAssetsCommondPojo.java
 * Description: InquireAssetsCommondPojo
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.bank.customerinformation;

import java.util.Date;

import com.abc.common.AbstractPojo;

/**
 * Title: InquireAssetsCommondPojo
 * @Description: InquireAssetsCommondPojo
 * @author Bo Liu
 * @date 2018-09-20
*/
public class InquireAssetsCommondPojo extends AbstractPojo {

	private static final long serialVersionUID = 8992883287302022354L;
	private String customer;
	private String party;
	private String deviceNum;
	private Date trDate;

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public Date getTrDate() {
		return trDate;
	}

	public void setTrDate(Date trDate) {
		this.trDate = trDate;
	}

}
