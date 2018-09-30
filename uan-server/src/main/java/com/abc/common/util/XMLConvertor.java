/**  
 * Title: XMLConvertor.java
 * Description: XMLConvertor
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.common.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;

/**
 * Title: XMLConvertor
 * @Description: XMLConvertor
 * @author Bo Liu
 * @date 2018-09-20
 */
@Component
public class XMLConvertor {

	/** 
	 * @Description: getXstream
	 * @return XStream
	 */ 
	protected XStream getXstream() {
		return new XStream();
	}

	/** 
	 * @Description: toXml
	 * @param source
	 * @return String
	 */ 
	public String toXml(Object source) {
		if (source == null) {
			return "";
		}
		XStream stream = getXstream();
		return stream.toXML(source);
	}

	/** 
	 * @Description: toObject
	 * @param xml
	 * @return Object
	 */ 
	public Object toObject(String xml) {
		if (StringUtils.isEmpty(xml)) {
			return null;
		}
		XStream stream = getXstream();
		return stream.fromXML(xml);
	}
}
