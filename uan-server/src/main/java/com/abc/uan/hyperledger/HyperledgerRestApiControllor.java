/**  
 * Title: HyperledgerRestApiControllor.java
 * Description: HyperledgerRestApiControllor
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.uan.hyperledger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abc.common.AbstractController;
import com.abc.common.Request;
import com.abc.common.Response;
import com.abc.common.util.ConvertUtils;
import com.abc.uan.blockchain.AgreementPojo;
import com.abc.uan.blockchain.BankReserveAccountPojo;
import com.abc.uan.blockchain.BlockChainServiceImpl;
import com.abc.uan.blockchain.TradePojo;

/**
 * Title: HyperledgerRestApiControllor
 * @Description: HyperledgerRestApiControllor
 * @author Bo Liu
 * @date 2018-09-20
 */
@RestController
@RequestMapping(value = AbstractController.REST_API_PREFIX + "/hyp")
public class HyperledgerRestApiControllor extends AbstractController {
	@Autowired
	private HyperledgerServiceImpl hyperledgerService;
	@Autowired
	private BlockChainServiceImpl blockChainService;

	/** 
	 * @Description: getBankReserveAccounts
	 * @param request
	 * @param req
	 * @return String
	 */ 
	@RequestMapping(value = "/getBankReserveAccounts", method = RequestMethod.GET)
	public String getBankReserveAccounts(Request request, HttpServletRequest req) {
		request = Request.create(null, request, req);
		List<BankReserveAccountPojo> bankReserveAccounts = hyperledgerService.getBankReserveAccounts();
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(bankReserveAccounts, request);
	}

	/** 
	 * @Description: getTrades
	 * @param request
	 * @param req
	 * @return
	 */ 
	@RequestMapping(value = "/getTrades", method = RequestMethod.GET)
	public String getTrades(Request request, HttpServletRequest req) {
		request = Request.create(null, request, req);
		List<TradePojo> tradePojos = hyperledgerService.getTrades();
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(tradePojos, request);
	}

	/** 
	 * @Description: getAgreements
	 * @param request
	 * @param req
	 * @return
	 */ 
	@RequestMapping(value = "/getAgreements", method = RequestMethod.GET)
	public String getAgreements(Request request, HttpServletRequest req) {
		request = Request.create(null, request, req);
		List<AgreementPojo> agreementPojos = hyperledgerService.getAgreements();
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(agreementPojos, request);
	}

	/** 
	 * @Description: clear
	 * @param request
	 * @param req
	 * @return String
	 */ 
	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public String clear(Request request, HttpServletRequest req) {
		request = Request.create(null, request, req);
		Map<String, String> filter = new HashMap<String, String>();
		filter.put("party", "110102200211112222");
		blockChainService.delete("Agreement", "110102200211112222", filter);
		filter = new HashMap<String, String>();
		filter.put("idCard", "110102200211112222");
		blockChainService.delete("Person", "110102200211112222", filter);

		filter = new HashMap<String, String>();
		filter.put("party", "110110200001011234");
		blockChainService.delete("Agreement", "110110200001011234", filter);
		filter = new HashMap<String, String>();
		filter.put("idCard", "110110200001011234");
		blockChainService.delete("Person", "110110200001011234", filter);
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(null, request);
	}

