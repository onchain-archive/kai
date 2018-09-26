package com.abc.bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abc.bank.bankcard.BankCardPojo;
import com.abc.bank.bankcard.BankCardService;
import com.abc.bank.customerinformation.CustomerInformationPojo;
import com.abc.bank.customerinformation.CustomerInformationService;
import com.abc.bank.customerinformation.FaceIDPojo;
import com.abc.bank.customerinformation.InquireAssetsCommondPojo;
import com.abc.bank.customerinformation.ReportLossCommondPojo;
import com.abc.bank.customerinformation.UanTrResultPojo;
import com.abc.bank.customerinformation.WithdrawCommondPojo;
import com.abc.bank.uancontract.PersonnelRelationshipPojo;
import com.abc.bank.uancontract.UanContractBCDAO;
import com.abc.bank.uancontract.UanContractPojo;
import com.abc.bank.uancontract.UanContractService;
import com.abc.common.Request;
import com.abc.common.Response;
import com.abc.common.bus.RequestBus;
import com.abc.common.bus.ThreadContext;
import com.abc.common.starter.UanApplication;
import com.abc.common.util.ConvertUtils;
import com.abc.common.util.JsonConvertor;
import com.abc.uan.blockchain.AgreementPojo;
import com.abc.uan.blockchain.BankReserveAccountPojo;
import com.abc.uan.blockchain.BlockChainService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UanApplication.class)
public class BankSericeTest {

	@Autowired
	private UanContractService uanContractService;
	@Autowired
	private BankCardService bankCardService;
	@Autowired
	private UanContractBCDAO uanContractBCDAO;
	@Autowired
	private JsonConvertor jsonConvertor;
	@Autowired
	private CustomerInformationService customerInformationService;
	@Autowired
	private BlockChainService blockChainService;

	@Test
	public void clear() {
		Map<String, String> filter = new HashMap<String, String>();
		filter.put("party", "110102200211112222");
		blockChainService.delete("Agreement", "110102200211112222", filter);
		filter = new HashMap<String, String>();
		filter.put("idCard", "110102200211112222");
		blockChainService.delete("Person", "110102200211112222", filter);
	}

