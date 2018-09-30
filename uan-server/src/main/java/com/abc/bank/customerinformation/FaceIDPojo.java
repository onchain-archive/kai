/**  
 * Title: FaceIDPojo.java
 * Description: FaceIDPojo
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.bank.customerinformation;

import com.abc.common.AbstractPojo;

public class FaceIDPojo extends AbstractPojo {
	
	private static final long serialVersionUID = 7920486993575575478L;
	private String idCard;
	private String faceBytes;

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getFaceBytes() {
		return faceBytes;
	}

	public void setFaceBytes(String faceBytes) {
		this.faceBytes = faceBytes;
	}

}
