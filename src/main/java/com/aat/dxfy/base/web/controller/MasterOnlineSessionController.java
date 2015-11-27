package com.aat.dxfy.base.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aat.dxfy.base.CommonConstant;
import com.aat.dxfy.base.bean.SessionOnline;
import com.aat.dxfy.base.web.taglib.DxyFun;
import com.aat.utils.Atools;

/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 在线用户管理
 * @info java springMVC
 * @update 无
 */
@Controller
@Scope("prototype")
@RequestMapping("master/online")
public class MasterOnlineSessionController extends BaseController {
	@Autowired
	private SessionDAO sessionDAO;

	private String onlineLists = "/master/online/list";

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(value = "s", required = false, defaultValue = "1") int start,
			@RequestParam(value = "p", required = false, defaultValue = "10") int perPage) {
		List<SessionOnline> allLists = getSessions();
		Long count = 0L + allLists.size();
		Long tmp = count / perPage;
		Long pages = count % perPage == 0 ? tmp : tmp + 1;
		List<SessionOnline> lists = new ArrayList<SessionOnline>();
		// 分页操作
		if (start > 0 && start <= pages) {
			int a = (start - 1) * perPage;
			int b = (int) (start * perPage > count ? count : start * perPage);
			lists = allLists.subList(a, b);
		}
		// 返回参数
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("datas", lists);// 数据
		json.put("pages", pages);// 总页
		json.put("start", start);// 页码
		json.put("count", count);// 总数
		return json;
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model) {

		return onlineLists;
	}

	@RequestMapping("forceLogout/{sid}")
	public String forceLogout(@PathVariable("sid") String sessionId,
			RedirectAttributes redirectAttributes) {
		try {
			Session session = sessionDAO.readSession(sessionId);
			if (session != null) {
				session.setAttribute(CommonConstant.SESSION_FORCE_LOGOUT_KEY,
						Boolean.TRUE);
			}
		} catch (Exception e) {
			/* ignore */
		}
		redirectAttributes.addFlashAttribute("msg", "强制退出成功！");
		return "redirect:" + onlineLists;
	}

	// 获得Sessions
	public List<SessionOnline> getSessions() {
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		// 改变结构
		List<Session> allS = new ArrayList<Session>();
		for (Session s : sessions) {
			allS.add(s);
		}
		// 按照时间排序
		Collections.sort(allS, new Comparator<Session>() {
			public int compare(Session o1, Session o2) {
				if (o1.getLastAccessTime().getTime() > o2.getLastAccessTime()
						.getTime()) {
					return -1;
				}
				return 1;
			}
		});
		// 去掉同一个IP且重复的用户
		Map<String, Session> mss = new HashMap<String, Session>();
		for (Session s : allS) {
			String key = s.getHost() + principal(s);
			if (mss.containsKey(key)) {
				destorySession(s.getId());
				// sessions.remove(s);
			} else {
				mss.put(key, s);
			}
		}
		// 得到有效的sessions
		List<SessionOnline> lists = new ArrayList<SessionOnline>();
		for (Map.Entry<String, Session> m : mss.entrySet()) {
			SessionOnline so = new SessionOnline();
			Session s = m.getValue();
			so.setId(s.getId().toString());
			so.setHost(s.getHost());
			so.setIsOut(DxyFun.isForceLogout(s) ? "1" : "0");
			so.setLastTime(Atools.getStrFmtTime("yyyy-MM-dd HH:mm:ss",
					s.getLastAccessTime()));
			so.setStartTime(Atools.getStrFmtTime("yyyy-MM-dd HH:mm:ss",
					s.getStartTimestamp()));
			so.setOnlineTime(DxyFun.onlineTime(s));
			so.setUname(principal(s));
			lists.add(so);
		}
		// 按照时间排序
		Collections.sort(lists, new Comparator<SessionOnline>() {
			public int compare(SessionOnline o1, SessionOnline o2) {
				try {
					Date d1 = Atools.getDateFmtStr("yyyy-MM-dd HH:mm:ss",
							o1.getLastTime());
					Date d2 = Atools.getDateFmtStr("yyyy-MM-dd HH:mm:ss",
							o2.getLastTime());
					if (d1.getTime() > d2.getTime()) {
						return -1;
					}
				} catch (Exception e) {
				}
				return 0;
			}
		});
		//ok
		return lists;
	}

	// 强制退出并删除
	public void destorySession(Serializable sessionId) {
		try {
			Session session = sessionDAO.readSession(sessionId);
			if (session != null) {
				session.setTimeout(0L);
			}
		} catch (Exception e) {
		}
	}

	// 得到用户名
	public String principal(Session session) {
		if (null != session) {
			PrincipalCollection principalCollection = (PrincipalCollection) session
					.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			if (null != principalCollection) {
				Object o = principalCollection.getPrimaryPrincipal();
				if (null != o) {
					return (String) o;
				}
			}
		}
		return "";
	}

	//
}
