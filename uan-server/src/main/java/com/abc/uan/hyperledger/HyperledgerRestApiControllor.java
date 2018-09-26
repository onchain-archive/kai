package com.abc.uan.hyperledger;

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
import com.abc.uan.blockchain.AgreementPojo;
import com.abc.uan.blockchain.BankReserveAccountPojo;
import com.abc.uan.blockchain.BlockChainService;
import com.abc.uan.blockchain.TradePojo;

@RestController
@RequestMapping(value = AbstractController.REST_API_PREFIX + "/hyp")
public class HyperledgerRestApiControllor extends AbstractController {
	@Autowired
	private HyperledgerService hyperledgerService;
	@Autowired
	private BlockChainService blockChainService;

	@RequestMapping(value = "/getBankReserveAccounts", method = RequestMethod.GET)
	public String getBankReserveAccounts(Request request, HttpServletRequest req) {
		request = Request.create(null, request, req);
		List<BankReserveAccountPojo> bankReserveAccounts = hyperledgerService.getBankReserveAccounts();
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(bankReserveAccounts, request);
	}

	@RequestMapping(value = "/getTrades", method = RequestMethod.GET)
	public String getTrades(Request request, HttpServletRequest req) {
		request = Request.create(null, request, req);
		List<TradePojo> tradePojos = hyperledgerService.getTrades();
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(tradePojos, request);
	}

	@RequestMapping(value = "/getAgreements", method = RequestMethod.GET)
	public String getAgreements(Request request, HttpServletRequest req) {
		request = Request.create(null, request, req);
		List<AgreementPojo> agreementPojos = hyperledgerService.getAgreements();
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(agreementPojos, request);
	}
	
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

	// @RequestMapping(value = "/getBankReserveAccounts", method =
	// RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// public String getBankReserveAccounts(@RequestBody String reqJson,
	// HttpServletRequest req) {
	// Request request = Request.create(reqJson, req, null);
	// List<BankReserveAccountPojo> bankReserveAccounts =
	// hyperledgerService.getBankReserveAccounts();
	// Response<List> resp = new Response<List>();
	// return resp.createSuccessJson(bankReserveAccounts, request);
	// }
	//
	// @RequestMapping(value = "/getTrades", method = RequestMethod.POST, produces =
	// MediaType.APPLICATION_JSON_UTF8_VALUE)
	// public String getTrades(@RequestBody String reqJson, HttpServletRequest req)
	// {
	// Request request = Request.create(reqJson, req, null);
	// List<TradePojo> tradePojos = hyperledgerService.getTrades();
	// Response<List> resp = new Response<List>();
	// return resp.createSuccessJson(tradePojos, request);
	// }
}
