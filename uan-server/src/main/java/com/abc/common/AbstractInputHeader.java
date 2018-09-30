package com.abc.common;

/**
 * @author 011893494
 * 
 * 
 * 
 */
public abstract class AbstractInputHeader extends AbstractPojo {

	private static final long serialVersionUID = 756047095878160923L;
	private String uniqueId; 
	// 统一流水号(请求终端设备产生，全流程唯一号),要求实现 (不应删除此字段)
	/**
	 * 统一流水号
	 */
	private String preUniqueId;
	private String appId; 
	// 请求方标识,推荐实现 (如不需要可删除此字段)
	/**
	 * 用户标识码,可选择性实现 (如不需要可删除此字段)
	 */
	private String userId; 
	/**
	 * 交易请求口令,可选择性实现 (如不需要可删除此字段)
	 */
	private String trToken; 
	/**
	 * 碎片化的数据访问规则，用于分库分表等场景
	 */
	private String shardingDataSource;

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getPreUniqueId() {
		return preUniqueId;
	}

	public void setPreUniqueId(String preUniqueId) {
		this.preUniqueId = preUniqueId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTrToken() {
		return trToken;
	}

	public void setTrToken(String trToken) {
		this.trToken = trToken;
	}

	public String getShardingDataSource() {
		return shardingDataSource;
	}

	public void setShardingDataSource(String shardingDataSource) {
		this.shardingDataSource = shardingDataSource;
	}
}
