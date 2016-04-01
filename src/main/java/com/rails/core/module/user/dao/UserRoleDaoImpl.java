package com.rails.core.module.user.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rails.core.frame.generic.dao.GenericDaoImpl;
import com.rails.core.module.user.domain.SystemUser;
import com.rails.core.module.user.domain.UserRole;

@Repository("UserRoleDaoImpl")
public class UserRoleDaoImpl extends GenericDaoImpl<UserRole> implements UserRoleDao{

	@Override
	public List<UserRole> getRoleByUser(SystemUser user) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userId", user.getId());
		return queryByMap(map);
	}

}
