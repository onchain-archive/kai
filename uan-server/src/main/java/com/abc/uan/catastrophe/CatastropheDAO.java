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

/**
 * Title: CatastropheDAO
 * @Description: CatastropheDAO
 * @author Bo Liu
 * @date 2018-09-20
 */
@Mapper
public interface CatastropheDAO extends IDAO {

	/** 
	 * @Description: load 
	 * @param name
	 * @return
	 */ 
	@Results(id = "Catastrophe", value = { @Result(property = "id", column = "ID"),
			@Result(property = "name", column = "NAME"), @Result(property = "begin", column = "BIGIN"),
			@Result(property = "end", column = "END"), @Result(property = "areas", column = "AREAS") })
	@Select("select ID,NAME,BIGIN,END,AREAS from UAN_CAT where NAME=#{name}")
	public CatastrophePojo load(@Param("name") String name);

	/** 
	 * @Description: findAll 
	 * @return
	 */ 
	@ResultMap("Catastrophe")
	@Select("select ID,NAME,BIGIN,END,AREAS from UAN_CAT")
	public List<CatastrophePojo> findAll();

	/** 
	 * @Description: findByWhere
	 * @param name
	 * @return
	 */ 
	@ResultMap("Catastrophe")
	@Select("<script> " + " select ID,NAME,BIGIN,END,AREAS from UAN_CAT  " + " <where> "
			+ " <if test=\"name != null and name != ''\"> NAME LIKE '%${name}%'</if> " + " </where> " + " </script> ")
	public List<CatastrophePojo> findByWhere(@Param("name") String name);

	/** 
	 * @Description: insert 
	 * @param unionPojo
	 */ 
	@Insert("insert into UAN_CAT ( ID,NAME,BIGIN,END,AREAS ) values ( #{id},#{name},#{begin},#{end},#{areas} )")
	public void insert(CatastrophePojo unionPojo);

}
