/**  
 * Title: UnionPojo.java
 * Description: UnionPojo
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.uan.union;

import java.util.Date;

import com.abc.common.AbstractPojo;

/**
 * Title: UnionPojo
 * @Description: UnionPojo
 * @author Bo Liu
 * @date 2018-09-20
 */
public class UnionPojo extends AbstractPojo {

	public static final String STATE_OPEN = "OPEN";
	public static final String STATE_CLOSE = "CLOSE";

	public static final String TYPE_UAN = "UAN";
	public static final String TYPE_BANK = "BANK";
	public static final String TYPE_NON_BANK = "NON BANK";
	public static final String TYPE_GOV = "GOV";
	public static final String TYPE_GOV_SAFETY = "SAFETY";
	public static final String TYPE_ORG = "ORG";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String code;
	private Date joinInTime;
	private String state = STATE_CLOSE;
	private String type = TYPE_BANK;
	private String phone;
	private String address;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getJoinInTime() {
		return joinInTime;
	}

	public void setJoinInTime(Date joinInTime) {
		this.joinInTime = joinInTime;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
