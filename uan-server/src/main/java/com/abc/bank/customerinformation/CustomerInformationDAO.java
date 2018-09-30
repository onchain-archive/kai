/**  
 * Title: CustomerInformationDAO.java
 * Description: CustomerInformationDAO
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.bank.customerinformation;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.abc.common.IDAO;

/**
 * Title: CustomerInformationDAO
 * @Description: CustomerInformationDAO
 * @author Bo Liu
 * @date 2018-09-20
*/
@Mapper
public interface CustomerInformationDAO extends IDAO {

	/** 
	 * @Description: loadByIdCard 
	 * @param idCard
	 * @return CustomerInformationPojo
	 * @throws 
	 */ 
	@Select("select ID,NAME,ID_CARD,PHONE,STATE,TYPE from BANK_CUSINF where ID_CARD=#{idCard}")
	@Results(id = "CustomerInformation", value = { @Result(property = "id", column = "ID"),
			@Result(property = "name", column = "NAME"), @Result(property = "idCard", column = "ID_CARD"),
			@Result(property = "phone", column = "PHONE"), @Result(property = "state", column = "STATE"),
			@Result(property = "type", column = "TYPE"), })
	public CustomerInformationPojo loadByIdCard(@Param("idCard") String idCard);

	/** 
	 * @Description: load
	 * @return CustomerInformationPojo
	 * @throws 
	 */ 
	@Select("select ID,NAME,ID_CARD,PHONE,STATE,TYPE from BANK_CUSINF where ID=#{id}")
	@ResultMap("CustomerInformation")
	public CustomerInformationPojo load(@Param("id") String id);
	
	/** 
	 * @Description: checkPwd
	 * @return  String
	 * @throws 
	 */ 
	@Select("select PWD from BANK_CUSINF where ID_CARD=#{idCard}")
	public String checkPwd(@Param("idCard") String idCard);

}
