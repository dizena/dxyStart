package com.aat.dxfy.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface RoleResDao {
	int addBean(Map<String, Object> map);

	int deleteBean(String roleid);

	List<String> queryResIds(String roleid);
}
