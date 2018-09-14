package com.abc.uan.blockchain;

public class BankReserveAccountPojo extends AbstractBlockChainPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -806741968534217166L;

	private String bank = "None";
	private String reference = "None";
	private String debit = "None";// 借，存
	private String credit = "None";// 贷，取
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
