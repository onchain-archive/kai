package com.abc.uan.blockchain;

public class TradePojo extends AbstractBlockChainPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 465073818860060640L;

	private String customer = "None";
	private String cardNum = "None";
	private String party = "None";
	private Double amt = -99D;
	// private String account = "resource:org.acme.mynetwork.CardAccount#" +
	// cardNum;

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

	public String getAccount() {
		return "resource:org.acme.mynetwork.CardAccount#" + cardNum;
	}

	public void setAccount(String account) {
//		this.account = account;
	}

}
