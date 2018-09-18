package com.abc.uan.catastrophe;

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
public interface CatastropheDAO extends IDAO {

	@Results(id = "Catastrophe", value = { @Result(property = "id", column = "ID"),
			@Result(property = "name", column = "NAME"), @Result(property = "begin", column = "BIGIN"),
			@Result(property = "end", column = "END"), @Result(property = "areas", column = "AREAS") })
	@Select("select ID,NAME,BIGIN,END,AREAS from UAN_CAT where NAME=#{name}")
	public CatastrophePojo load(@Param("name") String name);

	@ResultMap("Catastrophe")
	@Select("select ID,NAME,BIGIN,END,AREAS from UAN_CAT")
	public List<CatastrophePojo> findAll();

	@ResultMap("Catastrophe")
	@Select("<script> " + " select ID,NAME,BIGIN,END,AREAS from UAN_CAT  " + " <where> "
			+ " <if test=\"name != null and name != ''\"> NAME LIKE '%${name}%'</if> " + " </where> " + " </script> ")
	public List<CatastrophePojo> findByWhere(@Param("name") String name);

	@Insert("insert into UAN_CAT ( ID,NAME,BIGIN,END,AREAS ) values ( #{id},#{name},#{begin},#{end},#{areas} )")
	public void insert(CatastrophePojo unionPojo);

}
