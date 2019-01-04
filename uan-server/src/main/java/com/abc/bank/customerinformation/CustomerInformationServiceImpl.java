/*
 * Copyright 2018 Liu Bo
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.abc.bank.customerinformation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.thymeleaf.expression.Aggregates;

import com.abc.bank.bankcard.BankCardDAO;
import com.abc.bank.bankcard.BankCardPojo;
import com.abc.bank.bankcard.BankCardServiceImpl;
import com.abc.bank.uancontract.PersonnelRelationshipPojo;
import com.abc.bank.uancontract.UanContractPojo;
import com.abc.bank.uancontract.UanContractServiceImpl;
import com.abc.common.IService;
import com.abc.common.UanException;
import com.abc.common.util.ConvertUtils;
import com.abc.common.util.JsonConvertor;
import com.abc.common.util.UUIDNumberGenerator;
import com.abc.common.util.XMLConvertor;
import com.abc.gov.residence.RegisteredResidencePojo;
import com.abc.uan.blockchain.AgreementPojo;
import com.abc.uan.blockchain.BankReserveAccountPojo;
import com.abc.uan.blockchain.BlockChainServiceImpl;
import com.abc.uan.blockchain.CardAccountPojo;
import com.abc.uan.blockchain.PersonPojo;
import com.abc.uan.blockchain.TradePojo;

/**
 * Title: CustomerInformationService
 * @Description: CustomerInformationService
 * @author Bo Liu
 * @date 2018-09-20
*/
@Component
public class CustomerInformationServiceImpl implements IService {

	private final String OWNER = "Agriculture Bank of China";

	@Autowired
	private CustomerInformationDAO customerInformationDAO;
	@Autowired
	private UanContractServiceImpl uanContractService;
	@Autowired
	private BankCardServiceImpl bankCardService;
	@Autowired
	private UUIDNumberGenerator uuidNumberGenerator;
	@Autowired
	private JsonConvertor jsonConvertor;
	@Autowired
	private XMLConvertor xmlConvertor;
	@Autowired
	private BlockChainServiceImpl blockChainService;

	/** 
	 * @Description: findSuccessionRelations
	 * @param customerInformationPojo
	 * @return  RegisteredResidencePojo
	 * @throws 
	 */ 
	public RegisteredResidencePojo findSuccessionRelations(CustomerInformationPojo customerInformationPojo) {
		// TODO
		return null;
	}

	/** 
	 * @Description: confirmCustomer
	 * @param idCard
	 * @return  CustomerInformationPojo
	 * @throws UanException
	 */ 
	public CustomerInformationPojo confirmCustomer(String idCard) {
		CustomerInformationPojo customer = customerInformationDAO.loadByIdCard(idCard);
		if (customer == null) {
			throw new UanException("非本行客户，不可办理UAN业务。");
		} else {
			return customer;
		}
	}

	/** 
	 * @Description: login
	 * @param customerInformationPojo 
	 * @throws 
	 */ 
	public void login(CustomerInformationPojo customerInformationPojo) {
		checkPwd(customerInformationPojo);

	}

	/** 
	 * @Description: checkPwd
	 * @param customerInformationPojo  void
	 * @throws UanException
	 */ 
	public void checkPwd(CustomerInformationPojo customerInformationPojo) {
		String dbPwd = customerInformationDAO.checkPwd(customerInformationPojo.getIdCard());
		String inputPwd = md5(customerInformationPojo.getPwd());
		if (!dbPwd.equals(inputPwd)) {
			throw new UanException("用户或密码错误，请重新输入");
		}

	}

	/** 
	 * @Description: md5
	 * @param text
	 * @return  String
	 * @throws UanException
	 */ 
	private String md5(String text) {
		String encodeStr = DigestUtils.md5DigestAsHex((text + "uan").getBytes());
		return encodeStr;
	}

	public CustomerInformationPojo faceID(FaceIDPojo faceIDPojo) {

		CustomerInformationPojo customerInformationPojo = null;
		if (ArrayUtils.isEmpty(uanContractService.findMasters(faceIDPojo.getIdCard()))) {
			throw new UanException("未签约客户");
		}

		try {
			customerInformationPojo = confirmCustomer(faceIDPojo.getIdCard());
			// TODO 人脸身份认证
		} catch (UanException e) {
			Map<String, String> filter = new HashMap<String, String>();
			filter.put("idCard", faceIDPojo.getIdCard());
			PersonPojo personPojo = blockChainService.get("Person", faceIDPojo.getIdCard(), filter, PersonPojo.class);
			// TODO 人脸身份认证
			customerInformationPojo = new CustomerInformationPojo();
			customerInformationPojo.setIdCard(personPojo.getIdCard());
			customerInformationPojo.setName(personPojo.getName());

		}

		return customerInformationPojo;
	}

