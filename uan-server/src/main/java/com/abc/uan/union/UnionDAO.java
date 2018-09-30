/**  
 * Title: UnionDAO.java
 * Description: UnionDAO
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.uan.union;

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
 * Title: UnionDAO
 * @Description: UnionDAO
 * @author Bo Liu
 * @date 2018-09-20
 */
@Mapper
public interface UnionDAO extends IDAO {

	/** 
	 * @Description: load 
	 * @param code
	 * @return
	 */ 
	@Results(id = "Union", value = { @Result(property = "id", column = "ID"),
			@Result(property = "name", column = "NAME"), @Result(property = "code", column = "CODE"),
			@Result(property = "joinInTime", column = "JOIN_IN_TIME"), @Result(property = "state", column = "STATE"),
			@Result(property = "type", column = "TYPE"), @Result(property = "phone", column = "PHONE"),
			@Result(property = "address", column = "ADDRESS") })
	@Select("select ID,NAME,CODE,JOIN_IN_TIME,STATE,TYPE,PHONE,ADDRESS from UAN_UNI where CODE=#{code}")
	public UnionPojo load(@Param("code") String code);

	/** 
	 * @Description: findAll 
	 * @return
	 */ 
	@ResultMap("Union")
	@Select("select ID,NAME,CODE,JOIN_IN_TIME,STATE,TYPE,PHONE,ADDRESS from UAN_UNI")
	public List<UnionPojo> findAll();

	/** 
	 * @Description: findByWhere 
	 * @param unionQuery
	 * @return
	 */ 
	@ResultMap("Union")
	@Select("<script> " + " select ID,NAME,CODE,JOIN_IN_TIME,STATE,TYPE,PHONE,ADDRESS from UAN_UNI  " + " <where> "
			+ " <if test=\"code != null and code != ''\"> CODE=#{code}</if> "
			+ " <if test=\"name != null and name != ''\"> AND NAME LIKE '%${name}%'</if> " + " </where> " + " </script> ")
	public List<UnionPojo> findByWhere(UnionQuery unionQuery);

	/** 
	 * @Description: insert 
	 * @param unionPojo
	 */ 
	@Insert("insert into UAN_UNI ( ID,NAME,CODE,JOIN_IN_TIME,STATE,TYPE,PHONE,ADDRESS ) values ( #{id},#{name},#{code},#{joinInTime},#{state},#{type},#{phone},#{address} )")
	public void insert(UnionPojo unionPojo);

	/** 
	 * @Description: delete 
	 * @param code
	 */ 
	@Delete("delete from UAN_UNI where CODE = #{code} ")
	public void delete(@Param("code") String code);
}
