package com.abc.bank.customerinformation;

import java.util.Date;

import com.abc.common.AbstractPojo;

public class WithdrawCommondPojo extends AbstractPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2220244440907081535L;

	private String customer;
	private String cardNum;
	private String party;
	private Double amt;
	private String deviceNum;
	private Date trDate;

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
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
