/**  
 * Title: PersonnelRelationshipPojo.java
 * Description: PersonnelRelationshipPojo
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.bank.uancontract;

import com.abc.common.AbstractPojo;

/**
 * Title: PersonnelRelationshipPojo
 * @Description: PersonnelRelationshipPojo
 * @author Bo Liu
 * @date 2018-09-20
*/
public class PersonnelRelationshipPojo extends AbstractPojo {

	private static final long serialVersionUID = 8685551051470216812L;
	
	private String id;
	private String masterId;
	private String masterName;
	private int order;
	private String slaveId;
	private String slaveName;
	private String relationship;
	private String phone;
	private String idPhoto;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getSlaveId() {
		return slaveId;
	}

	public void setSlaveId(String slaveId) {
		this.slaveId = slaveId;
	}

	public String getSlaveName() {
		return slaveName;
	}

	public void setSlaveName(String slaveName) {
		this.slaveName = slaveName;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public String getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(String idPhoto) {
		idPhoto = idPhoto;
	}

}
