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
 * Title: BankReserveAccountPojo
 * @Description: BankReserveAccountPojo
 * @author Bo Liu
 * @date 2018-09-20
 */
public class BankReserveAccountPojo extends AbstractBlockChainPojo {

	private static final long serialVersionUID = -806741968534217166L;

	private String bank = "None";
	private String reference = "None";
	private String debit = "None";
	/**
	 * 	借，存
	 */
	private String credit = "None";
	/**
	 * 	// 贷，取
	 */
	private Double balance = -99D;
	private String debitBank = "None";
	private String creditBank = "None";

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDebit() {
		return debit;
	}

	public void setDebit(String debit) {
		this.debit = debit;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getDebitBank() {
		return debitBank;
	}

	public void setDebitBank(String debitBank) {
		this.debitBank = debitBank;
	}

	public String getCreditBank() {
		return creditBank;
	}

	public void setCreditBank(String creditBank) {
		this.creditBank = creditBank;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

}
