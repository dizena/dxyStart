package com.aat.dxfy.base.web.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aat.dxfy.base.CommonConstant;
import com.aat.dxfy.base.assist.SafeComponent;
import com.aat.utils.Atools;
import com.aat.utils.MakeImage;

/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 公共的controller，封装一些发邮件，发短信，鉴权的接口等
 * @info java springMVC
 * @update 无
 */
@Controller
@Scope("prototype")
public class CommonController extends BaseController {
	@Autowired
	private SafeComponent safeComponent;

	@Value("#{configProperties['page.index']}")
	private String pageIndex;
	
	@RequestMapping({ "", "index" })
	public String allIndex() {
		if(StringUtils.isNotEmpty(pageIndex)){
			return pageIndex;
		}
		return CommonConstant.WEB_INDEX;
	}

	// 跳转动作
	@RequestMapping({ "forward", "forward.do" })
	public String url(@RequestParam(value = "url") String url,
			@RequestParam(value = "params", required = false) String params,
			HttpServletRequest request, Model model) {
		log.info("跳转的地址url:" + url + ",参数:" + ((params == null) ? "" : params));
		if (StringUtils.isNotEmpty(params)) {
			if (params.startsWith("{") && params.endsWith("}")) {
				String str = params.substring(params.indexOf("{") + 1,
						params.lastIndexOf("}"));
				String ss[] = str.split(",");
				for (int i = 0; i < ss.length; i++) {
					String[] sss = ss[i].split(":");
					if (StringUtils.isNotEmpty(sss[0])
							&& StringUtils.isNotEmpty(sss[1])) {
						model.addAttribute(sss[0], sss[1]);
					}
				}

			}
		}
		if (url.endsWith(".jsp")) {
			url = url.replace(".jsp", "");
		}
		return url;
	}

	// 检验是否登录和权限等
	@RequestMapping("setMenu")
	public void setMenu(
			@RequestParam(value = "lv1", required = false) String lv1,
			@RequestParam(value = "lv2", required = false) String lv2,
			@RequestParam(value = "lv3", required = false) String lv3) {
		getSession().setAttribute("lv1", 1);
		if (StringUtils.isNotEmpty(lv1)) {
			getSession().setAttribute("lv1", lv1);
		}
		if (StringUtils.isNotEmpty(lv2)) {
			getSession().setAttribute("lv2", lv2);
		}
		if (StringUtils.isNotEmpty(lv3)) {
			getSession().setAttribute("lv3", lv3);
		}
	}

	// 检验是否登录和权限等
	@RequestMapping("chkLoginJson")
	@ResponseBody
	public int chkLoginJson() {
		if (null == getSessionUser()) {
			return 0;
		}
		return 1;
	}


	// 检验是否登录和权限等
	@RequestMapping("chkRoleJson")
	@ResponseBody
	public int chkRoleJson(@RequestParam(value = "role") String role) {
		if (chkRole(role)) {
			return 1;
		}
		return 0;
	}

	// 检验是否登录和权限等
	@RequestMapping("chkAuthJson")
	@ResponseBody
	public int chkAuthJson(@RequestParam(value = "auth") String auth) {
		if (chkAuth(auth)) {
			return 1;
		}
		return 0;
	}

	// 获得图片验证码
	@RequestMapping({ "getImgCode" })
	public void getImgCode(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, BufferedImage> m = MakeImage.birthCode();
		Set<Entry<String, BufferedImage>> s = m.entrySet();
		String code = null;
		BufferedImage img = null;
		for (Entry<String, BufferedImage> e : s) {
			code = e.getKey();
			img = e.getValue();
		}
		getSession().setAttribute(CommonConstant.KEY_IMG_CODE, code);
		try {
			OutputStream out = response.getOutputStream();
			ImageIO.write(img, "png", out);
			out.close();
		} catch (IOException e1) {
		}

	}

	// 远端验证图片验证码
	@RequestMapping({ "chkImgCode" })
	@ResponseBody
	public String chkImgCode(
			@RequestParam(value = "x", required = false, defaultValue = "~") String x) {
		Object imgCode = getSession().getAttribute(CommonConstant.KEY_IMG_CODE);
		if (null != imgCode && x.equalsIgnoreCase((String) imgCode)) {
			return "1";
		}
		return "0";
	}

	// 获得短信验证码
	@RequestMapping({ "getPhoneCode" })
	public void sendPhoneCode(@RequestParam("phone") String phone,HttpServletRequest request) {
		try {
			String code = Atools.birthNumString(4);
			getApplication(request).setAttribute(phone, code);
			//验证后 getApplication(request).setAttribute(phone, null);
			// 发送手机验证码

		} catch (Exception e) {

		}
	}

	// 获得邮箱验证码
	@RequestMapping(value = "getEmailCode", method = RequestMethod.POST)
	@ResponseBody
	public String sendEmailCode(@RequestParam("e") String email) {
		try {
			if (Atools.checkEmail(email)) {
				String code = Atools.birthNumString(6);
				getSession().setAttribute("email_" + email, code);
				safeComponent.sendEmailCode(email, code);
				return "1";
			}
		} catch (MessagingException e) {

		}
		return "0";
	}

	// 获得邮箱验证链接
	@Value("#{configProperties['email.link']}")
	private String elink;

	@RequestMapping({ "getEmailLink" })
	public void sendEmailLink(@RequestParam("e") String email) {
		try {
			long time = new Date().getTime() + 600000;
			String link = elink;
			link += "?e=" + Atools.jiami(email);
			link += "&t="
					+ Atools.jiami(Atools.getStrFmtTime("yyyy-MM-dd HH:mm:ss",
							new Date(time)));
			safeComponent.sendEmailLink(email, link);
		} catch (MessagingException e) {

		}
	}

}
