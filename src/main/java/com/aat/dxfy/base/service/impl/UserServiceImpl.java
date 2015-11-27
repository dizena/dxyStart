package com.aat.dxfy.base.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aat.dxfy.base.bean.User;
import com.aat.dxfy.base.dao.UserDao;
import com.aat.dxfy.base.service.UserService;
import com.aat.utils.Atools;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 用户业务
 * @info java spring 
 * @update 无
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	
	public int addUser(User user) {
		return userDao.addBean(user);
	}

	
	public int updateUser(User user) {
		return userDao.updateBean(user);
	}

	
	public int updateUserPwd(String id, String p) {
		User user = new User();
		user.setId(id);
		user.setPasswd(Atools.makePwd(p));
		user.setSalt(Atools.jiami(userDao.queryBean(id).getUsername()+"|"+p));
		return userDao.updateBean(user);
	}

	
	public int deleteUser(String id) {
		return userDao.deleteBean(id);
	}

	
	public User queryUserId(String id) {
		return userDao.queryBean(id);
	}

	
	public User queryUserLogin(String username) {
		if(StringUtils.isEmpty(username)){
			return null;
		}
		return userDao.queryUser(username);
	}

	
	public List<User> queryAll(Map<String, Object> map) {
		return userDao.queryAll(map);
	}

	
	public long queryAllCount(Map<String, Object> map) {
		return userDao.queryAllCount(map);
	}

}
