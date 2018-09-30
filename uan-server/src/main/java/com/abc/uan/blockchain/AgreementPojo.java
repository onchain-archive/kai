/**  
 * Title: AgreementPojo.java
 * Description: AgreementPojo
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.uan.blockchain;

/**
 * Title: AgreementPojo
 * @Description: AgreementPojo
 * @author Bo Liu
 * @date 2018-09-20
 */
public class AgreementPojo extends AbstractBlockChainPojo {

	private static final long serialVersionUID = 3681912757975870845L;

	private String[] personnelIdCards= {"None"};
	private String[] bindingCardNums= {"None"};
	private String party="None";

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

}
