package com.abc.uan.catastrophe;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abc.common.IService;
import com.abc.common.UanException;
import com.abc.common.util.UUIDNumberGenerator;

@Component
public class CatastropheService implements IService {

	@Autowired
	private CatastropheDAO catastropheDAO;
	@Autowired
	private UUIDNumberGenerator uuidNumberGenerator;

	public List<CatastrophePojo> findAll() {
		return catastropheDAO.findAll();
	}

	public List<CatastrophePojo> findByWhere(String name) {
		return catastropheDAO.findByWhere(name);
	}

	public void create(CatastrophePojo unionPojo) {
		unionPojo.setId(uuidNumberGenerator.generate());
		if (StringUtils.isEmpty(unionPojo.getAreas()) || StringUtils.isEmpty(unionPojo.getName())
				|| unionPojo.getBegin() == null || unionPojo.getEnd() == null) {
			throw new UanException("缺少必须的属性值");
		}
		catastropheDAO.insert(unionPojo);
	}

}
