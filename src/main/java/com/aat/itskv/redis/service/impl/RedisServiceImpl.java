package com.aat.itskv.redis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aat.itskv.redis.dao.RedisDao;
import com.aat.itskv.redis.service.RedisService;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function K、V数据存储
 * @info java spring data redis
 * @update 无
 */
@Service
public class RedisServiceImpl implements RedisService {
	@Autowired
	private RedisDao redisDao;
	
	public void addKV(String k, Object v) {
		redisDao.addKV(k, v);
	}

	public void delKV(String k) {
		redisDao.delKV(k);
	}

	public Object getKV(String k) {
		return redisDao.getKV(k);
	}

	public void addIfNotKV(String k, Object v) {
		if(getKV(k)==null){
			redisDao.addKV(k, v);
		}
	}

}
