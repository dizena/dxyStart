package com.aat.dxfy.base.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aat.dxfy.base.CommonConstant;
import com.aat.dxfy.base.bean.Menu;
import com.aat.dxfy.base.bean.Ress;
import com.aat.dxfy.base.bean.User;
import com.aat.dxfy.base.service.RoleService;
import com.aat.utils.Atools;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 管理模块首页
 * @info java springMVC
 * @update 无
 */
@Controller
@Scope("prototype")
@RequestMapping("master")
public class MasterIndexController extends BaseController {

	@Autowired
	private RoleService roleService;

	@RequestMapping({ "", "index" })
	public String mindex(Model model) {
		if (null == getSessionUser()) {
			return CommonConstant.WEB_LOGIN_INDEX;
		} else {
			List<Menu> ress = getUserRess();
			if (null == ress || ress.size() == 0) {
				model.addAttribute("error", "没有权限");
				return "/none";
			}
		}
		return "/master/index";
	}

	@SuppressWarnings("unchecked")
	public  List<Menu> getUserRess(){
		List<Menu> menus=new ArrayList<Menu>();
		Object tmp= getSession().getAttribute("menus");
		if(null==tmp){
			//1,获得用户的角色
			User loginUser=getSessionUser();
			List<String> roles = Atools.str2List(loginUser.getRoles(), ",");
			
			//2,根据角色得到角色的资源
			Set<Ress> allRess=new HashSet<Ress>();
			for (String role : roles) {
				allRess.addAll(roleService.queryRoleRess(role));
			}
			
			//3,分类按照级别排序
			List<Ress> nowRess=new ArrayList<Ress>(allRess);
			Collections.sort(nowRess);
			
			//5,构建菜单数据结构
			menus=getMenus(nowRess, 1, "0");
			
			//6,加入session 或者 memcached，redis
			getSession().setAttribute("menus", menus);
		}else{
			menus=(List<Menu>) tmp;
		}
		
		return menus;
	}
	
	public List<Menu> getMenus(List<Ress> nowRess,Integer level,String pid){
		List<Menu> menus=new ArrayList<Menu>();
		for (Ress res : nowRess) {
			if(null!=res && (level==res.getResLevel())&& (pid.equals(res.getResPid()))){
				Menu m=new Menu(res.getResName(), res.getResUrl(), res.getResSort(), res.getResIcon());
				m.setSubs(getMenus(nowRess,res.getResLevel()+1,res.getId()));
				menus.add(m);
			}
		}
		return menus;
	}

	
}
