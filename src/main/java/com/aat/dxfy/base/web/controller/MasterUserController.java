package com.aat.dxfy.base.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aat.dxfy.base.bean.User;
import com.aat.dxfy.base.service.UserService;
import com.aat.dxfy.base.web.controller.BaseController;
import com.aat.utils.Atools;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 用户信息-管理
 * @info java springMVC
 * @update 无
 */
@Controller
@Scope("prototype")
@RequestMapping("master")
public class MasterUserController extends BaseController {
	@Autowired
	private UserService userService;

	@RequestMapping(value="userLists",method=RequestMethod.GET)
	public String userLists(){
		return "/master/user/userLists";
	}
	
	@RequestMapping(value="userLists",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userLists(
			@RequestParam(value = "s", required = false, defaultValue = "1") int start,
			@RequestParam(value = "p", required = false, defaultValue = "10") int perPage,
			@RequestParam(value = "f", required = false, defaultValue = "regTime") String sortFiled,
			@RequestParam(value = "dir", required = false, defaultValue = "DESC") String direction,

			@RequestParam(value = "u", required = false) String username) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sortFiled", sortFiled);
		params.put("direction", direction);
		params.put("start", (start - 1) * perPage);
		params.put("perpage", perPage);
		// 其他参数
		if (StringUtils.isNotEmpty(username)) {
			params.put("username", username);
		}

		// 返回参数
		List<User> lists = userService.queryAll(params);
		Long count = userService.queryAllCount(params);
		Long tmp = count / perPage;
		Long pages = count % perPage == 0 ? tmp : tmp + 1;
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("datas", lists);// 数据
		json.put("pages", pages);// 总页
		json.put("start", start);// 页码
		json.put("count", count);// 总数
		return json;
	}
	
	@RequestMapping(value = "addUser", method = RequestMethod.GET)
	public String addUser(Model model){
		model.addAttribute("action", "master/addUser");
		model.addAttribute("title", "增加用户");
		User user=new User();
		user.setId(Atools.getOneKeyS());
		user.setAuths("none");
		model.addAttribute("user", user);
		return "/master/user/userEdit";
	}
	
	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	public String addUser(User user,Model model){
		model.addAttribute("action", "master/addUser");
		model.addAttribute("title", "增加用户");
		model.addAttribute("user", user);
		String jsp="/master/user/userEdit";
		if(StringUtils.isEmpty(user.getUsername())){
			model.addAttribute("error", "登录账户不为空");
			return jsp;
		}
		if(StringUtils.isEmpty(user.getPasswd())){
			model.addAttribute("error", "登录密码不为空");
			return jsp;
		}
		if(!user.getPasswd().equals(user.getSalt())){
			model.addAttribute("error", "密码输入不一致！");
			return jsp;
		}
		if(StringUtils.isEmpty(user.getNickname())){
			model.addAttribute("error", "用户昵称不为空");
			return jsp;
		}
		user.setSalt(Atools.jiami(user.getPasswd()));
		user.setPasswd(Atools.makePwd(user.getPasswd()));
		user.setLocked("0");
		user.setRegTime(new Date());
		user.setFlag("dxy");
		userService.addUser(user);
		return "redirect:/master/userLists";
	}
	
	@RequestMapping(value = "uptUser/{id}.html", method = RequestMethod.GET)
	public String uptUser(@PathVariable  String id,Model model){
		model.addAttribute("action", "master/uptUser");
		model.addAttribute("title", "修改用户");
		User user=userService.queryUserId(id);
		model.addAttribute("user", user);
		return "/master/user/userEdit";
	}
	
	@RequestMapping(value = "uptUser", method = RequestMethod.POST)
	public String uptUser(User user,Model model){
		model.addAttribute("action", "master/uptUser");
		model.addAttribute("title", "修改用户");
		model.addAttribute("user", user);
		String jsp="/master/user/userEdit";
		if(StringUtils.isEmpty(user.getUsername())){
			model.addAttribute("error", "登录账户不为空");
			return jsp;
		}
		if(StringUtils.isEmpty(user.getNickname())){
			model.addAttribute("error", "用户昵称不为空");
			return jsp;
		}
		
		if(StringUtils.isNotEmpty(user.getPasswd())){
			if(!user.getPasswd().equals(user.getSalt())){
				model.addAttribute("error", "密码输入不一致！");
				return jsp;
			}
			user.setSalt(Atools.jiami(user.getPasswd()));//先设置加密解密的盐
			user.setPasswd(Atools.makePwd(user.getPasswd()));
		}else{
			user.setPasswd(null);
			user.setSalt(null);
		}
		
		userService.updateUser(user);
		return "redirect:/master/userLists";
	}
	
	
	@RequestMapping(value = "delUser/{id}", method = RequestMethod.GET)
	public String delUser(@PathVariable  String id){
		userService.deleteUser(id);
		return "redirect:/master/userLists";
	}
	
	@RequestMapping(value = "lockUser")
	public String lockUser(@RequestParam("id")  String id,@RequestParam("s")  String s){
		User u=new User();
		u.setId(id);
		u.setLocked(s);
		userService.updateUser(u);
		return "redirect:/master/userLists";
	}
	
}
