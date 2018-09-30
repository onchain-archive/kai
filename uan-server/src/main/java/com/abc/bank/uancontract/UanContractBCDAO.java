/**  
 * Title: UanContractBCDAO.java
 * Description: UanContractBCDAO
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.bank.uancontract;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.abc.common.IDAO;

/**
 * Title: UanContractBCDAO
 * @Description: UanContractBCDAO
 * @author Bo Liu
 * @date 2018-09-20
*/
@Mapper
public interface UanContractBCDAO extends IDAO {

	/** 
	 * @Description: insert
	 * @param contractBCPojo
	 */ 
	@Insert("insert into UAN_CON ( MASTERID, CONTENT ) values ( #{masterId},#{content} )")
	public void insert(UanContractBCPojo contractBCPojo);

	/** 
	 * @Description: load
	 * @param masterId
	 * @return
	 */ 
	@Results(id = "UanContractBCPojo", value = { @Result(property = "masterId", column = "MASTERID"),
			@Result(property = "content", column = "CONTENT") })
	@Select("select MASTERID, CONTENT from UAN_CON where MASTERID=#{masterId}")
	public UanContractBCPojo load(@Param("masterId") String masterId);

	/** 
	 * @Description: findAll 
	 */ 
	@ResultMap("UanContractBCPojo")
	@Select("select MASTERID, CONTENT from UAN_CON ")
	public List<UanContractBCPojo> findAll();

	/** 
	 * @Description: delete
	 * @param masterId
	 */ 
	@Delete("delete from UAN_CON where MASTERID=#{masterId} ")
	public void delete(@Param("masterId") String masterId);

}
