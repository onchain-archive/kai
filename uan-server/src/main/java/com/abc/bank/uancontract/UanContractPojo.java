
/**  
 * Title: UanContractPojo.java
 * Description: UanContractPojo
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ package com.abc.bank.uancontract;

import java.util.Date;
import java.util.List;

import com.abc.bank.bankcard.BankCardPojo;
import com.abc.bank.customerinformation.CustomerInformationPojo;
import com.abc.common.AbstractPojo;

/**
 * Title: UanContractPojo
 * @Description: UanContractPojo
 * @author Bo Liu
 * @date 2018-09-20
*/
public class UanContractPojo extends AbstractPojo {

	private static final long serialVersionUID = -2322048537911447436L;

	public static final String STATE_SIGNED = "SIGNED";
	public static final String STATE_ABANDONED = "ABANDONED";

	private CustomerInformationPojo customerInformation;

	private boolean agreeWithUan = false;

	private List<PersonnelRelationshipPojo> personnelRelationships;
	private List<BankCardPojo> bindingCards;

	private String transactBank;
	private Date transactDate = new Date();
	private Date changeDate;
	private String changeInfo;
	private String state;

	private String content;

	public CustomerInformationPojo getCustomerInformation() {
		return customerInformation;
	}

	public void setCustomerInformation(CustomerInformationPojo customerInformation) {
		this.customerInformation = customerInformation;
	}

	public boolean isAgreeWithUan() {
		return agreeWithUan;
	}

	public void setAgreeWithUan(boolean agreeWithUan) {
		this.agreeWithUan = agreeWithUan;
	}

	public List<PersonnelRelationshipPojo> getPersonnelRelationships() {
		return personnelRelationships;
	}

	public void setPersonnelRelationships(List<PersonnelRelationshipPojo> personnelRelationships) {
		this.personnelRelationships = personnelRelationships;
	}

	public List<BankCardPojo> getBindingCards() {
		return bindingCards;
	}

	public void setBindingCards(List<BankCardPojo> bindingCards) {
		this.bindingCards = bindingCards;
	}

	public String getTransactBank() {
		return transactBank;
	}

	public void setTransactBank(String transactBank) {
		this.transactBank = transactBank;
	}

	public Date getTransactDate() {
		return transactDate;
	}

	public void setTransactDate(Date transactDate) {
		this.transactDate = transactDate;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getChangeInfo() {
		return changeInfo;
	}

	public void setChangeInfo(String changeInfo) {
		this.changeInfo = changeInfo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
