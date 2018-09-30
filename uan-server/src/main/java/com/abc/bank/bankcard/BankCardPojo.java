/**  
 * Title: BankCardPojo.java
 * Description: BankCardPojo
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.bank.bankcard;

import java.math.BigDecimal;

import com.abc.bank.customerinformation.CustomerInformationPojo;
import com.abc.common.AbstractPojo;

/**
 * Title: BankCardPojo
 * @Description: BankCardPojo
 * @author Bo Liu
 * @date 2018-09-20
*/
public class BankCardPojo extends AbstractPojo {

	private static final long serialVersionUID = 2507762584677732090L;

	public static final String STATE_NORMAL = "NORMAL";
	public static final String STATE_ABNORMAL = "ABNORMAL";

	private String id;
	private String code;
	private String idCard;
	private CustomerInformationPojo customerInformation;
	private String state = STATE_NORMAL;
	private BigDecimal amt;
	private String bankOfDeposit;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public CustomerInformationPojo getCustomerInformation() {
		return customerInformation;
	}

	public void setCustomerInformation(CustomerInformationPojo customerInformation) {
		this.customerInformation = customerInformation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getBankOfDeposit() {
		return bankOfDeposit;
	}

	public void setBankOfDeposit(String bankOfDeposit) {
		this.bankOfDeposit = bankOfDeposit;
	}

}
