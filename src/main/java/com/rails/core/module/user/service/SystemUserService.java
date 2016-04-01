package com.rails.core.module.user.service;

import com.rails.core.frame.generic.service.GenericService;
import com.rails.core.module.user.domain.SystemUser;

public interface SystemUserService extends GenericService<SystemUser>{
	public SystemUser findByName(String name) throws Exception;
}
