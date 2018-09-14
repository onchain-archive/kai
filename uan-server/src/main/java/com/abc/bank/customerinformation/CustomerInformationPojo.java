package com.abc.bank.customerinformation;

import com.abc.common.AbstractPojo;

public class CustomerInformationPojo extends AbstractPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7074995157715860919L;

	public static final String STATE_ON = "ON";
	public static final String STATE_OFF = "OFF";
	public static final String TYPE_CORPORATE = "CORPORATE";
	public static final String TYPE_PERSONAL = "PERSONAL";

	private String id;
	private String name;
	private String idCard;
	private String phone;
	private String state;
	private String type;
	private String pwd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
