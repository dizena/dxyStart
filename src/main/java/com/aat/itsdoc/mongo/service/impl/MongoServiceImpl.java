package com.aat.itsdoc.mongo.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.aat.itsdoc.mongo.dao.MongoDao;
import com.aat.itsdoc.mongo.service.MongoService;
/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function doc json 数据存储
 * @info java spring data mongoDB
 * @update 无
 */
@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MongoServiceImpl implements MongoService {

	@Autowired
	private MongoDao mongoDao;

	public Object save(Object obj) {
		mongoDao.save(obj);
		return obj;
	}

	public void delete(Object obj) {
		mongoDao.delete(obj);
	}

	public <T> void deleteById(Serializable id, Class<T> clazz) {
		mongoDao.deleteById(id, clazz);
	}

	public <T> int update(Query query, Update update, Class<T> clazz) {
		return mongoDao.update(query, update, clazz);
	}

	public <T> T findOne(Class<T> clazz, Query query) {
		return (T) mongoDao.findOne(clazz, query);
	}

	public <T> T findById(Class<T> clazz, Object id) {
		return (T) mongoDao.findById(clazz, id);
	}

	public <T> List<T> findAll(Class<T> clazz) {
		return mongoDao.findAll(clazz);
	}

	public <T> List<T> find(Class<T> clazz, Query query) {
		return mongoDao.find(clazz, query);
	}

	public <T> List<T> findList(Class<T> clazz, Query query, int currentPage, int pageSize) {
		return mongoDao.findList(clazz, query, currentPage, pageSize);
	}

	public <T> long findCount(Class<T> clazz, Query query) {
		return mongoDao.findCount(clazz, query);
	}
}
