package com.aat.dxfy.base.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aat.dxfy.base.bean.Urlauth;

import javax.annotation.PostConstruct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function shiro 权限业务
 * @info java spring 
 * @update 无
 */
@Service
@SuppressWarnings("restriction")
public class ShiroFilerChainManager {

	@Autowired
	private DefaultFilterChainManager filterChainManager;

	private Map<String, NamedFilterList> defaultFilterChains;

	
	@PostConstruct
	public void init() {
		defaultFilterChains = new HashMap<String, NamedFilterList>(
				filterChainManager.getFilterChains());
	}

	public void initFilterChains(List<Urlauth> urlFilters) {
		// 1、首先删除以前老的filter chain并注册默认的
		filterChainManager.getFilterChains().clear();
		
		if (defaultFilterChains != null) {
			filterChainManager.getFilterChains().putAll(defaultFilterChains);
		}
		// 2、循环URL Filter 注册filter chain
		for (Urlauth urlFilter : urlFilters) {
			String url = urlFilter.getUrl();
			// 注册roles filter
			if (StringUtils.isNotEmpty(urlFilter.getRoles())) {
				filterChainManager.addToChain(url, "roles",urlFilter.getRoles());
			}
			// 注册perms filter
			if (!StringUtils.isNotEmpty(urlFilter.getAuths())) {
				filterChainManager.addToChain(url, "perms",urlFilter.getAuths());
			}
		}
	}

}
