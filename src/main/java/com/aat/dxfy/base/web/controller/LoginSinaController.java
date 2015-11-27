package com.aat.dxfy.base.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;

import com.aat.dxfy.base.CommonConstant;
import com.aat.dxfy.base.bean.User;
import com.aat.dxfy.base.service.UserService;
import com.aat.utils.Atools;

/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 新浪用户登录
 * @info java springMVC WEIBOSDK
 * @update 无
 */
@Controller
@Scope("prototype")
public class LoginSinaController extends BaseController {

	@Autowired
	private UserService userService;

	@Value("#{configProperties['client_ID']}")
	private String kid;
	@Value("#{configProperties['redirect_URI']}")
	private String reurl;

	@RequestMapping(value = "sinaloginPage")
	public String sinaloginPage(HttpServletRequest request) {
		try {
			String forcelogin = "false";
			String uri = "https://api.weibo.com/oauth2/authorize?client_id="
					+ kid + "&response_type=code&redirect_uri=" + reurl
					+ "/sinalogin&forcelogin=" + forcelogin;
			return "redirect:" + uri;
		} catch (Exception e) {
		}
		return CommonConstant.WEB_LOGIN_INDEX;
	}

	@RequestMapping(value = "sinalogin")
	public String sinaLogin(@RequestParam("code") String code,
			HttpServletRequest request) {
		Oauth oauth = new Oauth();
		try {
			AccessToken at = oauth.getAccessTokenByCode(code);
			String access_token = at.getAccessToken();
			request.getSession().setAttribute("access_token", access_token);
			Users um = new Users(access_token);
			weibo4j.model.User wbuser = um.showUserById(at.getUid());
			// dxyUser
			User dxyUser = new User();
			dxyUser.setId(wbuser.getId());
			dxyUser.setUsername(wbuser.getId());
			dxyUser.setNickname(wbuser.getScreenName());
			if (StringUtils.isNotEmpty(wbuser.getGender())
					&& "m".equals(wbuser.getGender())) {
				dxyUser.setSex("男");
			} else {
				dxyUser.setSex("女");
			}
			dxyUser.setLocation(wbuser.getLocation());
			dxyUser.setIcon(wbuser.getProfileImageUrl());
			dxyUser.setToken(access_token);
			//
			dxyUser.setPasswd(Atools.makePwd(wbuser.getId()));
			dxyUser.setSalt(Atools.jiami(wbuser.getId()));
			dxyUser.setRoles("user");
			dxyUser.setAuths("none");
			dxyUser.setLocked("0");
			dxyUser.setRegTime(new Date());
			dxyUser.setFlag("sina");
			dxyUser.setSystime(new Date());
			//
			User dbUser = userService.queryUserId(wbuser.getId());
			if (null == dbUser) {
				userService.addUser(dxyUser);
			} else {
				User tmpUser = new User();
				tmpUser.setId(wbuser.getId());
				tmpUser.setToken(access_token);
				tmpUser.setSystime(new Date());
				userService.updateUser(tmpUser);
			}
			User u = userService.queryUserId(wbuser.getId());
			boolean b = shiroLogin(u.getUsername(),
					Atools.makePwd(wbuser.getId()));
			if (b) {
				return CommonConstant.WEB_INDEX;
			}

		} catch (WeiboException e) {

		}
		return CommonConstant.WEB_LOGIN_INDEX;
	}
	/*
	 @Autowired
	 private ExpandAccountService expandAccountService;
	 // 如果是已经登录的用户，就确定是增加微博账户
			if (null != getSessionUser()) {
				Expandaccount exc = new Expandaccount();
				exc.setId(wbuser.getId());
				exc.setUserid(getSessionUser().getId());
				exc.setAtype("sina");
				exc.setAusername(wbuser.getScreenName());
				exc.setAkeyid(access_token);
				exc.setSystime(new Date());
				Long l = new Date().getTime() + 1000 * 3600 * 24 * 7;
				exc.setOther(Atools.getStrFmtTime("yyyy-MM-dd", new Date(l)));
				if (expandAccountService.updateBean(exc) == 0) {
					expandAccountService.addBean(exc);
				}
				return "redirect:/master/exAccountList";
			}
	 */

}
