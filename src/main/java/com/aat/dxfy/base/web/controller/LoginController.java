package com.aat.dxfy.base.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aat.dxfy.base.CommonConstant;
import com.aat.dxfy.base.web.controller.BaseController;
import com.aat.utils.Atools;

@Controller
@Scope("prototype")
public class LoginController extends BaseController {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login0(HttpServletRequest request, Model model) {
		String error = "";
		String jsp = CommonConstant.WEB_LOGIN_INDEX;
		if ("1".equals(request.getParameter("kickout"))) {
			error = "你在其他设备登录！";
			model.addAttribute("error", error);
			return jsp;
		}
		if ("1".equals(request.getParameter("forceLogout"))) {
			error = "您被强制退出！";
			model.addAttribute("error", error);
			return jsp;
		}
		return jsp;
	}

	//
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login1(HttpServletRequest request, Model model) {
		String jsp = CommonConstant.WEB_LOGIN_INDEX;
		String error = "";
		String x = request.getParameter("x");
		String u = request.getParameter("u");
		String p = request.getParameter("p");
		String c = request.getParameter("c");
		model.addAttribute("u", u);
		log.info(x + "  " + u + "  " + p + "  " + c);

		Object imgCode = getSession().getAttribute(CommonConstant.KEY_IMG_CODE);
		if (null == x || null == imgCode
				|| !x.equalsIgnoreCase((String) imgCode)) {
			error = "验证码错误！";
			model.addAttribute("error", error);
			return jsp;
		}

		try {
			u=Atools.jsJieMi(u);
			p=Atools.jsJieMi(p);
			UsernamePasswordToken token = new UsernamePasswordToken(u,
					Atools.makePwd(p));
			token.setRememberMe("true".equalsIgnoreCase(c));
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.login(token);
			if (currentUser.isAuthenticated()) {

				return CommonConstant.WEB_INDEX;
			} else {
				error = "用户名或密码错误！";
				model.addAttribute("error", error);
				return jsp;
			}
		} catch (UnknownAccountException e) {
			error = "用户名或密码错误！";
			model.addAttribute("error", error);
			return jsp;
		} catch (LockedAccountException e) {
			error = "此账户锁定，请联系管理员！";
			model.addAttribute("error", error);
			return jsp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("error", "用户名或密码错误！");
		return jsp;
	}

	@RequestMapping(value = { "logout", "logout.do" })
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();
		} catch (Exception e) {
		}
		return "redirect:" + CommonConstant.WEB_LOGIN_INDEX;
	}

	@RequestMapping(value = { "none" })
	public String none(Model model) {
		model.addAttribute("error", "没有此操作的权限！");
		return "/none";
	}

}
