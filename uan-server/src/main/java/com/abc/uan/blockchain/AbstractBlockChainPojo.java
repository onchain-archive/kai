package com.abc.uan.blockchain;

import com.abc.common.AbstractPojo;
import com.abc.common.util.BeanFactoryUtils;
import com.abc.common.util.UUIDNumberGenerator;

public abstract class AbstractBlockChainPojo extends AbstractPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4217343769872941022L;

	private String serialNumber = BeanFactoryUtils.getBean(UUIDNumberGenerator.class).generate();
	private String trType = "None";
	private String trTime = "None";
	private String trDetail = "None";

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getTrType() {
		return trType;
	}

	public void setTrType(String trType) {
		this.trType = trType;
	}

	public String getTrTime() {
		return trTime;
	}

	public void setTrTime(String trTime) {
		this.trTime = trTime;
	}

	public String getTrDetail() {
		return trDetail;
	}

	public void setTrDetail(String trDetail) {
		this.trDetail = trDetail;
	}

}
