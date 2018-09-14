package com.abc.bank.customerinformation;

import java.util.Date;

import com.abc.bank.bankcard.BankCardPojo;

public class ReportLossCommondPojo extends BankCardPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1775406707908969887L;

	private String party;
	private String deviceNum;
	private Date trDate;

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
