package com.aat.dxfy.base.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function XSS的编码侧置
 * @info java servlet
 * @update 无
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public String getHeader(String name) {
		return StringEscapeUtils.escapeHtml4(super.getHeader(name));
	}

	public String getQueryString() {
		return StringEscapeUtils.escapeHtml4(super.getQueryString());
	}

	public String getParameter(String name) {
		return StringEscapeUtils.escapeHtml4(super.getParameter(name));
	}

	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values != null) {
			int length = values.length;
			String[] escapseValues = new String[length];
			for (int i = 0; i < length; i++) {
				escapseValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
			}
			return escapseValues;
		}
		return super.getParameterValues(name);
	}

}