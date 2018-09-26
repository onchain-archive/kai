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
public class BlockChainService implements IService {

	@Autowired
	private JsonConvertor jsonConvertor;
	@Autowired
	private CfgValueHandler cfgValueHandler;

	public void put(String trCode, String key, Object reqObj) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		String reqJson = reqObj.toString();
		HttpEntity<String> httpEntity = new HttpEntity<String>(reqJson, headers);
		LogWriter.info(BlockChainService.class, "区块链put请求地址：" + cfgValueHandler.getBlockChainUrl() + trCode + "/" + key);
		LogWriter.info(BlockChainService.class, "区块链put请求参数：" + reqJson);
		String url = cfgValueHandler.getBlockChainUrl() + trCode;
		if (key != null) {
			url = url + "/" + key;
		}
		restTemplate.put(url, httpEntity);
	}

	public <T> T post(String trCode, Object reqObj, Class<T> respCls) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		String reqJson = reqObj.toString();
		HttpEntity<String> httpEntity = new HttpEntity<String>(reqJson, headers);
		LogWriter.info(BlockChainService.class, "区块链post请求地址：" + cfgValueHandler.getBlockChainUrl() + trCode);
		LogWriter.info(BlockChainService.class, "区块链post请求参数：" + reqJson);
		String result = restTemplate.postForObject(cfgValueHandler.getBlockChainUrl() + trCode, httpEntity,
				String.class);
		LogWriter.info(BlockChainService.class, "区块链post响应：" + result);
		return jsonConvertor.toObject(result, respCls);
	}

	public void delete(String trCode, String key, Object filter) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("filter", jsonConvertor.toJson(filter));

		LogWriter.info(BlockChainService.class, "区块链delete请求地址：" + cfgValueHandler.getBlockChainUrl() + trCode + "/" + key);
		LogWriter.info(BlockChainService.class, "区块链delete请求参数：" + jsonConvertor.toJson(uriVariables));
		String url = cfgValueHandler.getBlockChainUrl() + trCode;
		if (key != null) {
			url = url + "/" + key;
		}

		try {
			restTemplate.delete(url, uriVariables);
		} catch (RestClientException e) {
			LogWriter.info(BlockChainService.class, "清理失败。" + url + " >< " + jsonConvertor.toJson(uriVariables));
			e.printStackTrace();
		}

	}

	// public <T> T get(String trCode, String key, Object filter, Class<T> respCls)
	// {
	// return get(trCode, key, null, filter, respCls); String conditionType,
	// }

	public <T> List<T> getList(String trCode, String key, Object filter, Class<T> respCls) {
		String result = doGet(trCode, key, filter);
		return jsonConvertor.toObject(result, List.class, respCls);
	}

	public <T> T get(String trCode, String key, Object filter, Class<T> respCls) {
		String result = doGet(trCode, key, filter);
		return jsonConvertor.toObject(result, respCls);
	}

	private String doGet(String trCode, String key, Object filter) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("filter", jsonConvertor.toJson(filter));
		LogWriter.info(BlockChainService.class, "区块链get请求地址：" + cfgValueHandler.getBlockChainUrl() + trCode + "/" + key);
		LogWriter.info(BlockChainService.class, "区块链get请求参数：" + jsonConvertor.toJson(uriVariables));
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
		LogWriter.info(BlockChainService.class, "区块链get响应：" + result);
		return result;
	}

}