	@Test
	public void testUanContract() {
		// try {
		uanContractBCDAO.delete("110110200001011234");
		Map<String, String> filter = new HashMap<String, String>();
		filter.put("party", "110110200001011234");
		blockChainService.delete("Agreement", "110110200001011234", filter);
		filter = new HashMap<String, String>();
		filter.put("idCard", "110110200001011234");
		blockChainService.delete("Person", "110110200001011234", filter);

		UanContractPojo uanContractPojo = new UanContractPojo();
		// 1、签阅合同
		String hetong = uanContractService.generate();
		System.out.println();
		System.out.println("合同正文：");
		System.out.println(hetong);
		System.out.println();
		uanContractPojo.setAgreeWithUan(true);
		uanContractPojo.setCustomerInformation(new CustomerInformationPojo());
		uanContractPojo.getCustomerInformation().setIdCard("110110200001011234");
		uanContractPojo.getCustomerInformation().setName("张三");

		// 2、添加关系人
		PersonnelRelationshipPojo p1 = new PersonnelRelationshipPojo();
		p1.setOrder(1);
		p1.setPhone("13800001111");
		p1.setRelationship("子");
		p1.setSlaveId("220220200001011234");
		p1.setSlaveName("张儿子");
		PersonnelRelationshipPojo p2 = new PersonnelRelationshipPojo();
		p2.setOrder(2);
		p2.setPhone("13800002222");
		p2.setRelationship("女");
		p2.setSlaveId("330330200001011234");
		p2.setSlaveName("张女儿");
		PersonnelRelationshipPojo p3 = new PersonnelRelationshipPojo();
		p3.setOrder(3);
		p3.setPhone("13800003333");
		p3.setRelationship("妻");
		p3.setSlaveId("440440200001011234");
		p3.setSlaveName("张老婆");
		PersonnelRelationshipPojo p4 = new PersonnelRelationshipPojo();
		p4.setOrder(4);
		p4.setPhone("13800004444");
		p4.setRelationship("父");
		p4.setSlaveId("550550200001011234");
		p4.setSlaveName("张父亲");
		PersonnelRelationshipPojo p5 = new PersonnelRelationshipPojo();
		p5.setOrder(5);
		p5.setPhone("13800005555");
		p5.setRelationship("母");
		p5.setSlaveId("660660200001011234");
		p5.setSlaveName("张母亲");

		uanContractPojo.setPersonnelRelationships(new ArrayList<PersonnelRelationshipPojo>());
		uanContractPojo.getPersonnelRelationships().add(p1);
		uanContractPojo.getPersonnelRelationships().add(p2);
		uanContractPojo.getPersonnelRelationships().add(p3);
		uanContractPojo.getPersonnelRelationships().add(p4);
		uanContractPojo.getPersonnelRelationships().add(p5);
		System.out.println();
		System.out.println("互助人列表：");
		System.out.println(uanContractPojo.getPersonnelRelationships());
		System.out.println();

		// 3.1、查询本行账号
		List<BankCardPojo> bankCards = bankCardService.findMyCard();
		System.out.println();
		System.out.println("本行卡列表：");
		System.out.println(bankCards);
		System.out.println();

		// 3.2、输入他行账号
		BankCardPojo b1 = new BankCardPojo();
		b1.setAmt(new BigDecimal(5000));
		b1.setBankOfDeposit("China Construction Bank");
		b1.setCode("622812345678999");
		b1.setIdCard("110110200001011234");
		b1.setState(BankCardPojo.STATE_NORMAL);
		BankCardPojo b2 = new BankCardPojo();
		b2.setAmt(new BigDecimal(123456));
		b2.setBankOfDeposit("China Construction Bank");
		b2.setCode("622811111111999");
		b2.setIdCard("110110200001011234");
		b2.setState(BankCardPojo.STATE_NORMAL);
		bankCards.add(b1);
		bankCards.add(b2);
		uanContractPojo.setBindingCards(bankCards);
		System.out.println();
		System.out.println("全部卡列表：");
		System.out.println(uanContractPojo.getBindingCards());
		System.out.println();

		// 4、完善合同并提交
		uanContractPojo.setState(UanContractPojo.STATE_SIGNED);
		uanContractPojo.setTransactBank("Agricultural Bank of China");
		uanContractPojo.setTransactDate(new Date());
		UanContractPojo res = uanContractService.contract(uanContractPojo);
		System.out.println();
		System.out.println("合约成功：");
		System.out.println(res);
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
		System.out.println("generate请求报文：");
		System.out.println(reqJson);
		System.out.println();
		// 使用上面的hetong
		Response resp = new Response();
		resp.createSuccessJson(hetong, req);
		String respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("generate应答报文：");
		System.out.println(respJson);
		System.out.println();

		System.out.println();
		System.out.println("findMyCard请求报文：");
		System.out.println(reqJson);
		System.out.println();
		// 使用上面的bankCards
		resp = new Response();
		resp.createSuccessJson(bankCards, req);
		respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("findMyCard应答报文：");
		System.out.println(respJson);
		System.out.println();

		req.setData(uanContractPojo);
		reqJson = jsonConvertor.toJson(req);
		System.out.println();
		System.out.println("contract请求报文：");
		System.out.println(reqJson);
		System.out.println();
		// 使用上面的res
		resp = new Response();
		resp.createSuccessJson(res, req);
		respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("contract应答报文：");
		System.out.println(respJson);
		System.out.println();
		// } catch (Exception e) {
		// e.printStackTrace();
		// Assert.fail(e.getMessage());
		// }
	}

	@Before
	public void login() {
		RequestBus bus = new RequestBus("uan-server");
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put(RequestBus.USER, "110110200001011234");
		bus.setAttributes(attrs);
		ThreadContext.getContext().setAttribute(RequestBus.REQUEST_BUS, bus);

		// 初始化区块链上的银行准备金账户
		Map<String, Object> filter = new HashMap<String, Object>();
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

		filter = new HashMap<String, Object>();
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

		filter = new HashMap<String, Object>();
		filter.put("bank", "Bank of China");
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

		filter = new HashMap<String, Object>();
		filter.put("bank", "Industrial and Commercial Bank of China");
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

		filter = new HashMap<String, Object>();
		filter.put("bank", "Bank of America");
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
	}

