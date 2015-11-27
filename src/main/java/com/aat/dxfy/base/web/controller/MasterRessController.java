package com.aat.dxfy.base.web.controller;

import java.util.ArrayList;
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

import com.aat.dxfy.base.bean.Ress;
import com.aat.dxfy.base.service.RessService;
import com.aat.utils.Atools;

/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 资源管理
 * @info java springMVC
 * @update 无
 */
@Controller
@Scope("prototype")
@RequestMapping("master")
public class MasterRessController extends BaseController {
	@Autowired
	private RessService ressService;

	@RequestMapping(value = "ressLists", method = RequestMethod.GET)
	public String ressLists() {
		return "/master/ress/ressLists";
	}

	@RequestMapping(value = "ressLists", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ressLists(
			@RequestParam(value = "s", required = false, defaultValue = "1") int start,
			@RequestParam(value = "p", required = false, defaultValue = "10") int perPage,
			@RequestParam(value = "f", required = false, defaultValue = "resSort") String sortFiled,
			@RequestParam(value = "dir", required = false, defaultValue = "ASC") String direction,

			@RequestParam(value = "type", required = false) String resType,
			@RequestParam(value = "name", required = false) String resName,
			@RequestParam(value = "level", required = false) Integer resLevel,
			@RequestParam(value = "pid", required = false) String resPid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sortFiled", sortFiled);
		params.put("direction", direction);
		params.put("start", (start - 1) * perPage);
		params.put("perpage", perPage);
		// 其他参数
		if (StringUtils.isNotEmpty(resType)) {
			params.put("resType", resType);
		}
		if (StringUtils.isNotEmpty(resName)) {
			params.put("resName", resName);
		}
		if (null != resLevel) {
			params.put("resLevel", resLevel);
		}
		if (StringUtils.isNotEmpty(resPid)) {
			params.put("resPid", resPid);
		}

		// 返回参数
		List<Ress> lists = ressService.queryAll(params);
		Long count = ressService.queryAllCount(params);
		Long tmp = count / perPage;
		Long pages = count % perPage == 0 ? tmp : tmp + 1;
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("datas", lists);// 数据
		json.put("pages", pages);// 总页
		json.put("start", start);// 页码
		json.put("count", count);// 总数
		return json;
	}

	@RequestMapping(value = "addRess", method = RequestMethod.GET)
	public String addRess(Model model) {
		model.addAttribute("action", "master/addRess");
		model.addAttribute("title", "增加资源");
		Ress ress = new Ress();
		ress.setId(Atools.getOneKeyS());
		ress.setResAuth("none");
		ress.setResIcon("fa fa-star");
		ress.setResType("1");
		ress.setResPid("0");
		// ress.setResSort(ressService.queryResSort("1", "1", "0"));
		model.addAttribute("m", ress);
		return "/master/ress/ressEdit";
	}

	@RequestMapping(value = "addRess", method = RequestMethod.POST)
	public String addRess(Ress ress, Model model) {
		model.addAttribute("action", "master/addRess");
		model.addAttribute("title", "增加资源");
		model.addAttribute("m", ress);

		String jsp = "/master/ress/ressEdit";
		if (StringUtils.isEmpty(ress.getResName())) {
			model.addAttribute("error", "名称不为空");
			return jsp;
		}
		if (StringUtils.isEmpty(ress.getResUrl())) {
			model.addAttribute("error", "链接不为空");
			return jsp;
		}

		if (!"1".equals(ress.getResType())) {
			ress.setResLevel(1);
			ress.setResPid("0");
		}

		ress.setLocked("0");
		ressService.addBean(ress);
		return "redirect:/master/ressLists";
	}

	@RequestMapping(value = "uptRess/{id}", method = RequestMethod.GET)
	public String uptRess(@PathVariable("id") String id, Model model) {
		model.addAttribute("action", "master/uptRess");
		model.addAttribute("title", "修改资源");
		Ress ress = ressService.queryBean(id);
		model.addAttribute("m", ress);
		return "/master/ress/ressEdit";
	}

	@RequestMapping(value = "uptRess", method = RequestMethod.POST)
	public String uptRess(Ress ress, Model model) {
		model.addAttribute("action", "master/uptRess");
		model.addAttribute("title", "修改资源");
		model.addAttribute("m", ress);
		String jsp = "/master/ress/ressEdit";
		if (StringUtils.isEmpty(ress.getResName())) {
			model.addAttribute("error", "名称不为空");
			return jsp;
		}
		if (StringUtils.isEmpty(ress.getResUrl())) {
			model.addAttribute("error", "链接不为空");
			return jsp;
		}
		if (!"1".equals(ress.getResType())) {
			ress.setResLevel(1);
			ress.setResPid("0");
		}

		ressService.updateBean(ress);

		return "redirect:/master/ressLists";
	}

	@RequestMapping(value = "delRess/{id}", method = RequestMethod.GET)
	public String delRess(@PathVariable("id") String id) {
		ressService.deleteBean(id);
		return "redirect:/master/ressLists";
	}

	@RequestMapping(value = "lockRess")
	public String lockRess(@RequestParam("id") String id,
			@RequestParam("s") String s) {
		Ress r = new Ress();
		r.setId(id);
		r.setLocked(s);
		ressService.updateBean(r);
		return "redirect:/master/ressLists";
	}

	// 取得排序的数据
	@RequestMapping("getResSort")
	@ResponseBody
	public Integer getResSort(@RequestParam("t") String t,
			@RequestParam("l") Integer l, @RequestParam("p") String p) {
		Ress tmp = ressService.queryBean(p);
		int i = ressService.queryResSort(t, l, p);
		if (tmp != null && i == 1) {
			i = tmp.getResSort() * 100 + i;
		}
		return i;
	}

	// 取得父级菜单
	@RequestMapping("queryParentRes")
	@ResponseBody
	public List<Ress> queryParentRes(@RequestParam("t") String t,
			@RequestParam("l") String l) {
		List<Ress> ress = new ArrayList<Ress>();
		if ("1".equals(l)) {
			Ress r = new Ress();
			r.setId("0");
			r.setResName("根目录");
			ress.add(r);
		} else if ("2".equals(l)) {
			ress = ressService.queryParentRes(t, 1);
		} else if ("3".equals(l)) {
			ress = ressService.queryParentRes(t, 2);
		}
		return ress;
	}

	// 取得菜单json
	@RequestMapping("getMTree")
	@ResponseBody
	public List<Map<String, Object>> getMenuTreeData() {
		List<Map<String, Object>> lists=new ArrayList<Map<String,Object>>();
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("id", "0");
		json.put("text", "所有菜单");
		json.put("nodes", getMenuTreeData("0", 1));
		lists.add(json);
		return lists;
	}
	
	public List<Map<String, Object>> getMenuTreeData(String pid,Integer level){
		List<Map<String, Object>> lists=new ArrayList<Map<String,Object>>();
		List<Ress> ress = ressService.queryResByLP(pid, level);
		for (Ress r : ress) {
			if(r!=null){
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", r.getId());
				m.put("text", r.getResName());
				List<Map<String, Object>> tmpLists =getMenuTreeData(r.getId(), r.getResLevel()+1);
				if(tmpLists.size()>0){
					m.put("nodes", tmpLists);
				}
				
				lists.add(m);
			}
		}
		return lists;
	}

	// /////////////////////////////////////
	void test() {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("id", "");
		json.put("text", "");
		json.put("nodes", getQTree("0", 1));
	}

	public List<QTree> getQTree(String p, Integer l) {
		List<QTree> ts = new ArrayList<QTree>();
		List<Ress> ress1 = ressService.queryResByLP(p, l);
		for (Ress ress : ress1) {
			if (null != ress) {
				QTree q = new QTree();
				q.id = ress.getId();
				q.text = ress.getResName();
				q.children = getQTree(ress.getId(), ress.getResLevel() + 1);
				ts.add(q);
			}

		}
		return ts;
	}

	class QTree {
		String id;
		String url;// tree组件一般用于菜单，url为菜单对应的地址
		String text;// 显示文字
		boolean checked = false;// 是否选中
		List<QTree> children;// 子tree
	}
}