	/** 
	 * @Description: withdraw
	 * @param withdrawCommondPojo
	 * @return  UanTrResultPojo
	 * @throws 
	 */ 
	public UanTrResultPojo withdraw(WithdrawCommondPojo withdrawCommondPojo) {
		UanTrResultPojo uanTrResultPojo = null;
		// 按照顺序扣款
		String[] masters = uanContractService.findMasters(withdrawCommondPojo.getParty());
		for (String m : masters) {
			List<BankCardPojo> bindingCards = uanContractService.findBindingCards(m);
			for (BankCardPojo c : bindingCards) {
				if (c.getState().equals(BankCardPojo.STATE_NORMAL)) {
					if (OWNER.equals(c.getBankOfDeposit())) {
						bankCardService.withdraw(c.getCode(), withdrawCommondPojo.getAmt());
						uanTrResultPojo = new UanTrResultPojo();
						uanTrResultPojo.setInfo("取款金額：" + withdrawCommondPojo.getAmt());
						uanTrResultPojo.setCardNum(c.getCode());
						uanTrResultPojo.setMasterName(c.getCustomerInformation().getName());
						TradePojo tradesPojo = new TradePojo();
						tradesPojo.setAmt(withdrawCommondPojo.getAmt());
						tradesPojo.setCardNum(c.getCode());
						tradesPojo.setCustomer(c.getIdCard());
						tradesPojo.setParty(withdrawCommondPojo.getParty());
						tradesPojo.setSerialNumber("abc-withdraw-" + uuidNumberGenerator.generate());
						tradesPojo.setTrDetail(uanTrResultPojo.toString());
						tradesPojo.setTrTime(ConvertUtils.timeToString(new Date()));
						tradesPojo.setTrType("debit");
						blockChainService.post("Trade", tradesPojo, TradePojo.class);
						break;
					} else {
						BigDecimal bd = ConvertUtils
								.stringToBigDecimal(ConvertUtils.doubleToString(withdrawCommondPojo.getAmt()));
						if (c.getAmt().compareTo(bd) >= 0) {
							TradePojo tradesPojo = new TradePojo();
							tradesPojo.setAmt(withdrawCommondPojo.getAmt());
							tradesPojo.setCardNum(c.getCode());
							tradesPojo.setCustomer(c.getIdCard());
							tradesPojo.setParty(withdrawCommondPojo.getParty());
							tradesPojo.setSerialNumber("abc-withdraw-" + uuidNumberGenerator.generate());
							uanTrResultPojo = new UanTrResultPojo();
							uanTrResultPojo.setCardNum(c.getCode());
							uanTrResultPojo.setMasterName(c.getIdCard());
							uanTrResultPojo.setInfo("取款金額：" + withdrawCommondPojo.getAmt() + "；从" + OWNER + "跨行支取"
									+ c.getBankOfDeposit() + "账号。");
							tradesPojo.setTrDetail(uanTrResultPojo.toString());
							tradesPojo.setTrTime(ConvertUtils.timeToString(new Date()));
							tradesPojo.setTrType("debit");
							blockChainService.post("Trade", tradesPojo, TradePojo.class);
							Map<String, Object> filter = new HashMap<String, Object>();
							filter.put("bank", c.getBankOfDeposit());
							BankReserveAccountPojo bankReserveAccountPojo = blockChainService.get("BankReserveAccount",
									c.getBankOfDeposit(), filter, BankReserveAccountPojo.class);
							bankReserveAccountPojo.setSerialNumber(uuidNumberGenerator.generate());
							bankReserveAccountPojo.setBalance(
									ConvertUtils.stringToDoubleObject(ConvertUtils.bigDecimalToString(ConvertUtils
											.stringToBigDecimal(
													ConvertUtils.doubleToString(bankReserveAccountPojo.getBalance()))
											.subtract(bd))));
							bankReserveAccountPojo.setCredit(ConvertUtils.doubleToString(withdrawCommondPojo.getAmt()));
							bankReserveAccountPojo.setCreditBank(OWNER);
							bankReserveAccountPojo.setDebit("None");
							bankReserveAccountPojo.setDebitBank(c.getBankOfDeposit());
							bankReserveAccountPojo.setReference(tradesPojo.getSerialNumber());
							bankReserveAccountPojo.setTrDetail(tradesPojo.toString());
							bankReserveAccountPojo.setTrTime(ConvertUtils.timeToString(new Date()));
							bankReserveAccountPojo.setTrType("credit");
							String key = bankReserveAccountPojo.getBank();
							bankReserveAccountPojo.setBank(null);
							blockChainService.put("BankReserveAccount", key, bankReserveAccountPojo);
							break;
						}
					}
				}
			}
		}
		return uanTrResultPojo;
	}

