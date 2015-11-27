package com.aat.dxfy.base.web.controller;

import java.util.*;

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

import com.aat.dxfy.base.bean.Urlauth;
import com.aat.dxfy.base.service.UrlauthService;
import com.aat.utils.Atools;

/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function URL权限设置管理
 * @info java springMVC
 * @update 无
 */
@Controller
@Scope("prototype")
@RequestMapping("master/urlauth")
public class MasterUrlauthController extends BaseController {
	@Autowired
	private UrlauthService urlauthService;
	
	private String editStr="/master/urlauth/edit";
	private String listStr="/master/urlauth/lists";
	private String toListStr="redirect:"+listStr;
	
	@RequestMapping(value="lists",method=RequestMethod.GET)
	public String lists(){
		return listStr;
	}
	
	@RequestMapping(value="lists",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> lists(
			@RequestParam(value = "s", required = false, defaultValue = "1") int start,
			@RequestParam(value = "p", required = false, defaultValue = "10") int perPage,
			@RequestParam(value = "f", required = false, defaultValue = "uname") String sortFiled,
			@RequestParam(value = "dir", required = false, defaultValue = "DESC") String direction,

			@RequestParam(value = "u", required = false) String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sortFiled", sortFiled);
		params.put("direction", direction);
		params.put("start", (start - 1) * perPage);
		params.put("perpage", perPage);
		// 其他参数
		if (StringUtils.isNotEmpty(name)) {
			params.put("uname", name);
		}

		// 返回参数
		List<Urlauth> lists = urlauthService.queryAll(params);
		Long count = urlauthService.queryAllCount(params);
		Long tmp = count / perPage;
		Long pages = count % perPage == 0 ? tmp : tmp + 1;
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("datas", lists);// 数据
		json.put("pages", pages);// 总页
		json.put("start", start);// 页码
		json.put("count", count);// 总数
		return json;
	}
	
	//add
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("action", "master/urlauth/add");
		model.addAttribute("title", "增加URL过滤");
		Urlauth ua=new Urlauth();
		ua.setId(Atools.getOneKeyS());
		model.addAttribute("p", ua);
		return editStr;
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(Urlauth ua,Model model){
		model.addAttribute("action", "master/urlauth/add");
		model.addAttribute("title", "增加URL权限");
		model.addAttribute("p", ua);
		if(StringUtils.isEmpty(ua.getUname())){
			model.addAttribute("error", "URL权限名称不为空");
			return editStr;
		}
		if(StringUtils.isEmpty(ua.getUrl())){
			model.addAttribute("error", "URL路径不为空");
			return editStr;
		}
		if(StringUtils.isEmpty(ua.getRoles())){
			model.addAttribute("error", "URL所需角色不为空");
			return editStr;
		}
		if(StringUtils.isEmpty(ua.getAuths())){
			model.addAttribute("error", "URL所需权限不为空");
			return editStr;
		}
		if(StringUtils.isEmpty(ua.getId())){
			ua.setId(Atools.getOneKeyS());
		}
		urlauthService.addUrlauth(ua);
		return toListStr;
	}
	
	//upt
	@RequestMapping(value="upt/{id}",method=RequestMethod.GET)
	public String upt(@PathVariable("id") String id, Model model){
		model.addAttribute("action", "master/urlauth/upt");
		model.addAttribute("title", "修改URL过滤");
		Urlauth ua=urlauthService.queryUrlauth(id);
		model.addAttribute("p", ua);
		return editStr;
	}
	
	@RequestMapping(value="upt",method=RequestMethod.POST)
	public String upt(Urlauth ua,Model model){
		model.addAttribute("action", "master/urlauth/upt");
		model.addAttribute("title", "修改URL权限");
		model.addAttribute("p", ua);
		if(StringUtils.isEmpty(ua.getUname())){
			model.addAttribute("error", "URL权限名称不为空");
			return editStr;
		}
		if(StringUtils.isEmpty(ua.getUrl())){
			model.addAttribute("error", "URL路径不为空");
			return editStr;
		}
		if(StringUtils.isEmpty(ua.getRoles())){
			model.addAttribute("error", "URL所需角色不为空");
			return editStr;
		}
		if(StringUtils.isEmpty(ua.getAuths())){
			model.addAttribute("error", "URL所需权限不为空");
			return editStr;
		}
		urlauthService.updateUrlauth(ua);
		return toListStr;
	}
	
	//del
	@RequestMapping(value="del/{id}",method=RequestMethod.GET)
	public String del(@PathVariable("id") String id, Model model){
		urlauthService.deleteUrlauth(id);
		return toListStr;
	}
	
	
}
