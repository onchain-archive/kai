/**  
 * Title: BankCardDAO.java
 * Description: BankCardDAO
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
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

/**
 * Title: BankCardDAO
 * @Description: BankCardDAO
 * @author Bo Liu
 * @date 2018-09-20
*/
@Mapper
public interface BankCardDAO extends IDAO {

	/** 
	 * <p>find By IdCard</p>
	 * @Description: find By IdCard
	 * @param idCard
	 * @return List<BankCardPojo>
	 */ 
	@Select("select ID,CODE,ID_CARD,STATE,AMT,BANK_OF_DEPOSIT from BANK_BANCAR where ID_CARD=#{idCard}")
	@Results(id = "BankCard", value = { @Result(property = "id", column = "ID"),
			@Result(property = "code", column = "CODE"),
			@Result(property = "idCard", column = "ID_CARD"),
			@Result(property = "customerInformation", column = "ID_CARD", one = @One(select = "com.abc.bank.customerinformation.CustomerInformationDAO.loadByIdCard")),
			@Result(property = "state", column = "STATE"), @Result(property = "amt", column = "AMT"),
			@Result(property = "bankOfDeposit", column = "BANK_OF_DEPOSIT") })
	public List<BankCardPojo> findByIdCard(@Param("idCard") String idCard);

	/** 
	 * @Description: loadByCode Select("select ID,CODE,ID_CARD,STATE,AMT,BANK_OF_DEPOSIT from BANK_BANCAR where CODE=#{code}")
	 * @param code
	 * @return BankCardPojo
	 * @throws 
	 */ 
	@Select("select ID,CODE,ID_CARD,STATE,AMT,BANK_OF_DEPOSIT from BANK_BANCAR where CODE=#{code}")
	@ResultMap("BankCard")
	public BankCardPojo loadByCode(@Param("code") String code);

	/** 
	 * @Description: updateAmt:Update("update BANK_BANCAR set AMT = #{amt} where CODE = #{code}")
	 * @param bankCardPojo
	 */ 
	@Update("update BANK_BANCAR set AMT = #{amt} where CODE = #{code}")
	public void updateAmt(BankCardPojo bankCardPojo);
	
	/** 
	 * @Description: updateState: Update("update BANK_BANCAR set STATE = #{state} where CODE = #{code}")
	 * @param bankCardPojo
	 */ 
	@Update("update BANK_BANCAR set STATE = #{state} where CODE = #{code}")
	public void updateState(BankCardPojo bankCardPojo);

}
