/**  
 * Title: CustomerInformationRestApiControllor.java
 * Description: CustomerInformationRestApiControllor
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
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
