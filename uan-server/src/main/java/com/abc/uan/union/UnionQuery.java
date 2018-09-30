/**  
 * Title: UnionQuery.java
 * Description: UnionQuery
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.uan.union;

import com.abc.common.AbstractPojo;

/**
 * Title: UnionQuery
 * @Description: UnionQuery
 * @author Bo Liu
 * @date 2018-09-20
 */
public class UnionQuery extends AbstractPojo {
	
	private static final long serialVersionUID = 7592348554232393456L;

	private String name;
	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
