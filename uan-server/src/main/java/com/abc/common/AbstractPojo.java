/**  
 * Title: AbstractPojo.java
 * Description: AbstractPojo
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.common;

import java.io.Serializable;

import com.abc.common.util.JsonConvertor;

public abstract class AbstractPojo implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		JsonConvertor jsonConvertor = new JsonConvertor();
		return jsonConvertor.toJson(this);
	}

}
