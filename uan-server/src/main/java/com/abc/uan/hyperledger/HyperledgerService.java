package com.abc.uan.hyperledger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abc.common.IService;
import com.abc.common.util.LogWriter;
import com.abc.uan.blockchain.AgreementPojo;
import com.abc.uan.blockchain.BankReserveAccountPojo;
import com.abc.uan.blockchain.BlockChainService;
import com.abc.uan.blockchain.TradePojo;

@Component
public class HyperledgerService implements IService {

	@Autowired
	private BlockChainService blockChainService;

	public List<BankReserveAccountPojo> getBankReserveAccounts() {
		Map<String, Object> filter = new HashMap<String, Object>();
		// filter.put("bank", "abc");
		List<BankReserveAccountPojo> bankReserveAccountPojos = blockChainService.getList("BankReserveAccount", null,
				filter, BankReserveAccountPojo.class);
		LogWriter.info(HyperledgerService.class, "准备金账户信息：{}", bankReserveAccountPojos);
		return bankReserveAccountPojos;
	}

	public List<TradePojo> getTrades() {
		Map<String, Object> filter = new HashMap<String, Object>();
		// filter.put("bank", "abc");
		List<TradePojo> tradePojos = blockChainService.getList("Trade", null, filter, TradePojo.class);
		LogWriter.info(HyperledgerService.class, "交易信息：{}", tradePojos);
		return tradePojos;
	}

	public List<AgreementPojo> getAgreements() {
		Map<String, Object> filter = new HashMap<String, Object>();
		// filter.put("bank", "abc");
		List<AgreementPojo> agreementPojos = blockChainService.getList("Agreement", null, filter, AgreementPojo.class);
		LogWriter.info(HyperledgerService.class, "交易信息：{}", agreementPojos);
		return agreementPojos;
	}

}
