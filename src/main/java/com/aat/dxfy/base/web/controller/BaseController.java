package com.aat.dxfy.base.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aat.dxfy.base.CommonConstant;
import com.aat.dxfy.base.bean.User;

/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 所有controller的基础，子类继承于此，封装好了session的会话取得user,auths,role等
 * @info java springMVC
 * @update 无
 */
public class BaseController {
	public final Logger log = LoggerFactory.getLogger(this.getClass());

	// 会话中得到USER用户
	protected User getSessionUser() {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(CommonConstant.KEY_LOGIN_USER);
		return user;
	}

	// 会话中得到session
	protected Session getSession() {
		Subject currentUser = SecurityUtils.getSubject();
		return currentUser.getSession();
	}

	// 得到server级别的attr
	protected ServletContext getApplication(HttpServletRequest request) {
		return request.getServletContext();
	}

	// 会话中得到subject
	protected Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	// 检查角色
	protected boolean chkRole(String role) {
		if (null == getSessionUser()) {
			return false;
		}
		Subject sub = SecurityUtils.getSubject();
		if (null != sub && null != role && sub.hasRole(role)) {
			return true;
		}
		return false;
	}

	// 检查权限
	protected boolean chkAuth(String auth) {
		if (null == getSessionUser()) {
			return false;
		}
		Subject sub = SecurityUtils.getSubject();
		if (null != sub && null != auth && sub.isPermitted(auth)) {
			return true;
		}
		return false;
	}

	// 检查ID与sessionUserID,用于DDL操作验证自己
	protected boolean chkSelfID(String id) {
		Subject sub = SecurityUtils.getSubject();
		if (null != sub && null != id && id.equals(getSessionUser().getId())) {
			return true;
		}
		return false;
	}


	// 第三方登录
	protected boolean shiroLogin(String u, String p) {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(u, p);
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.login(token);
			if (currentUser.isAuthenticated()) {

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
		}
		return false;
	}

	// isEmpty
	public boolean isEmpty(String str) {
		if (StringUtils.isEmpty(str)) {
			return true;
		}
		return false;
	}

	// isNotEmpty
	public boolean isNotEmpty(String str) {
		if (StringUtils.isNotEmpty(str)) {
			return true;
		}
		return false;
	}

}
