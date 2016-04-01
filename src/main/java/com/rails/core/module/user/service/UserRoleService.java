package com.rails.core.module.user.service;

import java.util.List;

import com.rails.core.frame.generic.service.GenericService;
import com.rails.core.module.user.domain.SystemUser;
import com.rails.core.module.user.domain.UserRole;

public interface UserRoleService extends GenericService<UserRole> {
	public List<UserRole> getRoleByUser(SystemUser user) throws Exception; 
}
