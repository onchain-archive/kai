/**  
 * Title: RejectedAccessException.java
 * Description: RejectedAccessException
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.common;

/**
 * Title: RejectedAccessException
 * @Description: RejectedAccessException
 * @author Bo Liu
 * @date 2018-09-20
*/
public class RejectedAccessException extends UanException {

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
