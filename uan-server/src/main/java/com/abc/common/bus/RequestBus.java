package com.abc.common.bus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.abc.common.UanException;
import com.abc.common.util.BeanFactoryUtils;
import com.abc.common.util.JsonConvertor;
import com.abc.common.util.LogWriter;
import com.abc.common.util.UUIDNumberGenerator;

/**
 * 
 * @author liubo
 * 
 */
public class RequestBus {

	public static final String REQUEST_BUS = "request.bus";
	public static final String USER = "user";

	private String token;
	private String id;
	private String namespace;
	private String uniqueId;
	private String flowNum = "0";

	private Date start;
	private Date end;
	private long useTime;
	private String state;
	private Map<String, Object> attributes = new HashMap<String, Object>();

	public RequestBus() {
		id = BeanFactoryUtils.getBean(UUIDNumberGenerator.class).generate();
		start = new Date();
		state = "BEGIN";
	}

	public RequestBus(String namespace) {
		id = BeanFactoryUtils.getBean(UUIDNumberGenerator.class).generate();
		start = new Date();
		state = "BEGIN";
		this.namespace = namespace;
		ThreadContext.getContext().setAttribute(REQUEST_BUS, this);
	}

	public RequestBus(String namespace, String userId) {
		id = BeanFactoryUtils.getBean(UUIDNumberGenerator.class).generate();
		start = new Date();
		state = "BEGIN";
		this.namespace = namespace;
		ThreadContext.getContext().setAttribute(REQUEST_BUS, this);
		attributes.put(USER, userId);
	}

	/**
	 * @param error
	 * @param rejection
	 * 
	 */
	public void close(boolean error, boolean rejection) {
		end = new Date();
		useTime = ((end != null) && (start != null)) ? (end.getTime() - start.getTime()) : -1;
		LogWriter.trace(RequestBus.class, "访问请求完成。上下文如下\n[{}]", toString());

		if (!state.equals("EXCEPTION")) {
			if (error) {
				state = "EXCEPTION";
			} else if (rejection) {
				state = "REJECTION";
			} else {
				state = "END";
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public static RequestBus get() {
		Object o = ThreadContext.getContext().getAttribute(REQUEST_BUS);
		if (o == null) {
			UanException e = new UanException("交易总线不存在");
			LogWriter.error(RequestBus.class, "交易总线不存在", e);
			throw e;
		} else {
			return (RequestBus) o;
		}
	}

	/**
	 * 
	 */
	public void update() {
		Object o = ThreadContext.getContext().getAttribute(REQUEST_BUS);
		if (o != null) {
			if (o != this) {
				LogWriter.info(RequestBus.class, "覆盖交易总线");
				ThreadContext.getContext().setAttribute(REQUEST_BUS, this);
			}
		} else {
			ThreadContext.getContext().setAttribute(REQUEST_BUS, this);
		}
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getUseTime() {
		return useTime;
	}

	public void setUseTime(long useTime) {
		this.useTime = useTime;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getFlowNum() {
		return flowNum;
	}

	public void setFlowNum(String flowNum) {
		this.flowNum = flowNum;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return BeanFactoryUtils.getBean(JsonConvertor.class).toJson(this);

	}
}