	/** 
	 * @Description: reportLoss
	 * @param reportLossCommondPojo
	 * @return  UanTrResultPojo
	 * @throws UanException
	 */ 
	public UanTrResultPojo reportLoss(ReportLossCommondPojo reportLossCommondPojo) {
		UanTrResultPojo uanTrResultPojo = null;
		BankCardPojo tmp = uanContractService.loadBankCard(reportLossCommondPojo.getCode(),
				reportLossCommondPojo.getIdCard());
		if (tmp == null) {
			throw new UanException("此卡没有签约");
		}

		boolean hasPersonnelRelationship = false;
		
		AgreementPojo agreementPojo = uanContractService.loadMy(reportLossCommondPojo.getIdCard());
		for (String id : agreementPojo.getPersonnelIdCards()) {
			if (id.equals(reportLossCommondPojo.getParty())) {
				hasPersonnelRelationship = true;
				break;
			}
		}
		if (!hasPersonnelRelationship) {
			throw new UanException("你无权挂失");
		}

		if (OWNER.equals(tmp.getBankOfDeposit())) {
			bankCardService.reportLoss(reportLossCommondPojo);

			Map<String, Object> filter = new HashMap<String, Object>();
			filter.put("cardNum", reportLossCommondPojo.getCode());
			CardAccountPojo cardAccountPojo = blockChainService.get("CardAccount", reportLossCommondPojo.getCode(),
					filter, CardAccountPojo.class);
			cardAccountPojo.setState(BankCardPojo.STATE_ABNORMAL);
			String key = cardAccountPojo.getCardNum();
			cardAccountPojo.setCardNum(null);
			blockChainService.put("CardAccount", key, cardAccountPojo);
		} else {
			Map<String, Object> filter = new HashMap<String, Object>();
			filter.put("cardNum", reportLossCommondPojo.getCode());
			CardAccountPojo cardAccountPojo = blockChainService.get("CardAccount", reportLossCommondPojo.getCode(),
					filter, CardAccountPojo.class);
			cardAccountPojo.setState(BankCardPojo.STATE_ABNORMAL);
			String key = cardAccountPojo.getCardNum();
			cardAccountPojo.setCardNum(null);
			blockChainService.put("CardAccount", key, cardAccountPojo);
		}
		uanTrResultPojo = new UanTrResultPojo();
		uanTrResultPojo.setCardNum(reportLossCommondPojo.getCode());
		UanContractPojo uanContractPojo = (UanContractPojo) xmlConvertor.toObject(agreementPojo.getTrDetail());
		uanTrResultPojo.setMasterName(uanContractPojo.getCustomerInformation().getName());
		uanTrResultPojo.setInfo("挂失成功");
		return uanTrResultPojo;
	}

	/** 
	 * @Description: inquireAssets
	 * @param inquireAssetsCommondPojo
	 * @return  List<BankCardPojo>
	 * @throws 
	 */ 
	public List<BankCardPojo> inquireAssets(InquireAssetsCommondPojo inquireAssetsCommondPojo) {
		AgreementPojo agreementPojo = uanContractService.loadMy(inquireAssetsCommondPojo.getCustomer());
		boolean hasPersonnelRelationship = false;
		for (String id : agreementPojo.getPersonnelIdCards()) {
			if (id.equals(inquireAssetsCommondPojo.getParty())) {
				hasPersonnelRelationship = true;
				break;
			}
		}
		if (!hasPersonnelRelationship) {
			throw new UanException("你无权查询");
		}
		Map<String, String> filter = new HashMap<String, String>();
		filter.put("idCard", agreementPojo.getParty());
		PersonPojo personPojo = blockChainService.get("Person", agreementPojo.getParty(), filter, PersonPojo.class);
		List<BankCardPojo> res = new ArrayList<BankCardPojo>();
		for (String cn : agreementPojo.getBindingCardNums()) {
			filter = new HashMap<String, String>();
			filter.put("cardNum", cn);
			CardAccountPojo cardAccountPojo = blockChainService.get("CardAccount", cn, filter, CardAccountPojo.class);

			if (OWNER.equals(cardAccountPojo.getBankOfDeposit())) {
				BankCardPojo tmp = bankCardService.loadByCode(cardAccountPojo.getCardNum());
				res.add(tmp);
			} else {
				BankCardPojo b = new BankCardPojo();
				b.setAmt(ConvertUtils.stringToBigDecimal(ConvertUtils.doubleToString(cardAccountPojo.getAmtLeft())));
				b.setBankOfDeposit(cardAccountPojo.getBankOfDeposit());
				b.setCode(cardAccountPojo.getCardNum());
				b.setIdCard(b.getIdCard());
				b.setState(cardAccountPojo.getState());
				b.setCustomerInformation(new CustomerInformationPojo());
				b.getCustomerInformation().setName(personPojo.getName());
				b.getCustomerInformation().setIdCard(personPojo.getIdCard());
				res.add(b);
			}

		}
		return res;
	}

}
