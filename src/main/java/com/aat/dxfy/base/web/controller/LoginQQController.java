package com.aat.dxfy.base.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aat.dxfy.base.bean.User;
import com.aat.dxfy.base.service.UserService;
import com.aat.utils.Atools;

/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 使用QQ登录
 * @info java springMVC
 * @update 无
 */
@Controller
@Scope("prototype")
public class LoginQQController extends BaseController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "qqlogin", method = RequestMethod.GET)
	public String qqlogin() {
		
		return "/loginqq";
	}

	@RequestMapping(value = "qqlogin", method = RequestMethod.POST)
	@ResponseBody
	public String qqlogin(@RequestParam("id") String openID,
			@RequestParam("t") String accessToken,
			@RequestParam("n") String nickname,
			@RequestParam("s") String gender,
			@RequestParam("a") String avatar100,
			@RequestParam("l") String location) {
		User user = new User();
		user.setId(openID);
		user.setUsername(openID);
		user.setPasswd(Atools.makePwd(openID));

		user.setSalt(Atools.jiami(openID));
		user.setRoles("user");
		user.setAuths("none");
		user.setLocked("0");
		user.setRegTime(new Date());
		user.setFlag("qq");
		user.setToken(accessToken);
		user.setNickname(nickname);
		user.setSex(gender);
		user.setIcon(avatar100);
		user.setLocation(location);
		user.setSystime(new Date());
		//
		User dbUser = userService.queryUserId(openID);
		if (null == dbUser) {
			userService.addUser(user);
		} else {
			User tmpUser = new User();
			tmpUser.setId(openID);
			tmpUser.setToken(accessToken);
			tmpUser.setSystime(new Date());
			userService.updateUser(tmpUser);
		}
		//
		User u = userService.queryUserId(openID);
		boolean b = shiroLogin(u.getUsername(), Atools.makePwd(openID));
		if (b) {
			return "1";
		}
		return "0";
	}
}