	@Test
	public void testWithdraw() {
		// 1.扫脸
		FaceIDPojo faceIDPojo = new FaceIDPojo();
		faceIDPojo.setIdCard("330330200001011234");
		CustomerInformationPojo ci = customerInformationService.faceID(faceIDPojo);
		System.out.println();
		System.out.println("人脸登录：");
		System.out.println(ci.toString());
		System.out.println();

		// 2.输入100并取钱
		WithdrawCommondPojo withdrawCommond = new WithdrawCommondPojo();
		withdrawCommond.setAmt(100D);
		withdrawCommond.setDeviceNum("ATM模拟器");
		withdrawCommond.setTrDate(new Date());
		withdrawCommond.setParty("330330200001011234");
		UanTrResultPojo uanTrResultPojo = customerInformationService.withdraw(withdrawCommond);
		System.out.println();
		System.out.println("取款结果：");
		System.out.println(uanTrResultPojo.toString());
		System.out.println();

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		System.out.println();
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		System.out.println();

		Request req = new Request();
		req.setAppId("uan");
		req.setUserId("");
		req.setData(faceIDPojo);
		String reqJson = jsonConvertor.toJson(req);
		System.out.println();
		System.out.println("faceID请求报文：");
		System.out.println(reqJson);
		System.out.println();
		Response resp = new Response();
		resp.createSuccessJson(ci, req);
		String respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("faceID应答报文：");
		System.out.println(respJson);
		System.out.println();

		req = new Request();
		req.setAppId("uan");
		req.setUserId("330330200001011234");
		req.setData(withdrawCommond);
		reqJson = jsonConvertor.toJson(req);
		System.out.println();
		System.out.println("withdraw请求报文：");
		System.out.println(reqJson);
		System.out.println();
		resp = new Response();
		resp.createSuccessJson(uanTrResultPojo, req);
		respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("withdraw应答报文：");
		System.out.println(respJson);
		System.out.println();
	}

	@Test
	public void testReportLoss() {
		// 1.扫脸
		FaceIDPojo faceIDPojo = new FaceIDPojo();
		faceIDPojo.setIdCard("330330200001011234");
		CustomerInformationPojo ci = customerInformationService.faceID(faceIDPojo);
		System.out.println();
		System.out.println("人脸登录：");
		System.out.println(ci.toString());
		System.out.println();

		// 2.获取关系人
		List<PersonnelRelationshipPojo> personnelRelationshipPojos = uanContractService
				.findMasterPojos("330330200001011234");
		System.out.println();
		System.out.println("获取联系人：");
		System.out.println(personnelRelationshipPojos.toString());
		System.out.println();

		// 3.取账号

		List<BankCardPojo> bankCardPojos = uanContractService
				.findBindingCards(personnelRelationshipPojos.get(0).getMasterId());
		System.out.println();
		System.out.println("取账号：");
		System.out.println(bankCardPojos.toString());
		System.out.println();

		// 4.挂失
		ReportLossCommondPojo reportLossCommondPojo = new ReportLossCommondPojo();
		reportLossCommondPojo.setBankOfDeposit(bankCardPojos.get(0).getBankOfDeposit());
		reportLossCommondPojo.setCode(bankCardPojos.get(0).getCode());
		reportLossCommondPojo.setDeviceNum("ATM模拟器");
		reportLossCommondPojo.setIdCard(bankCardPojos.get(0).getIdCard());
		reportLossCommondPojo.setParty("330330200001011234");
		reportLossCommondPojo.setTrDate(new Date());
		UanTrResultPojo res = customerInformationService.reportLoss(reportLossCommondPojo);
		System.out.println();
		System.out.println("挂失：");
		System.out.println(res);
		System.out.println();

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		System.out.println();
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		System.out.println();

		Request req = new Request();
		req.setAppId("uan");
		req.setUserId("");
		req.setData(faceIDPojo);
		String reqJson = jsonConvertor.toJson(req);
		System.out.println();
		System.out.println("faceID请求报文：");
		System.out.println(reqJson);
		System.out.println();
		Response resp = new Response();
		resp.createSuccessJson(ci, req);
		String respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("faceID应答报文：");
		System.out.println(respJson);
		System.out.println();

		req = new Request();
		req.setAppId("uan");
		req.setUserId("330330200001011234");
		req.setData("330330200001011234");
		reqJson = jsonConvertor.toJson(req);
		System.out.println();
		System.out.println("findMasters请求报文：");
		System.out.println(reqJson);
		System.out.println();
		resp = new Response();
		resp.createSuccessJson(personnelRelationshipPojos, req);
		respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("findMasters应答报文：");
		System.out.println(respJson);
		System.out.println();

		req = new Request();
		req.setAppId("uan");
		req.setUserId("330330200001011234");
		req.setData(personnelRelationshipPojos.get(0).getMasterId());
		reqJson = jsonConvertor.toJson(req);
		System.out.println();
		System.out.println("findByIdCard请求报文：");
		System.out.println(reqJson);
		System.out.println();
		resp = new Response();
		resp.createSuccessJson(bankCardPojos, req);
		respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("findByIdCard应答报文：");
		System.out.println(respJson);
		System.out.println();

		req = new Request();
		req.setAppId("uan");
		req.setUserId("330330200001011234");
		req.setData(reportLossCommondPojo);
		reqJson = jsonConvertor.toJson(req);
		System.out.println();
		System.out.println("reportLoss请求报文：");
		System.out.println(reqJson);
		System.out.println();
		resp = new Response();
		resp.createSuccessJson(res, req);
		respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("reportLoss应答报文：");
		System.out.println(respJson);
		System.out.println();

	}

