package com.aat.dxfy.base.web.controller;

import java.util.*;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.aat.utils.Atools;
import com.aat.dxfy.base.web.controller.BaseController;

import com.aat.dxfy.base.bean.User3job;
import com.aat.dxfy.base.service.User3jobService;

/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 用户信息-工作信息管理
 * @info java springMVC
 * @update 无
 */
@Controller
@Scope("prototype")
@RequestMapping("master/user3job")
public class MasterUser3jobController  extends BaseController{

    @Autowired
    private User3jobService user3jobService;

    private String list = "/master/user3job/list";
    private String edit = "/master/user3job/edit";
    private String reList = "redirect:" + list;
    private String addAction = "master/user3job/add";
    private String uptAction = "master/user3job/upt";
    private String delAction = "master/user3job/del";
    private String addTitle = "增加 ";
    private String uptTitle = "修改 ";
    private String listHead = " 管理";

    // list get
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model m) {
        m.addAttribute("addAction", addAction);
        m.addAttribute("uptAction", uptAction);
        m.addAttribute("delAction", delAction);
        m.addAttribute("listHead", listHead);
        m.addAttribute("list", list);
        return list;
    }

    // list post
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "s", required = false, defaultValue = "1") int start,
            @RequestParam(value = "p", required = false, defaultValue = "10") int perPage,
            @RequestParam(value = "f", required = false, defaultValue = "ts") String sortFiled,
            @RequestParam(value = "dir", required = false, defaultValue = "DESC") String direction,

        @RequestParam(value = "u", required = false) String skey) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sortFiled", sortFiled);
        params.put("direction", direction);
        params.put("start", (start - 1) * perPage);
        params.put("perpage", perPage);
        // 其他参数
        if (isNotEmpty(skey)) {
            params.put("skey", skey);
        }

        //  返回参数
        List<User3job> lists = user3jobService.queryAll(params);
        Long count = user3jobService.queryCount(params);
        Long tmp = count / perPage;
        Long pages = count % perPage == 0 ? tmp : tmp + 1;
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("datas", lists);// 数据
        json.put("pages", pages);// 总页
        json.put("start", start);// 页码
        json.put("count", count);// 总数
        return json;
    }

    // add get
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("action", addAction);
        model.addAttribute("title", addTitle);
        User3job m = new User3job();
        m.setId(Atools.getOneKeyS());
        model.addAttribute("m", m);
        return edit;
    }

    // add post
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(User3job m,Model model) {
        model.addAttribute("action", addAction);
        model.addAttribute("title", addTitle);
        model.addAttribute("m", m);
        // 验证

        //end 
        user3jobService.addBean(m);
        return reList;
    }

    // upt get
    @RequestMapping(value = "upt/{id}", method = RequestMethod.GET)
    public String upt(@PathVariable("id") String id,Model model) {
        model.addAttribute("action", uptAction);
        model.addAttribute("title", uptTitle);
        User3job m = user3jobService.queryBean(id);
        model.addAttribute("m", m);
        return edit;
    }

    // upt post
    @RequestMapping(value = "upt", method = RequestMethod.POST)
    public String upt(User3job m,Model model) {
        model.addAttribute("action", uptAction);
        model.addAttribute("title", uptTitle);
        model.addAttribute("m", m);
        // 验证

        //end 
        user3jobService.updateBean(m);
        return reList;
    }

    // del
    @RequestMapping(value = "del/{id}", method = RequestMethod.GET)
    public String del(@PathVariable("id") String id) {
        user3jobService.deleteBean(id);
        return reList;
    }

}
