package com.aat.dxfy.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aat.dxfy.base.bean.Role;

@Repository
public interface RoleDao {
	int addBean(Role role);

	int updateBean(Role role);

	int deleteBean(String id);

	Role queryBean(String id);
	
	Role queryRole(String id);
	

	List<Role> queryAll(Map<String, Object> map);

	long queryAllCount(Map<String, Object> map);
}
