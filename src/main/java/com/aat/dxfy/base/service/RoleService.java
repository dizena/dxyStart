package com.aat.dxfy.base.service;

import java.util.List;
import java.util.Map;

import com.aat.dxfy.base.bean.Ress;
import com.aat.dxfy.base.bean.Role;

public interface RoleService {
	int addRole(String role, String describe, String ressId);

	int updateRole(String id, String role, String describe, String ressId);

	int lockRole(String id, String locked);

	int deleteRole(String id);

	Role queryRole(String id);

	List<String> queryResIds(String id);

	List<Ress> queryRess(String id);
	
	List<Ress> queryRoleRess(String role);

	List<Role> queryAll(Map<String, Object> map);

	long queryAllCount(Map<String, Object> map);
}
