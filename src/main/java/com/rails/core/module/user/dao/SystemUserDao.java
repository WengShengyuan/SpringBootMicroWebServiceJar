package com.rails.core.module.user.dao;

import com.rails.core.frame.generic.dao.GenericDao;
import com.rails.core.module.user.domain.SystemUser;

public interface SystemUserDao extends GenericDao<SystemUser> {
	
	public SystemUser findByName(String name) throws Exception;

}
