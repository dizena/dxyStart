package com.aat.dxfy.base.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aat.dxfy.base.CommonConstant;
import com.aat.dxfy.base.bean.User;
import com.aat.dxfy.base.service.UserService;
import com.aat.utils.Atools;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 用户注册
 * @info java springMVC
 * @update 无
 */
@Controller
@Scope("prototype")
public class RegisterController extends BaseController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "reg", method = RequestMethod.GET)
	public String reg() {

		return "/reg";
	}

	@RequestMapping(value = "reg", method = RequestMethod.POST)
	public String reg(HttpServletRequest request, Model model) {
		String e = request.getParameter("e");
		String c = request.getParameter("c");
		String p1 = request.getParameter("p1");
		String p2 = request.getParameter("p2");
		model.addAttribute("e", e);
		Object eCode = getSession().getAttribute("email_" + e);
		if(null==c||null==eCode||!c.equalsIgnoreCase((String) eCode)){
			model.addAttribute("error", "验证码错误！");
			return "/reg";
		}
		
		if (Atools.checkEmail(e)) {
			if (null == userService.queryUserLogin(e)) {
				if (StringUtils.isNotEmpty(p1) && p1.equals(p2)) {
					User user = new User();
					user.setId(Atools.getOneKeyS());
					user.setPasswd(Atools.makePwd(p1));
					user.setSalt(Atools.jiami(p1));
					user.setEmail(e);
					user.setRoles("user");
					user.setAuths("none");
					user.setLocked("0");
					user.setRegTime(new Date());
					user.setFlag("dxy");
					user.setNickname(Atools.getNameFromEmail(e));
					user.setLocation("");
					user.setSystime(new Date());
					userService.addUser(user);
					model.addAttribute("u", e);
					model.addAttribute("error", "注册成功，请登录！");
					return CommonConstant.WEB_LOGIN_INDEX;
				} else {
					model.addAttribute("error", "密码不一致！");
				}

			} else {
				model.addAttribute("error", "邮箱已存在！");
			}
		} else {
			model.addAttribute("error", "邮箱格式错误！");
		}

		return "/reg";
	}
	
	// 手机注册，验证，加入登录
	public String regPhone(
			@RequestParam("p") String p,
			@RequestParam("c") String c,
			@RequestParam("p1") String p1,
			@RequestParam("p2") String p2,
			RedirectAttributes redirectAttributes){
		Object o = getSession().getAttribute("phone_" + p);
		String code = null;
		if (null != o) {
			code = (String) o;
		}
		if (code.equals(c)&&p1.equals(p2)) {
			User u=new User();
			u.setId(Atools.getOneKeyS());
			u.setPhone(p);
			u.setPasswd(Atools.makePwd(p1));
			u.setSalt(Atools.jiami(p1));
			//
			u.setNickname(p.substring(0,3)+"****"+p.substring(p.length()-4, p.length()));
			u.setFlag("dxy");
			u.setRoles("user");
			u.setAuths("none");
			u.setLocked("0");
			u.setRegTime(new Date());
			u.setSystime(new Date());
			userService.addUser(u);
		}
		redirectAttributes.addFlashAttribute("msg", "注册成功");
		return "redirect:/";
	}

}
