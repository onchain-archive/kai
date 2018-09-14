package com.abc.gov.residence;

import com.abc.common.AbstractPojo;

public class RegisteredResidencePagePojo extends AbstractPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6042221711060730595L;

	private String id;
	private RegisteredResidencePojo registeredResidence;
	private String idCard;
	private String name;
	private String telephone;
	private int relationshipWithHouseholder;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RegisteredResidencePojo getRegisteredResidence() {
		return registeredResidence;
	}

	public void setRegisteredResidence(RegisteredResidencePojo registeredResidence) {
		this.registeredResidence = registeredResidence;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getRelationshipWithHouseholder() {
		return relationshipWithHouseholder;
	}

	public void setRelationshipWithHouseholder(int relationshipWithHouseholder) {
		this.relationshipWithHouseholder = relationshipWithHouseholder;
	}

}
