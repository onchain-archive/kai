package com.abc.common;

/**
 * @author
 * 
 */
public abstract class AbstractOutputHeader extends AbstractPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7922952963828251159L;

	private String appId;
	private String uniqueId;
	private String id;
	private String statusCode = "200";
	private String message;
	private String detail;
	private String exceptionClassName;

	protected AbstractOutputHeader() {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getExceptionClassName() {
		return exceptionClassName;
	}

	public void setExceptionClassName(String exceptionClassName) {
		this.exceptionClassName = exceptionClassName;
	}

}