	@Test
	public void testInquireAssets() {
		// 1.扫脸
		FaceIDPojo faceIDPojo = new FaceIDPojo();
		faceIDPojo.setIdCard("330330200001011234");
		CustomerInformationPojo ci = customerInformationService.faceID(faceIDPojo);
		System.out.println();
		System.out.println("人脸登录：");
		System.out.println(ci.toString());
		System.out.println();

		// 2.获取关系人
		List<PersonnelRelationshipPojo> personnelRelationshipPojos = uanContractService
				.findMasterPojos("330330200001011234");
		System.out.println();
		System.out.println("获取联系人：");
		System.out.println(personnelRelationshipPojos.toString());
		System.out.println();

		// 3.查资产
		InquireAssetsCommondPojo inquireAssetsCommondPojo = new InquireAssetsCommondPojo();
		inquireAssetsCommondPojo.setCustomer(personnelRelationshipPojos.get(0).getMasterId());
		inquireAssetsCommondPojo.setDeviceNum("ATM模拟器");
		inquireAssetsCommondPojo.setParty("330330200001011234");
		inquireAssetsCommondPojo.setTrDate(new Date());
		List<BankCardPojo> bankCardPojos = customerInformationService.inquireAssets(inquireAssetsCommondPojo);
		System.out.println();
		System.out.println("资产明细：");
		System.out.println(bankCardPojos.toString());
		System.out.println();

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		System.out.println();
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		System.out.println();

		Request req = new Request();
		req.setAppId("uan");
		req.setUserId("");
		req.setData(faceIDPojo);
		String reqJson = jsonConvertor.toJson(req);
		System.out.println();
		System.out.println("faceID请求报文：");
		System.out.println(reqJson);
		System.out.println();
		Response resp = new Response();
		resp.createSuccessJson(ci, req);
		String respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("faceID应答报文：");
		System.out.println(respJson);
		System.out.println();

		req = new Request();
		req.setAppId("uan");
		req.setUserId("330330200001011234");
		req.setData("330330200001011234");
		reqJson = jsonConvertor.toJson(req);
		System.out.println();
		System.out.println("findMasters请求报文：");
		System.out.println(reqJson);
		System.out.println();
		resp = new Response();
		resp.createSuccessJson(personnelRelationshipPojos, req);
		respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("findMasters应答报文：");
		System.out.println(respJson);
		System.out.println();

		req = new Request();
		req.setAppId("uan");
		req.setUserId("330330200001011234");
		req.setData(inquireAssetsCommondPojo);
		reqJson = jsonConvertor.toJson(req);
		System.out.println();
		System.out.println("inquireAssets请求报文：");
		System.out.println(reqJson);
		System.out.println();
		resp = new Response();
		resp.createSuccessJson(bankCardPojos, req);
		respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("inquireAssets应答报文：");
		System.out.println(respJson);
		System.out.println();

	}
}
