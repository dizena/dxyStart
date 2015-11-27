package com.aat.dxfy.base.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aat.dxfy.base.bean.OuthCompany;
import com.aat.dxfy.base.bean.OuthToken;
import com.aat.dxfy.base.bean.User;
import com.aat.dxfy.base.service.OuthCompanyService;
import com.aat.dxfy.base.service.OuthTokenService;
import com.aat.dxfy.base.service.UserService;
import com.aat.utils.Atools;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function Oauth2的登录授权
 * @info java springMVC
 * @update 无
 */
@Controller
@Scope("prototype")
@RequestMapping("oauth")
public class Oauth2Controller extends BaseController {
	@Autowired
	private OuthTokenService tokenService;
	@Autowired
	private OuthCompanyService ocompanyService;
	@Autowired
	private UserService userService;

	// 1,验证企业的授权ID号，是否通过审核。{ID，企业名称，企业返回地址，授权ID,授权secret}
	@RequestMapping("authorize")
	public String authorize(@RequestParam("cid") String cid,
			@RequestParam("key") String key, Model model) {
		OuthCompany oc = ocompanyService.queryBean(cid);
		if (null!=oc && oc.getSecret().equals(key)&&"0".equals(oc.getFlag())) {
			model.addAttribute("oc", oc);//企业信息
			// 是否在线
			if (null != getSessionUser()) {
				return "/oauthOk";
			}
			// 不在线
			return "/oauthLogin";
		}
		model.addAttribute("error", "未授权的客户端。");
		return "/none";
	}

	// 2,进行登录，登陆后该用户的取资料tokenID返回给企业。记录该用户授权某个企业登录获取他的资料。
	@RequestMapping(value="oauthLogin",method=RequestMethod.POST)
	public String oauthLogin(
			@RequestParam("id") String cid,
			@RequestParam(value = "u", required = false) String u,
			@RequestParam(value = "p", required = false) String p, Model model) {
		// 1
		User user = null;
		if (null != getSessionUser()) {
			user = getSessionUser();
		} else {
			User tuser = userService.queryUserLogin(u);
			if (null != tuser && tuser.getPasswd().equals(Atools.makePwd(p))) {
				user = tuser;
			}
			shiroLogin(u, Atools.makePwd(p));
		}
		// 2
		if (null != user) {
			OuthCompany oc = ocompanyService.queryBean(cid);
			//加入token
			OuthToken ot= tokenService.queryBeanCUID(cid, user.getId());
			if(null==ot){
				String id = Atools.getOneKeyS();
				ot=new OuthToken();
				ot.setId(id);
				ot.setCompanyId(cid);
				ot.setUserId(user.getId());
				ot.setStartTime(new Date());
				ot.setEename(oc.getEename());
				ot.setFlag("0");
				tokenService.addBean(ot);
			}else{
				ot.setCompanyId(oc.getId());
				ot.setUserId(user.getId());
				ot.setStartTime(new Date());
				ot.setEename(oc.getEename());
				tokenService.updateBean(ot);
			}
			//返回客户端
			String url=oc.getResposeUrl()+"?code="+Atools.jiami(ot.getId());
			return "redirect:"+url;
		}
		model.addAttribute("id", cid);
		return "/oauthLogin";
	}
	
	//3，getUserinfo
	@RequestMapping(value="getInfo",method=RequestMethod.POST)//
	@ResponseBody
	public Map<String, Object> getInfo(@RequestParam("token") String token){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			String id = Atools.jiemi(token);
			OuthToken ot= tokenService.queryBean(id);
			long l1=new Date().getTime();
			if(null!=ot&&"0".equals(ot.getFlag())&&((l1-ot.getStartTime().getTime())<7*24*60*60*1000)){
				User u=userService.queryUserId(ot.getUserId());
				if(null!=u){
					map.put("nickname", u.getNickname());
					map.put("icon", u.getIcon());
					map.put("role", u.getRoles());
					map.put("auth", u.getAuths());
					map.put("sex", u.getSex());
					//email,phone等
					
				}
			}
		} catch (Exception e) {
		}
		return map;
	}

}
