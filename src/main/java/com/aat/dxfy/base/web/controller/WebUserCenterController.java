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
import org.springframework.web.bind.annotation.ResponseBody;

import com.aat.dxfy.base.CommonConstant;
import com.aat.dxfy.base.bean.User;
import com.aat.dxfy.base.service.UserService;
import com.aat.utils.Atools;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 用户个人中心
 * @info java springMVC
 * @update 无
 */
@Controller
@Scope("prototype")
public class WebUserCenterController extends BaseController {

	@Autowired
	private UserService userService;

	// 验证用户名,邮箱，手机是否存在
	@RequestMapping(value = "chkEPU",method=RequestMethod.POST)
	@ResponseBody
	public String chkEPU(@RequestParam("epu") String epu) {
		if (null != userService.queryUserLogin(epu)) {
			return "1";
		}
		return "0";
	}

	//邮箱修改密码
	@RequestMapping({ "chkEmailLink" })
	public String chkEmailLink(
			@RequestParam("e") String e,
			@RequestParam("t") String t,
			Model model) {
		try {
			String email = Atools.jiemi(e);
			String times = Atools.jiemi(t);
			if (Atools.getDateFmtStr("yyyy-MM-dd HH:mm:ss", times).getTime() > (new Date()
					.getTime())) {
				// 根据邮箱进行密码修改
				User u = userService.queryUserLogin(email);
				if (null != u) {
					model.addAttribute("id", u.getId());
					return "/chgpwd";
				}
			} else {
				// 此验证已过期
				model.addAttribute("error", "此验证已过期");
				return "/none";
			}
		} catch (Exception ex) {

		}
		model.addAttribute("error", "此链接有错误");
		return "/none";

	}
	
	// 增加或者修改基本信息（不含额，email,phone）
	public String uptBaseInfo() {

		return "";
	}

	// 增加邮箱，验证code
	public String uptEmail(
			@RequestParam("e") String e,
			@RequestParam("c") String c,
			Model model) {
		if(null==getSessionUser()){
			return CommonConstant.WEB_LOGIN_INDEX;
		}
		Object o = getSession().getAttribute("email_" + e);
		String code = null;
		if (null != o) {
			code = (String) o;
		}
		model.addAttribute("email", e);
		if (code.equals(c)) {
			User u=new User();
			u.setId(getSessionUser().getId());
			u.setEmail(e);
			userService.updateUser(u);
		}
		return "";
	}

	// 增加手机，验证code
	public String uptPhone(
			@RequestParam("p") String p,
			@RequestParam("c") String c,
			Model model) {
		if(null==getSessionUser()){
			return CommonConstant.WEB_LOGIN_INDEX;
		}
		Object o = getSession().getAttribute("phone_" + p);
		String code = null;
		if (null != o) {
			code = (String) o;
		}
		model.addAttribute("phone", p);
		if (code.equals(c)) {
			User u=new User();
			u.setId(getSessionUser().getId());
			u.setPhone(p);
			userService.updateUser(u);
		}
		return "";
	}

	//改变密码
	@RequestMapping(value = "uptpwd", method = RequestMethod.GET)
	public String uptpwd(){
		
		return "/uptpwd";
	}
	@RequestMapping(value = "uptpwd", method = RequestMethod.POST)
	public String uptpwd(HttpServletRequest request,Model model){
		String p=request.getParameter("p");
		String p1=request.getParameter("p1");
		String p2=request.getParameter("p2");
		User lu=getSessionUser();
		if(null!=lu){
			if(StringUtils.isNotEmpty(p)&& Atools.makePwd(p).equals(lu.getPasswd())){
				if(StringUtils.isNotEmpty(p1)&&p1.equals(p2)){
					User u=new User();
					u.setId(lu.getId());
					u.setPasswd(Atools.makePwd(p1));
					u.setSalt(Atools.jiami(p1));
					int i =userService.updateUser(u);
					if(i>0){
						model.addAttribute("error", "修改成功！");
						return CommonConstant.WEB_LOGIN_INDEX;
					}
				}else{
					model.addAttribute("error", "密码输入不一致！");
				}
			}else{
				model.addAttribute("error", "密码错误！");
			}
		}else{
			return CommonConstant.WEB_LOGIN_INDEX;
		}
		
		return "/uptpwd";
	}
	

	// 修改密码
	@RequestMapping(value = "chgpwd", method = RequestMethod.POST)
	public String chgpwd(HttpServletRequest request,Model model){
		String id=request.getParameter("id");
		String p1=request.getParameter("p1");
		String p2=request.getParameter("p2");
		if(StringUtils.isNotEmpty(p1)&&p1.equals(p2)){
			User u=new User();
			u.setId(id);
			u.setPasswd(Atools.makePwd(p1));
			u.setSalt(Atools.jiami(p1));
			int i =userService.updateUser(u);
			if(i>0){
				model.addAttribute("error", "修改成功！");
				return CommonConstant.WEB_LOGIN_INDEX;
			}
		}
		model.addAttribute("error", "密码输入不一致！");
		model.addAttribute("id", id);
		return "/chgpwd";
	}
	

}
