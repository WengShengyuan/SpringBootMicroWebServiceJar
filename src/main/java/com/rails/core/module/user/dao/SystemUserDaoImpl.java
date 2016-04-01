package com.rails.core.module.user.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rails.core.frame.generic.dao.GenericDaoImpl;
import com.rails.core.module.user.domain.SystemUser;

@Repository("SystemUserDaoImpl")
public class SystemUserDaoImpl extends GenericDaoImpl<SystemUser> implements SystemUserDao {

	@Override
	public SystemUser findByName(String name) throws Exception {
		HashMap<String, Object>map = new HashMap<String, Object>();
		map.put("userName", name);
		List<SystemUser> users = queryByMap(map);
		if(users != null && users.size()>0){
			return users.get(0);
		} else {
			return null;
		}
	}

}
