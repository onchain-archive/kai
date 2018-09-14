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

@Component
public class BankCardService implements IService {

	@Autowired
	private BankCardDAO bankCardDAO;

	public List<BankCardPojo> findMyCard() {
		String idCard = (String) ((RequestBus) ThreadContext.getContext().getAttribute(RequestBus.REQUEST_BUS))
				.getAttributes().get(RequestBus.USER);
		List<BankCardPojo> list = bankCardDAO.findByIdCard(idCard);
		return list;
	}

	public List<BankCardPojo> findByIdCard(String idCard) {
		
		List<BankCardPojo> list = bankCardDAO.findByIdCard(idCard);
		return list;
	}

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
