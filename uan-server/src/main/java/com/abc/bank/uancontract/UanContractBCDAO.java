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

@Mapper
public interface UanContractBCDAO extends IDAO {

	@Insert("insert into UAN_CON ( MASTERID, CONTENT ) values ( #{masterId},#{content} )")
	public void insert(UanContractBCPojo contractBCPojo);

	@Results(id = "UanContractBCPojo", value = { @Result(property = "masterId", column = "MASTERID"),
			@Result(property = "content", column = "CONTENT") })
	@Select("select MASTERID, CONTENT from UAN_CON where MASTERID=#{masterId}")
	public UanContractBCPojo load(@Param("masterId") String masterId);

	@ResultMap("UanContractBCPojo")
	@Select("select MASTERID, CONTENT from UAN_CON ")
	public List<UanContractBCPojo> findAll();

	@Delete("delete from UAN_CON where MASTERID=#{masterId} ")
	public void delete(@Param("masterId") String masterId);

}
