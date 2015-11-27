package com.aat.dxfy.base.web.taglib;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import com.aat.dxfy.base.CommonConstant;

/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 自定义的标签函数
 * @info java servlet jstl 
 * @update 无
 */
public class DxyFun {

	// XSS转义的数据进行恢复
	public static String escapeXSS(String str) {
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		return StringEscapeUtils.unescapeHtml4(str);
	}

	// 科学逗号计数法
	public static String addComma(String str) {
		boolean neg = false;
		if (str.startsWith("-")) {
			str = str.substring(1);
			neg = true;
		}
		String tail = null;
		if (str.indexOf('.') != -1) {
			tail = str.substring(str.indexOf('.'));
			str = str.substring(0, str.indexOf('.'));
		}
		StringBuilder sb = new StringBuilder(str);
		sb.reverse();
		for (int i = 3; i < sb.length(); i += 4) {
			sb.insert(i, ',');
		}
		sb.reverse();
		if (neg) {
			sb.insert(0, '-');
		}
		if (tail != null) {
			sb.append(tail.length() > 2 ? tail.subSequence(0, 3) : tail);
		}
		return sb.toString();
	}

	// 科学逗号计数法，保留两位小数
	public static String addCommaF(String str) {
		boolean neg = false;
		if (str.startsWith("-")) {
			str = str.substring(1);
			neg = true;
		}
		String tail = null;
		if (str.indexOf('.') != -1) {
			tail = str.substring(str.indexOf('.'));
			str = str.substring(0, str.indexOf('.'));
		}
		StringBuilder sb = new StringBuilder(str);
		sb.reverse();
		for (int i = 3; i < sb.length(); i += 4) {
			sb.insert(i, ',');
		}
		sb.reverse();
		if (neg) {
			sb.insert(0, '-');
		}
		if (tail != null) {
			if (tail.length() > 2) {
				sb.append(tail.subSequence(0, 3));
			} else if (tail.length() == 2) {
				sb.append(tail.subSequence(0, 2) + "0");
			} else if (tail.length() == 1) {
				sb.append(tail.subSequence(0, 1) + "00");
			}

		} else {
			sb.append(".00");
		}
		return sb.toString();
	}

	// session 会话 时间
	public static String onlineTime(Session session) {
		if (null != session) {
			Date d1 = session.getStartTimestamp();
			Date d2 = new Date();
			long l3 = d2.getTime() - d1.getTime();
			long s = l3 / 1000;
			long m = s / 60;
			long h = m / 60;
			String str = "";
			if (h > 1) {
				m = m % 60;
				str = h + "时" + m + "分";
			} else {
				if (m > 1) {
					s = s % 60;
					str = m + "分" + s + "秒";
				} else {
					str = s + "秒";
				}
			}
			return str;
		}
		return "";
	}

	// session 会话得到登录用户
	public static String principal(Session session) {
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
		return "游客";
	}

	// session 会话 用户是否强制被退出
	public static boolean isForceLogout(Session session) {
		if (null != session) {
			return session
					.getAttribute(CommonConstant.SESSION_FORCE_LOGOUT_KEY) != null;
		}
		return false;
	}

}
