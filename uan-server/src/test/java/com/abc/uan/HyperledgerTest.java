package com.abc.uan;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abc.common.Request;
import com.abc.common.Response;
import com.abc.common.starter.UanApplication;
import com.abc.common.util.JsonConvertor;
import com.abc.uan.blockchain.BankReserveAccountPojo;
import com.abc.uan.blockchain.TradePojo;
import com.abc.uan.catastrophe.CatastrophePojo;
import com.abc.uan.hyperledger.HyperledgerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UanApplication.class)
public class HyperledgerTest {

	@Autowired
	private JsonConvertor jsonConvertor;
	@Autowired
	private HyperledgerService hyperledgerService;

	@Test
	public void test() throws ParseException {
		List<BankReserveAccountPojo> bankReserveAccountPojos = hyperledgerService.getBankReserveAccounts();
		System.out.println();
		System.out.println("全部准备金账户信息：");
		System.out.println(bankReserveAccountPojos);
		System.out.println();

		List<TradePojo> tradePojos = hyperledgerService.getTrades();
		System.out.println();
		System.out.println("全部交易信息：");
		System.out.println(tradePojos);
		System.out.println();

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		System.out.println();
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		System.out.println();

		Request req = new Request();
		req.setAppId("uan");
		req.setUserId("110110200001011234");
		String reqJson = jsonConvertor.toJson(req);
		System.out.println();
		System.out.println("getBankReserveAccounts请求报文：");
		System.out.println(reqJson);
		System.out.println();

		Response resp = new Response();
		resp.createSuccessJson(bankReserveAccountPojos, req);
		String respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("getBankReserveAccounts应答报文：");
		System.out.println(respJson);
		System.out.println();

		req = new Request();
		req.setAppId("uan");
		req.setUserId("110110200001011234");
		reqJson = jsonConvertor.toJson(req);
		System.out.println();
		System.out.println("getTrades请求报文：");
		System.out.println(reqJson);
		System.out.println();

		resp = new Response();
		resp.createSuccessJson(tradePojos, req);
		respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("getTrades应答报文：");
		System.out.println(respJson);
		System.out.println();
	}

}
