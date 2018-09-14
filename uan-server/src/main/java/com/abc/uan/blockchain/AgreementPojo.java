package com.abc.uan.blockchain;

public class AgreementPojo extends AbstractBlockChainPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3681912757975870845L;

	private String[] personnelIdCards= {"None"};
	private String[] bindingCardNums= {"None"};
	private String party="None";

//	private String owner = "resource:org.acme.mynetwork.Person#0000000";

	public String[] getPersonnelIdCards() {
		return personnelIdCards;
	}

	public void setPersonnelIdCards(String[] personnelIdCards) {
		this.personnelIdCards = personnelIdCards;
	}

	public String[] getBindingCardNums() {
		return bindingCardNums;
	}

	public void setBindingCardNums(String[] bindingCardNums) {
		this.bindingCardNums = bindingCardNums;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	// public String getOwner() {
	// return owner;
	// }
	//
	// public void setOwner(String owner) {
	// this.owner = owner;
	// }

}
