package com.aat.itskv.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDao {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public void addKV(String k, Object v) {
		redisTemplate.opsForValue().set(k, v);
	}

	public void delKV(String k) {
		redisTemplate.opsForValue().getOperations().delete(k);
	}

	public Object getKV(String k) {
		return redisTemplate.opsForValue().get(k);
	}

}
