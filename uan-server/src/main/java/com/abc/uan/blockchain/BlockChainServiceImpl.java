/**  
 * Title: BlockChainService.java
 * Description: BlockChainService
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.uan.blockchain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.abc.common.IService;
import com.abc.common.util.CfgValueHandler;
import com.abc.common.util.JsonConvertor;
import com.abc.common.util.LogWriter;

@Component
public class BlockChainServiceImpl implements IService {

	@Autowired
	private JsonConvertor jsonConvertor;
	@Autowired
	private CfgValueHandler cfgValueHandler;

	/** 
	 * @Description: put
	 * @param trCode
	 * @param key
	 * @param reqObj
	 */ 
	public void put(String trCode, String key, Object reqObj) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		String reqJson = reqObj.toString();
		HttpEntity<String> httpEntity = new HttpEntity<String>(reqJson, headers);
		LogWriter.info(BlockChainServiceImpl.class, "区块链put请求地址：" + cfgValueHandler.getBlockChainUrl() + trCode + "/" + key);
		LogWriter.info(BlockChainServiceImpl.class, "区块链put请求参数：" + reqJson);
		String url = cfgValueHandler.getBlockChainUrl() + trCode;
		if (key != null) {
			url = url + "/" + key;
		}
		restTemplate.put(url, httpEntity);
	}

	/** 
	 * @Description: post
	 * @param trCode
	 * @param reqObj
	 * @param respCls
	 * @return
	 */ 
	public <T> T post(String trCode, Object reqObj, Class<T> respCls) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		String reqJson = reqObj.toString();
		HttpEntity<String> httpEntity = new HttpEntity<String>(reqJson, headers);
		LogWriter.info(BlockChainServiceImpl.class, "区块链post请求地址：" + cfgValueHandler.getBlockChainUrl() + trCode);
		LogWriter.info(BlockChainServiceImpl.class, "区块链post请求参数：" + reqJson);
		String result = restTemplate.postForObject(cfgValueHandler.getBlockChainUrl() + trCode, httpEntity,
				String.class);
		LogWriter.info(BlockChainServiceImpl.class, "区块链post响应：" + result);
		return jsonConvertor.toObject(result, respCls);
	}

	/** 
	 * @Description: delete
	 * @param trCode
	 * @param key
	 * @param filter
	 */ 
	public void delete(String trCode, String key, Object filter) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("filter", jsonConvertor.toJson(filter));

		LogWriter.info(BlockChainServiceImpl.class, "区块链delete请求地址：" + cfgValueHandler.getBlockChainUrl() + trCode + "/" + key);
		LogWriter.info(BlockChainServiceImpl.class, "区块链delete请求参数：" + jsonConvertor.toJson(uriVariables));
		String url = cfgValueHandler.getBlockChainUrl() + trCode;
		if (key != null) {
			url = url + "/" + key;
		}

		try {
			restTemplate.delete(url, uriVariables);
		} catch (RestClientException e) {
			LogWriter.info(BlockChainServiceImpl.class, "清理失败。" + url + " >< " + jsonConvertor.toJson(uriVariables));
			e.printStackTrace();
		}

	}

	/** 
	 * @Description: getList
	 * @param trCode
	 * @param key
	 * @param filter
	 * @param respCls
	 * @return
	 */ 
	public <T> List<T> getList(String trCode, String key, Object filter, Class<T> respCls) {
		String result = doGet(trCode, key, filter);
		return jsonConvertor.toObject(result, List.class, respCls);
	}

	public <T> T get(String trCode, String key, Object filter, Class<T> respCls) {
		String result = doGet(trCode, key, filter);
		return jsonConvertor.toObject(result, respCls);
	}

	/** 
	 * @Description: doGet
	 * @param trCode
	 * @param key
	 * @param filter
	 * @return String
	 */ 
	private String doGet(String trCode, String key, Object filter) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("filter", jsonConvertor.toJson(filter));
		LogWriter.info(BlockChainServiceImpl.class, "区块链get请求地址：" + cfgValueHandler.getBlockChainUrl() + trCode + "/" + key);
		LogWriter.info(BlockChainServiceImpl.class, "区块链get请求参数：" + jsonConvertor.toJson(uriVariables));
		String url = cfgValueHandler.getBlockChainUrl() + trCode;
		if (key != null) {
			url = url + "/" + key;
		}
		String result = null;
		try {
			result = restTemplate.getForObject(url, String.class, MapUtils.isEmpty(uriVariables) ? null : uriVariables);
		} catch (RestClientException e) {
			if (e.getMessage().contains("404 Not Found")) {
				result = null;
			} else {
				throw e;
			}
		}
		LogWriter.info(BlockChainServiceImpl.class, "区块链get响应：" + result);
		return result;
	}

}
