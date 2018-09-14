package com.abc.uan.union;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abc.common.IService;
import com.abc.common.UanException;
import com.abc.common.util.UUIDNumberGenerator;

@Component
public class UnionService implements IService {

	@Autowired
	private UnionDAO unionDAO;
	@Autowired
	private UUIDNumberGenerator uuidNumberGenerator;

	public List<UnionPojo> findAll() {
		return unionDAO.findAll();
	}

	public List<UnionPojo> findByWhere(UnionQuery unionQuery) {
		return unionDAO.findByWhere(unionQuery);
	}

	public UnionPojo loadByCode(String code) {
		return unionDAO.load(code);
	}

	public void create(UnionPojo unionPojo) {
		unionPojo.setId(uuidNumberGenerator.generate());
		unionPojo.setState(UnionPojo.STATE_OPEN);
		unionPojo.setJoinInTime(new Date());
		if (StringUtils.isEmpty(unionPojo.getName()) || StringUtils.isEmpty(unionPojo.getCode())
				|| StringUtils.isEmpty(unionPojo.getType())) {
			throw new UanException("缺少必须的属性值");
		}
		UnionPojo tmp = loadByCode(unionPojo.getCode());
		if (tmp != null) {
			throw new UanException("联盟成员[" + unionPojo.getCode() + "]已存在");
		}
		unionDAO.insert(unionPojo);
	}

}
