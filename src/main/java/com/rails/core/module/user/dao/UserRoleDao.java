package com.rails.core.module.user.dao;

import java.util.List;

import com.rails.core.frame.generic.dao.GenericDao;
import com.rails.core.module.user.domain.SystemUser;
import com.rails.core.module.user.domain.UserRole;

public interface UserRoleDao extends GenericDao<UserRole>{
	public List<UserRole> getRoleByUser(SystemUser user) throws Exception;
}
