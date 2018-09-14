package com.abc.common.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;

@Component
public class XMLConvertor {

	protected XStream getXstream() {
		return new XStream();
	}

	public String toXml(Object source) {
		if (source == null) {
			return "";
		}
		XStream stream = getXstream();
		return stream.toXML(source);
	}

	public Object toObject(String xml) {
		if (StringUtils.isEmpty(xml)) {
			return null;
		}
		XStream stream = getXstream();
		return stream.fromXML(xml);
	}
}
