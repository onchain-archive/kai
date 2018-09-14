package com.abc.uan.blockchain;

public class PersonPojo extends AbstractBlockChainPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2030890783591594930L;

	private String idCard = "None";
	private String name = "None";
	private String idPhoto = "None";// base64

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

	public String getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(String idPhoto) {
		this.idPhoto = idPhoto;
	}

}
