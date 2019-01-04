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
import com.abc.uan.blockchain.BlockChainServiceImpl;
import com.abc.uan.blockchain.TradePojo;

/**
 * Title: HyperledgerService
 * @Description: HyperledgerService
 * @author Bo Liu
 * @date 2018-09-20
 */
@Component
public class HyperledgerServiceImpl implements IService {

	@Autowired
	private BlockChainServiceImpl blockChainService;

	/** 
	 * @Description: getBankReserveAccounts
	 * @return List
	 */ 
	public List<BankReserveAccountPojo> getBankReserveAccounts() {
		Map<String, Object> filter = new HashMap<String, Object>();
		// filter.put("bank", "abc");
		List<BankReserveAccountPojo> bankReserveAccountPojos = blockChainService.getList("BankReserveAccount", null,
				filter, BankReserveAccountPojo.class);
		LogWriter.info(HyperledgerServiceImpl.class, "准备金账户信息：{}", bankReserveAccountPojos);
		return bankReserveAccountPojos;
	}

	/** 
	 * @Description: getTrades
	 * @return List
	 */ 
	public List<TradePojo> getTrades() {
		Map<String, Object> filter = new HashMap<String, Object>();
		// filter.put("bank", "abc");
		List<TradePojo> tradePojos = blockChainService.getList("Trade", null, filter, TradePojo.class);
		LogWriter.info(HyperledgerServiceImpl.class, "交易信息：{}", tradePojos);
		return tradePojos;
	}

	/** 
	 * @Description: getAgreements
	 * @return List
	 */ 
	public List<AgreementPojo> getAgreements() {
		Map<String, Object> filter = new HashMap<String, Object>();
		// filter.put("bank", "abc");
		List<AgreementPojo> agreementPojos = blockChainService.getList("Agreement", null, filter, AgreementPojo.class);
		LogWriter.info(HyperledgerServiceImpl.class, "交易信息：{}", agreementPojos);
		return agreementPojos;
	}

}
