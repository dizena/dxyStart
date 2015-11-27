package com.aat.dxfy.base.service;

import java.util.List;
import java.util.Map;

import com.aat.dxfy.base.bean.User;

public interface UserService {
	int addUser(User user);

	int updateUser(User user);

	int updateUserPwd(String id, String p);

	int deleteUser(String id);

	User queryUserId(String id);

	User queryUserLogin(String username);

	List<User> queryAll(Map<String, Object> map);

	long queryAllCount(Map<String, Object> map);

}
