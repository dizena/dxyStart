package com.aat.dxfy.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aat.dxfy.base.bean.User;

@Repository
public interface UserDao {
	int addBean(User user);

	int updateBean(User user);

	int deleteBean(String id);

	User queryBean(String id);

	User queryUser(String username);

	List<User> queryAll(Map<String, Object> map);

	long queryAllCount(Map<String, Object> map);

}
