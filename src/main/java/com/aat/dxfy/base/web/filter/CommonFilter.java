package com.aat.dxfy.base.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 全局的过滤器,防止XSS注入，增加路径常量path
 * @info java,filter
 * @update 无
 */
@WebFilter("/*")
public class CommonFilter implements Filter {

	public CommonFilter() {
	}

	public void destroy() {
	}

	/**
	 * @info 过滤器的功能实现
	 */
	public void doFilter(ServletRequest req, ServletResponse rep,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rep;
		// 路径信息加入SESSION
		request.getSession().setAttribute("path", request.getContextPath());
		//chain.doFilter(request, response);
		
		//XSS
		chain.doFilter(new XssHttpServletRequestWrapper(request), response);
		
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
