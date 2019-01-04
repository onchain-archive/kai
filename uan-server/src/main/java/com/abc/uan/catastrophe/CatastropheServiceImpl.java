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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abc.common.IService;
import com.abc.common.UanException;
import com.abc.common.util.UUIDNumberGenerator;

/**
 * Title: CatastropheService
 * @Description: CatastropheService
 * @author Bo Liu
 * @date 2018-09-20
 */
@Component
public class CatastropheServiceImpl implements IService {

	@Autowired
	private CatastropheDAO catastropheDAO;
	@Autowired
	private UUIDNumberGenerator uuidNumberGenerator;

	/** 
	 * @Description: findAll
	 * @return List
	 */ 
	public List<CatastrophePojo> findAll() {
		return catastropheDAO.findAll();
	}

	/** 
	 * @Description: findByWhere
	 * @param name
	 * @return List
	 */ 
	public List<CatastrophePojo> findByWhere(String name) {
		return catastropheDAO.findByWhere(name);
	}

	/** 
	 * @Description: create
	 * @param unionPojo
	 * @throws UanException
	 */ 
	public void create(CatastrophePojo unionPojo) {
		unionPojo.setId(uuidNumberGenerator.generate());
		if (StringUtils.isEmpty(unionPojo.getAreas()) || StringUtils.isEmpty(unionPojo.getName())
				|| unionPojo.getBegin() == null || unionPojo.getEnd() == null) {
			throw new UanException("缺少必须的属性值");
		}
		catastropheDAO.insert(unionPojo);
	}

}
