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
 * Title: TradePojo
 * @Description: TradePojo
 * @author Bo Liu
 * @date 2018-09-20
 */
public class TradePojo extends AbstractBlockChainPojo {

	private static final long serialVersionUID = 465073818860060640L;

	private String customer = "None";
	private String cardNum = "None";
	private String party = "None";
	private Double amt = -99D;

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

	}

}
