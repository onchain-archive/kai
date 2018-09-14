package com.abc.uan.hyperledger;

import java.util.List;

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
import com.abc.uan.blockchain.BankReserveAccountPojo;
import com.abc.uan.blockchain.TradePojo;

@RestController
@RequestMapping(value = AbstractController.REST_API_PREFIX + "/hyp")
public class HyperledgerRestApiControllor extends AbstractController {
	@Autowired
	private HyperledgerService hyperledgerService;

	@RequestMapping(value = "/getBankReserveAccounts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getBankReserveAccounts(@RequestBody String reqJson, HttpServletRequest req) {
		Request request = Request.create(reqJson, req, null);
		List<BankReserveAccountPojo> bankReserveAccounts = hyperledgerService.getBankReserveAccounts();
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(bankReserveAccounts, request);
	}

	@RequestMapping(value = "/getTrades", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getTrades(@RequestBody String reqJson, HttpServletRequest req) {
		Request request = Request.create(reqJson, req, null);
		List<TradePojo> tradePojos = hyperledgerService.getTrades();
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(tradePojos, request);
	}
}
