package com.aat.dxfy.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aat.dxfy.base.bean.Ress;
import com.aat.dxfy.base.dao.RessDao;
import com.aat.dxfy.base.service.RessService;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function 资源业务
 * @info java spring 
 * @update 无
 */
@Service
public class RessServiceImpl implements RessService {

	@Autowired
	private RessDao ressDao;

	public int addBean(Ress ress) {
		return ressDao.addBean(ress);
	}

	public int updateBean(Ress ress) {
		return ressDao.updateBean(ress);
	}

	public int deleteBean(String id) {
		return ressDao.deleteBean(id);
	}

	public Ress queryBean(String id) {
		return ressDao.queryBean(id);
	}

	public List<Ress> queryAll(Map<String, Object> map) {
		return ressDao.queryAll(map);
	}

	public List<Map<String, Object>> queryAllMap(Map<String, Object> map) {
		return ressDao.queryAllMap(map);
	}

	public long queryAllCount(Map<String, Object> map) {
		return ressDao.queryAllCount(map);
	}

	public Integer queryResSort(String resType, Integer resLevel, String resPid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resType", resType);
		map.put("resLevel", resLevel);
		map.put("resPid", resPid);

		Integer i= ressDao.queryResSort(map);
		if(null==i){
			i=1;
		}else{
			i++;
		}
		return i;
	}

	public List<Ress> queryParentRes(String resType, Integer resLevel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resType", resType);
		map.put("resLevel", resLevel);
		return ressDao.queryParentRes(map);
	}

	
	public List<Ress> queryResByLP(String resPid, Integer resLevel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resPid", resPid);
		map.put("resLevel", resLevel);
		return ressDao.queryResByLP(map);
	}

}
