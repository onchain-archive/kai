/**  
 * Title: CustomerInformationPojo.java
 * Description: CustomerInformationPojo
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.bank.customerinformation;

import com.abc.common.AbstractPojo;

/**
 * Title: CustomerInformationPojo
 * @Description: CustomerInformationPojo
 * @author Bo Liu
 * @date 2018-09-20
*/
public class CustomerInformationPojo extends AbstractPojo {

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

	/** 
	 * @Description: getId
	 * @return  String
	 */ 
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
