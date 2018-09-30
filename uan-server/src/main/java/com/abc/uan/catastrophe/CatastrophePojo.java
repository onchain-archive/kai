/**  
 * Title: CatastrophePojo.java
 * Description: CatastrophePojo
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.uan.catastrophe;

import java.util.Date;

import com.abc.common.AbstractPojo;

/**
 * Title: CatastrophePojo
 * @Description: CatastrophePojo
 * @author Bo Liu
 * @date 2018-09-20
 */
public class CatastrophePojo extends AbstractPojo {

	private static final long serialVersionUID = -6803911185834285873L;

	private String id;
	private String name;
	private Date begin;
	private Date end;
	private String areas;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

}
