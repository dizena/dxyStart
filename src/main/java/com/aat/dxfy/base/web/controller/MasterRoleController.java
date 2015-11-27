package com.aat.dxfy.base.web.controller;

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

import com.aat.dxfy.base.bean.Role;
import com.aat.dxfy.base.service.RoleService;
import com.aat.dxfy.base.web.controller.BaseController;
import com.aat.utils.Atools;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 角色管理
 * @info java springMVC
 * @update 无
 */
@Controller
@Scope("prototype")
@RequestMapping("master")
public class MasterRoleController extends BaseController {
	@Autowired
	private RoleService roleService;
	
	private String LIST="/master/role/roleLists";
	private String EDIT="/master/role/roleEdit";

	@RequestMapping(value = "roleLists", method = RequestMethod.GET)
	public String roleLists() {
		return LIST;
	}

	@RequestMapping(value = "roleLists", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> roleLists(
			@RequestParam(value = "s", required = false, defaultValue = "1") int start,
			@RequestParam(value = "p", required = false, defaultValue = "10") int perPage,
			@RequestParam(value = "f", required = false, defaultValue = "role") String sortFiled,
			@RequestParam(value = "dir", required = false, defaultValue = "DESC") String direction,

			@RequestParam(value = "r", required = false) String role) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sortFiled", sortFiled);
		params.put("direction", direction);
		params.put("start", (start - 1) * perPage);
		params.put("perpage", perPage);
		// 其他参数
		if (StringUtils.isNotEmpty(role)) {
			params.put("role", role);
		}

		// 返回参数
		List<Role> lists = roleService.queryAll(params);
		Long count = roleService.queryAllCount(params);
		Long tmp = count / perPage;
		Long pages = count % perPage == 0 ? tmp : tmp + 1;
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("datas", lists);// 数据
		json.put("pages", pages);// 总页
		json.put("start", start);// 页码
		json.put("count", count);// 总数
		return json;
	}

	@RequestMapping(value = "addRole", method = RequestMethod.GET)
	public String addRole(Model model) {
		model.addAttribute("action", "master/addRole");
		model.addAttribute("title", "增加角色");
		return EDIT;
	}

	@RequestMapping(value = "addRole", method = RequestMethod.POST)
	public String addRole(
			@RequestParam(value = "r", required = false) String role,
			@RequestParam(value = "d", required = false) String desc,
			@RequestParam(value = "s", required = false) String ressId,
			Model model) {
		model.addAttribute("action", "master/addRole");
		model.addAttribute("title", "增加角色");

		String jsp = EDIT;
		if (StringUtils.isEmpty(role)) {
			model.addAttribute("error", "角色的名称不为空");
			return jsp;
		}
		if (StringUtils.isEmpty(ressId)) {
			model.addAttribute("error", "角色的资源不为空");
			return jsp;
		}

		roleService.addRole(role, desc, ressId);
		return "redirect:/master/roleLists";
	}

	@RequestMapping(value = "uptRole/{id}", method = RequestMethod.GET)
	public String uptUser(@PathVariable(value = "id") String id, Model model) {
		model.addAttribute("action", "master/uptRole");
		model.addAttribute("title", "修改角色");
		Role r = roleService.queryRole(id);
		List<String> ressids = roleService.queryResIds(id);
		

		model.addAttribute("m", r);
		model.addAttribute("ressids", Atools.list2Str(ressids));
		// 要查出所有的资源，然后选中已有的资源

		return EDIT;
	}

	@RequestMapping(value = "uptRole", method = RequestMethod.POST)
	public String uptUser(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "r", required = false) String role,
			@RequestParam(value = "d", required = false) String desc,
			@RequestParam(value = "s", required = false) String ressId,
			Model model) {
		model.addAttribute("action", "master/uptRole");
		model.addAttribute("title", "修改角色");
		Role r = roleService.queryRole(id);
		List<String> ressids = roleService.queryResIds(id);
		model.addAttribute("m", r);
		model.addAttribute("ressids", Atools.list2Str(ressids));
		
		String jsp = EDIT;
		if (StringUtils.isEmpty(role)) {
			model.addAttribute("error", "角色的名称不为空");
			return jsp;
		}
		if (StringUtils.isEmpty(ressId)) {
			model.addAttribute("error", "角色的资源不为空");
			return jsp;
		}

		roleService.updateRole(id, role, desc, ressId);
		return "redirect:/master/roleLists";
	}

	@RequestMapping(value = "delRole/{id}", method = RequestMethod.GET)
	public String delRole(@PathVariable String id) {
		roleService.deleteRole(id);
		return "redirect:/master/roleLists";
	}

	@RequestMapping(value = "lockRole")
	public String lockRole(@RequestParam("id")  String id,@RequestParam("s")  String s){
		roleService.lockRole(id, s);
		return "redirect:/master/roleLists";
	}
	

}
