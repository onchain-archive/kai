/**  
 * Title: UanException.java
 * Description: UanException
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.common;

/**
 * Title: UanException
 * @Description: UanException
 * @author Bo Liu
 * @date 2018-09-20
 */
public class UanException extends RuntimeException {

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
