package com.abc.uan.catastrophe;

import java.util.Date;

import com.abc.common.AbstractPojo;

public class CatastrophePojo extends AbstractPojo {

	/**
	 * 
	 */
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
