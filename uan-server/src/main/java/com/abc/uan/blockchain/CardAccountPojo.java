/**  
 * Title: CardAccountPojo.java
 * Description: CardAccountPojo
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.uan.blockchain;

/**
 * Title: CardAccountPojo
 * @Description: CardAccountPojo
 * @author Bo Liu
 * @date 2018-09-20
 */
public class CardAccountPojo extends AbstractBlockChainPojo {

	private static final long serialVersionUID = 1488102802975878268L;

	private String cardNum="None";
	private String idCard="None";
	private String state="None";
	private Double amtLeft=-99D;
	private String bankOfDeposit="None";

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getAmtLeft() {
		return amtLeft;
	}

	public void setAmtLeft(Double amtLeft) {
		this.amtLeft = amtLeft;
	}

	public String getBankOfDeposit() {
		return bankOfDeposit;
	}

	public void setBankOfDeposit(String bankOfDeposit) {
		this.bankOfDeposit = bankOfDeposit;
	}

}
