package com.abc.common;

public class UanException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UanException() {
		super();
	}

	public UanException(String msg) {
		super(msg);
	}

	public UanException(Throwable cause) {
		super(cause);
	}

	public UanException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
