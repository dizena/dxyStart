package com.aat.dxfy.base.web.realm;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.aat.dxfy.base.CommonConstant;
import com.aat.dxfy.base.bean.User;
import com.aat.dxfy.base.service.UserService;
import com.aat.utils.Atools;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 权限登录的控制块
 * @info java shiro realm
 * @update 无
 */
public class MyRealm extends AuthorizingRealm {


	@Autowired
	private UserService userService;

	/**
	 * @info 授权
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		try {
			String key = (String) getAvailablePrincipal(principals);
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Object tmp = session.getAttribute(CommonConstant.KEY_LOGIN_USER);
			User user = null;
			if (null != tmp) {
				user = (User) tmp;
			} else {
				user = userService.queryUserLogin(key);
			}

			Set<String> roleNames = new HashSet<String>();
			Set<String> permissions = new TreeSet<String>();

			roleNames.addAll(Atools.parseStr2List(user.getRoles(), ","));
			permissions.addAll(Atools.parseStr2List(user.getAuths(), ","));

			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			info.setRoles(roleNames);
			info.setStringPermissions(permissions);
			return info;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @info 登录
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String key = upToken.getUsername();
		String passwd = new String(upToken.getPassword());

		User user = userService.queryUserLogin(key);
		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}
		if ("1".equals(user.getLocked())) {
			throw new LockedAccountException(); // 帐号锁定
		}

		if (user.getPasswd().equals(passwd)) {
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(key,
					passwd, ByteSource.Util.bytes(user.getSalt()), getName());
			// 加入session
			SecurityUtils.getSubject().getSession()
					.setAttribute(CommonConstant.KEY_LOGIN_USER, user);
			return info;
		}

		return null;
	}
}
