package com.aat.itskv.redis.service;

public interface RedisService {
	
	public void addKV(String k, Object v);

	public void delKV(String k);

	public Object getKV(String k);
	
	public void addIfNotKV(String k, Object v);
}
