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

/**  
 * Title: BankCardService.java
 * Description: BankCardService
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.bank.bankcard;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abc.common.IService;
import com.abc.common.UanException;
import com.abc.common.bus.RequestBus;
import com.abc.common.bus.ThreadContext;
import com.abc.common.util.ConvertUtils;

/**
 * Title: BankCardService
 * @Description: BankCardService
 * @author Bo Liu
 * @date 2018-09-20
*/
@Component
public class BankCardServiceImpl implements IService {

	@Autowired
	private BankCardDAO bankCardDAO;

	/** 
	 * @Description: findMyCard
	 * @return List<BankCardPojo>
	 * @throws 
	 */ 
	public List<BankCardPojo> findMyCard() {
		String idCard = (String) ((RequestBus) ThreadContext.getContext().getAttribute(RequestBus.REQUEST_BUS))
				.getAttributes().get(RequestBus.USER);
		List<BankCardPojo> list = bankCardDAO.findByIdCard(idCard);
		return list;
	}

	/** 
	 * @Description: findByIdCard
	 * @param idCard
	 * @return List<BankCardPojo>
	 * @throws 
	 */ 
	public List<BankCardPojo> findByIdCard(String idCard) {
		
		List<BankCardPojo> list = bankCardDAO.findByIdCard(idCard);
		return list;
	}

	/** 
	 * @Description: withdraw
	 * @param bankCode
	 * @param amount
	 * @return void
	 * @throws UanException
	 */ 
	public void withdraw(String bankCode, Double amount) {
		BankCardPojo bankCardPojo = bankCardDAO.loadByCode(bankCode);
		if (bankCardPojo == null) {
			throw new UanException("无此卡");
		}
		if (!BankCardPojo.STATE_NORMAL.equals(bankCardPojo.getState())) {
			throw new UanException("此卡已挂失");
		}
		BigDecimal bd = ConvertUtils.stringToBigDecimal(ConvertUtils.doubleToString(amount));
		if (bankCardPojo.getAmt().compareTo(bd) >= 0) {
			bankCardPojo.setAmt(bankCardPojo.getAmt().subtract(bd));
			bankCardDAO.updateAmt(bankCardPojo);
		} else {
			throw new UanException("余额不足");
		}

	}

	/** 
	 * @Description: reportLoss
	 * @param bankCardPojo 
	 * @return void
	 * @throws UanException
	 */ 
	public void reportLoss(BankCardPojo bankCardPojo) {
		BankCardPojo tmp = bankCardDAO.loadByCode(bankCardPojo.getCode());
		if (tmp == null) {
			throw new UanException("无此卡");
		}
		if (!BankCardPojo.STATE_NORMAL.equals(tmp.getState())) {
			throw new UanException("此卡已挂失");
		}
		bankCardPojo.setState(BankCardPojo.STATE_ABNORMAL);
		bankCardDAO.updateState(bankCardPojo);
	}

	public BankCardPojo loadByCode(String code) {
		return bankCardDAO.loadByCode(code);
	}

}
