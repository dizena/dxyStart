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

import com.aat.dxfy.base.bean.OuthCompany;
import com.aat.dxfy.base.service.OuthCompanyService;
import com.aat.utils.Atools;

/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function Oauth2 模块-企业管理
 * @info java springMVC
 * @update 无
 */
@Controller
@Scope("prototype")
@RequestMapping("master/ocomp")
public class MasterOauth2Controller extends BaseController {
	@Autowired
	private OuthCompanyService ocompanyService;

	private String ocompEdit = "/master/ocomp/ocompEdit";
	private String ocompList = "/master/ocomp/ocompList";

	// 1，企业oauth-管理
	@RequestMapping(value = "ocompList", method = RequestMethod.GET)
	public String ocompList() {

		return ocompList;
	}

	@RequestMapping(value = "ocompList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ocompList(
			@RequestParam(value = "s", required = false, defaultValue = "1") int start,
			@RequestParam(value = "p", required = false, defaultValue = "10") int perPage,
			@RequestParam(value = "f", required = false, defaultValue = "eename") String sortFiled,
			@RequestParam(value = "d", required = false, defaultValue = "ASC") String direction,

			@RequestParam(value = "p1", required = false) String eename,
			@RequestParam(value = "p2", required = false) String bindUserid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sortFiled", sortFiled);
		params.put("direction", direction);
		params.put("start", (start - 1) * perPage);
		params.put("perpage", perPage);
		// 其他参数
		if (StringUtils.isNotEmpty(eename)) {
			params.put("eename", eename);
		}
		if (StringUtils.isNotEmpty(bindUserid)) {
			params.put("bindUserid", bindUserid);
		}

		// 返回参数
		List<OuthCompany> lists = ocompanyService.queryAll(params);
		Long count = ocompanyService.queryAllCount(params);
		Long tmp = count / perPage;
		Long pages = count % perPage == 0 ? tmp : tmp + 1;
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("datas", lists);// 数据
		json.put("pages", pages);// 总页
		json.put("start", start);// 页码
		json.put("count", count);// 总数
		return json;
	}

	// 2，企业oauth-增加
	@RequestMapping(value = "addOComp", method = RequestMethod.GET)
	public String addOComp(Model model) {
		model.addAttribute("action", "master/ocomp/addOComp");
		model.addAttribute("title", "增加合作企业");

		OuthCompany oc = new OuthCompany();
		oc.setId(Atools.getOneKeyS());
		model.addAttribute("b", oc);
		return ocompEdit;
	}

	@RequestMapping(value = "addOComp", method = RequestMethod.POST)
	public String addOComp(OuthCompany oc, Model model) {
		model.addAttribute("action", "master/ocomp/addOComp");
		model.addAttribute("title", "增加合作企业");
		model.addAttribute("b", oc);
		//
		if (StringUtils.isEmpty(oc.getEename())) {
			model.addAttribute("error", "企业名称不可以为空！");
			return ocompEdit;
		}
		if (StringUtils.isEmpty(oc.getEeurl())) {
			model.addAttribute("error", "企业网址不可以为空！");
			return ocompEdit;
		}
		if (StringUtils.isEmpty(oc.getResposeUrl())) {
			model.addAttribute("error", "回跳地址不可以为空！");
			return ocompEdit;
		}
		if (StringUtils.isEmpty(oc.getEelogo())) {
			model.addAttribute("error", "企业LOGO不可以为空！");
			return ocompEdit;
		}
		oc.setFlag("1");
		oc.setSecret(Atools.getOneKeyS());
		oc.setSystime(new Date());
		ocompanyService.addBean(oc);
		//
		return "redirect:" + ocompList;
	}

	// 3，企业oauth-修改
	@RequestMapping(value = "editOComp/{id}", method = RequestMethod.GET)
	public String editOComp(@PathVariable("id") String id, Model model) {
		model.addAttribute("action", "master/ocomp/editOComp");
		model.addAttribute("title", "修改合作企业");

		OuthCompany oc = ocompanyService.queryBean(id);
		model.addAttribute("b", oc);
		return ocompEdit;
	}

	@RequestMapping(value = "editOComp", method = RequestMethod.POST)
	public String editOComp(OuthCompany oc, Model model) {
		model.addAttribute("action", "master/ocomp/editOComp");
		model.addAttribute("title", "修改合作企业");
		model.addAttribute("b", oc);
		//
		if (StringUtils.isEmpty(oc.getEename())) {
			model.addAttribute("error", "企业名称不可以为空！");
			return ocompEdit;
		}
		if (StringUtils.isEmpty(oc.getEeurl())) {
			model.addAttribute("error", "企业网址不可以为空！");
			return ocompEdit;
		}
		if (StringUtils.isEmpty(oc.getResposeUrl())) {
			model.addAttribute("error", "回跳地址不可以为空！");
			return ocompEdit;
		}
		if (StringUtils.isEmpty(oc.getEelogo())) {
			model.addAttribute("error", "企业LOGO不可以为空！");
			return ocompEdit;
		}
		ocompanyService.updateBean(oc);
		//
		return "redirect:" + ocompList;
	}

	// 4，企业oauth-删除
	@RequestMapping(value = "delOComp/{id}")
	public String delOComp(@PathVariable("id") String id) {
		if(chkRole("master")){
			ocompanyService.deleteBean(id);
		}
		
		return "redirect:" + ocompList;
	}

	// 5,企业oauth-审核
	@RequestMapping(value = "auditOComp/{id}/{s}")
	public String auditOComp(@PathVariable(value="id") String id,@PathVariable(value="s") String s) {
		if(chkRole("master")){
			OuthCompany oc=new OuthCompany();
			oc.setId(id);
			oc.setFlag(s);
			ocompanyService.updateBean(oc);
		}
		return "redirect:" + ocompList;
	}

	// 6,企业可以看见有哪些用户有过访问他的网站记录

	// 7，用户可以查看自己授权什么企业来访问他的资料，可以取消授权企业访问。

}
