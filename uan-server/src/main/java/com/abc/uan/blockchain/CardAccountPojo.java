/*
 * Copyright 2018 Liu Bo
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
