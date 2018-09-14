package com.abc.common;

import java.io.Serializable;

import com.abc.common.util.JsonConvertor;

public abstract class AbstractPojo implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
		JsonConvertor jsonConvertor = new JsonConvertor();
		return jsonConvertor.toJson(this);
	}

}