	/** 
	 * @Description: init
	 * @param request
	 * @param req
	 * @return String
	 */ 
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init(Request request, HttpServletRequest req) {
		request = Request.create(null, request, req);
		// 1.清理演示数据
		Map<String, String> filter = new HashMap<String, String>();
		filter.put("party", "110102200211112222");
		blockChainService.delete("Agreement", "110102200211112222", filter);
		filter = new HashMap<String, String>();
		filter.put("idCard", "110102200211112222");
		blockChainService.delete("Person", "110102200211112222", filter);

		filter = new HashMap<String, String>();
		filter.put("party", "110110200001011234");
		blockChainService.delete("Agreement", "110110200001011234", filter);
		filter = new HashMap<String, String>();
		filter.put("idCard", "110110200001011234");
		blockChainService.delete("Person", "110110200001011234", filter);
		Response<List> resp = new Response<List>();

		// 2.补充区块链银行准备金数据
		// 初始化区块链上的银行准备金账户
		filter = new HashMap<String, String>();
		filter.put("bank", "Agricultural-Bank-of-China");
		BankReserveAccountPojo bankReserveAccountPojo = blockChainService.get("BankReserveAccount",
				"Agricultural-Bank-of-China", filter, BankReserveAccountPojo.class);
		if (bankReserveAccountPojo == null) {
			bankReserveAccountPojo = new BankReserveAccountPojo();
			bankReserveAccountPojo.setBalance(5555555d);
			bankReserveAccountPojo.setDebit("5555555");
			bankReserveAccountPojo.setDebitBank("Agricultural-Bank-of-China");
			bankReserveAccountPojo.setTrTime(ConvertUtils.timeToString(new Date()));
			bankReserveAccountPojo.setTrType("init");
			bankReserveAccountPojo.setBank("Agricultural-Bank-of-China");
			blockChainService.post("BankReserveAccount", bankReserveAccountPojo, BankReserveAccountPojo.class);
		}

		filter = new HashMap<String, String>();
		filter.put("bank", "China-Construction-Bank");
		bankReserveAccountPojo = blockChainService.get("BankReserveAccount", "China-Construction-Bank", filter,
				BankReserveAccountPojo.class);
		if (bankReserveAccountPojo == null) {
			bankReserveAccountPojo = new BankReserveAccountPojo();
			bankReserveAccountPojo.setBalance(8888888d);
			bankReserveAccountPojo.setDebit("8888888");
			bankReserveAccountPojo.setDebitBank("China-Construction-Bank");
			bankReserveAccountPojo.setTrTime(ConvertUtils.timeToString(new Date()));
			bankReserveAccountPojo.setTrType("init");
			bankReserveAccountPojo.setBank("China-Construction-Bank");
			blockChainService.post("BankReserveAccount", bankReserveAccountPojo, BankReserveAccountPojo.class);
		}

		filter = new HashMap<String, String>();
		filter.put("bank", "Bank-of-China");
		bankReserveAccountPojo = blockChainService.get("BankReserveAccount", "Bank-of-China", filter,
				BankReserveAccountPojo.class);
		if (bankReserveAccountPojo == null) {
			bankReserveAccountPojo = new BankReserveAccountPojo();
			bankReserveAccountPojo.setBalance(6666666d);
			bankReserveAccountPojo.setDebit("6666666");
			bankReserveAccountPojo.setDebitBank("Bank-of-China");
			bankReserveAccountPojo.setTrTime(ConvertUtils.timeToString(new Date()));
			bankReserveAccountPojo.setTrType("init");
			bankReserveAccountPojo.setBank("Bank-of-China");
			blockChainService.post("BankReserveAccount", bankReserveAccountPojo, BankReserveAccountPojo.class);
		}

		filter = new HashMap<String, String>();
		filter.put("bank", "Industrial-and-Commercial-Bank-of-China");
		bankReserveAccountPojo = blockChainService.get("BankReserveAccount", "Industrial-and-Commercial-Bank-of-China",
				filter, BankReserveAccountPojo.class);
		if (bankReserveAccountPojo == null) {
			bankReserveAccountPojo = new BankReserveAccountPojo();
			bankReserveAccountPojo.setBalance(3333333d);
			bankReserveAccountPojo.setDebit("3333333");
			bankReserveAccountPojo.setDebitBank("Industrial-and-Commercial-Bank-of-China");
			bankReserveAccountPojo.setTrTime(ConvertUtils.timeToString(new Date()));
			bankReserveAccountPojo.setTrType("init");
			bankReserveAccountPojo.setBank("Industrial-and-Commercial-Bank-of-China");
			blockChainService.post("BankReserveAccount", bankReserveAccountPojo, BankReserveAccountPojo.class);
		}

		filter = new HashMap<String, String>();
		filter.put("bank", "Bank-of-America");
		bankReserveAccountPojo = blockChainService.get("BankReserveAccount", "Bank-of-America", filter,
				BankReserveAccountPojo.class);
		if (bankReserveAccountPojo == null) {
			bankReserveAccountPojo = new BankReserveAccountPojo();
			bankReserveAccountPojo.setBalance(3333333d);
			bankReserveAccountPojo.setDebit("4444444");
			bankReserveAccountPojo.setDebitBank("Bank-of-America");
			bankReserveAccountPojo.setTrTime(ConvertUtils.timeToString(new Date()));
			bankReserveAccountPojo.setTrType("init");
			bankReserveAccountPojo.setBank("Bank-of-America");
			blockChainService.post("BankReserveAccount", bankReserveAccountPojo, BankReserveAccountPojo.class);
		}
		return resp.createSuccessJson(null, request);
	}

}
