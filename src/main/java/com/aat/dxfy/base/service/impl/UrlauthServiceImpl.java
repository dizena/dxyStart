package com.aat.dxfy.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aat.dxfy.base.bean.Urlauth;
import com.aat.dxfy.base.dao.UrlauthDao;
import com.aat.dxfy.base.service.UrlauthService;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function url权限业务
 * @info java spring 
 * @update 无
 */
@Service
@SuppressWarnings("restriction")
public class UrlauthServiceImpl implements UrlauthService {
	@Autowired
	private UrlauthDao urlauthDao;
	@Autowired
	private ShiroFilerChainManager shiroFilerChainManager;
	
	@PostConstruct
	public void initFilterChain() {
		shiroFilerChainManager.initFilterChains(queryAll());
	}

	public int addUrlauth(Urlauth ua) {
		int i=urlauthDao.addBean(ua);
		initFilterChain();;
		return i;
	}

	public int updateUrlauth(Urlauth ua) {
		int i= urlauthDao.updateBean(ua);
		initFilterChain();;
		return i;
	}

	public int deleteUrlauth(String id) {
		int i =urlauthDao.deleteBean(id);
		initFilterChain();;
		return i;
	}

	public Urlauth queryUrlauth(String id) {
		return urlauthDao.queryBean(id);
	}

	public List<Urlauth> queryAll(Map<String, Object> map) {
		return urlauthDao.queryAll(map);
	}
	
	public List<Urlauth> queryAll() {
		Map<String, Object> map=new HashMap<String, Object>();
		return urlauthDao.queryAll(map);
	}

	public long queryAllCount(Map<String, Object> map) {
		return urlauthDao.queryAllCount(map);
	}

}
