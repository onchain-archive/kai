package com.abc.common;

public class RejectedAccessException extends UanException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4894033051527443132L;

	
	public RejectedAccessException() {
		super();
	}

	public RejectedAccessException(String msg) {
		super(msg);
	}

	public RejectedAccessException(Throwable cause) {
		super(cause);
	}

	public RejectedAccessException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
