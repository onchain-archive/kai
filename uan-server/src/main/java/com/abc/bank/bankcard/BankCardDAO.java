package com.abc.bank.bankcard;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.abc.common.IDAO;

@Mapper
public interface BankCardDAO extends IDAO {

	@Select("select ID,CODE,ID_CARD,STATE,AMT,BANK_OF_DEPOSIT from BANK_BANCAR where ID_CARD=#{idCard}")
	@Results(id = "BankCard", value = { @Result(property = "id", column = "ID"),
			@Result(property = "code", column = "CODE"),
			@Result(property = "idCard", column = "ID_CARD"),
			@Result(property = "customerInformation", column = "ID_CARD", one = @One(select = "com.abc.bank.customerinformation.CustomerInformationDAO.loadByIdCard")),
			@Result(property = "state", column = "STATE"), @Result(property = "amt", column = "AMT"),
			@Result(property = "bankOfDeposit", column = "BANK_OF_DEPOSIT") })
	public List<BankCardPojo> findByIdCard(@Param("idCard") String idCard);

	@Select("select ID,CODE,ID_CARD,STATE,AMT,BANK_OF_DEPOSIT from BANK_BANCAR where CODE=#{code}")
	@ResultMap("BankCard")
	public BankCardPojo loadByCode(@Param("code") String code);

	@Update("update BANK_BANCAR set AMT = #{amt} where CODE = #{code}")
	public void updateAmt(BankCardPojo bankCardPojo);
	
	@Update("update BANK_BANCAR set STATE = #{state} where CODE = #{code}")
	public void updateState(BankCardPojo bankCardPojo);

}
