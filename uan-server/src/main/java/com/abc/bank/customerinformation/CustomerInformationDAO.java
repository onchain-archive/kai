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
